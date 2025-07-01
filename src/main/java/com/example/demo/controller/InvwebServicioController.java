package com.example.demo.controller;

import com.example.demo.entity.InvwebServicio;
import com.example.demo.repository.InvwebServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Imports para OpenAPI/Swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
@Tag(name = "Servicios", description = "API para consulta de servicios del inventario con familias habilitadas para portal")
public class InvwebServicioController {
    @Autowired
    private InvwebServicioRepository servicioRepository;

    @GetMapping
    @Operation(
        summary = "Obtener servicios de familias habilitadas para portal",
        description = "Obtiene únicamente los servicios activos que pertenecen a familias con ver_portal='S'. " +
                     "Este endpoint filtra solo servicios de familias habilitadas para mostrar en el portal."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de servicios obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public List<InvwebServicio> getServicios(
            @Parameter(description = "Estado activo de los servicios", example = "S", required = false)
            @RequestParam(required = false, defaultValue = "S") String activo) {
        return servicioRepository.findActivosConFamiliaVerPortal(activo);
    }
}
