package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ServicioConPrecioDTO {
    private Integer noArticulo;
    private String descripcion;
    private String familia;
    private String subFamilia;
    private String unidad;
    private BigDecimal costo;
    private Integer noPais;
    private String nombrePais;
    private Integer noProvincia;
    private String nombreProvincia;
    private Integer noDistrito;
    private String nombreDistrito;
    private Integer noCorregimiento;
    private String nombreCorregimiento;
    private Integer noGrupo;
    private String tipo;
    private BigDecimal precioBase;
    private BigDecimal porcentaje;
    private BigDecimal precioVenta;
    private String usuarioMod;
    private LocalDateTime fechaMod;
    private String estadoListaPrecio;
    private BigDecimal precioBaseLista;
    private BigDecimal precioVentaLista;
    private String usuarioListaMod;
    private String indicadorAct;

    // Constructor completo para usar con consultas nativas
    public ServicioConPrecioDTO(Integer noArticulo, String descripcion, String familia, String subFamilia, 
                               String unidad, BigDecimal costo, Integer noPais, String nombrePais, 
                               Integer noProvincia, String nombreProvincia, Integer noDistrito, String nombreDistrito,
                               Integer noCorregimiento, String nombreCorregimiento, Integer noGrupo, String tipo,
                               BigDecimal precioBase, BigDecimal porcentaje, BigDecimal precioVenta, 
                               String usuarioMod, LocalDateTime fechaMod, String estadoListaPrecio,
                               BigDecimal precioBaseLista, BigDecimal precioVentaLista, String usuarioListaMod,
                               String indicadorAct) {
        this.noArticulo = noArticulo;
        this.descripcion = descripcion;
        this.familia = familia;
        this.subFamilia = subFamilia;
        this.unidad = unidad;
        this.costo = costo;
        this.noPais = noPais;
        this.nombrePais = nombrePais;
        this.noProvincia = noProvincia;
        this.nombreProvincia = nombreProvincia;
        this.noDistrito = noDistrito;
        this.nombreDistrito = nombreDistrito;
        this.noCorregimiento = noCorregimiento;
        this.nombreCorregimiento = nombreCorregimiento;
        this.noGrupo = noGrupo;
        this.tipo = tipo;
        this.precioBase = precioBase;
        this.porcentaje = porcentaje;
        this.precioVenta = precioVenta;
        this.usuarioMod = usuarioMod;
        this.fechaMod = fechaMod;
        this.estadoListaPrecio = estadoListaPrecio;
        this.precioBaseLista = precioBaseLista;
        this.precioVentaLista = precioVentaLista;
        this.usuarioListaMod = usuarioListaMod;
        this.indicadorAct = indicadorAct;
    }

    // Constructor vacío
    public ServicioConPrecioDTO() {}

    // Getters y setters
    public Integer getNoArticulo() { return noArticulo; }
    public void setNoArticulo(Integer noArticulo) { this.noArticulo = noArticulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getFamilia() { return familia; }
    public void setFamilia(String familia) { this.familia = familia; }

    public String getSubFamilia() { return subFamilia; }
    public void setSubFamilia(String subFamilia) { this.subFamilia = subFamilia; }

    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }

    public BigDecimal getCosto() { return costo; }
    public void setCosto(BigDecimal costo) { this.costo = costo; }

    public Integer getNoPais() { return noPais; }
    public void setNoPais(Integer noPais) { this.noPais = noPais; }

    public String getNombrePais() { return nombrePais; }
    public void setNombrePais(String nombrePais) { this.nombrePais = nombrePais; }

    public Integer getNoProvincia() { return noProvincia; }
    public void setNoProvincia(Integer noProvincia) { this.noProvincia = noProvincia; }

    public String getNombreProvincia() { return nombreProvincia; }
    public void setNombreProvincia(String nombreProvincia) { this.nombreProvincia = nombreProvincia; }

    public Integer getNoDistrito() { return noDistrito; }
    public void setNoDistrito(Integer noDistrito) { this.noDistrito = noDistrito; }

    public String getNombreDistrito() { return nombreDistrito; }
    public void setNombreDistrito(String nombreDistrito) { this.nombreDistrito = nombreDistrito; }

    public Integer getNoCorregimiento() { return noCorregimiento; }
    public void setNoCorregimiento(Integer noCorregimiento) { this.noCorregimiento = noCorregimiento; }

    public String getNombreCorregimiento() { return nombreCorregimiento; }
    public void setNombreCorregimiento(String nombreCorregimiento) { this.nombreCorregimiento = nombreCorregimiento; }

    public Integer getNoGrupo() { return noGrupo; }
    public void setNoGrupo(Integer noGrupo) { this.noGrupo = noGrupo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public BigDecimal getPrecioBase() { return precioBase; }
    public void setPrecioBase(BigDecimal precioBase) { this.precioBase = precioBase; }

    public BigDecimal getPorcentaje() { return porcentaje; }
    public void setPorcentaje(BigDecimal porcentaje) { this.porcentaje = porcentaje; }

    public BigDecimal getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(BigDecimal precioVenta) { this.precioVenta = precioVenta; }

    public String getUsuarioMod() { return usuarioMod; }
    public void setUsuarioMod(String usuarioMod) { this.usuarioMod = usuarioMod; }

    public LocalDateTime getFechaMod() { return fechaMod; }
    public void setFechaMod(LocalDateTime fechaMod) { this.fechaMod = fechaMod; }

    public String getEstadoListaPrecio() { return estadoListaPrecio; }
    public void setEstadoListaPrecio(String estadoListaPrecio) { this.estadoListaPrecio = estadoListaPrecio; }

    public BigDecimal getPrecioBaseLista() { return precioBaseLista; }
    public void setPrecioBaseLista(BigDecimal precioBaseLista) { this.precioBaseLista = precioBaseLista; }

    public BigDecimal getPrecioVentaLista() { return precioVentaLista; }
    public void setPrecioVentaLista(BigDecimal precioVentaLista) { this.precioVentaLista = precioVentaLista; }

    public String getUsuarioListaMod() { return usuarioListaMod; }
    public void setUsuarioListaMod(String usuarioListaMod) { this.usuarioListaMod = usuarioListaMod; }

    public String getIndicadorAct() { return indicadorAct; }
    public void setIndicadorAct(String indicadorAct) { this.indicadorAct = indicadorAct; }
}
