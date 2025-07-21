package com.example.demo.repository;

import com.example.demo.entity.FacwebFactura;
import com.example.demo.entity.FacwebFacturaId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FacwebFacturaRepository extends JpaRepository<FacwebFactura, FacwebFacturaId> {
    
    /**
     * Buscar facturas por compañía y status F (Facturada) - PAGINADO
     */
    Page<FacwebFactura> findByNoCiaAndStatusOrderByFechaRegistroDesc(Integer noCia, String status, Pageable pageable);
    
    /**
     * Buscar facturas por compañía, cliente y status F - PAGINADO
     */
    Page<FacwebFactura> findByNoCiaAndNoClienteAndStatusOrderByFechaRegistroDesc(Integer noCia, Integer noCliente, String status, Pageable pageable);
    
    /**
     * Buscar facturas por compañía, cliente y status F - SIN PAGINACION (para casos específicos)
     * LIMITADO A 1000 REGISTROS para evitar sobrecarga
     */
    @Query(value = "SELECT * FROM facweb_factura f WHERE f.no_cia = :noCia AND f.no_cliente = :noCliente AND f.status = :status ORDER BY f.fecha_registro DESC LIMIT 1000", nativeQuery = true)
    List<FacwebFactura> findByNoCiaAndNoClienteAndStatusOrderByFechaRegistroDesc(@Param("noCia") Integer noCia, @Param("noCliente") Integer noCliente, @Param("status") String status);
    
    /**
     * Buscar facturas recientes (últimos 30 días) por compañía y status F - LIMITADO A 100
     */
    @Query("SELECT f FROM FacwebFactura f WHERE f.noCia = :noCia AND f.status = 'F' AND f.fechaRegistro >= :fechaDesde ORDER BY f.fechaRegistro DESC")
    Page<FacwebFactura> findFacturasRecientes(@Param("noCia") Integer noCia, @Param("fechaDesde") LocalDateTime fechaDesde, Pageable pageable);
    
    /**
     * Buscar facturas recientes por cliente - LIMITADO A 50
     */
    @Query(value = "SELECT * FROM facweb_factura f WHERE f.no_cia = :noCia AND f.no_cliente = :noCliente AND f.status = 'F' AND f.fecha_registro >= :fechaDesde ORDER BY f.fecha_registro DESC LIMIT 50", nativeQuery = true)
    List<FacwebFactura> findFacturasRecientesPorCliente(@Param("noCia") Integer noCia, @Param("noCliente") Integer noCliente, @Param("fechaDesde") LocalDateTime fechaDesde);
    
    /**
     * Buscar facturas por compañía en un rango de fechas - PAGINADO
     */
    @Query("SELECT f FROM FacwebFactura f WHERE f.noCia = :noCia AND f.status = 'F' AND f.fechaRegistro BETWEEN :fechaDesde AND :fechaHasta ORDER BY f.fechaRegistro DESC")
    Page<FacwebFactura> findFacturasPorRangoFechas(@Param("noCia") Integer noCia, @Param("fechaDesde") LocalDateTime fechaDesde, @Param("fechaHasta") LocalDateTime fechaHasta, Pageable pageable);
    
    /**
     * Buscar facturas por compañía, cliente en un rango de fechas - SIN PAGINACION (cliente específico)
     * LIMITADO A 500 REGISTROS para evitar sobrecarga en rangos muy amplios
     */
    @Query(value = "SELECT * FROM facweb_factura f WHERE f.no_cia = :noCia AND f.no_cliente = :noCliente AND f.status = 'F' AND f.fecha_registro BETWEEN :fechaDesde AND :fechaHasta ORDER BY f.fecha_registro DESC LIMIT 500", nativeQuery = true)
    List<FacwebFactura> findFacturasPorClienteYRangoFechas(@Param("noCia") Integer noCia, @Param("noCliente") Integer noCliente, @Param("fechaDesde") LocalDateTime fechaDesde, @Param("fechaHasta") LocalDateTime fechaHasta);
    
    /**
     * Buscar factura específica por compañía y número de factura
     */
    @Query("SELECT f FROM FacwebFactura f WHERE f.noCia = :noCia AND f.noFactura = :noFactura AND f.status = 'F'")
    FacwebFactura findFacturaCompleta(@Param("noCia") Integer noCia, @Param("noFactura") Integer noFactura);
    
    /**
     * Contar facturas por compañía y status
     */
    long countByNoCiaAndStatus(Integer noCia, String status);
    
    /**
     * Contar facturas por compañía, cliente y status
     */
    long countByNoCiaAndNoClienteAndStatus(Integer noCia, Integer noCliente, String status);
}
