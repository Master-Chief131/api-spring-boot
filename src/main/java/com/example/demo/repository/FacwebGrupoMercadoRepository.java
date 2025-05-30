package com.example.demo.repository;

import com.example.demo.entity.FacwebGrupoMercado;
import com.example.demo.entity.FacwebGrupoMercadoId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FacwebGrupoMercadoRepository extends JpaRepository<FacwebGrupoMercado, FacwebGrupoMercadoId> {
    List<FacwebGrupoMercado> findByIndPortal(String indPortal);
    List<FacwebGrupoMercado> findByIndPortalAndNoCia(String indPortal, Integer noCia);
}
