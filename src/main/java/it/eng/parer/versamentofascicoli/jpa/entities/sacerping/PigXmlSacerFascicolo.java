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
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * The persistent class for the PIG_XML_SACER_UNITA_DOC database table.
 */
@Entity
@Table(name = "PIG_XML_SACER_FASCICOLO")
public class PigXmlSacerFascicolo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long idXmlSacerFascicolo;
    private String blXmlSacer;
    private String tiXmlSacer;
    private PigFascicoloObject pigFascicoloObject;

    public PigXmlSacerFascicolo() {
        // hibernate
    }

    @Id
    @Column(name = "ID_XML_SACER_FASCICOLO")
    public Long getIdXmlSacerFascicolo() {
        return this.idXmlSacerFascicolo;
    }

    public void setIdXmlSacerFascicolo(Long idXmlSacerFascicolo) {
        this.idXmlSacerFascicolo = idXmlSacerFascicolo;
    }

    @Lob()
    @Column(name = "BL_XML_SACER")
    public String getBlXmlSacer() {
        return this.blXmlSacer;
    }

    public void setBlXmlSacer(String blXmlSacer) {
        this.blXmlSacer = blXmlSacer;
    }

    @Column(name = "TI_XML_SACER")
    public String getTiXmlSacer() {
        return this.tiXmlSacer;
    }

    public void setTiXmlSacer(String tiXmlSacer) {
        this.tiXmlSacer = tiXmlSacer;
    }

    // bi-directional many-to-one association to PigUnitaDocObject
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FASCICOLO_OBJECT")
    public PigFascicoloObject getPigFascicoloObject() {
        return this.pigFascicoloObject;
    }

    public void setPigFascicoloObject(PigFascicoloObject pigFascicoloObject) {
        this.pigFascicoloObject = pigFascicoloObject;
    }
}
