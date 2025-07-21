package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(GrupoClienteId.class)
@Table(name = "genweb_grupo_cliente")
public class GrupoCliente implements Serializable {
    @Id
    @Column(name = "no_cia", nullable = false)
    private Integer noCia;

    @Id
    @Column(name = "no_grupo", length = 2, nullable = false)
    private String noGrupo;

    @Column(name = "descripcion", length = 45)
    private String descripcion;

    @Column(name = "cuenta", length = 50)
    private String cuenta;

    @Column(name = "activo", length = 1)
    private String activo;

    @Column(name = "estatus", length = 1)
    private String estatus;

    @Column(name = "ind_portal", length = 1)
    private String indPortal;

    @Column(name = "por_defecto", length = 1)
    private String porDefecto;

    @Column(name = "ind_inter_cia", length = 1)
    private String indInterCia;

    // Getters and setters
    public Integer getNoCia() {
        return noCia;
    }
    public void setNoCia(Integer noCia) {
        this.noCia = noCia;
    }
    public String getNoGrupo() {
        return noGrupo;
    }
    public void setNoGrupo(String noGrupo) {
        this.noGrupo = noGrupo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getCuenta() {
        return cuenta;
    }
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }
    public String getActivo() {
        return activo;
    }
    public void setActivo(String activo) {
        this.activo = activo;
    }
    public String getEstatus() {
        return estatus;
    }
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    public String getIndPortal() {
        return indPortal;
    }
    public void setIndPortal(String indPortal) {
        this.indPortal = indPortal;
    }
    public String getPorDefecto() {
        return porDefecto;
    }
    public void setPorDefecto(String porDefecto) {
        this.porDefecto = porDefecto;
    }
    public String getIndInterCia() {
        return indInterCia;
    }
    public void setIndInterCia(String indInterCia) {
        this.indInterCia = indInterCia;
    }
}
