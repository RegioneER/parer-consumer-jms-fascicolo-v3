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

import javax.xml.datatype.XMLGregorianCalendar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="VersioneEsitoVersamentoFascicolo" type="{}StringNVMax100Type"/&gt;
 *         &lt;element name="VersioneIndiceSIPFascicolo" type="{}StringNVMax100Type"/&gt;
 *         &lt;element name="DataEsitoVersamentoFascicolo" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;choice&gt;
 *           &lt;element name="RapportoVersamentoFascicolo" type="{}RapportoVersamentoFascicoloType"/&gt;
 *           &lt;element name="EsitoVersamentoNegativo" type="{}EsitoVersamentoNegativoType"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "versioneEsitoVersamentoFascicolo", "versioneIndiceSIPFascicolo",
        "dataEsitoVersamentoFascicolo", "rapportoVersamentoFascicolo", "esitoVersamentoNegativo" })
@XmlRootElement(name = "EsitoVersamentoFascicolo")
public class EsitoVersamentoFascicolo {

    @XmlElement(name = "VersioneEsitoVersamentoFascicolo", required = true)
    protected String versioneEsitoVersamentoFascicolo;
    @XmlElement(name = "VersioneIndiceSIPFascicolo", required = true)
    protected String versioneIndiceSIPFascicolo;
    @XmlElement(name = "DataEsitoVersamentoFascicolo", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEsitoVersamentoFascicolo;
    @XmlElement(name = "RapportoVersamentoFascicolo")
    protected RapportoVersamentoFascicoloType rapportoVersamentoFascicolo;
    @XmlElement(name = "EsitoVersamentoNegativo")
    protected EsitoVersamentoNegativoType esitoVersamentoNegativo;

    /**
     * Gets the value of the versioneEsitoVersamentoFascicolo property.
     *
     * @return possible object is {@link String }
     */
    public String getVersioneEsitoVersamentoFascicolo() {
        return versioneEsitoVersamentoFascicolo;
    }

    /**
     * Sets the value of the versioneEsitoVersamentoFascicolo property.
     *
     * @param value allowed object is {@link String }
     */
    public void setVersioneEsitoVersamentoFascicolo(String value) {
        this.versioneEsitoVersamentoFascicolo = value;
    }

    /**
     * Gets the value of the versioneIndiceSIPFascicolo property.
     *
     * @return possible object is {@link String }
     */
    public String getVersioneIndiceSIPFascicolo() {
        return versioneIndiceSIPFascicolo;
    }

    /**
     * Sets the value of the versioneIndiceSIPFascicolo property.
     *
     * @param value allowed object is {@link String }
     */
    public void setVersioneIndiceSIPFascicolo(String value) {
        this.versioneIndiceSIPFascicolo = value;
    }

    /**
     * Gets the value of the dataEsitoVersamentoFascicolo property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getDataEsitoVersamentoFascicolo() {
        return dataEsitoVersamentoFascicolo;
    }

    /**
     * Sets the value of the dataEsitoVersamentoFascicolo property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     */
    public void setDataEsitoVersamentoFascicolo(XMLGregorianCalendar value) {
        this.dataEsitoVersamentoFascicolo = value;
    }

    /**
     * Gets the value of the rapportoVersamentoFascicolo property.
     *
     * @return possible object is {@link RapportoVersamentoFascicoloType }
     */
    public RapportoVersamentoFascicoloType getRapportoVersamentoFascicolo() {
        return rapportoVersamentoFascicolo;
    }

    /**
     * Sets the value of the rapportoVersamentoFascicolo property.
     *
     * @param value allowed object is {@link RapportoVersamentoFascicoloType }
     */
    public void setRapportoVersamentoFascicolo(RapportoVersamentoFascicoloType value) {
        this.rapportoVersamentoFascicolo = value;
    }

    /**
     * Gets the value of the esitoVersamentoNegativo property.
     *
     * @return possible object is {@link EsitoVersamentoNegativoType }
     */
    public EsitoVersamentoNegativoType getEsitoVersamentoNegativo() {
        return esitoVersamentoNegativo;
    }

    /**
     * Sets the value of the esitoVersamentoNegativo property.
     *
     * @param value allowed object is {@link EsitoVersamentoNegativoType }
     */
    public void setEsitoVersamentoNegativo(EsitoVersamentoNegativoType value) {
        this.esitoVersamentoNegativo = value;
    }

}
