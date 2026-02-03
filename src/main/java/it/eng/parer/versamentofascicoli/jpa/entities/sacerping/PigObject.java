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

package it.eng.parer.versamentofascicoli.jpa.entities.sacerping;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the PIG_OBJECT database table.
 */
@Entity
@Table(name = "PIG_OBJECT")
public class PigObject implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long idObject;
    private String cdKeyObject;
    private BigDecimal idLastSessioneIngest;
    private String tiGestOggettiFigli;
    private String tiStatoObject;
    private List<PigFileObject> pigFileObjects = new ArrayList<>();
    private PigObject pigObjectPadre;
    private PigVers pigVer;
    private List<PigFascicoloObject> pigFascicoloObjects = new ArrayList<>();
    private List<PigObjectTrasf> pigObjectTrasfs = new ArrayList<>();
    private List<PigSessioneIngest> pigSessioneIngests = new ArrayList<>();

    public PigObject() {
        // for Hibernate
    }

    @Id
    @Column(name = "ID_OBJECT")
    public Long getIdObject() {
        return this.idObject;
    }

    public void setIdObject(Long idObject) {
        this.idObject = idObject;
    }

    @Column(name = "CD_KEY_OBJECT")
    public String getCdKeyObject() {
        return this.cdKeyObject;
    }

    public void setCdKeyObject(String cdKeyObject) {
        this.cdKeyObject = cdKeyObject;
    }

    @Column(name = "ID_LAST_SESSIONE_INGEST")
    public BigDecimal getIdLastSessioneIngest() {
        return this.idLastSessioneIngest;
    }

    public void setIdLastSessioneIngest(BigDecimal idLastSessioneIngest) {
        this.idLastSessioneIngest = idLastSessioneIngest;
    }

    @Column(name = "TI_GEST_OGGETTI_FIGLI")
    public String getTiGestOggettiFigli() {
        return tiGestOggettiFigli;
    }

    public void setTiGestOggettiFigli(String tiGestOggettiFigli) {
        this.tiGestOggettiFigli = tiGestOggettiFigli;
    }

    @Column(name = "TI_STATO_OBJECT")
    public String getTiStatoObject() {
        return this.tiStatoObject;
    }

    public void setTiStatoObject(String tiStatoObject) {
        this.tiStatoObject = tiStatoObject;
    }

    // bi-directional many-to-one association to PigFileObject
    @OneToMany(mappedBy = "pigObject")
    public List<PigFileObject> getPigFileObjects() {
        return this.pigFileObjects;
    }

    public void setPigFileObjects(List<PigFileObject> pigFileObjects) {
        this.pigFileObjects = pigFileObjects;
    }

    // bi-directional many-to-one association to PigObject
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_OBJECT_PADRE")
    public PigObject getPigObjectPadre() {
        return this.pigObjectPadre;
    }

    public void setPigObjectPadre(PigObject pigObjectPadre) {
        this.pigObjectPadre = pigObjectPadre;
    }

    // bi-directional many-to-one association to PigVer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VERS")
    public PigVers getPigVer() {
        return this.pigVer;
    }

    public void setPigVer(PigVers pigVer) {
        this.pigVer = pigVer;
    }

    // bi-directional many-to-one association to PigSessioneIngest
    @OneToMany(mappedBy = "pigObject")
    public List<PigSessioneIngest> getPigSessioneIngests() {
        return this.pigSessioneIngests;
    }

    public void setPigSessioneIngests(List<PigSessioneIngest> pigSessioneIngests) {
        this.pigSessioneIngests = pigSessioneIngests;
    }

    // bi-directional many-to-one association to PigObjectTrasf
    @OneToMany(mappedBy = "pigObject")
    public List<PigObjectTrasf> getPigObjectTrasfs() {
        return this.pigObjectTrasfs;
    }

    public void setPigObjectTrasfs(List<PigObjectTrasf> pigObjectTrasfs) {
        this.pigObjectTrasfs = pigObjectTrasfs;
    }

    // bi-directional many-to-one association to PigFascicoloObject
    @OneToMany(mappedBy = "pigObject", cascade = {
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    public List<PigFascicoloObject> getPigFascicoloObjects() {
        return this.pigFascicoloObjects;
    }

    public void setPigFascicoloObjects(List<PigFascicoloObject> pigFascicoloObjects) {
        this.pigFascicoloObjects = pigFascicoloObjects;
    }
}
