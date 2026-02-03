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

package it.eng.parer.versamentofascicoli.ws.jaxb;

import jakarta.xml.bind.annotation.*;

/**
 * <p>
 * Java class for SCContenutoType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SCContenutoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="NumeroUnitaDocumentarie" type="{}IntMax4DgtType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SCContenutoType", propOrder = {
        "numeroUnitaDocumentarie" })
public class SCContenutoType {

    @XmlElement(name = "NumeroUnitaDocumentarie")
    @XmlSchemaType(name = "integer")
    protected int numeroUnitaDocumentarie;

    /**
     * Gets the value of the numeroUnitaDocumentarie property.
     */
    public int getNumeroUnitaDocumentarie() {
        return numeroUnitaDocumentarie;
    }

    /**
     * Sets the value of the numeroUnitaDocumentarie property.
     */
    public void setNumeroUnitaDocumentarie(int value) {
        this.numeroUnitaDocumentarie = value;
    }

}
