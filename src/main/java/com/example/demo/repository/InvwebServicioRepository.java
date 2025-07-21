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

    @Query(value = "SELECT a.no_servicio as noArticulo, a.descripcion as descripcion, " +
                   "fa.nombre as familia, " +
                   "sf.nombre as subFamilia, " +
                   "um.nombre as unidad, " +
                   "IFNULL(a.costo,0) as costo, " +
                   "d.no_pais as noPais, pa.nombre as nombrePais, " +
                   "d.no_provincia as noProvincia, pr.nombre as nombreProvincia, " +
                   "d.no_distrito as noDistrito, ds.nombre as nombreDistrito, " +
                   "d.no_corregimiento as noCorregimiento, cg.nombre as nombreCorregimiento, " +
                   "d.no_grupo as noGrupo, IFNULL(d.tipo,'S') as tipo, " +
                   "CASE WHEN IFNULL(d.precio_base,a.precio_venta) = 0 THEN IFNULL(a.precio_venta,0) ELSE IFNULL(d.precio_base,a.precio_venta) END as precioBase, " +
                   "d.porcentaje as porcentaje, " +
                   "CASE WHEN IFNULL(d.precio_venta,a.precio_venta) = 0 THEN IFNULL(a.precio_venta,0) ELSE IFNULL(d.precio_venta,a.precio_venta) END as precioVenta, " +
                   "CASE WHEN d.usuario_mod IS NULL THEN d.usuario_crea ELSE d.usuario_mod END as usuarioMod, " +
                   "CASE WHEN d.fecha_mod IS NULL THEN d.fecha_crea ELSE d.fecha_mod END as fechaMod, " +
                   "d.estado_lista_precio as estadoListaPrecio, " +
                   "d.precio_base_lista as precioBaseLista, " +
                   "d.precio_venta_lista as precioVentaLista, " +
                   "d.usuario_lista_mod as usuarioListaMod, " +
                   "CASE WHEN d.no_grupo IS NULL THEN 'I' ELSE 'U' END as indicadorAct " +
                   "FROM invweb_servicio a " +
                   "INNER JOIN invweb_familia fa ON fa.no_cia = a.no_cia AND fa.no_familia = a.no_familia AND fa.ver_portal = 'S' " +
                   "INNER JOIN invweb_subfamilia sf ON sf.no_cia = a.no_cia AND sf.no_familia = a.no_familia AND sf.no_subfamilia = a.no_sub_familia " +
                   "INNER JOIN invweb_unidad_medida um ON um.no_cia = a.no_cia AND um.no_unidad = a.no_unidad " +
                   "LEFT JOIN facweb_descuento d ON a.no_cia = d.no_cia AND a.no_servicio = d.No_articulo AND d.tipo = 'S' AND d.no_grupo = :noGrupo " +
                   "LEFT JOIN genweb_pais pa ON pa.no_cia = a.no_cia AND pa.no_pais = d.no_pais " +
                   "LEFT JOIN genweb_provincia pr ON pr.no_cia = a.no_cia AND pr.no_pais = d.no_pais AND pr.no_provincia = d.no_provincia " +
                   "LEFT JOIN genweb_distrito ds ON ds.no_cia = a.no_cia AND ds.no_pais = d.no_pais AND ds.no_provincia = d.no_provincia AND ds.no_distrito = d.no_distrito " +
                   "LEFT JOIN genweb_corregimiento cg ON cg.no_cia = a.no_cia AND cg.no_pais = d.no_pais AND cg.no_provincia = d.no_provincia AND cg.no_distrito = d.no_distrito AND cg.no_corregimiento = d.no_corregimiento " +
                   "WHERE a.no_cia = :noCia AND a.activo = 'S' " +
                   "ORDER BY a.no_servicio ASC", nativeQuery = true)
    List<Object[]> findServiciosConPrecioPortal(@Param("noCia") Integer noCia, @Param("noGrupo") Integer noGrupo);

    // Método para obtener TODOS los servicios con precios (sin filtro de ver_portal)
    @Query(value = "SELECT a.no_servicio as noArticulo, a.descripcion as descripcion, " +
                   "fa.nombre as familia, " +
                   "sf.nombre as subFamilia, " +
                   "um.nombre as unidad, " +
                   "IFNULL(a.costo,0) as costo, " +
                   "d.no_pais as noPais, pa.nombre as nombrePais, " +
                   "d.no_provincia as noProvincia, pr.nombre as nombreProvincia, " +
                   "d.no_distrito as noDistrito, ds.nombre as nombreDistrito, " +
                   "d.no_corregimiento as noCorregimiento, cg.nombre as nombreCorregimiento, " +
                   "d.no_grupo as noGrupo, IFNULL(d.tipo,'S') as tipo, " +
                   "CASE WHEN IFNULL(d.precio_base,a.precio_venta) = 0 THEN IFNULL(a.precio_venta,0) ELSE IFNULL(d.precio_base,a.precio_venta) END as precioBase, " +
                   "d.porcentaje as porcentaje, " +
                   "CASE WHEN IFNULL(d.precio_venta,a.precio_venta) = 0 THEN IFNULL(a.precio_venta,0) ELSE IFNULL(d.precio_venta,a.precio_venta) END as precioVenta, " +
                   "CASE WHEN d.usuario_mod IS NULL THEN d.usuario_crea ELSE d.usuario_mod END as usuarioMod, " +
                   "CASE WHEN d.fecha_mod IS NULL THEN d.fecha_crea ELSE d.fecha_mod END as fechaMod, " +
                   "d.estado_lista_precio as estadoListaPrecio, " +
                   "d.precio_base_lista as precioBaseLista, " +
                   "d.precio_venta_lista as precioVentaLista, " +
                   "d.usuario_lista_mod as usuarioListaMod, " +
                   "CASE WHEN d.no_grupo IS NULL THEN 'I' ELSE 'U' END as indicadorAct " +
                   "FROM invweb_servicio a " +
                   "INNER JOIN invweb_familia fa ON fa.no_cia = a.no_cia AND fa.no_familia = a.no_familia " +
                   "INNER JOIN invweb_subfamilia sf ON sf.no_cia = a.no_cia AND sf.no_familia = a.no_familia AND sf.no_subfamilia = a.no_sub_familia " +
                   "INNER JOIN invweb_unidad_medida um ON um.no_cia = a.no_cia AND um.no_unidad = a.no_unidad " +
                   "LEFT JOIN facweb_descuento d ON a.no_cia = d.no_cia AND a.no_servicio = d.No_articulo AND d.tipo = 'S' AND d.no_grupo = :noGrupo " +
                   "LEFT JOIN genweb_pais pa ON pa.no_cia = a.no_cia AND pa.no_pais = d.no_pais " +
                   "LEFT JOIN genweb_provincia pr ON pr.no_cia = a.no_cia AND pr.no_pais = d.no_pais AND pr.no_provincia = d.no_provincia " +
                   "LEFT JOIN genweb_distrito ds ON ds.no_cia = a.no_cia AND ds.no_pais = d.no_pais AND ds.no_provincia = d.no_provincia AND ds.no_distrito = d.no_distrito " +
                   "LEFT JOIN genweb_corregimiento cg ON cg.no_cia = a.no_cia AND cg.no_pais = d.no_pais AND cg.no_provincia = d.no_provincia AND cg.no_distrito = d.no_distrito AND cg.no_corregimiento = d.no_corregimiento " +
                   "WHERE a.no_cia = :noCia AND a.activo = 'S' " +
                   "ORDER BY a.no_servicio ASC", nativeQuery = true)
    List<Object[]> findTodosServiciosConPrecioPortal(@Param("noCia") Integer noCia, @Param("noGrupo") Integer noGrupo);
}
