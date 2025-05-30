package com.example.demo.controller;

import com.example.demo.entity.FacwebPromocion;
import com.example.demo.repository.FacwebPromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/ofertas")
public class FacwebPromocionController {
    @Autowired
    private FacwebPromocionRepository promocionRepository;

    @GetMapping
    public List<FacwebPromocion> getOfertasActivas(@RequestParam(required = false) Integer noCia) {
        if (noCia != null) {
            return promocionRepository.findByIndActivaAndNoCia("S", noCia);
        } else {
            return promocionRepository.findByIndActiva("S");
        }
    }
}
