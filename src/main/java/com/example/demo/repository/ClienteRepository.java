package com.example.demo.repository;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.ClienteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, ClienteId>, JpaSpecificationExecutor<Cliente> {
    
    /**
     * Encuentra clientes por RUC/Cédula y número de compañía
     */
    List<Cliente> findByRucCedulaAndId_NoCia(String rucCedula, Integer noCia);
}