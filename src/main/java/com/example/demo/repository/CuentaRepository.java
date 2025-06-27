package com.example.demo.repository;

import com.example.demo.entity.Cuenta;
import com.example.demo.entity.CuentaId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, CuentaId>, JpaSpecificationExecutor<Cuenta> {
    
    /**
     * Buscar cuentas por compañía
     */
    List<Cuenta> findByNoCia(Integer noCia);
    
    /**
     * Buscar cuentas por compañía paginado
     */
    Page<Cuenta> findByNoCia(Integer noCia, Pageable pageable);
    
    /**
     * Buscar cuentas activas por compañía
     */
    List<Cuenta> findByNoCiaAndActivo(Integer noCia, String activo);
    
    /**
     * Buscar cuentas activas por compañía paginado
     */
    Page<Cuenta> findByNoCiaAndActivo(Integer noCia, String activo, Pageable pageable);
    
    /**
     * Buscar cuentas por tipo
     */
    List<Cuenta> findByNoCiaAndTipo(Integer noCia, Integer tipo);
    
    /**
     * Buscar cuentas por descripción (like)
     */
    List<Cuenta> findByNoCiaAndDescripcionContainingIgnoreCase(Integer noCia, String descripcion);
    
    /**
     * Buscar cuentas por descripción paginado
     */
    Page<Cuenta> findByNoCiaAndDescripcionContainingIgnoreCase(Integer noCia, String descripcion, Pageable pageable);
    
    /**
     * Buscar cuentas que permiten movimiento
     */
    List<Cuenta> findByNoCiaAndIndMov(Integer noCia, String indMov);
    
    /**
     * Buscar cuentas por nivel
     */
    List<Cuenta> findByNoCiaAndNivel(Integer noCia, String nivel);
    
    /**
     * Buscar cuentas hijas por cuenta padre
     */
    List<Cuenta> findByNoCiaAndPadre(Integer noCia, String padre);
    
    /**
     * Buscar cuentas por naturaleza (Débito/Crédito)
     */
    List<Cuenta> findByNoCiaAndNaturaleza(Integer noCia, String naturaleza);
    
    /**
     * Buscar cuentas que empiecen con un código específico
     */
    @Query("SELECT c FROM Cuenta c WHERE c.noCia = :noCia AND c.cuenta LIKE :codigoCuenta%")
    List<Cuenta> findByCodigoInicio(@Param("noCia") Integer noCia, @Param("codigoCuenta") String codigoCuenta);
    
    /**
     * Buscar cuentas con permisos específicos
     */
    @Query("SELECT c FROM Cuenta c WHERE c.noCia = :noCia AND " +
           "(c.permisoCont = 'S' OR c.permisoCxc = 'S' OR c.permisoCxp = 'S' OR " +
           "c.permisoInv = 'S' OR c.permisoFact = 'S')")
    List<Cuenta> findCuentasConPermisos(@Param("noCia") Integer noCia);
    
    /**
     * Buscar el plan de cuentas jerárquico
     */
    @Query("SELECT c FROM Cuenta c WHERE c.noCia = :noCia ORDER BY c.cuenta")
    List<Cuenta> findPlanCuentas(@Param("noCia") Integer noCia);
    
    /**
     * Contar cuentas por tipo
     */
    @Query("SELECT COUNT(c) FROM Cuenta c WHERE c.noCia = :noCia AND c.tipo = :tipo")
    Long countByTipo(@Param("noCia") Integer noCia, @Param("tipo") Integer tipo);
}
