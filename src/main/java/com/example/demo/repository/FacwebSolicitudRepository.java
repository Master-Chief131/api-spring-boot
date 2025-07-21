package com.example.demo.repository;

import com.example.demo.entity.FacwebSolicitud;
import com.example.demo.entity.SolicitudId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface FacwebSolicitudRepository extends JpaRepository<FacwebSolicitud, SolicitudId> {
    
    // Buscar solicitudes por compañía
    @Query("SELECT s FROM FacwebSolicitud s WHERE s.noCia = :noCia ORDER BY s.fechaRegistro DESC")
    List<FacwebSolicitud> findByNoCia(@Param("noCia") Integer noCia);
    
    // Buscar solicitudes por cliente
    @Query("SELECT s FROM FacwebSolicitud s WHERE s.noCia = :noCia AND s.noCliente = :noCliente ORDER BY s.fechaRegistro DESC")
    List<FacwebSolicitud> findByNoCiaAndNoCliente(@Param("noCia") Integer noCia, @Param("noCliente") Integer noCliente);
    
    // Buscar solicitudes por cliente y status
    @Query("SELECT s FROM FacwebSolicitud s WHERE s.noCia = :noCia AND s.noCliente = :noCliente AND s.status = :status ORDER BY s.fechaRegistro DESC")
    List<FacwebSolicitud> findByNoCiaAndNoClienteAndStatus(@Param("noCia") Integer noCia, @Param("noCliente") Integer noCliente, @Param("status") String status);
    
    @Query("SELECT s FROM FacwebSolicitud s WHERE s.noCliente = :noCliente ORDER BY s.noSolicitud DESC")
    List<FacwebSolicitud> findByNoClienteOrderByNoSolicitudDesc(@Param("noCliente") Integer noCliente);
    
    @Query("SELECT s FROM FacwebSolicitud s WHERE s.noCia = :noCia AND s.status = :status")
    List<FacwebSolicitud> findByNoCiaAndStatus(@Param("noCia") Integer noCia, @Param("status") String status);
    
    @Query("SELECT s FROM FacwebSolicitud s WHERE s.email = :email ORDER BY s.fechaRegistro DESC")
    List<FacwebSolicitud> findByEmailOrderByFechaRegistroDesc(@Param("email") String email);
    
    // Buscar solicitudes recientes (últimos 30 días)
    @Query("SELECT s FROM FacwebSolicitud s WHERE s.noCia = :noCia AND s.fechaRegistro >= CURRENT_DATE - 30 ORDER BY s.fechaRegistro DESC")
    List<FacwebSolicitud> findSolicitudesRecientes(@Param("noCia") Integer noCia);
}
