package com.example.demo.controller;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.ClienteId;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

  private final ClienteRepository repository;
  private final JdbcTemplate jdbcTemplate;
  private final DataSource dataSource;

  @Autowired
  public ClienteController(ClienteRepository repository, JdbcTemplate jdbcTemplate, DataSource dataSource) {
    this.repository = repository;
    this.jdbcTemplate = jdbcTemplate;
    this.dataSource = dataSource;
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
  public Map<String, Object> registrarDesdePortal(@RequestBody Cliente cliente) {
    // Llama al procedimiento almacenado para insertar el cliente
    SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("CLIENTE"); // Cambia por el nombre real

    Map<String, Object> params = new HashMap<>();
    params.put("NO_CIA", cliente.getId().getNoCia());
    params.put("no_cliente", cliente.getId().getNoCliente());
    params.put("fecha_creacion", java.sql.Date.valueOf(java.time.LocalDate.now()));
    params.put("f_cierre", null); // Ajusta según tu lógica
    params.put("tipo_cliente", null); // Ajusta según tu lógica
    params.put("grupo", cliente.getId().getGrupo());
    params.put("persona_nj", cliente.getPersonaNj());
    params.put("eti", null); // Ajusta según tu lógica
    params.put("nombre", cliente.getNombre());
    params.put("nombre_cont", cliente.getNombreCont());
    params.put("apellido_cont", cliente.getApellidoCont());
    params.put("no_actividad", cliente.getNoActividad());
    params.put("no_tipo_empresa", cliente.getNoTipoEmpresa());
    params.put("no_empleado", null); // Ajusta según tu lógica
    params.put("ruc_cedula", cliente.getRucCedula());
    params.put("direccion", cliente.getDireccion());
    params.put("telefono", cliente.getTelefono());
    params.put("extension", null); // Ajusta según tu lógica
    params.put("fax", null); // Ajusta según tu lógica
    params.put("telefono2", cliente.getTelefono2());
    params.put("extension2", null); // Ajusta según tu lógica
    params.put("pagina_web", null); // Ajusta según tu lógica
    params.put("pagina_web2", null); // Ajusta según tu lógica
    params.put("email1", cliente.getEmail1());
    params.put("facebook", null); // Ajusta según tu lógica
    params.put("instagram", null); // Ajusta según tu lógica
    params.put("twitter", null); // Ajusta según tu lógica
    params.put("apartado", null); // Ajusta según tu lógica
    params.put("no_pais", cliente.getNoPais());
    params.put("no_origen", cliente.getNoOrigen());
    params.put("no_provincia", cliente.getNoProvincia());
    params.put("limite_credi", null); // Ajusta según tu lógica
    params.put("no_distrito", cliente.getNoDistrito());
    params.put("saldo", null); // Ajusta según tu lógica
    params.put("no_corregimiento", cliente.getNoCorregimiento());
    params.put("disponible", null); // Ajusta según tu lógica
    params.put("referido", null); // Ajusta según tu lógica
    params.put("instruccion_especial", null); // Ajusta según tu lógica
    params.put("excento_imp", null); // Ajusta según tu lógica
    params.put("distribuidor", null); // Ajusta según tu lógica
    params.put("dv", cliente.getDv());
    params.put("movil", cliente.getMovil());
    params.put("fecha_nacimiento", null); // Ajusta según tu lógica
    params.put("no_grupo_mercado", null); // Ajusta según tu lógica
    params.put("acceso_web", null); // Ajusta según tu lógica
    params.put("usuario_web", null); // Ajusta según tu lógica
    params.put("no_plazo", cliente.getNoPlazo());
    params.put("ind_cliente_contado", null); // Ajusta según tu lógica
    params.put("USUARIO_CREACION", "PORTAL"); // O el usuario real
    params.put("ACTIVO", "S");
    params.put("MODULO_ORIGEN", null); // Ajusta según tu lógica
    params.put("LATITUD", null); // Ajusta según tu lógica
    params.put("LONGITUD", null); // Ajusta según tu lógica
    params.put("TOLERANCIA", null); // Ajusta según tu lógica
    params.put("RUTA", null); // Ajusta según tu lógica
    params.put("COD_RUTA", null); // Ajusta según tu lógica
    params.put("IND_SOPORTE", null); // Ajusta según tu lógica
    params.put("CLIENTE_HELP_DESK", null); // Ajusta según tu lógica
    params.put("ESTADO_PROMO", null); // Ajusta según tu lógica
    params.put("ind_sucursal", null); // Ajusta según tu lógica
    params.put("TIPO_P", null); // Ajusta según tu lógica
    params.put("ind_sexo", null); // Ajusta según tu lógica
    params.put("validar_morosidad_contado", null); // Ajusta según tu lógica
    params.put("compania_inter_cia", null); // Ajusta según tu lógica

    // Ejecuta el procedimiento
    Map<String, Object> result = jdbcCall.execute(params);
    return result;
  }
}