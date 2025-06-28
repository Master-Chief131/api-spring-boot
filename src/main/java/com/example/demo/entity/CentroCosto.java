package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "conweb_centro_costo")
@IdClass(CentroCostoId.class)
public class CentroCosto implements Serializable {

    @Id
    @Column(name = "no_cia", nullable = false)
    private Integer noCia;

    @Id
    @Column(name = "centro", nullable = false, length = 50)
    private String centro;

    @Column(name = "descrip_cc", length = 50)
    private String descripCc;

    @Column(name = "encargado_cc", length = 50)
    private String encargadoCc;

    @Column(name = "ultimo_nivel", length = 1)
    private String ultimoNivel;

    @Column(name = "padre", length = 9)
    private String padre;

    @Column(name = "nivel")
    private Integer nivel;

    @Column(name = "activo", length = 1)
    private String activo;

    @Column(name = "ind_mov", length = 1)
    private String indMov = "N";

    @Column(name = "tipo_gasto", length = 2)
    private String tipoGasto;

    @Column(name = "relacionado_a_cuentas", length = 1)
    private String relacionadoACuentas = "A";

    @Column(name = "cod_emple", length = 6)
    private String codEmple;

    @Column(name = "cod_pla", length = 2)
    private String codPla;

    @Column(name = "no_tipo_centro")
    private Integer noTipoCentro;

    // Constructor por defecto
    public CentroCosto() {}

    // Constructor con parámetros principales
    public CentroCosto(Integer noCia, String centro) {
        this.noCia = noCia;
        this.centro = centro;
    }

    // Getters y Setters
    public Integer getNoCia() {
        return noCia;
    }

    public void setNoCia(Integer noCia) {
        this.noCia = noCia;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getDescripCc() {
        return descripCc;
    }

    public void setDescripCc(String descripCc) {
        this.descripCc = descripCc;
    }

    public String getEncargadoCc() {
        return encargadoCc;
    }

    public void setEncargadoCc(String encargadoCc) {
        this.encargadoCc = encargadoCc;
    }

    public String getUltimoNivel() {
        return ultimoNivel;
    }

    public void setUltimoNivel(String ultimoNivel) {
        this.ultimoNivel = ultimoNivel;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getIndMov() {
        return indMov;
    }

    public void setIndMov(String indMov) {
        this.indMov = indMov;
    }

    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public String getRelacionadoACuentas() {
        return relacionadoACuentas;
    }

    public void setRelacionadoACuentas(String relacionadoACuentas) {
        this.relacionadoACuentas = relacionadoACuentas;
    }

    public String getCodEmple() {
        return codEmple;
    }

    public void setCodEmple(String codEmple) {
        this.codEmple = codEmple;
    }

    public String getCodPla() {
        return codPla;
    }

    public void setCodPla(String codPla) {
        this.codPla = codPla;
    }

    public Integer getNoTipoCentro() {
        return noTipoCentro;
    }

    public void setNoTipoCentro(Integer noTipoCentro) {
        this.noTipoCentro = noTipoCentro;
    }

    @Override
    public String toString() {
        return "CentroCosto{" +
                "noCia=" + noCia +
                ", centro='" + centro + '\'' +
                ", descripCc='" + descripCc + '\'' +
                ", encargadoCc='" + encargadoCc + '\'' +
                ", nivel=" + nivel +
                ", activo='" + activo + '\'' +
                ", indMov='" + indMov + '\'' +
                '}';
    }
}
