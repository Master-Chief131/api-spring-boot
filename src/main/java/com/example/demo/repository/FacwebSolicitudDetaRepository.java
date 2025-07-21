package com.example.demo.repository;

import com.example.demo.entity.FacwebSolicitudDeta;
import com.example.demo.entity.FacwebSolicitudDetaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FacwebSolicitudDetaRepository extends JpaRepository<FacwebSolicitudDeta, FacwebSolicitudDetaId> {
    
    // Buscar detalles por solicitud
    List<FacwebSolicitudDeta> findByNoCiaAndNoSolicitud(Integer noCia, Integer noSolicitud);
    
    // Buscar detalles por solicitud ordenados por línea
    @Query("SELECT d FROM FacwebSolicitudDeta d WHERE d.noCia = :noCia AND d.noSolicitud = :noSolicitud ORDER BY d.noLinea")
    List<FacwebSolicitudDeta> findByNoCiaAndNoSolicitudOrderByNoLinea(@Param("noCia") Integer noCia, @Param("noSolicitud") Integer noSolicitud);
    
    // Buscar detalles por artículo
    List<FacwebSolicitudDeta> findByNoCiaAndNoArticulo(Integer noCia, String noArticulo);
}
