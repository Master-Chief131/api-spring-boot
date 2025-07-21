package com.example.demo.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class CotizacionRequest {
    // Campos de cabecera
    private String noCia;
    private String noSucursal;
    private String noBodega;
    private String noSolicitud;
    private String fecha;
    private String noVendedor;
    private String noCliente;
    private String tipoFactura;
    private BigDecimal porcentajeDescuento;
    private BigDecimal descuentoNominal;
    private BigDecimal subtotalNominal;
    private BigDecimal impuestoNominal;
    private BigDecimal totalNominal;
    private BigDecimal descuentoDolar;
    private BigDecimal subtotalDolar;
    private BigDecimal impuestoDolar;
    private BigDecimal totalDolar;
    private String tipoCambio;
    private String moneda;
    private String fechaCambio;
    private String usuario;
    private String estatus;
    private String observacion;
    private Date fechaEntrega;
    private String nomCliente;
    private Integer tipoVenta;
    private String noSucursalV;
    private String piliminar;
    private String noOrdenCompra;
    private String alias;
    private String noProveedor;
    private String noClase;
    private String ciaOferta;
    private String proveOferta;
    private BigDecimal porcDescCia;
    private BigDecimal porcDescProve;
    private String tipoOferta;
    private String cantOfe;
    private String artiOfe;
    private String codigoPromo1;
    private String codigoPromo2;
    private String origenSolicitud;
    private BigDecimal sucursalCliente;
    private String noPlazo;
    private String indLocalidad;
    private String grupoCliente;    private String email;
    private Integer noReferido;
    private String nomReferido;
    private String rucCedula;
    
    // Campos adicionales para API_COTIZACION
    private String origenProceso;
    private String noCotizacion;

    // Detalle de líneas
    private List<CotizacionDetalle> detalle;

    public static class CotizacionDetalle {
        private String linea;
        private String noArticulo;
        private String cantidad;
        private String codBarra;
        private String descripcion;
        private BigDecimal precio;
        private BigDecimal precioDolar;
        private BigDecimal porcentajeDescuento;
        private BigDecimal subtotal;
        private BigDecimal subtotalDolar;
        private BigDecimal descuento;
        private BigDecimal descuentoDolar;
        private BigDecimal impuestoNominal;
        private BigDecimal impuestoDolar;
        private BigDecimal total;
        private BigDecimal totalDolar;
        private BigDecimal porcentajeImpuesto;
        private String noUnidad;
        private BigDecimal costoPromedio;
        private BigDecimal mtsXCaja;
        private String serie;
        private String noImpuesto;
        private String excento;
        private BigDecimal precioNuevo;
        private BigDecimal precioNuevoDolar;
        private String noBodega;
        private String tipoArticulo;
        private String noSucursal;
        private String cantidadEq;
        private String noUnidadEq;
        private Integer noGrupoMercado;

        // Getters y setters
        public String getLinea() { return linea; }
        public void setLinea(String linea) { this.linea = linea; }
        public String getNoArticulo() { return noArticulo; }
        public void setNoArticulo(String noArticulo) { this.noArticulo = noArticulo; }
        public String getCantidad() { return cantidad; }
        public void setCantidad(String cantidad) { this.cantidad = cantidad; }
        public String getCodBarra() { return codBarra; }
        public void setCodBarra(String codBarra) { this.codBarra = codBarra; }
        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
        public BigDecimal getPrecio() { return precio; }
        public void setPrecio(BigDecimal precio) { this.precio = precio; }
        public BigDecimal getPrecioDolar() { return precioDolar; }
        public void setPrecioDolar(BigDecimal precioDolar) { this.precioDolar = precioDolar; }
        public BigDecimal getPorcentajeDescuento() { return porcentajeDescuento; }
        public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) { this.porcentajeDescuento = porcentajeDescuento; }
        public BigDecimal getSubtotal() { return subtotal; }
        public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
        public BigDecimal getSubtotalDolar() { return subtotalDolar; }
        public void setSubtotalDolar(BigDecimal subtotalDolar) { this.subtotalDolar = subtotalDolar; }
        public BigDecimal getDescuento() { return descuento; }
        public void setDescuento(BigDecimal descuento) { this.descuento = descuento; }
        public BigDecimal getDescuentoDolar() { return descuentoDolar; }
        public void setDescuentoDolar(BigDecimal descuentoDolar) { this.descuentoDolar = descuentoDolar; }
        public BigDecimal getImpuestoNominal() { return impuestoNominal; }
        public void setImpuestoNominal(BigDecimal impuestoNominal) { this.impuestoNominal = impuestoNominal; }
        public BigDecimal getImpuestoDolar() { return impuestoDolar; }
        public void setImpuestoDolar(BigDecimal impuestoDolar) { this.impuestoDolar = impuestoDolar; }
        public BigDecimal getTotal() { return total; }
        public void setTotal(BigDecimal total) { this.total = total; }
        public BigDecimal getTotalDolar() { return totalDolar; }
        public void setTotalDolar(BigDecimal totalDolar) { this.totalDolar = totalDolar; }
        public BigDecimal getPorcentajeImpuesto() { return porcentajeImpuesto; }
        public void setPorcentajeImpuesto(BigDecimal porcentajeImpuesto) { this.porcentajeImpuesto = porcentajeImpuesto; }
        public String getNoUnidad() { return noUnidad; }
        public void setNoUnidad(String noUnidad) { this.noUnidad = noUnidad; }
        public BigDecimal getCostoPromedio() { return costoPromedio; }
        public void setCostoPromedio(BigDecimal costoPromedio) { this.costoPromedio = costoPromedio; }
        public BigDecimal getMtsXCaja() { return mtsXCaja; }
        public void setMtsXCaja(BigDecimal mtsXCaja) { this.mtsXCaja = mtsXCaja; }
        public String getSerie() { return serie; }
        public void setSerie(String serie) { this.serie = serie; }
        public String getNoImpuesto() { return noImpuesto; }
        public void setNoImpuesto(String noImpuesto) { this.noImpuesto = noImpuesto; }
        public String getExcento() { return excento; }
        public void setExcento(String excento) { this.excento = excento; }
        public BigDecimal getPrecioNuevo() { return precioNuevo; }
        public void setPrecioNuevo(BigDecimal precioNuevo) { this.precioNuevo = precioNuevo; }
        public BigDecimal getPrecioNuevoDolar() { return precioNuevoDolar; }
        public void setPrecioNuevoDolar(BigDecimal precioNuevoDolar) { this.precioNuevoDolar = precioNuevoDolar; }
        public String getNoBodega() { return noBodega; }
        public void setNoBodega(String noBodega) { this.noBodega = noBodega; }
        public String getTipoArticulo() { return tipoArticulo; }
        public void setTipoArticulo(String tipoArticulo) { this.tipoArticulo = tipoArticulo; }
        public String getNoSucursal() { return noSucursal; }
        public void setNoSucursal(String noSucursal) { this.noSucursal = noSucursal; }
        public String getCantidadEq() { return cantidadEq; }
        public void setCantidadEq(String cantidadEq) { this.cantidadEq = cantidadEq; }
        public String getNoUnidadEq() { return noUnidadEq; }
        public void setNoUnidadEq(String noUnidadEq) { this.noUnidadEq = noUnidadEq; }
        public Integer getNoGrupoMercado() { return noGrupoMercado; }
        public void setNoGrupoMercado(Integer noGrupoMercado) { this.noGrupoMercado = noGrupoMercado; }
    }

    // Getters y setters para cabecera
    public String getNoCia() { return noCia; }
    public void setNoCia(String noCia) { this.noCia = noCia; }
    public String getNoSucursal() { return noSucursal; }
    public void setNoSucursal(String noSucursal) { this.noSucursal = noSucursal; }
    public String getNoBodega() { return noBodega; }
    public void setNoBodega(String noBodega) { this.noBodega = noBodega; }
    public String getNoSolicitud() { return noSolicitud; }
    public void setNoSolicitud(String noSolicitud) { this.noSolicitud = noSolicitud; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public String getNoVendedor() { return noVendedor; }
    public void setNoVendedor(String noVendedor) { this.noVendedor = noVendedor; }
    public String getNoCliente() { return noCliente; }
    public void setNoCliente(String noCliente) { this.noCliente = noCliente; }
    public String getTipoFactura() { return tipoFactura; }
    public void setTipoFactura(String tipoFactura) { this.tipoFactura = tipoFactura; }
    public BigDecimal getPorcentajeDescuento() { return porcentajeDescuento; }
    public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) { this.porcentajeDescuento = porcentajeDescuento; }
    public BigDecimal getDescuentoNominal() { return descuentoNominal; }
    public void setDescuentoNominal(BigDecimal descuentoNominal) { this.descuentoNominal = descuentoNominal; }
    public BigDecimal getSubtotalNominal() { return subtotalNominal; }
    public void setSubtotalNominal(BigDecimal subtotalNominal) { this.subtotalNominal = subtotalNominal; }
    public BigDecimal getImpuestoNominal() { return impuestoNominal; }
    public void setImpuestoNominal(BigDecimal impuestoNominal) { this.impuestoNominal = impuestoNominal; }
    public BigDecimal getTotalNominal() { return totalNominal; }
    public void setTotalNominal(BigDecimal totalNominal) { this.totalNominal = totalNominal; }
    public BigDecimal getDescuentoDolar() { return descuentoDolar; }
    public void setDescuentoDolar(BigDecimal descuentoDolar) { this.descuentoDolar = descuentoDolar; }
    public BigDecimal getSubtotalDolar() { return subtotalDolar; }
    public void setSubtotalDolar(BigDecimal subtotalDolar) { this.subtotalDolar = subtotalDolar; }
    public BigDecimal getImpuestoDolar() { return impuestoDolar; }
    public void setImpuestoDolar(BigDecimal impuestoDolar) { this.impuestoDolar = impuestoDolar; }
    public BigDecimal getTotalDolar() { return totalDolar; }
    public void setTotalDolar(BigDecimal totalDolar) { this.totalDolar = totalDolar; }
    public String getTipoCambio() { return tipoCambio; }
    public void setTipoCambio(String tipoCambio) { this.tipoCambio = tipoCambio; }
    public String getMoneda() { return moneda; }
    public void setMoneda(String moneda) { this.moneda = moneda; }
    public String getFechaCambio() { return fechaCambio; }
    public void setFechaCambio(String fechaCambio) { this.fechaCambio = fechaCambio; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getEstatus() { return estatus; }
    public void setEstatus(String estatus) { this.estatus = estatus; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
    public Date getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(Date fechaEntrega) { this.fechaEntrega = fechaEntrega; }
    public String getNomCliente() { return nomCliente; }
    public void setNomCliente(String nomCliente) { this.nomCliente = nomCliente; }
    public Integer getTipoVenta() { return tipoVenta; }
    public void setTipoVenta(Integer tipoVenta) { this.tipoVenta = tipoVenta; }
    public String getNoSucursalV() { return noSucursalV; }
    public void setNoSucursalV(String noSucursalV) { this.noSucursalV = noSucursalV; }
    public String getPiliminar() { return piliminar; }
    public void setPiliminar(String piliminar) { this.piliminar = piliminar; }
    public String getNoOrdenCompra() { return noOrdenCompra; }
    public void setNoOrdenCompra(String noOrdenCompra) { this.noOrdenCompra = noOrdenCompra; }
    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }
    public String getNoProveedor() { return noProveedor; }
    public void setNoProveedor(String noProveedor) { this.noProveedor = noProveedor; }
    public String getNoClase() { return noClase; }
    public void setNoClase(String noClase) { this.noClase = noClase; }
    public String getCiaOferta() { return ciaOferta; }
    public void setCiaOferta(String ciaOferta) { this.ciaOferta = ciaOferta; }
    public String getProveOferta() { return proveOferta; }
    public void setProveOferta(String proveOferta) { this.proveOferta = proveOferta; }
    public BigDecimal getPorcDescCia() { return porcDescCia; }
    public void setPorcDescCia(BigDecimal porcDescCia) { this.porcDescCia = porcDescCia; }
    public BigDecimal getPorcDescProve() { return porcDescProve; }
    public void setPorcDescProve(BigDecimal porcDescProve) { this.porcDescProve = porcDescProve; }
    public String getTipoOferta() { return tipoOferta; }
    public void setTipoOferta(String tipoOferta) { this.tipoOferta = tipoOferta; }
    public String getCantOfe() { return cantOfe; }
    public void setCantOfe(String cantOfe) { this.cantOfe = cantOfe; }
    public String getArtiOfe() { return artiOfe; }
    public void setArtiOfe(String artiOfe) { this.artiOfe = artiOfe; }
    public String getCodigoPromo1() { return codigoPromo1; }
    public void setCodigoPromo1(String codigoPromo1) { this.codigoPromo1 = codigoPromo1; }
    public String getCodigoPromo2() { return codigoPromo2; }
    public void setCodigoPromo2(String codigoPromo2) { this.codigoPromo2 = codigoPromo2; }
    public String getOrigenSolicitud() { return origenSolicitud; }
    public void setOrigenSolicitud(String origenSolicitud) { this.origenSolicitud = origenSolicitud; }
    public BigDecimal getSucursalCliente() { return sucursalCliente; }
    public void setSucursalCliente(BigDecimal sucursalCliente) { this.sucursalCliente = sucursalCliente; }
    public String getNoPlazo() { return noPlazo; }
    public void setNoPlazo(String noPlazo) { this.noPlazo = noPlazo; }
    public String getIndLocalidad() { return indLocalidad; }
    public void setIndLocalidad(String indLocalidad) { this.indLocalidad = indLocalidad; }
    public String getGrupoCliente() { return grupoCliente; }
    public void setGrupoCliente(String grupoCliente) { this.grupoCliente = grupoCliente; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Integer getNoReferido() { return noReferido; }
    public void setNoReferido(Integer noReferido) { this.noReferido = noReferido; }
    public String getNomReferido() { return nomReferido; }
    public void setNomReferido(String nomReferido) { this.nomReferido = nomReferido; }    public String getRucCedula() { return rucCedula; }
    public void setRucCedula(String rucCedula) { this.rucCedula = rucCedula; }
    
    // Getters y setters para campos adicionales
    public String getOrigenProceso() { return origenProceso; }
    public void setOrigenProceso(String origenProceso) { this.origenProceso = origenProceso; }
    public String getNoCotizacion() { return noCotizacion; }
    public void setNoCotizacion(String noCotizacion) { this.noCotizacion = noCotizacion; }
    
    public List<CotizacionDetalle> getDetalle() { return detalle; }
    public void setDetalle(List<CotizacionDetalle> detalle) { this.detalle = detalle; }
}
