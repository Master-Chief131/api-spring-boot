package com.example.demo.repository;

import com.example.demo.entity.InvwebArticuloDetalle;
import com.example.demo.entity.InvwebArticuloDetalleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvwebArticuloDetalleRepository extends JpaRepository<InvwebArticuloDetalle, InvwebArticuloDetalleId> {

    // Buscar todos los detalles de un artículo específico
    List<InvwebArticuloDetalle> findByNoCiaAndNoArticulo(Integer noCia, String noArticulo);

    // Buscar detalles visibles en el portal para un artículo
    List<InvwebArticuloDetalle> findByNoCiaAndNoArticuloAndVerPortal(Integer noCia, String noArticulo,
            String verPortal);
}
