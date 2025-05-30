package com.example.demo.repository;

import com.example.demo.entity.InvwebSucursal;
import com.example.demo.entity.SucursalId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvwebSucursalRepository extends JpaRepository<InvwebSucursal, SucursalId> {
    List<InvwebSucursal> findByVerPortal(String verPortal);
    List<InvwebSucursal> findByVerPortalAndNoCia(String verPortal, Integer noCia);
}
