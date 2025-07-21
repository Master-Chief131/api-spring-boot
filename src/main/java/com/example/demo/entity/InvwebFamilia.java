package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "invweb_familia")
@IdClass(FamiliaId.class)
public class InvwebFamilia implements Serializable {
    @Id
    @Column(name = "NO_CIA")
    private Integer noCia;

    @Id
    @Column(name = "NO_FAMILIA")
    private Integer noFamilia;

    @Column(name = "NOMBRE", length = 50)
    private String nombre;

    @Column(name = "NO_IMPUESTO")
    private Integer noImpuesto;

    @Column(name = "APLICA_IMPUESTO", length = 1)
    private String aplicaImpuesto;

    @Column(name = "IDENTIFICADOR", length = 3)
    private String identificador;

    @Column(name = "IND_ARTICULO", length = 1)
    private String indArticulo;

    @Column(name = "ACTIVO", length = 1)
    private String activo;

    @Column(name = "VER_PORTAL", length = 1)
    private String verPortal;

    @Column(name = "IND_SERVICIO", length = 1)
    private String indServicio;

    @Column(name = "SECUENCIA")
    private Integer secuencia;

    @Column(name = "centro_costo", length = 9)
    private String centroCosto;

    @Transient
    private String imagenUrl;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "NO_CIA", referencedColumnName = "NO_CIA", insertable = false, updatable = false),
        @JoinColumn(name = "NO_FAMILIA", referencedColumnName = "NO_FAMILIA", insertable = false, updatable = false)
    })
    private java.util.List<InvwebSubfamilia> subfamilias;

    public Integer getNoCia() { return noCia; }
    public void setNoCia(Integer noCia) { this.noCia = noCia; }

    public Integer getNoFamilia() { return noFamilia; }
    public void setNoFamilia(Integer noFamilia) { this.noFamilia = noFamilia; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getNoImpuesto() { return noImpuesto; }
    public void setNoImpuesto(Integer noImpuesto) { this.noImpuesto = noImpuesto; }

    public String getAplicaImpuesto() { return aplicaImpuesto; }
    public void setAplicaImpuesto(String aplicaImpuesto) { this.aplicaImpuesto = aplicaImpuesto; }

    public String getIdentificador() { return identificador; }
    public void setIdentificador(String identificador) { this.identificador = identificador; }

    public String getIndArticulo() { return indArticulo; }
    public void setIndArticulo(String indArticulo) { this.indArticulo = indArticulo; }

    public String getActivo() { return activo; }
    public void setActivo(String activo) { this.activo = activo; }

    public String getVerPortal() { return verPortal; }
    public void setVerPortal(String verPortal) { this.verPortal = verPortal; }

    public String getIndServicio() { return indServicio; }
    public void setIndServicio(String indServicio) { this.indServicio = indServicio; }

    public Integer getSecuencia() { return secuencia; }
    public void setSecuencia(Integer secuencia) { this.secuencia = secuencia; }

    public String getCentroCosto() { return centroCosto; }
    public void setCentroCosto(String centroCosto) { this.centroCosto = centroCosto; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }

    public java.util.List<InvwebSubfamilia> getSubfamilias() { return subfamilias; }
    public void setSubfamilias(java.util.List<InvwebSubfamilia> subfamilias) { this.subfamilias = subfamilias; }
}
