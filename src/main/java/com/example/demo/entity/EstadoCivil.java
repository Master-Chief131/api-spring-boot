package com.example.demo.entity;

import javax.persistence.*;

/**
 * Entidad que representa la tabla genweb_estado_civil
 */
@Entity
@Table(name = "genweb_estado_civil")
public class EstadoCivil {
    
    @Id
    @Column(name = "codigo", nullable = false, length = 10)
    private String codigo;
    
    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;
    
    // Constructor por defecto
    public EstadoCivil() {}
    
    // Constructor con parámetros
    public EstadoCivil(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }
    
    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return "EstadoCivil{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
