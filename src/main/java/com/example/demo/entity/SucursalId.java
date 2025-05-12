package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class SucursalId implements Serializable {
    private Integer noCia;
    private Integer noSucursal;

    public SucursalId() {}
    public SucursalId(Integer noCia, Integer noSucursal) {
        this.noCia = noCia;
        this.noSucursal = noSucursal;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SucursalId that = (SucursalId) o;
        return Objects.equals(noCia, that.noCia) && Objects.equals(noSucursal, that.noSucursal);
    }
    @Override
    public int hashCode() {
        return Objects.hash(noCia, noSucursal);
    }
}
