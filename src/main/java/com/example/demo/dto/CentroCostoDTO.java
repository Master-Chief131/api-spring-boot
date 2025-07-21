package com.example.demo.dto;

import com.example.demo.entity.CentroCosto;

/**
 * DTO para mostrar información simplificada de los centros de costo
 */
public class CentroCostoDTO {
    
    private Integer noCia;
    private String centro;
    private String descripCc;
    private String encargadoCc;
    private String ultimoNivel;
    private String padre;
    private Integer nivel;
    private String activo;
    private String indMov;
    private String tipoGasto;
    private String relacionadoACuentas;
    private String codEmple;
    private String codPla;
    private Integer noTipoCentro;
    private String estadoDescripcion;
    private String tipoMovimiento;
    private String relacionDescripcion;
    
    // Constructor por defecto
    public CentroCostoDTO() {}
    
    // Constructor que recibe una entidad CentroCosto
    public CentroCostoDTO(CentroCosto centroCosto) {
        this.noCia = centroCosto.getNoCia();
        this.centro = centroCosto.getCentro();
        this.descripCc = centroCosto.getDescripCc();
        this.encargadoCc = centroCosto.getEncargadoCc();
        this.ultimoNivel = centroCosto.getUltimoNivel();
        this.padre = centroCosto.getPadre();
        this.nivel = centroCosto.getNivel();
        this.activo = centroCosto.getActivo();
        this.indMov = centroCosto.getIndMov();
        this.tipoGasto = centroCosto.getTipoGasto();
        this.relacionadoACuentas = centroCosto.getRelacionadoACuentas();
        this.codEmple = centroCosto.getCodEmple();
        this.codPla = centroCosto.getCodPla();
        this.noTipoCentro = centroCosto.getNoTipoCentro();
        
        // Descripciones amigables
        this.estadoDescripcion = "S".equals(centroCosto.getActivo()) ? "Activo" : "Inactivo";
        this.tipoMovimiento = "S".equals(centroCosto.getIndMov()) ? "Permite Movimiento" : "No Permite Movimiento";
        
        // Descripción del tipo de relación
        if ("A".equals(centroCosto.getRelacionadoACuentas())) {
            this.relacionDescripcion = "Administración";
        } else if ("C".equals(centroCosto.getRelacionadoACuentas())) {
            this.relacionDescripcion = "Cuentas de Costo";
        } else if ("G".equals(centroCosto.getRelacionadoACuentas())) {
            this.relacionDescripcion = "Cuentas de Gasto";
        } else {
            this.relacionDescripcion = "No Especificado";
        }
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

    public String getEstadoDescripcion() {
        return estadoDescripcion;
    }

    public void setEstadoDescripcion(String estadoDescripcion) {
        this.estadoDescripcion = estadoDescripcion;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getRelacionDescripcion() {
        return relacionDescripcion;
    }

    public void setRelacionDescripcion(String relacionDescripcion) {
        this.relacionDescripcion = relacionDescripcion;
    }

    @Override
    public String toString() {
        return "CentroCostoDTO{" +
                "centro='" + centro + '\'' +
                ", descripCc='" + descripCc + '\'' +
                ", encargadoCc='" + encargadoCc + '\'' +
                ", nivel=" + nivel +
                ", activo='" + activo + '\'' +
                ", estadoDescripcion='" + estadoDescripcion + '\'' +
                '}';
    }
}
