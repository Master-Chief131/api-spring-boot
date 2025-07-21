package com.example.demo.controller;

import com.example.demo.entity.CentroCosto;
import com.example.demo.entity.CentroCostoId;
import com.example.demo.repository.CentroCostoRepository;
import com.example.demo.dto.CentroCostoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/centros-costo")
@CrossOrigin(origins = "*")
@Tag(name = "Catálogo de Centros de Costo", description = "API para gestión del catálogo de centros de costo")
public class CentroCostoController {
    
    private final CentroCostoRepository centroCostoRepository;

    public CentroCostoController(CentroCostoRepository centroCostoRepository) {
        this.centroCostoRepository = centroCostoRepository;
    }

    @GetMapping
    @Operation(
        summary = "Obtener catálogo de centros de costo operativos",
        description = "Obtiene una lista paginada del catálogo de centros de costo que están ACTIVOS y PERMITEN MOVIMIENTO (operativos). " +
                     "Este endpoint está optimizado para mostrar solo los centros de costo que se pueden usar en operaciones contables."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Catálogo de centros de costo operativos obtenido exitosamente",
                    content = @Content(schema = @Schema(implementation = Page.class))),
        @ApiResponse(responseCode = "400", description = "Parámetros de consulta inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public Page<CentroCosto> getCatalogoCentrosCosto(
            @Parameter(description = "Número de compañía", example = "1", required = false)
            @RequestParam(required = false) Integer noCia,
            @Parameter(description = "Buscar en la descripción del centro de costo", example = "ADMINISTRACION", required = false)
            @RequestParam(required = false) String descripcion,
            @Parameter(description = "Tipo de gasto", example = "01", required = false)
            @RequestParam(required = false) String tipoGasto,
            @Parameter(description = "Relacionado a cuentas (A=Administración, C=Costo, G=Gasto)", example = "A", required = false)
            @RequestParam(required = false) String relacionadoACuentas,
            @Parameter(description = "Nivel jerárquico del centro de costo", example = "1", required = false)
            @RequestParam(required = false) Integer nivel,
            @Parameter(description = "Centro de costo padre", example = "001", required = false)
            @RequestParam(required = false) String padre,
            @Parameter(description = "Código de centro que inicie con", example = "001", required = false)
            @RequestParam(required = false) String codigoInicio,
            Pageable pageable) {
        
        // Construir especificación dinámica con condiciones OBLIGATORIAS
        Specification<CentroCosto> spec = Specification.where(null);
        
        // CONDICIONES OBLIGATORIAS
        spec = spec.and((root, query, cb) -> cb.equal(root.get("activo"), "S"));
        spec = spec.and((root, query, cb) -> cb.equal(root.get("indMov"), "S"));
        
        // CONDICIÓN OBLIGATORIA
        Integer companiaFiltro = noCia != null ? noCia : 1;
        spec = spec.and((root, query, cb) -> cb.equal(root.get("noCia"), companiaFiltro));
        
        // Filtros opcionales
        if (descripcion != null && !descripcion.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> 
                cb.or(
                    cb.like(cb.lower(root.get("centro")), "%" + descripcion.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("descripCc")), "%" + descripcion.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("encargadoCc")), "%" + descripcion.toLowerCase() + "%")
                )
            );
        }
        
        if (tipoGasto != null && !tipoGasto.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("tipoGasto"), tipoGasto));
        }
        
        if (relacionadoACuentas != null && !relacionadoACuentas.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("relacionadoACuentas"), relacionadoACuentas));
        }
        
        if (nivel != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("nivel"), nivel));
        }
        
        if (padre != null && !padre.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("padre"), padre));
        }
        
        if (codigoInicio != null && !codigoInicio.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> 
                cb.like(root.get("centro"), codigoInicio + "%"));
        }
        
        return centroCostoRepository.findAll(spec, pageable);
    }

    @GetMapping("/{noCia}/{centro}")
    @Operation(
        summary = "Obtener centro de costo específico",
        description = "Obtiene un centro de costo específico mediante su ID compuesto (número de compañía y código de centro)"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Centro de costo encontrado exitosamente",
                    content = @Content(schema = @Schema(implementation = CentroCosto.class))),
        @ApiResponse(responseCode = "404", description = "Centro de costo no encontrado"),
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
    public ResponseEntity<CentroCosto> getCentroCostoById(
            @Parameter(description = "Número de compañía", example = "1", required = true)
            @PathVariable Integer noCia,
            @Parameter(description = "Código del centro de costo", example = "001", required = true)
            @PathVariable String centro) {
        
        CentroCostoId id = new CentroCostoId(noCia, centro);
        Optional<CentroCosto> centroCostoOpt = centroCostoRepository.findById(id);
        
        if (centroCostoOpt.isPresent()) {
            return ResponseEntity.ok(centroCostoOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // @GetMapping("/dto")
    // @Operation(
    //     summary = "Obtener catálogo de centros de costo operativos (DTO)",
    //     description = "Obtiene una lista simplificada del catálogo de centros de costo que están ACTIVOS y PERMITEN MOVIMIENTO. " +
    //                  "Utiliza DTOs optimizados para una mejor presentación en interfaces de usuario."
    // )
    // @ApiResponses(value = {
    //     @ApiResponse(responseCode = "200", description = "Catálogo de centros de costo operativos DTO obtenido exitosamente"),
    //     @ApiResponse(responseCode = "400", description = "Parámetros de consulta inválidos"),
    //     @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    // })
    // public List<CentroCostoDTO> getCatalogoCentrosCostoDTO(
    //         @Parameter(description = "Número de compañía", example = "1", required = false)
    //         @RequestParam(required = false) Integer noCia,
    //         @Parameter(description = "Buscar en la descripción del centro de costo", example = "ADMINISTRACION", required = false)
    //         @RequestParam(required = false) String descripcion,
    //         @Parameter(description = "Tipo de gasto", example = "01", required = false)
    //         @RequestParam(required = false) String tipoGasto,
    //         @Parameter(description = "Relacionado a cuentas (A=Administración, C=Costo, G=Gasto)", example = "A", required = false)
    //         @RequestParam(required = false) String relacionadoACuentas) {
        
    //     // Construir especificación dinámica con condiciones OBLIGATORIAS
    //     Specification<CentroCosto> spec = Specification.where(null);
        
    //     // CONDICIONES OBLIGATORIAS: Solo centros activos que permiten movimiento
    //     spec = spec.and((root, query, cb) -> cb.equal(root.get("activo"), "S"));
    //     spec = spec.and((root, query, cb) -> cb.equal(root.get("indMov"), "S"));
        
    //     // CONDICIÓN OBLIGATORIA: Filtrar por compañía (por defecto compañía 1)
    //     Integer companiaFiltro = noCia != null ? noCia : 1;
    //     spec = spec.and((root, query, cb) -> cb.equal(root.get("noCia"), companiaFiltro));
        
    //     if (descripcion != null && !descripcion.trim().isEmpty()) {
    //         spec = spec.and((root, query, cb) -> 
    //             cb.or(
    //                 cb.like(cb.lower(root.get("centro")), "%" + descripcion.toLowerCase() + "%"),
    //                 cb.like(cb.lower(root.get("descripCc")), "%" + descripcion.toLowerCase() + "%"),
    //                 cb.like(cb.lower(root.get("encargadoCc")), "%" + descripcion.toLowerCase() + "%")
    //             )
    //         );
    //     }
        
    //     if (tipoGasto != null && !tipoGasto.trim().isEmpty()) {
    //         spec = spec.and((root, query, cb) -> cb.equal(root.get("tipoGasto"), tipoGasto));
    //     }
        
    //     if (relacionadoACuentas != null && !relacionadoACuentas.trim().isEmpty()) {
    //         spec = spec.and((root, query, cb) -> cb.equal(root.get("relacionadoACuentas"), relacionadoACuentas));
    //     }
        
    //     // Obtener entidades y convertir a DTOs
    //     List<CentroCosto> centrosCosto = centroCostoRepository.findAll(spec);
    //     return centrosCosto.stream()
    //                       .map(CentroCostoDTO::new)
    //                       .collect(Collectors.toList());
    // }

    @GetMapping("/dto-paginado")
    @Operation(
        summary = "Obtener catálogo de centros de costo operativos (DTO) con paginación",
        description = "Obtiene una lista paginada y simplificada del catálogo de centros de costo que están ACTIVOS y PERMITEN MOVIMIENTO. " +
                     "Utiliza DTOs optimizados para una mejor presentación en interfaces de usuario con soporte de paginación. " +
                     "Por defecto filtra por compañía 1, pero se puede especificar otra compañía con el parámetro 'noCia'."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Catálogo de centros de costo operativos DTO paginado obtenido exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros de consulta inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public Page<CentroCostoDTO> getCatalogoCentrosCostoDTOPaginado(
            @Parameter(description = "Número de compañía", example = "1", required = false)
            @RequestParam(required = false) Integer noCia,
            @Parameter(description = "Buscar en la descripción del centro de costo", example = "ADMINISTRACION", required = false)
            @RequestParam(required = false) String descripcion,
            @Parameter(description = "Tipo de gasto", example = "01", required = false)
            @RequestParam(required = false) String tipoGasto,
            @Parameter(description = "Relacionado a cuentas (A=Administración, C=Costo, G=Gasto)", example = "A", required = false)
            @RequestParam(required = false) String relacionadoACuentas,
            @Parameter(description = "Información de paginación y ordenamiento")
            Pageable pageable) {
        
        // Construir especificación dinámica con condiciones OBLIGATORIAS
        Specification<CentroCosto> spec = Specification.where(null);
        
        // CONDICIONES OBLIGATORIAS: Solo centros activos que permiten movimiento
        spec = spec.and((root, query, cb) -> cb.equal(root.get("activo"), "S"));
        spec = spec.and((root, query, cb) -> cb.equal(root.get("indMov"), "S"));
        
        // CONDICIÓN OBLIGATORIA: Filtrar por compañía (por defecto compañía 1)
        Integer companiaFiltro = noCia != null ? noCia : 1;
        spec = spec.and((root, query, cb) -> cb.equal(root.get("noCia"), companiaFiltro));
        
        if (descripcion != null && !descripcion.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> 
                cb.or(
                    cb.like(cb.lower(root.get("centro")), "%" + descripcion.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("descripCc")), "%" + descripcion.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("encargadoCc")), "%" + descripcion.toLowerCase() + "%")
                )
            );
        }
        
        if (tipoGasto != null && !tipoGasto.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("tipoGasto"), tipoGasto));
        }
        
        if (relacionadoACuentas != null && !relacionadoACuentas.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("relacionadoACuentas"), relacionadoACuentas));
        }
        
        // Obtener página de entidades y convertir a DTOs
        Page<CentroCosto> centrosCostoPage = centroCostoRepository.findAll(spec, pageable);
        return centrosCostoPage.map(CentroCostoDTO::new);
    }

    @GetMapping("/buscar")
    @Operation(
        summary = "Buscar centros de costo por criterios específicos",
        description = "Busca centros de costo utilizando diferentes criterios como descripción, código, encargado, etc."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros de búsqueda inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Object> buscarCentrosCosto(
            @Parameter(description = "Número de compañía", example = "1", required = true)
            @RequestParam Integer noCia,
            @Parameter(description = "Término de búsqueda (busca en código, descripción y encargado)", example = "ADMIN", required = true)
            @RequestParam String termino,
            @Parameter(description = "Filtrar solo centros activos", example = "true", required = false)
            @RequestParam(defaultValue = "true") Boolean soloActivos) {
        
        try {
            // Construir especificación de búsqueda
            Specification<CentroCosto> spec = Specification.where(null);
            
            // Filtrar por compañía
            spec = spec.and((root, query, cb) -> cb.equal(root.get("noCia"), noCia));
            
            // Filtrar por activos si se especifica
            if (soloActivos) {
                spec = spec.and((root, query, cb) -> cb.equal(root.get("activo"), "S"));
            }
            
            // Buscar en código de centro, descripción o encargado
            spec = spec.and((root, query, cb) -> 
                cb.or(
                    cb.like(cb.lower(root.get("centro")), "%" + termino.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("descripCc")), "%" + termino.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("encargadoCc")), "%" + termino.toLowerCase() + "%")
                )
            );
            
            List<CentroCosto> centrosCosto = centroCostoRepository.findAll(spec);
            
            if (!centrosCosto.isEmpty()) {
                return ResponseEntity.ok(centrosCosto);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("mensaje", "No se encontraron centros de costo con el término: " + termino);
                response.put("termino_buscado", termino);
                response.put("total_encontrados", 0);
                return ResponseEntity.ok(response);
            }
            
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error en la búsqueda: " + e.getMessage());
            errorResponse.put("termino_buscado", termino);
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }

    // @GetMapping("/estadisticas")
    // @Operation(
    //     summary = "Obtener estadísticas del catálogo de centros de costo",
    //     description = "Obtiene estadísticas generales del catálogo de centros de costo"
    // )
    // @ApiResponses(value = {
    //     @ApiResponse(responseCode = "200", description = "Estadísticas obtenidas exitosamente"),
    //     @ApiResponse(responseCode = "400", description = "Parámetros inválidos"),
    //     @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    // })
    // public Map<String, Object> getEstadisticasCatalogo(
    //         @Parameter(description = "Número de compañía", example = "1", required = true)
    //         @RequestParam Integer noCia) {
        
    //     Map<String, Object> estadisticas = new HashMap<>();
        
    //     try {
    //         // Total de centros de costo
    //         long totalCentros = centroCostoRepository.count();
    //         estadisticas.put("total_centros", totalCentros);
            
    //         // Centros activos
    //         List<CentroCosto> centrosActivos = centroCostoRepository.findByNoCiaAndActivo(noCia, "S");
    //         estadisticas.put("centros_activos", centrosActivos.size());
            
    //         // Centros con movimiento
    //         List<CentroCosto> centrosConMovimiento = centroCostoRepository.findByNoCiaAndIndMov(noCia, "S");
    //         estadisticas.put("centros_con_movimiento", centrosConMovimiento.size());
            
    //         // Centros por tipo de relación
    //         List<CentroCosto> centrosAdmin = centroCostoRepository.findByNoCiaAndRelacionadoACuentas(noCia, "A");
    //         List<CentroCosto> centrosCosto = centroCostoRepository.findByNoCiaAndRelacionadoACuentas(noCia, "C");
    //         List<CentroCosto> centrosGasto = centroCostoRepository.findByNoCiaAndRelacionadoACuentas(noCia, "G");
            
    //         estadisticas.put("centros_administracion", centrosAdmin.size());
    //         estadisticas.put("centros_costo", centrosCosto.size());
    //         estadisticas.put("centros_gasto", centrosGasto.size());
            
    //         // Información adicional
    //         estadisticas.put("compania", noCia);
    //         estadisticas.put("fecha_consulta", java.time.LocalDateTime.now());
            
    //     } catch (Exception e) {
    //         estadisticas.put("error", "Error al obtener estadísticas: " + e.getMessage());
    //     }
        
    //     return estadisticas;
    // }

    @GetMapping("/completo")
    @Operation(
        summary = "Obtener catálogo completo de centros de costo (Administrativo)",
        description = "Obtiene el catálogo COMPLETO de centros de costo incluyendo activos, inactivos, con y sin movimiento. " +
                     "Este endpoint está destinado para uso administrativo y configuración del sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Catálogo completo obtenido exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros de consulta inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public Page<CentroCosto> getCatalogoCompletoAdmin(
            @Parameter(description = "Número de compañía", example = "1", required = false)
            @RequestParam(required = false) Integer noCia,
            @Parameter(description = "Filtrar por estado (S=Activo, N=Inactivo)", example = "S", required = false)
            @RequestParam(required = false) String activo,
            @Parameter(description = "Filtrar por movimiento (S=Permite, N=No permite)", example = "S", required = false)
            @RequestParam(required = false) String indMov,
            @Parameter(description = "Tipo de gasto", example = "01", required = false)
            @RequestParam(required = false) String tipoGasto,
            @Parameter(description = "Relacionado a cuentas (A=Administración, C=Costo, G=Gasto)", example = "A", required = false)
            @RequestParam(required = false) String relacionadoACuentas,
            @Parameter(description = "Buscar en la descripción del centro de costo", example = "ADMIN", required = false)
            @RequestParam(required = false) String descripcion,
            @Parameter(description = "Nivel jerárquico del centro de costo", example = "1", required = false)
            @RequestParam(required = false) Integer nivel,
            Pageable pageable) {
        
        // Construir especificación dinámica SIN restricciones obligatorias
        Specification<CentroCosto> spec = Specification.where(null);
        
        // CONDICIÓN OBLIGATORIA: Filtrar por compañía (por defecto compañía 1)
        Integer companiaFiltro = noCia != null ? noCia : 1;
        spec = spec.and((root, query, cb) -> cb.equal(root.get("noCia"), companiaFiltro));
        
        if (activo != null && !activo.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("activo"), activo));
        }
        
        if (indMov != null && !indMov.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("indMov"), indMov));
        }
        
        if (tipoGasto != null && !tipoGasto.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("tipoGasto"), tipoGasto));
        }
        
        if (relacionadoACuentas != null && !relacionadoACuentas.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("relacionadoACuentas"), relacionadoACuentas));
        }
        
        if (descripcion != null && !descripcion.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> 
                cb.or(
                    cb.like(cb.lower(root.get("centro")), "%" + descripcion.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("descripCc")), "%" + descripcion.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("encargadoCc")), "%" + descripcion.toLowerCase() + "%")
                )
            );
        }
        
        if (nivel != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("nivel"), nivel));
        }
        
        return centroCostoRepository.findAll(spec, pageable);
    }
}
