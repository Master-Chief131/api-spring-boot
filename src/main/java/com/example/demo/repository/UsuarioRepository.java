package com.example.demo.repository;

import com.example.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    
    /**
     * Buscar usuario por email
     */
    Optional<Usuario> findByEmail(String email);
    
    /**
     * Verificar si existe un usuario con el email dado
     */
    boolean existsByEmail(String email);
    
    /**
     * Verificar si existe un usuario por su nombre de usuario
     */
    boolean existsByUsuario(String usuario);
    
    /**
     * Buscar usuario por nombre de usuario (que en este caso puede ser email)
     */
    Optional<Usuario> findByUsuario(String usuario);
    
    /**
     * Query personalizada para verificar existencia por email
     */
    @Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE LOWER(u.email) = LOWER(:email)")
    boolean existsByEmailIgnoreCase(@Param("email") String email);
}
