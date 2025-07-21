package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class ProveedorId implements Serializable {
    
    private Integer noCia;
    private Integer noProveedor;
    private Integer noClase;
    
    // Constructor por defecto
    public ProveedorId() {}
    
    // Constructor con parámetros
    public ProveedorId(Integer noCia, Integer noProveedor, Integer noClase) {
        this.noCia = noCia;
        this.noProveedor = noProveedor;
        this.noClase = noClase;
    }
    
    // Getters y Setters
    public Integer getNoCia() {
        return noCia;
    }
    
    public void setNoCia(Integer noCia) {
        this.noCia = noCia;
    }
    
    public Integer getNoProveedor() {
        return noProveedor;
    }
    
    public void setNoProveedor(Integer noProveedor) {
        this.noProveedor = noProveedor;
    }
    
    public Integer getNoClase() {
        return noClase;
    }
    
    public void setNoClase(Integer noClase) {
        this.noClase = noClase;
    }
    
    // equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProveedorId that = (ProveedorId) o;
        return Objects.equals(noCia, that.noCia) &&
               Objects.equals(noProveedor, that.noProveedor) &&
               Objects.equals(noClase, that.noClase);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(noCia, noProveedor, noClase);
    }
    
    @Override
    public String toString() {
        return "ProveedorId{" +
                "noCia=" + noCia +
                ", noProveedor=" + noProveedor +
                ", noClase=" + noClase +
                '}';
    }
}
