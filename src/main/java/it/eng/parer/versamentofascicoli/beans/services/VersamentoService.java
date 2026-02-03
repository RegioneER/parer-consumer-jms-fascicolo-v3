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

package it.eng.parer.versamentofascicoli.beans.services;

import it.eng.parer.versamentofascicoli.Constants;
import it.eng.parer.versamentofascicoli.dao.SacerPingDao;
import it.eng.parer.versamentofascicoli.dto.BackendStorage;
import it.eng.parer.versamentofascicoli.dto.ObjectStorageBackend;
import it.eng.parer.versamentofascicoli.dto.VersamentoFascicoloMessage;
import it.eng.parer.versamentofascicoli.exceptions.ObjectStorageException;
import it.eng.parer.versamentofascicoli.exceptions.VersamentoServiceException;
import it.eng.parer.versamentofascicoli.jpa.entities.sacerping.*;
import it.eng.parer.versamentofascicoli.ws.VersamentoSacerClient;
import it.eng.parer.versamentofascicoli.ws.jaxb.EsitoVersamentoFascicolo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.io.FileUtils;

/**
 * @author Cappelli_F
 */
@ApplicationScoped
public class VersamentoService {

    private static final Logger log = LoggerFactory.getLogger(VersamentoService.class);

    @Inject
    SacerPingDao sacerPingDao;

    @Inject
    @RestClient
    VersamentoSacerClient versamentoSacerClient;

    @ConfigProperty(name = "ftp.root-dir")
    String rootDir;

    @Transactional()
    public void handleVersamentoMessage(VersamentoFascicoloMessage message) {
        // identificativo operazione, usato per il logging.
        final UUID uuid = UUID.randomUUID();

        Long idObject = message.getObjectId();
        Long idFascicolo = message.getFascicoloId();
        Long idFascicoloSessione = message.getFascicoloSessionId();

        log.info("[{}] Inizio versamento fascicoli per oggetto {}", uuid, idObject);
        // controlla che tutte le proprietà del messaggio siano correttamente valorizzate.
        if (idObject == null || idFascicolo == null || idFascicoloSessione == null) {
            log.error("[{}] Alcuni parametri necessari non sono valorizzati.", uuid);
        }

        PigObject pigObject = sacerPingDao.findWithLockExtended(PigObject.class, idObject);
        log.debug("[{}] Lockato PigObjet {}", uuid, idObject);
        PigFascicoloObject pigFascicoloObject = sacerPingDao
                .findLockPigFascicoloObjectById(idFascicolo);
        PigFascicoloSessione pigFascicoloSessione = sacerPingDao
                .findWithLockExtended(PigFascicoloSessione.class, idFascicoloSessione);

        final boolean inCodaVersamento = pigFascicoloObject.getTiStatoFascicoloObject()
                .equalsIgnoreCase(Constants.StatoFascicoloObject.IN_CODA_VERS.name())
                && pigFascicoloSessione.getTiStatoFascicoloSessione()
                        .equalsIgnoreCase(Constants.StatoFascicoloSessione.IN_CODA_VERS.name());

        log.debug(
                "[{}] Stati controllati: StatoFascicoloObject = '{}', StatoFascicoloSessione = '{}', esito controllo = {}",
                uuid, pigFascicoloObject.getTiStatoFascicoloObject(),
                pigFascicoloSessione.getTiStatoFascicoloSessione(), inCodaVersamento);

        if (inCodaVersamento) {
            final String logHeader = "[" + uuid + "] " + " - idObj = " + pigObject.getIdObject()
                    + ", idSes = " + pigObject.getIdLastSessioneIngest() + ", fascicolo = "
                    + pigFascicoloObject.getIdFascicoloObject() + " :: ";
            log.debug("{} ricevuto fascicolo {} dell'oggetto {}", logHeader, idFascicolo, idObject);
            // per evitare problemi di memoria il produttore mette in coda solo gli id degli xml
            // e adesso con questi id gli xml vengono recuperati
            String xmlVersamentoSacer = sacerPingDao.getXmlVersamentoFascicolo(idFascicolo);
            // String urlVersamento = message.getUrlServVersamento(); FIXME : a che serve nel
            // versamento unità documentarie?
            String userIdSacer = message.getUserIdSacer();
            String passwordSacer = message.getPasswordSacer();

            // escapa l'xml con delle entities
            // xmlVersamentoSacer =
            // StringEscapeUtils.ESCAPE_XML.with(NumericEntityEscaper.between(0x7f,
            // Integer.MAX_VALUE)).translate(xmlVersamentoSacer);

            EsitoVersamentoFascicolo esitoVersamentoFascicolo = null;

            boolean incrementaVersateOk = false;
            boolean incrementaVersateErrore = false;
            boolean incrementaVersateTimeout = false;

            // chiamata al servizio versamento
            try (Response xmlResponse = versamentoSacerClient.versaFascicolo(Constants.WS_VERSION,
                    userIdSacer, passwordSacer, xmlVersamentoSacer);
                    Reader inXml = new InputStreamReader((InputStream) xmlResponse.getEntity(),
                            StandardCharsets.ISO_8859_1)) {
                JAXBContext context = JAXBContext.newInstance(EsitoVersamentoFascicolo.class);
                esitoVersamentoFascicolo = (EsitoVersamentoFascicolo) context.createUnmarshaller()
                        .unmarshal(inXml);

            } catch (IOException | JAXBException e) {
                log.error(logHeader + " Errore non recuperabile: ", e);
                throw new VersamentoServiceException(e.getMessage());
            } catch (ProcessingException ee) {
                // Probabilmente timeout, lo trattiamo come tale.
                log.warn(
                        "{} il fascicolo '{}' è andato in timeout durante la richiesta di versamento a Sacer.",
                        logHeader, idFascicolo);
                incrementaVersateTimeout = true;

                this.handleTimeout(pigFascicoloObject, pigFascicoloSessione);
            }

            String codiceEsitoVersamento = "";
            String codiceErroreVersamento = "";
            String messaggioErroreVersamento = "";

            log.debug(
                    "{} ho versato l'UD '{}': codice esito = {}; codice errore = {}; messaggio errore = {}",
                    logHeader, idFascicolo, codiceEsitoVersamento, codiceErroreVersamento,
                    messaggioErroreVersamento);

            if (esitoVersamentoFascicolo != null) {
                if (esitoVersamentoFascicolo.getRapportoVersamentoFascicolo() != null) {
                    codiceEsitoVersamento = esitoVersamentoFascicolo
                            .getRapportoVersamentoFascicolo().getEsitoGenerale().getCodiceEsito()
                            .name();
                    codiceErroreVersamento = esitoVersamentoFascicolo
                            .getRapportoVersamentoFascicolo().getEsitoGenerale().getCodiceErrore();
                    messaggioErroreVersamento = esitoVersamentoFascicolo
                            .getRapportoVersamentoFascicolo().getEsitoGenerale()
                            .getMessaggioErrore();

                    incrementaVersateOk = true;

                    log.debug(
                            "{} esito versamento UD '{}' {} {} - {} modifico lo stato della UD in VERSATA_OK.",
                            logHeader, idFascicolo, codiceEsitoVersamento, codiceErroreVersamento,
                            messaggioErroreVersamento);
                    this.handlePositivoWarning(pigFascicoloObject, pigFascicoloSessione);
                    log.debug("{} Stato UD '{}' {}", logHeader, idFascicolo,
                            pigFascicoloObject.getTiStatoFascicoloObject());
                }

                if (esitoVersamentoFascicolo.getEsitoVersamentoNegativo() != null) {
                    codiceEsitoVersamento = esitoVersamentoFascicolo.getEsitoVersamentoNegativo()
                            .getEsitoGenerale().getCodiceEsito().name();
                    codiceErroreVersamento = esitoVersamentoFascicolo.getEsitoVersamentoNegativo()
                            .getEsitoGenerale().getCodiceErrore();
                    messaggioErroreVersamento = esitoVersamentoFascicolo
                            .getEsitoVersamentoNegativo().getEsitoGenerale().getMessaggioErrore();

                    incrementaVersateErrore = true;

                    log.debug(
                            "{} esito versamento UD '{}' {} {} - {} modifico lo stato della UD in VERSATA_ERR.",
                            logHeader, idFascicolo, codiceEsitoVersamento, codiceErroreVersamento,
                            messaggioErroreVersamento);
                    this.handleNegativo(pigFascicoloObject, pigFascicoloSessione,
                            codiceErroreVersamento, messaggioErroreVersamento);
                }
            } else {
                log.warn(
                        "Warning: esitoVersamentoFascicolo non valorizzato, possibile timeout. PigObject: {}  fascicolo: {} sessione fascicolo: {}",
                        pigObject.getIdObject(), pigFascicoloObject.getIdFascicoloObject(),
                        pigFascicoloSessione.getIdFascicoloSessione());
            }

            log.debug(
                    "{} Aggiorno i contatori incrementaVersatiOk = {}, incrementaVersatiErrore = {}, incrementaVersatiTimeout = {}",
                    logHeader, incrementaVersateOk, incrementaVersateErrore,
                    incrementaVersateTimeout);
            PigSessioneIngest session = sacerPingDao.aggiornaContatoriFascicoli(
                    pigObject.getIdLastSessioneIngest().longValueExact(), incrementaVersateOk,
                    incrementaVersateErrore, incrementaVersateTimeout);

            // se il numero dei fascicoli versati coincide con il numero di fascicoli da
            // versare
            if (session.getNiFascicoliVers().intValue() == session.getNiFascicoliDaVers()
                    .intValue()) {
                log.debug("{} il numero di UD versate coincide con quello di UD da versare",
                        logHeader);
                try {
                    this.handleVersamentoTerminato(pigObject, session, logHeader);
                } catch (ObjectStorageException e) {
                    log.error(
                            "[{}] Errore non recuperabile nella comunicazione con l'object storage.",
                            logHeader, e);
                }
            }

            sacerPingDao.flushChanges();
        } else {
            log.error(
                    "Errore: Fascicolo non in versamento, PigObject: {}  fascicolo: {} sessione fascicolo: {}",
                    pigObject.getIdObject(), pigFascicoloObject.getIdFascicoloObject(),
                    pigFascicoloSessione.getIdFascicoloSessione());
        }

        log.info("[{}] Fine versamento fascicoli per oggetto {}", uuid, idObject);
    }

    private void handleTimeout(PigFascicoloObject fascicolo,
            PigFascicoloSessione fascicoloSessione) {
        fascicolo.setTiStatoFascicoloObject(Constants.StatoFascicoloObject.VERSATA_TIMEOUT.name());

        // MEV 27407
        LocalDateTime dtStato = LocalDateTime.now();
        fascicolo.setDtStato(dtStato);
        fascicoloSessione.setTiStatoFascicoloSessione(
                Constants.StatoFascicoloSessione.VERSATA_TIMEOUT.name());
        fascicoloSessione.setDtStato(dtStato);
    }

    private void handlePositivoWarning(PigFascicoloObject fascicolo,
            PigFascicoloSessione fascicoloSessione) {

        fascicolo.setTiStatoFascicoloObject(Constants.StatoFascicoloObject.VERSATA_OK.name());
        fascicoloSessione
                .setTiStatoFascicoloSessione(Constants.StatoFascicoloSessione.VERSATA_OK.name());

        // MEV 27407
        LocalDateTime dtStato = LocalDateTime.now();
        fascicolo.setDtStato(dtStato);
        fascicoloSessione.setDtStato(dtStato);
    }

    private void handleNegativo(PigFascicoloObject fascicolo,
            PigFascicoloSessione fascicoloSessione, String codiceErrore, String messaggioErrore) {
        fascicoloSessione
                .setTiStatoFascicoloSessione(Constants.StatoFascicoloSessione.VERSATA_ERR.name());

        // MEV 27407
        LocalDateTime dtStato = LocalDateTime.now();

        // setto codice e descrizione dell'errore determinati da Sacer
        fascicoloSessione.setDtStato(dtStato);
        fascicoloSessione.setCdErrSacer(codiceErrore);
        fascicoloSessione.setDlErrSacer(messaggioErrore);

        fascicolo.setTiStatoFascicoloObject(Constants.StatoFascicoloObject.VERSATA_ERR.name());
        // setto codice e descrizione dell'errore determinati da Sacer
        fascicolo.setDtStato(dtStato);
        fascicolo.setCdErrSacer(codiceErrore);
        fascicolo.setDlErrSacer(messaggioErrore);
    }

    private void handleVersamentoTerminato(PigObject object, PigSessioneIngest session,
            String logHeader) throws ObjectStorageException {

        long niFascicoliDuplicati = sacerPingDao.countFascicoliInPigObject(object,
                Constants.StatoFascicoloObject.VERSATA_ERR.name(), Constants.FASC_001_001);
        long niFascicoliTimeout = session.getNiFascicoliVersTimeout().longValue();
        long niFascicoliVersOk = session.getNiFascicoliVersOk().longValueExact();

        // MEV 30209 - Quante ud in ERR_666?
        long niErr666 = sacerPingDao.countFascicoliInPigObject(object,
                Constants.StatoFascicoloObject.VERSATA_ERR.name(), Constants.ERR_666);
        // MEV 33855 - Quante ud in ERR_666P?
        long niErr666PU = sacerPingDao.countFascicoliInPigObject(object,
                Constants.StatoFascicoloObject.VERSATA_ERR.name(), Constants.ERR_666P);

        LocalDateTime now = LocalDateTime.now();

        // tutte i fascicoli contenuti nello zip sono stati chiusi positivamente o sono falliti per
        // errore
        // di una chiave già presente e nessuno è andata in timeout.
        if (session.getNiFascicoliVersOk().intValue() == session.getNiFascicoliDaVers().intValue()
                || (niFascicoliTimeout == 0
                        && BigDecimal.valueOf(niFascicoliVersOk + niFascicoliDuplicati)
                                .equals(session.getNiFascicoliDaVers()))) {

            // la sessione viene chiusa positivamente ed assume lo stato CHIUSO_OK
            session.setTiStato(Constants.StatoSessioneIngest.CHIUSO_OK.name());
            // aggiorno l'oggetto assegnando stato = CHIUSO_OK
            object.setTiStatoObject(Constants.StatoOggetto.CHIUSO_OK.name());
            session.setDtChiusura(now);

            sacerPingDao.creaStatoSessione(session, Constants.StatoSessioneIngest.CHIUSO_OK.name(),
                    now);

            // cancello il file versato con l'oggetto
            this.deleteFileObject(object, logHeader);

            // Se esiste un oggetto 'padre', definisce il suo stato
            if (object.getPigObjectPadre() != null) {
                // MEV#14100 metodo definisciStatoOggettoPadre()
                definisciStatoOggettoPadre(object.getPigObjectPadre().getIdObject(), logHeader);
            }
        } else {
            // assegno zero al flag di sessione verificata
            session.setFlSesErrVerif(Constants.DB_FALSE);
            // Esistono UD chiuse con TIMEOUT Imposto oggetto e sessione con stato
            // CHIUSO_ERR_RECUPERABILE
            // MEV 30209 Esistono UD chiuse in VERSATA_ERR con codice d'errore ERR_666 -> Imposto
            // oggetto e sessione con stato CHIUSO_ERR_RECUPERABILE
            if (niFascicoliTimeout > 0 || niErr666 > 0 || niErr666PU > 0) {
                // la sessione viene chiusa negativamente ed assume lo stato CHIUSO_ERR_RECUPERABILE
                session.setTiStato(Constants.StatoSessioneIngest.CHIUSO_ERR_RECUPERABILE.name());
                // assegno codice di errore = PING-CONSCODA-002 e setto dlErr
                session.setCdErr(Constants.PING_CONSCODA_002);
                // setto dlErr = Il versamento di almeno una unità documentaria è fallito
                session.setDlErr(Constants.PING_CONSCODA_002_MSG);
                // aggiorno l'oggetto assegnando stato = CHIUSO_ERR_RECUPERABILE
                object.setTiStatoObject(Constants.StatoOggetto.CHIUSO_ERR_RECUPERABILE.name());
                session.setDtChiusura(now);
                sacerPingDao.creaStatoSessione(session,
                        Constants.StatoSessioneIngest.CHIUSO_ERR_RECUPERABILE.name(), now);
            } else {
                /*
                 * se almeno una UD è stata chiusa in errore la sessione viene chiusa in errore ed
                 * assume lo stato CHIUSO_ERR_VERS (anche se potenzialmente alcune UD sono state
                 * inviate a SACER)
                 */
                session.setTiStato(Constants.StatoSessioneIngest.CHIUSO_ERR_VERS.name());
                // assegno codice di errore = PING-CONSCODA-001 e setto dlErr
                session.setCdErr(Constants.PING_CONSCODA_001);
                // setto dlErr = Il versamento di almeno una unità documentaria è fallito
                session.setDlErr(Constants.PING_CONSCODA_001_MSG);
                // aggiorno l'oggetto assegnando stato = CHIUSO_ERR_VERS
                object.setTiStatoObject(Constants.StatoOggetto.CHIUSO_ERR_VERS.name());
                session.setDtChiusura(now);
                sacerPingDao.creaStatoSessione(session,
                        Constants.StatoOggetto.CHIUSO_ERR_VERS.name(), now);
                // non cancello più il file perché adesso questo caso viene trattato come quello
                // timeout. Ovvero verrà recuperato.
            }
        }
    }

    private void definisciStatoOggettoPadre(Long idOggettoPadre, String logHeader) {
        if (idOggettoPadre != null) {
            sacerPingDao.flushChanges();

            // assume il lock sull'oggetto padre
            PigObject oggettoPadre = sacerPingDao.findWithLock(PigObject.class, idOggettoPadre);
            String statoPadreCalcolato = sacerPingDao
                    .getCalcoloStatoObjDaTrasformare(oggettoPadre.getIdObject());

            if (statoPadreCalcolato != null) {
                LocalDateTime now = LocalDateTime.now();
                PigSessioneIngest sessionePadre = sacerPingDao
                        .retrieveSessionByObject(oggettoPadre);

                // Evita di creare 'n' volte lo stato chiuso Ok nella sessione
                if (statoPadreCalcolato.equals(Constants.StatoOggetto.CHIUSO_OK.name())
                        && !oggettoPadre.getTiStatoObject()
                                .equals(Constants.StatoOggetto.CHIUSO_OK.name())) {
                    oggettoPadre.setTiStatoObject(Constants.StatoOggetto.CHIUSO_OK.name());
                    sessionePadre.setTiStato(Constants.StatoSessioneIngest.CHIUSO_OK.name());
                    sessionePadre.setDtChiusura(now);
                    sacerPingDao.creaStatoSessione(sessionePadre,
                            Constants.StatoSessioneIngest.CHIUSO_OK.name(), now);
                    // Punto 4) Elimina le cartelle degli oggetti generati da trasformazione a
                    // partire dal
                    // parametro "ROOT_FTP"
                    // MAC#15470
                    if (oggettoPadre.getTiGestOggettiFigli() != null
                            && oggettoPadre.getTiGestOggettiFigli()
                                    .equals(Constants.TipoGestioneOggettiFigli.AUTOMATICA.name())) {
                        for (PigObjectTrasf objectFiglio : oggettoPadre.getPigObjectTrasfs()) {
                            deleteDir(logHeader, rootDir, objectFiglio.getPigVer().getDsPathTrasf(),
                                    objectFiglio.getDsPath());
                        }
                    }
                } else if (statoPadreCalcolato
                        .equals(Constants.StatoOggetto.CHIUSO_ERR_VERS.name())) {
                    oggettoPadre.setTiStatoObject(Constants.StatoOggetto.CHIUSO_ERR_VERS.name());
                    sessionePadre.setTiStato(Constants.StatoSessioneIngest.CHIUSO_ERR_VERS.name());
                    sessionePadre.setDtChiusura(now);
                    sacerPingDao.creaStatoSessione(sessionePadre,
                            Constants.StatoSessioneIngest.CHIUSO_ERR_VERS.name(), now);
                    sessionePadre.setCdErr(Constants.PING_ERR_VERS_001);
                    sessionePadre.setDlErr(Constants.PING_ERR_VERS_001_MSG);
                    // Punto 5) Aggiorna tutte le sessioni dell'oggetto padre
                    for (PigSessioneIngest ses : oggettoPadre.getPigSessioneIngests()) {
                        ses.setFlSesErrVerif("1");
                        // MAC34492 ora la flag non risolubile non viene più impostata.
                    }
                    // Punto 6) Elimina le cartelle degli oggetti generati da trasformazione a
                    // partire dal
                    // parametro "ROOT_FTP"
                    // MAC#15470
                    if (oggettoPadre.getTiGestOggettiFigli() != null
                            && oggettoPadre.getTiGestOggettiFigli()
                                    .equals(Constants.TipoGestioneOggettiFigli.AUTOMATICA.name())) {
                        for (PigObjectTrasf objectFiglio : oggettoPadre.getPigObjectTrasfs()) {
                            deleteDir(logHeader, rootDir, objectFiglio.getPigVer().getDsPathTrasf(),
                                    objectFiglio.getDsPath());
                        }
                    }
                }
            }
        }
    }

    public void deleteDir(String logHeader, String rootDir, String ftpPath, String ftpDir) {
        try {
            log.debug("{} cancello la cartella {}{}{}", logHeader, rootDir, ftpPath, ftpDir);

            String directoryToRemove = rootDir + ftpPath + ftpDir;
            // elimina file
            File tmpDirectory = new File(directoryToRemove);
            // FF - chiamato il metodo di cancellazione ricorsiva
            // per eliminare la dir temporanea estratta dallo zip
            if (tmpDirectory.isDirectory()) {
                FileUtils.deleteDirectory(tmpDirectory);
            } else {
                log.warn(
                        " {} errore nella cancellazione della cartella {}{}{}: la risorsa non è una cartella",
                        logHeader, rootDir, ftpPath, ftpDir);
            }
        } catch (Exception ex) {
            // la mancata cancellazione della cartella non è un errore.
            log.error(" {} errore nella cancellazione della cartella {}{}{}: errore generico.",
                    logHeader, rootDir, ftpPath, ftpDir, ex);
        }
    }

    private void deleteFileObject(PigObject object, String logHeader)
            throws ObjectStorageException {
        String ftpPath = object.getPigVer().getDsPathInputFtp();
        // - cartella definita dal codice identificante l'oggetto
        String ftpDir = object.getCdKeyObject();
        deleteDir(logHeader, rootDir, ftpPath, ftpDir);

        // MEV25602 e MEV34843 - mi assicuro di cancellare anche il file su OS se presente.
        for (PigFileObject fileObject : object.getPigFileObjects()) {
            if (fileObject.getPigFileObjectStorage() != null) {
                PigFileObjectStorage pfos = fileObject.getPigFileObjectStorage();
                BackendStorage backend = sacerPingDao.getBackend(pfos.getIdDecBackend());
                ObjectStorageBackend config = sacerPingDao
                        .getObjectStorageConfigurationForVersamento(backend.getBackendName(),
                                pfos.getNmBucket());

                sacerPingDao.deleteObjectFromOS(config, pfos.getCdKeyFile());
            }
        }
    }
}
