package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;

@Configuration
public class OpenApiConfig {

    @Value("${spring.application.name:Api-cptsoft}")
    private String applicationName;

    @Bean
    public OpenAPI customOpenAPI() {
        // Lista de servidores dinámicos
        List<Server> servers = new ArrayList<>();
        
        // Intentar detectar el contexto actual
        String currentContextPath = "";
        try {
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attr != null) {
                HttpServletRequest request = attr.getRequest();
                currentContextPath = request.getContextPath();
                String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + currentContextPath;
                
                // Agregar el servidor actual como primera opción
                servers.add(new Server()
                        .url(baseUrl)
                        .description("Servidor actual - " + (currentContextPath.isEmpty() ? "Desarrollo" : "Tomcat")));
            }
        } catch (Exception e) {
            // En caso de error, continuar con servidores estáticos
        }
        
        // Agregar servidores estáticos como alternativas
        servers.add(new Server()
                .url("http://localhost:8080")
                .description("Servidor de desarrollo local (puerto 8080)"));
        
        servers.add(new Server()
                .url("http://localhost:8088/" + applicationName)
                .description("Servidor Tomcat local (puerto 8088)"));
        
        servers.add(new Server()
                .url("https://api.cptsoft.com/" + applicationName)
                .description("Servidor de producción CPTSoft"));

        return new OpenAPI()
                .info(new Info()
                        .title("API CPTSoft - Gestión de Clientes")
                        .description("API REST para la gestión completa de clientes, contactos y usuarios. " +
                                   "Permite realizar operaciones de registro, consulta y actualización de información.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("CPTSoft")
                                .email("desarrollo@cptsoft.com"))
                        .license(new License()
                                .name("MIT License")
                        )
                )
                .servers(servers);
    };
}
