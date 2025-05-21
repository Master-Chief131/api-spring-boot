package com.example.demo.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "invweb_bodegas")
@IdClass(BodegaId.class)
public class InvwebBodega implements Serializable {
    @Id
    @Column(name = "NO_CIA")
    private Integer noCia;

    @Id
    @Column(name = "NO_SUCURSAL")
    private Integer noSucursal;

    @Id
    @Column(name = "NO_BODEGA")
    private Integer noBodega;

    @Column(name = "NOMBRE", length = 100)
    private String nombre;

    @Column(name = "ACTIVO", length = 1)
    private String activo;

    @Column(name = "NO_UBICACION", length = 50)
    private String noUbicacion;

    @Column(name = "POR_DESPACHO", length = 1)
    private String porDespacho;

    @Column(name = "VER_PORTAL", length = 1)
    private String verPortal;

    @Column(name = "TIPO_DESPACHO", length = 2)
    private String tipoDespacho;

    @Column(name = "tipo_bodega", length = 2)
    private String tipoBodega;

    @Column(name = "no_temperatura")
    private Integer noTemperatura;

    @Column(name = "ind_traslado_automatico", length = 1)
    private String indTrasladoAutomatico;

    @Column(name = "genera_orden", length = 1)
    private String generaOrden;

    public Integer getNoCia() { return noCia; }
    public void setNoCia(Integer noCia) { this.noCia = noCia; }
    public Integer getNoSucursal() { return noSucursal; }
    public void setNoSucursal(Integer noSucursal) { this.noSucursal = noSucursal; }
    public Integer getNoBodega() { return noBodega; }
    public void setNoBodega(Integer noBodega) { this.noBodega = noBodega; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getActivo() { return activo; }
    public void setActivo(String activo) { this.activo = activo; }
    public String getNoUbicacion() { return noUbicacion; }
    public void setNoUbicacion(String noUbicacion) { this.noUbicacion = noUbicacion; }
    public String getPorDespacho() { return porDespacho; }
    public void setPorDespacho(String porDespacho) { this.porDespacho = porDespacho; }
    public String getVerPortal() { return verPortal; }
    public void setVerPortal(String verPortal) { this.verPortal = verPortal; }
    public String getTipoDespacho() { return tipoDespacho; }
    public void setTipoDespacho(String tipoDespacho) { this.tipoDespacho = tipoDespacho; }
    public String getTipoBodega() { return tipoBodega; }
    public void setTipoBodega(String tipoBodega) { this.tipoBodega = tipoBodega; }
    public Integer getNoTemperatura() { return noTemperatura; }
    public void setNoTemperatura(Integer noTemperatura) { this.noTemperatura = noTemperatura; }
    public String getIndTrasladoAutomatico() { return indTrasladoAutomatico; }
    public void setIndTrasladoAutomatico(String indTrasladoAutomatico) { this.indTrasladoAutomatico = indTrasladoAutomatico; }
    public String getGeneraOrden() { return generaOrden; }
    public void setGeneraOrden(String generaOrden) { this.generaOrden = generaOrden; }
}
