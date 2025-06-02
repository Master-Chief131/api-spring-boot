package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "invweb_sucursal")
@IdClass(SucursalId.class)
public class InvwebSucursal implements Serializable {
    @Id
    @Column(name = "NO_CIA")
    private Integer noCia;

    @Id
    @Column(name = "NO_SUCURSAL")
    private Integer noSucursal;

    @Column(name = "NOMBRE", length = 100)
    private String nombre;

    @Column(name = "DIRECCION", length = 100)
    private String direccion;

    @Column(name = "TELEFONO1", length = 20)
    private String telefono1;

    @Column(name = "TELEFONO2", length = 20)
    private String telefono2;

    @Column(name = "FAX", length = 20)
    private String fax;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "ACTIVO", length = 1)
    private String activo;

    @Column(name = "CENTRO_COSTO", length = 9)
    private String centroCosto;

    @Column(name = "CUENTA_CAJA", length = 15)
    private String cuentaCaja;

    @Column(name = "usuario_crea", length = 50)
    private String usuarioCrea;

    @Column(name = "fecha_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCrea;

    @Column(name = "usuario_mod", length = 50)
    private String usuarioMod;

    @Column(name = "fecha_mod")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMod;

    @Column(name = "pais")
    private Integer pais;

    @Column(name = "provincia")
    private Integer provincia;

    @Column(name = "distrito")
    private Integer distrito;

    @Column(name = "corregimiento")
    private Integer corregimiento;

    @Column(name = "VER_PORTAL")
    private String verPortal;

    public Integer getNoCia() { return noCia; }
    public void setNoCia(Integer noCia) { this.noCia = noCia; }
    public Integer getNoSucursal() { return noSucursal; }
    public void setNoSucursal(Integer noSucursal) { this.noSucursal = noSucursal; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getTelefono1() { return telefono1; }
    public void setTelefono1(String telefono1) { this.telefono1 = telefono1; }
    public String getTelefono2() { return telefono2; }
    public void setTelefono2(String telefono2) { this.telefono2 = telefono2; }
    public String getFax() { return fax; }
    public void setFax(String fax) { this.fax = fax; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getActivo() { return activo; }
    public void setActivo(String activo) { this.activo = activo; }
    public String getCentroCosto() { return centroCosto; }
    public void setCentroCosto(String centroCosto) { this.centroCosto = centroCosto; }
    public String getCuentaCaja() { return cuentaCaja; }
    public void setCuentaCaja(String cuentaCaja) { this.cuentaCaja = cuentaCaja; }
    public String getUsuarioCrea() { return usuarioCrea; }
    public void setUsuarioCrea(String usuarioCrea) { this.usuarioCrea = usuarioCrea; }
    public java.util.Date getFechaCrea() { return fechaCrea; }
    public void setFechaCrea(java.util.Date fechaCrea) { this.fechaCrea = fechaCrea; }
    public String getUsuarioMod() { return usuarioMod; }
    public void setUsuarioMod(String usuarioMod) { this.usuarioMod = usuarioMod; }
    public java.util.Date getFechaMod() { return fechaMod; }
    public void setFechaMod(java.util.Date fechaMod) { this.fechaMod = fechaMod; }
    public Integer getPais() { return pais; }
    public void setPais(Integer pais) { this.pais = pais; }
    public Integer getProvincia() { return provincia; }
    public void setProvincia(Integer provincia) { this.provincia = provincia; }
    public Integer getDistrito() { return distrito; }
    public void setDistrito(Integer distrito) { this.distrito = distrito; }
    public Integer getCorregimiento() { return corregimiento; }
    public void setCorregimiento(Integer corregimiento) { this.corregimiento = corregimiento; }
    public String getVerPortal() { return verPortal; }
    public void setVerPortal(String verPortal) { this.verPortal = verPortal; }
}
