package com.example.demo.controller;

import com.example.demo.entity.InvwebExistencia;
import com.example.demo.repository.InvwebBodegaRepository;
import com.example.demo.repository.InvwebExistenciaRepository;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/existencias")
public class InvwebExistenciaController {
    @Autowired
    private InvwebExistenciaRepository existenciaRepository;

    @Autowired
    private InvwebBodegaRepository bodegaRepository;

    @GetMapping
    public ExistenciaPage getExistencias(Pageable pageable,
                                         @RequestParam(required = false) Integer noCia) {
        Set<String> bodegasClave;
        if (noCia != null) {
            bodegasClave = bodegaRepository.findByVerPortalAndNoCia("S", noCia).stream()
                .map(b -> b.getNoCia() + ":" + b.getNoSucursal() + ":" + b.getNoBodega())
                .collect(Collectors.toSet());
        } else {
            bodegasClave = bodegaRepository.findByVerPortal("S").stream()
                .map(b -> b.getNoCia() + ":" + b.getNoSucursal() + ":" + b.getNoBodega())
                .collect(Collectors.toSet());
        }
        Page<InvwebExistencia> existencias = existenciaRepository.findAll(pageable);
        var filtradas = existencias.getContent().stream()
            .filter(e -> bodegasClave.contains(e.getNoCia() + ":" + e.getNoSucursal() + ":" + e.getNoBodega()))
            .collect(Collectors.toList());
        return new ExistenciaPage(filtradas, pageable.getPageNumber(), pageable.getPageSize(), existencias.getTotalElements());
    }

    public static class ExistenciaPage {
        public java.util.List<InvwebExistencia> content;
        public int page;
        public int size;
        public long totalElements;
        public int totalPages;
        public ExistenciaPage(java.util.List<InvwebExistencia> content, int page, int size, long totalElements) {
            this.content = content;
            this.page = page;
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = (int) Math.ceil((double) totalElements / size);
        }
    }
}
