package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class CuentaId implements Serializable {
    
    private Integer noCia;
    private String cuenta;

    // Constructor por defecto
    public CuentaId() {}

    // Constructor con parámetros
    public CuentaId(Integer noCia, String cuenta) {
        this.noCia = noCia;
        this.cuenta = cuenta;
    }

    // Getters y Setters
    public Integer getNoCia() {
        return noCia;
    }

    public void setNoCia(Integer noCia) {
        this.noCia = noCia;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    // Métodos equals y hashCode (requeridos para claves compuestas)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaId cuentaId = (CuentaId) o;
        return Objects.equals(noCia, cuentaId.noCia) && 
               Objects.equals(cuenta, cuentaId.cuenta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noCia, cuenta);
    }

    @Override
    public String toString() {
        return "CuentaId{" +
                "noCia=" + noCia +
                ", cuenta='" + cuenta + '\'' +
                '}';
    }
}
