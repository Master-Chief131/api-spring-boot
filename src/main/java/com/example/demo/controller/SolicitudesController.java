package com.example.demo.controller;

import com.example.demo.dto.SolicitudCompletaDTO;
import com.example.demo.dto.SolicitudDetalleDTO;
import com.example.demo.entity.FacwebSolicitud;
import com.example.demo.entity.FacwebSolicitudDeta;
import com.example.demo.repository.FacwebSolicitudRepository;
import com.example.demo.repository.FacwebSolicitudDetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Imports para OpenAPI/Swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/solicitudes")
@Tag(name = "Solicitudes", description = "API para consulta de solicitudes de clientes y sus detalles")
public class SolicitudesController {
    
    @Autowired
    private FacwebSolicitudRepository solicitudRepository;
    
    @Autowired
    private FacwebSolicitudDetaRepository solicitudDetaRepository;

    @GetMapping
    @Operation(
        summary = "Obtener todas las solicitudes",
        description = "Obtiene todas las solicitudes para una compañía específica. " +
                     "Puede filtrar por cliente, status y otros criterios."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de solicitudes obtenida exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros de entrada inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<SolicitudCompletaDTO>> getSolicitudes(
            @Parameter(description = "Número de compañía", example = "1", required = false)
            @RequestParam(required = false, defaultValue = "1") Integer noCia,
            @Parameter(description = "Número de cliente", example = "12345", required = false)
            @RequestParam(required = false) Integer noCliente,
            @Parameter(description = "Status de la solicitud", example = "P", required = false)
            @RequestParam(required = false) String status) {
        
        List<FacwebSolicitud> solicitudes;
        
        if (noCliente != null && status != null) {
            solicitudes = solicitudRepository.findByNoCiaAndNoClienteAndStatus(noCia, noCliente, status);
        } else if (noCliente != null) {
            solicitudes = solicitudRepository.findByNoCiaAndNoCliente(noCia, noCliente);
        } else if (status != null) {
            solicitudes = solicitudRepository.findByNoCiaAndStatus(noCia, status);
        } else {
            solicitudes = solicitudRepository.findByNoCia(noCia);
        }
        
        List<SolicitudCompletaDTO> resultado = solicitudes.stream()
            .map(this::convertirASolicitudCompleta)
            .collect(Collectors.toList());
            
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{noSolicitud}")
    @Operation(
        summary = "Obtener solicitud específica con detalles",
        description = "Obtiene una solicitud específica junto con todos sus detalles de artículos."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Solicitud encontrada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Solicitud no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<?> getSolicitud(
            @Parameter(description = "Número de compañía", example = "1", required = false)
            @RequestParam(required = false, defaultValue = "1") Integer noCia,
            @Parameter(description = "Número de solicitud", example = "12345", required = true)
            @PathVariable Integer noSolicitud) {
        
        Optional<FacwebSolicitud> solicitudOpt = solicitudRepository.findById(
            new com.example.demo.entity.SolicitudId(noCia, noSolicitud));
        
        if (!solicitudOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        SolicitudCompletaDTO resultado = convertirASolicitudCompleta(solicitudOpt.get());
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/cliente/{noCliente}")
    @Operation(
        summary = "Obtener solicitudes de un cliente específico",
        description = "Obtiene todas las solicitudes de un cliente específico."
    )
    public ResponseEntity<List<SolicitudCompletaDTO>> getSolicitudesPorCliente(
            @Parameter(description = "Número de compañía", example = "1", required = false)
            @RequestParam(required = false, defaultValue = "1") Integer noCia,
            @Parameter(description = "Número de cliente", example = "12345", required = true)
            @PathVariable Integer noCliente) {
        
        List<FacwebSolicitud> solicitudes = solicitudRepository.findByNoCiaAndNoCliente(noCia, noCliente);
        
        List<SolicitudCompletaDTO> resultado = solicitudes.stream()
            .map(this::convertirASolicitudCompleta)
            .collect(Collectors.toList());
            
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/recientes")
    @Operation(
        summary = "Obtener solicitudes recientes",
        description = "Obtiene las solicitudes de los últimos 30 días."
    )
    public ResponseEntity<List<SolicitudCompletaDTO>> getSolicitudesRecientes(
            @Parameter(description = "Número de compañía", example = "1", required = false)
            @RequestParam(required = false, defaultValue = "1") Integer noCia) {
        
        List<FacwebSolicitud> solicitudes = solicitudRepository.findSolicitudesRecientes(noCia);
        
        List<SolicitudCompletaDTO> resultado = solicitudes.stream()
            .map(this::convertirASolicitudCompleta)
            .collect(Collectors.toList());
            
        return ResponseEntity.ok(resultado);
    }

    private SolicitudCompletaDTO convertirASolicitudCompleta(FacwebSolicitud solicitud) {
        SolicitudCompletaDTO dto = new SolicitudCompletaDTO(solicitud);
        
        // Obtener los detalles de la solicitud
        List<FacwebSolicitudDeta> detalles = solicitudDetaRepository
            .findByNoCiaAndNoSolicitudOrderByNoLinea(solicitud.getNoCia(), solicitud.getNoSolicitud());
        
        List<SolicitudDetalleDTO> detallesDTO = detalles.stream()
            .map(SolicitudDetalleDTO::new)
            .collect(Collectors.toList());
        
        dto.setDetalles(detallesDTO);
        return dto;
    }
}
