package com.example.demo.controller;

import com.example.demo.dto.CambioClaveDTO;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "API para gestión de usuarios del sistema")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/validar-email")
    @Operation(
        summary = "Validar existencia de usuario por email",
        description = "Verifica si ya existe un usuario registrado con el email proporcionado en la tabla segweb_usuario"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Validación exitosa",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Map.class)
            )
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Email no proporcionado o inválido"
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Error interno del servidor"
        )
    })
    public ResponseEntity<Map<String, Object>> validarEmail(
        @Parameter(
            description = "Email del usuario a validar", 
            required = true,
            example = "usuario@ejemplo.com"
        )
        @RequestParam String usuario
    ) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Validar que el email no esté vacío
            if (usuario == null || usuario.trim().isEmpty()) {
                response.put("success", false);
                response.put("existe", false);
                response.put("mensaje", "El email es requerido");
                return ResponseEntity.badRequest().body(response);
            }
            
            String email = usuario.trim();
            
            // Verificar si el usuario existe por email (case insensitive)
            boolean existe = usuarioRepository.existsByEmailIgnoreCase(email);
            
            response.put("success", true);
            response.put("existe", existe);
            
            if (existe) {
                response.put("mensaje", "El usuario con email '" + email + "' ya existe en el sistema");
            } else {
                response.put("mensaje", "El email '" + email + "' está disponible");
            }
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("existe", false);
            response.put("mensaje", "Error al validar el usuario: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    @GetMapping("/validar-usuario")
    @Operation(
        summary = "Validar existencia de usuario por nombre de usuario",
        description = "Verifica si ya existe un usuario registrado con el nombre de usuario proporcionado"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Validación exitosa",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Map.class)
            )
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Usuario no proporcionado o inválido"
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Error interno del servidor"
        )
    })
    public ResponseEntity<Map<String, Object>> validarUsuario(
        @Parameter(
            description = "Nombre de usuario a validar", 
            required = true,
            example = "juan.perez"
        )
        @RequestParam String usuario
    ) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Validar que el usuario no esté vacío
            if (usuario == null || usuario.trim().isEmpty()) {
                response.put("success", false);
                response.put("existe", false);
                response.put("mensaje", "El nombre de usuario es requerido");
                return ResponseEntity.badRequest().body(response);
            }
            
            String nombreUsuario = usuario.trim();
            
            // Verificar si el usuario existe por nombre de usuario
            boolean existe = usuarioRepository.existsByUsuario(nombreUsuario);
            
            response.put("success", true);
            response.put("existe", existe);
            
            if (existe) {
                response.put("mensaje", "El usuario '" + nombreUsuario + "' ya existe en el sistema");
            } else {
                response.put("mensaje", "El usuario '" + nombreUsuario + "' está disponible");
            }
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("existe", false);
            response.put("mensaje", "Error al validar el usuario: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    @PostMapping("/cambiar-clave")
    @Operation(
        summary = "Cambiar contraseña de usuario",
        description = "Permite cambiar la contraseña de un usuario validando primero las credenciales actuales"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Contraseña cambiada exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Map.class)
            )
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Datos inválidos o contraseña actual incorrecta"
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Usuario no encontrado"
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Error interno del servidor"
        )
    })
    public ResponseEntity<Map<String, Object>> cambiarClave(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos para el cambio de contraseña",
            required = true,
            content = @Content(
                schema = @Schema(implementation = CambioClaveDTO.class)
            )
        )
        @RequestBody CambioClaveDTO cambioClaveDTO
    ) {
        try {
            Map<String, Object> response = usuarioService.cambiarClave(cambioClaveDTO);
            
            if ((Boolean) response.get("success")) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
            
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("mensaje", "Error interno del servidor: " + e.getMessage());
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
    
    @GetMapping("/info")
    @Operation(
        summary = "Obtener información de usuario",
        description = "Obtiene la información básica de un usuario (sin incluir la contraseña)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Información obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Map.class)
            )
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Usuario no proporcionado"
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Usuario no encontrado"
        ),
        @ApiResponse(
            responseCode = "500", 
            description = "Error interno del servidor"
        )
    })
    public ResponseEntity<Map<String, Object>> obtenerInfoUsuario(
        @Parameter(
            description = "Nombre de usuario", 
            required = true,
            example = "juan.perez"
        )
        @RequestParam String usuario
    ) {
        try {
            if (usuario == null || usuario.trim().isEmpty()) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("mensaje", "El nombre de usuario es requerido");
                return ResponseEntity.badRequest().body(response);
            }
            
            Map<String, Object> response = usuarioService.obtenerInfoUsuario(usuario.trim());
            
            if ((Boolean) response.get("success")) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(404).body(response);
            }
            
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("mensaje", "Error interno del servidor: " + e.getMessage());
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
}
