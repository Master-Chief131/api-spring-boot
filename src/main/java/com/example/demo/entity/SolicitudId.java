package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class SolicitudId implements Serializable {
    private Integer noCia;
    private Integer noSolicitud;

    public SolicitudId() {}

    public SolicitudId(Integer noCia, Integer noSolicitud) {
        this.noCia = noCia;
        this.noSolicitud = noSolicitud;
    }

    public Integer getNoCia() {
        return noCia;
    }
    public void setNoCia(Integer noCia) {
        this.noCia = noCia;
    }
    public Integer getNoSolicitud() {
        return noSolicitud;
    }
    public void setNoSolicitud(Integer noSolicitud) {
        this.noSolicitud = noSolicitud;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolicitudId that = (SolicitudId) o;
        return Objects.equals(noCia, that.noCia) && Objects.equals(noSolicitud, that.noSolicitud);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noCia, noSolicitud);
    }
}
