package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "facweb_prefactura")
@IdClass(PrefacturaId.class)
public class FacwebPrefactura implements Serializable {
    
    @Id
    @Column(name = "no_cia", nullable = false)
    private Integer noCia;
    
    @Id
    @Column(name = "no_prefactura", nullable = false)
    private Integer noPrefactura;
    
    @Column(name = "no_sucursal")
    private Integer noSucursal;
    
    @Column(name = "no_bodega")
    private Integer noBodega;
    
    @Column(name = "fecha_REGISTRO")
    private Timestamp fechaRegistro;
    
    @Column(name = "no_vendedor", length = 30)
    private String noVendedor;
    
    @Column(name = "no_cliente")
    private Integer noCliente;
    
    @Column(name = "TIPO_FACTURA", length = 3)
    private String tipoFactura;
    
    @Column(name = "PDESCUENTO", precision = 15, scale = 2)
    private BigDecimal pdescuento;
    
    @Column(name = "DESCUENTO_NOMINAL", precision = 15, scale = 6)
    private BigDecimal descuentoNominal;
    
    @Column(name = "SUBTOTAL_NOMINAL", precision = 15, scale = 6)
    private BigDecimal subtotalNominal;
    
    @Column(name = "IMPUESTO_NOMINAL", precision = 15, scale = 6)
    private BigDecimal impuestoNominal;
    
    @Column(name = "TOTAL_NOMINAL", precision = 15, scale = 6)
    private BigDecimal totalNominal;
    
    @Column(name = "DESCUENTO_DOLAR", precision = 15, scale = 6)
    private BigDecimal descuentoDolar;
    
    @Column(name = "SUBTOTAL_DOLAR", precision = 15, scale = 6)
    private BigDecimal subtotalDolar;
    
    @Column(name = "IMPUESTO_DOLAR", precision = 15, scale = 6)
    private BigDecimal impuestoDolar;
    
    @Column(name = "TOTAL_DOLAR", precision = 15, scale = 6)
    private BigDecimal totalDolar;
    
    @Column(name = "TIPO_CAMBIO")
    private Integer tipoCambio;
    
    @Column(name = "CODIGO_MONEDA")
    private Integer codigoMoneda;
    
    @Column(name = "FECHA_CAMBIO")
    private Date fechaCambio;
    
    @Column(name = "USUARIO_CREO", length = 50)
    private String usuarioCreo;
    
    @Column(name = "FECHA_CREACION")
    private Timestamp fechaCreacion;
    
    @Column(name = "STATUS", length = 1)
    private String status;
    
    @Column(name = "USUARIO_PREFACTURA", length = 50)
    private String usuarioPrefactura;
    
    @Column(name = "FECHA_PREFACTURA")
    private Timestamp fechaPrefactura;
    
    @Column(name = "USUARIO_FACTURO", length = 50)
    private String usuarioFacturo;
    
    @Column(name = "FECHA_FACTURO")
    private Timestamp fechaFacturo;
    
    @Column(name = "OBSERVACION", length = 500)
    private String observacion;
    
    @Column(name = "no_orden_compra", length = 100)
    private String noOrdenCompra;
    
    @Column(name = "USUARIO_ANULO", length = 50)
    private String usuarioAnulo;
    
    @Column(name = "FECHA_ANULO")
    private Timestamp fechaAnulo;
    
    @Column(name = "no_solicitud")
    private Integer noSolicitud;
    
    @Column(name = "no_plazo")
    private Integer noPlazo;
    
    @Column(name = "no_cotizacion")
    private Integer noCotizacion;
    
    @Column(name = "estado_despacho", length = 1)
    private String estadoDespacho;
    
    @Column(name = "fecha_entrega")
    private Date fechaEntrega;
    
    @Column(name = "SUBTOTAL_FACTURAR", precision = 15, scale = 4)
    private BigDecimal subtotalFacturar;
    
    @Column(name = "DESCUENTO_FACTURAR", precision = 15, scale = 4)
    private BigDecimal descuentoFacturar;
    
    @Column(name = "IMPUESTO_FACTURAR", precision = 15, scale = 4)
    private BigDecimal impuestoFacturar;
    
    @Column(name = "TOTAL_FACTURAR", precision = 15, scale = 4)
    private BigDecimal totalFacturar;
    
    @Column(name = "PDESCUENTO_FACTURAR", precision = 15, scale = 2)
    private BigDecimal pdescuentoFacturar;
    
    @Column(name = "ind_localidad", length = 1)
    private String indLocalidad;
    
    @Column(name = "no_sucursal_cliente")
    private Integer noSucursalCliente;
    
    @Column(name = "grupo", length = 2)
    private String grupo;
    
    @Column(name = "con_articulos", length = 1)
    private String conArticulos;
    
    @Column(name = "ruc_cedula", length = 25)
    private String rucCedula;
    
    @Column(name = "nombre_cliente", length = 100)
    private String nombreCliente;
    
    @Column(name = "tipo_venta")
    private Integer tipoVenta;
    
    @Column(name = "cliente_telefono", length = 50)
    private String clienteTelefono;
    
    @Column(name = "cliente_direccion", length = 300)
    private String clienteDireccion;
    
    @Column(name = "no_referido")
    private Integer noReferido;
    
    @Column(name = "nombre_referido", length = 300)
    private String nombreReferido;
    
    @Column(name = "no_sucursal_venta")
    private Integer noSucursalVenta;
    
    @Column(name = "email", length = 150)
    private String email;
    
    @Column(name = "dv", length = 2)
    private String dv;
    
    @Column(name = "inter_cia", length = 1)
    private String interCia;
    
    @Column(name = "compania_inter_cia")
    private Integer companiaInterCia;
    
    @Column(name = "sucursal_inter_cia")
    private Integer sucursalInterCia;

    // Constructor
    public FacwebPrefactura() {}

    // Getters y setters
    public Integer getNoCia() { return noCia; }
    public void setNoCia(Integer noCia) { this.noCia = noCia; }
    public Integer getNoPrefactura() { return noPrefactura; }
    public void setNoPrefactura(Integer noPrefactura) { this.noPrefactura = noPrefactura; }
    public Integer getNoSucursal() { return noSucursal; }
    public void setNoSucursal(Integer noSucursal) { this.noSucursal = noSucursal; }
    public Integer getNoBodega() { return noBodega; }
    public void setNoBodega(Integer noBodega) { this.noBodega = noBodega; }
    public Timestamp getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Timestamp fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public String getNoVendedor() { return noVendedor; }
    public void setNoVendedor(String noVendedor) { this.noVendedor = noVendedor; }
    public Integer getNoCliente() { return noCliente; }
    public void setNoCliente(Integer noCliente) { this.noCliente = noCliente; }
    public String getTipoFactura() { return tipoFactura; }
    public void setTipoFactura(String tipoFactura) { this.tipoFactura = tipoFactura; }
    public BigDecimal getPdescuento() { return pdescuento; }
    public void setPdescuento(BigDecimal pdescuento) { this.pdescuento = pdescuento; }
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
    public Integer getTipoCambio() { return tipoCambio; }
    public void setTipoCambio(Integer tipoCambio) { this.tipoCambio = tipoCambio; }
    public Integer getCodigoMoneda() { return codigoMoneda; }
    public void setCodigoMoneda(Integer codigoMoneda) { this.codigoMoneda = codigoMoneda; }
    public Date getFechaCambio() { return fechaCambio; }
    public void setFechaCambio(Date fechaCambio) { this.fechaCambio = fechaCambio; }
    public String getUsuarioCreo() { return usuarioCreo; }
    public void setUsuarioCreo(String usuarioCreo) { this.usuarioCreo = usuarioCreo; }
    public Timestamp getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Timestamp fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getUsuarioPrefactura() { return usuarioPrefactura; }
    public void setUsuarioPrefactura(String usuarioPrefactura) { this.usuarioPrefactura = usuarioPrefactura; }
    public Timestamp getFechaPrefactura() { return fechaPrefactura; }
    public void setFechaPrefactura(Timestamp fechaPrefactura) { this.fechaPrefactura = fechaPrefactura; }
    public String getUsuarioFacturo() { return usuarioFacturo; }
    public void setUsuarioFacturo(String usuarioFacturo) { this.usuarioFacturo = usuarioFacturo; }
    public Timestamp getFechaFacturo() { return fechaFacturo; }
    public void setFechaFacturo(Timestamp fechaFacturo) { this.fechaFacturo = fechaFacturo; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
    public String getNoOrdenCompra() { return noOrdenCompra; }
    public void setNoOrdenCompra(String noOrdenCompra) { this.noOrdenCompra = noOrdenCompra; }
    public String getUsuarioAnulo() { return usuarioAnulo; }
    public void setUsuarioAnulo(String usuarioAnulo) { this.usuarioAnulo = usuarioAnulo; }
    public Timestamp getFechaAnulo() { return fechaAnulo; }
    public void setFechaAnulo(Timestamp fechaAnulo) { this.fechaAnulo = fechaAnulo; }
    public Integer getNoSolicitud() { return noSolicitud; }
    public void setNoSolicitud(Integer noSolicitud) { this.noSolicitud = noSolicitud; }
    public Integer getNoPlazo() { return noPlazo; }
    public void setNoPlazo(Integer noPlazo) { this.noPlazo = noPlazo; }
    public Integer getNoCotizacion() { return noCotizacion; }
    public void setNoCotizacion(Integer noCotizacion) { this.noCotizacion = noCotizacion; }
    public String getEstadoDespacho() { return estadoDespacho; }
    public void setEstadoDespacho(String estadoDespacho) { this.estadoDespacho = estadoDespacho; }
    public Date getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(Date fechaEntrega) { this.fechaEntrega = fechaEntrega; }
    public BigDecimal getSubtotalFacturar() { return subtotalFacturar; }
    public void setSubtotalFacturar(BigDecimal subtotalFacturar) { this.subtotalFacturar = subtotalFacturar; }
    public BigDecimal getDescuentoFacturar() { return descuentoFacturar; }
    public void setDescuentoFacturar(BigDecimal descuentoFacturar) { this.descuentoFacturar = descuentoFacturar; }
    public BigDecimal getImpuestoFacturar() { return impuestoFacturar; }
    public void setImpuestoFacturar(BigDecimal impuestoFacturar) { this.impuestoFacturar = impuestoFacturar; }
    public BigDecimal getTotalFacturar() { return totalFacturar; }
    public void setTotalFacturar(BigDecimal totalFacturar) { this.totalFacturar = totalFacturar; }
    public BigDecimal getPdescuentoFacturar() { return pdescuentoFacturar; }
    public void setPdescuentoFacturar(BigDecimal pdescuentoFacturar) { this.pdescuentoFacturar = pdescuentoFacturar; }
    public String getIndLocalidad() { return indLocalidad; }
    public void setIndLocalidad(String indLocalidad) { this.indLocalidad = indLocalidad; }
    public Integer getNoSucursalCliente() { return noSucursalCliente; }
    public void setNoSucursalCliente(Integer noSucursalCliente) { this.noSucursalCliente = noSucursalCliente; }
    public String getGrupo() { return grupo; }
    public void setGrupo(String grupo) { this.grupo = grupo; }
    public String getConArticulos() { return conArticulos; }
    public void setConArticulos(String conArticulos) { this.conArticulos = conArticulos; }
    public String getRucCedula() { return rucCedula; }
    public void setRucCedula(String rucCedula) { this.rucCedula = rucCedula; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public Integer getTipoVenta() { return tipoVenta; }
    public void setTipoVenta(Integer tipoVenta) { this.tipoVenta = tipoVenta; }
    public String getClienteTelefono() { return clienteTelefono; }
    public void setClienteTelefono(String clienteTelefono) { this.clienteTelefono = clienteTelefono; }
    public String getClienteDireccion() { return clienteDireccion; }
    public void setClienteDireccion(String clienteDireccion) { this.clienteDireccion = clienteDireccion; }
    public Integer getNoReferido() { return noReferido; }
    public void setNoReferido(Integer noReferido) { this.noReferido = noReferido; }
    public String getNombreReferido() { return nombreReferido; }
    public void setNombreReferido(String nombreReferido) { this.nombreReferido = nombreReferido; }
    public Integer getNoSucursalVenta() { return noSucursalVenta; }
    public void setNoSucursalVenta(Integer noSucursalVenta) { this.noSucursalVenta = noSucursalVenta; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDv() { return dv; }
    public void setDv(String dv) { this.dv = dv; }
    public String getInterCia() { return interCia; }
    public void setInterCia(String interCia) { this.interCia = interCia; }
    public Integer getCompaniaInterCia() { return companiaInterCia; }
    public void setCompaniaInterCia(Integer companiaInterCia) { this.companiaInterCia = companiaInterCia; }
    public Integer getSucursalInterCia() { return sucursalInterCia; }
    public void setSucursalInterCia(Integer sucursalInterCia) { this.sucursalInterCia = sucursalInterCia; }
}
