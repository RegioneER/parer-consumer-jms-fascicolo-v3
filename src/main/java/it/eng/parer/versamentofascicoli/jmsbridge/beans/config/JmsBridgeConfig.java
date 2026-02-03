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

package it.eng.parer.versamentofascicoli.jmsbridge.beans.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import java.util.List;

@ConfigMapping(prefix = "parer.jms.bridge")
public interface JmsBridgeConfig {

    String sourceQueueJndi();

    String sourceQueuePhysical();

    String targetQueueJndi();

    String targetQueuePhysical();

    TargetConnection targetConnection();

    @WithDefault("5000")
    long failureRetryInterval();

    @WithDefault("-1")
    int maxRetryAttempts();

    @WithDefault("10")
    int maxBatchSize();

    @WithDefault("-1")
    long maxBatchTime();

    @WithDefault("ONCE_AND_ONLY_ONCE")
    String qualityOfService();

    List<String> sourceNodes();

    interface TargetConnection {
        String url();

        @WithDefault("artemis")
        String username();

        @WithDefault("artemis")
        String password();
    }
}
