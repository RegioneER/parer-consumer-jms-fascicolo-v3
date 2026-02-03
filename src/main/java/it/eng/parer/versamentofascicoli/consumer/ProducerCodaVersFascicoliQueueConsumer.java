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

package it.eng.parer.versamentofascicoli.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import it.eng.parer.versamentofascicoli.beans.services.VersamentoService;
import it.eng.parer.versamentofascicoli.dto.VersamentoFascicoloMessage;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSConsumer;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Cappelli_F
 */
@ApplicationScoped
public class ProducerCodaVersFascicoliQueueConsumer implements Runnable {

    private static final Logger log = LoggerFactory
            .getLogger(ProducerCodaVersFascicoliQueueConsumer.class);

    private final ExecutorService scheduler = Executors.newSingleThreadExecutor();
    @Inject
    ConnectionFactory connectionFactory;
    @Inject
    VersamentoService versamentoService;

    @ConfigProperty(name = "parer.fascicolo.jms-polling-interval", defaultValue = "100")
    long jmsPollingInterval;

    void onStart(@Observes StartupEvent ev) {
        log.info("Starting ProducerCodaVersFascicoliQueue Consumer...");
        scheduler.submit(this);
    }

    void onStop(@Observes ShutdownEvent ev) {
        log.info("...Stopping ProducerCodaVersFascicoliQueue Consumer.");
        scheduler.shutdown();
    }

    @Override
    public void run() {
        try (JMSContext context = connectionFactory.createContext(JMSContext.CLIENT_ACKNOWLEDGE)) {
            JMSConsumer consumer = context
                    .createConsumer(context.createQueue("ProducerCodaVersFascicoliQueue"));
            while (true) {
                Message message = consumer.receive(jmsPollingInterval);
                if (message != null) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        VersamentoFascicoloMessage versamentoFascicoloMessage = objectMapper
                                .readValue(message.getBody(String.class),
                                        VersamentoFascicoloMessage.class);
                        versamentoService.handleVersamentoMessage(versamentoFascicoloMessage);

                        message.acknowledge();
                    } catch (JsonProcessingException e) {
                        message.acknowledge();
                        log.error("Ricevuto pacchetto malformato: ", e);
                    }
                }
            }
        } catch (Exception e) {
            log.error("Errore critico: ", e);
        }
    }

}
