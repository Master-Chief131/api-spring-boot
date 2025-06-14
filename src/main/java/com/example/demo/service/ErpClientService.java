package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.util.Map;

@Service
public class ErpClientService {    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ErpWebXmlReaderService webXmlReaderService;

    @Value("${erp.base.url:http://localhost:8088}")
    private String erpBaseUrl;

    @Value("${erp.servlet.validar-usuario:/erp/ValidarUsuario}")
    private String validarUsuarioPath;

    @Value("${erp.servlet.cambiar-clave:/erp/CambiarClave}")
    private String cambiarClavePath;

    @Value("${erp.servlet.buscar-cliente:/erp/BuscarCliente}")
    private String buscarClientePath;

    @Value("${erp.servlet.cotizacion:/erp/CotizacionServlet}")
    private String cotizacionPath;

    @Value("${erp.same.tomcat:true}")
    private boolean sameTomcat;    @Value("${erp.webapp.context:/cptsoft-erp-prueba}")
    private String erpWebappContext;

    @Value("${erp.use.webxml.mappings:true}")
    private boolean useWebXmlMappings;    /**
     * Construye la URL optimizada para el ERP
     * Si están en el mismo Tomcat, optimiza la URL
     * Si está habilitado, usa los mappings del web.xml
     */
    private String buildErpUrl(String servletPath) {
        if (useWebXmlMappings && sameTomcat) {
            // Intentar obtener la URL desde el web.xml
            String servletName = extractServletName(servletPath);
            String mappedUrl = webXmlReaderService.getServletUrl(servletName);
            
            if (mappedUrl != null && !mappedUrl.isEmpty()) {
                System.out.println("Usando mapping del web.xml: " + servletName + " -> " + mappedUrl);
                return erpBaseUrl + mappedUrl;
            } else {
                System.out.println("No se encontró mapping en web.xml para: " + servletName + ", usando configuración por defecto");
            }
        }
        
        if (sameTomcat) {
            // Mismo Tomcat: usar localhost con el puerto y contexto específicos
            // servletPath ya incluye el contexto completo (/cptsoft-erp-prueba/ServletName)
            return erpBaseUrl + servletPath;
        } else {
            // Diferentes servidores: usar la URL base configurada
            return erpBaseUrl + servletPath;
        }
    }

    /**
     * Extrae el nombre del servlet de la ruta
     */
    private String extractServletName(String servletPath) {
        if (servletPath == null) return "";
        
        // Remover contexto y obtener solo el nombre del servlet
        // Ej: /cptsoft-erp-prueba/ValidarUsuario -> ValidarUsuario
        int lastSlash = servletPath.lastIndexOf('/');
        if (lastSlash >= 0 && lastSlash < servletPath.length() - 1) {
            return servletPath.substring(lastSlash + 1);
        }
        
        return servletPath;
    }    /**
     * Logs informativos sobre la configuración
     */
    public void logConfiguration() {
        System.out.println("=== ERP Client Configuration ===");
        System.out.println("Same Tomcat: " + sameTomcat);
        System.out.println("Use WebXML Mappings: " + useWebXmlMappings);
        System.out.println("ERP Base URL: " + erpBaseUrl);
        System.out.println("ERP Webapp Context: " + erpWebappContext);
        
        if (useWebXmlMappings) {
            System.out.println("WebXML Mappings:");
            Map<String, String> mappings = webXmlReaderService.getServletMappings();
            mappings.forEach((key, value) -> System.out.println("  " + key + " -> " + value));
        }
        
        System.out.println("Configured URLs:");
        System.out.println("  Validar Usuario: " + buildErpUrl(validarUsuarioPath));
        System.out.println("  Cambiar Clave: " + buildErpUrl(cambiarClavePath));
        System.out.println("  Buscar Cliente: " + buildErpUrl(buscarClientePath));
        System.out.println("  Cotización: " + buildErpUrl(cotizacionPath));
        System.out.println("================================");
    }

    /**
     * Obtiene información sobre los mappings del web.xml
     */
    public Map<String, Object> getWebXmlInfo() {
        return webXmlReaderService.getLoadingInfo();
    }

    /**
     * Refresca los mappings del web.xml
     */
    public void refreshWebXmlMappings() {
        webXmlReaderService.refreshMappings();
    }/**
     * Validar usuario en el ERP
     */
    public ResponseEntity<String> validarUsuario(String email, String clave) {
        try {
            String url = buildErpUrl(validarUsuarioPath);
            
            // Crear headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            
            // Crear parámetros
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("email", email);
            params.add("clave", clave);
            
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
            
            System.out.println("Llamando a ERP en: " + url);
            return restTemplate.postForEntity(url, request, String.class);
            
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al comunicarse con el ERP: " + e.getMessage());
        }
    }    /**
     * Cambiar clave en el ERP
     */
    public ResponseEntity<String> cambiarClave(String email, String claveActual, String claveNueva) {
        try {
            String url = buildErpUrl(cambiarClavePath);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("email", email);
            params.add("claveActual", claveActual);
            params.add("claveNueva", claveNueva);
            
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
            
            System.out.println("Llamando a ERP en: " + url);
            return restTemplate.postForEntity(url, request, String.class);
            
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al comunicarse con el ERP: " + e.getMessage());
        }
    }    /**
     * Buscar cliente en el ERP
     */
    public ResponseEntity<String> buscarCliente(String criterio, String valor) {
        try {
            String url = buildErpUrl(buscarClientePath) + "?criterio=" + criterio + "&valor=" + valor;
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            HttpEntity<String> request = new HttpEntity<>(headers);
            
            System.out.println("Llamando a ERP en: " + url);
            return restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al comunicarse con el ERP: " + e.getMessage());
        }
    }    /**
     * Enviar cotización al ERP
     */
    public ResponseEntity<String> enviarCotizacion(Map<String, Object> cotizacionData) {
        try {
            String url = buildErpUrl(cotizacionPath);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(cotizacionData, headers);
            
            System.out.println("Llamando a ERP en: " + url);
            return restTemplate.postForEntity(url, request, String.class);
            
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al comunicarse con el ERP: " + e.getMessage());
        }
    }    /**
     * Llamada genérica GET al ERP
     */
    public ResponseEntity<String> llamadaGet(String endpoint, Map<String, String> params) {
        try {
            StringBuilder urlBuilder = new StringBuilder(buildErpUrl(endpoint));
            
            if (params != null && !params.isEmpty()) {
                urlBuilder.append("?");
                params.forEach((key, value) -> 
                    urlBuilder.append(key).append("=").append(value).append("&")
                );
                // Remover último &
                urlBuilder.setLength(urlBuilder.length() - 1);
            }
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            HttpEntity<String> request = new HttpEntity<>(headers);
            
            System.out.println("Llamando a ERP en: " + urlBuilder.toString());
            return restTemplate.exchange(urlBuilder.toString(), HttpMethod.GET, request, String.class);
            
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al comunicarse con el ERP: " + e.getMessage());
        }
    }    /**
     * Llamada genérica POST al ERP
     */
    public ResponseEntity<String> llamadaPost(String endpoint, Object data) {
        try {
            String url = buildErpUrl(endpoint);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            HttpEntity<Object> request = new HttpEntity<>(data, headers);
            
            System.out.println("Llamando a ERP en: " + url);
            return restTemplate.postForEntity(url, request, String.class);
            
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al comunicarse con el ERP: " + e.getMessage());
        }
    }    /**
     * Llamada genérica POST con form data al ERP
     */
    public ResponseEntity<String> llamadaPostFormData(String endpoint, MultiValueMap<String, String> formData) {
        try {
            String url = buildErpUrl(endpoint);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);
            
            System.out.println("Llamando a ERP en: " + url);
            return restTemplate.postForEntity(url, request, String.class);
            
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al comunicarse con el ERP: " + e.getMessage());
        }
    }
}
