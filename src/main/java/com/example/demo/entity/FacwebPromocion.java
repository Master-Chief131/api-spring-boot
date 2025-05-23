package com.example.demo.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "facweb_promociones")
@IdClass(FacwebPromocionId.class)
public class FacwebPromocion implements Serializable {
    @Id
    @Column(name = "no_cia")
    private Integer noCia;

    @Id
    @Column(name = "codigo", length = 11)
    private String codigo;

    @Column(name = "descripcion", length = 100, nullable = false)
    private String descripcion;

    @Column(name = "tipo_prom", length = 1, nullable = false)
    private String tipoProm;

    @Column(name = "tipo_cliente")
    private Integer tipoCliente;

    @Column(name = "grupo", length = 2)
    private String grupo;

    @Column(name = "no_cliente")
    private Integer noCliente;

    @Column(name = "fix_plat", length = 1)
    private String fixPlat;

    @Column(name = "min_qty")
    private Integer minQty;

    @Column(name = "max_qty")
    private Integer maxQty;

    @Column(name = "centsoff", precision = 8, scale = 2)
    private BigDecimal centsOff;

    @Column(name = "b_f_qty")
    private Integer bFQty;

    @Column(name = "promo_prot", length = 1)
    private String promoProt;

    @Column(name = "alt_pcode", length = 50)
    private String altPcode;

    @Column(name = "beg_date")
    private LocalDate begDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "psi_item", length = 1)
    private String psiItem;

    @Column(name = "promo", length = 4)
    private String promo;

    @Column(name = "ind_unidad_facturada", length = 1, nullable = false)
    private String indUnidadFacturada;

    @Column(name = "no_prove")
    private Integer noProve;

    @Column(name = "no_clase")
    private Integer noClase;

    @Column(name = "descuento_cia", precision = 8, scale = 2)
    private BigDecimal descuentoCia;

    @Column(name = "descuento_prove", precision = 8, scale = 2)
    private BigDecimal descuentoProve;

    @Column(name = "no_familia")
    private Integer noFamilia;

    @Column(name = "no_subfamilia")
    private Integer noSubfamilia;

    @Column(name = "no_marca")
    private Integer noMarca;

    @Column(name = "no_modelo")
    private Integer noModelo;

    @Column(name = "ind_activa", length = 1)
    private String indActiva;

    @Column(name = "usuario_crea", length = 50)
    private String usuarioCrea;

    @Column(name = "fecha_crea")
    private LocalDateTime fechaCrea;

    @Column(name = "usuario_mod", length = 50)
    private String usuarioMod;

    @Column(name = "fecha_mod")
    private LocalDateTime fechaMod;

    // Getters y setters
    public Integer getNoCia() { return noCia; }
    public void setNoCia(Integer noCia) { this.noCia = noCia; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getTipoProm() { return tipoProm; }
    public void setTipoProm(String tipoProm) { this.tipoProm = tipoProm; }
    public Integer getTipoCliente() { return tipoCliente; }
    public void setTipoCliente(Integer tipoCliente) { this.tipoCliente = tipoCliente; }
    public String getGrupo() { return grupo; }
    public void setGrupo(String grupo) { this.grupo = grupo; }
    public Integer getNoCliente() { return noCliente; }
    public void setNoCliente(Integer noCliente) { this.noCliente = noCliente; }
    public String getFixPlat() { return fixPlat; }
    public void setFixPlat(String fixPlat) { this.fixPlat = fixPlat; }
    public Integer getMinQty() { return minQty; }
    public void setMinQty(Integer minQty) { this.minQty = minQty; }
    public Integer getMaxQty() { return maxQty; }
    public void setMaxQty(Integer maxQty) { this.maxQty = maxQty; }
    public BigDecimal getCentsOff() { return centsOff; }
    public void setCentsOff(BigDecimal centsOff) { this.centsOff = centsOff; }
    public Integer getBFQty() { return bFQty; }
    public void setBFQty(Integer bFQty) { this.bFQty = bFQty; }
    public String getPromoProt() { return promoProt; }
    public void setPromoProt(String promoProt) { this.promoProt = promoProt; }
    public String getAltPcode() { return altPcode; }
    public void setAltPcode(String altPcode) { this.altPcode = altPcode; }
    public LocalDate getBegDate() { return begDate; }
    public void setBegDate(LocalDate begDate) { this.begDate = begDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public String getPsiItem() { return psiItem; }
    public void setPsiItem(String psiItem) { this.psiItem = psiItem; }
    public String getPromo() { return promo; }
    public void setPromo(String promo) { this.promo = promo; }
    public String getIndUnidadFacturada() { return indUnidadFacturada; }
    public void setIndUnidadFacturada(String indUnidadFacturada) { this.indUnidadFacturada = indUnidadFacturada; }
    public Integer getNoProve() { return noProve; }
    public void setNoProve(Integer noProve) { this.noProve = noProve; }
    public Integer getNoClase() { return noClase; }
    public void setNoClase(Integer noClase) { this.noClase = noClase; }
    public BigDecimal getDescuentoCia() { return descuentoCia; }
    public void setDescuentoCia(BigDecimal descuentoCia) { this.descuentoCia = descuentoCia; }
    public BigDecimal getDescuentoProve() { return descuentoProve; }
    public void setDescuentoProve(BigDecimal descuentoProve) { this.descuentoProve = descuentoProve; }
    public Integer getNoFamilia() { return noFamilia; }
    public void setNoFamilia(Integer noFamilia) { this.noFamilia = noFamilia; }
    public Integer getNoSubfamilia() { return noSubfamilia; }
    public void setNoSubfamilia(Integer noSubfamilia) { this.noSubfamilia = noSubfamilia; }
    public Integer getNoMarca() { return noMarca; }
    public void setNoMarca(Integer noMarca) { this.noMarca = noMarca; }
    public Integer getNoModelo() { return noModelo; }
    public void setNoModelo(Integer noModelo) { this.noModelo = noModelo; }
    public String getIndActiva() { return indActiva; }
    public void setIndActiva(String indActiva) { this.indActiva = indActiva; }
    public String getUsuarioCrea() { return usuarioCrea; }
    public void setUsuarioCrea(String usuarioCrea) { this.usuarioCrea = usuarioCrea; }
    public LocalDateTime getFechaCrea() { return fechaCrea; }
    public void setFechaCrea(LocalDateTime fechaCrea) { this.fechaCrea = fechaCrea; }
    public String getUsuarioMod() { return usuarioMod; }
    public void setUsuarioMod(String usuarioMod) { this.usuarioMod = usuarioMod; }
    public LocalDateTime getFechaMod() { return fechaMod; }
    public void setFechaMod(LocalDateTime fechaMod) { this.fechaMod = fechaMod; }
}
