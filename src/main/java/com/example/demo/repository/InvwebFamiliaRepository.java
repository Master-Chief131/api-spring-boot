package com.example.demo.repository;

import com.example.demo.entity.InvwebFamilia;
import com.example.demo.entity.FamiliaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InvwebFamiliaRepository extends JpaRepository<InvwebFamilia, FamiliaId> {
    @Query("SELECT f FROM InvwebFamilia f WHERE f.verPortal = :verPortal")
    java.util.List<InvwebFamilia> findByVerPortal(@Param("verPortal") String verPortal);
}
