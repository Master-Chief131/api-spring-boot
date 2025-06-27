package com.example.demo.dto;

import com.example.demo.entity.Cuenta;
import java.math.BigDecimal;

/**
 * DTO para mostrar información simplificada de las cuentas contables
 */
public class CuentaDTO {
    
    private Integer noCia;
    private String cuenta;
    private String descripcion;
    private String descripcionLarga;
    private Integer tipo;
    private String clase;
    private String indMov;
    private String nivel;
    private String padre;
    private String activo;
    private BigDecimal saldoActual;
    private String tipoMovimiento;
    private String estadoDescripcion;
    
    // Constructor por defecto
    public CuentaDTO() {}
    
    // Constructor que recibe una entidad Cuenta
    public CuentaDTO(Cuenta cuenta) {
        this.noCia = cuenta.getNoCia();
        this.cuenta = cuenta.getCuenta();
        this.descripcion = cuenta.getDescripcion();
        this.descripcionLarga = cuenta.getDescripLarga() != null ? cuenta.getDescripLarga() : cuenta.getDescrip1();
        this.tipo = cuenta.getTipo();
        this.clase = cuenta.getClase();
        this.indMov = cuenta.getIndMov();
        this.nivel = cuenta.getNivel();
        this.padre = cuenta.getPadre();
        this.activo = cuenta.getActivo();
        
        // Calcular saldo actual (ejemplo simple)
        BigDecimal debitos = cuenta.getDebitos() != null ? cuenta.getDebitos() : BigDecimal.ZERO;
        BigDecimal creditos = cuenta.getCreditos() != null ? cuenta.getCreditos() : BigDecimal.ZERO;
        
        if ("D".equals(cuenta.getNaturaleza())) {
            this.saldoActual = debitos.subtract(creditos);
        } else {
            this.saldoActual = creditos.subtract(debitos);
        }
        
        // Descripciones amigables
        this.tipoMovimiento = "S".equals(cuenta.getIndMov()) ? "Permite Movimiento" : "No Permite Movimiento";
        this.estadoDescripcion = "S".equals(cuenta.getActivo()) ? "Activa" : "Inactiva";
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

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
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

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public BigDecimal getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(BigDecimal saldoActual) {
        this.saldoActual = saldoActual;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getEstadoDescripcion() {
        return estadoDescripcion;
    }

    public void setEstadoDescripcion(String estadoDescripcion) {
        this.estadoDescripcion = estadoDescripcion;
    }

    @Override
    public String toString() {
        return "CuentaDTO{" +
                "cuenta='" + cuenta + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipo=" + tipo +
                ", activo='" + activo + '\'' +
                ", saldoActual=" + saldoActual +
                '}';
    }
}
