package com.example.demo.repository;

import com.example.demo.entity.InvwebSubfamiliaImg;
import com.example.demo.entity.SubfamiliaImgId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvwebSubfamiliaImgRepository extends JpaRepository<InvwebSubfamiliaImg, SubfamiliaImgId> {
    Optional<InvwebSubfamiliaImg> findByNoCiaAndNoFamiliaAndNoSubfamilia(Integer noCia, Integer noFamilia, Integer noSubfamilia);
}
