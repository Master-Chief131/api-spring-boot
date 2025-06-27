package com.example.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "conweb_cuentas")
@IdClass(CuentaId.class)
public class Cuenta {
    
    @Id
    @Column(name = "no_cia", nullable = false)
    private Integer noCia;
    
    @Id
    @Column(name = "cuenta", nullable = false, length = 50)
    private String cuenta;
    
    @Column(name = "descripcion", length = 50)
    private String descripcion;
    
    @Column(name = "tipo")
    private Integer tipo;
    
    @Column(name = "clase", length = 1)
    private String clase;
    
    @Column(name = "ind_mov", length = 1)
    private String indMov;
    
    @Column(name = "debitos", precision = 17, scale = 2)
    private BigDecimal debitos;
    
    @Column(name = "creditos", precision = 17, scale = 2)
    private BigDecimal creditos;
    
    @Column(name = "saldo_per_ant", precision = 17, scale = 2)
    private BigDecimal saldoPerAnt;
    
    @Column(name = "saldo_mes_ant", precision = 17, scale = 2)
    private BigDecimal saldoMesAnt;
    
    @Column(name = "descrip1", length = 100)
    private String descrip1;
    
    @Column(name = "moneda")
    private Integer moneda;
    
    @Column(name = "tipo_cambio", precision = 15, scale = 2)
    private BigDecimal tipoCambio;
    
    @Column(name = "saldo_per_ant_dol", precision = 17, scale = 2)
    private BigDecimal saldoPerAntDol;
    
    @Column(name = "saldo_mes_ant_dol", precision = 17, scale = 2)
    private BigDecimal saldoMesAntDol;
    
    @Column(name = "debitos_dol", precision = 17, scale = 2)
    private BigDecimal debitosDol;
    
    @Column(name = "creditos_dol", precision = 17, scale = 2)
    private BigDecimal creditosDol;
    
    @Column(name = "permiso_cont", length = 1)
    private String permisoCont;
    
    @Column(name = "permiso_cxc", length = 1)
    private String permisoCxc;
    
    @Column(name = "permiso_cxp", length = 1)
    private String permisoCxp;
    
    @Column(name = "permiso_inv", length = 1)
    private String permisoInv;
    
    @Column(name = "permiso_fact", length = 1)
    private String permisoFact;
    
    @Column(name = "acepta_cc", length = 1)
    private String aceptaCc;
    
    @Column(name = "creditos_pen", precision = 17, scale = 2)
    private BigDecimal creditosPen;
    
    @Column(name = "debitos_pen", precision = 17, scale = 2)
    private BigDecimal debitosPen;
    
    @Column(name = "usado_en", length = 2)
    private String usadoEn;
    
    @Column(name = "padre", length = 15)
    private String padre;
    
    @Column(name = "nivel", length = 1)
    private String nivel;
    
    @Column(name = "ind_tercero", length = 1)
    private String indTercero;
    
    @Column(name = "decrip_larga", length = 100)
    private String descripLarga;
    
    @Column(name = "naturaleza", length = 1)
    private String naturaleza;
    
    @Column(name = "creditos_pend_dol", precision = 17, scale = 2)
    private BigDecimal creditosPendDol;
    
    @Column(name = "debitos_pend_dol", precision = 17, scale = 2)
    private BigDecimal debitosPendDol;
    
    @Column(name = "activo", length = 1)
    private String activo;
    
    @Column(name = "acepta_presu", length = 1)
    private String aceptaPresu;
    
    @Column(name = "propietario", length = 2)
    private String propietario;
    
    @Column(name = "compartir", length = 1)
    private String compartir;
    
    @Column(name = "porc_alerta")
    private Integer porcAlerta;
    
    @Column(name = "cta_correccion", length = 15)
    private String ctaCorreccion;
    
    @Column(name = "ajustable", length = 1)
    private String ajustable;
    
    @Column(name = "no_sub_tipo_cuenta")
    private Integer noSubTipoCuenta;
    
    @Column(name = "no_sub_sub_tipo_cuenta")
    private Integer noSubSubTipoCuenta;
    
    @Column(name = "ind_Capex", length = 1)
    private String indCapex;

    // Constructor por defecto
    public Cuenta() {}

    // Constructor con parámetros principales
    public Cuenta(Integer noCia, String cuenta) {
        this.noCia = noCia;
        this.cuenta = cuenta;
    }

    // Getters y Setters
    public Integer getNoCia() {
        return noCia;
    }

    public void setNoCia(Integer noCia) {
        this.noCia = noCia;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getIndMov() {
        return indMov;
    }

    public void setIndMov(String indMov) {
        this.indMov = indMov;
    }

    public BigDecimal getDebitos() {
        return debitos;
    }

    public void setDebitos(BigDecimal debitos) {
        this.debitos = debitos;
    }

    public BigDecimal getCreditos() {
        return creditos;
    }

    public void setCreditos(BigDecimal creditos) {
        this.creditos = creditos;
    }

    public BigDecimal getSaldoPerAnt() {
        return saldoPerAnt;
    }

    public void setSaldoPerAnt(BigDecimal saldoPerAnt) {
        this.saldoPerAnt = saldoPerAnt;
    }

    public BigDecimal getSaldoMesAnt() {
        return saldoMesAnt;
    }

    public void setSaldoMesAnt(BigDecimal saldoMesAnt) {
        this.saldoMesAnt = saldoMesAnt;
    }

    public String getDescrip1() {
        return descrip1;
    }

    public void setDescrip1(String descrip1) {
        this.descrip1 = descrip1;
    }

    public Integer getMoneda() {
        return moneda;
    }

    public void setMoneda(Integer moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public BigDecimal getSaldoPerAntDol() {
        return saldoPerAntDol;
    }

    public void setSaldoPerAntDol(BigDecimal saldoPerAntDol) {
        this.saldoPerAntDol = saldoPerAntDol;
    }

    public BigDecimal getSaldoMesAntDol() {
        return saldoMesAntDol;
    }

    public void setSaldoMesAntDol(BigDecimal saldoMesAntDol) {
        this.saldoMesAntDol = saldoMesAntDol;
    }

    public BigDecimal getDebitosDol() {
        return debitosDol;
    }

    public void setDebitosDol(BigDecimal debitosDol) {
        this.debitosDol = debitosDol;
    }

    public BigDecimal getCreditosDol() {
        return creditosDol;
    }

    public void setCreditosDol(BigDecimal creditosDol) {
        this.creditosDol = creditosDol;
    }

    public String getPermisoCont() {
        return permisoCont;
    }

    public void setPermisoCont(String permisoCont) {
        this.permisoCont = permisoCont;
    }

    public String getPermisoCxc() {
        return permisoCxc;
    }

    public void setPermisoCxc(String permisoCxc) {
        this.permisoCxc = permisoCxc;
    }

    public String getPermisoCxp() {
        return permisoCxp;
    }

    public void setPermisoCxp(String permisoCxp) {
        this.permisoCxp = permisoCxp;
    }

    public String getPermisoInv() {
        return permisoInv;
    }

    public void setPermisoInv(String permisoInv) {
        this.permisoInv = permisoInv;
    }

    public String getPermisoFact() {
        return permisoFact;
    }

    public void setPermisoFact(String permisoFact) {
        this.permisoFact = permisoFact;
    }

    public String getAceptaCc() {
        return aceptaCc;
    }

    public void setAceptaCc(String aceptaCc) {
        this.aceptaCc = aceptaCc;
    }

    public BigDecimal getCreditosPen() {
        return creditosPen;
    }

    public void setCreditosPen(BigDecimal creditosPen) {
        this.creditosPen = creditosPen;
    }

    public BigDecimal getDebitosPen() {
        return debitosPen;
    }

    public void setDebitosPen(BigDecimal debitosPen) {
        this.debitosPen = debitosPen;
    }

    public String getUsadoEn() {
        return usadoEn;
    }

    public void setUsadoEn(String usadoEn) {
        this.usadoEn = usadoEn;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getIndTercero() {
        return indTercero;
    }

    public void setIndTercero(String indTercero) {
        this.indTercero = indTercero;
    }

    public String getDescripLarga() {
        return descripLarga;
    }

    public void setDescripLarga(String descripLarga) {
        this.descripLarga = descripLarga;
    }

    public String getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }

    public BigDecimal getCreditosPendDol() {
        return creditosPendDol;
    }

    public void setCreditosPendDol(BigDecimal creditosPendDol) {
        this.creditosPendDol = creditosPendDol;
    }

    public BigDecimal getDebitosPendDol() {
        return debitosPendDol;
    }

    public void setDebitosPendDol(BigDecimal debitosPendDol) {
        this.debitosPendDol = debitosPendDol;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getAceptaPresu() {
        return aceptaPresu;
    }

    public void setAceptaPresu(String aceptaPresu) {
        this.aceptaPresu = aceptaPresu;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getCompartir() {
        return compartir;
    }

    public void setCompartir(String compartir) {
        this.compartir = compartir;
    }

    public Integer getPorcAlerta() {
        return porcAlerta;
    }

    public void setPorcAlerta(Integer porcAlerta) {
        this.porcAlerta = porcAlerta;
    }

    public String getCtaCorreccion() {
        return ctaCorreccion;
    }

    public void setCtaCorreccion(String ctaCorreccion) {
        this.ctaCorreccion = ctaCorreccion;
    }

    public String getAjustable() {
        return ajustable;
    }

    public void setAjustable(String ajustable) {
        this.ajustable = ajustable;
    }

    public Integer getNoSubTipoCuenta() {
        return noSubTipoCuenta;
    }

    public void setNoSubTipoCuenta(Integer noSubTipoCuenta) {
        this.noSubTipoCuenta = noSubTipoCuenta;
    }

    public Integer getNoSubSubTipoCuenta() {
        return noSubSubTipoCuenta;
    }

    public void setNoSubSubTipoCuenta(Integer noSubSubTipoCuenta) {
        this.noSubSubTipoCuenta = noSubSubTipoCuenta;
    }

    public String getIndCapex() {
        return indCapex;
    }

    public void setIndCapex(String indCapex) {
        this.indCapex = indCapex;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "noCia=" + noCia +
                ", cuenta='" + cuenta + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipo=" + tipo +
                ", clase='" + clase + '\'' +
                ", indMov='" + indMov + '\'' +
                ", activo='" + activo + '\'' +
                '}';
    }
}
