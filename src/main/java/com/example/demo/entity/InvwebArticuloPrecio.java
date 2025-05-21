package com.example.demo.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "invweb_articulo_precio")
@IdClass(ArticuloPrecioId.class)
public class InvwebArticuloPrecio implements Serializable {
    @Id
    @Column(name = "no_cia")
    private Integer noCia;

    @Id
    @Column(name = "no_articulo", length = 50)
    private String noArticulo;

    @Column(name = "precio_base", precision = 15, scale = 6)
    private BigDecimal precioBase;

    @Column(name = "ver_portal", length = 1)
    private String verPortal;

    public Integer getNoCia() { return noCia; }
    public void setNoCia(Integer noCia) { this.noCia = noCia; }
    public String getNoArticulo() { return noArticulo; }
    public void setNoArticulo(String noArticulo) { this.noArticulo = noArticulo; }
    public java.math.BigDecimal getPrecioBase() { return precioBase; }
    public void setPrecioBase(java.math.BigDecimal precioBase) { this.precioBase = precioBase; }
    public String getVerPortal() { return verPortal; }
    public void setVerPortal(String verPortal) { this.verPortal = verPortal; }
}
