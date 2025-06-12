package com.example.demo.controller;

import com.example.demo.entity.GrupoCliente;
import com.example.demo.service.GrupoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/grupos-clientes")
public class GrupoClienteController {
    @Autowired
    private GrupoClienteService grupoClienteService;

    @GetMapping("/portal")
    public List<GrupoCliente> getGruposConIndPortalS() {
        return grupoClienteService.getGruposConIndPortalS();
    }
}
