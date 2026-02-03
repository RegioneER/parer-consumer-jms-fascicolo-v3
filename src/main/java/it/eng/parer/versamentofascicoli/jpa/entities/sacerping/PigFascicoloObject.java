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

import jakarta.persistence.*;

/**
 * @author Cappelli_F
 */
@Entity
@Table(name = "PIG_FASCICOLO_OBJECT")
public class PigFascicoloObject implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long idFascicoloObject;
    private String cdErrSacer;
    private String dlErrSacer;
    private String tiStatoFascicoloObject;
    private PigObject pigObject;
    // MEV 27407
    private LocalDateTime dtStato;

    public PigFascicoloObject() {
        // for Hibernate
    }

    @Id
    @Column(name = "ID_FASCICOLO_OBJECT")
    public Long getIdFascicoloObject() {
        return this.idFascicoloObject;
    }

    public void setIdFascicoloObject(Long idFascicoloObject) {
        this.idFascicoloObject = idFascicoloObject;
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

    @Column(name = "TI_STATO_FASCICOLO_OBJECT")
    public String getTiStatoFascicoloObject() {
        return this.tiStatoFascicoloObject;
    }

    public void setTiStatoFascicoloObject(String tiStatoFascicoloObject) {
        this.tiStatoFascicoloObject = tiStatoFascicoloObject;
    }

    // bi-directional many-to-one association to PigObject
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_OBJECT")
    public PigObject getPigObject() {
        return this.pigObject;
    }

    public void setPigObject(PigObject pigObject) {
        this.pigObject = pigObject;
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
