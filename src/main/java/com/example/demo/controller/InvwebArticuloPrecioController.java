package com.example.demo.controller;

import com.example.demo.entity.InvwebArticulo;
import com.example.demo.repository.InvwebArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ListaPrecioPage getListaPrecios(
            @RequestParam(required = false) Integer noCia,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        String sql = "SELECT d.* FROM facweb_descuento d " +
                "JOIN facweb_grupo_mercado gm ON d.no_cia = gm.no_cia AND d.no_grupo = gm.no_grupo " +
                "WHERE gm.ind_portal = 'S' ";
        String countSql = "SELECT COUNT(*) FROM facweb_descuento d JOIN facweb_grupo_mercado gm ON d.no_cia = gm.no_cia AND d.no_grupo = gm.no_grupo WHERE gm.ind_portal = 'S'";
        if (noCia != null) {
            sql += " AND d.no_cia = " + noCia + " ";
            countSql += " AND d.no_cia = " + noCia + " ";
        }
        int offset = page * size;
        sql += " LIMIT " + size + " OFFSET " + offset;
        Integer totalObj = jdbcTemplate.queryForObject(countSql, Integer.class);
        int total = totalObj != null ? totalObj : 0;
        List<ListaPrecioDTO> content = jdbcTemplate.query(sql, new ListaPrecioRowMapper());
        return new ListaPrecioPage(content, page, size, total);
    }

    public static class ListaPrecioDTO {
        public Integer noCia;
        public Integer noGrupo;
        public String noArticulo;
        public java.math.BigDecimal precioVenta;
        public java.math.BigDecimal precioBase;
        public java.math.BigDecimal porcentaje;
        public java.sql.Date limiteOferta;
        public String tipo;
        public String usuarioCrea;
        public java.util.Date fechaCrea;
        public String usuarioMod;
        public java.util.Date fechaMod;
        public String estadoListaPrecio;
        public java.math.BigDecimal precioBaseLista;
        public java.math.BigDecimal precioVentaLista;
        public String usuarioListaMod;
        public java.math.BigDecimal precioBaseAnt;
        public java.math.BigDecimal precioVentaAnt;
        public String usuarioListaApr;
        public java.math.BigDecimal porcentajeAnt;
    }

    public static class ListaPrecioRowMapper implements RowMapper<ListaPrecioDTO> {
        @Override
        public ListaPrecioDTO mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            ListaPrecioDTO dto = new ListaPrecioDTO();
            dto.noCia = rs.getInt("no_cia");
            dto.noGrupo = rs.getInt("no_grupo");
            dto.noArticulo = rs.getString("No_articulo");
            dto.precioVenta = rs.getBigDecimal("Precio_Venta");
            dto.precioBase = rs.getBigDecimal("Precio_Base");
            dto.porcentaje = rs.getBigDecimal("Porcentaje");
            dto.limiteOferta = rs.getDate("Limite_Oferta");
            dto.tipo = rs.getString("tipo");
            dto.usuarioCrea = rs.getString("usuario_crea");
            dto.fechaCrea = rs.getTimestamp("fecha_crea");
            dto.usuarioMod = rs.getString("usuario_mod");
            dto.fechaMod = rs.getTimestamp("fecha_mod");
            dto.estadoListaPrecio = rs.getString("estado_lista_precio");
            dto.precioBaseLista = rs.getBigDecimal("precio_base_lista");
            dto.precioVentaLista = rs.getBigDecimal("precio_venta_lista");
            dto.usuarioListaMod = rs.getString("usuario_lista_mod");
            dto.precioBaseAnt = rs.getBigDecimal("precio_base_ant");
            dto.precioVentaAnt = rs.getBigDecimal("precio_venta_ant");
            dto.usuarioListaApr = rs.getString("usuario_lista_apr");
            dto.porcentajeAnt = rs.getBigDecimal("porcentaje_ant");
            return dto;
        }
    }

    public static class ListaPrecioPage {
        public List<ListaPrecioDTO> content;
        public int page;
        public int size;
        public int totalElements;
        public int totalPages;
        public ListaPrecioPage(List<ListaPrecioDTO> content, int page, int size, int totalElements) {
            this.content = content;
            this.page = page;
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = (int) Math.ceil((double) totalElements / size);
        }
    }
}
