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

package it.eng.parer.versamentofascicoli.rest;

import it.eng.parer.versamentofascicoli.beans.services.VersamentoService;
import it.eng.parer.versamentofascicoli.dto.VersamentoFascicoloMessage;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestQuery;

@Path("/versamentoFascicoloDaPreingest")
public class VersamentoFascicoloDaPreingest {
    @Inject
    VersamentoService versamentoService;

    @Path("/versaFascicoloDaPreingest")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String versaFascicoloDaPreingest(@RestQuery Long idObject, @RestQuery Long idFascicolo,
            @RestQuery Long idSessioneFascicolo, @RestQuery String user,
            @RestQuery String password) {
        VersamentoFascicoloMessage vfe = new VersamentoFascicoloMessage();
        vfe.setObjectId(idObject); // 6440632668
        vfe.setFascicoloId(idFascicolo); // 38974477
        vfe.setFascicoloSessionId(idSessioneFascicolo); // 18324477

        vfe.setUserIdSacer(user);
        vfe.setPasswordSacer(password);

        versamentoService.handleVersamentoMessage(vfe);

        return "Done.";
    }
}
