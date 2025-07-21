package com.example.demo.repository;

import com.example.demo.entity.EstadoEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para la entidad EstadoEmpleado
 */
@Repository
public interface EstadoEmpleadoRepository extends JpaRepository<EstadoEmpleado, String> {
    
    /**
     * Buscar estado por código
     */
    Optional<EstadoEmpleado> findByCodigo(String codigo);
    
    /**
     * Obtener todos los estados ordenados por descripción
     */
    @Query("SELECT e FROM EstadoEmpleado e ORDER BY e.descripcion")
    List<EstadoEmpleado> findAllOrderByDescripcion();
    
    /**
     * Verificar si existe un código de estado
     */
    boolean existsByCodigo(String codigo);
}
