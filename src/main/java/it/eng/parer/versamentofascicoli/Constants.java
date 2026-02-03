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

package it.eng.parer.versamentofascicoli;

/**
 * @author Cappelli_F
 */
public class Constants {
    public static final String JPA_PROPERTIES_TIMEOUT = "javax.persistence.lock.timeout";

    public static final String URL_ADMIN_BASE = "/admin";

    private static final String URL_CTX_PUBLIC = "/public";
    private static final String URL_CTX_OAUTH2 = "/oauth2";

    public static final String RESOURCE_FASCICOLO_V3 = "/fascicolo";

    public static final String RESOURCE_INFOS = "/infos";

    public static final String URL_GET_INFOS = URL_ADMIN_BASE + RESOURCE_INFOS;
    public static final String URL_GET_STATUS = "/status";

    public static final String WS_VERSION = "3.0";

    public enum StatoOggetto {

        ANNULLATO, CHIUSO_ERR_CODA, CHIUSO_ERR_CRASH_DPI, CHIUSO_ERR_CRASH_FS_PRIM,
        CHIUSO_ERR_CRASH_FS_SECOND, CHIUSO_ERR_CRASH_FTP, CHIUSO_ERR_NOTIF, CHIUSO_ERR_SCHED,
        CHIUSO_ERR_RECUPERABILE, CHIUSO_ERR_VERS, CHIUSO_ERR_VERSAMENTO_A_PING,
        CHIUSO_ERR_TRASFORMAZIONE, CHIUSO_OK, CHIUSO_WARNING, DA_TRASFORMARE,
        ERRORE_VERSAMENTO_A_PING, ERRORE_TRASFORMAZIONE, IN_ATTESA_FILE, IN_ATTESA_SCHED,
        IN_ATTESA_VERS, IN_CODA_VERS, IN_CORSO_ANNULLAMENTO, PREPARAZIONE_OGG_IN_CORSO, TRASFORMATO,
        TRASFORMAZIONE_NON_ATTIVA, VERSATO_A_PING, WARNING, WARNING_TRASFORMAZIONE,
        TRASFORMAZIONE_IN_CORSO, CHIUSO_ERR_VERIFICA_HASH, IN_CODA_HASH,
        // Stati fake lista riepilogo oggetti versati
        IN_CORSO_VERS_SACER, PROBLEMA_VERS_SACER, CHIUSO_ERR, PROBLEMA_PREPARAZIONE_SIP,
        WARNING_CHIAVE_DUPLICATA
    }

    public enum StatoSessioneIngest {
        ANNULLATA, CHIUSO_ERR, CHIUSO_ERR_CODA, CHIUSO_ERR_CRASH_DPI, CHIUSO_ERR_CRASH_FTP,
        CHIUSO_ERR_NOTIF, CHIUSO_ERR_SCHED, CHIUSO_ERR_RECUPERABILE, CHIUSO_ERR_VERS,
        CHIUSO_ERR_VERSAMENTO_A_PING, CHIUSO_ERR_TRASFORMAZIONE, CHIUSO_FORZATA, CHIUSO_OK,
        CHIUSO_WARNING, DA_TRASFORMARE, ERRORE_VERSAMENTO_A_PING, ERRORE_TRASFORMAZIONE,
        IN_ATTESA_FILE, IN_ATTESA_SCHED, IN_ATTESA_VERS, IN_CODA_VERS, IN_CORSO_ANNULLAMENTO,
        PREPARAZIONE_OGG_IN_CORSO, TRASFORMATO, TRASFORMAZIONE_NON_ATTIVA, VERSATO_A_PING, WARNING,
        WARNING_TRASFORMAZIONE, TRASFORMAZIONE_IN_CORSO, CHIUSO_ERR_VERIFICA_HASH, IN_CODA_HASH
    }

    public enum StatoFascicoloObject {
        ANNULLATA, DA_VERSARE, ERR_CRASH_FS_PRIM, ERR_CRASH_FS_SECOND, IN_CODA_VERS,
        IN_CORSO_ANNULLAMENTO, VERSATA_ERR, VERSATA_OK, VERSATA_TIMEOUT, PREPARA_XML_IN_ERRORE,
        PREPARA_XML_OK
    }

    public enum StatoFascicoloSessione {
        ANNULLATA, DA_VERSARE, IN_CODA_VERS, IN_CORSO_ANNULLAMENTO, VERSATA_ERR, VERSATA_OK,
        VERSATA_TIMEOUT, PREPARA_XML_IN_ERRORE
    }

    public enum TipoGestioneOggettiFigli {
        AUTOMATICA, MANUALE;

        public static TipoGestioneOggettiFigli[] getEnums(TipoGestioneOggettiFigli... vals) {
            return vals;
        }

        public static TipoGestioneOggettiFigli[] getTipoGestioneOggettiFigli() {
            return getEnums(AUTOMATICA, MANUALE);
        }
    }

    public static final String DB_TRUE = "1";
    public static final String DB_FALSE = "0";

    public static final String CONF_VERSAMENTO = "VERSAMENTO_OGGETTO";
    public static final String BUCKET = "BUCKET";
    public static final String ACCESS_KEY_ID_SYS_PROP = "ACCESS_KEY_ID_SYS_PROP";
    public static final String SECRET_KEY_SYS_PROP = "SECRET_KEY_SYS_PROP";

    // Codici d'errore
    // Errore imprevisto
    public static final String ERR_666 = "666";
    // Errore imprevisto nella persistenza
    public static final String ERR_666P = "666P";
    public static final String FASC_001_001 = "FASC-001-001";
    public static final String PING_ERR_VERS_001 = "PING_ERR_VERS_001";
    public static final String PING_ERR_VERS_001_MSG = "Nessun fascicolo è stato versato in Sacer ed almeno un oggetto generato da trasformazione ha stato = CHIUSO_ERR_SCHED o CHIUSO_ERR_CODA o CHIUSO_ERR_VERS con tutte le sessioni non risolubili e tutti gli altri oggetti generati da trasformazione hanno stato = CHIUSO_ERR_SCHED o CHIUSO_ERR_CODA o CHIUSO_ERR_VERS con tutte le sessioni non risolubili oppure hanno stato = ANNULLATO";
    public static final String PING_CONSCODA_001 = "PING-CONSCODA-001";
    public static final String PING_CONSCODA_001_MSG = "Il versamento di almeno un fascicolo è fallito.";
    public static final String PING_CONSCODA_002 = "PING-CONSCODA-002";
    public static final String PING_CONSCODA_002_MSG = "Problemi nel versamento a SACER.";
}
