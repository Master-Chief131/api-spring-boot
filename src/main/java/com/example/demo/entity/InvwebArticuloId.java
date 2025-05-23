package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class InvwebArticuloId implements Serializable {
    private Integer noCia;
    private String noArticulo;

    public InvwebArticuloId() {}
    public InvwebArticuloId(Integer noCia, String noArticulo) {
        this.noCia = noCia;
        this.noArticulo = noArticulo;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvwebArticuloId that = (InvwebArticuloId) o;
        return Objects.equals(noCia, that.noCia) && Objects.equals(noArticulo, that.noArticulo);
    }
    @Override
    public int hashCode() {
        return Objects.hash(noCia, noArticulo);
    }
}
