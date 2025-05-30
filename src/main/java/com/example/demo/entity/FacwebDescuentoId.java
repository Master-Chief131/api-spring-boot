package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class FacwebDescuentoId implements Serializable {
    private Integer noCia;
    private Integer noGrupo;
    private String noArticulo;

    public FacwebDescuentoId() {}
    public FacwebDescuentoId(Integer noCia, Integer noGrupo, String noArticulo) {
        this.noCia = noCia;
        this.noGrupo = noGrupo;
        this.noArticulo = noArticulo;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacwebDescuentoId that = (FacwebDescuentoId) o;
        return Objects.equals(noCia, that.noCia) &&
               Objects.equals(noGrupo, that.noGrupo) &&
               Objects.equals(noArticulo, that.noArticulo);
    }
    @Override
    public int hashCode() {
        return Objects.hash(noCia, noGrupo, noArticulo);
    }
}
