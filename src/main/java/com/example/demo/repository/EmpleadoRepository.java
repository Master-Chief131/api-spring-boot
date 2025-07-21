package com.example.demo.repository;

import com.example.demo.entity.Empleado;
import com.example.demo.entity.EmpleadoId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para la entidad Empleado
 */
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, EmpleadoId> {
    
    /**
     * Buscar empleados activos por compañía
     * NOTA: Temporalmente sin filtrar por estado para ver qué empleados hay
     */
    @Query("SELECT e FROM Empleado e WHERE e.noCia = :noCia")
    Page<Empleado> findByNoCiaAndEstadoActivo(@Param("noCia") Integer noCia, Pageable pageable);
    
    /**
     * Buscar empleados por compañía, código de planilla y filtros opcionales
     * NOTA: Temporalmente sin filtrar por estado para ver qué empleados hay
     */
    @Query("SELECT e FROM Empleado e WHERE e.noCia = :noCia " +
           "AND (:codPla IS NULL OR e.codPla = :codPla) " +
           "AND (:depto IS NULL OR e.depto = :depto) " +
           "AND (:tipoEmp IS NULL OR e.tipoEmp = :tipoEmp) " +
           "AND (:estado IS NULL OR e.estado = :estado) " +
           "AND (:sexo IS NULL OR e.sexo = :sexo)")
    Page<Empleado> findByFiltros(@Param("noCia") Integer noCia,
                                @Param("codPla") String codPla,
                                @Param("depto") String depto,
                                @Param("tipoEmp") String tipoEmp,
                                @Param("estado") String estado,
                                @Param("sexo") String sexo,
                                Pageable pageable);
    
    /**
     * Buscar empleados por término de búsqueda (nombre, cédula, email)
     * NOTA: Temporalmente sin filtrar por estado para ver qué empleados hay
     */
    @Query("SELECT e FROM Empleado e WHERE e.noCia = :noCia " +
           "AND (UPPER(e.nombre) LIKE UPPER(CONCAT('%', :termino, '%')) " +
           "OR UPPER(e.cedula) LIKE UPPER(CONCAT('%', :termino, '%')) " +
           "OR UPPER(e.email) LIKE UPPER(CONCAT('%', :termino, '%')) " +
           "OR UPPER(e.noEmple) LIKE UPPER(CONCAT('%', :termino, '%')))")
    List<Empleado> findByTerminoBusqueda(@Param("noCia") Integer noCia, @Param("termino") String termino);
    
    /**
     * Buscar empleado específico por clave primaria
     */
    Optional<Empleado> findByNoCiaAndCodPlaAndNoEmple(Integer noCia, String codPla, String noEmple);
    
    /**
     * Contar empleados activos por compañía
     * NOTA: Temporalmente contando todos para ver qué empleados hay
     */
    @Query("SELECT COUNT(e) FROM Empleado e WHERE e.noCia = :noCia")
    Long countEmpleadosActivos(@Param("noCia") Integer noCia);
    
    /**
     * Contar empleados por sexo
     * NOTA: Temporalmente sin filtrar por estado para ver qué empleados hay
     */
    @Query("SELECT COUNT(e) FROM Empleado e WHERE e.noCia = :noCia AND e.sexo = :sexo")
    Long countEmpleadosBySexo(@Param("noCia") Integer noCia, @Param("sexo") String sexo);
    
    /**
     * Contar empleados por tipo
     * NOTA: Temporalmente sin filtrar por estado para ver qué empleados hay
     */
    @Query("SELECT COUNT(e) FROM Empleado e WHERE e.noCia = :noCia AND e.tipoEmp = :tipoEmp")
    Long countEmpleadosByTipo(@Param("noCia") Integer noCia, @Param("tipoEmp") String tipoEmp);
    
    /**
     * Buscar supervisores
     * NOTA: Temporalmente sin filtrar por estado para ver qué empleados hay
     */
    @Query("SELECT e FROM Empleado e WHERE e.noCia = :noCia AND e.indSupervisor = 'S'")
    List<Empleado> findSupervisores(@Param("noCia") Integer noCia);
    
    /**
     * Buscar empleados por departamento
     * NOTA: Temporalmente sin filtrar por estado para ver qué empleados hay
     */
    @Query("SELECT e FROM Empleado e WHERE e.noCia = :noCia AND e.depto = :depto")
    List<Empleado> findByDepartamento(@Param("noCia") Integer noCia, @Param("depto") String depto);
    
    // MÉTODOS DE DEPURACIÓN - Para verificar qué empleados existen
    
    /**
     * Buscar TODOS los empleados por compañía (sin filtrar por estado) - SOLO PARA DEPURACIÓN
     */
    @Query("SELECT e FROM Empleado e WHERE e.noCia = :noCia")
    Page<Empleado> findAllByNoCia(@Param("noCia") Integer noCia, Pageable pageable);
    
    /**
     * Buscar todos los empleados sin filtros - SOLO PARA DEPURACIÓN
     */
    @Query("SELECT e FROM Empleado e")
    Page<Empleado> findAllEmpleados(Pageable pageable);
    
    /**
     * Contar total de empleados por compañía (sin filtrar por estado)
     */
    @Query("SELECT COUNT(e) FROM Empleado e WHERE e.noCia = :noCia")
    Long countAllEmpleados(@Param("noCia") Integer noCia);
}
