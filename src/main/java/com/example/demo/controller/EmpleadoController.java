package com.example.demo.controller;

import com.example.demo.entity.Empleado;
import com.example.demo.repository.EmpleadoRepository;
import com.example.demo.dto.EmpleadoDTO;
import com.example.demo.service.EstadoEmpleadoService;
import com.example.demo.service.EstadoCivilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(origins = "*")
@Tag(name = "Catálogo de Empleados", description = "API para gestión del catálogo de empleados/colaboradores")
public class EmpleadoController {
    
    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    @Autowired
    private EstadoEmpleadoService estadoEmpleadoService;
    
    @Autowired
    private EstadoCivilService estadoCivilService;

    @GetMapping
    @Operation(
        summary = "Obtener catálogo de empleados activos",
        description = "Obtiene una lista paginada del catálogo de empleados que están ACTIVOS. " +
                     "Este endpoint está optimizado para mostrar solo los empleados operativos."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Catálogo de empleados activos obtenido exitosamente",
                    content = @Content(schema = @Schema(implementation = Page.class))),
        @ApiResponse(responseCode = "400", description = "Parámetros de consulta inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public Page<Empleado> getCatalogoEmpleados(
            @Parameter(description = "Número de compañía", required = true)
            @RequestParam(defaultValue = "1") Integer noCia,
            
            @Parameter(description = "Código de planilla")
            @RequestParam(required = false) String codPla,
            
            @Parameter(description = "Departamento")
            @RequestParam(required = false) String depto,
            
            @Parameter(description = "Tipo de empleado")
            @RequestParam(required = false) String tipoEmp,
            
            @Parameter(description = "Estado del empleado")
            @RequestParam(required = false) String estado,
            
            @Parameter(description = "Sexo del empleado")
            @RequestParam(required = false) String sexo,
            
            Pageable pageable) {
        
        try {
            if (codPla != null || depto != null || tipoEmp != null || estado != null || sexo != null) {
                return empleadoRepository.findByFiltros(noCia, codPla, depto, tipoEmp, estado, sexo, pageable);
            } else {
                return empleadoRepository.findByNoCiaAndEstadoActivo(noCia, pageable);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el catálogo de empleados: " + e.getMessage(), e);
        }
    }

    // @GetMapping("/dto")
    // @Operation(
    //     summary = "Obtener catálogo de empleados en formato DTO",
    //     description = "Obtiene una lista simplificada de empleados activos usando el DTO para mejor presentación"
    // )
    // @ApiResponses(value = {
    //     @ApiResponse(responseCode = "200", description = "Catálogo de empleados DTO obtenido exitosamente"),
    //     @ApiResponse(responseCode = "400", description = "Parámetros de consulta inválidos"),
    //     @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    // })
    // public List<EmpleadoDTO> getCatalogoEmpleadosDTO(
    //         @Parameter(description = "Número de compañía", required = true)
    //         @RequestParam(defaultValue = "1") Integer noCia,
            
    //         @Parameter(description = "Código de planilla")
    //         @RequestParam(required = false) String codPla,
            
    //         @Parameter(description = "Departamento")
    //         @RequestParam(required = false) String depto,
            
    //         @Parameter(description = "Tipo de empleado")
    //         @RequestParam(required = false) String tipoEmp,
            
    //         @Parameter(description = "Estado del empleado")
    //         @RequestParam(required = false) String estado,
            
    //         @Parameter(description = "Sexo del empleado")
    //         @RequestParam(required = false) String sexo) {
        
    //     try {
    //         List<Empleado> empleados;
            
    //         if (codPla != null || depto != null || tipoEmp != null || estado != null || sexo != null) {
    //             empleados = empleadoRepository.findByFiltros(noCia, codPla, depto, tipoEmp, estado, sexo, Pageable.unpaged()).getContent();
    //         } else {
    //             empleados = empleadoRepository.findByNoCiaAndEstadoActivo(noCia, Pageable.unpaged()).getContent();
    //         }
            
    //         return empleados.stream()
    //                      .map(empleado -> new EmpleadoDTO(empleado, estadoEmpleadoService, estadoCivilService))
    //                      .collect(Collectors.toList());
                         
    //     } catch (Exception e) {
    //         throw new RuntimeException("Error al obtener el catálogo de empleados DTO: " + e.getMessage(), e);
    //     }
    // }

    @GetMapping("/dto-paginado")
    @Operation(
        summary = "Obtener catálogo de empleados DTO con paginación",
        description = "Obtiene una lista paginada de empleados activos usando el DTO para mejor presentación"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Catálogo de empleados DTO paginado obtenido exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros de consulta inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public Page<EmpleadoDTO> getCatalogoEmpleadosDTOPaginado(
            @Parameter(description = "Número de compañía", required = true)
            @RequestParam(defaultValue = "1") Integer noCia,
            
            @Parameter(description = "Código de planilla")
            @RequestParam(required = false) String codPla,
            
            @Parameter(description = "Departamento")
            @RequestParam(required = false) String depto,
            
            @Parameter(description = "Tipo de empleado")
            @RequestParam(required = false) String tipoEmp,
            
            @Parameter(description = "Estado del empleado")
            @RequestParam(required = false) String estado,
            
            @Parameter(description = "Sexo del empleado")
            @RequestParam(required = false) String sexo,
            
            Pageable pageable) {
        
        try {
            Page<Empleado> empleadosPage;
            
            if (codPla != null || depto != null || tipoEmp != null || estado != null || sexo != null) {
                empleadosPage = empleadoRepository.findByFiltros(noCia, codPla, depto, tipoEmp, estado, sexo, pageable);
            } else {
                empleadosPage = empleadoRepository.findByNoCiaAndEstadoActivo(noCia, pageable);
            }
            
            return empleadosPage.map(empleado -> new EmpleadoDTO(empleado, estadoEmpleadoService, estadoCivilService));
            
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el catálogo de empleados DTO paginado: " + e.getMessage(), e);
        }
    }

    @GetMapping("/buscar")
    @Operation(
        summary = "Buscar empleados por término",
        description = "Busca empleados activos por nombre, cédula, email o código de empleado"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Búsqueda de empleados completada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros de búsqueda inválidos"),
        @ApiResponse(responseCode = "404", description = "No se encontraron empleados"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<?> buscarEmpleados(
            @Parameter(description = "Número de compañía", required = true)
            @RequestParam(defaultValue = "1") Integer noCia,
            
            @Parameter(description = "Término de búsqueda", required = true)
            @RequestParam String termino) {
        
        try {
            if (termino == null || termino.trim().isEmpty()) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "El término de búsqueda es requerido");
                return ResponseEntity.badRequest().body(errorResponse);
            }
            
            List<Empleado> empleados = empleadoRepository.findByTerminoBusqueda(noCia, termino.trim());
            
            if (empleados.isEmpty()) {
                Map<String, Object> noResultsResponse = new HashMap<>();
                noResultsResponse.put("mensaje", "No se encontraron empleados que coincidan con el término de búsqueda");
                noResultsResponse.put("termino", termino);
                noResultsResponse.put("total", 0);
                return ResponseEntity.ok(noResultsResponse);
            }
            
            List<EmpleadoDTO> empleadosDTO = empleados.stream()
                .map(empleado -> new EmpleadoDTO(empleado, estadoEmpleadoService, estadoCivilService))
                .collect(Collectors.toList());
            
            return ResponseEntity.ok(empleadosDTO);
            
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al buscar empleados: " + e.getMessage());
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }

    @GetMapping("/{noCia}/{codPla}/{noEmple}")
    @Operation(
        summary = "Obtener empleado específico",
        description = "Obtiene los detalles completos de un empleado específico por su clave primaria"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Empleado encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Empleado no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Empleado> getEmpleado(
            @Parameter(description = "Número de compañía", required = true)
            @PathVariable Integer noCia,
            
            @Parameter(description = "Código de planilla", required = true)
            @PathVariable String codPla,
            
            @Parameter(description = "Número de empleado", required = true)
            @PathVariable String noEmple) {
        
        try {
            Optional<Empleado> empleado = empleadoRepository.findByNoCiaAndCodPlaAndNoEmple(noCia, codPla, noEmple);
            
            if (empleado.isPresent()) {
                return ResponseEntity.ok(empleado.get());
            } else {
                return ResponseEntity.notFound().build();
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el empleado: " + e.getMessage(), e);
        }
    }

    @GetMapping("/estadisticas")
    @Operation(
        summary = "Obtener estadísticas de empleados",
        description = "Obtiene estadísticas generales de los empleados por compañía"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estadísticas obtenidas exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Map<String, Object>> getEstadisticas(
            @Parameter(description = "Número de compañía", required = true)
            @RequestParam(defaultValue = "1") Integer noCia) {
        
        try {
            Map<String, Object> estadisticas = new HashMap<>();
            
            // Empleados activos
            Long empleadosActivos = empleadoRepository.countEmpleadosActivos(noCia);
            estadisticas.put("empleados_activos", empleadosActivos);
            
            // Por sexo
            Long hombres = empleadoRepository.countEmpleadosBySexo(noCia, "M");
            Long mujeres = empleadoRepository.countEmpleadosBySexo(noCia, "F");
            estadisticas.put("empleados_hombres", hombres);
            estadisticas.put("empleados_mujeres", mujeres);
            
            // Por tipo de empleado
            Long tipoEmpleado = empleadoRepository.countEmpleadosByTipo(noCia, "01");
            Long tipoEjecutivo = empleadoRepository.countEmpleadosByTipo(noCia, "02");
            estadisticas.put("tipo_empleados", tipoEmpleado);
            estadisticas.put("tipo_ejecutivos", tipoEjecutivo);
            
            // Supervisores
            List<Empleado> supervisores = empleadoRepository.findSupervisores(noCia);
            estadisticas.put("supervisores", supervisores.size());
            
            return ResponseEntity.ok(estadisticas);
            
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al obtener estadísticas: " + e.getMessage());
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }

    @GetMapping("/supervisores")
    @Operation(
        summary = "Obtener lista de supervisores",
        description = "Obtiene la lista de empleados que son supervisores"
    )
    public ResponseEntity<List<EmpleadoDTO>> getSupervisores(
            @Parameter(description = "Número de compañía", required = true)
            @RequestParam(defaultValue = "1") Integer noCia) {
        
        try {
            List<Empleado> supervisores = empleadoRepository.findSupervisores(noCia);
            List<EmpleadoDTO> supervisoresDTO = supervisores.stream()
                .map(empleado -> new EmpleadoDTO(empleado, estadoEmpleadoService, estadoCivilService))
                .collect(Collectors.toList());
            
            return ResponseEntity.ok(supervisoresDTO);
            
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener supervisores: " + e.getMessage(), e);
        }
    }

    @GetMapping("/por-departamento/{depto}")
    @Operation(
        summary = "Obtener empleados por departamento",
        description = "Obtiene la lista de empleados de un departamento específico"
    )
    public ResponseEntity<List<EmpleadoDTO>> getEmpleadosPorDepartamento(
            @Parameter(description = "Número de compañía", required = true)
            @RequestParam(defaultValue = "1") Integer noCia,
            
            @Parameter(description = "Código del departamento", required = true)
            @PathVariable String depto) {
        
        try {
            List<Empleado> empleados = empleadoRepository.findByDepartamento(noCia, depto);
            List<EmpleadoDTO> empleadosDTO = empleados.stream()
                .map(empleado -> new EmpleadoDTO(empleado, estadoEmpleadoService, estadoCivilService))
                .collect(Collectors.toList());
            
            return ResponseEntity.ok(empleadosDTO);
            
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener empleados por departamento: " + e.getMessage(), e);
        }
    }
    
    // ENDPOINTS DE DEPURACIÓN - Para verificar qué empleados existen
    
    // @GetMapping("/debug/todos")
    // @Operation(
    //     summary = "Obtener TODOS los empleados (sin filtros) - DEPURACIÓN",
    //     description = "Obtiene todos los empleados sin filtrar por estado - Solo para depuración"
    // )
    // public ResponseEntity<Page<Empleado>> getTodosLosEmpleados(
    //         @Parameter(description = "Número de compañía")
    //         @RequestParam(required = false) Integer noCia,
    //         Pageable pageable) {
        
    //     try {
    //         Page<Empleado> empleados;
    //         if (noCia != null) {
    //             empleados = empleadoRepository.findAllByNoCia(noCia, pageable);
    //         } else {
    //             empleados = empleadoRepository.findAllEmpleados(pageable);
    //         }
            
    //         return ResponseEntity.ok(empleados);
            
    //     } catch (Exception e) {
    //         throw new RuntimeException("Error al obtener todos los empleados: " + e.getMessage(), e);
    //     }
    // }
    
    // @GetMapping("/debug/contar")
    // @Operation(
    //     summary = "Contar empleados - DEPURACIÓN",
    //     description = "Cuenta cuántos empleados hay en la base de datos"
    // )
    // public ResponseEntity<Map<String, Object>> contarEmpleados(
    //         @Parameter(description = "Número de compañía")
    //         @RequestParam(required = false, defaultValue = "1") Integer noCia) {
        
    //     try {
    //         Map<String, Object> estadisticas = new HashMap<>();
            
    //         Long totalEmpleados = empleadoRepository.countAllEmpleados(noCia);
    //         Long empleadosActivos = empleadoRepository.countEmpleadosActivos(noCia);
            
    //         estadisticas.put("total_empleados", totalEmpleados);
    //         estadisticas.put("empleados_activos", empleadosActivos);
    //         estadisticas.put("compania", noCia);
            
    //         return ResponseEntity.ok(estadisticas);
            
    //     } catch (Exception e) {
    //         Map<String, Object> errorResponse = new HashMap<>();
    //         errorResponse.put("error", "Error al contar empleados: " + e.getMessage());
    //         return ResponseEntity.internalServerError().body(errorResponse);
    //     }
    // }
    
    // @GetMapping("/debug/estados")
    // @Operation(
    //     summary = "Ver qué estados de empleados existen - DEPURACIÓN",
    //     description = "Muestra los diferentes valores de estado que existen en la base de datos"
    // )
    // public ResponseEntity<Map<String, Object>> verEstados(
    //         @Parameter(description = "Número de compañía")
    //         @RequestParam(required = false, defaultValue = "1") Integer noCia) {
        
    //     try {
    //         // Obtener algunos empleados para ver sus estados
    //         Page<Empleado> empleados = empleadoRepository.findAllByNoCia(noCia, 
    //             org.springframework.data.domain.PageRequest.of(0, 10));
            
    //         Map<String, Object> resultado = new HashMap<>();
    //         Map<String, Integer> estadosPorCantidad = new HashMap<>();
            
    //         for (Empleado emp : empleados.getContent()) {
    //             String estado = emp.getEstado();
    //             estadosPorCantidad.put(estado, estadosPorCantidad.getOrDefault(estado, 0) + 1);
    //         }
            
    //         resultado.put("total_empleados_muestra", empleados.getContent().size());
    //         resultado.put("total_empleados_bd", empleados.getTotalElements());
    //         resultado.put("estados_encontrados", estadosPorCantidad);
    //         resultado.put("empleados_muestra", empleados.getContent().stream()
    //             .map(emp -> "Estado: " + emp.getEstado() + " - Nombre: " + emp.getNombre() + " - Empleado: " + emp.getNoEmple())
    //             .collect(java.util.stream.Collectors.toList()));
            
    //         return ResponseEntity.ok(resultado);
            
    //     } catch (Exception e) {
    //         Map<String, Object> errorResponse = new HashMap<>();
    //         errorResponse.put("error", "Error al verificar estados: " + e.getMessage());
    //         return ResponseEntity.internalServerError().body(errorResponse);
    //     }
    // }
    
    @GetMapping("/estados")
    @Operation(
        summary = "Obtener todos los estados de empleados",
        description = "Obtiene la lista de todos los estados de empleados disponibles con sus descripciones"
    )
    public ResponseEntity<Map<String, String>> getEstados() {
        try {
            Map<String, String> estados = estadoEmpleadoService.getTodosLosEstados();
            return ResponseEntity.ok(estados);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al obtener estados: " + e.getMessage());
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
    
    // @PostMapping("/estados/recargar")
    // @Operation(
    //     summary = "Recargar cache de estados",
    //     description = "Recarga el cache de estados desde la base de datos"
    // )
    // public ResponseEntity<Map<String, Object>> recargarEstados() {
    //     try {
    //         estadoEmpleadoService.recargarCache();
    //         Map<String, Object> response = new HashMap<>();
    //         response.put("mensaje", "Cache de estados recargado exitosamente");
    //         response.put("estados", estadoEmpleadoService.getTodosLosEstados());
    //         return ResponseEntity.ok(response);
    //     } catch (Exception e) {
    //         Map<String, Object> errorResponse = new HashMap<>();
    //         errorResponse.put("error", "Error al recargar cache de estados: " + e.getMessage());
    //         return ResponseEntity.internalServerError().body(errorResponse);
    //     }
    // }
}
