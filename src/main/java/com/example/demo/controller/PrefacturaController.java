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
        String sql = "{call SP_REGISTRAR_PREFACTURA(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        // Ajusta la cantidad y orden de parámetros según tu SP
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            int idx = 1;
            stmt.setString(idx++, request.getTipoRegistro());
            stmt.setString(idx++, request.getFecha());
            stmt.setString(idx++, request.getNoCia());
            stmt.setString(idx++, request.getNoSucursalV());
            stmt.setString(idx++, request.getNoSucursal());
            stmt.setString(idx++, request.getNoBodega());
            stmt.setString(idx++, request.getNoPrefactura());
            stmt.setString(idx++, request.getInterCompania());
            stmt.setString(idx++, request.getCiaDestino());
            stmt.setString(idx++, request.getSucursalDestino());
            stmt.setString(idx++, request.getCliente());
            stmt.setString(idx++, request.getNomCliente());
            stmt.setString(idx++, request.getRucCedula());
            stmt.setString(idx++, request.getEmail());
            stmt.setString(idx++, request.getTipoFactura());
            stmt.setString(idx++, request.getNoPlazo());
            stmt.setString(idx++, request.getXreferido());
            stmt.setString(idx++, request.getXnoVendedor());
            stmt.setString(idx++, request.getFetipoVenta());
            stmt.setString(idx++, request.getNoOrdenCompra());
            stmt.setString(idx++, request.getIndLocalidad());
            stmt.setString(idx++, request.getFechaEntrega());
            stmt.setString(idx++, request.getObservacion());
            // Detalle como JSON
            String detalleJson = objectMapper.writeValueAsString(request.getDetalle());
            stmt.setString(idx++, detalleJson);
            // Si el SP retorna un valor, puedes registrar un parámetro OUT
            // stmt.registerOutParameter(idx, Types.VARCHAR);
            stmt.execute();
            // String resultado = stmt.getString(idx); // Si hay OUT
            return ResponseEntity.ok("Prefactura registrada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al registrar prefactura: " + e.getMessage());
        }
    }
}
