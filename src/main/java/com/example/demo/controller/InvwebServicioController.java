package com.example.demo.controller;

import com.example.demo.entity.InvwebServicio;
import com.example.demo.repository.InvwebServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
//@RequestMapping("/api/servicios")
//public class InvwebServicioController {
//     @Autowired
//     private InvwebServicioRepository servicioRepository;
//
//     @GetMapping
//     public List<InvwebServicio> getServicios() {
//         return servicioRepository.findActivosConFamiliaVerPortal("S");
//     }
// }
