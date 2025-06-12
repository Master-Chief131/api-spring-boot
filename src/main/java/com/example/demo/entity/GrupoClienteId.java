package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class GrupoClienteId implements Serializable {
    private Integer noCia;
    private String noGrupo;

    public GrupoClienteId() {}

    public GrupoClienteId(Integer noCia, String noGrupo) {
        this.noCia = noCia;
        this.noGrupo = noGrupo;
    }

    public Integer getNoCia() {
        return noCia;
    }
    public void setNoCia(Integer noCia) {
        this.noCia = noCia;
    }
    public String getNoGrupo() {
        return noGrupo;
    }
    public void setNoGrupo(String noGrupo) {
        this.noGrupo = noGrupo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoClienteId that = (GrupoClienteId) o;
        return Objects.equals(noCia, that.noCia) && Objects.equals(noGrupo, that.noGrupo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noCia, noGrupo);
    }
}
