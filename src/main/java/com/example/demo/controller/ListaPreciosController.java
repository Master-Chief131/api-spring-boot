package com.example.demo.controller;

import com.example.demo.dto.ServicioConPrecioDTO;
import com.example.demo.repository.InvwebServicioRepository;
import com.example.demo.repository.FacwebGrupoMercadoRepository;
import com.example.demo.entity.FacwebGrupoMercado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

// Imports para OpenAPI/Swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lista-precios")
@Tag(name = "Lista de Precios", description = "API para consulta de todos los servicios con precios de lista de portal")
public class ListaPreciosController {
    
    @Autowired
    private InvwebServicioRepository servicioRepository;
    
    @Autowired
    private FacwebGrupoMercadoRepository grupoMercadoRepository;

    @GetMapping
    @Operation(
        summary = "Obtener todos los servicios con precios de lista de portal",
        description = "Obtiene todos los servicios activos junto con los servicios y precios únicos de la lista de precios de portal. " +
                     "Busca automáticamente el grupo de mercado con indicador de portal ('S') para la compañía especificada " +
                     "y utiliza ese grupo para obtener los precios correspondientes. Si no se especifica compañía, usa la 1 por defecto."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de servicios con precios obtenida exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros de entrada inválidos"),
        @ApiResponse(responseCode = "404", description = "No se encontró grupo de mercado de portal para la compañía especificada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<?> getListaPrecios(
            @Parameter(description = "Número de compañía", example = "1", required = false)
            @RequestParam(required = false, defaultValue = "1") Integer noCia) {
        
        // Buscar el grupo de mercado de portal para la compañía
        List<FacwebGrupoMercado> gruposPortal = grupoMercadoRepository.findByIndPortalAndNoCia("S", noCia);
        
        if (gruposPortal.isEmpty()) {
            return ResponseEntity.badRequest()
                .body("No se encontró grupo de mercado de portal para la compañía " + noCia);
        }
        
        // Usar el primer grupo encontrado (debería ser único)
        Integer noGrupo = gruposPortal.get(0).getNoGrupo();
        
        // Obtener los servicios con precios
        List<Object[]> resultados = servicioRepository.findTodosServiciosConPrecioPortal(noCia, noGrupo);
        
        List<ServicioConPrecioDTO> servicios = resultados.stream()
            .map(this::mapToServicioConPrecioDTO)
            .collect(Collectors.toList());
            
        return ResponseEntity.ok(servicios);
    }

    private ServicioConPrecioDTO mapToServicioConPrecioDTO(Object[] row) {
        try {
            return new ServicioConPrecioDTO(
                row[0] != null ? (Integer) row[0] : null,  // noArticulo
                row[1] != null ? (String) row[1] : null,   // descripcion
                row[2] != null ? (String) row[2] : null,   // familia
                row[3] != null ? (String) row[3] : null,   // subFamilia
                row[4] != null ? (String) row[4] : null,   // unidad
                row[5] != null ? (BigDecimal) row[5] : BigDecimal.ZERO, // costo
                row[6] != null ? (Integer) row[6] : null,  // noPais
                row[7] != null ? (String) row[7] : null,   // nombrePais
                row[8] != null ? (Integer) row[8] : null,  // noProvincia
                row[9] != null ? (String) row[9] : null,   // nombreProvincia
                row[10] != null ? (Integer) row[10] : null, // noDistrito
                row[11] != null ? (String) row[11] : null,  // nombreDistrito
                row[12] != null ? (Integer) row[12] : null, // noCorregimiento
                row[13] != null ? (String) row[13] : null,  // nombreCorregimiento
                row[14] != null ? (Integer) row[14] : null, // noGrupo
                row[15] != null ? (String) row[15] : "S",   // tipo
                row[16] != null ? (BigDecimal) row[16] : BigDecimal.ZERO, // precioBase
                row[17] != null ? (BigDecimal) row[17] : BigDecimal.ZERO, // porcentaje
                row[18] != null ? (BigDecimal) row[18] : BigDecimal.ZERO, // precioVenta
                row[19] != null ? (String) row[19] : null,  // usuarioMod
                // Convertir Timestamp a LocalDateTime
                row[20] != null ? ((java.sql.Timestamp) row[20]).toLocalDateTime() : null, // fechaMod
                row[21] != null ? (String) row[21] : null,  // estadoListaPrecio
                row[22] != null ? (BigDecimal) row[22] : BigDecimal.ZERO, // precioBaseLista
                row[23] != null ? (BigDecimal) row[23] : BigDecimal.ZERO, // precioVentaLista
                row[24] != null ? (String) row[24] : null,  // usuarioListaMod
                row[25] != null ? (String) row[25] : "I"   // indicadorAct
            );
        } catch (Exception e) {
            throw new RuntimeException("Error al mapear resultado de la consulta: " + e.getMessage(), e);
        }
    }

    @GetMapping("/debug")
    @Operation(summary = "Debug: Ver tipos de datos y primeros registros")
    public ResponseEntity<?> debugListaPrecios(
            @RequestParam(required = false, defaultValue = "1") Integer noCia) {
        
        try {
            // Buscar el grupo de mercado de portal para la compañía
            List<FacwebGrupoMercado> gruposPortal = grupoMercadoRepository.findByIndPortalAndNoCia("S", noCia);
            
            if (gruposPortal.isEmpty()) {
                return ResponseEntity.badRequest()
                    .body("No se encontró grupo de mercado de portal para la compañía " + noCia);
            }
            
            Integer noGrupo = gruposPortal.get(0).getNoGrupo();
            
            // Obtener solo los primeros 3 resultados para debug
            List<Object[]> resultados = servicioRepository.findTodosServiciosConPrecioPortal(noCia, noGrupo);
            
            if (resultados.isEmpty()) {
                return ResponseEntity.ok("No hay resultados para la consulta");
            }
            
            // Analizar el primer resultado
            Object[] primerResultado = resultados.get(0);
            StringBuilder debug = new StringBuilder();
            debug.append("Total resultados: ").append(resultados.size()).append("\n");
            debug.append("Número de columnas: ").append(primerResultado.length).append("\n");
            debug.append("Tipos de datos por columna:\n");
            
            for (int i = 0; i < primerResultado.length; i++) {
                Object valor = primerResultado[i];
                debug.append("Columna ").append(i).append(": ");
                if (valor == null) {
                    debug.append("null");
                } else {
                    debug.append(valor.getClass().getSimpleName()).append(" = ").append(valor);
                }
                debug.append("\n");
            }
            
            return ResponseEntity.ok(debug.toString());
            
        } catch (Exception e) {
            return ResponseEntity.status(500)
                .body("Error en debug: " + e.getMessage() + "\nCausa: " + e.getCause());
        }
    }
}
