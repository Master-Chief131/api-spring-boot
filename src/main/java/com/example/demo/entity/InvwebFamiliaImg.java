package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "invweb_familia_img")
@IdClass(FamiliaImgId.class)
public class InvwebFamiliaImg implements Serializable {
    @Id
    @Column(name = "NO_CIA")
    private Integer noCia;

    @Id
    @Column(name = "NO_FAMILIA")
    private Integer noFamilia;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "ARCHIVO")
    private String archivo;

    @Column(name = "TAMANO")
    private String tamano;

    @Column(name = "AGENTE")
    private String agente;

    @Column(name = "ACTUALIZADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizado;

    // Getters y setters
    public Integer getNoCia() { return noCia; }
    public void setNoCia(Integer noCia) { this.noCia = noCia; }
    public Integer getNoFamilia() { return noFamilia; }
    public void setNoFamilia(Integer noFamilia) { this.noFamilia = noFamilia; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getArchivo() { return archivo; }
    public void setArchivo(String archivo) { this.archivo = archivo; }
    public String getTamano() { return tamano; }
    public void setTamano(String tamano) { this.tamano = tamano; }
    public String getAgente() { return agente; }
    public void setAgente(String agente) { this.agente = agente; }
    public Date getActualizado() { return actualizado; }
    public void setActualizado(Date actualizado) { this.actualizado = actualizado; }
}
