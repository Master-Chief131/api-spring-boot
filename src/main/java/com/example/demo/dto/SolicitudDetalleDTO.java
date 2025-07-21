package com.example.demo.dto;

import com.example.demo.entity.FacwebSolicitudDeta;

import java.math.BigDecimal;

public class SolicitudDetalleDTO {
    
    private Integer noLinea;
    private String noArticulo;
    private String descripcion;
    private BigDecimal cantidad;
    private BigDecimal precioNominal;
    private BigDecimal precioDolar;
    private BigDecimal porDescuento;
    private BigDecimal subtotalNominal;
    private BigDecimal subtotalDolar;
    private BigDecimal totalNominal;
    private BigDecimal totalDolar;
    private String status;
    private String tipoArticulo;
    
    // Constructor vacío
    public SolicitudDetalleDTO() {}
    
    // Constructor que recibe la entidad FacwebSolicitudDeta
    public SolicitudDetalleDTO(FacwebSolicitudDeta detalle) {
        this.noLinea = detalle.getNoLinea();
        this.noArticulo = detalle.getNoArticulo();
        this.descripcion = detalle.getDescripcion();
        this.cantidad = detalle.getCantidad();
        this.precioNominal = detalle.getPrecioNominal();
        this.precioDolar = detalle.getPrecioDolar();
        this.porDescuento = detalle.getPorDescuento();
        this.subtotalNominal = detalle.getSubtotalNominal();
        this.subtotalDolar = detalle.getSubtotalDolar();
        this.totalNominal = detalle.getTotalNominal();
        this.totalDolar = detalle.getTotalDolar();
        this.status = detalle.getStatus();
        this.tipoArticulo = detalle.getTipoArticulo();
    }

    // Getters y Setters
    public Integer getNoLinea() { return noLinea; }
    public void setNoLinea(Integer noLinea) { this.noLinea = noLinea; }

    public String getNoArticulo() { return noArticulo; }
    public void setNoArticulo(String noArticulo) { this.noArticulo = noArticulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public BigDecimal getCantidad() { return cantidad; }
    public void setCantidad(BigDecimal cantidad) { this.cantidad = cantidad; }

    public BigDecimal getPrecioNominal() { return precioNominal; }
    public void setPrecioNominal(BigDecimal precioNominal) { this.precioNominal = precioNominal; }

    public BigDecimal getPrecioDolar() { return precioDolar; }
    public void setPrecioDolar(BigDecimal precioDolar) { this.precioDolar = precioDolar; }

    public BigDecimal getPorDescuento() { return porDescuento; }
    public void setPorDescuento(BigDecimal porDescuento) { this.porDescuento = porDescuento; }

    public BigDecimal getSubtotalNominal() { return subtotalNominal; }
    public void setSubtotalNominal(BigDecimal subtotalNominal) { this.subtotalNominal = subtotalNominal; }

    public BigDecimal getSubtotalDolar() { return subtotalDolar; }
    public void setSubtotalDolar(BigDecimal subtotalDolar) { this.subtotalDolar = subtotalDolar; }

    public BigDecimal getTotalNominal() { return totalNominal; }
    public void setTotalNominal(BigDecimal totalNominal) { this.totalNominal = totalNominal; }

    public BigDecimal getTotalDolar() { return totalDolar; }
    public void setTotalDolar(BigDecimal totalDolar) { this.totalDolar = totalDolar; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTipoArticulo() { return tipoArticulo; }
    public void setTipoArticulo(String tipoArticulo) { this.tipoArticulo = tipoArticulo; }
}
