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
import java.sql.Date;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;

@RestController
@RequestMapping("/api/prefactura-db")
@Tag(name = "Prefacturas", description = "API para gestión de prefacturas y documentos de salida")
public class PrefacturaController {    @Autowired
    private DataSource dataSource;

    /**
     * Convierte una fecha de String a java.sql.Date
     * @param fechaString Fecha en formato "yyyy-MM-dd" o "dd/MM/yyyy"
     * @return java.sql.Date o null si la conversión falla
     */    private Date convertStringToSqlDate(String fechaString) {
        if (fechaString == null || fechaString.trim().isEmpty()) {
            return null;
        }
        
        try {
            SimpleDateFormat format;
            if (fechaString.contains("/")) {
                format = new SimpleDateFormat("dd/MM/yyyy");
            } else {
                format = new SimpleDateFormat("yyyy-MM-dd");
            }
            java.util.Date utilDate = format.parse(fechaString.trim());
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            // Si falla la conversión, intentar con el formato ISO
            try {
                return Date.valueOf(fechaString.trim());
            } catch (IllegalArgumentException ex) {
                System.err.println("Error al convertir fecha: " + fechaString + " - " + ex.getMessage());
                return null;
            }
        }
    }
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ObjectMapper objectMapper;    /* ENDPOINT DESHABILITADO - PREFACTURA MAL CONFIGURADA
    @PostMapping
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
    */

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
              Map<String, Object> response = new HashMap<>();
            response.put("mensaje", mensajeRespuesta);
            response.put("noSolicitud", noSolicitudGenerada);
            response.put("estado", "exitoso");
            return ResponseEntity.ok().body(response);        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Error al registrar cotización: " + e.getMessage());
            errorResponse.put("estado", "error");
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PostMapping("/cotizacion-api")
    @Operation(
        summary = "Registrar cotización usando API_COTIZACION",
        description = "Registra una nueva cotización mediante el procedimiento almacenado API_COTIZACION. " +
                      "Este procedimiento procesa tanto validaciones como la creación de la cotización."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cotización procesada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de la cotización inválidos o incompletos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor durante el proceso")
    })    public ResponseEntity<?> registrarCotizacionApi(
        @Parameter(description = "Datos de la cotización para el procedimiento API_COTIZACION", 
                   required = true)
        @RequestBody CotizacionRequest request) {
        
        System.out.println("=== INICIO ENDPOINT COTIZACION-API ===");
        
        try {
            // Validación básica del request
            if (request == null) {
                System.err.println("ERROR: Request es null");
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("mensaje", "Request no puede ser null");
                errorResponse.put("estado", "error");
                return ResponseEntity.status(400).body(errorResponse);
            }
            
            // Log de campos principales
            System.out.println("Request recibido:");
            System.out.println("- noCia: " + request.getNoCia());
            System.out.println("- noSucursal: " + request.getNoSucursal());
            System.out.println("- noBodega: " + request.getNoBodega());
            System.out.println("- noCliente: " + request.getNoCliente());
            System.out.println("- fecha: " + request.getFecha());
            
            // Validar detalle
            if (request.getDetalle() == null || request.getDetalle().isEmpty()) {
                System.err.println("ERROR: Detalle es null o está vacío");
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("mensaje", "El detalle de la cotización no puede estar vacío");
                errorResponse.put("estado", "error");
                return ResponseEntity.status(400).body(errorResponse);
            }
            
            System.out.println("- Número de líneas de detalle: " + request.getDetalle().size());
            
        } catch (Exception e) {
            System.err.println("ERROR al procesar request inicial: " + e.getMessage());
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Error al procesar datos de entrada: " + e.getMessage());
            errorResponse.put("estado", "error");
            return ResponseEntity.status(400).body(errorResponse);
        }
        
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);// Procedimiento API_COTIZACION con 50 parámetros (46 IN + 4 OUT)
            String sql = "{call API_COTIZACION(?,?,?,?,?,?,?,?,?,?," +  // 1-10
                        "?,?,?,?,?,?,?,?,?,?," +                        // 11-20
                        "?,?,?,?,?,?,?,?,?,?," +                        // 21-30
                        "?,?,?,?,?,?,?,?,?,?," +                        // 31-40
                        "?,?,?,?,?,?,?,?,?,?)}";                       // 41-50 (46 IN + 4 OUT)
            
            StringBuilder mensajeCompleto = new StringBuilder();
            String noSolicitudGenerada = null;
            String lineaGenerada = null;
            
            // Procesar cada línea del detalle
            for (CotizacionRequest.CotizacionDetalle detalle : request.getDetalle()) {
                try (CallableStatement stmt = conn.prepareCall(sql)) {
                    int idx = 1;
                      // DEBUG: Contador para verificar parámetros
                    System.out.println("=== INICIANDO CONFIGURACIÓN DE PARÁMETROS ===");
                    System.out.println("Procesando línea: " + detalle.getLinea());
                    
                    // Validaciones antes de los parseInt
                    try {
                        if (request.getNoCia() == null || request.getNoCia().trim().isEmpty()) {
                            throw new IllegalArgumentException("noCia no puede estar vacío");
                        }
                        if (request.getNoSucursal() == null || request.getNoSucursal().trim().isEmpty()) {
                            throw new IllegalArgumentException("noSucursal no puede estar vacío");
                        }
                        if (request.getNoBodega() == null || request.getNoBodega().trim().isEmpty()) {
                            throw new IllegalArgumentException("noBodega no puede estar vacío");
                        }
                        if (request.getNoCliente() == null || request.getNoCliente().trim().isEmpty()) {
                            throw new IllegalArgumentException("noCliente no puede estar vacío");
                        }
                        if (request.getNoVendedor() == null || request.getNoVendedor().trim().isEmpty()) {
                            throw new IllegalArgumentException("noVendedor no puede estar vacío");
                        }
                        if (detalle.getLinea() == null || detalle.getLinea().trim().isEmpty()) {
                            throw new IllegalArgumentException("linea de detalle no puede estar vacía");
                        }
                        if (detalle.getCantidad() == null || detalle.getCantidad().trim().isEmpty()) {
                            throw new IllegalArgumentException("cantidad no puede estar vacía");
                        }
                    } catch (IllegalArgumentException e) {
                        System.err.println("ERROR de validación: " + e.getMessage());
                        conn.rollback();
                        Map<String, Object> errorResponse = new HashMap<>();
                        errorResponse.put("mensaje", "Datos inválidos: " + e.getMessage());
                        errorResponse.put("estado", "error");
                        return ResponseEntity.status(400).body(errorResponse);
                    }
                    
                    // Parámetros IN (1-46)
                    stmt.setInt(idx++, Integer.parseInt(request.getNoCia()));                    // 1. p_no_cia
                    stmt.setInt(idx++, Integer.parseInt(request.getNoSucursal()));              // 2. p_no_sucursal
                    stmt.setInt(idx++, Integer.parseInt(request.getNoBodega()));                // 3. p_no_bodega
                    stmt.setString(idx++, detalle.getCodBarra());                               // 4. p_cod_barra
                    stmt.setBigDecimal(idx++, new BigDecimal(detalle.getCantidad()));          // 5. p_cantidad
                    stmt.setInt(idx++, Integer.parseInt(request.getNoCliente()));              // 6. p_no_cliente
                    stmt.setString(idx++, request.getTipoFactura());                           // 7. p_tipo_factura
                    stmt.setDate(idx++, convertStringToSqlDate(request.getFecha()));          // 8. p_fecha
                    stmt.setString(idx++, request.getMoneda());                                // 9. p_moneda
                    stmt.setString(idx++, request.getUsuario());                               // 10. p_usuario
                    
                    // Si es la primera línea o no hay solicitud, usar null para generar nueva
                    if (noSolicitudGenerada != null) {
                        stmt.setInt(idx++, Integer.parseInt(noSolicitudGenerada));             // 11. p_no_solicitud
                    } else if (request.getNoSolicitud() != null && !request.getNoSolicitud().isEmpty()) {
                        stmt.setInt(idx++, Integer.parseInt(request.getNoSolicitud()));       // 11. p_no_solicitud
                    } else {
                        stmt.setNull(idx++, Types.INTEGER);                                    // 11. p_no_solicitud
                    }
                    
                    stmt.setInt(idx++, request.getNoCotizacion() != null ? 
                        Integer.parseInt(request.getNoCotizacion()) : 0);                      // 12. p_no_cotizacion
                    stmt.setString(idx++, request.getOrigenProceso() != null ? 
                        request.getOrigenProceso() : "A");                                     // 13. p_origen_proceso
                    stmt.setInt(idx++, Integer.parseInt(request.getNoVendedor()));            // 14. p_no_vendedor
                    stmt.setBigDecimal(idx++, request.getPorcentajeDescuento());              // 15. p_porcentaje_descuento
                    stmt.setBigDecimal(idx++, request.getDescuentoNominal());                 // 16. p_descuento_nominal
                    stmt.setBigDecimal(idx++, request.getSubtotalNominal());                  // 17. p_subtotal_nominal
                    stmt.setBigDecimal(idx++, request.getImpuestoNominal());                  // 18. p_impuesto_nominal
                    stmt.setBigDecimal(idx++, request.getTotalNominal());                     // 19. p_total_nominal
                    stmt.setBigDecimal(idx++, request.getDescuentoDolar());                   // 20. p_descuento_dolar
                    stmt.setBigDecimal(idx++, request.getSubtotalDolar());                    // 21. p_subtotal_dolar
                    stmt.setBigDecimal(idx++, request.getImpuestoDolar());                    // 22. p_impuesto_dolar
                    stmt.setBigDecimal(idx++, request.getTotalDolar());                       // 23. p_total_dolar
                    stmt.setBigDecimal(idx++, new BigDecimal(request.getTipoCambio()));       // 24. p_tipo_cambio
                    stmt.setDate(idx++, convertStringToSqlDate(request.getFechaCambio()));  // 25. p_fecha_cambio
                    stmt.setString(idx++, request.getEstatus());                              // 26. p_estatus
                    stmt.setString(idx++, request.getObservacion());                          // 27. p_observacion
                    stmt.setInt(idx++, Integer.parseInt(detalle.getLinea()));                // 28. p_linea
                    stmt.setDate(idx++, request.getFechaEntrega());                           // 29. p_fecha_entrega
                    stmt.setString(idx++, request.getNomCliente());                           // 30. p_nom_cliente
                    stmt.setInt(idx++, request.getTipoVenta() != null ? 
                        request.getTipoVenta() : 1);                                          // 31. p_tipo_venta
                    stmt.setInt(idx++, Integer.parseInt(request.getNoSucursalV()));          // 32. p_no_sucursal_v
                    stmt.setString(idx++, detalle.getSerie() != null ? detalle.getSerie() : ""); // 33. p_serie_l
                    stmt.setString(idx++, request.getPiliminar() != null ? 
                        request.getPiliminar() : "N");                                        // 34. p_piliminar
                    stmt.setString(idx++, request.getNoOrdenCompra() != null ? 
                        request.getNoOrdenCompra() : "");                                     // 35. p_no_orden_compra
                    stmt.setString(idx++, request.getAlias() != null ? 
                        request.getAlias() : "");                                             // 36. p_alias
                    stmt.setInt(idx++, Integer.parseInt(detalle.getNoSucursal()));           // 37. p_no_sucursal_l
                    stmt.setString(idx++, request.getOrigenSolicitud() != null ? 
                        request.getOrigenSolicitud() : "WEB");                               // 38. p_origen_solicitud
                    stmt.setBigDecimal(idx++, request.getSucursalCliente() != null ? 
                        request.getSucursalCliente() : new BigDecimal("1"));                  // 39. p_sucursal_cliente
                    stmt.setInt(idx++, Integer.parseInt(request.getNoPlazo()));              // 40. p_no_plazo
                    stmt.setString(idx++, request.getIndLocalidad() != null ? 
                        request.getIndLocalidad() : "N");                                     // 41. p_ind_localidad
                    stmt.setInt(idx++, Integer.parseInt(request.getGrupoCliente()));         // 42. p_grupo_cliente
                    stmt.setString(idx++, request.getEmail() != null ? 
                        request.getEmail() : "");                                             // 43. p_email
                    stmt.setInt(idx++, request.getNoReferido() != null ? 
                        request.getNoReferido() : 0);                                         // 44. p_no_referido
                    stmt.setString(idx++, request.getNomReferido() != null ? 
                        request.getNomReferido() : "");                                       // 45. p_nom_referido
                    stmt.setString(idx++, request.getRucCedula() != null ? 
                        request.getRucCedula() : "");                                         // 46. p_ruc_cedula
                    
                    System.out.println("Total parámetros IN configurados: " + (idx - 1));
                    
                    // Parámetros de salida (47-50)
                    stmt.registerOutParameter(idx++, Types.INTEGER);                          // 47. p_resultado
                    stmt.registerOutParameter(idx++, Types.LONGVARCHAR);                     // 48. p_mensaje
                    stmt.registerOutParameter(idx++, Types.INTEGER);                          // 49. p_no_solicitud_generada
                    stmt.registerOutParameter(idx++, Types.INTEGER);                          // 50. p_linea_generada
                    
                    System.out.println("Total parámetros (IN + OUT): " + (idx - 1));
                    
                    // Ejecutar el procedimiento
                    stmt.execute();
                      // Obtener resultados
                    int resultado = stmt.getInt(47);
                    String mensaje = stmt.getString(48);
                    Integer solicitudGenerada = stmt.getInt(49);
                    Integer lineaGen = stmt.getInt(50);
                    
                    // DEBUG: Mostrar los resultados del procedimiento
                    System.out.println("=== RESULTADOS DEL PROCEDIMIENTO ===");
                    System.out.println("Resultado: " + resultado);
                    System.out.println("Mensaje: '" + mensaje + "'");
                    System.out.println("Solicitud generada: " + solicitudGenerada);
                    System.out.println("Línea generada: " + lineaGen);
                    System.out.println("Mensaje es null: " + (mensaje == null));
                    System.out.println("Mensaje está vacío: " + (mensaje != null && mensaje.isEmpty()));
                    System.out.println("======================================");
                    
                    // Almacenar el número de solicitud para las siguientes líneas
                    if (resultado == 1 && noSolicitudGenerada == null && solicitudGenerada != 0) {
                        noSolicitudGenerada = String.valueOf(solicitudGenerada);
                    }
                    
                    if (lineaGen != 0) {
                        lineaGenerada = String.valueOf(lineaGen);
                    }
                    
                    // Agregar mensaje de esta línea
                    mensajeCompleto.append("Línea ").append(detalle.getLinea()).append(": ");
                    if (resultado == 1) {
                        mensajeCompleto.append("OK - ").append(mensaje);                    } else {
                        String mensajeFinal = (mensaje != null && !mensaje.isEmpty()) ? mensaje : "Error sin descripción";
                        mensajeCompleto.append("ERROR - ").append(mensajeFinal);
                        // Si hay error, hacer rollback y retornar error
                        conn.rollback();
                        Map<String, Object> errorResponse = new HashMap<>();
                        errorResponse.put("mensaje", "Error en línea " + detalle.getLinea() + ": " + mensajeFinal);
                        errorResponse.put("estado", "error");
                        errorResponse.put("resultado", resultado);
                        errorResponse.put("mensajeOriginal", mensaje);
                        errorResponse.put("codigoArticulo", detalle.getCodBarra());
                        errorResponse.put("cantidad", detalle.getCantidad());
                        return ResponseEntity.status(400).body(errorResponse);
                    }
                    mensajeCompleto.append("\n");
                }
            }
              // Si llegamos aquí, todo fue exitoso
            conn.commit();
            
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("mensaje", "COTIZACIÓN PROCESADA EXITOSAMENTE");
            successResponse.put("detalleProceso", mensajeCompleto.toString());
            successResponse.put("noSolicitudGenerada", noSolicitudGenerada);
            successResponse.put("lineaGenerada", lineaGenerada);
            successResponse.put("estado", "exitoso");
            return ResponseEntity.ok().body(successResponse);        } catch (Exception e) {
            System.err.println("ERROR GENERAL en cotizacion-api: " + e.getMessage());
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Error al procesar cotización: " + e.getMessage());
            errorResponse.put("estado", "error");
            errorResponse.put("tipoError", e.getClass().getSimpleName());
            errorResponse.put("stackTrace", e.getStackTrace()[0].toString());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}
