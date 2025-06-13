package com.example.demo.service;

import com.example.demo.dto.CambioClaveDTO;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    /**
     * Encripta una clave usando el mismo método que el sistema existente
     */
    private String encriptarClave(String clave) {
        // Implementación simple de encriptación (consistente con ClienteController)
        return clave != null ? clave.toUpperCase() : "123456";
    }
    
    /**
     * Valida las credenciales del usuario
     */
    public boolean validarCredenciales(String usuario, String clave) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsuario(usuario);
        
        if (usuarioOpt.isPresent()) {
            Usuario u = usuarioOpt.get();
            String claveEncriptada = encriptarClave(clave);
            return claveEncriptada.equals(u.getClave());
        }
        
        return false;
    }
    
    /**
     * Cambia la contraseña de un usuario
     */
    @Transactional
    public Map<String, Object> cambiarClave(CambioClaveDTO cambioClaveDTO) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Validar datos de entrada
            if (!cambioClaveDTO.isValid()) {
                response.put("success", false);
                response.put("mensaje", "Todos los campos son requeridos");
                return response;
            }
            
            // Validar que las claves no sean iguales
            if (cambioClaveDTO.getClaveActual().equals(cambioClaveDTO.getClaveNueva())) {
                response.put("success", false);
                response.put("mensaje", "La nueva contraseña debe ser diferente a la actual");
                return response;
            }
            
            // Buscar el usuario
            Optional<Usuario> usuarioOpt = usuarioRepository.findByUsuario(cambioClaveDTO.getUsuario());
            
            if (!usuarioOpt.isPresent()) {
                response.put("success", false);
                response.put("mensaje", "Usuario no encontrado");
                return response;
            }
            
            Usuario usuario = usuarioOpt.get();
            
            // Verificar la contraseña actual
            String claveActualEncriptada = encriptarClave(cambioClaveDTO.getClaveActual());
            if (!claveActualEncriptada.equals(usuario.getClave())) {
                response.put("success", false);
                response.put("mensaje", "La contraseña actual es incorrecta");
                return response;
            }
            
            // Validar nueva contraseña (opcional: agregar reglas de complejidad)
            if (cambioClaveDTO.getClaveNueva().length() < 6) {
                response.put("success", false);
                response.put("mensaje", "La nueva contraseña debe tener al menos 6 caracteres");
                return response;
            }
            
            // Encriptar y actualizar la nueva contraseña
            String claveNuevaEncriptada = encriptarClave(cambioClaveDTO.getClaveNueva());
            usuario.setClave(claveNuevaEncriptada);
            
            // Guardar cambios
            usuarioRepository.save(usuario);
            
            response.put("success", true);
            response.put("mensaje", "Contraseña actualizada exitosamente");
            response.put("usuario", cambioClaveDTO.getUsuario());
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("mensaje", "Error interno al cambiar la contraseña: " + e.getMessage());
        }
        
        return response;
    }
    
    /**
     * Obtiene información básica de un usuario (sin la clave)
     */
    public Map<String, Object> obtenerInfoUsuario(String usuario) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Optional<Usuario> usuarioOpt = usuarioRepository.findByUsuario(usuario);
            
            if (usuarioOpt.isPresent()) {
                Usuario u = usuarioOpt.get();
                response.put("success", true);
                response.put("usuario", u.getUsuario());
                response.put("nomUsuario", u.getNomUsuario());
                response.put("apeUsuario", u.getApeUsuario());
                response.put("email", u.getEmail());
                response.put("indActivo", u.getIndActivo());
                response.put("indCliente", u.getIndCliente());
            } else {
                response.put("success", false);
                response.put("mensaje", "Usuario no encontrado");
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("mensaje", "Error al obtener información del usuario: " + e.getMessage());
        }
        
        return response;
    }
}
