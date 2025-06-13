package com.example.demo.repository;

import com.example.demo.entity.GrupoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface GrupoClienteRepository extends JpaRepository<GrupoCliente, com.example.demo.entity.GrupoClienteId> {
    @Query("SELECT g FROM GrupoCliente g WHERE g.indPortal = 'S'")
    List<GrupoCliente> findByIndPortalS();
}
