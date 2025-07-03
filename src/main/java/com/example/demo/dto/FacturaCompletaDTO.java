package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class FacturaCompletaDTO {
    private Integer noCia;
    private Integer noFactura;
    private Integer noSucursal;
    private Integer noBodega;
    private Integer noPrefactura;
    private LocalDateTime fechaRegistro;
    private Integer noVendedor;
    private Integer noCliente;
    private String nombreCliente;
    private String rucCedula;
    private String tipoFactura;
    private BigDecimal subtotalNominal;
    private BigDecimal impuestoNominal;
    private BigDecimal totalNominal;
    private BigDecimal descuentoNominal;
    private String status;
    private String usuarioFacturo;
    private LocalDateTime fechaFacturo;
    private String observacion;
    private String noOrdenCompra;
    private Integer noSolicitud;
    private String consecutivoFiscal;
    private String email;
    private String clienteTelefono;
    private String clienteDireccion;
    private String grupo;
    private List<FacturaDetalleDTO> detalles;

    // Constructores
    public FacturaCompletaDTO() {}

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

    public Integer getNoSucursal() {
        return noSucursal;
    }

    public void setNoSucursal(Integer noSucursal) {
        this.noSucursal = noSucursal;
    }

    public Integer getNoBodega() {
        return noBodega;
    }

    public void setNoBodega(Integer noBodega) {
        this.noBodega = noBodega;
    }

    public Integer getNoPrefactura() {
        return noPrefactura;
    }

    public void setNoPrefactura(Integer noPrefactura) {
        this.noPrefactura = noPrefactura;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getNoVendedor() {
        return noVendedor;
    }

    public void setNoVendedor(Integer noVendedor) {
        this.noVendedor = noVendedor;
    }

    public Integer getNoCliente() {
        return noCliente;
    }

    public void setNoCliente(Integer noCliente) {
        this.noCliente = noCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getRucCedula() {
        return rucCedula;
    }

    public void setRucCedula(String rucCedula) {
        this.rucCedula = rucCedula;
    }

    public String getTipoFactura() {
        return tipoFactura;
    }

    public void setTipoFactura(String tipoFactura) {
        this.tipoFactura = tipoFactura;
    }

    public BigDecimal getSubtotalNominal() {
        return subtotalNominal;
    }

    public void setSubtotalNominal(BigDecimal subtotalNominal) {
        this.subtotalNominal = subtotalNominal;
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

    public BigDecimal getDescuentoNominal() {
        return descuentoNominal;
    }

    public void setDescuentoNominal(BigDecimal descuentoNominal) {
        this.descuentoNominal = descuentoNominal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsuarioFacturo() {
        return usuarioFacturo;
    }

    public void setUsuarioFacturo(String usuarioFacturo) {
        this.usuarioFacturo = usuarioFacturo;
    }

    public LocalDateTime getFechaFacturo() {
        return fechaFacturo;
    }

    public void setFechaFacturo(LocalDateTime fechaFacturo) {
        this.fechaFacturo = fechaFacturo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getNoOrdenCompra() {
        return noOrdenCompra;
    }

    public void setNoOrdenCompra(String noOrdenCompra) {
        this.noOrdenCompra = noOrdenCompra;
    }

    public Integer getNoSolicitud() {
        return noSolicitud;
    }

    public void setNoSolicitud(Integer noSolicitud) {
        this.noSolicitud = noSolicitud;
    }

    public String getConsecutivoFiscal() {
        return consecutivoFiscal;
    }

    public void setConsecutivoFiscal(String consecutivoFiscal) {
        this.consecutivoFiscal = consecutivoFiscal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClienteTelefono() {
        return clienteTelefono;
    }

    public void setClienteTelefono(String clienteTelefono) {
        this.clienteTelefono = clienteTelefono;
    }

    public String getClienteDireccion() {
        return clienteDireccion;
    }

    public void setClienteDireccion(String clienteDireccion) {
        this.clienteDireccion = clienteDireccion;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public List<FacturaDetalleDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<FacturaDetalleDTO> detalles) {
        this.detalles = detalles;
    }
}
