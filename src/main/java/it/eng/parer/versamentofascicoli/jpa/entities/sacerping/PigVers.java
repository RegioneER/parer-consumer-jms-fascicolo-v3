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
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

/**
 * The persistent class for the PIG_VERS database table.
 */
@Entity
@XmlRootElement
// @Cacheable(true)
@Table(name = "PIG_VERS")
public class PigVers implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long idVers;
    private String dsPathInputFtp;
    private String dsPathTrasf;

    public PigVers() {
        // hibernate
    }

    @Id
    @Column(name = "ID_VERS")
    public Long getIdVers() {
        return this.idVers;
    }

    public void setIdVers(Long idVers) {
        this.idVers = idVers;
    }

    @Column(name = "DS_PATH_INPUT_FTP")
    public String getDsPathInputFtp() {
        return this.dsPathInputFtp;
    }

    public void setDsPathInputFtp(String dsPathInputFtp) {
        this.dsPathInputFtp = dsPathInputFtp;
    }

    @Column(name = "DS_PATH_TRASF")
    public String getDsPathTrasf() {
        return this.dsPathTrasf;
    }

    public void setDsPathTrasf(String dsPathTrasf) {
        this.dsPathTrasf = dsPathTrasf;
    }
}
