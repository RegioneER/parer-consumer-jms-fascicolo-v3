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

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for SIPType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SIPType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="URNSIP" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="URNIndiceSIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DataVersamento" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SIPType", propOrder = {
        "urnsip", "urnIndiceSIP", "dataVersamento" })
public class SIPType {

    @XmlElement(name = "URNSIP", required = true)
    protected String urnsip;
    @XmlElement(name = "URNIndiceSIP")
    protected String urnIndiceSIP;
    @XmlElement(name = "DataVersamento", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataVersamento;

    /**
     * Gets the value of the urnsip property.
     *
     * @return possible object is {@link String }
     */
    public String getURNSIP() {
        return urnsip;
    }

    /**
     * Sets the value of the urnsip property.
     *
     * @param value allowed object is {@link String }
     */
    public void setURNSIP(String value) {
        this.urnsip = value;
    }

    /**
     * Gets the value of the urnIndiceSIP property.
     *
     * @return possible object is {@link String }
     */
    public String getURNIndiceSIP() {
        return urnIndiceSIP;
    }

    /**
     * Sets the value of the urnIndiceSIP property.
     *
     * @param value allowed object is {@link String }
     */
    public void setURNIndiceSIP(String value) {
        this.urnIndiceSIP = value;
    }

    /**
     * Gets the value of the dataVersamento property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getDataVersamento() {
        return dataVersamento;
    }

    /**
     * Sets the value of the dataVersamento property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     */
    public void setDataVersamento(XMLGregorianCalendar value) {
        this.dataVersamento = value;
    }

}
