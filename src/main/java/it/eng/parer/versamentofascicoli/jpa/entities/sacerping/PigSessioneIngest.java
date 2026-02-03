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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

/**
 * The persistent class for the PIG_SESSIONE_INGEST database table.
 */
@NamedStoredProcedureQuery(name = "aggiornaContatori", procedureName = "AGGIORNA_CONTATORI", parameters = {
        @StoredProcedureParameter(name = "IDSESSIONEINGEST", type = Long.class, mode = ParameterMode.IN),
        @StoredProcedureParameter(name = "INCREMENTAVERSATEOK", type = Integer.class, mode = ParameterMode.IN),
        @StoredProcedureParameter(name = "INCREMENTAVERSATEERRORE", type = Integer.class, mode = ParameterMode.IN),
        @StoredProcedureParameter(name = "INCREMENTAVERSATETIMEOUT", type = Integer.class, mode = ParameterMode.IN) })
@NamedStoredProcedureQuery(name = "aggiornaContatoriFascicoli", procedureName = "AGGIORNA_CONTATORI_FASCICOLI", parameters = {
        @StoredProcedureParameter(name = "IDSESSIONEINGEST", type = Long.class, mode = ParameterMode.IN),
        @StoredProcedureParameter(name = "INCREMENTAVERSATIOK", type = Integer.class, mode = ParameterMode.IN),
        @StoredProcedureParameter(name = "INCREMENTAVERSATIERRORE", type = Integer.class, mode = ParameterMode.IN),
        @StoredProcedureParameter(name = "INCREMENTAVERSATITIMEOUT", type = Integer.class, mode = ParameterMode.IN) })
@Entity
@Table(name = "PIG_SESSIONE_INGEST")
public class PigSessioneIngest implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long idSessioneIngest;
    private String cdErr;
    private String dlErr;
    private LocalDateTime dtChiusura;
    private String flSesErrVerif;
    private String note;
    private BigDecimal idStatoSessioneIngestCor;
    private String tiStato;
    private PigObject pigObject;
    private PigVers pigVer;
    private List<PigStatoSessioneIngest> pigStatoSessioneIngests = new ArrayList<>();
    private List<PigFascicoloSessione> pigFascicoloSessiones = new ArrayList<>();

    // MEV 32983
    private BigDecimal niFascicoliAttesi;
    private BigDecimal niFascicoliDaVers;
    private BigDecimal niFascicoliVers;
    private BigDecimal niFascicoliVersErr;
    private BigDecimal niFascicoliVersOk;
    private BigDecimal niFascicoliVersTimeout;

    public PigSessioneIngest() {
        // for Hibernate
    }

    @Id
    @Column(name = "ID_SESSIONE_INGEST")
    public Long getIdSessioneIngest() {
        return this.idSessioneIngest;
    }

    public void setIdSessioneIngest(Long idSessioneIngest) {
        this.idSessioneIngest = idSessioneIngest;
    }

    @Column(name = "CD_ERR")
    public String getCdErr() {
        return this.cdErr;
    }

    public void setCdErr(String cdErr) {
        this.cdErr = cdErr;
    }

    @Column(name = "DL_ERR")
    public String getDlErr() {
        return this.dlErr;
    }

    public void setDlErr(String dlErr) {
        this.dlErr = dlErr;
    }

    @Column(name = "DT_CHIUSURA")
    public LocalDateTime getDtChiusura() {
        return this.dtChiusura;
    }

    public void setDtChiusura(LocalDateTime dtChiusura) {
        this.dtChiusura = dtChiusura;
    }

    @Column(name = "FL_SES_ERR_VERIF", columnDefinition = "char")
    public String getFlSesErrVerif() {
        return this.flSesErrVerif;
    }

    public void setFlSesErrVerif(String flSesErrVerif) {
        this.flSesErrVerif = flSesErrVerif;
    }

    @Column(name = "ID_STATO_SESSIONE_INGEST_COR")
    public BigDecimal getIdStatoSessioneIngestCor() {
        return this.idStatoSessioneIngestCor;
    }

    public void setIdStatoSessioneIngestCor(BigDecimal idStatoSessioneIngestCor) {
        this.idStatoSessioneIngestCor = idStatoSessioneIngestCor;
    }

    @Column(name = "TI_STATO")
    public String getTiStato() {
        return this.tiStato;
    }

    public void setTiStato(String tiStato) {
        this.tiStato = tiStato;
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

    // bi-directional many-to-one association to PigVers
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VERS")
    public PigVers getPigVer() {
        return this.pigVer;
    }

    public void setPigVer(PigVers pigVer) {
        this.pigVer = pigVer;
    }

    // bi-directional many-to-one association to PigStatoSessioneIngest
    @OneToMany(mappedBy = "pigSessioneIngest", cascade = {
            CascadeType.PERSIST })
    public List<PigStatoSessioneIngest> getPigStatoSessioneIngests() {
        return this.pigStatoSessioneIngests;
    }

    public void setPigStatoSessioneIngests(List<PigStatoSessioneIngest> pigStatoSessioneIngests) {
        this.pigStatoSessioneIngests = pigStatoSessioneIngests;
    }

    // bi-directional many-to-one association to PigUnitaDocSessione
    @OneToMany(mappedBy = "pigSessioneIngest", cascade = {
            CascadeType.PERSIST })
    public List<PigFascicoloSessione> getPigFascicoloSessiones() {
        return this.pigFascicoloSessiones;
    }

    public void setPigFascicoloSessiones(List<PigFascicoloSessione> pigFascicoloSessiones) {
        this.pigFascicoloSessiones = pigFascicoloSessiones;
    }

    @Column(name = "NI_FASCICOLI_DA_VERS")
    public BigDecimal getNiFascicoliDaVers() {
        return niFascicoliDaVers;
    }

    public void setNiFascicoliDaVers(BigDecimal niFascicoliDaVers) {
        this.niFascicoliDaVers = niFascicoliDaVers;
    }

    @Column(name = "NI_FASCICOLI_VERS")
    public BigDecimal getNiFascicoliVers() {
        return niFascicoliVers;
    }

    public void setNiFascicoliVers(BigDecimal niFascicoliVers) {
        this.niFascicoliVers = niFascicoliVers;
    }

    @Column(name = "NI_FASCICOLI_VERS_ERR")
    public BigDecimal getNiFascicoliVersErr() {
        return niFascicoliVersErr;
    }

    public void setNiFascicoliVersErr(BigDecimal niFascicoliVersErr) {
        this.niFascicoliVersErr = niFascicoliVersErr;
    }

    @Column(name = "NI_FASCICOLI_VERS_OK")
    public BigDecimal getNiFascicoliVersOk() {
        return niFascicoliVersOk;
    }

    public void setNiFascicoliVersOk(BigDecimal niFascicoliVersOk) {
        this.niFascicoliVersOk = niFascicoliVersOk;
    }

    @Column(name = "NI_FASCICOLI_VERS_TIMEOUT")
    public BigDecimal getNiFascicoliVersTimeout() {
        return niFascicoliVersTimeout;
    }

    public void setNiFascicoliVersTimeout(BigDecimal niFascicoliVersTimeout) {
        this.niFascicoliVersTimeout = niFascicoliVersTimeout;
    }

}
