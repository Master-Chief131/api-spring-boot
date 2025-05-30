package com.example.demo.controller;

import com.example.demo.entity.FacwebGrupoMercado;
import com.example.demo.repository.FacwebGrupoMercadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class FacwebGrupoMercadoController {
    @Autowired
    private FacwebGrupoMercadoRepository grupoMercadoRepository;

    @GetMapping
    public List<FacwebGrupoMercado> getServicios(@RequestParam(required = false) Integer noCia) {
        if (noCia != null) {
            return grupoMercadoRepository.findByIndPortalAndNoCia("S", noCia);
        } else {
            return grupoMercadoRepository.findByIndPortal("S");
        }
    }
}
