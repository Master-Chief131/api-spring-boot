package com.example.demo.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "invweb_subfamilia")
@IdClass(SubfamiliaId.class)
public class InvwebSubfamilia implements Serializable {
    @Id
    @Column(name = "no_cia")
    private Integer noCia;

    @Id
    @Column(name = "no_familia")
    private Integer noFamilia;

    @Id
    @Column(name = "no_subfamilia")
    private Integer noSubfamilia;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "activo", length = 1)
    private String activo;

    @Column(name = "identificador", length = 3)
    private String identificador;

    @Column(name = "secuencia")
    private Integer secuencia;

    @Transient
    private String imagenUrl;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "no_cia", referencedColumnName = "no_cia", insertable = false, updatable = false),
        @JoinColumn(name = "no_familia", referencedColumnName = "no_familia", insertable = false, updatable = false),
        @JoinColumn(name = "no_subfamilia", referencedColumnName = "no_subfamilia", insertable = false, updatable = false)
    })
    private java.util.List<InvwebSubSubfamilia> subSubfamilias;

    public Integer getNoCia() { return noCia; }
    public void setNoCia(Integer noCia) { this.noCia = noCia; }

    public Integer getNoFamilia() { return noFamilia; }
    public void setNoFamilia(Integer noFamilia) { this.noFamilia = noFamilia; }

    public Integer getNoSubfamilia() { return noSubfamilia; }
    public void setNoSubfamilia(Integer noSubfamilia) { this.noSubfamilia = noSubfamilia; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getActivo() { return activo; }
    public void setActivo(String activo) { this.activo = activo; }

    public String getIdentificador() { return identificador; }
    public void setIdentificador(String identificador) { this.identificador = identificador; }

    public Integer getSecuencia() { return secuencia; }
    public void setSecuencia(Integer secuencia) { this.secuencia = secuencia; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }

    public java.util.List<InvwebSubSubfamilia> getSubSubfamilias() { return subSubfamilias; }
    public void setSubSubfamilias(java.util.List<InvwebSubSubfamilia> subSubfamilias) { this.subSubfamilias = subSubfamilias; }
}
