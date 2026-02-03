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
 * Java class for ECEsitoChiamataWSType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ECEsitoChiamataWSType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CodiceEsito" type="{}ECEsitoPosNegNesType"/&gt;
 *         &lt;element name="VersioneWSCorretta" type="{}ECEsitoPosNegNesType"/&gt;
 *         &lt;element name="CredenzialiOperatore" type="{}ECEsitoPosNegNesType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ECEsitoChiamataWSType", propOrder = {
        "codiceEsito", "versioneWSCorretta", "credenzialiOperatore" })
public class ECEsitoChiamataWSType {

    @XmlElement(name = "CodiceEsito", required = true)
    @XmlSchemaType(name = "NMTOKEN")
    protected ECEsitoPosNegNesType codiceEsito;
    @XmlElement(name = "VersioneWSCorretta", required = true)
    @XmlSchemaType(name = "NMTOKEN")
    protected ECEsitoPosNegNesType versioneWSCorretta;
    @XmlElement(name = "CredenzialiOperatore", required = true)
    @XmlSchemaType(name = "NMTOKEN")
    protected ECEsitoPosNegNesType credenzialiOperatore;

    /**
     * Gets the value of the codiceEsito property.
     *
     * @return possible object is {@link ECEsitoPosNegNesType }
     */
    public ECEsitoPosNegNesType getCodiceEsito() {
        return codiceEsito;
    }

    /**
     * Sets the value of the codiceEsito property.
     *
     * @param value allowed object is {@link ECEsitoPosNegNesType }
     */
    public void setCodiceEsito(ECEsitoPosNegNesType value) {
        this.codiceEsito = value;
    }

    /**
     * Gets the value of the versioneWSCorretta property.
     *
     * @return possible object is {@link ECEsitoPosNegNesType }
     */
    public ECEsitoPosNegNesType getVersioneWSCorretta() {
        return versioneWSCorretta;
    }

    /**
     * Sets the value of the versioneWSCorretta property.
     *
     * @param value allowed object is {@link ECEsitoPosNegNesType }
     */
    public void setVersioneWSCorretta(ECEsitoPosNegNesType value) {
        this.versioneWSCorretta = value;
    }

    /**
     * Gets the value of the credenzialiOperatore property.
     *
     * @return possible object is {@link ECEsitoPosNegNesType }
     */
    public ECEsitoPosNegNesType getCredenzialiOperatore() {
        return credenzialiOperatore;
    }

    /**
     * Sets the value of the credenzialiOperatore property.
     *
     * @param value allowed object is {@link ECEsitoPosNegNesType }
     */
    public void setCredenzialiOperatore(ECEsitoPosNegNesType value) {
        this.credenzialiOperatore = value;
    }

}
