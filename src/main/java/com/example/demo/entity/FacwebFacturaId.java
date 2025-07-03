package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class FacwebFacturaId implements Serializable {
    private Integer noCia;
    private Integer noFactura;

    public FacwebFacturaId() {}

    public FacwebFacturaId(Integer noCia, Integer noFactura) {
        this.noCia = noCia;
        this.noFactura = noFactura;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacwebFacturaId that = (FacwebFacturaId) o;
        return Objects.equals(noCia, that.noCia) && Objects.equals(noFactura, that.noFactura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noCia, noFactura);
    }
}
