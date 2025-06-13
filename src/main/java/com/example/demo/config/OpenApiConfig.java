package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Arrays;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Clientes")
                        .description("API REST para la gestión completa de clientes, contactos y usuarios. " +
                                   "Permite realizar operaciones de registro, consulta y actualización de información.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("CPTSoft")
                                .email("desarrollo@empresa.com"))
                        .license(new License()
                                .name("MIT License")
                        )
                )
                .servers(Arrays.asList(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de desarrollo local"),
                        new Server()
                                .url("https://api.Prochem.com")
                                .description("Servidor de producción")
                ));
    };
}
