package com.example.demo.controller;

import com.example.demo.entity.InvwebSucursal;
import com.example.demo.repository.InvwebSucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class InvwebSucursalController {
    @Autowired
    private InvwebSucursalRepository sucursalRepository;

    @GetMapping
    public List<InvwebSucursal> getSucursales() {
        return sucursalRepository.findByVerPortal("S");
    }

    @GetMapping("/{no_sucursal}")
    public InvwebSucursal getSucursalById(@PathVariable Integer no_sucursal) {
        // Suponiendo que solo hay una compañía, o puedes agregar también no_cia si es necesario
        return sucursalRepository.findAll().stream()
            .filter(s -> s.getNoSucursal().equals(no_sucursal))
            .findFirst()
            .orElse(null);
    }
}
