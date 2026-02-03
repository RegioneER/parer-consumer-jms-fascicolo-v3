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
 * Java class for ECConfigurazioneType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ECConfigurazioneType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ForzaClassificazione" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="ForzaNumero" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="ForzaCollegamento" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="AbilitaControlloClassificazione" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="AbilitaControlloFormatoNumero" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="AbilitaControlloCollegamenti" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="AccettaControlloClassificazioneNegativo" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="AccettaControlloFormatoNumeroNegativo" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="AccettaControlloCollegamentiNegativo" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ECConfigurazioneType", propOrder = {
        "forzaClassificazione", "forzaNumero", "forzaCollegamento",
        "abilitaControlloClassificazione", "abilitaControlloFormatoNumero",
        "abilitaControlloCollegamenti", "accettaControlloClassificazioneNegativo",
        "accettaControlloFormatoNumeroNegativo", "accettaControlloCollegamentiNegativo" })
public class ECConfigurazioneType {

    @XmlElement(name = "ForzaClassificazione")
    protected boolean forzaClassificazione;
    @XmlElement(name = "ForzaNumero")
    protected boolean forzaNumero;
    @XmlElement(name = "ForzaCollegamento")
    protected boolean forzaCollegamento;
    @XmlElement(name = "AbilitaControlloClassificazione")
    protected boolean abilitaControlloClassificazione;
    @XmlElement(name = "AbilitaControlloFormatoNumero")
    protected boolean abilitaControlloFormatoNumero;
    @XmlElement(name = "AbilitaControlloCollegamenti")
    protected boolean abilitaControlloCollegamenti;
    @XmlElement(name = "AccettaControlloClassificazioneNegativo")
    protected boolean accettaControlloClassificazioneNegativo;
    @XmlElement(name = "AccettaControlloFormatoNumeroNegativo")
    protected boolean accettaControlloFormatoNumeroNegativo;
    @XmlElement(name = "AccettaControlloCollegamentiNegativo")
    protected boolean accettaControlloCollegamentiNegativo;

    /**
     * Gets the value of the forzaClassificazione property.
     */
    public boolean isForzaClassificazione() {
        return forzaClassificazione;
    }

    /**
     * Sets the value of the forzaClassificazione property.
     */
    public void setForzaClassificazione(boolean value) {
        this.forzaClassificazione = value;
    }

    /**
     * Gets the value of the forzaNumero property.
     */
    public boolean isForzaNumero() {
        return forzaNumero;
    }

    /**
     * Sets the value of the forzaNumero property.
     */
    public void setForzaNumero(boolean value) {
        this.forzaNumero = value;
    }

    /**
     * Gets the value of the forzaCollegamento property.
     */
    public boolean isForzaCollegamento() {
        return forzaCollegamento;
    }

    /**
     * Sets the value of the forzaCollegamento property.
     */
    public void setForzaCollegamento(boolean value) {
        this.forzaCollegamento = value;
    }

    /**
     * Gets the value of the abilitaControlloClassificazione property.
     */
    public boolean isAbilitaControlloClassificazione() {
        return abilitaControlloClassificazione;
    }

    /**
     * Sets the value of the abilitaControlloClassificazione property.
     */
    public void setAbilitaControlloClassificazione(boolean value) {
        this.abilitaControlloClassificazione = value;
    }

    /**
     * Gets the value of the abilitaControlloFormatoNumero property.
     */
    public boolean isAbilitaControlloFormatoNumero() {
        return abilitaControlloFormatoNumero;
    }

    /**
     * Sets the value of the abilitaControlloFormatoNumero property.
     */
    public void setAbilitaControlloFormatoNumero(boolean value) {
        this.abilitaControlloFormatoNumero = value;
    }

    /**
     * Gets the value of the abilitaControlloCollegamenti property.
     */
    public boolean isAbilitaControlloCollegamenti() {
        return abilitaControlloCollegamenti;
    }

    /**
     * Sets the value of the abilitaControlloCollegamenti property.
     */
    public void setAbilitaControlloCollegamenti(boolean value) {
        this.abilitaControlloCollegamenti = value;
    }

    /**
     * Gets the value of the accettaControlloClassificazioneNegativo property.
     */
    public boolean isAccettaControlloClassificazioneNegativo() {
        return accettaControlloClassificazioneNegativo;
    }

    /**
     * Sets the value of the accettaControlloClassificazioneNegativo property.
     */
    public void setAccettaControlloClassificazioneNegativo(boolean value) {
        this.accettaControlloClassificazioneNegativo = value;
    }

    /**
     * Gets the value of the accettaControlloFormatoNumeroNegativo property.
     */
    public boolean isAccettaControlloFormatoNumeroNegativo() {
        return accettaControlloFormatoNumeroNegativo;
    }

    /**
     * Sets the value of the accettaControlloFormatoNumeroNegativo property.
     */
    public void setAccettaControlloFormatoNumeroNegativo(boolean value) {
        this.accettaControlloFormatoNumeroNegativo = value;
    }

    /**
     * Gets the value of the accettaControlloCollegamentiNegativo property.
     */
    public boolean isAccettaControlloCollegamentiNegativo() {
        return accettaControlloCollegamentiNegativo;
    }

    /**
     * Sets the value of the accettaControlloCollegamentiNegativo property.
     */
    public void setAccettaControlloCollegamentiNegativo(boolean value) {
        this.accettaControlloCollegamentiNegativo = value;
    }

}
