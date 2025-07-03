package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "facweb_solicitud_deta")
@IdClass(FacwebSolicitudDetaId.class)
public class FacwebSolicitudDeta implements Serializable {
    
    @Id
    @Column(name = "no_cia", nullable = false)
    private Integer noCia;
    
    @Id
    @Column(name = "no_sucursal", nullable = false)
    private Integer noSucursal;
    
    @Id
    @Column(name = "no_bodega", nullable = false)
    private Integer noBodega;
    
    @Id
    @Column(name = "no_solicitud", nullable = false)
    private Integer noSolicitud;
    
    @Id
    @Column(name = "no_linea", nullable = false)
    private Integer noLinea;
    
    @Id
    @Column(name = "no_articulo", nullable = false, length = 50)
    private String noArticulo;
    
    @Column(name = "cantidad", precision = 19, scale = 6)
    private BigDecimal cantidad;
    
    @Column(name = "descripcion", length = 100)
    private String descripcion;
    
    @Column(name = "precio_nominal", precision = 15, scale = 6)
    private BigDecimal precioNominal;
    
    @Column(name = "precio_dolar", precision = 15, scale = 6)
    private BigDecimal precioDolar;
    
    @Column(name = "pordescuento", precision = 15, scale = 2)
    private BigDecimal porDescuento;
    
    @Column(name = "subtotal_nominal", precision = 15, scale = 4)
    private BigDecimal subtotalNominal;
    
    @Column(name = "subtotal_dolar", precision = 15, scale = 4)
    private BigDecimal subtotalDolar;
    
    @Column(name = "descuento_nominal", precision = 15, scale = 4)
    private BigDecimal descuentoNominal;
    
    @Column(name = "descuento_dolar", precision = 15, scale = 4)
    private BigDecimal descuentoDolar;
    
    @Column(name = "impuesto_nominal", precision = 15, scale = 4)
    private BigDecimal impuestoNominal;
    
    @Column(name = "impuesto_dolar", precision = 15, scale = 4)
    private BigDecimal impuestoDolar;
    
    @Column(name = "total_nominal", precision = 15, scale = 4)
    private BigDecimal totalNominal;
    
    @Column(name = "total_dolar", precision = 15, scale = 4)
    private BigDecimal totalDolar;
    
    @Column(name = "porimpuesto", precision = 15, scale = 2)
    private BigDecimal porImpuesto;
    
    @Column(name = "no_impuesto")
    private Integer noImpuesto;
    
    @Column(name = "precio_nuevo_nominal", precision = 15, scale = 6)
    private BigDecimal precioNuevoNominal;
    
    @Column(name = "precio_nuevo_dolar", precision = 15, scale = 6)
    private BigDecimal precioNuevoDolar;
    
    @Column(name = "cantidad_confirmada", precision = 15, scale = 2)
    private BigDecimal cantidadConfirmada;
    
    @Column(name = "excento", length = 1)
    private String excento = "N";
    
    @Column(name = "status", length = 1)
    private String status;
    
    @Column(name = "cantidad_carga", precision = 15, scale = 2)
    private BigDecimal cantidadCarga;
    
    @Column(name = "tipo_promo", length = 1)
    private String tipoPromo;
    
    @Column(name = "cantidad_oferta", precision = 19, scale = 6)
    private BigDecimal cantidadOferta;
    
    @Column(name = "cia_oferta", precision = 19, scale = 6)
    private BigDecimal ciaOferta;
    
    @Column(name = "proveedor_oferta", precision = 19, scale = 6)
    private BigDecimal proveedorOferta;
    
    @Column(name = "descuento_cia", precision = 15, scale = 2)
    private BigDecimal descuentoCia;
    
    @Column(name = "descuento_prove", precision = 15, scale = 2)
    private BigDecimal descuentoProve;
    
    @Column(name = "no_articulo_oferta", length = 50)
    private String noArticuloOferta;
    
    @Column(name = "no_proveedor")
    private Integer noProveedor;
    
    @Column(name = "no_clase")
    private Integer noClase;
    
    @Column(name = "costo_oferta", precision = 15, scale = 2)
    private BigDecimal costoOferta;
    
    @Column(name = "codigo_promo1", length = 11)
    private String codigoPromo1;
    
    @Column(name = "codigo_promo2", length = 11)
    private String codigoPromo2;
    
    @Column(name = "no_unidad")
    private Integer noUnidad;
    
    @Column(name = "cantidad_eq", precision = 19, scale = 6)
    private BigDecimal cantidadEq;
    
    @Column(name = "no_unidad_eq")
    private Integer noUnidadEq;
    
    @Column(name = "tipo_articulo", length = 1)
    private String tipoArticulo;
    
    @Column(name = "ind_pendiente", length = 1)
    private String indPendiente = "N";
    
    @Column(name = "no_grupo_mercado")
    private Integer noGrupoMercado;
    
    @Column(name = "tipo_descto", length = 1)
    private String tipoDescto = "P";
    
    @Column(name = "factor_conversion", precision = 12, scale = 2)
    private BigDecimal factorConversion = new BigDecimal("1.00");
    
    @Column(name = "no_grupo_conta")
    private Integer noGrupoConta;
    
    @Column(name = "pedido_mts_x_caja", precision = 8, scale = 2)
    private BigDecimal pedidoMtsXCaja = new BigDecimal("0.00");

    // Constructor vacío
    public FacwebSolicitudDeta() {}

    // Getters y Setters
    public Integer getNoCia() { return noCia; }
    public void setNoCia(Integer noCia) { this.noCia = noCia; }

    public Integer getNoSucursal() { return noSucursal; }
    public void setNoSucursal(Integer noSucursal) { this.noSucursal = noSucursal; }

    public Integer getNoBodega() { return noBodega; }
    public void setNoBodega(Integer noBodega) { this.noBodega = noBodega; }

    public Integer getNoSolicitud() { return noSolicitud; }
    public void setNoSolicitud(Integer noSolicitud) { this.noSolicitud = noSolicitud; }

    public Integer getNoLinea() { return noLinea; }
    public void setNoLinea(Integer noLinea) { this.noLinea = noLinea; }

    public String getNoArticulo() { return noArticulo; }
    public void setNoArticulo(String noArticulo) { this.noArticulo = noArticulo; }

    public BigDecimal getCantidad() { return cantidad; }
    public void setCantidad(BigDecimal cantidad) { this.cantidad = cantidad; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

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

    public BigDecimal getDescuentoNominal() { return descuentoNominal; }
    public void setDescuentoNominal(BigDecimal descuentoNominal) { this.descuentoNominal = descuentoNominal; }

    public BigDecimal getDescuentoDolar() { return descuentoDolar; }
    public void setDescuentoDolar(BigDecimal descuentoDolar) { this.descuentoDolar = descuentoDolar; }

    public BigDecimal getImpuestoNominal() { return impuestoNominal; }
    public void setImpuestoNominal(BigDecimal impuestoNominal) { this.impuestoNominal = impuestoNominal; }

    public BigDecimal getImpuestoDolar() { return impuestoDolar; }
    public void setImpuestoDolar(BigDecimal impuestoDolar) { this.impuestoDolar = impuestoDolar; }

    public BigDecimal getTotalNominal() { return totalNominal; }
    public void setTotalNominal(BigDecimal totalNominal) { this.totalNominal = totalNominal; }

    public BigDecimal getTotalDolar() { return totalDolar; }
    public void setTotalDolar(BigDecimal totalDolar) { this.totalDolar = totalDolar; }

    public BigDecimal getPorImpuesto() { return porImpuesto; }
    public void setPorImpuesto(BigDecimal porImpuesto) { this.porImpuesto = porImpuesto; }

    public Integer getNoImpuesto() { return noImpuesto; }
    public void setNoImpuesto(Integer noImpuesto) { this.noImpuesto = noImpuesto; }

    public BigDecimal getPrecioNuevoNominal() { return precioNuevoNominal; }
    public void setPrecioNuevoNominal(BigDecimal precioNuevoNominal) { this.precioNuevoNominal = precioNuevoNominal; }

    public BigDecimal getPrecioNuevoDolar() { return precioNuevoDolar; }
    public void setPrecioNuevoDolar(BigDecimal precioNuevoDolar) { this.precioNuevoDolar = precioNuevoDolar; }

    public BigDecimal getCantidadConfirmada() { return cantidadConfirmada; }
    public void setCantidadConfirmada(BigDecimal cantidadConfirmada) { this.cantidadConfirmada = cantidadConfirmada; }

    public String getExcento() { return excento; }
    public void setExcento(String excento) { this.excento = excento; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public BigDecimal getCantidadCarga() { return cantidadCarga; }
    public void setCantidadCarga(BigDecimal cantidadCarga) { this.cantidadCarga = cantidadCarga; }

    public String getTipoPromo() { return tipoPromo; }
    public void setTipoPromo(String tipoPromo) { this.tipoPromo = tipoPromo; }

    public BigDecimal getCantidadOferta() { return cantidadOferta; }
    public void setCantidadOferta(BigDecimal cantidadOferta) { this.cantidadOferta = cantidadOferta; }

    public BigDecimal getCiaOferta() { return ciaOferta; }
    public void setCiaOferta(BigDecimal ciaOferta) { this.ciaOferta = ciaOferta; }

    public BigDecimal getProveedorOferta() { return proveedorOferta; }
    public void setProveedorOferta(BigDecimal proveedorOferta) { this.proveedorOferta = proveedorOferta; }

    public BigDecimal getDescuentoCia() { return descuentoCia; }
    public void setDescuentoCia(BigDecimal descuentoCia) { this.descuentoCia = descuentoCia; }

    public BigDecimal getDescuentoProve() { return descuentoProve; }
    public void setDescuentoProve(BigDecimal descuentoProve) { this.descuentoProve = descuentoProve; }

    public String getNoArticuloOferta() { return noArticuloOferta; }
    public void setNoArticuloOferta(String noArticuloOferta) { this.noArticuloOferta = noArticuloOferta; }

    public Integer getNoProveedor() { return noProveedor; }
    public void setNoProveedor(Integer noProveedor) { this.noProveedor = noProveedor; }

    public Integer getNoClase() { return noClase; }
    public void setNoClase(Integer noClase) { this.noClase = noClase; }

    public BigDecimal getCostoOferta() { return costoOferta; }
    public void setCostoOferta(BigDecimal costoOferta) { this.costoOferta = costoOferta; }

    public String getCodigoPromo1() { return codigoPromo1; }
    public void setCodigoPromo1(String codigoPromo1) { this.codigoPromo1 = codigoPromo1; }

    public String getCodigoPromo2() { return codigoPromo2; }
    public void setCodigoPromo2(String codigoPromo2) { this.codigoPromo2 = codigoPromo2; }

    public Integer getNoUnidad() { return noUnidad; }
    public void setNoUnidad(Integer noUnidad) { this.noUnidad = noUnidad; }

    public BigDecimal getCantidadEq() { return cantidadEq; }
    public void setCantidadEq(BigDecimal cantidadEq) { this.cantidadEq = cantidadEq; }

    public Integer getNoUnidadEq() { return noUnidadEq; }
    public void setNoUnidadEq(Integer noUnidadEq) { this.noUnidadEq = noUnidadEq; }

    public String getTipoArticulo() { return tipoArticulo; }
    public void setTipoArticulo(String tipoArticulo) { this.tipoArticulo = tipoArticulo; }

    public String getIndPendiente() { return indPendiente; }
    public void setIndPendiente(String indPendiente) { this.indPendiente = indPendiente; }

    public Integer getNoGrupoMercado() { return noGrupoMercado; }
    public void setNoGrupoMercado(Integer noGrupoMercado) { this.noGrupoMercado = noGrupoMercado; }

    public String getTipoDescto() { return tipoDescto; }
    public void setTipoDescto(String tipoDescto) { this.tipoDescto = tipoDescto; }

    public BigDecimal getFactorConversion() { return factorConversion; }
    public void setFactorConversion(BigDecimal factorConversion) { this.factorConversion = factorConversion; }

    public Integer getNoGrupoConta() { return noGrupoConta; }
    public void setNoGrupoConta(Integer noGrupoConta) { this.noGrupoConta = noGrupoConta; }

    public BigDecimal getPedidoMtsXCaja() { return pedidoMtsXCaja; }
    public void setPedidoMtsXCaja(BigDecimal pedidoMtsXCaja) { this.pedidoMtsXCaja = pedidoMtsXCaja; }
}
