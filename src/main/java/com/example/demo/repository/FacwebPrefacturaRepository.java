package com.example.demo.repository;

import com.example.demo.entity.FacwebPrefactura;
import com.example.demo.entity.PrefacturaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface FacwebPrefacturaRepository extends JpaRepository<FacwebPrefactura, PrefacturaId> {
    
    @Query("SELECT p FROM FacwebPrefactura p WHERE p.noCliente = :noCliente ORDER BY p.fechaRegistro DESC")
    List<FacwebPrefactura> findByNoClienteOrderByFechaRegistroDesc(@Param("noCliente") Integer noCliente);
    
    @Query("SELECT p FROM FacwebPrefactura p WHERE p.email = :email ORDER BY p.fechaRegistro DESC")
    List<FacwebPrefactura> findByEmailOrderByFechaRegistroDesc(@Param("email") String email);
    
    @Query("SELECT p FROM FacwebPrefactura p WHERE p.noCia = :noCia AND p.status = :status ORDER BY p.fechaRegistro DESC")
    List<FacwebPrefactura> findByNoCiaAndStatusOrderByFechaRegistroDesc(@Param("noCia") Integer noCia, @Param("status") String status);
    
    @Query("SELECT p FROM FacwebPrefactura p WHERE p.estadoDespacho = :estadoDespacho ORDER BY p.fechaRegistro DESC")
    List<FacwebPrefactura> findByEstadoDespachoOrderByFechaRegistroDesc(@Param("estadoDespacho") String estadoDespacho);
    
    @Query("SELECT p FROM FacwebPrefactura p WHERE p.noSolicitud = :noSolicitud")
    List<FacwebPrefactura> findByNoSolicitud(@Param("noSolicitud") Integer noSolicitud);
    
    @Query("SELECT p FROM FacwebPrefactura p WHERE p.rucCedula = :rucCedula ORDER BY p.fechaRegistro DESC")
    List<FacwebPrefactura> findByRucCedulaOrderByFechaRegistroDesc(@Param("rucCedula") String rucCedula);
    
    @Query("SELECT p FROM FacwebPrefactura p WHERE p.noPrefactura = :noPrefactura")
    List<FacwebPrefactura> findByNoPrefactura(@Param("noPrefactura") Integer noPrefactura);
    
    @Query("SELECT p FROM FacwebPrefactura p WHERE p.noCia = :noCia AND p.noPrefactura = :noPrefactura")
    List<FacwebPrefactura> findByNoCiaAndNoPrefactura(@Param("noCia") Integer noCia, @Param("noPrefactura") Integer noPrefactura);
}
