package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class InvwebArticuloDetalleId implements Serializable {
    private Integer noCia;
    private String noArticulo;
    private Integer noDetalle;

    public InvwebArticuloDetalleId() {
    }

    public InvwebArticuloDetalleId(Integer noCia, String noArticulo, Integer noDetalle) {
        this.noCia = noCia;
        this.noArticulo = noArticulo;
        this.noDetalle = noDetalle;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        InvwebArticuloDetalleId that = (InvwebArticuloDetalleId) o;
        return Objects.equals(noCia, that.noCia) &&
                Objects.equals(noArticulo, that.noArticulo) &&
                Objects.equals(noDetalle, that.noDetalle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noCia, noArticulo, noDetalle);
    }
}
