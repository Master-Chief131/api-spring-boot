package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class ServicioId implements Serializable {
    private Integer noCia;
    private Integer noServicio;

    public ServicioId() {}
    public ServicioId(Integer noCia, Integer noServicio) {
        this.noCia = noCia;
        this.noServicio = noServicio;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicioId that = (ServicioId) o;
        return Objects.equals(noCia, that.noCia) && Objects.equals(noServicio, that.noServicio);
    }
    @Override
    public int hashCode() {
        return Objects.hash(noCia, noServicio);
    }
}
