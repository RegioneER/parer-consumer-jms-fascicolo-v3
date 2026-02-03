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
 * Java class for ECWarningType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ECWarningType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CodiceWarning" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="MessaggioWarning" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ECWarningType", propOrder = {
        "codiceWarning", "messaggioWarning" })
public class ECWarningType {

    @XmlElement(name = "CodiceWarning", required = true)
    protected String codiceWarning;
    @XmlElement(name = "MessaggioWarning", required = true)
    protected String messaggioWarning;

    /**
     * Gets the value of the codiceWarning property.
     *
     * @return possible object is {@link String }
     */
    public String getCodiceWarning() {
        return codiceWarning;
    }

    /**
     * Sets the value of the codiceWarning property.
     *
     * @param value allowed object is {@link String }
     */
    public void setCodiceWarning(String value) {
        this.codiceWarning = value;
    }

    /**
     * Gets the value of the messaggioWarning property.
     *
     * @return possible object is {@link String }
     */
    public String getMessaggioWarning() {
        return messaggioWarning;
    }

    /**
     * Sets the value of the messaggioWarning property.
     *
     * @param value allowed object is {@link String }
     */
    public void setMessaggioWarning(String value) {
        this.messaggioWarning = value;
    }

}
