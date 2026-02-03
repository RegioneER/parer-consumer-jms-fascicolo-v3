/*
 * Engineering Ingegneria Informatica S.p.A.
 *
 * Copyright (C) 2023 Regione Emilia-Romagna <p/> This program is free software: you can
 * redistribute it and/or modify it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version. <p/> This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Affero General Public License for more details. <p/> You should
 * have received a copy of the GNU Affero General Public License along with this program. If not,
 * see <https://www.gnu.org/licenses/>.
 */

/**
 *
 */
package it.eng.parer.versamentofascicoli.qute.models;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

@RequestScoped
public class ApplicationInfo {

    @ConfigProperty(name = "quarkus.application.name")
    String name;

    @ConfigProperty(name = "quarkus.http.static-resources.index-page", defaultValue = "0")
    String index;

    @ConfigProperty(name = "quarkus.application.version", defaultValue = "-")
    String version;

    @ConfigProperty(name = "quarkus.swagger-ui.path", defaultValue = "q/swagger-ui")
    String swagger;

    Properties git;

    @PostConstruct
    public void init() throws IOException {
        git = new Properties();
        try (InputStream input = getClass().getResourceAsStream("/git.properties")) {
            // load a properties file
            git.load(input);
        }
    }

    public Optional<String> getVersion() {
        return Optional.of(version);
    }

    public Optional<String> getSwagger() {
        return Optional.of(swagger);
    }

    public String getName() {
        return name;
    }

    public Properties getGit() {
        return git;
    }

    public String getIndex() {
        return index;
    }

}
