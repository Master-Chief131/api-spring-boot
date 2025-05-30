package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class FacwebPrefacturaId implements Serializable {
    private Integer noCia;
    private Integer noPrefactura;

    public FacwebPrefacturaId() {}
    public FacwebPrefacturaId(Integer noCia, Integer noPrefactura) {
        this.noCia = noCia;
        this.noPrefactura = noPrefactura;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacwebPrefacturaId that = (FacwebPrefacturaId) o;
        return Objects.equals(noCia, that.noCia) &&
               Objects.equals(noPrefactura, that.noPrefactura);
    }
    @Override
    public int hashCode() {
        return Objects.hash(noCia, noPrefactura);
    }
}
