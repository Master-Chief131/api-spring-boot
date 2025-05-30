package com.example.demo.controller;

import com.example.demo.entity.PrefacturaRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

@RestController
@RequestMapping("/api/prefactura-db")
public class PrefacturaController {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<?> registrarPrefactura(@RequestBody PrefacturaRequest request) {
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
}
