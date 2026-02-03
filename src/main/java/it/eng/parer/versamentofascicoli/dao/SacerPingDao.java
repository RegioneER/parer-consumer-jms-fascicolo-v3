/*
 * Engineering Ingegneria Informatica S.p.A.
 *
 * Copyright (C) 2023 Regione Emilia-Romagna
 * <p/>
 * This program is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Affero General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 */

package it.eng.parer.versamentofascicoli.dao;

import io.smallrye.config.SmallRyeConfig;
import it.eng.parer.versamentofascicoli.AwsClient;
import it.eng.parer.versamentofascicoli.Constants;
import it.eng.parer.versamentofascicoli.dto.BackendStorage;
import it.eng.parer.versamentofascicoli.dto.ObjectStorageBackend;
import it.eng.parer.versamentofascicoli.exceptions.AppGenericRuntimeException;
import it.eng.parer.versamentofascicoli.exceptions.ErrorCategory;
import it.eng.parer.versamentofascicoli.exceptions.ObjectStorageException;
import it.eng.parer.versamentofascicoli.jpa.entities.sacerping.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.*;

import java.io.Serial;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.microprofile.config.ConfigProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

/**
 * @author Cappelli_F <Francesco.Cappelli@Regione.Emilia-Romagna.it>
 */
@ApplicationScoped
public class SacerPingDao {

    private static final Logger log = LoggerFactory.getLogger(SacerPingDao.class);

    @Inject
    @PersistenceUnit
    EntityManager entityManagerSacerPing;

    @Inject
    AwsClient s3Clients;

    public PigFascicoloObject findLockPigFascicoloObjectById(Long fascicoloId) {
        final LockModeType lockType = LockModeType.PESSIMISTIC_WRITE;
        log.debug("Leggo PigFascicoloObject {} e faccio lock {}", fascicoloId, lockType);
        Map<String, Object> properties = new HashMap<>();
        properties.put(Constants.JPA_PROPERTIES_TIMEOUT, 25000);
        /*
         * Attenzione, Oracle usa multi version control quindi il LockModeType.PESSIMISTIC_WRITE
         * impedisce scritture concorrenti ma ammette che qualcun altro legga questo record ma senza
         * gli eventuali update fatti in quest sessione
         */
        return entityManagerSacerPing.find(PigFascicoloObject.class, fascicoloId, lockType,
                properties);
    }

    public String getXmlVersamentoFascicolo(Long idFascicolo) {
        String queryString = "SELECT pxsf FROM PigXmlSacerFascicolo pxsf WHERE pxsf.pigFascicoloObject.idFascicoloObject = :idFascicolo AND pxsf.tiXmlSacer = 'XML_VERS'";

        Query query = entityManagerSacerPing.createQuery(queryString);
        query.setParameter("idFascicolo", idFascicolo);

        PigXmlSacerFascicolo xmlSacerFascicolo = (PigXmlSacerFascicolo) query.getSingleResult();
        return xmlSacerFascicolo.getBlXmlSacer();
    }

    public <T> T findWithLock(Class<T> entityClass, Long id) {
        try {
            return entityManagerSacerPing.find(entityClass, id, LockModeType.PESSIMISTIC_WRITE);
        } catch (Exception e) {
            throw AppGenericRuntimeException.builder().category(ErrorCategory.HIBERNATE).cause(e)
                    .message("Errore estrazione oggetto {0} con id {1} ", entityClass, id).build();
        }
    }

    public <T> T findWithLockExtended(Class<T> entityClass, Long id) {
        try {
            Map<String, Object> properties = new HashMap<>();
            properties.put("jakarta.persistence", PessimisticLockScope.EXTENDED);

            return entityManagerSacerPing.find(entityClass, id, LockModeType.PESSIMISTIC_WRITE,
                    properties);
        } catch (Exception e) {
            throw AppGenericRuntimeException.builder().category(ErrorCategory.HIBERNATE).cause(e)
                    .message("Errore estrazione oggetto {0} con id {1} ", entityClass, id).build();
        }
    }

    public void flushChanges() {
        entityManagerSacerPing.flush();
    }

    /**
     * Effettua l'aggiornamento dei contatori in un'operazione singola per demandare al database la
     * gestione della concorrenza. Aggiorna sempre PigSessioneIngest.niUnitaDocVers ed eventuali
     * altri contatori in base ai parametri che vengono passati.
     *
     * @param idSessioneIngest         primary key di {@link PigSessioneIngest}
     * @param incrementaVersatiOk      se true indica di aggiornare niUnitaDocVersOk
     * @param incrementaVersatiErrore  se true indica di aggiornare niUnitaDocVersErr
     * @param incrementaVersatiTimeout se true indica di aggiornare niUnitaDocVersTimeout
     * @return {@link PigSessioneIngest} aggiornato
     */
    public PigSessioneIngest aggiornaContatoriFascicoli(long idSessioneIngest,
            boolean incrementaVersatiOk, boolean incrementaVersatiErrore,
            boolean incrementaVersatiTimeout) {

        StoredProcedureQuery query = entityManagerSacerPing
                .createNamedStoredProcedureQuery("aggiornaContatoriFascicoli");
        query.setParameter("IDSESSIONEINGEST", idSessioneIngest);
        query.setParameter("INCREMENTAVERSATIOK", incrementaVersatiOk ? 1 : 0);
        query.setParameter("INCREMENTAVERSATIERRORE", incrementaVersatiErrore ? 1 : 0);
        query.setParameter("INCREMENTAVERSATITIMEOUT", incrementaVersatiTimeout ? 1 : 0);
        query.execute();
        // recupero il dato aggiornato
        PigSessioneIngest session = entityManagerSacerPing.find(PigSessioneIngest.class,
                idSessioneIngest);
        entityManagerSacerPing.refresh(session);

        if (session.getNiFascicoliVersTimeout() == null) {
            session.setNiFascicoliVersTimeout(BigDecimal.ZERO);
        }
        if (session.getNiFascicoliVersOk() == null) {
            session.setNiFascicoliVersOk(BigDecimal.ZERO);
        }
        if (session.getNiFascicoliVersErr() == null) {
            session.setNiFascicoliVersErr(BigDecimal.ZERO);
        }
        return session;
    }

    public Long countFascicoliInPigObject(PigObject object, String state, String errCode) {
        Query q = entityManagerSacerPing.createQuery("SELECT count(fascicolo) "
                + "FROM PigObject obj JOIN obj.pigFascicoloObjects fascicolo "
                + "WHERE obj.idObject = :objId " + "AND fascicolo.tiStatoFascicoloObject = :state "
                + "AND fascicolo.cdErrSacer = :errCode ");
        q.setParameter("objId", object.getIdObject());
        q.setParameter("state", state);
        q.setParameter("errCode", errCode);
        return (Long) q.getSingleResult();
    }

    public void creaStatoSessione(PigSessioneIngest pigSessioneIngest, String statoSessione,
            LocalDateTime dtRegStato) {
        PigStatoSessioneIngest pigStatoSessione = new PigStatoSessioneIngest();
        pigStatoSessione.setPigSessioneIngest(pigSessioneIngest);
        pigStatoSessione.setTiStato(statoSessione);
        pigStatoSessione.setTsRegStato(dtRegStato);
        pigStatoSessione.setIdVers(pigSessioneIngest.getPigVer().getIdVers());
        entityManagerSacerPing.persist(pigStatoSessione);
        pigSessioneIngest.setIdStatoSessioneIngestCor(
                new BigDecimal(pigStatoSessione.getIdStatoSessioneIngest()));
        // entityManagerSacerPing.flush();
    }

    /**
     * Calcola lo stato che l'oggetto padre dovrà assumere come da analisi (punto 2.11.2)
     *
     * @param idObjectPadre id padre
     * @return valore calcolo
     */
    public String getCalcoloStatoObjDaTrasformare(Long idObjectPadre) {
        MonVCalcStatoObjDaTrasf obj = entityManagerSacerPing.find(MonVCalcStatoObjDaTrasf.class,
                new BigDecimal(idObjectPadre));
        return (obj == null ? null : obj.getTiStatoObjectPadre());
    }

    public PigSessioneIngest retrieveSessionByObject(PigObject object) {
        Query q = entityManagerSacerPing
                .createQuery("SELECT ses " + "FROM PigObject obj JOIN obj.pigSessioneIngests ses "
                        + "where obj.idObject = :objectId "
                        + "and obj.idLastSessioneIngest = ses.idSessioneIngest");
        q.setParameter("objectId", object.getIdObject());
        return (PigSessioneIngest) q.getSingleResult();
    }

    /**
     * Ottieni la configurazione del backend a partire dall'id del backend
     *
     * @param backendId chiave primaria del record
     * @return Informazioni sul Backend identificato
     * @throws ObjectStorageException in caso di errore
     */
    public BackendStorage getBackend(Long backendId) throws ObjectStorageException {
        try {

            DecBackend backend = entityManagerSacerPing.find(DecBackend.class, backendId);
            final BackendStorage.STORAGE_TYPE type = BackendStorage.STORAGE_TYPE
                    .valueOf(backend.getNmTipoBackend());
            final String backendName = backend.getNmBackend();
            final Long idBackend = backend.getIdDecBackend();

            return new BackendStorage() {
                @Serial
                private static final long serialVersionUID = 5092016605462729859L;

                @Override
                public BackendStorage.STORAGE_TYPE getType() {
                    return type;
                }

                @Override
                public String getBackendName() {
                    return backendName;
                }

                @Override
                public Long getBackendId() {
                    return idBackend;
                }
            };

        } catch (IllegalArgumentException | NonUniqueResultException e) {
            throw ObjectStorageException.builder()
                    .message("Impossibile ottenere le informazioni di backend").cause(e).build();
        }
    }

    public ObjectStorageBackend getObjectStorageConfigurationForVersamento(String nomeBackend,
            String nomeBucketUtilizzato) throws ObjectStorageException {
        ObjectStorageBackend objectStorageConfiguration = getObjectStorageConfiguration(nomeBackend,
                Constants.CONF_VERSAMENTO);
        return new ObjectStorageBackend() {
            private static final long serialVersionUID = -7032516962480163852L;

            @Override
            public String getBackendName() {
                return nomeBackend;
            }

            @Override
            public URI getAddress() {
                return objectStorageConfiguration.getAddress();
            }

            @Override
            public String getBucket() {
                return nomeBucketUtilizzato;
            }

            @Override
            public String getAccessKeyId() {
                return objectStorageConfiguration.getAccessKeyId();
            }

            @Override
            public String getSecretKey() {
                return objectStorageConfiguration.getSecretKey();
            }

            @Override
            public Long getBackendId() {
                return objectStorageConfiguration.getBackendId();
            }
        };
    }

    /**
     * Ottieni la configurazione per potersi collegare a quel bucket dell'Object Storage scelto.
     *
     * @param nomeBackend nome del backend <strong> di tipo PIG_DEC_BACKEND.NM_TIPO_BACKEND = 'OS'
     *                    </strong>come censito su DEC_BACKEND (per esempio OBJECT_STORAGE_PRIMARIO)
     * @param tipoUsoOs   ambito di utilizzo di questo backend (per esempio STAGING)
     * @return Configurazione dell'Object Storage per quell'ambito
     * @throws ObjectStorageException in caso di errore
     */
    public ObjectStorageBackend getObjectStorageConfiguration(final String nomeBackend,
            final String tipoUsoOs) throws ObjectStorageException {
        TypedQuery<DecConfigObjectStorage> query = entityManagerSacerPing.createQuery(
                "Select c from DecConfigObjectStorage c where c.tiUsoConfigObjectStorage = :tipoUsoOs and c.decBackend.nmBackend = :nomeBackend order by c.nmConfigObjectStorage",
                DecConfigObjectStorage.class);
        query.setParameter("tipoUsoOs", tipoUsoOs);
        query.setParameter("nomeBackend", nomeBackend);
        List<DecConfigObjectStorage> resultList = query.getResultList();
        String bucket = null;
        String nomeSystemPropertyAccessKeyId = null;
        String nomeSystemPropertySecretKey = null;
        String storageAddress = null;
        Long backendId = null;

        for (DecConfigObjectStorage decConfigObjectStorage : resultList) {
            switch (decConfigObjectStorage.getNmConfigObjectStorage()) {
            case Constants.ACCESS_KEY_ID_SYS_PROP:
                nomeSystemPropertyAccessKeyId = decConfigObjectStorage
                        .getDsValoreConfigObjectStorage();
                break;
            case Constants.BUCKET:
                bucket = decConfigObjectStorage.getDsValoreConfigObjectStorage();
                break;
            case Constants.SECRET_KEY_SYS_PROP:
                nomeSystemPropertySecretKey = decConfigObjectStorage
                        .getDsValoreConfigObjectStorage();
                break;
            default:
                throw ObjectStorageException.builder().message(
                        "Impossibile stabilire la tipologia del parametro per l'object storage")
                        .build();
            }
            // identico per tutti perché definito nella tabella padre
            storageAddress = decConfigObjectStorage.getDecBackend().getDlBackendUri();
            backendId = decConfigObjectStorage.getDecBackend().getIdDecBackend();
        }
        if (StringUtils.isBlank(bucket) || StringUtils.isBlank(nomeSystemPropertyAccessKeyId)
                || StringUtils.isBlank(nomeSystemPropertySecretKey)
                || StringUtils.isBlank(storageAddress)) {
            throw ObjectStorageException.builder()
                    .message(
                            "Impossibile stabilire la tipologia del parametro per l'object storage")
                    .build();
        }

        SmallRyeConfig config = ConfigProvider.getConfig().unwrap(SmallRyeConfig.class);

        final String accessKeyId = config.getValue(nomeSystemPropertyAccessKeyId, String.class);
        final String secretKey = config.getValue(nomeSystemPropertySecretKey, String.class);
        final URI osURI = URI.create(storageAddress);
        final String stagingBucket = bucket;
        final Long idBackend = backendId;

        return new ObjectStorageBackend() {
            private static final long serialVersionUID = -7032516962480163852L;

            @Override
            public String getBackendName() {
                return nomeBackend;
            }

            @Override
            public URI getAddress() {
                return osURI;
            }

            @Override
            public String getBucket() {
                return stagingBucket;
            }

            @Override
            public String getAccessKeyId() {
                return accessKeyId;
            }

            @Override
            public String getSecretKey() {
                return secretKey;
            }

            @Override
            public Long getBackendId() {
                return idBackend;
            }
        };
    }

    public void deleteObjectFromOS(ObjectStorageBackend configuration, String objectKey)
            throws ObjectStorageException {
        try {
            DeleteObjectRequest delOb = DeleteObjectRequest.builder()
                    .bucket(configuration.getBucket()).key(objectKey).build();

            S3Client s3SourceClient = s3Clients.getClient(configuration.getAddress(),
                    configuration.getAccessKeyId(), configuration.getSecretKey());

            s3SourceClient.deleteObject(delOb);
        } catch (S3Exception e) {
            throw ObjectStorageException.builder()
                    .message("{0}: impossibile eliminare dal bucket {1} oggetto con chiave {2}",
                            configuration.getBackendName(), configuration.getBucket(), objectKey)
                    .cause(e).build();
        }
    }
}
