package com.example.demo.controller;

import com.example.demo.entity.InvwebArticulo;
import com.example.demo.repository.InvwebArticuloRepository;
import com.example.demo.repository.InvwebFamiliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/articulos")
public class InvwebArticuloController {
    @Autowired
    private InvwebArticuloRepository articuloRepository;

    @Autowired
    private InvwebFamiliaRepository familiaRepository;

    public static class ArticuloDTO {
        public String codigo;
        public String nombre;
        public java.math.BigDecimal precioVenta;
        public Integer unidadVenta;
        public Integer tamano;
        public Integer familia;
        public Integer subfamilia;
        public Integer subsubfamilia;
    }

    @GetMapping
    public ArticuloPage getArticulos(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        // Obtener todas las familias con ver_portal = 'S' de una sola vez
        List<com.example.demo.entity.InvwebFamilia> familiasPortal = familiaRepository.findByVerPortal("S");
        java.util.Set<com.example.demo.entity.FamiliaId> familiasValidas = familiasPortal.stream()
            .map(f -> new com.example.demo.entity.FamiliaId(f.getNoCia(), f.getNoFamilia()))
            .collect(java.util.stream.Collectors.toSet());
        List<InvwebArticulo> articulos = articuloRepository.findAll();
        List<ArticuloDTO> articulosFiltrados = articulos.stream()
            .filter(a -> a.getNoFamilia() != null && familiasValidas.contains(new com.example.demo.entity.FamiliaId(a.getNoCia(), a.getNoFamilia())))
            .map(a -> {
                ArticuloDTO dto = new ArticuloDTO();
                dto.codigo = a.getNoArticulo();
                dto.nombre = a.getNombreLargo();
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
