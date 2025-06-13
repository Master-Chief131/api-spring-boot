package com.example.demo.controller;

import com.example.demo.entity.PrefacturaRequest;
import com.example.demo.entity.CotizacionRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

// Imports para OpenAPI/Swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.Map;

@RestController
@RequestMapping("/api/prefactura-db")
@Tag(name = "Prefacturas", description = "API para gestión de prefacturas y documentos de salida")
public class PrefacturaController {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ObjectMapper objectMapper;    @PostMapping
    @Operation(
        summary = "Registrar nueva prefactura",
        description = "Registra una nueva prefactura o documento de salida en el sistema mediante procedimiento almacenado. " +
                      "Procesa tanto la cabecera como el detalle de líneas de la prefactura."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Prefactura registrada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de la prefactura inválidos o incompletos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor durante el proceso de registro")
    })
    public ResponseEntity<?> registrarPrefactura(
        @Parameter(description = "Datos completos de la prefactura incluyendo cabecera y detalle de líneas", 
                   required = true)
        @RequestBody PrefacturaRequest request) {
        String sql = "{call SALIDA_PREFACTURA(\n"
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,\n"   // 1-10
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,\n"   // 11-20
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,\n"   // 21-30
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,\n"   // 31-40
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,\n"   // 41-50
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,\n"   // 51-60
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,\n"   // 61-70
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,\n"   // 71-80
                + "?, ?, ?, ?, ?, ?)}";             // 81-86
        try (Connection conn = dataSource.getConnection()) {
            for (PrefacturaRequest.PrefacturaDetalle detalle : request.getDetalle()) {
                try (CallableStatement stmt = conn.prepareCall(sql)) {
                    int idx = 1;
                    // CABECERA
                    stmt.setString(idx++, request.getNoCia());
                    stmt.setString(idx++, request.getNoSucursal());
                    stmt.setString(idx++, request.getNoBodega());
                    stmt.setString(idx++, request.getNoPrefactura());
                    stmt.setString(idx++, request.getFecha());
                    stmt.setString(idx++, request.getXnoVendedor());
                    stmt.setString(idx++, request.getCliente());
                    stmt.setString(idx++, request.getTipoFactura());
                    stmt.setBigDecimal(idx++, null); // pporcentaje_descuento
                    stmt.setBigDecimal(idx++, null); // pdescuento_nominal
                    stmt.setBigDecimal(idx++, null); // psubtotal_nominal
                    stmt.setBigDecimal(idx++, null); // pimpuesto_nominal
                    stmt.setBigDecimal(idx++, null); // ptotal_nominal
                    stmt.setBigDecimal(idx++, null); // pdescuento_dolar
                    stmt.setBigDecimal(idx++, null); // psubtotal_dolar
                    stmt.setBigDecimal(idx++, null); // pimpuesto_dolar
                    stmt.setBigDecimal(idx++, null); // ptotal_dolar
                    stmt.setString(idx++, null); // ptipo_cambio
                    stmt.setString(idx++, null); // pmoneda
                    stmt.setString(idx++, null); // pfecha_cambio
                    stmt.setString(idx++, null); // pusuario
                    stmt.setString(idx++, null); // pfecha_creacion
                    stmt.setString(idx++, null); // pestatus
                    stmt.setString(idx++, request.getObservacion());
                    // DETALLE (por cada línea)
                    stmt.registerOutParameter(idx, java.sql.Types.VARCHAR); // plinea_l (INOUT)
                    stmt.setString(idx++, detalle.getCodigo()); // pno_articulo_l
                    stmt.setBigDecimal(idx++, java.math.BigDecimal.valueOf(detalle.getCantidad())); // pcantidad_l
                    stmt.setString(idx++, null); // pcod_barra_l
                    stmt.setString(idx++, null); // pdescripcion_l
                    stmt.setBigDecimal(idx++, null); // pprecio_l
                    stmt.setBigDecimal(idx++, null); // pprecio_dolar_l
                    stmt.setBigDecimal(idx++, null); // pporcentaje_descuento_l
                    stmt.setBigDecimal(idx++, null); // psubtotal_l
                    stmt.setBigDecimal(idx++, null); // psubtotal_dolar_l
                    stmt.setBigDecimal(idx++, null); // pdescuento_l
                    stmt.setBigDecimal(idx++, null); // pdescuento_dolar_l
                    stmt.setBigDecimal(idx++, null); // pimpuesto_nominal_l
                    stmt.setBigDecimal(idx++, null); // pimpuesto_dolar_l
                    stmt.setBigDecimal(idx++, null); // ptotal_l
                    stmt.setBigDecimal(idx++, null); // ptotal_dolar_l
                    stmt.setBigDecimal(idx++, null); // pporcentaje_impuesto_l
                    stmt.setString(idx++, null); // pno_unidad_l
                    stmt.setBigDecimal(idx++, null); // pcosto_promedio_l
                    stmt.setBigDecimal(idx++, null); // ppedido_mts_x_caja_l
                    stmt.setString(idx++, request.getFetipoVenta()); // ptipo_venta
                    stmt.setString(idx++, null); // pfecha_vencimiento_lote_l
                    stmt.setString(idx++, null); // ptipo_descto_l
                    stmt.setString(idx++, request.getNoSucursalV()); // pno_sucursal_venta
                    stmt.setString(idx++, null); // pserie_l
                    stmt.setString(idx++, null); // pno_impuesto_l
                    stmt.setString(idx++, null); // pexcento_l
                    stmt.setBigDecimal(idx++, null); // pprecio_nuevo_l
                    stmt.setBigDecimal(idx++, null); // pprecio_nuevo_dolar_l
                    stmt.setString(idx++, null); // pno_bodega_l
                    stmt.setString(idx++, null); // piliminar
                    stmt.registerOutParameter(idx, java.sql.Types.INTEGER); // v_secuencial (OUT)
                    idx++;
                    stmt.setString(idx++, request.getNoOrdenCompra()); // pno_orden_compra
                    stmt.setString(idx++, null); // ptipo_articulo_l
                    stmt.setString(idx++, null); // pno_solicitud
                    stmt.setString(idx++, request.getNoPlazo()); // pno_plazo
                    stmt.setString(idx++, null); // pno_sucursal_l
                    stmt.setString(idx++, null); // pplaca_l
                    stmt.setString(idx++, null); // pno_cotizacion
                    stmt.setString(idx++, null); // pno_proveedor
                    stmt.setString(idx++, null); // pno_clase
                    stmt.setBigDecimal(idx++, null); // pcia_oferta
                    stmt.setBigDecimal(idx++, null); // pprove_oferta
                    stmt.setBigDecimal(idx++, null); // pporc_desc_cia
                    stmt.setBigDecimal(idx++, null); // pporc_desc_prove
                    stmt.setString(idx++, null); // ptipo_oferta
                    stmt.setBigDecimal(idx++, null); // pcantidad_oferta
                    stmt.setString(idx++, null); // particulo_oferta
                    stmt.setString(idx++, null); // pcodigo_promo1
                    stmt.setString(idx++, null); // pcodigo_promo2
                    stmt.setString(idx++, null); // pno_unidad_eq
                    stmt.setString(idx++, null); // pind_desc_global
                    stmt.setDate(idx++, null); // pfecha_entrega
                    stmt.setInt(idx++, 0); // psucursal_cliente
                    stmt.setString(idx++, request.getIndLocalidad()); // pind_localidad
                    stmt.setInt(idx++, 0); // pno_grupo_mercado
                    stmt.setString(idx++, null); // pgrupo_cliente
                    stmt.setString(idx++, request.getNomCliente()); // pnom_cliente
                    stmt.setString(idx++, request.getRucCedula()); // pruc_cedula
                    stmt.setString(idx++, request.getEmail()); // pemail
                    stmt.setInt(idx++, 0); // pno_referido
                    stmt.setString(idx++, null); // pnombre_referido
                    stmt.execute();
                }
            }
            return ResponseEntity.ok("Prefactura registrada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al registrar prefactura: " + e.getMessage());
        }
    }

    @PostMapping("/cotizacion")
    @Operation(
        summary = "Registrar nueva cotización",
        description = "Registra una nueva cotización mediante el procedimiento almacenado SOLI_ORDEN_COTIZACION. " +
                      "Procesa tanto la cabecera como el detalle de líneas de la cotización."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cotización registrada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de la cotización inválidos o incompletos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor durante el proceso de registro")
    })
    public ResponseEntity<?> registrarCotizacion(
        @Parameter(description = "Datos completos de la cotización incluyendo cabecera y detalle de líneas", 
                   required = true)
        @RequestBody CotizacionRequest request) {
        
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            
            String sql = "{call SOLI_ORDEN_COTIZACION(?,?,?,?,?,?,?,?,?,?," +
                        "?,?,?,?,?,?,?,?,?,?," +
                        "?,?,?,?,?,?,?,?,?,?," +
                        "?,?,?,?,?,?,?,?,?,?," +
                        "?,?,?,?,?,?,?,?,?,?," +
                        "?,?,?,?,?,?,?,?,?,?," +
                        "?,?,?,?,?,?,?,?,?,?," +
                        "?,?,?,?,?,?)}";
            
            String mensajeRespuesta = "";
            String noSolicitudGenerada = request.getNoSolicitud();
            
            for (CotizacionRequest.CotizacionDetalle detalle : request.getDetalle()) {
                try (CallableStatement stmt = conn.prepareCall(sql)) {
                    int idx = 1;
                    
                    // Parámetros de cabecera (1-24)
                    stmt.setString(idx++, request.getNoCia());
                    stmt.setString(idx++, request.getNoSucursal());
                    stmt.setString(idx++, request.getNoBodega());
                    stmt.setString(idx++, request.getNoSolicitud());
                    stmt.setString(idx++, request.getFecha());
                    stmt.setString(idx++, request.getNoVendedor());
                    stmt.setString(idx++, request.getNoCliente());
                    stmt.setString(idx++, request.getTipoFactura());
                    stmt.setBigDecimal(idx++, request.getPorcentajeDescuento());
                    stmt.setBigDecimal(idx++, request.getDescuentoNominal());
                    stmt.setBigDecimal(idx++, request.getSubtotalNominal());
                    stmt.setBigDecimal(idx++, request.getImpuestoNominal());
                    stmt.setBigDecimal(idx++, request.getTotalNominal());
                    stmt.setBigDecimal(idx++, request.getDescuentoDolar());
                    stmt.setBigDecimal(idx++, request.getSubtotalDolar());
                    stmt.setBigDecimal(idx++, request.getImpuestoDolar());
                    stmt.setBigDecimal(idx++, request.getTotalDolar());
                    stmt.setString(idx++, request.getTipoCambio());
                    stmt.setString(idx++, request.getMoneda());
                    stmt.setString(idx++, request.getFechaCambio());
                    stmt.setString(idx++, request.getUsuario());
                    stmt.setString(idx++, null); // fecha_creacion
                    stmt.setString(idx++, request.getEstatus());
                    stmt.setString(idx++, request.getObservacion());
                    
                    // Parámetros de detalle por línea (25-54)
                    stmt.setString(idx++, detalle.getLinea());
                    stmt.setString(idx++, detalle.getNoArticulo());
                    stmt.setString(idx++, detalle.getCantidad());
                    stmt.setString(idx++, detalle.getCodBarra());
                    stmt.setString(idx++, detalle.getDescripcion());
                    stmt.setBigDecimal(idx++, detalle.getPrecio());
                    stmt.setBigDecimal(idx++, detalle.getPrecioDolar());
                    stmt.setBigDecimal(idx++, detalle.getPorcentajeDescuento());
                    stmt.setBigDecimal(idx++, detalle.getSubtotal());
                    stmt.setBigDecimal(idx++, detalle.getSubtotalDolar());
                    stmt.setBigDecimal(idx++, detalle.getDescuento());
                    stmt.setBigDecimal(idx++, detalle.getDescuentoDolar());
                    stmt.setBigDecimal(idx++, detalle.getImpuestoNominal());
                    stmt.setBigDecimal(idx++, detalle.getImpuestoDolar());
                    stmt.setBigDecimal(idx++, detalle.getTotal());
                    stmt.setBigDecimal(idx++, detalle.getTotalDolar());
                    stmt.setBigDecimal(idx++, detalle.getPorcentajeImpuesto());
                    stmt.setString(idx++, detalle.getNoUnidad());
                    stmt.setBigDecimal(idx++, detalle.getCostoPromedio());
                    stmt.setBigDecimal(idx++, detalle.getMtsXCaja());
                    stmt.setDate(idx++, request.getFechaEntrega());
                    stmt.setString(idx++, request.getNomCliente());
                    stmt.setInt(idx++, request.getTipoVenta() != null ? request.getTipoVenta() : 0);
                    stmt.setString(idx++, request.getNoSucursalV());
                    stmt.setString(idx++, detalle.getSerie());
                    stmt.setString(idx++, detalle.getNoImpuesto());
                    stmt.setString(idx++, detalle.getExcento());
                    stmt.setBigDecimal(idx++, detalle.getPrecioNuevo());
                    stmt.setBigDecimal(idx++, detalle.getPrecioNuevoDolar());
                    stmt.setString(idx++, detalle.getNoBodega());
                    
                    // Parámetros adicionales (55-86)
                    stmt.setString(idx++, request.getPiliminar());
                    stmt.registerOutParameter(idx++, Types.INTEGER); // v_secuencial (OUT) - posición 56
                    stmt.setString(idx++, request.getNoOrdenCompra());
                    stmt.setString(idx++, detalle.getTipoArticulo());
                    stmt.setString(idx++, request.getAlias());
                    stmt.setString(idx++, detalle.getNoSucursal());
                    stmt.setString(idx++, ""); // ind_carga
                    stmt.setString(idx++, null); // cantidad_carga
                    stmt.setString(idx++, request.getNoProveedor());
                    stmt.setString(idx++, request.getNoClase());
                    stmt.setString(idx++, request.getCiaOferta());
                    stmt.setString(idx++, request.getProveOferta());
                    stmt.setBigDecimal(idx++, request.getPorcDescCia());
                    stmt.setBigDecimal(idx++, request.getPorcDescProve());
                    stmt.setString(idx++, request.getTipoOferta());
                    stmt.setString(idx++, request.getCantOfe());
                    stmt.setString(idx++, request.getArtiOfe());
                    stmt.setString(idx++, request.getCodigoPromo1());
                    stmt.setString(idx++, request.getCodigoPromo2());
                    stmt.setString(idx++, detalle.getCantidadEq());
                    stmt.setString(idx++, detalle.getNoUnidadEq());
                    stmt.setString(idx++, "N"); // parámetro fijo
                    stmt.setString(idx++, request.getOrigenSolicitud());
                    stmt.setBigDecimal(idx++, request.getSucursalCliente());
                    stmt.setObject(idx++, detalle.getNoGrupoMercado(), Types.INTEGER);
                    stmt.setString(idx++, request.getNoPlazo());
                    stmt.setString(idx++, request.getIndLocalidad());
                    stmt.setString(idx++, request.getGrupoCliente());
                    stmt.setString(idx++, request.getEmail());
                    stmt.setObject(idx++, request.getNoReferido(), Types.INTEGER);
                    stmt.setString(idx++, request.getNomReferido());
                    stmt.setString(idx++, request.getRucCedula());
                    
                    stmt.execute();
                    
                    // Obtener el número de solicitud generado si es nuevo
                    if (request.getNoSolicitud() == null || request.getNoSolicitud().isEmpty()) {
                        int secuencial = stmt.getInt(56);
                        noSolicitudGenerada = String.valueOf(secuencial);
                        request.setNoSolicitud(noSolicitudGenerada);
                    }
                }
            }
            
            conn.commit();
            mensajeRespuesta = "PROCESO REALIZADO CORRECTAMENTE";
            
            if (noSolicitudGenerada != null && !noSolicitudGenerada.isEmpty()) {
                mensajeRespuesta += " SOLICITUD DE PEDIDO NO. " + noSolicitudGenerada;
            }
            
            return ResponseEntity.ok().body(Map.of(
                "mensaje", mensajeRespuesta,
                "noSolicitud", noSolicitudGenerada,
                "estado", "exitoso"
            ));
            
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                "mensaje", "Error al registrar cotización: " + e.getMessage(),
                "estado", "error"
            ));
        }
    }
}
