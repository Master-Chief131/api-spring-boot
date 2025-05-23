package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class FacwebPromocionId implements Serializable {
    private Integer noCia;
    private String codigo;

    public FacwebPromocionId() {}
    public FacwebPromocionId(Integer noCia, String codigo) {
        this.noCia = noCia;
        this.codigo = codigo;
    }
    public Integer getNoCia() { return noCia; }
    public void setNoCia(Integer noCia) { this.noCia = noCia; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacwebPromocionId that = (FacwebPromocionId) o;
        return Objects.equals(noCia, that.noCia) && Objects.equals(codigo, that.codigo);
    }
    @Override
    public int hashCode() {
        return Objects.hash(noCia, codigo);
    }
}
