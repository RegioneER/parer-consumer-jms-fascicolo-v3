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
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>
 * Java class for ECConfigurazioneSIPType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ECConfigurazioneSIPType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TipoConservazione" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN"&gt;
 *               &lt;enumeration value="VERSAMENTO_ANTICIPATO"/&gt;
 *               &lt;enumeration value="IN_ARCHIVIO"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ForzaClassificazione" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="ForzaNumero" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="ForzaCollegamento" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ECConfigurazioneSIPType", propOrder = {
        "tipoConservazione", "forzaClassificazione", "forzaNumero", "forzaCollegamento" })
public class ECConfigurazioneSIPType {

    @XmlElement(name = "TipoConservazione")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String tipoConservazione;
    @XmlElement(name = "ForzaClassificazione")
    protected Boolean forzaClassificazione;
    @XmlElement(name = "ForzaNumero")
    protected Boolean forzaNumero;
    @XmlElement(name = "ForzaCollegamento")
    protected Boolean forzaCollegamento;

    /**
     * Gets the value of the tipoConservazione property.
     *
     * @return possible object is {@link String }
     */
    public String getTipoConservazione() {
        return tipoConservazione;
    }

    /**
     * Sets the value of the tipoConservazione property.
     *
     * @param value allowed object is {@link String }
     */
    public void setTipoConservazione(String value) {
        this.tipoConservazione = value;
    }

    /**
     * Gets the value of the forzaClassificazione property.
     *
     * @return possible object is {@link Boolean }
     */
    public Boolean isForzaClassificazione() {
        return forzaClassificazione;
    }

    /**
     * Sets the value of the forzaClassificazione property.
     *
     * @param value allowed object is {@link Boolean }
     */
    public void setForzaClassificazione(Boolean value) {
        this.forzaClassificazione = value;
    }

    /**
     * Gets the value of the forzaNumero property.
     *
     * @return possible object is {@link Boolean }
     */
    public Boolean isForzaNumero() {
        return forzaNumero;
    }

    /**
     * Sets the value of the forzaNumero property.
     *
     * @param value allowed object is {@link Boolean }
     */
    public void setForzaNumero(Boolean value) {
        this.forzaNumero = value;
    }

    /**
     * Gets the value of the forzaCollegamento property.
     *
     * @return possible object is {@link Boolean }
     */
    public Boolean isForzaCollegamento() {
        return forzaCollegamento;
    }

    /**
     * Sets the value of the forzaCollegamento property.
     *
     * @param value allowed object is {@link Boolean }
     */
    public void setForzaCollegamento(Boolean value) {
        this.forzaCollegamento = value;
    }

}
