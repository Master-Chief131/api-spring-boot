package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class FacwebFacturaDetaId implements Serializable {
    private Integer noCia;
    private Integer noFactura;
    private Integer noLinea;
    private String noArticulo;

    public FacwebFacturaDetaId() {}

    public FacwebFacturaDetaId(Integer noCia, Integer noFactura, Integer noLinea, String noArticulo) {
        this.noCia = noCia;
        this.noFactura = noFactura;
        this.noLinea = noLinea;
        this.noArticulo = noArticulo;
    }

    public Integer getNoCia() {
        return noCia;
    }

    public void setNoCia(Integer noCia) {
        this.noCia = noCia;
    }

    public Integer getNoFactura() {
        return noFactura;
    }

    public void setNoFactura(Integer noFactura) {
        this.noFactura = noFactura;
    }

    public Integer getNoLinea() {
        return noLinea;
    }

    public void setNoLinea(Integer noLinea) {
        this.noLinea = noLinea;
    }

    public String getNoArticulo() {
        return noArticulo;
    }

    public void setNoArticulo(String noArticulo) {
        this.noArticulo = noArticulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacwebFacturaDetaId that = (FacwebFacturaDetaId) o;
        return Objects.equals(noCia, that.noCia) && 
               Objects.equals(noFactura, that.noFactura) && 
               Objects.equals(noLinea, that.noLinea) && 
               Objects.equals(noArticulo, that.noArticulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noCia, noFactura, noLinea, noArticulo);
    }
}
