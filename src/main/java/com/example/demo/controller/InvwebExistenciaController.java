package com.example.demo.controller;

import com.example.demo.entity.InvwebExistencia;
import com.example.demo.repository.InvwebExistenciaRepository;
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

    @GetMapping
    public Page<InvwebExistencia> getExistencias(Pageable pageable,
                                                 @RequestParam(required = false) Integer noCia) {
        if (noCia != null) {
            // Consulta optimizada para una compañía específica con stock > 0
            return existenciaRepository.findExistenciasConStockEnBodegasPortalPorCia(noCia, pageable);
        } else {
            // Consulta optimizada para todas las compañías con stock > 0
            return existenciaRepository.findExistenciasConStockEnBodegasPortal(pageable);
        }
    }
}
