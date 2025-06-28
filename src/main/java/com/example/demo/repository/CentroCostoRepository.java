package com.example.demo.repository;

import com.example.demo.entity.CentroCosto;
import com.example.demo.entity.CentroCostoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CentroCostoRepository extends JpaRepository<CentroCosto, CentroCostoId>, JpaSpecificationExecutor<CentroCosto> {

    /**
     * Buscar centros de costo por compañía y estado activo
     */
    List<CentroCosto> findByNoCiaAndActivo(Integer noCia, String activo);

    /**
     * Buscar centros de costo por compañía y que permiten movimiento
     */
    List<CentroCosto> findByNoCiaAndIndMov(Integer noCia, String indMov);

    /**
     * Buscar centros de costo por compañía, activos y que permiten movimiento
     */
    List<CentroCosto> findByNoCiaAndActivoAndIndMov(Integer noCia, String activo, String indMov);

    /**
     * Buscar centros de costo por compañía y tipo de gasto
     */
    List<CentroCosto> findByNoCiaAndTipoGasto(Integer noCia, String tipoGasto);

    /**
     * Buscar centros de costo por compañía y relacionado a cuentas
     */
    List<CentroCosto> findByNoCiaAndRelacionadoACuentas(Integer noCia, String relacionadoACuentas);

    /**
     * Buscar centros de costo hijos por compañía y centro padre
     */
    List<CentroCosto> findByNoCiaAndPadre(Integer noCia, String padre);

    /**
     * Buscar centros de costo por compañía y nivel
     */
    List<CentroCosto> findByNoCiaAndNivel(Integer noCia, Integer nivel);

    /**
     * Buscar centros de costo por compañía y encargado
     */
    List<CentroCosto> findByNoCiaAndEncargadoCc(Integer noCia, String encargadoCc);

    /**
     * Obtener el plan de centros de costo ordenado jerárquicamente
     */
    @Query("SELECT cc FROM CentroCosto cc WHERE cc.noCia = :noCia ORDER BY cc.centro")
    List<CentroCosto> findPlanCentrosCosto(@Param("noCia") Integer noCia);

    /**
     * Buscar centros de costo operativos (activos y que permiten movimiento)
     */
    @Query("SELECT cc FROM CentroCosto cc WHERE cc.noCia = :noCia AND cc.activo = 'S' AND cc.indMov = 'S' ORDER BY cc.centro")
    List<CentroCosto> findCentrosCostoOperativos(@Param("noCia") Integer noCia);

    /**
     * Contar centros de costo por compañía y estado
     */
    @Query("SELECT COUNT(cc) FROM CentroCosto cc WHERE cc.noCia = :noCia AND cc.activo = :activo")
    long countByNoCiaAndActivo(@Param("noCia") Integer noCia, @Param("activo") String activo);

    /**
     * Buscar centros de costo por descripción (búsqueda flexible)
     */
    @Query("SELECT cc FROM CentroCosto cc WHERE cc.noCia = :noCia AND " +
           "(LOWER(cc.centro) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(cc.descripCc) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(cc.encargadoCc) LIKE LOWER(CONCAT('%', :termino, '%')))")
    List<CentroCosto> buscarPorTermino(@Param("noCia") Integer noCia, @Param("termino") String termino);
}
