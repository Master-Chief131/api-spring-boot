package com.example.demo.controller;

import com.example.demo.entity.InvwebFamilia;
import com.example.demo.entity.InvwebFamiliaImg;
import com.example.demo.entity.InvwebSubfamiliaImg;
import com.example.demo.repository.InvwebFamiliaImgRepository;
import com.example.demo.repository.InvwebFamiliaRepository;
import com.example.demo.repository.InvwebSubfamiliaImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/familias-producto")
public class InvwebFamiliaController {
    @Autowired
    private InvwebFamiliaRepository invwebFamiliaRepository;
    @Autowired
    private InvwebFamiliaImgRepository invwebFamiliaImgRepository;
    @Autowired
    private InvwebSubfamiliaImgRepository invwebSubfamiliaImgRepository;

    @Value("${familia.img.base-url:http://localhost:8088/cptsoft-erp-prueba/Imagenes/}")
    private String familiaImgBaseUrl;

    @GetMapping
    @Transactional(readOnly = true)
    public List<InvwebFamilia> getAllFamilias() {
        List<InvwebFamilia> familias = invwebFamiliaRepository.findByVerPortal("S");
        // Agregar imagenUrl a familia y subfamilia usando métodos transient
        familias.forEach(f -> {
            invwebFamiliaImgRepository.findByNoCiaAndNoFamilia(f.getNoCia(), f.getNoFamilia())
                .ifPresent(img -> f.setImagenUrl(familiaImgBaseUrl + img.getArchivo()));
            if (f.getSubfamilias() != null) {
                f.getSubfamilias().forEach(sf -> {
                    invwebSubfamiliaImgRepository.findByNoCiaAndNoFamiliaAndNoSubfamilia(f.getNoCia(), f.getNoFamilia(), sf.getNoSubfamilia())
                        .ifPresent(img -> sf.setImagenUrl(familiaImgBaseUrl + img.getArchivo()));
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
