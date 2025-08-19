package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "invweb_articulo")
@IdClass(InvwebArticuloId.class)
public class InvwebArticulo implements Serializable {
    @Id
    @Column(name = "no_cia")
    private Integer noCia;

    @Id
    @Column(name = "no_articulo", length = 50)
    private String noArticulo;

    @Column(name = "nombre_largo", length = 500)
    private String nombreLargo;

    @Column(name = "nombre_corto", length = 500)
    private String nombreCorto;

    @Column(name = "no_unidad")
    private Integer noUnidad;

    @Column(name = "peso", precision = 9, scale = 2)
    private BigDecimal peso;

    @Column(name = "no_tamano")
    private Integer noTamano;

    @Column(name = "no_color")
    private Integer noColor;

    @Column(name = "no_familia")
    private Integer noFamilia;

    @Column(name = "no_sub_familia")
    private Integer noSubFamilia;

    @Column(name = "no_marca")
    private Integer noMarca;

    @Column(name = "no_modelo")
    private Integer noModelo;

    @Column(name = "metodo_costo", length = 1)
    private String metodoCosto;

    @Column(name = "no_proveedor_principal")
    private Integer noProveedorPrincipal;

    @Column(name = "ruta_foto", length = 150)
    private String rutaFoto;

    @Column(name = "Cuenta_Contable")
    private Integer cuentaContable;

    @Column(name = "tipo_articulo", length = 1)
    private String tipoArticulo;

    @Column(name = "es_codigobarra", length = 1)
    private String esCodigobarra;

    @Column(name = "serie", length = 1)
    private String serie;

    @Column(name = "no_metal")
    private Integer noMetal;

    @Column(name = "activo", length = 1)
    private String activo;

    @Column(name = "no_clasificacion", length = 3)
    private String noClasificacion;

    @Column(name = "fecha_ultima_compra")
    private LocalDateTime fechaUltimaCompra;

    @Column(name = "fecha_ultima_venta")
    private LocalDateTime fechaUltimaVenta;

    @Column(name = "usuario_ultima_compra", length = 50)
    private String usuarioUltimaCompra;

    @Column(name = "usuario_ultima_venta", length = 50)
    private String usuarioUltimaVenta;

    @Column(name = "excento", length = 1)
    private String excento;

    @Column(name = "no_impuesto")
    private Integer noImpuesto;

    @Column(name = "no_clase")
    private Integer noClase;

    @Column(name = "ind_kit", length = 1)
    private String indKit;

    @Column(name = "exento_compra", length = 1)
    private String exentoCompra;

    @Column(name = "exento_venta", length = 1)
    private String exentoVenta;

    @Column(name = "impuesto_parte_costo", length = 1)
    private String impuestoParteCosto;

    @Column(name = "precio_base", precision = 15, scale = 6)
    private BigDecimal precioBase;

    @Column(name = "acepta_descuento", length = 1)
    private String aceptaDescuento;

    @Column(name = "no_unidad_compra")
    private Integer noUnidadCompra;

    @Column(name = "no_unidad_venta")
    private Integer noUnidadVenta;

    @Column(name = "ind_lote", length = 1)
    private String indLote;

    @Column(name = "no_sub_subfamilia")
    private Integer noSubSubfamilia;

    @Column(name = "motor_chasis", length = 100)
    private String motorChasis;

    @Column(name = "detalle_vehiculo", length = 100)
    private String detalleVehiculo;

    @Column(name = "ano_vehiculo", length = 25)
    private String anoVehiculo;

    @Column(name = "voltaje", length = 50)
    private String voltaje;

    @Column(name = "diodos", length = 50)
    private String diodos;

    @Column(name = "precio_ultima_venta", precision = 15, scale = 6)
    private BigDecimal precioUltimaVenta;

    @Column(name = "precio_venta", precision = 15, scale = 6)
    private BigDecimal precioVenta;

    @Column(name = "porcentaje", precision = 15, scale = 6)
    private BigDecimal porcentaje;

    @Column(name = "factor", precision = 15, scale = 6)
    private BigDecimal factor;

    @Column(name = "medida", length = 100)
    private String medida;

    @Column(name = "unidad_peso", length = 50)
    private String unidadPeso;

    @Column(name = "no_arti_ant", length = 50)
    private String noArtiAnt;

    @Column(name = "usuario_crea", length = 50)
    private String usuarioCrea;

    @Column(name = "fecha_crea")
    private LocalDateTime fechaCrea;

    @Column(name = "usuario_mod", length = 50)
    private String usuarioMod;

    @Column(name = "fecha_mod")
    private LocalDateTime fechaMod;

    @Column(name = "no_arti_nuevo", length = 50)
    private String noArtiNuevo;

    @Column(name = "referencias", length = 100)
    private String referencias;

    @Column(name = "lester", length = 50)
    private String lester;

    @Column(name = "atributo1", length = 50)
    private String atributo1;

    @Column(name = "no_temperatura")
    private Integer noTemperatura;

    @Column(name = "estado_lista_precio", length = 1)
    private String estadoListaPrecio;

    @Column(name = "precio_base_lista", precision = 15, scale = 2)
    private BigDecimal precioBaseLista;

    @Column(name = "precio_venta_lista", precision = 15, scale = 2)
    private BigDecimal precioVentaLista;

    @Column(name = "usuario_lista_mod", length = 50)
    private String usuarioListaMod;

    @Column(name = "precio_base_ant", precision = 15, scale = 2)
    private BigDecimal precioBaseAnt;

    @Column(name = "precio_venta_ant", precision = 15, scale = 2)
    private BigDecimal precioVentaAnt;

    @Column(name = "usuario_lista_apr", length = 50)
    private String usuarioListaApr;

    @Column(name = "factor_ant", precision = 15, scale = 6)
    private BigDecimal factorAnt;

    @Column(name = "costo_ant", precision = 15, scale = 6)
    private BigDecimal costoAnt;

    @Column(name = "no_aranceles", length = 50)
    private String noAranceles;

    @Column(name = "dias_vencimiento_lote")
    private Integer diasVencimientoLote;

    @Column(name = "descrip_tamano", length = 100)
    private String descripTamano;

    @Column(name = "copia_ant", length = 50)
    private String copiaAnt;

    @Column(name = "articulo_errado", length = 50)
    private String articuloErrado;

    @Column(name = "no_arti_amt", length = 50)
    private String noArtiAmt;

    @Column(name = "costo_tmp_devolucion", precision = 15, scale = 6)
    private BigDecimal costoTmpDevolucion;

    @Column(name = "no_unidad_volumen")
    private Integer noUnidadVolumen;

    @Column(name = "medida_volumen", precision = 10, scale = 6)
    private BigDecimal medidaVolumen;

    @Column(name = "mts_x_caja", precision = 9, scale = 6)
    private BigDecimal mtsXCaja;

    @Column(name = "fecha_ultima_produccion")
    private LocalDateTime fechaUltimaProduccion;

    @Column(name = "usuario_ultima_produccion", length = 50)
    private String usuarioUltimaProduccion;

    @Column(name = "ver_portal", length = 1)
    private String verPortal;

    public Integer getNoCia() { return noCia; }
    public void setNoCia(Integer noCia) { this.noCia = noCia; }
    public String getNoArticulo() { return noArticulo; }
    public void setNoArticulo(String noArticulo) { this.noArticulo = noArticulo; }
    public String getNombreLargo() { return nombreLargo; }
    public void setNombreLargo(String nombreLargo) { this.nombreLargo = nombreLargo; }
    public String getNombreCorto() { return nombreCorto; }
    public void setNombreCorto(String nombreCorto) { this.nombreCorto = nombreCorto; }
    public Integer getNoUnidad() { return noUnidad; }
    public void setNoUnidad(Integer noUnidad) { this.noUnidad = noUnidad; }
    public BigDecimal getPeso() { return peso; }
    public void setPeso(BigDecimal peso) { this.peso = peso; }
    public Integer getNoTamano() { return noTamano; }
    public void setNoTamano(Integer noTamano) { this.noTamano = noTamano; }
    public Integer getNoColor() { return noColor; }
    public void setNoColor(Integer noColor) { this.noColor = noColor; }
    public Integer getNoFamilia() { return noFamilia; }
    public void setNoFamilia(Integer noFamilia) { this.noFamilia = noFamilia; }
    public Integer getNoSubFamilia() { return noSubFamilia; }
    public void setNoSubFamilia(Integer noSubFamilia) { this.noSubFamilia = noSubFamilia; }
    public Integer getNoMarca() { return noMarca; }
    public void setNoMarca(Integer noMarca) { this.noMarca = noMarca; }
    public Integer getNoModelo() { return noModelo; }
    public void setNoModelo(Integer noModelo) { this.noModelo = noModelo; }
    public String getMetodoCosto() { return metodoCosto; }
    public void setMetodoCosto(String metodoCosto) { this.metodoCosto = metodoCosto; }
    public Integer getNoProveedorPrincipal() { return noProveedorPrincipal; }
    public void setNoProveedorPrincipal(Integer noProveedorPrincipal) { this.noProveedorPrincipal = noProveedorPrincipal; }
    public String getRutaFoto() { return rutaFoto; }
    public void setRutaFoto(String rutaFoto) { this.rutaFoto = rutaFoto; }
    public Integer getCuentaContable() { return cuentaContable; }
    public void setCuentaContable(Integer cuentaContable) { this.cuentaContable = cuentaContable; }
    public String getTipoArticulo() { return tipoArticulo; }
    public void setTipoArticulo(String tipoArticulo) { this.tipoArticulo = tipoArticulo; }
    public String getEsCodigobarra() { return esCodigobarra; }
    public void setEsCodigobarra(String esCodigobarra) { this.esCodigobarra = esCodigobarra; }
    public String getSerie() { return serie; }
    public void setSerie(String serie) { this.serie = serie; }
    public Integer getNoMetal() { return noMetal; }
    public void setNoMetal(Integer noMetal) { this.noMetal = noMetal; }
    public String getActivo() { return activo; }
    public void setActivo(String activo) { this.activo = activo; }
    public String getNoClasificacion() { return noClasificacion; }
    public void setNoClasificacion(String noClasificacion) { this.noClasificacion = noClasificacion; }
    public LocalDateTime getFechaUltimaCompra() { return fechaUltimaCompra; }
    public void setFechaUltimaCompra(LocalDateTime fechaUltimaCompra) { this.fechaUltimaCompra = fechaUltimaCompra; }
    public LocalDateTime getFechaUltimaVenta() { return fechaUltimaVenta; }
    public void setFechaUltimaVenta(LocalDateTime fechaUltimaVenta) { this.fechaUltimaVenta = fechaUltimaVenta; }
    public String getUsuarioUltimaCompra() { return usuarioUltimaCompra; }
    public void setUsuarioUltimaCompra(String usuarioUltimaCompra) { this.usuarioUltimaCompra = usuarioUltimaCompra; }
    public String getUsuarioUltimaVenta() { return usuarioUltimaVenta; }
    public void setUsuarioUltimaVenta(String usuarioUltimaVenta) { this.usuarioUltimaVenta = usuarioUltimaVenta; }
    public String getExcento() { return excento; }
    public void setExcento(String excento) { this.excento = excento; }
    public Integer getNoImpuesto() { return noImpuesto; }
    public void setNoImpuesto(Integer noImpuesto) { this.noImpuesto = noImpuesto; }
    public Integer getNoClase() { return noClase; }
    public void setNoClase(Integer noClase) { this.noClase = noClase; }
    public String getIndKit() { return indKit; }
    public void setIndKit(String indKit) { this.indKit = indKit; }
    public String getExentoCompra() { return exentoCompra; }
    public void setExentoCompra(String exentoCompra) { this.exentoCompra = exentoCompra; }
    public String getExentoVenta() { return exentoVenta; }
    public void setExentoVenta(String exentoVenta) { this.exentoVenta = exentoVenta; }
    public String getImpuestoParteCosto() { return impuestoParteCosto; }
    public void setImpuestoParteCosto(String impuestoParteCosto) { this.impuestoParteCosto = impuestoParteCosto; }
    public BigDecimal getPrecioBase() { return precioBase; }
    public void setPrecioBase(BigDecimal precioBase) { this.precioBase = precioBase; }
    public String getAceptaDescuento() { return aceptaDescuento; }
    public void setAceptaDescuento(String aceptaDescuento) { this.aceptaDescuento = aceptaDescuento; }
    public Integer getNoUnidadCompra() { return noUnidadCompra; }
    public void setNoUnidadCompra(Integer noUnidadCompra) { this.noUnidadCompra = noUnidadCompra; }
    public Integer getNoUnidadVenta() { return noUnidadVenta; }
    public void setNoUnidadVenta(Integer noUnidadVenta) { this.noUnidadVenta = noUnidadVenta; }
    public String getIndLote() { return indLote; }
    public void setIndLote(String indLote) { this.indLote = indLote; }
    public Integer getNoSubSubfamilia() { return noSubSubfamilia; }
    public void setNoSubSubfamilia(Integer noSubSubfamilia) { this.noSubSubfamilia = noSubSubfamilia; }
    public String getMotorChasis() { return motorChasis; }
    public void setMotorChasis(String motorChasis) { this.motorChasis = motorChasis; }
    public String getDetalleVehiculo() { return detalleVehiculo; }
    public void setDetalleVehiculo(String detalleVehiculo) { this.detalleVehiculo = detalleVehiculo; }
    public String getAnoVehiculo() { return anoVehiculo; }
    public void setAnoVehiculo(String anoVehiculo) { this.anoVehiculo = anoVehiculo; }
    public String getVoltaje() { return voltaje; }
    public void setVoltaje(String voltaje) { this.voltaje = voltaje; }
    public String getDiodos() { return diodos; }
    public void setDiodos(String diodos) { this.diodos = diodos; }
    public BigDecimal getPrecioUltimaVenta() { return precioUltimaVenta; }
    public void setPrecioUltimaVenta(BigDecimal precioUltimaVenta) { this.precioUltimaVenta = precioUltimaVenta; }
    public BigDecimal getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(BigDecimal precioVenta) { this.precioVenta = precioVenta; }
    public BigDecimal getPorcentaje() { return porcentaje; }
    public void setPorcentaje(BigDecimal porcentaje) { this.porcentaje = porcentaje; }
    public BigDecimal getFactor() { return factor; }
    public void setFactor(BigDecimal factor) { this.factor = factor; }
    public String getMedida() { return medida; }
    public void setMedida(String medida) { this.medida = medida; }
    public String getUnidadPeso() { return unidadPeso; }
    public void setUnidadPeso(String unidadPeso) { this.unidadPeso = unidadPeso; }
    public String getNoArtiAnt() { return noArtiAnt; }
    public void setNoArtiAnt(String noArtiAnt) { this.noArtiAnt = noArtiAnt; }
    public String getUsuarioCrea() { return usuarioCrea; }
    public void setUsuarioCrea(String usuarioCrea) { this.usuarioCrea = usuarioCrea; }
    public LocalDateTime getFechaCrea() { return fechaCrea; }
    public void setFechaCrea(LocalDateTime fechaCrea) { this.fechaCrea = fechaCrea; }
    public String getUsuarioMod() { return usuarioMod; }
    public void setUsuarioMod(String usuarioMod) { this.usuarioMod = usuarioMod; }
    public LocalDateTime getFechaMod() { return fechaMod; }
    public void setFechaMod(LocalDateTime fechaMod) { this.fechaMod = fechaMod; }
    public String getNoArtiNuevo() { return noArtiNuevo; }
    public void setNoArtiNuevo(String noArtiNuevo) { this.noArtiNuevo = noArtiNuevo; }
    public String getReferencias() { return referencias; }
    public void setReferencias(String referencias) { this.referencias = referencias; }
    public String getLester() { return lester; }
    public void setLester(String lester) { this.lester = lester; }
    public String getAtributo1() { return atributo1; }
    public void setAtributo1(String atributo1) { this.atributo1 = atributo1; }
    public Integer getNoTemperatura() { return noTemperatura; }
    public void setNoTemperatura(Integer noTemperatura) { this.noTemperatura = noTemperatura; }
    public String getEstadoListaPrecio() { return estadoListaPrecio; }
    public void setEstadoListaPrecio(String estadoListaPrecio) { this.estadoListaPrecio = estadoListaPrecio; }
    public BigDecimal getPrecioBaseLista() { return precioBaseLista; }
    public void setPrecioBaseLista(BigDecimal precioBaseLista) { this.precioBaseLista = precioBaseLista; }
    public BigDecimal getPrecioVentaLista() { return precioVentaLista; }
    public void setPrecioVentaLista(BigDecimal precioVentaLista) { this.precioVentaLista = precioVentaLista; }
    public String getUsuarioListaMod() { return usuarioListaMod; }
    public void setUsuarioListaMod(String usuarioListaMod) { this.usuarioListaMod = usuarioListaMod; }
    public BigDecimal getPrecioBaseAnt() { return precioBaseAnt; }
    public void setPrecioBaseAnt(BigDecimal precioBaseAnt) { this.precioBaseAnt = precioBaseAnt; }
    public BigDecimal getPrecioVentaAnt() { return precioVentaAnt; }
    public void setPrecioVentaAnt(BigDecimal precioVentaAnt) { this.precioVentaAnt = precioVentaAnt; }
    public String getUsuarioListaApr() { return usuarioListaApr; }
    public void setUsuarioListaApr(String usuarioListaApr) { this.usuarioListaApr = usuarioListaApr; }
    public BigDecimal getFactorAnt() { return factorAnt; }
    public void setFactorAnt(BigDecimal factorAnt) { this.factorAnt = factorAnt; }
    public BigDecimal getCostoAnt() { return costoAnt; }
    public void setCostoAnt(BigDecimal costoAnt) { this.costoAnt = costoAnt; }
    public String getNoAranceles() { return noAranceles; }
    public void setNoAranceles(String noAranceles) { this.noAranceles = noAranceles; }
    public Integer getDiasVencimientoLote() { return diasVencimientoLote; }
    public void setDiasVencimientoLote(Integer diasVencimientoLote) { this.diasVencimientoLote = diasVencimientoLote; }
    public String getDescripTamano() { return descripTamano; }
    public void setDescripTamano(String descripTamano) { this.descripTamano = descripTamano; }
    public String getCopiaAnt() { return copiaAnt; }
    public void setCopiaAnt(String copiaAnt) { this.copiaAnt = copiaAnt; }
    public String getArticuloErrado() { return articuloErrado; }
    public void setArticuloErrado(String articuloErrado) { this.articuloErrado = articuloErrado; }
    public String getNoArtiAmt() { return noArtiAmt; }
    public void setNoArtiAmt(String noArtiAmt) { this.noArtiAmt = noArtiAmt; }
    public BigDecimal getCostoTmpDevolucion() { return costoTmpDevolucion; }
    public void setCostoTmpDevolucion(BigDecimal costoTmpDevolucion) { this.costoTmpDevolucion = costoTmpDevolucion; }
    public Integer getNoUnidadVolumen() { return noUnidadVolumen; }
    public void setNoUnidadVolumen(Integer noUnidadVolumen) { this.noUnidadVolumen = noUnidadVolumen; }
    public BigDecimal getMedidaVolumen() { return medidaVolumen; }
    public void setMedidaVolumen(BigDecimal medidaVolumen) { this.medidaVolumen = medidaVolumen; }
    public BigDecimal getMtsXCaja() { return mtsXCaja; }
    public void setMtsXCaja(BigDecimal mtsXCaja) { this.mtsXCaja = mtsXCaja; }
    public LocalDateTime getFechaUltimaProduccion() { return fechaUltimaProduccion; }
    public void setFechaUltimaProduccion(LocalDateTime fechaUltimaProduccion) { this.fechaUltimaProduccion = fechaUltimaProduccion; }
    public String getUsuarioUltimaProduccion() { return usuarioUltimaProduccion; }
    public void setUsuarioUltimaProduccion(String usuarioUltimaProduccion) { this.usuarioUltimaProduccion = usuarioUltimaProduccion; }
    public String getVerPortal() { return verPortal; }
    public void setVerPortal(String verPortal) { this.verPortal = verPortal; }
}
