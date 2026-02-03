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

import java.io.Serializable;

/**
 * The persistent class for the PIG_AMBIENTE_VERS database table.
 */
@Entity
@Table(name = "PIG_AMBIENTE_VERS")
public class PigAmbienteVers implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long idAmbienteVers;

    public PigAmbienteVers() {
        // for Hibernate
    }

    @Id
    @Column(name = "ID_AMBIENTE_VERS")
    public Long getIdAmbienteVers() {
        return this.idAmbienteVers;
    }

    public void setIdAmbienteVers(Long idAmbienteVers) {
        this.idAmbienteVers = idAmbienteVers;
    }
}
