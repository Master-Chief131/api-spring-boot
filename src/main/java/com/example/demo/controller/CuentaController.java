package com.example.demo.controller;

import com.example.demo.entity.Cuenta;
import com.example.demo.entity.CuentaId;
import com.example.demo.repository.CuentaRepository;
import com.example.demo.dto.CuentaDTO;
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
@RequestMapping("/api/cuentas")
@CrossOrigin(origins = "*")
@Tag(name = "Catálogo de Cuentas", description = "API para gestión del catálogo de cuentas contables")
public class CuentaController {
    
    private final CuentaRepository cuentaRepository;

    public CuentaController(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @GetMapping
    @Operation(
        summary = "Obtener catálogo de cuentas operativas",
        description = "Obtiene una lista paginada del catálogo de cuentas que están ACTIVAS y PERMITEN MOVIMIENTO (operativas). " +
                     "Este endpoint está optimizado para mostrar solo las cuentas que se pueden usar en operaciones contables."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Catálogo de cuentas operativas obtenido exitosamente",
                    content = @Content(schema = @Schema(implementation = Page.class))),
        @ApiResponse(responseCode = "400", description = "Parámetros de consulta inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public Page<Cuenta> getCatalogoCuentas(
            @Parameter(description = "Número de compañía", example = "1", required = false)
            @RequestParam(required = false) Integer noCia,
            @Parameter(description = "Tipo de cuenta", example = "1", required = false)
            @RequestParam(required = false) Integer tipo,
            @Parameter(description = "Buscar en la descripción de la cuenta", example = "CAJA", required = false)
            @RequestParam(required = false) String descripcion,
            @Parameter(description = "Nivel jerárquico de la cuenta", example = "1", required = false)
            @RequestParam(required = false) String nivel,
            @Parameter(description = "Cuenta padre", example = "1", required = false)
            @RequestParam(required = false) String padre,
            @Parameter(description = "Naturaleza de la cuenta (D=Débito, C=Crédito)", example = "D", required = false)
            @RequestParam(required = false) String naturaleza,
            @Parameter(description = "Código de cuenta que inicie con", example = "1", required = false)
            @RequestParam(required = false) String codigoInicio,
            Pageable pageable) {
        
        // Construir especificación dinámica con condiciones OBLIGATORIAS
        Specification<Cuenta> spec = Specification.where(null);
        
        // CONDICIONES OBLIGATORIAS: Solo cuentas activas que permiten movimiento
        spec = spec.and((root, query, cb) -> cb.equal(root.get("activo"), "S"));
        spec = spec.and((root, query, cb) -> cb.equal(root.get("indMov"), "S"));
        
        // Filtros opcionales
        if (noCia != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("noCia"), noCia));
        }
        
        if (tipo != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("tipo"), tipo));
        }
        
        if (descripcion != null && !descripcion.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> 
                cb.like(cb.lower(root.get("descripcion")), "%" + descripcion.toLowerCase() + "%"));
        }
        
        if (nivel != null && !nivel.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("nivel"), nivel));
        }
        
        if (padre != null && !padre.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("padre"), padre));
        }
        
        if (naturaleza != null && !naturaleza.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("naturaleza"), naturaleza));
        }
        
        if (codigoInicio != null && !codigoInicio.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> 
                cb.like(root.get("cuenta"), codigoInicio + "%"));
        }
        
        return cuentaRepository.findAll(spec, pageable);
    }

    @GetMapping("/{noCia}/{cuenta}")
    @Operation(
        summary = "Obtener cuenta específica",
        description = "Obtiene una cuenta específica mediante su ID compuesto (número de compañía y código de cuenta)"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuenta encontrada exitosamente",
                    content = @Content(schema = @Schema(implementation = Cuenta.class))),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada"),
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
    public ResponseEntity<Cuenta> getCuentaById(
            @Parameter(description = "Número de compañía", example = "1", required = true)
            @PathVariable Integer noCia,
            @Parameter(description = "Código de la cuenta", example = "1.1.01.001", required = true)
            @PathVariable String cuenta) {
        
        CuentaId id = new CuentaId(noCia, cuenta);
        Optional<Cuenta> cuentaOpt = cuentaRepository.findById(id);
        
        if (cuentaOpt.isPresent()) {
            return ResponseEntity.ok(cuentaOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/plan-cuentas")
    @Operation(
        summary = "Obtener plan de cuentas completo",
        description = "Obtiene el plan de cuentas completo ordenado jerárquicamente por código de cuenta"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Plan de cuentas obtenido exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public List<Cuenta> getPlanCuentas(
            @Parameter(description = "Número de compañía", example = "1", required = true)
            @RequestParam Integer noCia) {
        
        return cuentaRepository.findPlanCuentas(noCia);
    }

    @GetMapping("/buscar")
    @Operation(
        summary = "Buscar cuentas por criterios específicos",
        description = "Busca cuentas utilizando diferentes criterios como descripción, código, tipo, etc."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros de búsqueda inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Object> buscarCuentas(
            @Parameter(description = "Número de compañía", example = "1", required = true)
            @RequestParam Integer noCia,
            @Parameter(description = "Término de búsqueda (busca en código y descripción)", example = "CAJA", required = true)
            @RequestParam String termino,
            @Parameter(description = "Filtrar solo cuentas activas", example = "true", required = false)
            @RequestParam(defaultValue = "true") Boolean soloActivas) {
        
        try {
            // Construir especificación de búsqueda
            Specification<Cuenta> spec = Specification.where(null);
            
            // Filtrar por compañía
            spec = spec.and((root, query, cb) -> cb.equal(root.get("noCia"), noCia));
            
            // Filtrar por activas si se especifica
            if (soloActivas) {
                spec = spec.and((root, query, cb) -> cb.equal(root.get("activo"), "S"));
            }
            
            // Buscar en código de cuenta o descripción
            spec = spec.and((root, query, cb) -> 
                cb.or(
                    cb.like(cb.lower(root.get("cuenta")), "%" + termino.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("descripcion")), "%" + termino.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("descrip1")), "%" + termino.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("descripLarga")), "%" + termino.toLowerCase() + "%")
                )
            );
            
            List<Cuenta> cuentas = cuentaRepository.findAll(spec);
            
            if (!cuentas.isEmpty()) {
                return ResponseEntity.ok(cuentas);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("mensaje", "No se encontraron cuentas con el término: " + termino);
                response.put("termino_buscado", termino);
                response.put("total_encontradas", 0);
                return ResponseEntity.ok(response);
            }
            
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error en la búsqueda: " + e.getMessage());
            errorResponse.put("termino_buscado", termino);
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }

    @GetMapping("/por-tipo")
    @Operation(
        summary = "Obtener cuentas por tipo",
        description = "Obtiene todas las cuentas de un tipo específico"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuentas por tipo obtenidas exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public List<Cuenta> getCuentasPorTipo(
            @Parameter(description = "Número de compañía", example = "1", required = true)
            @RequestParam Integer noCia,
            @Parameter(description = "Tipo de cuenta", example = "1", required = true)
            @RequestParam Integer tipo) {
        
        return cuentaRepository.findByNoCiaAndTipo(noCia, tipo);
    }

    @GetMapping("/cuentas-hijas")
    @Operation(
        summary = "Obtener cuentas hijas",
        description = "Obtiene todas las cuentas hijas de una cuenta padre específica"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuentas hijas obtenidas exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public List<Cuenta> getCuentasHijas(
            @Parameter(description = "Número de compañía", example = "1", required = true)
            @RequestParam Integer noCia,
            @Parameter(description = "Código de la cuenta padre", example = "1.1", required = true)
            @RequestParam String cuentaPadre) {
        
        return cuentaRepository.findByNoCiaAndPadre(noCia, cuentaPadre);
    }

    @GetMapping("/con-movimiento")
    @Operation(
        summary = "Obtener cuentas que permiten movimiento",
        description = "Obtiene todas las cuentas que permiten registrar movimientos contables"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuentas con movimiento obtenidas exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public List<Cuenta> getCuentasConMovimiento(
            @Parameter(description = "Número de compañía", example = "1", required = true)
            @RequestParam Integer noCia) {
        
        return cuentaRepository.findByNoCiaAndIndMov(noCia, "S");
    }

    @GetMapping("/por-naturaleza")
    @Operation(
        summary = "Obtener cuentas por naturaleza",
        description = "Obtiene cuentas filtradas por su naturaleza contable (Débito o Crédito)"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cuentas por naturaleza obtenidas exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public List<Cuenta> getCuentasPorNaturaleza(
            @Parameter(description = "Número de compañía", example = "1", required = true)
            @RequestParam Integer noCia,
            @Parameter(description = "Naturaleza de la cuenta (D=Débito, C=Crédito)", example = "D", required = true)
            @RequestParam String naturaleza) {
        
        return cuentaRepository.findByNoCiaAndNaturaleza(noCia, naturaleza);
    }

    @GetMapping("/estadisticas")
    @Operation(
        summary = "Obtener estadísticas del catálogo",
        description = "Obtiene estadísticas generales del catálogo de cuentas"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estadísticas obtenidas exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public Map<String, Object> getEstadisticasCatalogo(
            @Parameter(description = "Número de compañía", example = "1", required = true)
            @RequestParam Integer noCia) {
        
        Map<String, Object> estadisticas = new HashMap<>();
        
        try {
            // Total de cuentas
            long totalCuentas = cuentaRepository.count();
            estadisticas.put("total_cuentas", totalCuentas);
            
            // Cuentas activas
            List<Cuenta> cuentasActivas = cuentaRepository.findByNoCiaAndActivo(noCia, "S");
            estadisticas.put("cuentas_activas", cuentasActivas.size());
            
            // Cuentas con movimiento
            List<Cuenta> cuentasConMovimiento = cuentaRepository.findByNoCiaAndIndMov(noCia, "S");
            estadisticas.put("cuentas_con_movimiento", cuentasConMovimiento.size());
            
            // Cuentas por naturaleza
            List<Cuenta> cuentasDebito = cuentaRepository.findByNoCiaAndNaturaleza(noCia, "D");
            List<Cuenta> cuentasCredito = cuentaRepository.findByNoCiaAndNaturaleza(noCia, "C");
            
            estadisticas.put("cuentas_debito", cuentasDebito.size());
            estadisticas.put("cuentas_credito", cuentasCredito.size());
            
            // Información adicional
            estadisticas.put("compania", noCia);
            estadisticas.put("fecha_consulta", java.time.LocalDateTime.now());
            
        } catch (Exception e) {
            estadisticas.put("error", "Error al obtener estadísticas: " + e.getMessage());
        }
        
        return estadisticas;
    }

    @GetMapping("/dto")
    @Operation(
        summary = "Obtener catálogo de cuentas operativas (DTO)",
        description = "Obtiene una lista simplificada del catálogo de cuentas que están ACTIVAS y PERMITEN MOVIMIENTO. " +
                     "Utiliza DTOs optimizados para una mejor presentación en interfaces de usuario."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Catálogo de cuentas operativas DTO obtenido exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros de consulta inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public List<CuentaDTO> getCatalogoCuentasDTO(
            @Parameter(description = "Número de compañía", example = "1", required = false)
            @RequestParam(required = false) Integer noCia,
            @Parameter(description = "Buscar en la descripción de la cuenta", example = "CAJA", required = false)
            @RequestParam(required = false) String descripcion,
            @Parameter(description = "Tipo de cuenta", example = "1", required = false)
            @RequestParam(required = false) Integer tipo,
            @Parameter(description = "Naturaleza de la cuenta (D=Débito, C=Crédito)", example = "D", required = false)
            @RequestParam(required = false) String naturaleza) {
        
        // Construir especificación dinámica con condiciones OBLIGATORIAS
        Specification<Cuenta> spec = Specification.where(null);
        
        // CONDICIONES OBLIGATORIAS: Solo cuentas activas que permiten movimiento
        spec = spec.and((root, query, cb) -> cb.equal(root.get("activo"), "S"));
        spec = spec.and((root, query, cb) -> cb.equal(root.get("indMov"), "S"));
        
        if (noCia != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("noCia"), noCia));
        }
        
        if (descripcion != null && !descripcion.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> 
                cb.or(
                    cb.like(cb.lower(root.get("cuenta")), "%" + descripcion.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("descripcion")), "%" + descripcion.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("descrip1")), "%" + descripcion.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("descripLarga")), "%" + descripcion.toLowerCase() + "%")
                )
            );
        }
        
        if (tipo != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("tipo"), tipo));
        }
        
        if (naturaleza != null && !naturaleza.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("naturaleza"), naturaleza));
        }
        
        // Obtener entidades y convertir a DTOs
        List<Cuenta> cuentas = cuentaRepository.findAll(spec);
        return cuentas.stream()
                     .map(CuentaDTO::new)
                     .collect(Collectors.toList());
    }

    @GetMapping("/completo")
    @Operation(
        summary = "Obtener catálogo completo de cuentas (Administrativo)",
        description = "Obtiene el catálogo COMPLETO de cuentas incluyendo activas, inactivas, con y sin movimiento. " +
                     "Este endpoint está destinado para uso administrativo y configuración del sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Catálogo completo obtenido exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros de consulta inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public Page<Cuenta> getCatalogoCompletoAdmin(
            @Parameter(description = "Número de compañía", example = "1", required = false)
            @RequestParam(required = false) Integer noCia,
            @Parameter(description = "Filtrar por estado (S=Activa, N=Inactiva)", example = "S", required = false)
            @RequestParam(required = false) String activo,
            @Parameter(description = "Filtrar por movimiento (S=Permite, N=No permite)", example = "S", required = false)
            @RequestParam(required = false) String indMov,
            @Parameter(description = "Tipo de cuenta", example = "1", required = false)
            @RequestParam(required = false) Integer tipo,
            @Parameter(description = "Buscar en la descripción de la cuenta", example = "CAJA", required = false)
            @RequestParam(required = false) String descripcion,
            @Parameter(description = "Nivel jerárquico de la cuenta", example = "1", required = false)
            @RequestParam(required = false) String nivel,
            @Parameter(description = "Naturaleza de la cuenta (D=Débito, C=Crédito)", example = "D", required = false)
            @RequestParam(required = false) String naturaleza,
            Pageable pageable) {
        
        // Construir especificación dinámica SIN restricciones obligatorias
        Specification<Cuenta> spec = Specification.where(null);
        
        if (noCia != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("noCia"), noCia));
        }
        
        if (activo != null && !activo.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("activo"), activo));
        }
        
        if (indMov != null && !indMov.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("indMov"), indMov));
        }
        
        if (tipo != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("tipo"), tipo));
        }
        
        if (descripcion != null && !descripcion.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> 
                cb.or(
                    cb.like(cb.lower(root.get("cuenta")), "%" + descripcion.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("descripcion")), "%" + descripcion.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("descrip1")), "%" + descripcion.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("descripLarga")), "%" + descripcion.toLowerCase() + "%")
                )
            );
        }
        
        if (nivel != null && !nivel.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("nivel"), nivel));
        }
        
        if (naturaleza != null && !naturaleza.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("naturaleza"), naturaleza));
        }
        
        return cuentaRepository.findAll(spec, pageable);
    }

}
