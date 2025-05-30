package com.example.demo.repository;

import com.example.demo.entity.InvwebFamiliaImg;
import com.example.demo.entity.FamiliaImgId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvwebFamiliaImgRepository extends JpaRepository<InvwebFamiliaImg, FamiliaImgId> {
    Optional<InvwebFamiliaImg> findByNoCiaAndNoFamilia(Integer noCia, Integer noFamilia);
}
