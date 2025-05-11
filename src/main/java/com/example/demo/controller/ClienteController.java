package com.example.demo.controller;

import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

  private final ClienteRepository repository;

  public ClienteController(ClienteRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public List<Cliente> getAll() {
    return repository.findAll();
  }

  @GetMapping("/{id}")
  public Cliente getById(@PathVariable Long id) {
    return repository.findById(id).orElse(null);
  }

  @PostMapping
  public Cliente create(@RequestBody Cliente cliente) {
    return repository.save(cliente);
  }

  @PutMapping("/{id}")
  public Cliente update(@PathVariable Long id, @RequestBody Cliente cliente) {
    cliente.setId(id);
    return repository.save(cliente);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    repository.deleteById(id);
  }
}