package com.example.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "facweb_factura_deta")
@IdClass(FacwebFacturaDetaId.class)
public class FacwebFacturaDeta {

    @Id
    @Column(name = "no_cia", nullable = false)
    private Integer noCia;

    @Id
    @Column(name = "no_factura", nullable = false)
    private Integer noFactura;

    @Id
    @Column(name = "no_linea", nullable = false)
    private Integer noLinea;

    @Id
    @Column(name = "no_articulo", nullable = false, length = 50)
    private String noArticulo;

    @Column(name = "no_sucursal", nullable = false)
    private Integer noSucursal;

    @Column(name = "no_bodega", nullable = false)
    private Integer noBodega;

    @Column(name = "cantidad", precision = 19, scale = 6)
    private BigDecimal cantidad;

    @Column(name = "cod_barra", length = 50)
    private String codBarra;

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

    @Column(name = "no_unidad")
    private Integer noUnidad;

    @Column(name = "costo_promedio", precision = 15, scale = 6)
    private BigDecimal costoPromedio;

    @Column(name = "costo_lote", precision = 15, scale = 6)
    private BigDecimal costoLote;

    @Column(name = "fecha_ingreso_lote")
    private LocalDateTime fechaIngresoLote;

    @Column(name = "no_lote", length = 50)
    private String noLote;

    @Column(name = "no_ubicacion", length = 50)
    private String noUbicacion;

    @Column(name = "serie", length = 1)
    private String serie;

    @Column(name = "no_impuesto")
    private Integer noImpuesto;

    @Column(name = "paga_impuesto", length = 1)
    private String pagaImpuesto;

    @Column(name = "precio_nuevo_nominal", precision = 15, scale = 2)
    private BigDecimal precioNuevoNominal;

    @Column(name = "precio_nuevo_dolar", precision = 15, scale = 2)
    private BigDecimal precioNuevoDolar;

    @Column(name = "fecha_vencimiento_lote")
    private LocalDateTime fechaVencimientoLote;

    @Column(name = "excento", length = 1)
    private String excento = "N";

    @Column(name = "status", length = 1)
    private String status;

    @Column(name = "cantidad_devuelta", precision = 19, scale = 6)
    private BigDecimal cantidadDevuelta = BigDecimal.ZERO;

    @Column(name = "tipo_articulo", length = 1)
    private String tipoArticulo;

    @Column(name = "placa", length = 10)
    private String placa = "";

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

    @Column(name = "cantidad_eq", precision = 19, scale = 6)
    private BigDecimal cantidadEq;

    @Column(name = "no_unidad_eq")
    private Integer noUnidadEq;

    @Column(name = "acumul_despacho", precision = 19, scale = 6)
    private BigDecimal acumulDespacho;

    @Column(name = "pendiente_despacho", precision = 19, scale = 6)
    private BigDecimal pendienteDespacho;

    @Column(name = "acumul_despacho_eq", precision = 19, scale = 6)
    private BigDecimal acumulDespachoEq;

    @Column(name = "pendiente_despacho_eq", precision = 19, scale = 6)
    private BigDecimal pendienteDespachoEq;

    @Column(name = "estado_despacho", length = 1)
    private String estadoDespacho;

    @Column(name = "cantidad_devuelta_eq", precision = 19, scale = 6)
    private BigDecimal cantidadDevueltaEq = BigDecimal.ZERO;

    @Column(name = "no_grupo_mercado")
    private Integer noGrupoMercado;

    @Column(name = "bloqueado_nc", columnDefinition = "TINYINT(1)")
    private Boolean bloqueadoNc = false;

    @Column(name = "nota_credito_bloqueada")
    private Integer notaCreditoBloqueada;

    @Column(name = "no_factura_refe")
    private Integer noFacturaRefe;

    @Column(name = "no_linea_refe")
    private Integer noLineaRefe;

    @Column(name = "no_razon")
    private Integer noRazon;

    @Column(name = "tipo_descto", length = 1)
    private String tipoDescto = "P";

    @Column(name = "no_fisico_refe", length = 90)
    private String noFisicoRefe;

    @Column(name = "estado_revision", length = 1)
    private String estadoRevision;

    @Column(name = "para_merma", length = 1)
    private String paraMerma = "N";

    @Column(name = "factor_conversion", precision = 12, scale = 2)
    private BigDecimal factorConversion = new BigDecimal("1.00");

    @Column(name = "no_grupo_conta")
    private Integer noGrupoConta;

    @Column(name = "pedido_mts_x_caja", precision = 8, scale = 2)
    private BigDecimal pedidoMtsXCaja = BigDecimal.ZERO;

    @Column(name = "aumento_costo_promedio", precision = 15, scale = 12)
    private BigDecimal aumentoCostoPromedio;

    @Column(name = "costo_promedio_ant", precision = 15, scale = 12)
    private BigDecimal costoPromedioAnt;

    @Column(name = "no_factura_ex")
    private Integer noFacturaEx = 0;

    @Column(name = "no_cia_cli")
    private Integer noCiaCli = 0;

    // Constructores
    public FacwebFacturaDeta() {}

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

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
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

    public BigDecimal getPrecioDolar() {
        return precioDolar;
    }

    public void setPrecioDolar(BigDecimal precioDolar) {
        this.precioDolar = precioDolar;
    }

    public BigDecimal getPorDescuento() {
        return porDescuento;
    }

    public void setPorDescuento(BigDecimal porDescuento) {
        this.porDescuento = porDescuento;
    }

    public BigDecimal getSubtotalNominal() {
        return subtotalNominal;
    }

    public void setSubtotalNominal(BigDecimal subtotalNominal) {
        this.subtotalNominal = subtotalNominal;
    }

    public BigDecimal getSubtotalDolar() {
        return subtotalDolar;
    }

    public void setSubtotalDolar(BigDecimal subtotalDolar) {
        this.subtotalDolar = subtotalDolar;
    }

    public BigDecimal getDescuentoNominal() {
        return descuentoNominal;
    }

    public void setDescuentoNominal(BigDecimal descuentoNominal) {
        this.descuentoNominal = descuentoNominal;
    }

    public BigDecimal getDescuentoDolar() {
        return descuentoDolar;
    }

    public void setDescuentoDolar(BigDecimal descuentoDolar) {
        this.descuentoDolar = descuentoDolar;
    }

    public BigDecimal getImpuestoNominal() {
        return impuestoNominal;
    }

    public void setImpuestoNominal(BigDecimal impuestoNominal) {
        this.impuestoNominal = impuestoNominal;
    }

    public BigDecimal getImpuestoDolar() {
        return impuestoDolar;
    }

    public void setImpuestoDolar(BigDecimal impuestoDolar) {
        this.impuestoDolar = impuestoDolar;
    }

    public BigDecimal getTotalNominal() {
        return totalNominal;
    }

    public void setTotalNominal(BigDecimal totalNominal) {
        this.totalNominal = totalNominal;
    }

    public BigDecimal getTotalDolar() {
        return totalDolar;
    }

    public void setTotalDolar(BigDecimal totalDolar) {
        this.totalDolar = totalDolar;
    }

    public BigDecimal getPorImpuesto() {
        return porImpuesto;
    }

    public void setPorImpuesto(BigDecimal porImpuesto) {
        this.porImpuesto = porImpuesto;
    }

    public Integer getNoUnidad() {
        return noUnidad;
    }

    public void setNoUnidad(Integer noUnidad) {
        this.noUnidad = noUnidad;
    }

    public BigDecimal getCostoPromedio() {
        return costoPromedio;
    }

    public void setCostoPromedio(BigDecimal costoPromedio) {
        this.costoPromedio = costoPromedio;
    }

    public BigDecimal getCostoLote() {
        return costoLote;
    }

    public void setCostoLote(BigDecimal costoLote) {
        this.costoLote = costoLote;
    }

    public LocalDateTime getFechaIngresoLote() {
        return fechaIngresoLote;
    }

    public void setFechaIngresoLote(LocalDateTime fechaIngresoLote) {
        this.fechaIngresoLote = fechaIngresoLote;
    }

    public String getNoLote() {
        return noLote;
    }

    public void setNoLote(String noLote) {
        this.noLote = noLote;
    }

    public String getNoUbicacion() {
        return noUbicacion;
    }

    public void setNoUbicacion(String noUbicacion) {
        this.noUbicacion = noUbicacion;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Integer getNoImpuesto() {
        return noImpuesto;
    }

    public void setNoImpuesto(Integer noImpuesto) {
        this.noImpuesto = noImpuesto;
    }

    public String getPagaImpuesto() {
        return pagaImpuesto;
    }

    public void setPagaImpuesto(String pagaImpuesto) {
        this.pagaImpuesto = pagaImpuesto;
    }

    public BigDecimal getPrecioNuevoNominal() {
        return precioNuevoNominal;
    }

    public void setPrecioNuevoNominal(BigDecimal precioNuevoNominal) {
        this.precioNuevoNominal = precioNuevoNominal;
    }

    public BigDecimal getPrecioNuevoDolar() {
        return precioNuevoDolar;
    }

    public void setPrecioNuevoDolar(BigDecimal precioNuevoDolar) {
        this.precioNuevoDolar = precioNuevoDolar;
    }

    public LocalDateTime getFechaVencimientoLote() {
        return fechaVencimientoLote;
    }

    public void setFechaVencimientoLote(LocalDateTime fechaVencimientoLote) {
        this.fechaVencimientoLote = fechaVencimientoLote;
    }

    public String getExcento() {
        return excento;
    }

    public void setExcento(String excento) {
        this.excento = excento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getCantidadDevuelta() {
        return cantidadDevuelta;
    }

    public void setCantidadDevuelta(BigDecimal cantidadDevuelta) {
        this.cantidadDevuelta = cantidadDevuelta;
    }

    public String getTipoArticulo() {
        return tipoArticulo;
    }

    public void setTipoArticulo(String tipoArticulo) {
        this.tipoArticulo = tipoArticulo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoPromo() {
        return tipoPromo;
    }

    public void setTipoPromo(String tipoPromo) {
        this.tipoPromo = tipoPromo;
    }

    public BigDecimal getCantidadOferta() {
        return cantidadOferta;
    }

    public void setCantidadOferta(BigDecimal cantidadOferta) {
        this.cantidadOferta = cantidadOferta;
    }

    public BigDecimal getCiaOferta() {
        return ciaOferta;
    }

    public void setCiaOferta(BigDecimal ciaOferta) {
        this.ciaOferta = ciaOferta;
    }

    public BigDecimal getProveedorOferta() {
        return proveedorOferta;
    }

    public void setProveedorOferta(BigDecimal proveedorOferta) {
        this.proveedorOferta = proveedorOferta;
    }

    public BigDecimal getDescuentoCia() {
        return descuentoCia;
    }

    public void setDescuentoCia(BigDecimal descuentoCia) {
        this.descuentoCia = descuentoCia;
    }

    public BigDecimal getDescuentoProve() {
        return descuentoProve;
    }

    public void setDescuentoProve(BigDecimal descuentoProve) {
        this.descuentoProve = descuentoProve;
    }

    public String getNoArticuloOferta() {
        return noArticuloOferta;
    }

    public void setNoArticuloOferta(String noArticuloOferta) {
        this.noArticuloOferta = noArticuloOferta;
    }

    public Integer getNoProveedor() {
        return noProveedor;
    }

    public void setNoProveedor(Integer noProveedor) {
        this.noProveedor = noProveedor;
    }

    public Integer getNoClase() {
        return noClase;
    }

    public void setNoClase(Integer noClase) {
        this.noClase = noClase;
    }

    public BigDecimal getCostoOferta() {
        return costoOferta;
    }

    public void setCostoOferta(BigDecimal costoOferta) {
        this.costoOferta = costoOferta;
    }

    public String getCodigoPromo1() {
        return codigoPromo1;
    }

    public void setCodigoPromo1(String codigoPromo1) {
        this.codigoPromo1 = codigoPromo1;
    }

    public String getCodigoPromo2() {
        return codigoPromo2;
    }

    public void setCodigoPromo2(String codigoPromo2) {
        this.codigoPromo2 = codigoPromo2;
    }

    public BigDecimal getCantidadEq() {
        return cantidadEq;
    }

    public void setCantidadEq(BigDecimal cantidadEq) {
        this.cantidadEq = cantidadEq;
    }

    public Integer getNoUnidadEq() {
        return noUnidadEq;
    }

    public void setNoUnidadEq(Integer noUnidadEq) {
        this.noUnidadEq = noUnidadEq;
    }

    public BigDecimal getAcumulDespacho() {
        return acumulDespacho;
    }

    public void setAcumulDespacho(BigDecimal acumulDespacho) {
        this.acumulDespacho = acumulDespacho;
    }

    public BigDecimal getPendienteDespacho() {
        return pendienteDespacho;
    }

    public void setPendienteDespacho(BigDecimal pendienteDespacho) {
        this.pendienteDespacho = pendienteDespacho;
    }

    public BigDecimal getAcumulDespachoEq() {
        return acumulDespachoEq;
    }

    public void setAcumulDespachoEq(BigDecimal acumulDespachoEq) {
        this.acumulDespachoEq = acumulDespachoEq;
    }

    public BigDecimal getPendienteDespachoEq() {
        return pendienteDespachoEq;
    }

    public void setPendienteDespachoEq(BigDecimal pendienteDespachoEq) {
        this.pendienteDespachoEq = pendienteDespachoEq;
    }

    public String getEstadoDespacho() {
        return estadoDespacho;
    }

    public void setEstadoDespacho(String estadoDespacho) {
        this.estadoDespacho = estadoDespacho;
    }

    public BigDecimal getCantidadDevueltaEq() {
        return cantidadDevueltaEq;
    }

    public void setCantidadDevueltaEq(BigDecimal cantidadDevueltaEq) {
        this.cantidadDevueltaEq = cantidadDevueltaEq;
    }

    public Integer getNoGrupoMercado() {
        return noGrupoMercado;
    }

    public void setNoGrupoMercado(Integer noGrupoMercado) {
        this.noGrupoMercado = noGrupoMercado;
    }

    public Boolean getBloqueadoNc() {
        return bloqueadoNc;
    }

    public void setBloqueadoNc(Boolean bloqueadoNc) {
        this.bloqueadoNc = bloqueadoNc;
    }

    public Integer getNotaCreditoBloqueada() {
        return notaCreditoBloqueada;
    }

    public void setNotaCreditoBloqueada(Integer notaCreditoBloqueada) {
        this.notaCreditoBloqueada = notaCreditoBloqueada;
    }

    public Integer getNoFacturaRefe() {
        return noFacturaRefe;
    }

    public void setNoFacturaRefe(Integer noFacturaRefe) {
        this.noFacturaRefe = noFacturaRefe;
    }

    public Integer getNoLineaRefe() {
        return noLineaRefe;
    }

    public void setNoLineaRefe(Integer noLineaRefe) {
        this.noLineaRefe = noLineaRefe;
    }

    public Integer getNoRazon() {
        return noRazon;
    }

    public void setNoRazon(Integer noRazon) {
        this.noRazon = noRazon;
    }

    public String getTipoDescto() {
        return tipoDescto;
    }

    public void setTipoDescto(String tipoDescto) {
        this.tipoDescto = tipoDescto;
    }

    public String getNoFisicoRefe() {
        return noFisicoRefe;
    }

    public void setNoFisicoRefe(String noFisicoRefe) {
        this.noFisicoRefe = noFisicoRefe;
    }

    public String getEstadoRevision() {
        return estadoRevision;
    }

    public void setEstadoRevision(String estadoRevision) {
        this.estadoRevision = estadoRevision;
    }

    public String getParaMerma() {
        return paraMerma;
    }

    public void setParaMerma(String paraMerma) {
        this.paraMerma = paraMerma;
    }

    public BigDecimal getFactorConversion() {
        return factorConversion;
    }

    public void setFactorConversion(BigDecimal factorConversion) {
        this.factorConversion = factorConversion;
    }

    public Integer getNoGrupoConta() {
        return noGrupoConta;
    }

    public void setNoGrupoConta(Integer noGrupoConta) {
        this.noGrupoConta = noGrupoConta;
    }

    public BigDecimal getPedidoMtsXCaja() {
        return pedidoMtsXCaja;
    }

    public void setPedidoMtsXCaja(BigDecimal pedidoMtsXCaja) {
        this.pedidoMtsXCaja = pedidoMtsXCaja;
    }

    public BigDecimal getAumentoCostoPromedio() {
        return aumentoCostoPromedio;
    }

    public void setAumentoCostoPromedio(BigDecimal aumentoCostoPromedio) {
        this.aumentoCostoPromedio = aumentoCostoPromedio;
    }

    public BigDecimal getCostoPromedioAnt() {
        return costoPromedioAnt;
    }

    public void setCostoPromedioAnt(BigDecimal costoPromedioAnt) {
        this.costoPromedioAnt = costoPromedioAnt;
    }

    public Integer getNoFacturaEx() {
        return noFacturaEx;
    }

    public void setNoFacturaEx(Integer noFacturaEx) {
        this.noFacturaEx = noFacturaEx;
    }

    public Integer getNoCiaCli() {
        return noCiaCli;
    }

    public void setNoCiaCli(Integer noCiaCli) {
        this.noCiaCli = noCiaCli;
    }
}
