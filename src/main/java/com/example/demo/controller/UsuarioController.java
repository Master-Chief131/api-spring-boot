package com.example.demo.controller;

import com.example.demo.repository.UsuarioRepository;
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
}
