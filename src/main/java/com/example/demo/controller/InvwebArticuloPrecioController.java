package com.example.demo.controller;

import com.example.demo.entity.InvwebArticuloPrecio;
import com.example.demo.repository.InvwebArticuloPrecioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lista-precios")
public class InvwebArticuloPrecioController {
    @Autowired
    private InvwebArticuloPrecioRepository articuloPrecioRepository;

    @GetMapping
    public List<InvwebArticuloPrecio> getListaPrecios() {
        return articuloPrecioRepository.findByVerPortal("S");
    }
}
