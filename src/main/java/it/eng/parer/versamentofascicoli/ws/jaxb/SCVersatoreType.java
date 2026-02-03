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

/**
 * <p>
 * Java class for SCVersatoreType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SCVersatoreType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Ambiente" type="{}StringNVMax100Type"/&gt;
 *         &lt;element name="Ente" type="{}StringNVMax100Type"/&gt;
 *         &lt;element name="Struttura" type="{}StringNVMax100Type"/&gt;
 *         &lt;element name="UserID" type="{}StringNVMax100Type"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SCVersatoreType", propOrder = {
        "ambiente", "ente", "struttura", "userID" })
public class SCVersatoreType {

    @XmlElement(name = "Ambiente", required = true)
    protected String ambiente;
    @XmlElement(name = "Ente", required = true)
    protected String ente;
    @XmlElement(name = "Struttura", required = true)
    protected String struttura;
    @XmlElement(name = "UserID", required = true)
    protected String userID;

    /**
     * Gets the value of the ambiente property.
     *
     * @return possible object is {@link String }
     */
    public String getAmbiente() {
        return ambiente;
    }

    /**
     * Sets the value of the ambiente property.
     *
     * @param value allowed object is {@link String }
     */
    public void setAmbiente(String value) {
        this.ambiente = value;
    }

    /**
     * Gets the value of the ente property.
     *
     * @return possible object is {@link String }
     */
    public String getEnte() {
        return ente;
    }

    /**
     * Sets the value of the ente property.
     *
     * @param value allowed object is {@link String }
     */
    public void setEnte(String value) {
        this.ente = value;
    }

    /**
     * Gets the value of the struttura property.
     *
     * @return possible object is {@link String }
     */
    public String getStruttura() {
        return struttura;
    }

    /**
     * Sets the value of the struttura property.
     *
     * @param value allowed object is {@link String }
     */
    public void setStruttura(String value) {
        this.struttura = value;
    }

    /**
     * Gets the value of the userID property.
     *
     * @return possible object is {@link String }
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the value of the userID property.
     *
     * @param value allowed object is {@link String }
     */
    public void setUserID(String value) {
        this.userID = value;
    }

}
