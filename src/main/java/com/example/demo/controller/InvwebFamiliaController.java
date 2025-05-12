package com.example.demo.controller;

import com.example.demo.entity.InvwebFamilia;
import com.example.demo.repository.InvwebFamiliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/familias-producto")
public class InvwebFamiliaController {
    @Autowired
    private InvwebFamiliaRepository invwebFamiliaRepository;

    @GetMapping
    @Transactional(readOnly = true)
    public List<InvwebFamilia> getAllFamilias() {
        List<InvwebFamilia> familias = invwebFamiliaRepository.findByVerPortal("S");
        familias.forEach(f -> {
            f.getSubfamilias().forEach(sf -> sf.getSubSubfamilias().size());
        });
        return familias;
    }
}
