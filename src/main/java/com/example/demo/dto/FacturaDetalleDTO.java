package com.example.demo.dto;

import java.math.BigDecimal;

public class FacturaDetalleDTO {
    private Integer noCia;
    private Integer noFactura;
    private Integer noLinea;
    private String noArticulo;
    private BigDecimal cantidad;
    private String descripcion;
    private BigDecimal precioNominal;
    private BigDecimal subtotalNominal;
    private BigDecimal descuentoNominal;
    private BigDecimal impuestoNominal;
    private BigDecimal totalNominal;
    private BigDecimal porDescuento;
    private BigDecimal porImpuesto;
    private String codBarra;

    // Constructores
    public FacturaDetalleDTO() {}

    // Getters y Setters
    public Integer getNoCia() {
        return noCia;
    }

    public void setNoCia(Integer noCia) {
        this.noCia = noCia;
    }

    public Integer getNoFactura() {
        return noFactura;
    }

    public void setNoFactura(Integer noFactura) {
        this.noFactura = noFactura;
    }

    public Integer getNoLinea() {
        return noLinea;
    }

    public void setNoLinea(Integer noLinea) {
        this.noLinea = noLinea;
    }

    public String getNoArticulo() {
        return noArticulo;
    }

    public void setNoArticulo(String noArticulo) {
        this.noArticulo = noArticulo;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecioNominal() {
        return precioNominal;
    }

    public void setPrecioNominal(BigDecimal precioNominal) {
        this.precioNominal = precioNominal;
    }

    public BigDecimal getSubtotalNominal() {
        return subtotalNominal;
    }

    public void setSubtotalNominal(BigDecimal subtotalNominal) {
        this.subtotalNominal = subtotalNominal;
    }

    public BigDecimal getDescuentoNominal() {
        return descuentoNominal;
    }

    public void setDescuentoNominal(BigDecimal descuentoNominal) {
        this.descuentoNominal = descuentoNominal;
    }

    public BigDecimal getImpuestoNominal() {
        return impuestoNominal;
    }

    public void setImpuestoNominal(BigDecimal impuestoNominal) {
        this.impuestoNominal = impuestoNominal;
    }

    public BigDecimal getTotalNominal() {
        return totalNominal;
    }

    public void setTotalNominal(BigDecimal totalNominal) {
        this.totalNominal = totalNominal;
    }

    public BigDecimal getPorDescuento() {
        return porDescuento;
    }

    public void setPorDescuento(BigDecimal porDescuento) {
        this.porDescuento = porDescuento;
    }

    public BigDecimal getPorImpuesto() {
        return porImpuesto;
    }

    public void setPorImpuesto(BigDecimal porImpuesto) {
        this.porImpuesto = porImpuesto;
    }

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }
}
