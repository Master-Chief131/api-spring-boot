package com.example.demo.controller;

import com.example.demo.dto.FacturaCompletaDTO;
import com.example.demo.dto.FacturaDetalleDTO;
import com.example.demo.dto.FacturasPaginadasDTO;
import com.example.demo.entity.FacwebFactura;
import com.example.demo.entity.FacwebFacturaDeta;
import com.example.demo.repository.FacwebFacturaRepository;
import com.example.demo.repository.FacwebFacturaDetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/facturas")
@CrossOrigin(origins = "*")
public class FacturasController {

    /*
     * OPTIMIZACIONES IMPLEMENTADAS PARA GRANDES VOLÚMENES DE DATOS:
     * 
     * 1. PAGINACIÓN OBLIGATORIA:
     *    - Búsquedas generales: Paginación obligatoria (máximo 100 por página)
     *    - Rango de fechas general: Paginación obligatoria
     * 
     * 2. LÍMITES EN CONSULTAS ESPECÍFICAS:
     *    - Facturas por cliente: Límite de 1000 registros
     *    - Facturas por cliente + fechas: Límite de 500 registros
     *    - Facturas recientes generales: Límite de 100 registros (con paginación)
     *    - Facturas recientes por cliente: Límite de 50 registros
     * 
     * 3. OPTIMIZACIÓN DE CONSULTAS:
     *    - Detalles cargados en batch para evitar N+1 queries
     *    - Uso de consultas nativas SQL para mejor rendimiento en límites
     *    - Índices implícitos en no_cia, no_cliente, status, fecha_registro
     */

    @Autowired
    private FacwebFacturaRepository facturaRepository;

    @Autowired
    private FacwebFacturaDetaRepository facturaDetaRepository;

    /**
     * Listar facturas facturadas (status = 'F') de una compañía - CON PAGINACIÓN
     */
    @GetMapping
    public ResponseEntity<FacturasPaginadasDTO> listarFacturas(
            @RequestParam Integer noCia,
            @RequestParam(required = false) Integer noCliente,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaDesde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaHasta,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "20") int tamano) {
        
        try {
            // Validar tamaño de página (máximo 100 registros por página)
            if (tamano > 100) {
                tamano = 100;
            }
            
            Pageable pageable = PageRequest.of(pagina, tamano);
            Page<FacwebFactura> paginaFacturas;
            
            if (fechaDesde != null && fechaHasta != null) {
                // Búsqueda por rango de fechas
                if (noCliente != null) {
                    // Cliente específico en rango de fechas - SIN PAGINACIÓN (ya es específico)
                    List<FacwebFactura> facturas = facturaRepository.findFacturasPorClienteYRangoFechas(noCia, noCliente, fechaDesde, fechaHasta);
                    return ResponseEntity.ok(crearRespuestaSinPaginacion(facturas));
                } else {
                    // Rango de fechas general - CON PAGINACIÓN
                    paginaFacturas = facturaRepository.findFacturasPorRangoFechas(noCia, fechaDesde, fechaHasta, pageable);
                }
            } else if (noCliente != null) {
                // Cliente específico - SIN PAGINACIÓN (ya es específico)
                List<FacwebFactura> facturas = facturaRepository.findByNoCiaAndNoClienteAndStatusOrderByFechaRegistroDesc(noCia, noCliente, "F");
                return ResponseEntity.ok(crearRespuestaSinPaginacion(facturas));
            } else {
                // Búsqueda general - CON PAGINACIÓN OBLIGATORIA
                paginaFacturas = facturaRepository.findByNoCiaAndStatusOrderByFechaRegistroDesc(noCia, "F", pageable);
            }

            List<FacturaCompletaDTO> facturasCompletas = convertirAFacturasCompletas(paginaFacturas.getContent());
            
            FacturasPaginadasDTO respuesta = new FacturasPaginadasDTO(
                facturasCompletas,
                paginaFacturas.getNumber(),
                paginaFacturas.getTotalPages(),
                paginaFacturas.getTotalElements(),
                paginaFacturas.getSize(),
                paginaFacturas.isLast(),
                paginaFacturas.isFirst()
            );
            
            return ResponseEntity.ok(respuesta);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Obtener una factura específica con sus detalles
     */
    @GetMapping("/{noFactura}")
    public ResponseEntity<FacturaCompletaDTO> obtenerFactura(
            @RequestParam Integer noCia,
            @PathVariable Integer noFactura) {
        
        try {
            FacwebFactura factura = facturaRepository.findFacturaCompleta(noCia, noFactura);
            
            if (factura == null) {
                return ResponseEntity.notFound().build();
            }

            List<FacwebFactura> facturas = Arrays.asList(factura);
            List<FacturaCompletaDTO> facturasCompletas = convertirAFacturasCompletas(facturas);
            
            return ResponseEntity.ok(facturasCompletas.get(0));
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Listar facturas de un cliente específico
     */
    @GetMapping("/cliente/{noCliente}")
    public ResponseEntity<List<FacturaCompletaDTO>> listarFacturasPorCliente(
            @RequestParam Integer noCia,
            @PathVariable Integer noCliente) {
        
        try {
            List<FacwebFactura> facturas = facturaRepository.findByNoCiaAndNoClienteAndStatusOrderByFechaRegistroDesc(noCia, noCliente, "F");
            List<FacturaCompletaDTO> facturasCompletas = convertirAFacturasCompletas(facturas);
            return ResponseEntity.ok(facturasCompletas);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Listar facturas recientes (últimos 30 días) - LIMITADO A 100 REGISTROS
     */
    @GetMapping("/recientes")
    public ResponseEntity<List<FacturaCompletaDTO>> listarFacturasRecientes(
            @RequestParam Integer noCia,
            @RequestParam(required = false) Integer noCliente) {
        
        try {
            LocalDateTime fechaDesde = LocalDateTime.now().minusDays(30);
            List<FacwebFactura> facturas;
            
            if (noCliente != null) {
                // Cliente específico - máximo 50 registros
                facturas = facturaRepository.findFacturasRecientesPorCliente(noCia, noCliente, fechaDesde);
            } else {
                // General - máximo 100 registros usando paginación
                Pageable pageable = PageRequest.of(0, 100);
                Page<FacwebFactura> paginaFacturas = facturaRepository.findFacturasRecientes(noCia, fechaDesde, pageable);
                facturas = paginaFacturas.getContent();
            }

            List<FacturaCompletaDTO> facturasCompletas = convertirAFacturasCompletas(facturas);
            return ResponseEntity.ok(facturasCompletas);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Crear respuesta sin paginación para casos específicos (cliente, etc.)
     */
    private FacturasPaginadasDTO crearRespuestaSinPaginacion(List<FacwebFactura> facturas) {
        List<FacturaCompletaDTO> facturasCompletas = convertirAFacturasCompletas(facturas);
        return new FacturasPaginadasDTO(
            facturasCompletas,
            0, // página actual
            1, // total páginas
            facturas.size(), // total elementos
            facturas.size(), // tamaño página
            true, // es última página
            true  // es primera página
        );
    }

    /**
     * Método privado para convertir entidades a DTOs con detalles
     */
    private List<FacturaCompletaDTO> convertirAFacturasCompletas(List<FacwebFactura> facturas) {
        if (facturas.isEmpty()) {
            return new ArrayList<>();
        }

        // Obtener números de facturas
        List<Integer> numerosFacturas = facturas.stream()
                .map(FacwebFactura::getNoFactura)
                .collect(Collectors.toList());

        // Obtener todos los detalles de una vez
        Integer noCia = facturas.get(0).getNoCia();
        List<FacwebFacturaDeta> todosLosDetalles = facturaDetaRepository.findDetallesPorFacturas(noCia, numerosFacturas);

        // Agrupar detalles por número de factura
        Map<Integer, List<FacwebFacturaDeta>> detallesPorFactura = todosLosDetalles.stream()
                .collect(Collectors.groupingBy(FacwebFacturaDeta::getNoFactura));

        // Convertir a DTOs
        return facturas.stream().map(factura -> {
            FacturaCompletaDTO dto = convertirAFacturaDTO(factura);
            
            List<FacwebFacturaDeta> detalles = detallesPorFactura.getOrDefault(factura.getNoFactura(), new ArrayList<>());
            List<FacturaDetalleDTO> detallesDTO = detalles.stream()
                    .map(this::convertirAFacturaDetalleDTO)
                    .collect(Collectors.toList());
            
            dto.setDetalles(detallesDTO);
            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * Convertir entidad FacwebFactura a DTO
     */
    private FacturaCompletaDTO convertirAFacturaDTO(FacwebFactura factura) {
        FacturaCompletaDTO dto = new FacturaCompletaDTO();
        dto.setNoCia(factura.getNoCia());
        dto.setNoFactura(factura.getNoFactura());
        dto.setNoSucursal(factura.getNoSucursal());
        dto.setNoBodega(factura.getNoBodega());
        dto.setNoPrefactura(factura.getNoPrefactura());
        dto.setFechaRegistro(factura.getFechaRegistro());
        dto.setNoVendedor(factura.getNoVendedor());
        dto.setNoCliente(factura.getNoCliente());
        dto.setNombreCliente(factura.getNombreCliente());
        dto.setRucCedula(factura.getRucCedula());
        dto.setTipoFactura(factura.getTipoFactura());
        dto.setSubtotalNominal(factura.getSubtotalNominal());
        dto.setImpuestoNominal(factura.getImpuestoNominal());
        dto.setTotalNominal(factura.getTotalNominal());
        dto.setDescuentoNominal(factura.getDescuentoNominal());
        dto.setStatus(factura.getStatus());
        dto.setUsuarioFacturo(factura.getUsuarioFacturo());
        dto.setFechaFacturo(factura.getFechaFacturo());
        dto.setObservacion(factura.getObservacion());
        dto.setNoOrdenCompra(factura.getNoOrdenCompra());
        dto.setNoSolicitud(factura.getNoSolicitud());
        dto.setConsecutivoFiscal(factura.getConsecutivoFiscal());
        dto.setEmail(factura.getEmail());
        dto.setClienteTelefono(factura.getClienteTelefono());
        dto.setClienteDireccion(factura.getClienteDireccion());
        dto.setGrupo(factura.getGrupo());
        return dto;
    }

    /**
     * Convertir entidad FacwebFacturaDeta a DTO
     */
    private FacturaDetalleDTO convertirAFacturaDetalleDTO(FacwebFacturaDeta detalle) {
        FacturaDetalleDTO dto = new FacturaDetalleDTO();
        dto.setNoCia(detalle.getNoCia());
        dto.setNoFactura(detalle.getNoFactura());
        dto.setNoLinea(detalle.getNoLinea());
        dto.setNoArticulo(detalle.getNoArticulo());
        dto.setCantidad(detalle.getCantidad());
        dto.setDescripcion(detalle.getDescripcion());
        dto.setPrecioNominal(detalle.getPrecioNominal());
        dto.setSubtotalNominal(detalle.getSubtotalNominal());
        dto.setDescuentoNominal(detalle.getDescuentoNominal());
        dto.setImpuestoNominal(detalle.getImpuestoNominal());
        dto.setTotalNominal(detalle.getTotalNominal());
        dto.setPorDescuento(detalle.getPorDescuento());
        dto.setPorImpuesto(detalle.getPorImpuesto());
        dto.setCodBarra(detalle.getCodBarra());
        return dto;
    }
}
