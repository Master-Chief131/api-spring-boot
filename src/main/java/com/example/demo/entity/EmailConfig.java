package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "arweb_cia")
public class EmailConfig {
    
    @Id
    @Column(name = "no_cia")
    private Integer noCia;
    
    @Column(name = "host_correo")
    private String hostCorreo;
    
    @Column(name = "usuario")
    private String usuario;
    
    @Column(name = "contrasena")
    private String contrasena;
    
    @Column(name = "email_from")
    private String emailFrom;
      @Column(name = "host_puerto")
    private String hostPuerto;
    
    @Column(name = "cifrado")
    private String cifrado;
    
    @Column(name = "autenticar_correo", columnDefinition = "TINYINT")
    private Integer autenticarCorreo;
    
    @Column(name = "EMAIL_HELPDESK")
    private String emailHelpdesk;
    
    @Column(name = "CONTRASENA_HD_FROM")
    private String contrasenaHdFrom;
    
    @Column(name = "EMAIL_HD_FROM")
    private String emailHdFrom;
    
    // Constructors
    public EmailConfig() {}
    
    // Getters and Setters
    public Integer getNoCia() {
        return noCia;
    }
    
    public void setNoCia(Integer noCia) {
        this.noCia = noCia;
    }
    
    public String getHostCorreo() {
        return hostCorreo;
    }
    
    public void setHostCorreo(String hostCorreo) {
        this.hostCorreo = hostCorreo;
    }
    
    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getContrasena() {
        return contrasena;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public String getEmailFrom() {
        return emailFrom;
    }
    
    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }
      public String getHostPuerto() {
        return hostPuerto;
    }
    
    public void setHostPuerto(String hostPuerto) {
        this.hostPuerto = hostPuerto;
    }
    
    public String getCifrado() {
        return cifrado;
    }
    
    public void setCifrado(String cifrado) {
        this.cifrado = cifrado;
    }    public Integer getAutenticarCorreo() {
        return autenticarCorreo;
    }
    
    public void setAutenticarCorreo(Integer autenticarCorreo) {
        this.autenticarCorreo = autenticarCorreo;
    }
    
    public String getEmailHelpdesk() {
        return emailHelpdesk;
    }
    
    public void setEmailHelpdesk(String emailHelpdesk) {
        this.emailHelpdesk = emailHelpdesk;
    }
    
    public String getContrasenaHdFrom() {
        return contrasenaHdFrom;
    }
    
    public void setContrasenaHdFrom(String contrasenaHdFrom) {
        this.contrasenaHdFrom = contrasenaHdFrom;
    }
    
    public String getEmailHdFrom() {
        return emailHdFrom;
    }
    
    public void setEmailHdFrom(String emailHdFrom) {
        this.emailHdFrom = emailHdFrom;
    }
}
