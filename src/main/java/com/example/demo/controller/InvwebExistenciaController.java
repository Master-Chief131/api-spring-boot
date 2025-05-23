package com.example.demo.controller;

import com.example.demo.entity.InvwebExistencia;
import com.example.demo.repository.InvwebBodegaRepository;
import com.example.demo.repository.InvwebExistenciaRepository;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/existencias")
public class InvwebExistenciaController {
    @Autowired
    private InvwebExistenciaRepository existenciaRepository;

    @Autowired
    private InvwebBodegaRepository bodegaRepository;

    @GetMapping
    public Page<InvwebExistencia> getExistencias(Pageable pageable) {
        // Obtener las bodegas con verPortal = 'S'
        Set<String> bodegasClave = bodegaRepository.findByVerPortal("S").stream()
            .filter(b -> b.getNoCia() != null && b.getNoCia() == 1)
            .map(b -> b.getNoCia() + ":" + b.getNoSucursal() + ":" + b.getNoBodega())
            .collect(Collectors.toSet());
        // Filtrar existencias por bodegas válidas
        Page<InvwebExistencia> existencias = existenciaRepository.findAll(pageable);
        var filtradas = existencias.getContent().stream()
            .filter(e -> bodegasClave.contains(e.getNoCia() + ":" + e.getNoSucursal() + ":" + e.getNoBodega()))
            .collect(Collectors.toList());
        return new PageImpl<>(filtradas, pageable, existencias.getTotalElements());
    }
}
