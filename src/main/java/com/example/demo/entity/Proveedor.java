package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "genweb_proveedor")
@IdClass(ProveedorId.class)
public class Proveedor implements Serializable {

    @Id
    @Column(name = "NO_CIA", nullable = false)
    private Integer noCia;

    @Id
    @Column(name = "NO_PROVEEDOR", nullable = false)
    private Integer noProveedor;

    @Id
    @Column(name = "NO_CLASE", nullable = false)
    private Integer noClase;

    @Column(name = "NOMBRE", length = 50)
    private String nombre;

    @Column(name = "DIRECCION", length = 300)
    private String direccion;

    @Column(name = "TELEFONO", length = 20)
    private String telefono;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "EMAIL2", length = 50)
    private String email2;

    @Column(name = "FAX", length = 20)
    private String fax;

    @Column(name = "PAGINA_WEB", length = 100)
    private String paginaWeb;

    @Column(name = "TIPO_ID_TRIBUITARIO", length = 1)
    private String tipoIdTributario;

    @Column(name = "CEDULA_RUC", length = 20)
    private String cedulaRuc;

    @Column(name = "DV", length = 3)
    private String dv;

    @Column(name = "ENCARGADO", length = 50)
    private String encargado;

    @Column(name = "PLAZO_PAGO")
    private Integer plazoPago;

    @Column(name = "FECHA_ULTIMA_COMPRA")
    @Temporal(TemporalType.DATE)
    private Date fechaUltimaCompra;

    @Column(name = "TIPO", nullable = false)
    private Integer tipo;

    @Column(name = "OBSERVACIONES", length = 350)
    private String observaciones;

    @Column(name = "IND_NACIONAL", length = 1)
    private String indNacional;

    @Column(name = "CODIGO_TERCERO")
    private Integer codigoTercero;

    @Column(name = "CONDICION_TRIBUITARIA", length = 3)
    private String condicionTributaria;

    @Column(name = "PAIS")
    private Integer pais;

    @Column(name = "PROVINCIA")
    private Integer provincia;

    @Column(name = "DISTRITO")
    private Integer distrito;

    @Column(name = "CORREGIMIENTO")
    private Integer corregimiento;

    @Column(name = "IND_ACEPTA_RETENCIONES", length = 1, nullable = false)
    private String indAceptaRetenciones = "S";

    @Column(name = "FECHA_TRANSACCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTransaccion;

    @Column(name = "USUARIO_TRANSACCION", length = 30)
    private String usuarioTransaccion;

    @Column(name = "NOMBRE_LARGO", length = 80)
    private String nombreLargo;

    @Column(name = "IDENTIFICADOR", length = 3)
    private String identificador;

    @Column(name = "ACTIVO", length = 1)
    private String activo;

    @Column(name = "SALDO", precision = 9, scale = 2)
    private BigDecimal saldo;

    @Column(name = "DISPONIBLE", precision = 9, scale = 2)
    private BigDecimal disponible;

    @Column(name = "LIMITE_CREDI", precision = 9, scale = 2)
    private BigDecimal limiteCredito;

    @Column(name = "MOVIL", length = 50)
    private String movil;

    @Column(name = "APARTADO", length = 20)
    private String apartado;

    @Column(name = "NO_CARGO")
    private Integer noCargo;

    @Column(name = "NOMBRE_CONTACTO_COMPRA", length = 50)
    private String nombreContactoCompra;

    @Column(name = "EMAIL_CONTACTO_COMPRA", length = 50)
    private String emailContactoCompra;

    @Column(name = "tiempo_entrega")
    private Integer tiempoEntrega;

    @Column(name = "NO_PROVEEDOR_ANT", length = 6)
    private String noProveedorAnt;

    @Column(name = "compania_inter_cia")
    private Integer companiaInterCia;

    @Column(name = "EXCENTO_IMP", length = 1)
    private String excentoImp = "N";

    @Column(name = "es_prove_cia", length = 1)
    private String esProveCia = "N";

    // Constructor por defecto
    public Proveedor() {}

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

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
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

    public Integer getPlazoPago() {
        return plazoPago;
    }

    public void setPlazoPago(Integer plazoPago) {
        this.plazoPago = plazoPago;
    }

    public Date getFechaUltimaCompra() {
        return fechaUltimaCompra;
    }

    public void setFechaUltimaCompra(Date fechaUltimaCompra) {
        this.fechaUltimaCompra = fechaUltimaCompra;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getIndNacional() {
        return indNacional;
    }

    public void setIndNacional(String indNacional) {
        this.indNacional = indNacional;
    }

    public Integer getCodigoTercero() {
        return codigoTercero;
    }

    public void setCodigoTercero(Integer codigoTercero) {
        this.codigoTercero = codigoTercero;
    }

    public String getCondicionTributaria() {
        return condicionTributaria;
    }

    public void setCondicionTributaria(String condicionTributaria) {
        this.condicionTributaria = condicionTributaria;
    }

    public Integer getPais() {
        return pais;
    }

    public void setPais(Integer pais) {
        this.pais = pais;
    }

    public Integer getProvincia() {
        return provincia;
    }

    public void setProvincia(Integer provincia) {
        this.provincia = provincia;
    }

    public Integer getDistrito() {
        return distrito;
    }

    public void setDistrito(Integer distrito) {
        this.distrito = distrito;
    }

    public Integer getCorregimiento() {
        return corregimiento;
    }

    public void setCorregimiento(Integer corregimiento) {
        this.corregimiento = corregimiento;
    }

    public String getIndAceptaRetenciones() {
        return indAceptaRetenciones;
    }

    public void setIndAceptaRetenciones(String indAceptaRetenciones) {
        this.indAceptaRetenciones = indAceptaRetenciones;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public String getUsuarioTransaccion() {
        return usuarioTransaccion;
    }

    public void setUsuarioTransaccion(String usuarioTransaccion) {
        this.usuarioTransaccion = usuarioTransaccion;
    }

    public String getNombreLargo() {
        return nombreLargo;
    }

    public void setNombreLargo(String nombreLargo) {
        this.nombreLargo = nombreLargo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getDisponible() {
        return disponible;
    }

    public void setDisponible(BigDecimal disponible) {
        this.disponible = disponible;
    }

    public BigDecimal getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(BigDecimal limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getApartado() {
        return apartado;
    }

    public void setApartado(String apartado) {
        this.apartado = apartado;
    }

    public Integer getNoCargo() {
        return noCargo;
    }

    public void setNoCargo(Integer noCargo) {
        this.noCargo = noCargo;
    }

    public String getNombreContactoCompra() {
        return nombreContactoCompra;
    }

    public void setNombreContactoCompra(String nombreContactoCompra) {
        this.nombreContactoCompra = nombreContactoCompra;
    }

    public String getEmailContactoCompra() {
        return emailContactoCompra;
    }

    public void setEmailContactoCompra(String emailContactoCompra) {
        this.emailContactoCompra = emailContactoCompra;
    }

    public Integer getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(Integer tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public String getNoProveedorAnt() {
        return noProveedorAnt;
    }

    public void setNoProveedorAnt(String noProveedorAnt) {
        this.noProveedorAnt = noProveedorAnt;
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

    public String getEsProveCia() {
        return esProveCia;
    }

    public void setEsProveCia(String esProveCia) {
        this.esProveCia = esProveCia;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "noCia=" + noCia +
                ", noProveedor=" + noProveedor +
                ", noClase=" + noClase +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", activo='" + activo + '\'' +
                '}';
    }
}
