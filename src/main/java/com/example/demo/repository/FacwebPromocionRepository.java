package com.example.demo.repository;

import com.example.demo.entity.FacwebPromocion;
import com.example.demo.entity.FacwebPromocionId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FacwebPromocionRepository extends JpaRepository<FacwebPromocion, FacwebPromocionId> {
    List<FacwebPromocion> findByIndActiva(String indActiva);
}
