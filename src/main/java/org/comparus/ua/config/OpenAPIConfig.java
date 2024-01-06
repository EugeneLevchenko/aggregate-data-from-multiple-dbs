package org.comparus.ua.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import static org.comparus.ua.constants.OpenAPI.*;

@Configuration
public class OpenAPIConfig {

    @Value("${openapi.dev-url}")
    private String devUrl;

    @Value("${openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI openAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription(DEV_ENV);
        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription(PROD_ENV);
        Contact contact = new Contact();
        contact.setEmail(DEV_EMAIL);
        contact.setName(DEV_NAME);
        Info info = new Info().title(TITLE).version(VERSION).contact(contact).description(DESCRIPTION);
        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}