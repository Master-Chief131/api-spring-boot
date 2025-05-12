package com.example.demo.controller;

import com.example.demo.entity.InvwebBodega;
import com.example.demo.repository.InvwebBodegaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
