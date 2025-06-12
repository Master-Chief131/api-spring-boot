package com.example.demo.service;

import com.example.demo.entity.GrupoCliente;
import com.example.demo.repository.GrupoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GrupoClienteServiceImpl implements GrupoClienteService {
    @Autowired
    private GrupoClienteRepository grupoClienteRepository;

    @Override
    public List<GrupoCliente> getGruposConIndPortalS() {
        return grupoClienteRepository.findByIndPortalS();
    }
}
