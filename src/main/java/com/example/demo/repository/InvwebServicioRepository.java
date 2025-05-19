package com.example.demo.repository;

import com.example.demo.entity.InvwebServicio;
import com.example.demo.entity.ServicioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvwebServicioRepository extends JpaRepository<InvwebServicio, ServicioId> {
    List<InvwebServicio> findByActivo(String activo);

    @Query("SELECT s FROM InvwebServicio s WHERE s.activo = :activo AND s.noFamilia IN (SELECT f.noFamilia FROM InvwebFamilia f WHERE f.noCia = s.noCia AND f.verPortal = 'S')")
    java.util.List<InvwebServicio> findActivosConFamiliaVerPortal(@Param("activo") String activo);
}
