package com.example.demo.repository;

import com.example.demo.entity.FacwebSolicitud;
import com.example.demo.entity.SolicitudId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface FacwebSolicitudRepository extends JpaRepository<FacwebSolicitud, SolicitudId> {
    
    @Query("SELECT s FROM FacwebSolicitud s WHERE s.noCliente = :noCliente ORDER BY s.noSolicitud DESC")
    List<FacwebSolicitud> findByNoClienteOrderByNoSolicitudDesc(@Param("noCliente") Integer noCliente);
    
    @Query("SELECT s FROM FacwebSolicitud s WHERE s.noCia = :noCia AND s.status = :status")
    List<FacwebSolicitud> findByNoCiaAndStatus(@Param("noCia") Integer noCia, @Param("status") String status);
    
    @Query("SELECT s FROM FacwebSolicitud s WHERE s.email = :email ORDER BY s.fechaRegistro DESC")
    List<FacwebSolicitud> findByEmailOrderByFechaRegistroDesc(@Param("email") String email);
}
