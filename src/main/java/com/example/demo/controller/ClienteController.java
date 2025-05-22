package com.example.demo.controller;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.ClienteId;
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

  @GetMapping("/{noCia}/{noCliente}/{grupo}")
  public Cliente getById(@PathVariable int noCia, @PathVariable int noCliente, @PathVariable String grupo) {
    ClienteId id = new ClienteId();
    id.setNoCia(noCia);
    id.setNoCliente(noCliente);
    id.setGrupo(grupo);
    return repository.findById(id).orElse(null);
  }

  @PostMapping
  public Cliente create(@RequestBody Cliente cliente) {
    return repository.save(cliente);
  }

  @PutMapping("/{noCia}/{noCliente}/{grupo}")
  public Cliente update(@PathVariable int noCia, @PathVariable int noCliente, @PathVariable String grupo, @RequestBody Cliente cliente) {
    ClienteId id = new ClienteId();
    id.setNoCia(noCia);
    id.setNoCliente(noCliente);
    id.setGrupo(grupo);
    cliente.setId(id);
    return repository.save(cliente);
  }

  @DeleteMapping("/{noCia}/{noCliente}/{grupo}")
  public void delete(@PathVariable int noCia, @PathVariable int noCliente, @PathVariable String grupo) {
    ClienteId id = new ClienteId();
    id.setNoCia(noCia);
    id.setNoCliente(noCliente);
    id.setGrupo(grupo);
    repository.deleteById(id);
  }

  @GetMapping("/{noCliente}")
  public List<Cliente> getByNoCliente(@PathVariable int noCliente) {
    return repository.findAll().stream()
      .filter(c -> c.getId() != null && c.getId().getNoCliente() == noCliente)
      .toList();
  }

  @PostMapping("/registro-portal")
  public Cliente registrarDesdePortal(@RequestBody Cliente cliente) {
    // Ya no forzamos listaPrecio ni plazo porque no existen en la entidad ni en la tabla
    return repository.save(cliente);
  }
}