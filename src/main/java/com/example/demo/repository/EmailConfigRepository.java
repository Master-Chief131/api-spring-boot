package com.example.demo.repository;

import com.example.demo.entity.EmailConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailConfigRepository extends JpaRepository<EmailConfig, Integer> {
    
    @Query("SELECT e FROM EmailConfig e WHERE e.noCia = :noCia")
    Optional<EmailConfig> findByNoCia(@Param("noCia") Integer noCia);
}
