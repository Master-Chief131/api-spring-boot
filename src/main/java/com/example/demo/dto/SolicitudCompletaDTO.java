package com.example.demo.dto;

import com.example.demo.entity.FacwebSolicitud;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class SolicitudCompletaDTO {
    
    // Datos de la solicitud principal
    private Integer noCia;
    private Integer noSolicitud;
    private Integer noSucursal;
    private Integer noBodega;
    private LocalDateTime fechaRegistro;
    private String noVendedor;
    private Integer noCliente;
    private String nombreCliente;
    private String tipoSolicitud;
    private String status;
    private BigDecimal totalNominal;
    private BigDecimal totalDolar;
    private String observacion;
    private String origenSolicitud;
    private LocalDate fechaEntrega;
    private String email;
    private String clienteTelefono;
    private String clienteDireccion;
    private String rucCedula;
    
    // Lista de detalles
    private List<SolicitudDetalleDTO> detalles;
    
    // Constructor vacío
    public SolicitudCompletaDTO() {}
    
    // Constructor que recibe la entidad FacwebSolicitud
    public SolicitudCompletaDTO(FacwebSolicitud solicitud) {
        this.noCia = solicitud.getNoCia();
        this.noSolicitud = solicitud.getNoSolicitud();
        this.noSucursal = solicitud.getNoSucursal();
        this.noBodega = solicitud.getNoBodega();
        // Conversión de Timestamp a LocalDateTime
        this.fechaRegistro = solicitud.getFechaRegistro() != null ? 
            solicitud.getFechaRegistro().toLocalDateTime() : null;
        this.noVendedor = solicitud.getNoVendedor();
        this.noCliente = solicitud.getNoCliente();
        this.nombreCliente = solicitud.getNombreCliente();
        this.tipoSolicitud = solicitud.getTipoSolicitud();
        this.status = solicitud.getStatus();
        this.totalNominal = solicitud.getTotalNominal();
        this.totalDolar = solicitud.getTotalDolar();
        this.observacion = solicitud.getObservacion();
        this.origenSolicitud = solicitud.getOrigenSolicitud();
        // Conversión de Date a LocalDate
        this.fechaEntrega = solicitud.getFechaEntrega() != null ? 
            solicitud.getFechaEntrega().toLocalDate() : null;
        this.email = solicitud.getEmail();
        this.clienteTelefono = solicitud.getClienteTelefono();
        this.clienteDireccion = solicitud.getClienteDireccion();
        this.rucCedula = solicitud.getRucCedula();
    }

    // Getters y Setters
    public Integer getNoCia() { return noCia; }
    public void setNoCia(Integer noCia) { this.noCia = noCia; }

    public Integer getNoSolicitud() { return noSolicitud; }
    public void setNoSolicitud(Integer noSolicitud) { this.noSolicitud = noSolicitud; }

    public Integer getNoSucursal() { return noSucursal; }
    public void setNoSucursal(Integer noSucursal) { this.noSucursal = noSucursal; }

    public Integer getNoBodega() { return noBodega; }
    public void setNoBodega(Integer noBodega) { this.noBodega = noBodega; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public String getNoVendedor() { return noVendedor; }
    public void setNoVendedor(String noVendedor) { this.noVendedor = noVendedor; }

    public Integer getNoCliente() { return noCliente; }
    public void setNoCliente(Integer noCliente) { this.noCliente = noCliente; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public String getTipoSolicitud() { return tipoSolicitud; }
    public void setTipoSolicitud(String tipoSolicitud) { this.tipoSolicitud = tipoSolicitud; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public BigDecimal getTotalNominal() { return totalNominal; }
    public void setTotalNominal(BigDecimal totalNominal) { this.totalNominal = totalNominal; }

    public BigDecimal getTotalDolar() { return totalDolar; }
    public void setTotalDolar(BigDecimal totalDolar) { this.totalDolar = totalDolar; }

    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }

    public String getOrigenSolicitud() { return origenSolicitud; }
    public void setOrigenSolicitud(String origenSolicitud) { this.origenSolicitud = origenSolicitud; }

    public LocalDate getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDate fechaEntrega) { this.fechaEntrega = fechaEntrega; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getClienteTelefono() { return clienteTelefono; }
    public void setClienteTelefono(String clienteTelefono) { this.clienteTelefono = clienteTelefono; }

    public String getClienteDireccion() { return clienteDireccion; }
    public void setClienteDireccion(String clienteDireccion) { this.clienteDireccion = clienteDireccion; }

    public String getRucCedula() { return rucCedula; }
    public void setRucCedula(String rucCedula) { this.rucCedula = rucCedula; }

    public List<SolicitudDetalleDTO> getDetalles() { return detalles; }
    public void setDetalles(List<SolicitudDetalleDTO> detalles) { this.detalles = detalles; }
}
