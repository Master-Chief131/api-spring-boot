package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/lista-precios")
public class InvwebArticuloPrecioController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<ListaPrecioDTO> getListaPrecios() {
        String sql = "SELECT a.no_articulo, a.nombre_largo, ma.nombre AS marca, fa.nombre AS familia, " +
                "d.precio_base, d.precio_venta, d.porcentaje, d.no_grupo " +
                "FROM invweb_articulo a " +
                "LEFT JOIN facweb_descuento d ON a.no_cia = d.no_cia AND a.no_articulo = d.no_articulo AND d.tipo = 'A' " +
                "LEFT JOIN invweb_marca ma ON ma.no_cia = a.no_cia AND ma.no_marca = a.no_marca " +
                "LEFT JOIN invweb_familia fa ON fa.no_cia = a.no_cia AND fa.no_familia = a.no_familia ";
        return jdbcTemplate.query(sql, new ListaPrecioRowMapper());
    }

    public static class ListaPrecioDTO {
        public String noArticulo;
        public String nombreLargo;
        public String marca;
        public String familia;
        public java.math.BigDecimal precioBase;
        public java.math.BigDecimal precioVenta;
        public java.math.BigDecimal porcentaje;
        public Integer noGrupo;
    }

    public static class ListaPrecioRowMapper implements RowMapper<ListaPrecioDTO> {
        @Override
        public ListaPrecioDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            ListaPrecioDTO dto = new ListaPrecioDTO();
            dto.noArticulo = rs.getString("no_articulo");
            dto.nombreLargo = rs.getString("nombre_largo");
            dto.marca = rs.getString("marca");
            dto.familia = rs.getString("familia");
            dto.precioBase = rs.getBigDecimal("precio_base");
            dto.precioVenta = rs.getBigDecimal("precio_venta");
            dto.porcentaje = rs.getBigDecimal("porcentaje");
            dto.noGrupo = rs.getObject("no_grupo") != null ? rs.getInt("no_grupo") : null;
            return dto;
        }
    }
}
