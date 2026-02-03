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
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for ECFascicoloType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ECFascicoloType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Versatore" type="{}SCVersatoreType"/&gt;
 *         &lt;element name="SoggettoProduttore" type="{}SCSoggettoProduttoreType" minOccurs="0"/&gt;
 *         &lt;element name="Chiave" type="{}SCChiaveType" minOccurs="0"/&gt;
 *         &lt;element name="TipoFascicolo" type="{}TokenNonVuotoType" minOccurs="0"/&gt;
 *         &lt;element name="DataApertura" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="DataChiusura" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="Contenuto" type="{}SCContenutoType" minOccurs="0"/&gt;
 *         &lt;element name="TempoConservazione" type="{}PosIntMax12DgtType" minOccurs="0"/&gt;
 *         &lt;element name="EsitoControlliFascicolo" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="CodiceEsito" type="{}ECEsitoPosNegWarType"/&gt;
 *                   &lt;element name="IdentificazioneVersatore" type="{}ECEsitoPosNegType" minOccurs="0"/&gt;
 *                   &lt;element name="IdentificazioneSoggettoProduttore" type="{}ECEsitoPosNegWarType" minOccurs="0"/&gt;
 *                   &lt;element name="UnivocitaChiave" type="{}ECEsitoPosNegType" minOccurs="0"/&gt;
 *                   &lt;element name="VerificaTipoFascicolo" type="{}ECEsitoPosNegType" minOccurs="0"/&gt;
 *                   &lt;element name="ControlloProfiloArchivistico" type="{}ECEsitoPosNegType" minOccurs="0"/&gt;
 *                   &lt;element name="ControlloProfiloGenerale" type="{}ECEsitoPosNegType" minOccurs="0"/&gt;
 *                   &lt;element name="ControlloProfiloSpecifico" type="{}ECEsitoPosNegType" minOccurs="0"/&gt;
 *                   &lt;element name="ControlloProfiloNormativo" type="{}ECEsitoPosNegType" minOccurs="0"/&gt;
 *                   &lt;element name="ControlloConsistenzaUnitaDocumentarie" type="{}ECEsitoPosNegType" minOccurs="0"/&gt;
 *                   &lt;element name="ControlloConsistenzaFascicoli" type="{}ECEsitoPosNegType" minOccurs="0"/&gt;
 *                   &lt;element name="ControlloClassificazione" type="{}ECEsitoPosNegWarType" minOccurs="0"/&gt;
 *                   &lt;element name="ControlloFormatoNumero" type="{}ECEsitoPosNegWarType" minOccurs="0"/&gt;
 *                   &lt;element name="ControlloCollegamenti" type="{}ECEsitoPosNegWarType" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ControlliContenutoFascicolo" type="{}ECEsitoContenutoFascicoloType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ECFascicoloType", propOrder = {
        "versatore", "soggettoProduttore", "chiave", "tipoFascicolo", "dataApertura",
        "dataChiusura", "contenuto", "tempoConservazione", "esitoControlliFascicolo",
        "controlliContenutoFascicolo" })
public class ECFascicoloType {

    @XmlElement(name = "Versatore", required = true)
    protected SCVersatoreType versatore;
    @XmlElement(name = "SoggettoProduttore")
    protected SCSoggettoProduttoreType soggettoProduttore;
    @XmlElement(name = "Chiave")
    protected SCChiaveType chiave;
    @XmlElement(name = "TipoFascicolo")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String tipoFascicolo;
    @XmlElement(name = "DataApertura")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataApertura;
    @XmlElement(name = "DataChiusura")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataChiusura;
    @XmlElement(name = "Contenuto")
    protected SCContenutoType contenuto;
    @XmlElement(name = "TempoConservazione")
    @XmlSchemaType(name = "integer")
    protected Long tempoConservazione;
    @XmlElement(name = "EsitoControlliFascicolo")
    protected EsitoControlliFascicolo esitoControlliFascicolo;
    @XmlElement(name = "ControlliContenutoFascicolo")
    protected ECEsitoContenutoFascicoloType controlliContenutoFascicolo;

    /**
     * Gets the value of the versatore property.
     *
     * @return possible object is {@link SCVersatoreType }
     */
    public SCVersatoreType getVersatore() {
        return versatore;
    }

    /**
     * Sets the value of the versatore property.
     *
     * @param value allowed object is {@link SCVersatoreType }
     */
    public void setVersatore(SCVersatoreType value) {
        this.versatore = value;
    }

    /**
     * Gets the value of the soggettoProduttore property.
     *
     * @return possible object is {@link SCSoggettoProduttoreType }
     */
    public SCSoggettoProduttoreType getSoggettoProduttore() {
        return soggettoProduttore;
    }

    /**
     * Sets the value of the soggettoProduttore property.
     *
     * @param value allowed object is {@link SCSoggettoProduttoreType }
     */
    public void setSoggettoProduttore(SCSoggettoProduttoreType value) {
        this.soggettoProduttore = value;
    }

    /**
     * Gets the value of the chiave property.
     *
     * @return possible object is {@link SCChiaveType }
     */
    public SCChiaveType getChiave() {
        return chiave;
    }

    /**
     * Sets the value of the chiave property.
     *
     * @param value allowed object is {@link SCChiaveType }
     */
    public void setChiave(SCChiaveType value) {
        this.chiave = value;
    }

    /**
     * Gets the value of the tipoFascicolo property.
     *
     * @return possible object is {@link String }
     */
    public String getTipoFascicolo() {
        return tipoFascicolo;
    }

    /**
     * Sets the value of the tipoFascicolo property.
     *
     * @param value allowed object is {@link String }
     */
    public void setTipoFascicolo(String value) {
        this.tipoFascicolo = value;
    }

    /**
     * Gets the value of the dataApertura property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getDataApertura() {
        return dataApertura;
    }

    /**
     * Sets the value of the dataApertura property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     */
    public void setDataApertura(XMLGregorianCalendar value) {
        this.dataApertura = value;
    }

    /**
     * Gets the value of the dataChiusura property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getDataChiusura() {
        return dataChiusura;
    }

    /**
     * Sets the value of the dataChiusura property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     */
    public void setDataChiusura(XMLGregorianCalendar value) {
        this.dataChiusura = value;
    }

    /**
     * Gets the value of the contenuto property.
     *
     * @return possible object is {@link SCContenutoType }
     */
    public SCContenutoType getContenuto() {
        return contenuto;
    }

    /**
     * Sets the value of the contenuto property.
     *
     * @param value allowed object is {@link SCContenutoType }
     */
    public void setContenuto(SCContenutoType value) {
        this.contenuto = value;
    }

    /**
     * Gets the value of the tempoConservazione property.
     *
     * @return possible object is {@link Long }
     */
    public Long getTempoConservazione() {
        return tempoConservazione;
    }

    /**
     * Sets the value of the tempoConservazione property.
     *
     * @param value allowed object is {@link Long }
     */
    public void setTempoConservazione(Long value) {
        this.tempoConservazione = value;
    }

    /**
     * Gets the value of the esitoControlliFascicolo property.
     *
     * @return possible object is {@link EsitoControlliFascicolo }
     */
    public EsitoControlliFascicolo getEsitoControlliFascicolo() {
        return esitoControlliFascicolo;
    }

    /**
     * Sets the value of the esitoControlliFascicolo property.
     *
     * @param value allowed object is {@link EsitoControlliFascicolo }
     */
    public void setEsitoControlliFascicolo(EsitoControlliFascicolo value) {
        this.esitoControlliFascicolo = value;
    }

    /**
     * Gets the value of the controlliContenutoFascicolo property.
     *
     * @return possible object is {@link ECEsitoContenutoFascicoloType }
     */
    public ECEsitoContenutoFascicoloType getControlliContenutoFascicolo() {
        return controlliContenutoFascicolo;
    }

    /**
     * Sets the value of the controlliContenutoFascicolo property.
     *
     * @param value allowed object is {@link ECEsitoContenutoFascicoloType }
     */
    public void setControlliContenutoFascicolo(ECEsitoContenutoFascicoloType value) {
        this.controlliContenutoFascicolo = value;
    }

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
     *         &lt;element name="CodiceEsito" type="{}ECEsitoPosNegWarType"/&gt;
     *         &lt;element name="IdentificazioneVersatore" type="{}ECEsitoPosNegType" minOccurs="0"/&gt;
     *         &lt;element name="IdentificazioneSoggettoProduttore" type="{}ECEsitoPosNegWarType" minOccurs="0"/&gt;
     *         &lt;element name="UnivocitaChiave" type="{}ECEsitoPosNegType" minOccurs="0"/&gt;
     *         &lt;element name="VerificaTipoFascicolo" type="{}ECEsitoPosNegType" minOccurs="0"/&gt;
     *         &lt;element name="ControlloProfiloArchivistico" type="{}ECEsitoPosNegType" minOccurs="0"/&gt;
     *         &lt;element name="ControlloProfiloGenerale" type="{}ECEsitoPosNegType" minOccurs="0"/&gt;
     *         &lt;element name="ControlloProfiloSpecifico" type="{}ECEsitoPosNegType" minOccurs="0"/&gt;
     *         &lt;element name="ControlloProfiloNormativo" type="{}ECEsitoPosNegType" minOccurs="0"/&gt;
     *         &lt;element name="ControlloConsistenzaUnitaDocumentarie" type="{}ECEsitoPosNegType" minOccurs="0"/&gt;
     *         &lt;element name="ControlloConsistenzaFascicoli" type="{}ECEsitoPosNegType" minOccurs="0"/&gt;
     *         &lt;element name="ControlloClassificazione" type="{}ECEsitoPosNegWarType" minOccurs="0"/&gt;
     *         &lt;element name="ControlloFormatoNumero" type="{}ECEsitoPosNegWarType" minOccurs="0"/&gt;
     *         &lt;element name="ControlloCollegamenti" type="{}ECEsitoPosNegWarType" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "codiceEsito", "identificazioneVersatore", "identificazioneSoggettoProduttore",
            "univocitaChiave", "verificaTipoFascicolo", "controlloProfiloArchivistico",
            "controlloProfiloGenerale", "controlloProfiloSpecifico", "controlloProfiloNormativo",
            "controlloConsistenzaUnitaDocumentarie", "controlloConsistenzaFascicoli",
            "controlloClassificazione", "controlloFormatoNumero", "controlloCollegamenti" })
    public static class EsitoControlliFascicolo {

        @XmlElement(name = "CodiceEsito", required = true)
        @XmlSchemaType(name = "NMTOKEN")
        protected ECEsitoPosNegWarType codiceEsito;
        @XmlElement(name = "IdentificazioneVersatore")
        @XmlSchemaType(name = "NMTOKEN")
        protected ECEsitoPosNegType identificazioneVersatore;
        @XmlElement(name = "IdentificazioneSoggettoProduttore")
        @XmlSchemaType(name = "NMTOKEN")
        protected ECEsitoPosNegWarType identificazioneSoggettoProduttore;
        @XmlElement(name = "UnivocitaChiave")
        @XmlSchemaType(name = "NMTOKEN")
        protected ECEsitoPosNegType univocitaChiave;
        @XmlElement(name = "VerificaTipoFascicolo")
        @XmlSchemaType(name = "NMTOKEN")
        protected ECEsitoPosNegType verificaTipoFascicolo;
        @XmlElement(name = "ControlloProfiloArchivistico")
        @XmlSchemaType(name = "NMTOKEN")
        protected ECEsitoPosNegType controlloProfiloArchivistico;
        @XmlElement(name = "ControlloProfiloGenerale")
        @XmlSchemaType(name = "NMTOKEN")
        protected ECEsitoPosNegType controlloProfiloGenerale;
        @XmlElement(name = "ControlloProfiloSpecifico")
        @XmlSchemaType(name = "NMTOKEN")
        protected ECEsitoPosNegType controlloProfiloSpecifico;
        @XmlElement(name = "ControlloProfiloNormativo")
        @XmlSchemaType(name = "NMTOKEN")
        protected ECEsitoPosNegType controlloProfiloNormativo;
        @XmlElement(name = "ControlloConsistenzaUnitaDocumentarie")
        @XmlSchemaType(name = "NMTOKEN")
        protected ECEsitoPosNegType controlloConsistenzaUnitaDocumentarie;
        @XmlElement(name = "ControlloConsistenzaFascicoli")
        @XmlSchemaType(name = "NMTOKEN")
        protected ECEsitoPosNegType controlloConsistenzaFascicoli;
        @XmlElement(name = "ControlloClassificazione")
        @XmlSchemaType(name = "NMTOKEN")
        protected ECEsitoPosNegWarType controlloClassificazione;
        @XmlElement(name = "ControlloFormatoNumero")
        @XmlSchemaType(name = "NMTOKEN")
        protected ECEsitoPosNegWarType controlloFormatoNumero;
        @XmlElement(name = "ControlloCollegamenti")
        @XmlSchemaType(name = "NMTOKEN")
        protected ECEsitoPosNegWarType controlloCollegamenti;

        /**
         * Gets the value of the codiceEsito property.
         *
         * @return possible object is {@link ECEsitoPosNegWarType }
         */
        public ECEsitoPosNegWarType getCodiceEsito() {
            return codiceEsito;
        }

        /**
         * Sets the value of the codiceEsito property.
         *
         * @param value allowed object is {@link ECEsitoPosNegWarType }
         */
        public void setCodiceEsito(ECEsitoPosNegWarType value) {
            this.codiceEsito = value;
        }

        /**
         * Gets the value of the identificazioneVersatore property.
         *
         * @return possible object is {@link ECEsitoPosNegType }
         */
        public ECEsitoPosNegType getIdentificazioneVersatore() {
            return identificazioneVersatore;
        }

        /**
         * Sets the value of the identificazioneVersatore property.
         *
         * @param value allowed object is {@link ECEsitoPosNegType }
         */
        public void setIdentificazioneVersatore(ECEsitoPosNegType value) {
            this.identificazioneVersatore = value;
        }

        /**
         * Gets the value of the identificazioneSoggettoProduttore property.
         *
         * @return possible object is {@link ECEsitoPosNegWarType }
         */
        public ECEsitoPosNegWarType getIdentificazioneSoggettoProduttore() {
            return identificazioneSoggettoProduttore;
        }

        /**
         * Sets the value of the identificazioneSoggettoProduttore property.
         *
         * @param value allowed object is {@link ECEsitoPosNegWarType }
         */
        public void setIdentificazioneSoggettoProduttore(ECEsitoPosNegWarType value) {
            this.identificazioneSoggettoProduttore = value;
        }

        /**
         * Gets the value of the univocitaChiave property.
         *
         * @return possible object is {@link ECEsitoPosNegType }
         */
        public ECEsitoPosNegType getUnivocitaChiave() {
            return univocitaChiave;
        }

        /**
         * Sets the value of the univocitaChiave property.
         *
         * @param value allowed object is {@link ECEsitoPosNegType }
         */
        public void setUnivocitaChiave(ECEsitoPosNegType value) {
            this.univocitaChiave = value;
        }

        /**
         * Gets the value of the verificaTipoFascicolo property.
         *
         * @return possible object is {@link ECEsitoPosNegType }
         */
        public ECEsitoPosNegType getVerificaTipoFascicolo() {
            return verificaTipoFascicolo;
        }

        /**
         * Sets the value of the verificaTipoFascicolo property.
         *
         * @param value allowed object is {@link ECEsitoPosNegType }
         */
        public void setVerificaTipoFascicolo(ECEsitoPosNegType value) {
            this.verificaTipoFascicolo = value;
        }

        /**
         * Gets the value of the controlloProfiloArchivistico property.
         *
         * @return possible object is {@link ECEsitoPosNegType }
         */
        public ECEsitoPosNegType getControlloProfiloArchivistico() {
            return controlloProfiloArchivistico;
        }

        /**
         * Sets the value of the controlloProfiloArchivistico property.
         *
         * @param value allowed object is {@link ECEsitoPosNegType }
         */
        public void setControlloProfiloArchivistico(ECEsitoPosNegType value) {
            this.controlloProfiloArchivistico = value;
        }

        /**
         * Gets the value of the controlloProfiloGenerale property.
         *
         * @return possible object is {@link ECEsitoPosNegType }
         */
        public ECEsitoPosNegType getControlloProfiloGenerale() {
            return controlloProfiloGenerale;
        }

        /**
         * Sets the value of the controlloProfiloGenerale property.
         *
         * @param value allowed object is {@link ECEsitoPosNegType }
         */
        public void setControlloProfiloGenerale(ECEsitoPosNegType value) {
            this.controlloProfiloGenerale = value;
        }

        /**
         * Gets the value of the controlloProfiloSpecifico property.
         *
         * @return possible object is {@link ECEsitoPosNegType }
         */
        public ECEsitoPosNegType getControlloProfiloSpecifico() {
            return controlloProfiloSpecifico;
        }

        /**
         * Sets the value of the controlloProfiloSpecifico property.
         *
         * @param value allowed object is {@link ECEsitoPosNegType }
         */
        public void setControlloProfiloSpecifico(ECEsitoPosNegType value) {
            this.controlloProfiloSpecifico = value;
        }

        /**
         * Gets the value of the controlloProfiloNormativo property.
         *
         * @return possible object is {@link ECEsitoPosNegType }
         */
        public ECEsitoPosNegType getControlloProfiloNormativo() {
            return controlloProfiloNormativo;
        }

        /**
         * Sets the value of the controlloProfiloNormativo property.
         *
         * @param value allowed object is {@link ECEsitoPosNegType }
         */
        public void setControlloProfiloNormativo(ECEsitoPosNegType value) {
            this.controlloProfiloNormativo = value;
        }

        /**
         * Gets the value of the controlloConsistenzaUnitaDocumentarie property.
         *
         * @return possible object is {@link ECEsitoPosNegType }
         */
        public ECEsitoPosNegType getControlloConsistenzaUnitaDocumentarie() {
            return controlloConsistenzaUnitaDocumentarie;
        }

        /**
         * Sets the value of the controlloConsistenzaUnitaDocumentarie property.
         *
         * @param value allowed object is {@link ECEsitoPosNegType }
         */
        public void setControlloConsistenzaUnitaDocumentarie(ECEsitoPosNegType value) {
            this.controlloConsistenzaUnitaDocumentarie = value;
        }

        /**
         * Gets the value of the controlloConsistenzaFascicoli property.
         *
         * @return possible object is {@link ECEsitoPosNegType }
         */
        public ECEsitoPosNegType getControlloConsistenzaFascicoli() {
            return controlloConsistenzaFascicoli;
        }

        /**
         * Sets the value of the controlloConsistenzaFascicoli property.
         *
         * @param value allowed object is {@link ECEsitoPosNegType }
         */
        public void setControlloConsistenzaFascicoli(ECEsitoPosNegType value) {
            this.controlloConsistenzaFascicoli = value;
        }

        /**
         * Gets the value of the controlloClassificazione property.
         *
         * @return possible object is {@link ECEsitoPosNegWarType }
         */
        public ECEsitoPosNegWarType getControlloClassificazione() {
            return controlloClassificazione;
        }

        /**
         * Sets the value of the controlloClassificazione property.
         *
         * @param value allowed object is {@link ECEsitoPosNegWarType }
         */
        public void setControlloClassificazione(ECEsitoPosNegWarType value) {
            this.controlloClassificazione = value;
        }

        /**
         * Gets the value of the controlloFormatoNumero property.
         *
         * @return possible object is {@link ECEsitoPosNegWarType }
         */
        public ECEsitoPosNegWarType getControlloFormatoNumero() {
            return controlloFormatoNumero;
        }

        /**
         * Sets the value of the controlloFormatoNumero property.
         *
         * @param value allowed object is {@link ECEsitoPosNegWarType }
         */
        public void setControlloFormatoNumero(ECEsitoPosNegWarType value) {
            this.controlloFormatoNumero = value;
        }

        /**
         * Gets the value of the controlloCollegamenti property.
         *
         * @return possible object is {@link ECEsitoPosNegWarType }
         */
        public ECEsitoPosNegWarType getControlloCollegamenti() {
            return controlloCollegamenti;
        }

        /**
         * Sets the value of the controlloCollegamenti property.
         *
         * @param value allowed object is {@link ECEsitoPosNegWarType }
         */
        public void setControlloCollegamenti(ECEsitoPosNegWarType value) {
            this.controlloCollegamenti = value;
        }

    }

}
