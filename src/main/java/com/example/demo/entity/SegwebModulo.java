package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "segweb_modulo")
public class SegwebModulo implements Serializable {
    
    @Id
    @Column(name = "MODULO", length = 2)
    private String modulo;
    
    @Column(name = "NOM_MODULO", length = 40, nullable = false)
    private String nomModulo;
    
    @Column(name = "VALIDA_SEGURIDAD_MENU", length = 1, nullable = false)
    private String validaSeguridadMenu = "S";
    
    @Column(name = "VALIDA_SEGURIDAD_NAF", length = 2)
    private String validaSeguridadNaf = "N";
    
    @Column(name = "ARCHIVO", length = 255)
    private String archivo;
    
    @Column(name = "TAMANO", length = 15)
    private String tamano;
    
    @Column(name = "AGENTE", length = 16)
    private String agente;
    
    @Column(name = "ACTUALIZADO")
    private LocalDateTime actualizado;
    
    @Column(name = "CONTA_COMP", length = 1)
    private String contaComp;
    
    @Column(name = "IND_PORTAL", length = 1)
    private String indPortal = "N";
    
    @Column(name = "ruta", length = 150)
    private String ruta;
    
    // Getters y Setters
    public String getModulo() { return modulo; }
    public void setModulo(String modulo) { this.modulo = modulo; }
    
    public String getNomModulo() { return nomModulo; }
    public void setNomModulo(String nomModulo) { this.nomModulo = nomModulo; }
    
    public String getValidaSeguridadMenu() { return validaSeguridadMenu; }
    public void setValidaSeguridadMenu(String validaSeguridadMenu) { this.validaSeguridadMenu = validaSeguridadMenu; }
    
    public String getValidaSeguridadNaf() { return validaSeguridadNaf; }
    public void setValidaSeguridadNaf(String validaSeguridadNaf) { this.validaSeguridadNaf = validaSeguridadNaf; }
    
    public String getArchivo() { return archivo; }
    public void setArchivo(String archivo) { this.archivo = archivo; }
    
    public String getTamano() { return tamano; }
    public void setTamano(String tamano) { this.tamano = tamano; }
    
    public String getAgente() { return agente; }
    public void setAgente(String agente) { this.agente = agente; }
    
    public LocalDateTime getActualizado() { return actualizado; }
    public void setActualizado(LocalDateTime actualizado) { this.actualizado = actualizado; }
    
    public String getContaComp() { return contaComp; }
    public void setContaComp(String contaComp) { this.contaComp = contaComp; }
    
    public String getIndPortal() { return indPortal; }
    public void setIndPortal(String indPortal) { this.indPortal = indPortal; }
    
    public String getRuta() { return ruta; }
    public void setRuta(String ruta) { this.ruta = ruta; }
}
