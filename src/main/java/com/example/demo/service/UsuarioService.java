package com.example.demo.service;

import com.example.demo.dto.CambioClaveDTO;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.utils.PasswordUtils;
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
     * Encripta una clave usando SHA + Base64 (mismo método que PasswordUtils)
     * IMPORTANTE: Debe ser consistente con el registro de clientes
     */
    private String encriptarClave(String clave) {
        return PasswordUtils.encriptarClave(clave);
    }
      /**
     * Valida las credenciales del usuario encriptando la clave recibida
     * y comparándola con la almacenada en la base de datos
     */
    public boolean validarCredenciales(String usuario, String clave) {
        System.out.println("=== VALIDANDO CREDENCIALES ===");
        System.out.println("Usuario: " + usuario);
        System.out.println("Clave recibida (texto plano): [OCULTA]");
        
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsuario(usuario);
        
        if (usuarioOpt.isPresent()) {
            Usuario u = usuarioOpt.get();
            String claveRecibidaEncriptada = encriptarClave(clave);
            String claveAlmacenada = u.getClave();
            
            System.out.println("Clave recibida encriptada (SHA+Base64): " + claveRecibidaEncriptada);
            System.out.println("Clave almacenada en BD: " + claveAlmacenada);
            
            boolean coinciden = claveRecibidaEncriptada.equals(claveAlmacenada);
            System.out.println("¿Contraseñas coinciden?: " + coinciden);
            
            return coinciden;
        } else {
            System.err.println("Usuario no encontrado: " + usuario);
            return false;
        }
    }
      /**
     * Cambia la contraseña de un usuario validando primero la contraseña actual
     * FLUJO CORRECTO: 
     * 1. Recibe contraseña actual en texto plano
     * 2. La encripta con SHA+Base64 
     * 3. Compara con la almacenada en BD
     * 4. Si coincide, encripta la nueva contraseña y la guarda
     */
    @Transactional
    public Map<String, Object> cambiarClave(CambioClaveDTO cambioClaveDTO) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            System.out.println("=== INICIANDO CAMBIO DE CONTRASEÑA ===");
            System.out.println("Usuario: " + cambioClaveDTO.getUsuario());
            
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
                System.err.println("Usuario no encontrado: " + cambioClaveDTO.getUsuario());
                response.put("success", false);
                response.put("mensaje", "Usuario no encontrado");
                return response;
            }
            
            Usuario usuario = usuarioOpt.get();
            
            // Verificar la contraseña actual (ENCRIPTAR la recibida y comparar)
            String claveActualRecibida = cambioClaveDTO.getClaveActual();
            String claveActualEncriptada = encriptarClave(claveActualRecibida);
            String claveAlmacenadaBD = usuario.getClave();
            
            System.out.println("=== VALIDACIÓN DE CONTRASEÑA ACTUAL ===");
            System.out.println("Clave actual recibida (texto plano): [OCULTA]");
            System.out.println("Clave actual encriptada (SHA+Base64): " + claveActualEncriptada);
            System.out.println("Clave almacenada en BD: " + claveAlmacenadaBD);
            
            if (!claveActualEncriptada.equals(claveAlmacenadaBD)) {
                System.err.println("❌ CONTRASEÑA ACTUAL INCORRECTA");
                response.put("success", false);
                response.put("mensaje", "La contraseña actual es incorrecta");
                return response;
            }
            
            System.out.println("✅ CONTRASEÑA ACTUAL VALIDADA CORRECTAMENTE");
            
            // Validar nueva contraseña
            if (cambioClaveDTO.getClaveNueva().length() < 6) {
                response.put("success", false);
                response.put("mensaje", "La nueva contraseña debe tener al menos 6 caracteres");
                return response;
            }
            
            // Encriptar y actualizar la nueva contraseña
            String claveNuevaEncriptada = encriptarClave(cambioClaveDTO.getClaveNueva());
            
            System.out.println("=== ACTUALIZANDO CONTRASEÑA ===");
            System.out.println("Nueva clave encriptada (SHA+Base64): " + claveNuevaEncriptada);
            
            usuario.setClave(claveNuevaEncriptada);
            
            // Guardar cambios
            usuarioRepository.save(usuario);
            
            System.out.println("✅ CONTRASEÑA ACTUALIZADA EXITOSAMENTE");
            
            response.put("success", true);
            response.put("mensaje", "Contraseña actualizada exitosamente");
            response.put("usuario", cambioClaveDTO.getUsuario());
            
        } catch (Exception e) {
            System.err.println("❌ ERROR EN CAMBIO DE CONTRASEÑA: " + e.getMessage());
            e.printStackTrace();
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
