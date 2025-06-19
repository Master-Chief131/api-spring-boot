package com.example.demo.controller;

import com.example.demo.entity.InvwebFamilia;
import com.example.demo.entity.InvwebFamiliaImg;
import com.example.demo.entity.InvwebSubfamiliaImg;
import com.example.demo.repository.InvwebFamiliaImgRepository;
import com.example.demo.repository.InvwebFamiliaRepository;
import com.example.demo.repository.InvwebSubfamiliaImgRepository;
import com.example.demo.service.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/familias-producto")
@Tag(name = "Familias de Productos", description = "API para consulta de familias de productos y sus imágenes")
public class InvwebFamiliaController {    @Autowired
    private InvwebFamiliaRepository invwebFamiliaRepository;
    @Autowired
    private InvwebFamiliaImgRepository invwebFamiliaImgRepository;
    @Autowired
    private InvwebSubfamiliaImgRepository invwebSubfamiliaImgRepository;
    @Autowired
    private ImagenService imagenService;    @GetMapping
    @Transactional(readOnly = true)
    public List<InvwebFamilia> getAllFamilias() {
        List<InvwebFamilia> familias = invwebFamiliaRepository.findByVerPortal("S");
        // Agregar imagenUrl a familia y subfamilia usando el nuevo servicio de imágenes
        familias.forEach(f -> {
            invwebFamiliaImgRepository.findByNoCiaAndNoFamilia(f.getNoCia(), f.getNoFamilia())
                .ifPresent(img -> {
                    String urlImagen = imagenService.obtenerUrlImagen(img.getArchivo());
                    f.setImagenUrl(urlImagen);
                });
            if (f.getSubfamilias() != null) {
                f.getSubfamilias().forEach(sf -> {
                    invwebSubfamiliaImgRepository.findByNoCiaAndNoFamiliaAndNoSubfamilia(f.getNoCia(), f.getNoFamilia(), sf.getNoSubfamilia())
                        .ifPresent(img -> {
                            String urlImagen = imagenService.obtenerUrlImagen(img.getArchivo());
                            sf.setImagenUrl(urlImagen);
                        });
                });
            }
        });
        return familias;
    }

    @GetMapping("/{no_familia}")
    @Transactional(readOnly = true)
    public InvwebFamilia getFamiliaById(@PathVariable Integer no_familia) {
        // Suponiendo que solo hay una compañía, o puedes agregar también no_cia si es necesario
        return invwebFamiliaRepository.findAll().stream()
            .filter(f -> f.getNoFamilia().equals(no_familia))
            .findFirst()
            .orElse(null);
    }
}
