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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The persistent class for the PIG_XML_SACER_UNITA_DOC_SES database table.
 */
@Entity
@Table(name = "PIG_XML_SACER_FASCICOLO_SES")
public class PigXmlSacerFascicoloSes implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long idXmlSacerFascicoloSes;

    public PigXmlSacerFascicoloSes() {
        // hibernate
    }

    @Id
    @Column(name = "ID_XML_SACER_FASCICOLO_SES")
    public Long getIdXmlSacerFascicoloSes() {
        return this.idXmlSacerFascicoloSes;
    }

    public void setIdXmlSacerFascicoloSes(Long idXmlSacerFascicoloSes) {
        this.idXmlSacerFascicoloSes = idXmlSacerFascicoloSes;
    }
}
