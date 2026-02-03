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
 * Java class for SCSoggettoProduttoreType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SCSoggettoProduttoreType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Ambiente" type="{}StringNVMax100Type" minOccurs="0"/&gt;
 *         &lt;element name="Codice" type="{}StringNVMax100Type" minOccurs="0"/&gt;
 *         &lt;element name="Denominazione" type="{}StringNVMax254Type" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SCSoggettoProduttoreType", propOrder = {
        "ambiente", "codice", "denominazione" })
public class SCSoggettoProduttoreType {

    @XmlElement(name = "Ambiente")
    protected String ambiente;
    @XmlElement(name = "Codice")
    protected String codice;
    @XmlElement(name = "Denominazione")
    protected String denominazione;

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
     * Gets the value of the codice property.
     *
     * @return possible object is {@link String }
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Sets the value of the codice property.
     *
     * @param value allowed object is {@link String }
     */
    public void setCodice(String value) {
        this.codice = value;
    }

    /**
     * Gets the value of the denominazione property.
     *
     * @return possible object is {@link String }
     */
    public String getDenominazione() {
        return denominazione;
    }

    /**
     * Sets the value of the denominazione property.
     *
     * @param value allowed object is {@link String }
     */
    public void setDenominazione(String value) {
        this.denominazione = value;
    }

}
