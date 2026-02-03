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

package it.eng.parer.versamentofascicoli.dto;

/**
 * @author Cappelli_F
 */
public class VersamentoFascicoloMessage {
    private Long objectId;
    private Long sessionId;
    private Long fascicoloId;
    private Long fascicoloSessionId;
    private Integer aaFascicoloSacer;
    private String cdKeyFascicoloSacer;
    private String urlServVersamento;
    private String userIdSacer;
    private String passwordSacer;

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getFascicoloId() {
        return fascicoloId;
    }

    public void setFascicoloId(Long fascicoloId) {
        this.fascicoloId = fascicoloId;
    }

    public Long getFascicoloSessionId() {
        return fascicoloSessionId;
    }

    public void setFascicoloSessionId(Long fascicoloSessionId) {
        this.fascicoloSessionId = fascicoloSessionId;
    }

    public Integer getAaFascicoloSacer() {
        return aaFascicoloSacer;
    }

    public void setAaFascicoloSacer(Integer aaFascicoloSacer) {
        this.aaFascicoloSacer = aaFascicoloSacer;
    }

    public String getCdKeyFascicoloSacer() {
        return cdKeyFascicoloSacer;
    }

    public void setCdKeyFascicoloSacer(String cdKeyFascicoloSacer) {
        this.cdKeyFascicoloSacer = cdKeyFascicoloSacer;
    }

    public String getUrlServVersamento() {
        return urlServVersamento;
    }

    public void setUrlServVersamento(String urlServVersamento) {
        this.urlServVersamento = urlServVersamento;
    }

    public String getUserIdSacer() {
        return userIdSacer;
    }

    public void setUserIdSacer(String userIdSacer) {
        this.userIdSacer = userIdSacer;
    }

    public String getPasswordSacer() {
        return passwordSacer;
    }

    public void setPasswordSacer(String passwordSacer) {
        this.passwordSacer = passwordSacer;
    }

    @Override
    public String toString() {
        return "VersamentoFascicoloMessage{" + "objctId=" + objectId + ", sessionId=" + sessionId
                + ", fascicoloId=" + fascicoloId + ", fascicoloSessionId=" + fascicoloSessionId
                + ", aaFascicoloSacer=" + aaFascicoloSacer + ", cdKeyFascicoloSacer="
                + cdKeyFascicoloSacer + ", urlServVersamento=" + urlServVersamento
                + ", userIdSacer=" + userIdSacer + ", passwordSacer=" + passwordSacer + '}';
    }
}
