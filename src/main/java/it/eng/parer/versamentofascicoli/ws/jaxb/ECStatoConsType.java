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

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ECStatoConsType.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;simpleType name="ECStatoConsType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN"&gt;
 *     &lt;enumeration value="PRESA_IN_CARICO"/&gt;
 *     &lt;enumeration value="AIP_GENERATO"/&gt;
 *     &lt;enumeration value="ANNULLATA"/&gt;
 *     &lt;enumeration value="AIP_IN_AGGIORNAMENTO"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "ECStatoConsType")
@XmlEnum
public enum ECStatoConsType {

    PRESA_IN_CARICO, AIP_GENERATO, ANNULLATA, AIP_IN_AGGIORNAMENTO;

    public String value() {
        return name();
    }

    public static ECStatoConsType fromValue(String v) {
        return valueOf(v);
    }

}
