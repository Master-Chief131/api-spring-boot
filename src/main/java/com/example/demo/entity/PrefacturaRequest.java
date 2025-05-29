package com.example.demo.entity;

import java.util.List;

public class PrefacturaRequest {
    private String tipoRegistro;
    private String fecha;
    private String noCia;
    private String noSucursalV;
    private String noSucursal;
    private String noBodega;
    private String noPrefactura;
    private String interCompania;
    private String ciaDestino;
    private String sucursalDestino;
    private String cliente;
    private String nomCliente;
    private String rucCedula;
    private String email;
    private String tipoFactura;
    private String noPlazo;
    private String xreferido;
    private String xnoVendedor;
    private String fetipoVenta;
    private String noOrdenCompra;
    private String indLocalidad;
    private String fechaEntrega;
    private String observacion;
    private List<PrefacturaDetalle> detalle;

    // getters y setters
    public String getTipoRegistro() { return tipoRegistro; }
    public void setTipoRegistro(String tipoRegistro) { this.tipoRegistro = tipoRegistro; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public String getNoCia() { return noCia; }
    public void setNoCia(String noCia) { this.noCia = noCia; }
    public String getNoSucursalV() { return noSucursalV; }
    public void setNoSucursalV(String noSucursalV) { this.noSucursalV = noSucursalV; }
    public String getNoSucursal() { return noSucursal; }
    public void setNoSucursal(String noSucursal) { this.noSucursal = noSucursal; }
    public String getNoBodega() { return noBodega; }
    public void setNoBodega(String noBodega) { this.noBodega = noBodega; }
    public String getNoPrefactura() { return noPrefactura; }
    public void setNoPrefactura(String noPrefactura) { this.noPrefactura = noPrefactura; }
    public String getInterCompania() { return interCompania; }
    public void setInterCompania(String interCompania) { this.interCompania = interCompania; }
    public String getCiaDestino() { return ciaDestino; }
    public void setCiaDestino(String ciaDestino) { this.ciaDestino = ciaDestino; }
    public String getSucursalDestino() { return sucursalDestino; }
    public void setSucursalDestino(String sucursalDestino) { this.sucursalDestino = sucursalDestino; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public String getNomCliente() { return nomCliente; }
    public void setNomCliente(String nomCliente) { this.nomCliente = nomCliente; }
    public String getRucCedula() { return rucCedula; }
    public void setRucCedula(String rucCedula) { this.rucCedula = rucCedula; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTipoFactura() { return tipoFactura; }
    public void setTipoFactura(String tipoFactura) { this.tipoFactura = tipoFactura; }
    public String getNoPlazo() { return noPlazo; }
    public void setNoPlazo(String noPlazo) { this.noPlazo = noPlazo; }
    public String getXreferido() { return xreferido; }
    public void setXreferido(String xreferido) { this.xreferido = xreferido; }
    public String getXnoVendedor() { return xnoVendedor; }
    public void setXnoVendedor(String xnoVendedor) { this.xnoVendedor = xnoVendedor; }
    public String getFetipoVenta() { return fetipoVenta; }
    public void setFetipoVenta(String fetipoVenta) { this.fetipoVenta = fetipoVenta; }
    public String getNoOrdenCompra() { return noOrdenCompra; }
    public void setNoOrdenCompra(String noOrdenCompra) { this.noOrdenCompra = noOrdenCompra; }
    public String getIndLocalidad() { return indLocalidad; }
    public void setIndLocalidad(String indLocalidad) { this.indLocalidad = indLocalidad; }
    public String getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(String fechaEntrega) { this.fechaEntrega = fechaEntrega; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
    public List<PrefacturaDetalle> getDetalle() { return detalle; }
    public void setDetalle(List<PrefacturaDetalle> detalle) { this.detalle = detalle; }

    public static class PrefacturaDetalle {
        private String modoLectura;
        private String codigo;
        private int cantidad;
        public String getModoLectura() { return modoLectura; }
        public void setModoLectura(String modoLectura) { this.modoLectura = modoLectura; }
        public String getCodigo() { return codigo; }
        public void setCodigo(String codigo) { this.codigo = codigo; }
        public int getCantidad() { return cantidad; }
        public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    }
}
