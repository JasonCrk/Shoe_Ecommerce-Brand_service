package com.shoe_ecommerce.brand.context.presentation.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public final class SwaggerUIConfig {

    @Bean
    public OpenAPI workflowServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Shoe E-commerce - Brand service")
                        .version("v1.0.0"));
    }
}
