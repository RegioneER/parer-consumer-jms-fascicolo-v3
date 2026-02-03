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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;

import org.hibernate.annotations.Immutable;

/**
 * The persistent class for the PIG_TIPO_FILE_OBJECT database table.
 */
@Entity
@Immutable
@Table(name = "PIG_TIPO_FILE_OBJECT")
public class PigTipoFileObject implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long idTipoFileObject;

    public PigTipoFileObject() {
        // non utilizzato
    }

    @Id
    @Column(name = "ID_TIPO_FILE_OBJECT")
    public Long getIdTipoFileObject() {
        return this.idTipoFileObject;
    }

    public void setIdTipoFileObject(Long idTipoFileObject) {
        this.idTipoFileObject = idTipoFileObject;
    }
}
