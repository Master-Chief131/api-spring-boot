package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class FacwebSolicitudDetaId implements Serializable {
    private Integer noCia;
    private Integer noSucursal;
    private Integer noBodega;
    private Integer noSolicitud;
    private Integer noLinea;
    private String noArticulo;

    public FacwebSolicitudDetaId() {}

    public FacwebSolicitudDetaId(Integer noCia, Integer noSucursal, Integer noBodega, 
                                Integer noSolicitud, Integer noLinea, String noArticulo) {
        this.noCia = noCia;
        this.noSucursal = noSucursal;
        this.noBodega = noBodega;
        this.noSolicitud = noSolicitud;
        this.noLinea = noLinea;
        this.noArticulo = noArticulo;
    }

    public Integer getNoCia() { return noCia; }
    public void setNoCia(Integer noCia) { this.noCia = noCia; }

    public Integer getNoSucursal() { return noSucursal; }
    public void setNoSucursal(Integer noSucursal) { this.noSucursal = noSucursal; }

    public Integer getNoBodega() { return noBodega; }
    public void setNoBodega(Integer noBodega) { this.noBodega = noBodega; }

    public Integer getNoSolicitud() { return noSolicitud; }
    public void setNoSolicitud(Integer noSolicitud) { this.noSolicitud = noSolicitud; }

    public Integer getNoLinea() { return noLinea; }
    public void setNoLinea(Integer noLinea) { this.noLinea = noLinea; }

    public String getNoArticulo() { return noArticulo; }
    public void setNoArticulo(String noArticulo) { this.noArticulo = noArticulo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacwebSolicitudDetaId that = (FacwebSolicitudDetaId) o;
        return Objects.equals(noCia, that.noCia) && 
               Objects.equals(noSucursal, that.noSucursal) && 
               Objects.equals(noBodega, that.noBodega) && 
               Objects.equals(noSolicitud, that.noSolicitud) && 
               Objects.equals(noLinea, that.noLinea) && 
               Objects.equals(noArticulo, that.noArticulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noCia, noSucursal, noBodega, noSolicitud, noLinea, noArticulo);
    }
}
