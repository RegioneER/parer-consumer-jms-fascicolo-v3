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
 * Java class for EsitoVersamentoNegativoType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="EsitoVersamentoNegativoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EsitoGenerale" type="{}EsitoGeneraleType"/&gt;
 *         &lt;element name="ErroriUlteriori" type="{}ECErroriUlterioriType" minOccurs="0"/&gt;
 *         &lt;element name="WarningUlteriori" type="{}ECWarningUlterioriType" minOccurs="0"/&gt;
 *         &lt;element name="EsitoChiamataWS" type="{}ECEsitoChiamataWSType"/&gt;
 *         &lt;element name="EsitoXSD" type="{}ECEsitoXSDType" minOccurs="0"/&gt;
 *         &lt;element name="ParametriVersamento" type="{}ECConfigurazioneSIPType" minOccurs="0"/&gt;
 *         &lt;element name="ConfigurazioneStruttura" type="{}ECConfigurazioneType" minOccurs="0"/&gt;
 *         &lt;element name="Fascicolo" type="{}ECFascicoloType" minOccurs="0"/&gt;
 *         &lt;element name="IndiceSIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RapportoVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EsitoVersamentoNegativoType", propOrder = {
        "esitoGenerale", "erroriUlteriori", "warningUlteriori", "esitoChiamataWS", "esitoXSD",
        "parametriVersamento", "configurazioneStruttura", "fascicolo", "indiceSIP",
        "rapportoVersamento" })
public class EsitoVersamentoNegativoType {

    @XmlElement(name = "EsitoGenerale", required = true)
    protected EsitoGeneraleType esitoGenerale;
    @XmlElement(name = "ErroriUlteriori")
    protected ECErroriUlterioriType erroriUlteriori;
    @XmlElement(name = "WarningUlteriori")
    protected ECWarningUlterioriType warningUlteriori;
    @XmlElement(name = "EsitoChiamataWS", required = true)
    protected ECEsitoChiamataWSType esitoChiamataWS;
    @XmlElement(name = "EsitoXSD")
    protected ECEsitoXSDType esitoXSD;
    @XmlElement(name = "ParametriVersamento")
    protected ECConfigurazioneSIPType parametriVersamento;
    @XmlElement(name = "ConfigurazioneStruttura")
    protected ECConfigurazioneType configurazioneStruttura;
    @XmlElement(name = "Fascicolo")
    protected ECFascicoloType fascicolo;
    @XmlElement(name = "IndiceSIP")
    protected String indiceSIP;
    @XmlElement(name = "RapportoVersamento")
    protected String rapportoVersamento;

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
     * Gets the value of the erroriUlteriori property.
     *
     * @return possible object is {@link ECErroriUlterioriType }
     */
    public ECErroriUlterioriType getErroriUlteriori() {
        return erroriUlteriori;
    }

    /**
     * Sets the value of the erroriUlteriori property.
     *
     * @param value allowed object is {@link ECErroriUlterioriType }
     */
    public void setErroriUlteriori(ECErroriUlterioriType value) {
        this.erroriUlteriori = value;
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
     * Gets the value of the indiceSIP property.
     *
     * @return possible object is {@link String }
     */
    public String getIndiceSIP() {
        return indiceSIP;
    }

    /**
     * Sets the value of the indiceSIP property.
     *
     * @param value allowed object is {@link String }
     */
    public void setIndiceSIP(String value) {
        this.indiceSIP = value;
    }

    /**
     * Gets the value of the rapportoVersamento property.
     *
     * @return possible object is {@link String }
     */
    public String getRapportoVersamento() {
        return rapportoVersamento;
    }

    /**
     * Sets the value of the rapportoVersamento property.
     *
     * @param value allowed object is {@link String }
     */
    public void setRapportoVersamento(String value) {
        this.rapportoVersamento = value;
    }

}
