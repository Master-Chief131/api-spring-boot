package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class BodegaId implements Serializable {
    private Integer noCia;
    private Integer noSucursal;
    private Integer noBodega;

    public BodegaId() {}
    public BodegaId(Integer noCia, Integer noSucursal, Integer noBodega) {
        this.noCia = noCia;
        this.noSucursal = noSucursal;
        this.noBodega = noBodega;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BodegaId that = (BodegaId) o;
        return Objects.equals(noCia, that.noCia) && Objects.equals(noSucursal, that.noSucursal) && Objects.equals(noBodega, that.noBodega);
    }
    @Override
    public int hashCode() {
        return Objects.hash(noCia, noSucursal, noBodega);
    }
}
