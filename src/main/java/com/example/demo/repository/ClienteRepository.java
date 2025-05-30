package com.example.demo.repository;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.ClienteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClienteRepository extends JpaRepository<Cliente, ClienteId>, JpaSpecificationExecutor<Cliente> {
}