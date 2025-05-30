package com.example.demo.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "facweb_grupo_mercado")
@IdClass(FacwebGrupoMercadoId.class)
public class FacwebGrupoMercado implements Serializable {
    @Id
    @Column(name = "no_cia")
    private Integer noCia;

    @Id
    @Column(name = "no_grupo")
    private Integer noGrupo;

    @Column(name = "Nombre", length = 100)
    private String nombre;

    @Column(name = "Activo", length = 1)
    private String activo;

    @Column(name = "Tipo_Descuento", length = 1)
    private String tipoDescuento;

    @Column(name = "Descuento_global", length = 1)
    private String descuentoGlobal;

    @Column(name = "Porcentaje", precision = 21, scale = 2)
    private BigDecimal porcentaje;

    @Column(name = "Descuento_Articulo", length = 1)
    private String descuentoArticulo;

    @Column(name = "limite_fecha")
    @Temporal(TemporalType.DATE)
    private Date limiteFecha;

    @Column(name = "por_defecto", length = 1)
    private String porDefecto;

    @Column(name = "ind_portal", length = 1)
    private String indPortal;

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

    @Column(name = "estado_lista_precio", length = 1)
    private String estadoListaPrecio;

    @Column(name = "para_cliente", length = 2)
    private String paraCliente;

    // Getters y setters
    public Integer getNoCia() { return noCia; }
    public void setNoCia(Integer noCia) { this.noCia = noCia; }
    public Integer getNoGrupo() { return noGrupo; }
    public void setNoGrupo(Integer noGrupo) { this.noGrupo = noGrupo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getActivo() { return activo; }
    public void setActivo(String activo) { this.activo = activo; }
    public String getTipoDescuento() { return tipoDescuento; }
    public void setTipoDescuento(String tipoDescuento) { this.tipoDescuento = tipoDescuento; }
    public String getDescuentoGlobal() { return descuentoGlobal; }
    public void setDescuentoGlobal(String descuentoGlobal) { this.descuentoGlobal = descuentoGlobal; }
    public BigDecimal getPorcentaje() { return porcentaje; }
    public void setPorcentaje(BigDecimal porcentaje) { this.porcentaje = porcentaje; }
    public String getDescuentoArticulo() { return descuentoArticulo; }
    public void setDescuentoArticulo(String descuentoArticulo) { this.descuentoArticulo = descuentoArticulo; }
    public Date getLimiteFecha() { return limiteFecha; }
    public void setLimiteFecha(Date limiteFecha) { this.limiteFecha = limiteFecha; }
    public String getPorDefecto() { return porDefecto; }
    public void setPorDefecto(String porDefecto) { this.porDefecto = porDefecto; }
    public String getIndPortal() { return indPortal; }
    public void setIndPortal(String indPortal) { this.indPortal = indPortal; }
    public String getUsuarioCrea() { return usuarioCrea; }
    public void setUsuarioCrea(String usuarioCrea) { this.usuarioCrea = usuarioCrea; }
    public Date getFechaCrea() { return fechaCrea; }
    public void setFechaCrea(Date fechaCrea) { this.fechaCrea = fechaCrea; }
    public String getUsuarioMod() { return usuarioMod; }
    public void setUsuarioMod(String usuarioMod) { this.usuarioMod = usuarioMod; }
    public Date getFechaMod() { return fechaMod; }
    public void setFechaMod(Date fechaMod) { this.fechaMod = fechaMod; }
    public String getEstadoListaPrecio() { return estadoListaPrecio; }
    public void setEstadoListaPrecio(String estadoListaPrecio) { this.estadoListaPrecio = estadoListaPrecio; }
    public String getParaCliente() { return paraCliente; }
    public void setParaCliente(String paraCliente) { this.paraCliente = paraCliente; }
}
