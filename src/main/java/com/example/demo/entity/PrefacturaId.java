package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class PrefacturaId implements Serializable {
    private Integer noCia;
    private Integer noPrefactura;

    public PrefacturaId() {}

    public PrefacturaId(Integer noCia, Integer noPrefactura) {
        this.noCia = noCia;
        this.noPrefactura = noPrefactura;
    }

    public Integer getNoCia() {
        return noCia;
    }
    public void setNoCia(Integer noCia) {
        this.noCia = noCia;
    }
    public Integer getNoPrefactura() {
        return noPrefactura;
    }
    public void setNoPrefactura(Integer noPrefactura) {
        this.noPrefactura = noPrefactura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrefacturaId that = (PrefacturaId) o;
        return Objects.equals(noCia, that.noCia) && Objects.equals(noPrefactura, that.noPrefactura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noCia, noPrefactura);
    }
}
