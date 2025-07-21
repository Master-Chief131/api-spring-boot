package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "segweb_usuario")
public class Usuario {
    
    @Id
    @Column(name = "usuario", length = 50)
    private String usuario;
    
    @Column(name = "nom_usuario", length = 50)
    private String nomUsuario;
    
    @Column(name = "ape_usuario", length = 50)
    private String apeUsuario;
    
    @Column(name = "email", length = 100)
    private String email;
    
    @Column(name = "clave", length = 100)
    private String clave;
    
    @Column(name = "ind_activo", length = 1)
    private String indActivo;
    
    @Column(name = "ind_cliente", length = 1)
    private String indCliente;
    
    // Constructores
    public Usuario() {}
    
    public Usuario(String usuario, String email) {
        this.usuario = usuario;
        this.email = email;
    }
    
    // Getters y Setters
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    
    public String getNomUsuario() { return nomUsuario; }
    public void setNomUsuario(String nomUsuario) { this.nomUsuario = nomUsuario; }
    
    public String getApeUsuario() { return apeUsuario; }
    public void setApeUsuario(String apeUsuario) { this.apeUsuario = apeUsuario; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }
    
    public String getIndActivo() { return indActivo; }
    public void setIndActivo(String indActivo) { this.indActivo = indActivo; }
    
    public String getIndCliente() { return indCliente; }
    public void setIndCliente(String indCliente) { this.indCliente = indCliente; }
}
