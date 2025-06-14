package com.example.demo.controller;

import com.example.demo.service.ErpClientService;
import com.example.demo.service.ErpDirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

// Imports para OpenAPI/Swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/erp")
@CrossOrigin(origins = "*")
@Tag(name = "ERP Integration", description = "API para comunicación con servlets del ERP")
public class ErpIntegrationController {    @Autowired
    private ErpClientService erpClientService;

    @Autowired
    private ErpDirectService erpDirectService;

    @PostMapping("/validar-usuario")
    @Operation(
        summary = "Validar usuario en el ERP",
        description = "Realiza una llamada al servlet ValidarUsuario del ERP"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario validado exitosamente"),
        @ApiResponse(responseCode = "401", description = "Credenciales inválidas"),
        @ApiResponse(responseCode = "500", description = "Error de comunicación con el ERP")
    })
    public ResponseEntity<String> validarUsuario(
        @Parameter(description = "Email del usuario", required = true)
        @RequestParam String email,
        @Parameter(description = "Contraseña del usuario", required = true)
        @RequestParam String clave) {
        
        return erpClientService.validarUsuario(email, clave);
    }

    @PostMapping("/cambiar-clave")
    @Operation(
        summary = "Cambiar contraseña en el ERP",
        description = "Realiza una llamada al servlet CambiarClave del ERP"
    )
    public ResponseEntity<String> cambiarClave(
        @Parameter(description = "Email del usuario", required = true)
        @RequestParam String email,
        @Parameter(description = "Contraseña actual", required = true)
        @RequestParam String claveActual,
        @Parameter(description = "Nueva contraseña", required = true)
        @RequestParam String claveNueva) {
        
        return erpClientService.cambiarClave(email, claveActual, claveNueva);
    }

    @GetMapping("/buscar-cliente")
    @Operation(
        summary = "Buscar cliente en el ERP",
        description = "Realiza una llamada al servlet BuscarCliente del ERP"
    )
    public ResponseEntity<String> buscarCliente(
        @Parameter(description = "Criterio de búsqueda (email, ruc, nombre, etc.)", required = true)
        @RequestParam String criterio,
        @Parameter(description = "Valor a buscar", required = true)
        @RequestParam String valor) {
        
        return erpClientService.buscarCliente(criterio, valor);
    }

    @PostMapping("/cotizacion")
    @Operation(
        summary = "Enviar cotización al ERP",
        description = "Realiza una llamada al servlet de cotización del ERP"
    )
    public ResponseEntity<String> enviarCotizacion(
        @Parameter(description = "Datos de la cotización", required = true)
        @RequestBody Map<String, Object> cotizacionData) {
        
        return erpClientService.enviarCotizacion(cotizacionData);
    }

    @PostMapping("/llamada-generica")
    @Operation(
        summary = "Llamada genérica POST al ERP",
        description = "Permite hacer una llamada POST genérica a cualquier servlet del ERP"
    )
    public ResponseEntity<String> llamadaGenericaPost(
        @Parameter(description = "Endpoint del servlet (ej: /erp/MiServlet)", required = true)
        @RequestParam String endpoint,
        @Parameter(description = "Datos a enviar", required = true)
        @RequestBody Map<String, Object> data) {
        
        return erpClientService.llamadaPost(endpoint, data);
    }

    @PostMapping("/llamada-form-data")
    @Operation(
        summary = "Llamada con form data al ERP",
        description = "Permite hacer una llamada POST con form data a cualquier servlet del ERP"
    )
    public ResponseEntity<String> llamadaFormData(
        @Parameter(description = "Endpoint del servlet (ej: /erp/MiServlet)", required = true)
        @RequestParam String endpoint,
        @Parameter(description = "Parámetros del formulario", required = true)
        @RequestBody Map<String, String> formParams) {
        
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formParams.forEach(formData::add);
        
        return erpClientService.llamadaPostFormData(endpoint, formData);
    }

    @GetMapping("/llamada-get")
    @Operation(
        summary = "Llamada genérica GET al ERP",
        description = "Permite hacer una llamada GET genérica a cualquier servlet del ERP"
    )
    public ResponseEntity<String> llamadaGenericaGet(
        @Parameter(description = "Endpoint del servlet (ej: /erp/MiServlet)", required = true)
        @RequestParam String endpoint,
        @Parameter(description = "Parámetros de consulta (opcional)", required = false)
        @RequestParam(required = false) Map<String, String> params) {
        
        return erpClientService.llamadaGet(endpoint, params);
    }

    @GetMapping("/info-contexto")
    @Operation(
        summary = "Información del contexto de aplicación",
        description = "Muestra información sobre el contexto actual y si está en el mismo Tomcat que el ERP"
    )
    public ResponseEntity<Map<String, Object>> infoContexto() {
        Map<String, Object> info = new HashMap<>();
        
        info.put("contexto", erpDirectService.getContextInfo());
        info.put("mismoTomcat", erpDirectService.isInSameTomcat());
        info.put("timestamp", System.currentTimeMillis());
        
        return ResponseEntity.ok(info);
    }

    @PostMapping("/directo/validar-usuario")
    @Operation(
        summary = "Validar usuario usando comunicación directa",
        description = "Usa RequestDispatcher para comunicación directa con el servlet del ERP (solo funciona en el mismo Tomcat)"
    )
    public ResponseEntity<Map<String, Object>> validarUsuarioDirecto(
        @RequestParam String email,
        @RequestParam String clave) {
        
        Map<String, Object> resultado = new HashMap<>();
        
        if (!erpDirectService.isInSameTomcat()) {
            resultado.put("error", "No está en el mismo Tomcat. Use el endpoint HTTP regular.");
            return ResponseEntity.badRequest().body(resultado);
        }
        
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("clave", clave);
        
        String respuesta = erpDirectService.llamadaDirectaServlet("/cptsoft-erp-prueba/ValidarUsuario", params);
        
        resultado.put("respuesta", respuesta);
        resultado.put("metodo", "comunicacion_directa");
        
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/webxml-info")
    @Operation(
        summary = "Información del web.xml del ERP",
        description = "Muestra información sobre los mappings cargados desde el web.xml del ERP"
    )
    public ResponseEntity<Map<String, Object>> webXmlInfo() {
        Map<String, Object> info = erpClientService.getWebXmlInfo();
        return ResponseEntity.ok(info);
    }

    @PostMapping("/refresh-webxml")
    @Operation(
        summary = "Refrescar mappings del web.xml",
        description = "Refresca los mappings cargados desde el web.xml del ERP"
    )
    public ResponseEntity<Map<String, Object>> refreshWebXml() {
        erpClientService.refreshWebXmlMappings();
        
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Mappings del web.xml refrescados");
        response.put("timestamp", System.currentTimeMillis());
        response.put("info", erpClientService.getWebXmlInfo());
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/configuracion")
    @Operation(
        summary = "Ver configuración del ERP",
        description = "Muestra la configuración actual de conexión con el ERP"
    )
    public ResponseEntity<Map<String, Object>> verConfiguracion() {
        Map<String, Object> config = new HashMap<>();
        
        // Llamar al método de logging para mostrar configuración
        erpClientService.logConfiguration();
        
        config.put("mensaje", "Configuración mostrada en logs del servidor");
        config.put("timestamp", System.currentTimeMillis());
        
        return ResponseEntity.ok(config);
    }

    @GetMapping("/test-conectividad")
    @Operation(
        summary = "Probar conectividad con el ERP",
        description = "Realiza una llamada de prueba para verificar conectividad con el ERP"
    )
    public ResponseEntity<Map<String, Object>> testConectividad() {
        Map<String, Object> resultado = new HashMap<>();
        
        try {
            // Intentar una llamada simple al ERP
            ResponseEntity<String> response = erpClientService.llamadaGet("/", null);
            
            resultado.put("conectividad", "OK");
            resultado.put("status", response.getStatusCode().value());
            resultado.put("mensaje", "Conexión exitosa con el ERP");
            
            return ResponseEntity.ok(resultado);
            
        } catch (Exception e) {
            resultado.put("conectividad", "ERROR");
            resultado.put("mensaje", "Error de conexión: " + e.getMessage());
            
            return ResponseEntity.status(500).body(resultado);
        }
    }
}
