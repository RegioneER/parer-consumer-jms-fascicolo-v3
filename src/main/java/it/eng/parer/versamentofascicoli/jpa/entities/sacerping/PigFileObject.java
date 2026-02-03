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

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The persistent class for the PIG_FILE_OBJECT database table.
 */
@Entity
@Table(name = "PIG_FILE_OBJECT")
public class PigFileObject implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long idFileObject;
    private PigObject pigObject;
    private PigTipoFileObject pigTipoFileObject;
    private PigFileObjectStorage pigFileObjectStorage;

    public PigFileObject() {
        // for Hibernate
    }

    @Id
    @Column(name = "ID_FILE_OBJECT")
    public Long getIdFileObject() {
        return this.idFileObject;
    }

    public void setIdFileObject(Long idFileObject) {
        this.idFileObject = idFileObject;
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

    @OneToOne(mappedBy = "pigFileObject", cascade = {
            CascadeType.PERSIST })
    public PigFileObjectStorage getPigFileObjectStorage() {
        return pigFileObjectStorage;
    }

    public void setPigFileObjectStorage(PigFileObjectStorage pigFileObjectStorage) {
        this.pigFileObjectStorage = pigFileObjectStorage;
    }
}
