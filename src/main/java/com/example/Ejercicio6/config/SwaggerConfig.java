package com.example.Ejercicio6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * Configuracion Swagger para la generacion de documentacion de la API REST
 * @author Alejandro
 *
 * HTML: http://localhost:8080/swagger-ui/
 * JSON: http://localhost:8080/v2/api-docs
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Ejercicio6",
                "API REST para la gestion de laptos",
                "1.0",
                "Terms of service",
                new Contact("Alejandro", "www.alejandro.com", "alejandro.munoza@udea.edu.co"),
                "MIT",
                "www.alejandro.com",
                Collections.emptyList());
    }
}
