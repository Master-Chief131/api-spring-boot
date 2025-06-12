package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para el cambio de contraseña de usuario")
public class CambioClaveDTO {
    
    @Schema(description = "Nombre de usuario", example = "juan.perez", required = true)
    private String usuario;
    
    @Schema(description = "Contraseña actual del usuario", example = "claveActual123", required = true)
    private String claveActual;
    
    @Schema(description = "Nueva contraseña del usuario", example = "nuevaClave456", required = true)
    private String claveNueva;
    
    // Constructores
    public CambioClaveDTO() {}
    
    public CambioClaveDTO(String usuario, String claveActual, String claveNueva) {
        this.usuario = usuario;
        this.claveActual = claveActual;
        this.claveNueva = claveNueva;
    }
    
    // Getters y Setters
    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getClaveActual() {
        return claveActual;
    }
    
    public void setClaveActual(String claveActual) {
        this.claveActual = claveActual;
    }
    
    public String getClaveNueva() {
        return claveNueva;
    }
    
    public void setClaveNueva(String claveNueva) {
        this.claveNueva = claveNueva;
    }
    
    // Validaciones
    public boolean isValid() {
        return usuario != null && !usuario.trim().isEmpty() &&
               claveActual != null && !claveActual.trim().isEmpty() &&
               claveNueva != null && !claveNueva.trim().isEmpty();
    }
    
    @Override
    public String toString() {
        return "CambioClaveDTO{" +
                "usuario='" + usuario + '\'' +
                ", claveActual='***'" +
                ", claveNueva='***'" +
                '}';
    }
}
