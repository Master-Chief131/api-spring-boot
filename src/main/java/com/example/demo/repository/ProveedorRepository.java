package com.example.demo.repository;

import com.example.demo.entity.Proveedor;
import com.example.demo.entity.ProveedorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, ProveedorId>, JpaSpecificationExecutor<Proveedor> {

    // Buscar por compañía y estado activo
    List<Proveedor> findByNoCiaAndActivo(Integer noCia, String activo);

    // Buscar por compañía y tipo
    List<Proveedor> findByNoCiaAndTipo(Integer noCia, Integer tipo);

    // Buscar por compañía y clase
    List<Proveedor> findByNoCiaAndNoClase(Integer noCia, Integer noClase);

    // Buscar por compañía y nacionalidad
    List<Proveedor> findByNoCiaAndIndNacional(Integer noCia, String indNacional);

    // Buscar por compañía y si acepta retenciones
    List<Proveedor> findByNoCiaAndIndAceptaRetenciones(Integer noCia, String indAceptaRetenciones);

    // Buscar por compañía y si es proveedor de compañía
    List<Proveedor> findByNoCiaAndEsProveCia(Integer noCia, String esProveCia);

    // Buscar por cédula/RUC
    List<Proveedor> findByNoCiaAndCedulaRuc(Integer noCia, String cedulaRuc);

    // Buscar por email
    List<Proveedor> findByNoCiaAndEmail(Integer noCia, String email);

    // Query personalizada para buscar proveedores con saldo mayor a cero
    @Query("SELECT p FROM Proveedor p WHERE p.noCia = :noCia AND p.saldo > 0")
    List<Proveedor> findProveedoresConSaldo(@Param("noCia") Integer noCia);

    // Query personalizada para buscar proveedores por rango de plazo de pago
    @Query("SELECT p FROM Proveedor p WHERE p.noCia = :noCia AND p.plazoPago BETWEEN :plazoMin AND :plazoMax")
    List<Proveedor> findByPlazoPagoRange(@Param("noCia") Integer noCia, 
                                        @Param("plazoMin") Integer plazoMin, 
                                        @Param("plazoMax") Integer plazoMax);

    // Query personalizada para buscar proveedores activos por texto en nombre
    @Query("SELECT p FROM Proveedor p WHERE p.noCia = :noCia AND p.activo = 'S' AND " +
           "(LOWER(p.nombre) LIKE LOWER(CONCAT('%', :texto, '%')) OR " +
           "LOWER(p.nombreLargo) LIKE LOWER(CONCAT('%', :texto, '%')) OR " +
           "LOWER(p.encargado) LIKE LOWER(CONCAT('%', :texto, '%')))")
    List<Proveedor> findProveedoresActivosPorTexto(@Param("noCia") Integer noCia, @Param("texto") String texto);

    // Query para estadísticas - contar proveedores activos
    @Query("SELECT COUNT(p) FROM Proveedor p WHERE p.noCia = :noCia AND p.activo = 'S'")
    Long countProveedoresActivos(@Param("noCia") Integer noCia);

    // Query para estadísticas - contar proveedores por tipo
    @Query("SELECT COUNT(p) FROM Proveedor p WHERE p.noCia = :noCia AND p.tipo = :tipo")
    Long countProveedoresPorTipo(@Param("noCia") Integer noCia, @Param("tipo") Integer tipo);

    // Query para obtener proveedores con compras recientes (últimos 90 días)
    @Query("SELECT p FROM Proveedor p WHERE p.noCia = :noCia AND p.fechaUltimaCompra >= CURRENT_DATE - 90")
    List<Proveedor> findProveedoresComprasRecientes(@Param("noCia") Integer noCia);

    // Query para obtener proveedores por país
    List<Proveedor> findByNoCiaAndPais(Integer noCia, Integer pais);

    // Query para obtener proveedores por condición tributaria
    List<Proveedor> findByNoCiaAndCondicionTributaria(Integer noCia, String condicionTributaria);
}
