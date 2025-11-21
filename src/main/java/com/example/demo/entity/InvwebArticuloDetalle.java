package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "invweb_articulo_detalle")
@IdClass(InvwebArticuloDetalleId.class)
public class InvwebArticuloDetalle implements Serializable {

    @Id
    @Column(name = "no_cia")
    private Integer noCia;

    @Id
    @Column(name = "no_articulo", length = 50)
    private String noArticulo;

    @Id
    @Column(name = "no_detalle")
    private Integer noDetalle;

    @Column(name = "nombre", length = 150)
    private String nombre;

    @Column(name = "archivo", length = 255)
    private String archivo;

    @Column(name = "tipo_archivo", length = 100)
    private String tipoArchivo;

    @Column(name = "tamano", length = 15)
    private String tamano;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "agente", length = 50)
    private String agente;

    @Column(name = "actualizado")
    private LocalDateTime actualizado;

    @Column(name = "ver_portal", length = 1)
    private String verPortal;

    // Getters y Setters
    public Integer getNoCia() {
        return noCia;
    }

    public void setNoCia(Integer noCia) {
        this.noCia = noCia;
    }

    public String getNoArticulo() {
        return noArticulo;
    }

    public void setNoArticulo(String noArticulo) {
        this.noArticulo = noArticulo;
    }

    public Integer getNoDetalle() {
        return noDetalle;
    }

    public void setNoDetalle(Integer noDetalle) {
        this.noDetalle = noDetalle;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public LocalDateTime getActualizado() {
        return actualizado;
    }

    public void setActualizado(LocalDateTime actualizado) {
        this.actualizado = actualizado;
    }

    public String getVerPortal() {
        return verPortal;
    }

    public void setVerPortal(String verPortal) {
        this.verPortal = verPortal;
    }
}
