package com.example.demo.controller;

import com.example.demo.entity.Proveedor;
import com.example.demo.entity.ProveedorId;
import com.example.demo.repository.ProveedorRepository;
import com.example.demo.dto.ProveedorDTO;
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
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "*")
@Tag(name = "Catálogo de Proveedores", description = "API para gestión del catálogo de proveedores")
public class ProveedorController {
    
    private final ProveedorRepository proveedorRepository;

    public ProveedorController(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @GetMapping
    @Operation(
        summary = "Obtener catálogo de proveedores activos",
        description = "Obtiene una lista paginada del catálogo de proveedores que están ACTIVOS. " +
                     "Este endpoint está optimizado para mostrar solo los proveedores operativos."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Catálogo de proveedores activos obtenido exitosamente",
                    content = @Content(schema = @Schema(implementation = Page.class))),
        @ApiResponse(responseCode = "400", description = "Parámetros de consulta inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public Page<Proveedor> getCatalogoProveedores(
            @Parameter(description = "Número de compañía", example = "1", required = false)
            @RequestParam(required = false) Integer noCia,
            @Parameter(description = "Buscar en nombre, nombre largo o encargado", example = "ACME", required = false)
            @RequestParam(required = false) String nombre,
            @Parameter(description = "Tipo de proveedor", example = "1", required = false)
            @RequestParam(required = false) Integer tipo,
            @Parameter(description = "Clase de proveedor", example = "1", required = false)
            @RequestParam(required = false) Integer noClase,
            @Parameter(description = "Nacionalidad (S=Nacional, N=Extranjero)", example = "S", required = false)
            @RequestParam(required = false) String indNacional,
            @Parameter(description = "Cédula o RUC", example = "8-123-456", required = false)
            @RequestParam(required = false) String cedulaRuc,
            @Parameter(description = "Email del proveedor", example = "proveedor@email.com", required = false)
            @RequestParam(required = false) String email,
            @Parameter(description = "País", example = "1", required = false)
            @RequestParam(required = false) Integer pais,
            Pageable pageable) {
        
        // Construir especificación dinámica con condiciones OBLIGATORIAS
        Specification<Proveedor> spec = Specification.where(null);
        
        // CONDICIÓN OBLIGATORIA: Solo proveedores activos
        spec = spec.and((root, query, cb) -> cb.equal(root.get("activo"), "S"));
        
        // CONDICIÓN OBLIGATORIA: Filtrar por compañía (por defecto compañía 1)
        Integer companiaFiltro = noCia != null ? noCia : 1;
        spec = spec.and((root, query, cb) -> cb.equal(root.get("noCia"), companiaFiltro));
        
        // Filtros opcionales
        if (nombre != null && !nombre.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> 
                cb.or(
                    cb.like(cb.lower(root.get("nombre")), "%" + nombre.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("nombreLargo")), "%" + nombre.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("encargado")), "%" + nombre.toLowerCase() + "%")
                )
            );
        }
        
        if (tipo != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("tipo"), tipo));
        }
        
        if (noClase != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("noClase"), noClase));
        }
        
        if (indNacional != null && !indNacional.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("indNacional"), indNacional));
        }
        
        if (cedulaRuc != null && !cedulaRuc.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> 
                cb.like(cb.lower(root.get("cedulaRuc")), "%" + cedulaRuc.toLowerCase() + "%"));
        }
        
        if (email != null && !email.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> 
                cb.or(
                    cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("email2")), "%" + email.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("emailContactoCompra")), "%" + email.toLowerCase() + "%")
                )
            );
        }
        
        if (pais != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("pais"), pais));
        }
        
        return proveedorRepository.findAll(spec, pageable);
    }

    @GetMapping("/{noCia}/{noProveedor}/{noClase}")
    @Operation(
        summary = "Obtener proveedor específico",
        description = "Obtiene un proveedor específico mediante su ID compuesto (compañía, número de proveedor y clase)"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proveedor encontrado exitosamente",
                    content = @Content(schema = @Schema(implementation = Proveedor.class))),
        @ApiResponse(responseCode = "404", description = "Proveedor no encontrado"),
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
    public ResponseEntity<Proveedor> getProveedorById(
            @Parameter(description = "Número de compañía", example = "1", required = true)
            @PathVariable Integer noCia,
            @Parameter(description = "Número del proveedor", example = "123", required = true)
            @PathVariable Integer noProveedor,
            @Parameter(description = "Clase del proveedor", example = "1", required = true)
            @PathVariable Integer noClase) {
        
        ProveedorId id = new ProveedorId(noCia, noProveedor, noClase);
        Optional<Proveedor> proveedorOpt = proveedorRepository.findById(id);
        
        if (proveedorOpt.isPresent()) {
            return ResponseEntity.ok(proveedorOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/dto")
    @Operation(
        summary = "Obtener catálogo de proveedores activos (DTO)",
        description = "Obtiene una lista simplificada del catálogo de proveedores que están ACTIVOS. " +
                     "Utiliza DTOs optimizados para una mejor presentación en interfaces de usuario."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Catálogo de proveedores activos DTO obtenido exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros de consulta inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public List<ProveedorDTO> getCatalogoProveedoresDTO(
            @Parameter(description = "Número de compañía", example = "1", required = false)
            @RequestParam(required = false) Integer noCia,
            @Parameter(description = "Buscar en nombre, nombre largo o encargado", example = "ACME", required = false)
            @RequestParam(required = false) String nombre,
            @Parameter(description = "Tipo de proveedor", example = "1", required = false)
            @RequestParam(required = false) Integer tipo,
            @Parameter(description = "Clase de proveedor", example = "1", required = false)
            @RequestParam(required = false) Integer noClase,
            @Parameter(description = "Nacionalidad (S=Nacional, N=Extranjero)", example = "S", required = false)
            @RequestParam(required = false) String indNacional) {
        
        // Construir especificación dinámica con condiciones OBLIGATORIAS
        Specification<Proveedor> spec = Specification.where(null);
        
        // CONDICIÓN OBLIGATORIA: Solo proveedores activos
        spec = spec.and((root, query, cb) -> cb.equal(root.get("activo"), "S"));
        
        // CONDICIÓN OBLIGATORIA: Filtrar por compañía (por defecto compañía 1)
        Integer companiaFiltro = noCia != null ? noCia : 1;
        spec = spec.and((root, query, cb) -> cb.equal(root.get("noCia"), companiaFiltro));
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> 
                cb.or(
                    cb.like(cb.lower(root.get("nombre")), "%" + nombre.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("nombreLargo")), "%" + nombre.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("encargado")), "%" + nombre.toLowerCase() + "%")
                )
            );
        }
        
        if (tipo != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("tipo"), tipo));
        }
        
        if (noClase != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("noClase"), noClase));
        }
        
        if (indNacional != null && !indNacional.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("indNacional"), indNacional));
        }
        
        // Obtener entidades y convertir a DTOs
        List<Proveedor> proveedores = proveedorRepository.findAll(spec);
        return proveedores.stream()
                         .map(ProveedorDTO::new)
                         .collect(Collectors.toList());
    }

    @GetMapping("/dto-paginado")
    @Operation(
        summary = "Obtener catálogo de proveedores activos (DTO) con paginación",
        description = "Obtiene una lista paginada y simplificada del catálogo de proveedores que están ACTIVOS. " +
                     "Utiliza DTOs optimizados para una mejor presentación en interfaces de usuario con soporte de paginación. " +
                     "Por defecto filtra por compañía 1, pero se puede especificar otra compañía con el parámetro 'noCia'."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Catálogo de proveedores activos DTO paginado obtenido exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros de consulta inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public Page<ProveedorDTO> getCatalogoProveedoresDTOPaginado(
            @Parameter(description = "Número de compañía", example = "1", required = false)
            @RequestParam(required = false) Integer noCia,
            @Parameter(description = "Buscar en nombre, nombre largo o encargado", example = "ACME", required = false)
            @RequestParam(required = false) String nombre,
            @Parameter(description = "Tipo de proveedor", example = "1", required = false)
            @RequestParam(required = false) Integer tipo,
            @Parameter(description = "Clase de proveedor", example = "1", required = false)
            @RequestParam(required = false) Integer noClase,
            @Parameter(description = "Nacionalidad (S=Nacional, N=Extranjero)", example = "S", required = false)
            @RequestParam(required = false) String indNacional,
            @Parameter(description = "Información de paginación y ordenamiento")
            Pageable pageable) {
        
        // Construir especificación dinámica con condiciones OBLIGATORIAS
        Specification<Proveedor> spec = Specification.where(null);
        
        // CONDICIÓN OBLIGATORIA: Solo proveedores activos
        spec = spec.and((root, query, cb) -> cb.equal(root.get("activo"), "S"));
        
        // CONDICIÓN OBLIGATORIA: Filtrar por compañía (por defecto compañía 1)
        Integer companiaFiltro = noCia != null ? noCia : 1;
        spec = spec.and((root, query, cb) -> cb.equal(root.get("noCia"), companiaFiltro));
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> 
                cb.or(
                    cb.like(cb.lower(root.get("nombre")), "%" + nombre.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("nombreLargo")), "%" + nombre.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("encargado")), "%" + nombre.toLowerCase() + "%")
                )
            );
        }
        
        if (tipo != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("tipo"), tipo));
        }
        
        if (noClase != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("noClase"), noClase));
        }
        
        if (indNacional != null && !indNacional.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("indNacional"), indNacional));
        }
        
        // Obtener página de entidades y convertir a DTOs
        Page<Proveedor> proveedoresPage = proveedorRepository.findAll(spec, pageable);
        return proveedoresPage.map(ProveedorDTO::new);
    }

    @GetMapping("/buscar")
    @Operation(
        summary = "Buscar proveedores por criterios específicos",
        description = "Busca proveedores utilizando diferentes criterios como nombre, RUC, email, etc."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros de búsqueda inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Object> buscarProveedores(
            @Parameter(description = "Número de compañía", example = "1", required = true)
            @RequestParam Integer noCia,
            @Parameter(description = "Término de búsqueda (busca en nombre, RUC, email, encargado)", example = "ACME", required = true)
            @RequestParam String termino,
            @Parameter(description = "Filtrar solo proveedores activos", example = "true", required = false)
            @RequestParam(defaultValue = "true") Boolean soloActivos) {
        
        try {
            // Construir especificación de búsqueda
            Specification<Proveedor> spec = Specification.where(null);
            
            // Filtrar por compañía
            spec = spec.and((root, query, cb) -> cb.equal(root.get("noCia"), noCia));
            
            // Filtrar por activos si se especifica
            if (soloActivos) {
                spec = spec.and((root, query, cb) -> cb.equal(root.get("activo"), "S"));
            }
            
            // Buscar en múltiples campos
            spec = spec.and((root, query, cb) -> 
                cb.or(
                    cb.like(cb.lower(root.get("nombre")), "%" + termino.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("nombreLargo")), "%" + termino.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("encargado")), "%" + termino.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("cedulaRuc")), "%" + termino.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("email")), "%" + termino.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("email2")), "%" + termino.toLowerCase() + "%")
                )
            );
            
            List<Proveedor> proveedores = proveedorRepository.findAll(spec);
            
            if (!proveedores.isEmpty()) {
                return ResponseEntity.ok(proveedores);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("mensaje", "No se encontraron proveedores con el término: " + termino);
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

    @GetMapping("/estadisticas")
    @Operation(
        summary = "Obtener estadísticas del catálogo de proveedores",
        description = "Obtiene estadísticas generales del catálogo de proveedores"
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
            // Total de proveedores
            long totalProveedores = proveedorRepository.count();
            estadisticas.put("total_proveedores", totalProveedores);
            
            // Proveedores activos
            List<Proveedor> proveedoresActivos = proveedorRepository.findByNoCiaAndActivo(noCia, "S");
            estadisticas.put("proveedores_activos", proveedoresActivos.size());
            
            // Proveedores nacionales vs extranjeros
            List<Proveedor> proveedoresNacionales = proveedorRepository.findByNoCiaAndIndNacional(noCia, "N");
            List<Proveedor> proveedoresExtranjeros = proveedorRepository.findByNoCiaAndIndNacional(noCia, "E");
            
            estadisticas.put("proveedores_nacionales", proveedoresNacionales.size());
            estadisticas.put("proveedores_extranjeros", proveedoresExtranjeros.size());
            
            // Proveedores que aceptan retenciones
            List<Proveedor> proveedoresConRetenciones = proveedorRepository.findByNoCiaAndIndAceptaRetenciones(noCia, "S");
            estadisticas.put("proveedores_acepta_retenciones", proveedoresConRetenciones.size());
            
            // Proveedores de compañía vs externos
            List<Proveedor> proveedoresCia = proveedorRepository.findByNoCiaAndEsProveCia(noCia, "S");
            List<Proveedor> proveedoresExternos = proveedorRepository.findByNoCiaAndEsProveCia(noCia, "N");
            
            estadisticas.put("proveedores_compania", proveedoresCia.size());
            estadisticas.put("proveedores_externos", proveedoresExternos.size());
            
            // Proveedores con compras recientes
            List<Proveedor> proveedoresComprasRecientes = proveedorRepository.findProveedoresComprasRecientes(noCia);
            estadisticas.put("proveedores_compras_recientes", proveedoresComprasRecientes.size());
            
            // Información adicional
            estadisticas.put("compania", noCia);
            estadisticas.put("fecha_consulta", java.time.LocalDateTime.now());
            
        } catch (Exception e) {
            estadisticas.put("error", "Error al obtener estadísticas: " + e.getMessage());
        }
        
        return estadisticas;
    }

    @GetMapping("/completo")
    @Operation(
        summary = "Obtener catálogo completo de proveedores (Administrativo)",
        description = "Obtiene el catálogo COMPLETO de proveedores incluyendo activos e inactivos. " +
                     "Este endpoint está destinado para uso administrativo y configuración del sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Catálogo completo obtenido exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros de consulta inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public Page<Proveedor> getCatalogoCompletoAdmin(
            @Parameter(description = "Número de compañía", example = "1", required = false)
            @RequestParam(required = false) Integer noCia,
            @Parameter(description = "Filtrar por estado (S=Activo, N=Inactivo)", example = "S", required = false)
            @RequestParam(required = false) String activo,
            @Parameter(description = "Tipo de proveedor", example = "1", required = false)
            @RequestParam(required = false) Integer tipo,
            @Parameter(description = "Clase de proveedor", example = "1", required = false)
            @RequestParam(required = false) Integer noClase,
            @Parameter(description = "Nacionalidad (S=Nacional, N=Extranjero)", example = "S", required = false)
            @RequestParam(required = false) String indNacional,
            @Parameter(description = "Buscar en nombre, nombre largo o encargado", example = "ACME", required = false)
            @RequestParam(required = false) String nombre,
            Pageable pageable) {
        
        // Construir especificación dinámica SIN restricciones obligatorias
        Specification<Proveedor> spec = Specification.where(null);
        
        // CONDICIÓN OBLIGATORIA: Filtrar por compañía (por defecto compañía 1)
        Integer companiaFiltro = noCia != null ? noCia : 1;
        spec = spec.and((root, query, cb) -> cb.equal(root.get("noCia"), companiaFiltro));
        
        if (activo != null && !activo.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("activo"), activo));
        }
        
        if (tipo != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("tipo"), tipo));
        }
        
        if (noClase != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("noClase"), noClase));
        }
        
        if (indNacional != null && !indNacional.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("indNacional"), indNacional));
        }
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> 
                cb.or(
                    cb.like(cb.lower(root.get("nombre")), "%" + nombre.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("nombreLargo")), "%" + nombre.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("encargado")), "%" + nombre.toLowerCase() + "%")
                )
            );
        }
        
        return proveedorRepository.findAll(spec, pageable);
    }
}
