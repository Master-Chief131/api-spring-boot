package com.example.demo.controller;

import com.example.demo.entity.InvwebBodega;
import com.example.demo.repository.InvwebBodegaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bodegas")
public class InvwebBodegaController {
    @Autowired
    private InvwebBodegaRepository bodegaRepository;

    @GetMapping
    public List<InvwebBodega> getBodegas() {
        return bodegaRepository.findByVerPortal("S");
    }

    @GetMapping("/{no_sucursal}/{no_bodega}")
    public InvwebBodega getBodegaBySucursalAndBodega(@PathVariable Integer no_sucursal, @PathVariable Integer no_bodega) {
        return bodegaRepository.findAll().stream()
            .filter(b -> b.getNoSucursal().equals(no_sucursal) && b.getNoBodega().equals(no_bodega))
            .findFirst()
            .orElse(null);
    }
}
