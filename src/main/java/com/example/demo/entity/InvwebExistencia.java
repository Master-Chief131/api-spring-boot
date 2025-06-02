package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "invweb_existencia")
@IdClass(InvwebExistenciaId.class)
public class InvwebExistencia implements Serializable {
    @Id
    @Column(name = "no_cia")
    private Integer noCia;

    @Id
    @Column(name = "No_sucursal")
    private Integer noSucursal;

    @Id
    @Column(name = "No_Bodega")
    private Integer noBodega;

    @Id
    @Column(name = "No_articulo", length = 50)
    private String noArticulo;

    @Column(name = "No_unidad")
    private Integer noUnidad;

    @Column(name = "No_ubicacion", length = 50)
    private String noUbicacion;

    @Column(name = "cantidad", precision = 19, scale = 6)
    private BigDecimal cantidad;

    @Column(name = "entrada_pendiente", precision = 19, scale = 6)
    private BigDecimal entradaPendiente;

    @Column(name = "salida_pendiente", precision = 19, scale = 6)
    private BigDecimal salidaPendiente;

    @Column(name = "Costo_promedio", precision = 20, scale = 10)
    private BigDecimal costoPromedio;

    @Column(name = "Precio_Venta", precision = 15, scale = 6)
    private BigDecimal precioVenta;

    @Column(name = "En_Toma", length = 1)
    private String enToma;

    @Column(name = "Precio_Base", precision = 15, scale = 6)
    private BigDecimal precioBase;

    @Column(name = "Porcentaje", precision = 15, scale = 2)
    private BigDecimal porcentaje;

    @Column(name = "Limite_Oferta")
    private java.sql.Date limiteOferta;

    @Column(name = "Factor", precision = 15, scale = 2)
    private BigDecimal factor;

    @Column(name = "fecha_ultima_compra")
    private LocalDateTime fechaUltimaCompra;

    @Column(name = "fecha_ultima_venta")
    private LocalDateTime fechaUltimaVenta;

    @Column(name = "usuario_compra", length = 50)
    private String usuarioCompra;

    @Column(name = "usuario_vendio", length = 50)
    private String usuarioVendio;

    @Column(name = "Ultimo_Precio", precision = 15, scale = 6)
    private BigDecimal ultimoPrecio;

    @Column(name = "fecha_ultimo_traslado")
    private LocalDateTime fechaUltimoTraslado;

    @Column(name = "Compra_pendiente", precision = 15, scale = 6)
    private BigDecimal compraPendiente;

    @Column(name = "ind_reorden", length = 1)
    private String indReorden;

    @Column(name = "fecha_ultima_produccion")
    private LocalDateTime fechaUltimaProduccion;

    @Column(name = "usuario_ultima_produccion", length = 50)
    private String usuarioUltimaProduccion;

    // Getters y setters generados automáticamente
    public Integer getNoCia() {
        return noCia;
    }

    public void setNoCia(Integer noCia) {
        this.noCia = noCia;
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

    public String getNoArticulo() {
        return noArticulo;
    }

    public void setNoArticulo(String noArticulo) {
        this.noArticulo = noArticulo;
    }

    public Integer getNoUnidad() {
        return noUnidad;
    }

    public void setNoUnidad(Integer noUnidad) {
        this.noUnidad = noUnidad;
    }

    public String getNoUbicacion() {
        return noUbicacion;
    }

    public void setNoUbicacion(String noUbicacion) {
        this.noUbicacion = noUbicacion;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getEntradaPendiente() {
        return entradaPendiente;
    }

    public void setEntradaPendiente(BigDecimal entradaPendiente) {
        this.entradaPendiente = entradaPendiente;
    }

    public BigDecimal getSalidaPendiente() {
        return salidaPendiente;
    }

    public void setSalidaPendiente(BigDecimal salidaPendiente) {
        this.salidaPendiente = salidaPendiente;
    }

    public BigDecimal getCostoPromedio() {
        return costoPromedio;
    }

    public void setCostoPromedio(BigDecimal costoPromedio) {
        this.costoPromedio = costoPromedio;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getEnToma() {
        return enToma;
    }

    public void setEnToma(String enToma) {
        this.enToma = enToma;
    }

    public BigDecimal getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(BigDecimal precioBase) {
        this.precioBase = precioBase;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public java.sql.Date getLimiteOferta() {
        return limiteOferta;
    }

    public void setLimiteOferta(java.sql.Date limiteOferta) {
        this.limiteOferta = limiteOferta;
    }

    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }

    public LocalDateTime getFechaUltimaCompra() {
        return fechaUltimaCompra;
    }

    public void setFechaUltimaCompra(LocalDateTime fechaUltimaCompra) {
        this.fechaUltimaCompra = fechaUltimaCompra;
    }

    public LocalDateTime getFechaUltimaVenta() {
        return fechaUltimaVenta;
    }

    public void setFechaUltimaVenta(LocalDateTime fechaUltimaVenta) {
        this.fechaUltimaVenta = fechaUltimaVenta;
    }

    public String getUsuarioCompra() {
        return usuarioCompra;
    }

    public void setUsuarioCompra(String usuarioCompra) {
        this.usuarioCompra = usuarioCompra;
    }

    public String getUsuarioVendio() {
        return usuarioVendio;
    }

    public void setUsuarioVendio(String usuarioVendio) {
        this.usuarioVendio = usuarioVendio;
    }

    public BigDecimal getUltimoPrecio() {
        return ultimoPrecio;
    }

    public void setUltimoPrecio(BigDecimal ultimoPrecio) {
        this.ultimoPrecio = ultimoPrecio;
    }

    public LocalDateTime getFechaUltimoTraslado() {
        return fechaUltimoTraslado;
    }

    public void setFechaUltimoTraslado(LocalDateTime fechaUltimoTraslado) {
        this.fechaUltimoTraslado = fechaUltimoTraslado;
    }

    public BigDecimal getCompraPendiente() {
        return compraPendiente;
    }

    public void setCompraPendiente(BigDecimal compraPendiente) {
        this.compraPendiente = compraPendiente;
    }

    public String getIndReorden() {
        return indReorden;
    }

    public void setIndReorden(String indReorden) {
        this.indReorden = indReorden;
    }

    public LocalDateTime getFechaUltimaProduccion() {
        return fechaUltimaProduccion;
    }

    public void setFechaUltimaProduccion(LocalDateTime fechaUltimaProduccion) {
        this.fechaUltimaProduccion = fechaUltimaProduccion;
    }

    public String getUsuarioUltimaProduccion() {
        return usuarioUltimaProduccion;
    }

    public void setUsuarioUltimaProduccion(String usuarioUltimaProduccion) {
        this.usuarioUltimaProduccion = usuarioUltimaProduccion;
    }
}
