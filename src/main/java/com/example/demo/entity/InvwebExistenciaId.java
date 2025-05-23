package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class InvwebExistenciaId implements Serializable {
    private Integer noCia;
    private Integer noSucursal;
    private Integer noBodega;
    private String noArticulo;

    public InvwebExistenciaId() {}
    public InvwebExistenciaId(Integer noCia, Integer noSucursal, Integer noBodega, String noArticulo) {
        this.noCia = noCia;
        this.noSucursal = noSucursal;
        this.noBodega = noBodega;
        this.noArticulo = noArticulo;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvwebExistenciaId that = (InvwebExistenciaId) o;
        return Objects.equals(noCia, that.noCia) && Objects.equals(noSucursal, that.noSucursal) && Objects.equals(noBodega, that.noBodega) && Objects.equals(noArticulo, that.noArticulo);
    }
    @Override
    public int hashCode() {
        return Objects.hash(noCia, noSucursal, noBodega, noArticulo);
    }
}
