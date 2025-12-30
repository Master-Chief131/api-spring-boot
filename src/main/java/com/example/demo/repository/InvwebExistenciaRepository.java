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

       @Query("SELECT e FROM InvwebExistencia e " +
                     "INNER JOIN InvwebArticulo a ON a.noCia = e.noCia AND a.noArticulo = e.noArticulo " +
                     "INNER JOIN InvwebFamilia f ON f.noCia = a.noCia AND f.noFamilia = a.noFamilia " +
                     "INNER JOIN InvwebSubfamilia sf ON sf.noCia = a.noCia AND sf.noFamilia = a.noFamilia AND sf.noSubfamilia = a.noSubFamilia "
                     +
                     "WHERE e.cantidad IS NOT NULL AND e.cantidad > 0 " +
                     "AND sf.activo = 'S' " +
                     "AND a.activo = 'S' " +
                     "AND a.verPortal != 'O' " +
                     "AND (a.verPortal = 'S' OR (a.verPortal = 'N' AND f.verPortal = 'S')) " +
                     "AND EXISTS (SELECT 1 FROM InvwebBodega b WHERE b.noCia = e.noCia AND b.noSucursal = e.noSucursal AND b.noBodega = e.noBodega AND b.verPortal = 'S')")
       Page<InvwebExistencia> findExistenciasConStockEnBodegasPortal(Pageable pageable);

       @Query("SELECT e FROM InvwebExistencia e " +
                     "INNER JOIN InvwebArticulo a ON a.noCia = e.noCia AND a.noArticulo = e.noArticulo " +
                     "INNER JOIN InvwebFamilia f ON f.noCia = a.noCia AND f.noFamilia = a.noFamilia " +
                     "INNER JOIN InvwebSubfamilia sf ON sf.noCia = a.noCia AND sf.noFamilia = a.noFamilia AND sf.noSubfamilia = a.noSubFamilia "
                     +
                     "WHERE e.cantidad IS NOT NULL AND e.cantidad > 0 " +
                     "AND sf.activo = 'S' " +
                     "AND a.activo = 'S' " +
                     "AND a.verPortal != 'O' " +
                     "AND (a.verPortal = 'S' OR (a.verPortal = 'N' AND f.verPortal = 'S')) " +
                     "AND EXISTS (SELECT 1 FROM InvwebBodega b WHERE b.noCia = e.noCia AND b.noSucursal = e.noSucursal AND b.noBodega = e.noBodega AND b.verPortal = 'S' AND b.noCia = :noCia)")
       Page<InvwebExistencia> findExistenciasConStockEnBodegasPortalPorCia(@Param("noCia") Integer noCia,
                     Pageable pageable);
}
