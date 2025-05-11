package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;
  private String telefono;

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }

  public String getTelefono() { return telefono; }
  public void setTelefono(String telefono) { this.telefono = telefono; }
}