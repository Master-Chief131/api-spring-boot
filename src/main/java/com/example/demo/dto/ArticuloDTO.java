package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;

public class ArticuloDTO {
    public String codigo;

    public String nombre;

    @JsonProperty("descripcion_larga")
    public String descripcionLarga;

    @JsonProperty("descripcion_corta")
    public String descripcionCorta;

    @JsonProperty("imagen_url")
    public String imagenUrl;

    @JsonProperty("imagenes_detalle")
    public List<ImagenDetalleDTO> imagenesDetalle;

    public BigDecimal precioVenta;
    public Integer unidadVenta;
    public Integer tamano;
    public Integer familia;
    public Integer subfamilia;
    public Integer subsubfamilia;
}
