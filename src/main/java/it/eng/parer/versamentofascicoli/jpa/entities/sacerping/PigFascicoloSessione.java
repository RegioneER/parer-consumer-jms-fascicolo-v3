/*
 * Engineering Ingegneria Informatica S.p.A.
 *
 * Copyright (C) 2023 Regione Emilia-Romagna <p/> This program is free software: you can
 * redistribute it and/or modify it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version. <p/> This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Affero General Public License for more details. <p/> You should
 * have received a copy of the GNU Affero General Public License along with this program. If not,
 * see <https://www.gnu.org/licenses/>.
 */

package it.eng.parer.versamentofascicoli.jpa.entities.sacerping;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * The persistent class for the PIG_UNITA_DOC_SESSIONE database table.
 */
@Entity
@Table(name = "PIG_FASCICOLO_SESSIONE")
public class PigFascicoloSessione implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long idFascicoloSessione;
    private String cdErrSacer;
    private String dlErrSacer;
    private String tiStatoFascicoloSessione;
    private PigSessioneIngest pigSessioneIngest;
    // MEV 27407
    private LocalDateTime dtStato;

    public PigFascicoloSessione() {
        // for Hibernate
    }

    @Id
    @Column(name = "ID_FASCICOLO_SESSIONE")
    public Long getIdFascicoloSessione() {
        return this.idFascicoloSessione;
    }

    public void setIdFascicoloSessione(Long idFascicoloSessione) {
        this.idFascicoloSessione = idFascicoloSessione;
    }

    @Column(name = "CD_ERR_SACER")
    public String getCdErrSacer() {
        return this.cdErrSacer;
    }

    public void setCdErrSacer(String cdErrSacer) {
        this.cdErrSacer = cdErrSacer;
    }

    @Column(name = "DL_ERR_SACER")
    public String getDlErrSacer() {
        return this.dlErrSacer;
    }

    public void setDlErrSacer(String dlErrSacer) {
        this.dlErrSacer = dlErrSacer;
    }

    @Column(name = "TI_STATO_FASCICOLO_SESSIONE")
    public String getTiStatoFascicoloSessione() {
        return this.tiStatoFascicoloSessione;
    }

    public void setTiStatoFascicoloSessione(String tiStatoFascicoloSessione) {
        this.tiStatoFascicoloSessione = tiStatoFascicoloSessione;
    }

    // bi-directional many-to-one association to PigSessioneIngest
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SESSIONE_INGEST")
    public PigSessioneIngest getPigSessioneIngest() {
        return this.pigSessioneIngest;
    }

    public void setPigSessioneIngest(PigSessioneIngest pigSessioneIngest) {
        this.pigSessioneIngest = pigSessioneIngest;
    }

    // MEV 27407
    @Column(name = "DT_STATO")
    public LocalDateTime getDtStato() {
        return dtStato;
    }

    public void setDtStato(LocalDateTime dtStato) {
        this.dtStato = dtStato;
    }
}
