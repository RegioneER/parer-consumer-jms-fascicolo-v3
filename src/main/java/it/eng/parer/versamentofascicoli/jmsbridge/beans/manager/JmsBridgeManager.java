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

package it.eng.parer.versamentofascicoli.jmsbridge.beans.manager;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import it.eng.parer.versamentofascicoli.jmsbridge.beans.config.JmsBridgeConfig;
import it.eng.parer.versamentofascicoli.jmsbridge.beans.config.SourceNodesConfig;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.TransactionManager;
import org.apache.activemq.artemis.jms.bridge.JMSBridge;
import org.apache.activemq.artemis.jms.bridge.QualityOfServiceMode;
import org.apache.activemq.artemis.jms.bridge.impl.JMSBridgeImpl;
import org.apache.activemq.artemis.jms.bridge.impl.JNDIConnectionFactoryFactory;
import org.apache.activemq.artemis.jms.bridge.impl.JNDIDestinationFactory;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@ApplicationScoped
public class JmsBridgeManager {

    private static final Logger log = Logger.getLogger(JmsBridgeManager.class);

    @Inject
    JmsBridgeConfig bridgeConfig;

    @Inject
    SourceNodesConfig nodesConfig;

    @Inject
    TransactionManager transactionManager;

    private final List<JMSBridge> bridges = new ArrayList<>();
    private final List<String> bridgeNodeNames = new ArrayList<>();

    void onStart(@Observes StartupEvent ev) {
        log.info("=== Inizializzazione JMS Bridges ===");

        // Verifica se usare XA
        QualityOfServiceMode qosMode = QualityOfServiceMode
                .valueOf(bridgeConfig.qualityOfService().toUpperCase());

        boolean useXA = (qosMode == QualityOfServiceMode.ONCE_AND_ONLY_ONCE);

        if (useXA) {
            log.infof("Modalità XA attivata (QoS: %s)", qosMode);
            log.infof("Transaction Manager: %s",
                    transactionManager != null ? transactionManager.getClass().getSimpleName()
                            : "NONE");

            if (transactionManager == null) {
                log.error(
                        "QoS ONCE_AND_ONLY_ONCE richiede Transaction Manager ma non è disponibile!");
                log.error(
                        "Usare quality-of-service: DUPLICATES_OK oppure aggiungere quarkus-narayana-jta");
                return;
            }
        }

        for (String nodeName : bridgeConfig.sourceNodes()) {
            try {
                SourceNodesConfig.NodeConfig nodeConfig = nodesConfig.nodes().get(nodeName);
                if (nodeConfig == null) {
                    log.warnf("Nodo %s non configurato in source-nodes-config", nodeName);
                    continue;
                }

                log.infof("Creazione bridge per nodo: %s (XA: %s)", nodeName, useXA);
                JMSBridge bridge = createBridge(nodeName, nodeConfig, useXA);

                log.infof("Avvio bridge %s...", nodeName);
                bridge.start();

                bridges.add(bridge);
                bridgeNodeNames.add(nodeName);
                log.infof("Bridge %s avviato con successo", nodeName);

            } catch (Exception e) {
                log.errorf(e, "Errore avvio bridge %s", nodeName);
            }
        }

        log.infof("=== Bridges avviati: %d/%d ===", bridges.size(),
                bridgeConfig.sourceNodes().size());
    }

    private JMSBridge createBridge(String nodeName, SourceNodesConfig.NodeConfig nodeConfig,
            boolean useXA) throws Exception {

        // Usa XAConnectionFactory se XA è abilitato
        String connectionFactoryName = useXA ? "XAConnectionFactory" : "ConnectionFactory";

        // Parametri JNDI per il server REMOTO (source)
        Hashtable<String, String> sourceJndiParams = createRemoteJndiParams(nodeConfig, useXA);

        // Parametri JNDI per il server LOCALE (target)
        Hashtable<String, String> targetJndiParams = createLocalJndiParams(useXA);

        // QoS Mode
        QualityOfServiceMode qosMode = QualityOfServiceMode
                .valueOf(bridgeConfig.qualityOfService().toUpperCase());

        log.infof("=== Configurazione Bridge %s ===", nodeName);
        log.infof("  Source: %s:%d", nodeConfig.host(), nodeConfig.port());
        log.infof("  Source CF: %s", connectionFactoryName);
        log.infof("  Source Queue JNDI: %s -> %s", bridgeConfig.sourceQueueJndi(),
                bridgeConfig.sourceQueuePhysical());
        log.infof("  Target: %s", bridgeConfig.targetConnection().url());
        log.infof("  Target CF: %s", connectionFactoryName);
        log.infof("  Target Queue JNDI: %s -> %s", bridgeConfig.targetQueueJndi(),
                bridgeConfig.targetQueuePhysical());
        log.infof("  QoS: %s (XA: %s)", qosMode, useXA);

        // Crea il bridge con il costruttore standard
        JMSBridgeImpl bridge = new JMSBridgeImpl(
                new JNDIConnectionFactoryFactory(sourceJndiParams, connectionFactoryName),
                new JNDIConnectionFactoryFactory(targetJndiParams, connectionFactoryName),
                new JNDIDestinationFactory(sourceJndiParams, bridgeConfig.sourceQueueJndi()),
                new JNDIDestinationFactory(targetJndiParams, bridgeConfig.targetQueueJndi()),
                nodeConfig.user(), nodeConfig.password(),
                bridgeConfig.targetConnection().username(),
                bridgeConfig.targetConnection().password(), null, // selector
                bridgeConfig.failureRetryInterval(), bridgeConfig.maxRetryAttempts(), qosMode,
                bridgeConfig.maxBatchSize(), bridgeConfig.maxBatchTime(), null, // subName
                nodeName + "-bridge", true // addMessageIDInHeader
        );

        // Se XA, impostare il Transaction Manager
        if (useXA && transactionManager != null) {
            bridge.setTransactionManager(transactionManager);
            log.infof("Transaction Manager impostato per bridge %s", nodeName);
        }

        return bridge;
    }

    private Hashtable<String, String> createRemoteJndiParams(
            SourceNodesConfig.NodeConfig nodeConfig, boolean useXA) {

        Hashtable<String, String> jndiProps = new Hashtable<>();

        String brokerUrl = String.format(
                "tcp://%s:%d?httpUpgradeEnabled=true&httpUpgradeEndpoint=http-acceptor&ha=false&useTopologyForLoadBalancing=false&reconnectAttempts=-1&retryInterval=5000",
                nodeConfig.host(), nodeConfig.port());

        jndiProps.put("java.naming.factory.initial",
                "org.apache.activemq.artemis.jndi.ActiveMQInitialContextFactory");

        // ConnectionFactory normale (sempre presente)
        jndiProps.put("connectionFactory.ConnectionFactory", brokerUrl);

        // XAConnectionFactory (solo se XA è abilitato)
        if (useXA) {
            // Artemis usa la STESSA URL per XA e non-XA
            // La differenza è nel tipo di factory che viene creata
            jndiProps.put("connectionFactory.XAConnectionFactory", brokerUrl);
        }

        // Queue mapping
        jndiProps.put("queue." + bridgeConfig.sourceQueueJndi(),
                bridgeConfig.sourceQueuePhysical());

        log.debugf("Remote JNDI params (XA=%s) for %s:%d", useXA, nodeConfig.host(),
                nodeConfig.port());

        return jndiProps;
    }

    private Hashtable<String, String> createLocalJndiParams(boolean useXA) {
        Hashtable<String, String> jndiProps = new Hashtable<>();

        String brokerUrl = bridgeConfig.targetConnection().url();

        jndiProps.put("java.naming.factory.initial",
                "org.apache.activemq.artemis.jndi.ActiveMQInitialContextFactory");

        // ConnectionFactory normale
        jndiProps.put("connectionFactory.ConnectionFactory", brokerUrl);

        // XAConnectionFactory
        if (useXA) {
            jndiProps.put("connectionFactory.XAConnectionFactory", brokerUrl);
        }

        // Queue mapping
        jndiProps.put("queue." + bridgeConfig.targetQueueJndi(),
                bridgeConfig.targetQueuePhysical());

        log.debugf("Local JNDI params (XA=%s)", useXA);

        return jndiProps;
    }

    void onStop(@Observes ShutdownEvent ev) {
        log.info("=== Arresto JMS Bridges ===");

        for (JMSBridge bridge : bridges) {
            try {
                bridge.stop();
            } catch (Exception e) {
                log.warnf(e, "Errore arresto bridge");
            }
        }

        bridges.clear();
        bridgeNodeNames.clear();
        log.info("=== Bridges arrestati ===");
    }

}
