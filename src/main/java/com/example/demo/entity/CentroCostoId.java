package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase de ID compuesta para la entidad CentroCosto
 */
public class CentroCostoId implements Serializable {
    
    private Integer noCia;
    private String centro;
    
    // Constructor por defecto
    public CentroCostoId() {}
    
    // Constructor con parámetros
    public CentroCostoId(Integer noCia, String centro) {
        this.noCia = noCia;
        this.centro = centro;
    }
    
    // Getters y Setters
    public Integer getNoCia() {
        return noCia;
    }
    
    public void setNoCia(Integer noCia) {
        this.noCia = noCia;
    }
    
    public String getCentro() {
        return centro;
    }
    
    public void setCentro(String centro) {
        this.centro = centro;
    }
    
    // equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CentroCostoId that = (CentroCostoId) o;
        return Objects.equals(noCia, that.noCia) && 
               Objects.equals(centro, that.centro);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(noCia, centro);
    }
    
    @Override
    public String toString() {
        return "CentroCostoId{" +
                "noCia=" + noCia +
                ", centro='" + centro + '\'' +
                '}';
    }
}
