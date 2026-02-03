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
 * Java class for RapportoVersamentoFascicoloType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="RapportoVersamentoFascicoloType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="VersioneRapportoVersamento" type="{}StringNVMax100Type"/&gt;
 *         &lt;element name="IdentificativoRapportoVersamento" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="DataRapportoVersamento" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="SIP" type="{}SIPType"/&gt;
 *         &lt;element name="EsitoGenerale" type="{}EsitoGeneraleType"/&gt;
 *         &lt;element name="WarningUlteriori" type="{}ECWarningUlterioriType" minOccurs="0"/&gt;
 *         &lt;element name="EsitoChiamataWS" type="{}ECEsitoChiamataWSType"/&gt;
 *         &lt;element name="EsitoXSD" type="{}ECEsitoXSDType"/&gt;
 *         &lt;element name="ParametriVersamento" type="{}ECConfigurazioneSIPType" minOccurs="0"/&gt;
 *         &lt;element name="ConfigurazioneStruttura" type="{}ECConfigurazioneType" minOccurs="0"/&gt;
 *         &lt;element name="Fascicolo" type="{}ECFascicoloType"/&gt;
 *         &lt;element name="StatoConservazione" type="{}ECStatoConsType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RapportoVersamentoFascicoloType", propOrder = {
        "versioneRapportoVersamento", "identificativoRapportoVersamento", "dataRapportoVersamento",
        "sip", "esitoGenerale", "warningUlteriori", "esitoChiamataWS", "esitoXSD",
        "parametriVersamento", "configurazioneStruttura", "fascicolo", "statoConservazione" })
public class RapportoVersamentoFascicoloType {

    @XmlElement(name = "VersioneRapportoVersamento", required = true)
    protected String versioneRapportoVersamento;
    @XmlElement(name = "IdentificativoRapportoVersamento", required = true)
    protected String identificativoRapportoVersamento;
    @XmlElement(name = "DataRapportoVersamento", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRapportoVersamento;
    @XmlElement(name = "SIP", required = true)
    protected SIPType sip;
    @XmlElement(name = "EsitoGenerale", required = true)
    protected EsitoGeneraleType esitoGenerale;
    @XmlElement(name = "WarningUlteriori")
    protected ECWarningUlterioriType warningUlteriori;
    @XmlElement(name = "EsitoChiamataWS", required = true)
    protected ECEsitoChiamataWSType esitoChiamataWS;
    @XmlElement(name = "EsitoXSD", required = true)
    protected ECEsitoXSDType esitoXSD;
    @XmlElement(name = "ParametriVersamento")
    protected ECConfigurazioneSIPType parametriVersamento;
    @XmlElement(name = "ConfigurazioneStruttura")
    protected ECConfigurazioneType configurazioneStruttura;
    @XmlElement(name = "Fascicolo", required = true)
    protected ECFascicoloType fascicolo;
    @XmlElement(name = "StatoConservazione")
    @XmlSchemaType(name = "NMTOKEN")
    protected ECStatoConsType statoConservazione;

    /**
     * Gets the value of the versioneRapportoVersamento property.
     *
     * @return possible object is {@link String }
     */
    public String getVersioneRapportoVersamento() {
        return versioneRapportoVersamento;
    }

    /**
     * Sets the value of the versioneRapportoVersamento property.
     *
     * @param value allowed object is {@link String }
     */
    public void setVersioneRapportoVersamento(String value) {
        this.versioneRapportoVersamento = value;
    }

    /**
     * Gets the value of the identificativoRapportoVersamento property.
     *
     * @return possible object is {@link String }
     */
    public String getIdentificativoRapportoVersamento() {
        return identificativoRapportoVersamento;
    }

    /**
     * Sets the value of the identificativoRapportoVersamento property.
     *
     * @param value allowed object is {@link String }
     */
    public void setIdentificativoRapportoVersamento(String value) {
        this.identificativoRapportoVersamento = value;
    }

    /**
     * Gets the value of the dataRapportoVersamento property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getDataRapportoVersamento() {
        return dataRapportoVersamento;
    }

    /**
     * Sets the value of the dataRapportoVersamento property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     */
    public void setDataRapportoVersamento(XMLGregorianCalendar value) {
        this.dataRapportoVersamento = value;
    }

    /**
     * Gets the value of the sip property.
     *
     * @return possible object is {@link SIPType }
     */
    public SIPType getSIP() {
        return sip;
    }

    /**
     * Sets the value of the sip property.
     *
     * @param value allowed object is {@link SIPType }
     */
    public void setSIP(SIPType value) {
        this.sip = value;
    }

    /**
     * Gets the value of the esitoGenerale property.
     *
     * @return possible object is {@link EsitoGeneraleType }
     */
    public EsitoGeneraleType getEsitoGenerale() {
        return esitoGenerale;
    }

    /**
     * Sets the value of the esitoGenerale property.
     *
     * @param value allowed object is {@link EsitoGeneraleType }
     */
    public void setEsitoGenerale(EsitoGeneraleType value) {
        this.esitoGenerale = value;
    }

    /**
     * Gets the value of the warningUlteriori property.
     *
     * @return possible object is {@link ECWarningUlterioriType }
     */
    public ECWarningUlterioriType getWarningUlteriori() {
        return warningUlteriori;
    }

    /**
     * Sets the value of the warningUlteriori property.
     *
     * @param value allowed object is {@link ECWarningUlterioriType }
     */
    public void setWarningUlteriori(ECWarningUlterioriType value) {
        this.warningUlteriori = value;
    }

    /**
     * Gets the value of the esitoChiamataWS property.
     *
     * @return possible object is {@link ECEsitoChiamataWSType }
     */
    public ECEsitoChiamataWSType getEsitoChiamataWS() {
        return esitoChiamataWS;
    }

    /**
     * Sets the value of the esitoChiamataWS property.
     *
     * @param value allowed object is {@link ECEsitoChiamataWSType }
     */
    public void setEsitoChiamataWS(ECEsitoChiamataWSType value) {
        this.esitoChiamataWS = value;
    }

    /**
     * Gets the value of the esitoXSD property.
     *
     * @return possible object is {@link ECEsitoXSDType }
     */
    public ECEsitoXSDType getEsitoXSD() {
        return esitoXSD;
    }

    /**
     * Sets the value of the esitoXSD property.
     *
     * @param value allowed object is {@link ECEsitoXSDType }
     */
    public void setEsitoXSD(ECEsitoXSDType value) {
        this.esitoXSD = value;
    }

    /**
     * Gets the value of the parametriVersamento property.
     *
     * @return possible object is {@link ECConfigurazioneSIPType }
     */
    public ECConfigurazioneSIPType getParametriVersamento() {
        return parametriVersamento;
    }

    /**
     * Sets the value of the parametriVersamento property.
     *
     * @param value allowed object is {@link ECConfigurazioneSIPType }
     */
    public void setParametriVersamento(ECConfigurazioneSIPType value) {
        this.parametriVersamento = value;
    }

    /**
     * Gets the value of the configurazioneStruttura property.
     *
     * @return possible object is {@link ECConfigurazioneType }
     */
    public ECConfigurazioneType getConfigurazioneStruttura() {
        return configurazioneStruttura;
    }

    /**
     * Sets the value of the configurazioneStruttura property.
     *
     * @param value allowed object is {@link ECConfigurazioneType }
     */
    public void setConfigurazioneStruttura(ECConfigurazioneType value) {
        this.configurazioneStruttura = value;
    }

    /**
     * Gets the value of the fascicolo property.
     *
     * @return possible object is {@link ECFascicoloType }
     */
    public ECFascicoloType getFascicolo() {
        return fascicolo;
    }

    /**
     * Sets the value of the fascicolo property.
     *
     * @param value allowed object is {@link ECFascicoloType }
     */
    public void setFascicolo(ECFascicoloType value) {
        this.fascicolo = value;
    }

    /**
     * Gets the value of the statoConservazione property.
     *
     * @return possible object is {@link ECStatoConsType }
     */
    public ECStatoConsType getStatoConservazione() {
        return statoConservazione;
    }

    /**
     * Sets the value of the statoConservazione property.
     *
     * @param value allowed object is {@link ECStatoConsType }
     */
    public void setStatoConservazione(ECStatoConsType value) {
        this.statoConservazione = value;
    }

}
