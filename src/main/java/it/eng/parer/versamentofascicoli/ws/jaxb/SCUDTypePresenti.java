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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Java class for SCUDTypePresenti complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SCUDTypePresenti"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="NumeroUnitaDocumentariePresenti" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="UnitaDocumentaria" type="{}SCChiaveUDType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SCUDTypePresenti", propOrder = {
        "numeroUnitaDocumentariePresenti", "unitaDocumentaria" })
public class SCUDTypePresenti {

    @XmlElement(name = "NumeroUnitaDocumentariePresenti", required = true)
    protected BigInteger numeroUnitaDocumentariePresenti;
    @XmlElement(name = "UnitaDocumentaria")
    protected List<SCChiaveUDType> unitaDocumentaria;

    /**
     * Gets the value of the numeroUnitaDocumentariePresenti property.
     *
     * @return possible object is {@link BigInteger }
     */
    public BigInteger getNumeroUnitaDocumentariePresenti() {
        return numeroUnitaDocumentariePresenti;
    }

    /**
     * Sets the value of the numeroUnitaDocumentariePresenti property.
     *
     * @param value allowed object is {@link BigInteger }
     */
    public void setNumeroUnitaDocumentariePresenti(BigInteger value) {
        this.numeroUnitaDocumentariePresenti = value;
    }

    /**
     * Gets the value of the unitaDocumentaria property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the Jakarta XML Binding
     * object. This is why there is not a <CODE>set</CODE> method for the unitaDocumentaria
     * property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     *
     * <pre>
     * getUnitaDocumentaria().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link SCChiaveUDType }
     */
    public List<SCChiaveUDType> getUnitaDocumentaria() {
        if (unitaDocumentaria == null) {
            unitaDocumentaria = new ArrayList<SCChiaveUDType>();
        }
        return this.unitaDocumentaria;
    }

}
