package com.example.demo.controller;

import com.example.demo.entity.FacwebPrefactura;
import com.example.demo.repository.FacwebPrefacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Imports para OpenAPI/Swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/prefacturas")
@CrossOrigin(origins = "*")
@Tag(name = "Prefacturas Aprobadas", description = "API para consultar prefacturas (cotizaciones aprobadas)")
public class PrefacturaConsultaController {

    @Autowired
    private FacwebPrefacturaRepository prefacturaRepository;

    @GetMapping("/cliente/{noCliente}")
    @Operation(
        summary = "Obtener prefacturas por cliente",
        description = "Retorna todas las prefacturas de un cliente específico ordenadas por fecha de registro descendente"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de prefacturas del cliente"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public List<FacwebPrefactura> getPrefacturasByCliente(
        @Parameter(description = "Número de cliente", required = true)
        @PathVariable Integer noCliente) {
        return prefacturaRepository.findByNoClienteOrderByFechaRegistroDesc(noCliente);
    }

    @GetMapping("/email/{email}")
    @Operation(
        summary = "Obtener prefacturas por email",
        description = "Retorna todas las prefacturas asociadas a un email específico ordenadas por fecha de registro descendente"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de prefacturas del email"),
        @ApiResponse(responseCode = "404", description = "Email no encontrado")
    })
    public List<FacwebPrefactura> getPrefacturasByEmail(
        @Parameter(description = "Email del cliente", required = true)
        @PathVariable String email) {
        return prefacturaRepository.findByEmailOrderByFechaRegistroDesc(email);
    }

    @GetMapping("/estado-despacho/{estadoDespacho}")
    @Operation(
        summary = "Obtener prefacturas por estado de despacho",
        description = "Retorna prefacturas filtradas por estado de despacho (N: Pendiente, P: Parcial, T: Total, C: Cerrado)"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de prefacturas por estado de despacho")
    })
    public List<FacwebPrefactura> getPrefacturasByEstadoDespacho(
        @Parameter(description = "Estado de despacho (N/P/T/C)", required = true)
        @PathVariable String estadoDespacho) {
        return prefacturaRepository.findByEstadoDespachoOrderByFechaRegistroDesc(estadoDespacho);
    }

    @GetMapping("/ruc/{rucCedula}")
    @Operation(
        summary = "Obtener prefacturas por RUC/Cédula",
        description = "Retorna todas las prefacturas asociadas a un RUC o cédula específica"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de prefacturas del RUC/Cédula")
    })
    public List<FacwebPrefactura> getPrefacturasByRucCedula(
        @Parameter(description = "RUC o Cédula del cliente", required = true)
        @PathVariable String rucCedula) {
        return prefacturaRepository.findByRucCedulaOrderByFechaRegistroDesc(rucCedula);
    }

    @GetMapping("/prefactura/{noPrefactura}")
    @Operation(
        summary = "Obtener prefactura por número",
        description = "Retorna una prefactura específica por su número de prefactura"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Prefactura encontrada"),
        @ApiResponse(responseCode = "404", description = "Prefactura no encontrada")
    })
    public List<FacwebPrefactura> getPrefacturaByNoPrefactura(
        @Parameter(description = "Número de prefactura", required = true)
        @PathVariable Integer noPrefactura,
        @Parameter(description = "Número de compañía (opcional)", required = false)
        @RequestParam(required = false) Integer noCia) {
        if (noCia != null) {
            return prefacturaRepository.findByNoCiaAndNoPrefactura(noCia, noPrefactura);
        } else {
            return prefacturaRepository.findByNoPrefactura(noPrefactura);
        }
    }

    @GetMapping("/solicitud/{noSolicitud}")
    @Operation(
        summary = "Obtener prefacturas por número de solicitud",
        description = "Retorna las prefacturas generadas a partir de una solicitud específica"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de prefacturas de la solicitud")
    })
    public List<FacwebPrefactura> getPrefacturasBySolicitud(
        @Parameter(description = "Número de solicitud", required = true)
        @PathVariable Integer noSolicitud) {
        return prefacturaRepository.findByNoSolicitud(noSolicitud);
    }

    @GetMapping("/status/{status}")
    @Operation(
        summary = "Obtener prefacturas por status",
        description = "Retorna prefacturas filtradas por status (G = para Revisar, etc.)"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de prefacturas por status")
    })
    public List<FacwebPrefactura> getPrefacturasByStatus(
        @Parameter(description = "Status de la prefactura", required = true)
        @PathVariable String status,
        @Parameter(description = "Número de compañía", required = false)
        @RequestParam(required = false) Integer noCia) {
        if (noCia != null) {
            return prefacturaRepository.findByNoCiaAndStatusOrderByFechaRegistroDesc(noCia, status);        } else {
            // Si no se especifica compañía, buscar por status solamente
            return prefacturaRepository.findAll().stream()
                .filter(p -> status.equals(p.getStatus()))
                .sorted((p1, p2) -> p2.getFechaRegistro().compareTo(p1.getFechaRegistro()))
                .collect(java.util.stream.Collectors.toList());
        }
    }
}
