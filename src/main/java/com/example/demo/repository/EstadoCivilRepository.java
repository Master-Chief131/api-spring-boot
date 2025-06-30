package com.example.demo.repository;

import com.example.demo.entity.EstadoCivil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para la entidad EstadoCivil
 */
@Repository
public interface EstadoCivilRepository extends JpaRepository<EstadoCivil, String> {
    
    /**
     * Buscar estado civil por código
     */
    Optional<EstadoCivil> findByCodigo(String codigo);
    
    /**
     * Obtener todos los estados civiles ordenados por descripción
     */
    @Query("SELECT e FROM EstadoCivil e ORDER BY e.descripcion")
    List<EstadoCivil> findAllOrderByDescripcion();
    
    /**
     * Buscar estados civiles por descripción (búsqueda parcial)
     */
    @Query("SELECT e FROM EstadoCivil e WHERE LOWER(e.descripcion) LIKE LOWER(CONCAT('%', :descripcion, '%'))")
    List<EstadoCivil> findByDescripcionContainingIgnoreCase(String descripcion);
}
