package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "invweb_servicio")
@IdClass(ServicioId.class)
public class InvwebServicio implements Serializable {
    @Id
    @Column(name = "no_cia")
    private Integer noCia;

    @Id
    @Column(name = "no_servicio")
    private Integer noServicio;

    @Column(name = "Descripcion", length = 50)
    private String descripcion;

    @Column(name = "no_unidad")
    private Integer noUnidad;

    @Column(name = "Cuenta_Contable", length = 50)
    private String cuentaContable;

    @Column(name = "no_impuesto")
    private Integer noImpuesto;

    @Column(name = "activo")
    private String activo;

    @Column(name = "precio_base")
    private BigDecimal precioBase;

    @Column(name = "porcentaje")
    private BigDecimal porcentaje;

    @Column(name = "limite_oferta")
    @Temporal(TemporalType.DATE)
    private Date limiteOferta;

    @Column(name = "factor")
    private BigDecimal factor;

    @Column(name = "fecha_ultima_venta")
    @Temporal(TemporalType.DATE)
    private Date fechaUltimaVenta;

    @Column(name = "usuario_vendio", length = 50)
    private String usuarioVendio;

    @Column(name = "costo")
    private BigDecimal costo;

    @Column(name = "precio_venta")
    private BigDecimal precioVenta;

    @Column(name = "no_familia")
    private Integer noFamilia;

    @Column(name = "no_sub_familia")
    private Integer noSubFamilia;

    @Column(name = "estado_lista_precio")
    private String estadoListaPrecio;

    @Column(name = "precio_base_lista")
    private BigDecimal precioBaseLista;

    @Column(name = "precio_venta_lista")
    private BigDecimal precioVentaLista;

    @Column(name = "usuario_lista_mod", length = 50)
    private String usuarioListaMod;

    @Column(name = "precio_base_ant")
    private BigDecimal precioBaseAnt;

    @Column(name = "precio_venta_ant")
    private BigDecimal precioVentaAnt;

    @Column(name = "usuario_lista_apr", length = 50)
    private String usuarioListaApr;

    @Column(name = "factor_ant")
    private BigDecimal factorAnt;

    @Column(name = "costo_ant")
    private BigDecimal costoAnt;

    @Column(name = "usuario_crea", length = 50)
    private String usuarioCrea;

    @Column(name = "fecha_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCrea;

    @Column(name = "usuario_mod", length = 50)
    private String usuarioMod;

    @Column(name = "fecha_mod")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMod;

    @Column(name = "acepta_descuento")
    private String aceptaDescuento;

    @Column(name = "ruta_foto", length = 150)
    private String rutaFoto;

    @Column(name = "ind_pago_comision")
    private String indPagoComision;

    @Column(name = "ind_division_area")
    private String indDivisionArea;

    // Getters y setters
    public Integer getNoCia() { return noCia; }
    public void setNoCia(Integer noCia) { this.noCia = noCia; }
    public Integer getNoServicio() { return noServicio; }
    public void setNoServicio(Integer noServicio) { this.noServicio = noServicio; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Integer getNoUnidad() { return noUnidad; }
    public void setNoUnidad(Integer noUnidad) { this.noUnidad = noUnidad; }
    public String getCuentaContable() { return cuentaContable; }
    public void setCuentaContable(String cuentaContable) { this.cuentaContable = cuentaContable; }
    public Integer getNoImpuesto() { return noImpuesto; }
    public void setNoImpuesto(Integer noImpuesto) { this.noImpuesto = noImpuesto; }
    public String getActivo() { return activo; }
    public void setActivo(String activo) { this.activo = activo; }
    public java.math.BigDecimal getPrecioBase() { return precioBase; }
    public void setPrecioBase(java.math.BigDecimal precioBase) { this.precioBase = precioBase; }
    public java.math.BigDecimal getPorcentaje() { return porcentaje; }
    public void setPorcentaje(java.math.BigDecimal porcentaje) { this.porcentaje = porcentaje; }
    public java.util.Date getLimiteOferta() { return limiteOferta; }
    public void setLimiteOferta(java.util.Date limiteOferta) { this.limiteOferta = limiteOferta; }
    public java.math.BigDecimal getFactor() { return factor; }
    public void setFactor(java.math.BigDecimal factor) { this.factor = factor; }
    public java.util.Date getFechaUltimaVenta() { return fechaUltimaVenta; }
    public void setFechaUltimaVenta(java.util.Date fechaUltimaVenta) { this.fechaUltimaVenta = fechaUltimaVenta; }
    public String getUsuarioVendio() { return usuarioVendio; }
    public void setUsuarioVendio(String usuarioVendio) { this.usuarioVendio = usuarioVendio; }
    public java.math.BigDecimal getCosto() { return costo; }
    public void setCosto(java.math.BigDecimal costo) { this.costo = costo; }
    public java.math.BigDecimal getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(java.math.BigDecimal precioVenta) { this.precioVenta = precioVenta; }
    public Integer getNoFamilia() { return noFamilia; }
    public void setNoFamilia(Integer noFamilia) { this.noFamilia = noFamilia; }
    public Integer getNoSubFamilia() { return noSubFamilia; }
    public void setNoSubFamilia(Integer noSubFamilia) { this.noSubFamilia = noSubFamilia; }
    public String getEstadoListaPrecio() { return estadoListaPrecio; }
    public void setEstadoListaPrecio(String estadoListaPrecio) { this.estadoListaPrecio = estadoListaPrecio; }
    public java.math.BigDecimal getPrecioBaseLista() { return precioBaseLista; }
    public void setPrecioBaseLista(java.math.BigDecimal precioBaseLista) { this.precioBaseLista = precioBaseLista; }
    public java.math.BigDecimal getPrecioVentaLista() { return precioVentaLista; }
    public void setPrecioVentaLista(java.math.BigDecimal precioVentaLista) { this.precioVentaLista = precioVentaLista; }
    public String getUsuarioListaMod() { return usuarioListaMod; }
    public void setUsuarioListaMod(String usuarioListaMod) { this.usuarioListaMod = usuarioListaMod; }
    public java.math.BigDecimal getPrecioBaseAnt() { return precioBaseAnt; }
    public void setPrecioBaseAnt(java.math.BigDecimal precioBaseAnt) { this.precioBaseAnt = precioBaseAnt; }
    public java.math.BigDecimal getPrecioVentaAnt() { return precioVentaAnt; }
    public void setPrecioVentaAnt(java.math.BigDecimal precioVentaAnt) { this.precioVentaAnt = precioVentaAnt; }
    public String getUsuarioListaApr() { return usuarioListaApr; }
    public void setUsuarioListaApr(String usuarioListaApr) { this.usuarioListaApr = usuarioListaApr; }
    public java.math.BigDecimal getFactorAnt() { return factorAnt; }
    public void setFactorAnt(java.math.BigDecimal factorAnt) { this.factorAnt = factorAnt; }
    public java.math.BigDecimal getCostoAnt() { return costoAnt; }
    public void setCostoAnt(java.math.BigDecimal costoAnt) { this.costoAnt = costoAnt; }
    public String getUsuarioCrea() { return usuarioCrea; }
    public void setUsuarioCrea(String usuarioCrea) { this.usuarioCrea = usuarioCrea; }
    public java.util.Date getFechaCrea() { return fechaCrea; }
    public void setFechaCrea(java.util.Date fechaCrea) { this.fechaCrea = fechaCrea; }
    public String getUsuarioMod() { return usuarioMod; }
    public void setUsuarioMod(String usuarioMod) { this.usuarioMod = usuarioMod; }
    public java.util.Date getFechaMod() { return fechaMod; }
    public void setFechaMod(java.util.Date fechaMod) { this.fechaMod = fechaMod; }
    public String getAceptaDescuento() { return aceptaDescuento; }
    public void setAceptaDescuento(String aceptaDescuento) { this.aceptaDescuento = aceptaDescuento; }
    public String getRutaFoto() { return rutaFoto; }
    public void setRutaFoto(String rutaFoto) { this.rutaFoto = rutaFoto; }
    public String getIndPagoComision() { return indPagoComision; }
    public void setIndPagoComision(String indPagoComision) { this.indPagoComision = indPagoComision; }
    public String getIndDivisionArea() { return indDivisionArea; }
    public void setIndDivisionArea(String indDivisionArea) { this.indDivisionArea = indDivisionArea; }
}
