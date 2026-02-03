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
 * Java class for SCChiaveType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SCChiaveType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Anno" type="{}IntMax4DgtType"/&gt;
 *         &lt;element name="Numero" type="{}StringNVMax100Type"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SCChiaveType", propOrder = {
        "anno", "numero" })
public class SCChiaveType {

    @XmlElement(name = "Anno", required = true, type = Integer.class, nillable = true)
    @XmlSchemaType(name = "integer")
    protected Integer anno;
    @XmlElement(name = "Numero", required = true)
    protected String numero;

    /**
     * Gets the value of the anno property.
     *
     * @return possible object is {@link Integer }
     */
    public Integer getAnno() {
        return anno;
    }

    /**
     * Sets the value of the anno property.
     *
     * @param value allowed object is {@link Integer }
     */
    public void setAnno(Integer value) {
        this.anno = value;
    }

    /**
     * Gets the value of the numero property.
     *
     * @return possible object is {@link String }
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Sets the value of the numero property.
     *
     * @param value allowed object is {@link String }
     */
    public void setNumero(String value) {
        this.numero = value;
    }

}
