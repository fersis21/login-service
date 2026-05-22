package com.kaiser.login.service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Fernando Apaza
 * Date: 25/04/2026
 */
@Configuration
@Slf4j
public class config {

    @Value("${server.servlet.context-path}")
    private String context;
    @Bean
    public OpenAPI customOpenAPI(@Value("${spring.application.name}") String appTitle,
                                 @Value("${application-description}") String appDescription,
                                 @Value("${application-version}") String appVersion) {
        log.info("Swagger generate on context path '{}/v3/api-docs'", (context.equals("/") ? "" : context));

        return new OpenAPI()
                .info(new Info()
                        .title(appTitle)
                        .version(appVersion)
                        .description(appDescription)
                        .contact(new Contact()
                                .email("soporte@kaiser.com")
                                .name("Soporte Soluciones Tecnologicas Kaiser")
                                .url("https://www.kaiser.com"))
                        .termsOfService(""));
    }

}
