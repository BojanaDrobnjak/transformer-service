package com.incode.transformer_service.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger configuration class for setting up OpenAPI documentation for
 * the Transformer Service API.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configures the OpenAPI documentation for the Transformer Service API.
     *
     * @return OpenAPI object with basic information about the API.
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(
                new Info().title("Transformer Service API")
                        .description("Transformer Service API")
                        .version("1.0.0"));
    }
}
