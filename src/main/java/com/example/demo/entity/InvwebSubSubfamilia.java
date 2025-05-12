package com.example.demo.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "invweb_sub_subfamilia")
@IdClass(SubSubfamiliaId.class)
public class InvwebSubSubfamilia implements Serializable {
    @Id
    @Column(name = "no_cia")
    private Integer noCia;

    @Id
    @Column(name = "no_familia")
    private Integer noFamilia;

    @Id
    @Column(name = "no_subfamilia")
    private Integer noSubfamilia;

    @Id
    @Column(name = "no_sub_subfamilia")
    private Integer noSubSubfamilia;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "activo")
    private String activo;

    @Column(name = "identificador")
    private String identificador;

    public Integer getNoCia() { return noCia; }
    public void setNoCia(Integer noCia) { this.noCia = noCia; }

    public Integer getNoFamilia() { return noFamilia; }
    public void setNoFamilia(Integer noFamilia) { this.noFamilia = noFamilia; }

    public Integer getNoSubfamilia() { return noSubfamilia; }
    public void setNoSubfamilia(Integer noSubfamilia) { this.noSubfamilia = noSubfamilia; }

    public Integer getNoSubSubfamilia() { return noSubSubfamilia; }
    public void setNoSubSubfamilia(Integer noSubSubfamilia) { this.noSubSubfamilia = noSubSubfamilia; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getActivo() { return activo; }
    public void setActivo(String activo) { this.activo = activo; }

    public String getIdentificador() { return identificador; }
    public void setIdentificador(String identificador) { this.identificador = identificador; }
}
