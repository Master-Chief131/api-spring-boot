package com.example.demo.repository;

import com.example.demo.entity.InvwebExistencia;
import com.example.demo.entity.InvwebExistenciaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvwebExistenciaRepository extends JpaRepository<InvwebExistencia, InvwebExistenciaId> {
    @Query("SELECT e FROM InvwebExistencia e WHERE EXISTS (SELECT 1 FROM InvwebBodega b WHERE b.noCia = e.noCia AND b.noSucursal = e.noSucursal AND b.noBodega = e.noBodega AND b.verPortal = 'S')")
    Page<InvwebExistencia> findExistenciasConBodegaVerPortal(Pageable pageable);
}
