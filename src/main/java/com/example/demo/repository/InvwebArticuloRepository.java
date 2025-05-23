package com.example.demo.repository;

import com.example.demo.entity.InvwebArticulo;
import com.example.demo.entity.InvwebArticuloId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvwebArticuloRepository extends JpaRepository<InvwebArticulo, InvwebArticuloId> {
}
