package com.example.demo.controller;

import com.example.demo.entity.InvwebArticulo;
import com.example.demo.entity.InvwebArticuloDetalle;
import com.example.demo.entity.SegwebModulo;
import com.example.demo.repository.InvwebArticuloRepository;
import com.example.demo.repository.InvwebArticuloDetalleRepository;
import com.example.demo.repository.InvwebFamiliaRepository;
import com.example.demo.repository.SegwebModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

// Import para Jackson JSON
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/articulos")
@Tag(name = "Artículos", description = "API para consulta de artículos del inventario")
public class InvwebArticuloController {
    @Autowired
    private InvwebArticuloRepository articuloRepository;

    @Autowired
    private InvwebFamiliaRepository familiaRepository;

    @Autowired
    private InvwebArticuloDetalleRepository articuloDetalleRepository;

    @Autowired
    private SegwebModuloRepository moduloRepository;

    @Value("${app.base-url}")
    private String baseUrl;

    public static class ImagenDetalleDTO {
        public String nombre;
        public String url;
        public String tipoArchivo;
        public String noArticulo;
    }

    public static class ArticuloDTO {
        public String codigo;

        public String nombre;

        @JsonProperty("descripcion_larga")
        public String descripcionLarga;

        @JsonProperty("descripcion_corta")
        public String descripcionCorta;

        @JsonProperty("imagen_url")
        public String imagenUrl;

        @JsonProperty("imagenes_detalle")
        public java.util.List<ImagenDetalleDTO> imagenesDetalle;

        public java.math.BigDecimal precioVenta;
        public Integer unidadVenta;
        public Integer tamano;
        public Integer familia;
        public Integer subfamilia;
        public Integer subsubfamilia;
    }

    @GetMapping
    @Operation(summary = "Obtener artículos del inventario", description = "Obtiene una lista paginada de artículos del inventario. Solo incluye artículos de familias "
            +
            "habilitadas para el portal (ver_portal = 'S').")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de artículos obtenida exitosamente", content = @Content(schema = @Schema(implementation = ArticuloPage.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ArticuloPage getArticulos(
            @Parameter(description = "Número de página (base 0)", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamaño de página", example = "20") @RequestParam(defaultValue = "20") int size) {

        // Obtener la ruta de visualización del módulo de inventario
        SegwebModulo moduloInventario = moduloRepository.findModuloInventario().orElse(null);
        String rutaVisualizacion = (moduloInventario != null && moduloInventario.getRutaVisualizacion() != null)
                ? moduloInventario.getRutaVisualizacion()
                : "/inventario/";

        // Obtener todas las familias con ver_portal = 'S' de una sola vez
        List<com.example.demo.entity.InvwebFamilia> familiasPortal = familiaRepository.findByVerPortal("S");
        java.util.Set<com.example.demo.entity.FamiliaId> familiasValidas = familiasPortal.stream()
                .map(f -> new com.example.demo.entity.FamiliaId(f.getNoCia(), f.getNoFamilia()))
                .collect(java.util.stream.Collectors.toSet());
        List<InvwebArticulo> articulos = articuloRepository.findAll();
        List<ArticuloDTO> articulosFiltrados = articulos.stream()
                .filter(a -> a.getNoFamilia() != null && familiasValidas
                        .contains(new com.example.demo.entity.FamiliaId(a.getNoCia(), a.getNoFamilia())))
                .map(a -> {
                    ArticuloDTO dto = new ArticuloDTO();
                    dto.codigo = a.getNoArticulo();
                    dto.nombre = a.getNombreLargo();
                    dto.descripcionLarga = a.getNombreCorto();
                    dto.descripcionCorta = a.getReferencias();

                    // Construir URL completa de la imagen principal
                    if (a.getRutaFoto() != null && !a.getRutaFoto().trim().isEmpty()) {
                        dto.imagenUrl = baseUrl + "/Articulos/" + a.getRutaFoto();
                    } else {
                        dto.imagenUrl = null;
                    }

                    // Obtener imágenes de detalle del artículo
                    List<InvwebArticuloDetalle> detalles = articuloDetalleRepository
                            .findByNoCiaAndNoArticuloAndVerPortal(a.getNoCia(), a.getNoArticulo(), "S");

                    dto.imagenesDetalle = detalles.stream()
                            .filter(d -> d.getArchivo() != null && !d.getArchivo().trim().isEmpty())
                            .map(d -> {
                                ImagenDetalleDTO imgDto = new ImagenDetalleDTO();
                                imgDto.nombre = d.getNombre();
                                imgDto.tipoArchivo = d.getTipoArchivo();
                                imgDto.noArticulo = d.getNoArticulo();

                                // Normalizar el archivo: reemplazar backslashes por forward slashes
                                String archivoNormalizado = d.getArchivo().replace("\\", "/");

                                // Extraer solo servidor:puerto del baseUrl (sin el contexto de la aplicación)
                                // Por ejemplo: http://localhost:8088/cptsoft-erp-prueba ->
                                // http://localhost:8088
                                String serverUrl = baseUrl;
                                int contextIndex = serverUrl.indexOf("/", serverUrl.indexOf("://") + 3);
                                if (contextIndex != -1) {
                                    serverUrl = serverUrl.substring(0, contextIndex);
                                }

                                // Construir URL completa: serverUrl + rutaVisualizacion + archivo
                                String url = serverUrl;
                                if (!url.endsWith("/") && !rutaVisualizacion.startsWith("/")) {
                                    url += "/";
                                }
                                url += rutaVisualizacion;
                                if (!url.endsWith("/") && !archivoNormalizado.startsWith("/")) {
                                    url += "/";
                                }
                                url += archivoNormalizado;

                                imgDto.url = url;

                                return imgDto;
                            })
                            .collect(java.util.stream.Collectors.toList());

                    dto.precioVenta = a.getPrecioVenta();
                    dto.unidadVenta = a.getNoUnidadVenta();
                    dto.tamano = a.getNoTamano();
                    dto.familia = a.getNoFamilia();
                    dto.subfamilia = a.getNoSubFamilia();
                    dto.subsubfamilia = a.getNoSubSubfamilia();
                    return dto;
                }).collect(java.util.stream.Collectors.toList());
        int total = articulosFiltrados.size();
        int fromIndex = Math.min(page * size, total);
        int toIndex = Math.min(fromIndex + size, total);
        List<ArticuloDTO> content = articulosFiltrados.subList(fromIndex, toIndex);
        return new ArticuloPage(content, page, size, total);
    }

    public static class ArticuloPage {
        public List<ArticuloDTO> content;
        public int page;
        public int size;
        public int totalElements;
        public int totalPages;

        public ArticuloPage(List<ArticuloDTO> content, int page, int size, int totalElements) {
            this.content = content;
            this.page = page;
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = (int) Math.ceil((double) totalElements / size);
        }
    }
}
