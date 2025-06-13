package com.example.demo.controller;

import com.example.demo.entity.InvwebBodega;
import com.example.demo.repository.InvwebBodegaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Imports para OpenAPI/Swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@RestController
@RequestMapping("/api/bodegas")
@Tag(name = "Bodegas", description = "API para consulta de bodegas del inventario")
public class InvwebBodegaController {
    @Autowired
    private InvwebBodegaRepository bodegaRepository;    @GetMapping
    @Operation(
        summary = "Obtener bodegas disponibles",
        description = "Obtiene una lista de bodegas habilitadas para el portal. Opcionalmente se puede filtrar por compañía."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de bodegas obtenida exitosamente",
                    content = @Content(schema = @Schema(implementation = List.class))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public List<InvwebBodega> getBodegas(
        @Parameter(description = "Número de compañía para filtrar las bodegas", example = "1") 
        @RequestParam(required = false) Integer noCia) {
        if (noCia != null) {
            return bodegaRepository.findByVerPortalAndNoCia("S", noCia);
        } else {
            return bodegaRepository.findByVerPortal("S");
        }    }

    @GetMapping("/{no_sucursal}/{no_bodega}")
    @Operation(
        summary = "Obtener bodega específica",
        description = "Obtiene información de una bodega específica mediante sucursal y número de bodega"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bodega encontrada exitosamente",
                    content = @Content(schema = @Schema(implementation = InvwebBodega.class))),
        @ApiResponse(responseCode = "404", description = "Bodega no encontrada"),
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
    public InvwebBodega getBodegaBySucursalAndBodega(
        @Parameter(description = "Número de sucursal", example = "1", required = true)
        @PathVariable Integer no_sucursal, 
        @Parameter(description = "Número de bodega", example = "1", required = true)
        @PathVariable Integer no_bodega) {
        return bodegaRepository.findAll().stream()
            .filter(b -> b.getNoSucursal().equals(no_sucursal) && b.getNoBodega().equals(no_bodega))
            .findFirst()
            .orElse(null);
    }
}
