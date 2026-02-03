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

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * The persistent class for the PIG_OBJECT_TRASF database table.
 */
@Entity
@Table(name = "PIG_OBJECT_TRASF")
public class PigObjectTrasf implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long idObjectTrasf;
    private String dsPath;
    private PigObject pigObject;
    private PigVers pigVer;

    public PigObjectTrasf() {
        // for Hibernate
    }

    @Id
    @Column(name = "ID_OBJECT_TRASF")
    public Long getIdObjectTrasf() {
        return this.idObjectTrasf;
    }

    public void setIdObjectTrasf(Long idObjectTrasf) {
        this.idObjectTrasf = idObjectTrasf;
    }

    @Column(name = "DS_PATH")
    public String getDsPath() {
        return this.dsPath;
    }

    public void setDsPath(String dsPath) {
        this.dsPath = dsPath;
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

    // bi-directional many-to-one association to PigVer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VERS")
    public PigVers getPigVer() {
        return this.pigVer;
    }

    public void setPigVer(PigVers pigVer) {
        this.pigVer = pigVer;
    }
}
