package com.example.demo.repository;

import com.example.demo.entity.InvwebSubfamilia;
import com.example.demo.entity.SubfamiliaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvwebSubfamiliaRepository extends JpaRepository<InvwebSubfamilia, SubfamiliaId> {
    List<InvwebSubfamilia> findByNoCiaAndNoFamilia(Integer noCia, Integer noFamilia);
}
