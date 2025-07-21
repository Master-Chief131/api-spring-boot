package com.example.demo.repository;

import com.example.demo.entity.FacwebFacturaDeta;
import com.example.demo.entity.FacwebFacturaDetaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacwebFacturaDetaRepository extends JpaRepository<FacwebFacturaDeta, FacwebFacturaDetaId> {
    
    /**
     * Buscar detalles de una factura específica
     */
    List<FacwebFacturaDeta> findByNoCiaAndNoFacturaOrderByNoLinea(Integer noCia, Integer noFactura);
    
    /**
     * Buscar detalles de múltiples facturas
     */
    @Query("SELECT d FROM FacwebFacturaDeta d WHERE d.noCia = :noCia AND d.noFactura IN :noFacturas ORDER BY d.noFactura, d.noLinea")
    List<FacwebFacturaDeta> findDetallesPorFacturas(@Param("noCia") Integer noCia, @Param("noFacturas") List<Integer> noFacturas);
}
