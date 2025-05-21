package com.example.demo.repository;

import com.example.demo.entity.InvwebArticuloPrecio;
import com.example.demo.entity.ArticuloPrecioId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvwebArticuloPrecioRepository extends JpaRepository<InvwebArticuloPrecio, ArticuloPrecioId> {
    List<InvwebArticuloPrecio> findByVerPortal(String verPortal);
}
