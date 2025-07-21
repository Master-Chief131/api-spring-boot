package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase de clave primaria compuesta para la entidad Empleado
 */
public class EmpleadoId implements Serializable {
    
    private Integer noCia;
    private String codPla;
    private String noEmple;
    
    public EmpleadoId() {}
    
    public EmpleadoId(Integer noCia, String codPla, String noEmple) {
        this.noCia = noCia;
        this.codPla = codPla;
        this.noEmple = noEmple;
    }
    
    public Integer getNoCia() {
        return noCia;
    }
    
    public void setNoCia(Integer noCia) {
        this.noCia = noCia;
    }
    
    public String getCodPla() {
        return codPla;
    }
    
    public void setCodPla(String codPla) {
        this.codPla = codPla;
    }
    
    public String getNoEmple() {
        return noEmple;
    }
    
    public void setNoEmple(String noEmple) {
        this.noEmple = noEmple;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpleadoId that = (EmpleadoId) o;
        return Objects.equals(noCia, that.noCia) &&
               Objects.equals(codPla, that.codPla) &&
               Objects.equals(noEmple, that.noEmple);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(noCia, codPla, noEmple);
    }
    
    @Override
    public String toString() {
        return "EmpleadoId{" +
                "noCia=" + noCia +
                ", codPla='" + codPla + '\'' +
                ", noEmple='" + noEmple + '\'' +
                '}';
    }
}
