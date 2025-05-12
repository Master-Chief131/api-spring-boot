package com.example.demo.repository;

import com.example.demo.entity.InvwebSubSubfamilia;
import com.example.demo.entity.SubSubfamiliaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvwebSubSubfamiliaRepository extends JpaRepository<InvwebSubSubfamilia, SubSubfamiliaId> {
    List<InvwebSubSubfamilia> findByNoCiaAndNoFamiliaAndNoSubfamilia(Integer noCia, Integer noFamilia, Integer noSubfamilia);
}
