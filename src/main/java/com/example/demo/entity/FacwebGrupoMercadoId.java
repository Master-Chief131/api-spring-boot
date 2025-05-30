package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class FacwebGrupoMercadoId implements Serializable {
    private Integer noCia;
    private Integer noGrupo;

    public FacwebGrupoMercadoId() {}
    public FacwebGrupoMercadoId(Integer noCia, Integer noGrupo) {
        this.noCia = noCia;
        this.noGrupo = noGrupo;
    }
    public Integer getNoCia() { return noCia; }
    public void setNoCia(Integer noCia) { this.noCia = noCia; }
    public Integer getNoGrupo() { return noGrupo; }
    public void setNoGrupo(Integer noGrupo) { this.noGrupo = noGrupo; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacwebGrupoMercadoId that = (FacwebGrupoMercadoId) o;
        return Objects.equals(noCia, that.noCia) && Objects.equals(noGrupo, that.noGrupo);
    }
    @Override
    public int hashCode() {
        return Objects.hash(noCia, noGrupo);
    }
}
