package com.example.demo.repository;

import com.example.demo.entity.InvwebBodega;
import com.example.demo.entity.BodegaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvwebBodegaRepository extends JpaRepository<InvwebBodega, BodegaId> {
    List<InvwebBodega> findByVerPortal(String verPortal);
}
