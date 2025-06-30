package com.example.demo.dto;

import com.example.demo.entity.Proveedor;

/**
 * DTO para mostrar información simplificada de los proveedores
 */
public class ProveedorDTO {
    
    private Integer noCia;
    private Integer noProveedor;
    private Integer noClase;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String tipoIdTributario;
    private String cedulaRuc;
    private String dv;
    private String encargado;
    private Integer tipo;
    private String indNacional;
    private String condicionTributaria;
    private String indAceptaRetenciones;
    private String activo;
    private Integer companiaInterCia;
    private String excentoImp;
    
    // Campos calculados para mejor presentación
    private String estadoDescripcion;
    private String tipoNacionalidad;
    private String aceptaRetencionesTexto;
    private String tipoProveedorTexto;
    
    // Constructor por defecto
    public ProveedorDTO() {}
    
    // Constructor que recibe una entidad Proveedor
    public ProveedorDTO(Proveedor proveedor) {
        this.noCia = proveedor.getNoCia();
        this.noProveedor = proveedor.getNoProveedor();
        this.noClase = proveedor.getNoClase();
        this.nombre = proveedor.getNombre();
        this.direccion = proveedor.getDireccion();
        this.telefono = proveedor.getTelefono();
        this.email = proveedor.getEmail();
        this.tipoIdTributario = proveedor.getTipoIdTributario();
        this.cedulaRuc = proveedor.getCedulaRuc();
        this.dv = proveedor.getDv();
        this.encargado = proveedor.getEncargado();
        this.tipo = proveedor.getTipo();
        this.indNacional = proveedor.getIndNacional();
        this.condicionTributaria = proveedor.getCondicionTributaria();
        this.indAceptaRetenciones = proveedor.getIndAceptaRetenciones();
        this.activo = proveedor.getActivo();
        this.companiaInterCia = proveedor.getCompaniaInterCia();
        this.excentoImp = proveedor.getExcentoImp();
        
        // Descripciones amigables
        this.estadoDescripcion = "S".equals(proveedor.getActivo()) ? "Activo" : "Inactivo";
        this.tipoNacionalidad = "N".equals(proveedor.getIndNacional()) ? "Nacional" : "Extranjero";
        this.aceptaRetencionesTexto = "S".equals(proveedor.getIndAceptaRetenciones()) ? "Sí" : "No";
        this.tipoProveedorTexto = "Proveedor Externo"; // Valor por defecto ya que se eliminó el campo esProveCia
    }

    // Getters y Setters
    public Integer getNoCia() {
        return noCia;
    }

    public void setNoCia(Integer noCia) {
        this.noCia = noCia;
    }

    public Integer getNoProveedor() {
        return noProveedor;
    }

    public void setNoProveedor(Integer noProveedor) {
        this.noProveedor = noProveedor;
    }

    public Integer getNoClase() {
        return noClase;
    }

    public void setNoClase(Integer noClase) {
        this.noClase = noClase;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoIdTributario() {
        return tipoIdTributario;
    }

    public void setTipoIdTributario(String tipoIdTributario) {
        this.tipoIdTributario = tipoIdTributario;
    }

    public String getCedulaRuc() {
        return cedulaRuc;
    }

    public void setCedulaRuc(String cedulaRuc) {
        this.cedulaRuc = cedulaRuc;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getIndNacional() {
        return indNacional;
    }

    public void setIndNacional(String indNacional) {
        this.indNacional = indNacional;
    }

    public String getCondicionTributaria() {
        return condicionTributaria;
    }

    public void setCondicionTributaria(String condicionTributaria) {
        this.condicionTributaria = condicionTributaria;
    }

    public String getIndAceptaRetenciones() {
        return indAceptaRetenciones;
    }

    public void setIndAceptaRetenciones(String indAceptaRetenciones) {
        this.indAceptaRetenciones = indAceptaRetenciones;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }





















    public Integer getCompaniaInterCia() {
        return companiaInterCia;
    }

    public void setCompaniaInterCia(Integer companiaInterCia) {
        this.companiaInterCia = companiaInterCia;
    }

    public String getExcentoImp() {
        return excentoImp;
    }

    public void setExcentoImp(String excentoImp) {
        this.excentoImp = excentoImp;
    }



    public String getEstadoDescripcion() {
        return estadoDescripcion;
    }

    public void setEstadoDescripcion(String estadoDescripcion) {
        this.estadoDescripcion = estadoDescripcion;
    }

    public String getTipoNacionalidad() {
        return tipoNacionalidad;
    }

    public void setTipoNacionalidad(String tipoNacionalidad) {
        this.tipoNacionalidad = tipoNacionalidad;
    }

    public String getAceptaRetencionesTexto() {
        return aceptaRetencionesTexto;
    }

    public void setAceptaRetencionesTexto(String aceptaRetencionesTexto) {
        this.aceptaRetencionesTexto = aceptaRetencionesTexto;
    }

    public String getTipoProveedorTexto() {
        return tipoProveedorTexto;
    }

    public void setTipoProveedorTexto(String tipoProveedorTexto) {
        this.tipoProveedorTexto = tipoProveedorTexto;
    }

    @Override
    public String toString() {
        return "ProveedorDTO{" +
                "noCia=" + noCia +
                ", noProveedor=" + noProveedor +
                ", noClase=" + noClase +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", activo='" + activo + '\'' +
                '}';
    }
}
