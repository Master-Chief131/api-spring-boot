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
import java.util.Optional;

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
    SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
            // .withCatalogName("cptsoft-prochem")
            .withProcedureName("CLIENTE");

    Map<String, Object> params = new HashMap<>();
    params.put("CIA", cliente.getId().getNoCia());
    params.put("PARAMETRO1", null); // Puedes ajustar según lógica
    params.put("PARAMETRO2", null);
    params.put("PARAMETRO3", null);
    params.put("PARAMETRO4", cliente.getId().getGrupo());
    params.put("PARAMETRO5", cliente.getPersonaNj() != null ? cliente.getPersonaNj() : "N");
    params.put("PARAMETRO6", null); // ETI
    params.put("PARAMETRO7", cliente.getNombre());
    params.put("PARAMETRO8", cliente.getNombreCont());
    params.put("PARAMETRO9", cliente.getApellidoCont());
    params.put("PARAMETRO10", cliente.getNoActividad());
    params.put("PARAMETRO11", cliente.getNoTipoEmpresa());
    params.put("PARAMETRO12", cliente.getNoEmpleado());
    params.put("PARAMETRO13", cliente.getRucCedula());
    params.put("PARAMETRO14", cliente.getDireccion());
    params.put("PARAMETRO15", cliente.getTelefono());
    params.put("PARAMETRO16", cliente.getExtension());
    params.put("PARAMETRO17", cliente.getFax());
    params.put("PARAMETRO18", cliente.getTelefono2());
    params.put("PARAMETRO19", cliente.getExtension2());
    params.put("PARAMETRO20", cliente.getPaginaWeb());
    params.put("PARAMETRO21", cliente.getPaginaWeb2());
    params.put("PARAMETRO22", cliente.getEmail1());
    params.put("PARAMETRO23", cliente.getFacebook());
    params.put("PARAMETRO24", cliente.getInstagram());
    params.put("PARAMETRO25", cliente.getTwitter());
    params.put("PARAMETRO26", cliente.getApartado());
    params.put("PARAMETRO27", cliente.getNoPais());
    params.put("PARAMETRO28", cliente.getNoOrigen());
    params.put("PARAMETRO29", cliente.getNoProvincia());
    params.put("PARAMETRO30", cliente.getLimiteCredi());
    params.put("PARAMETRO31", cliente.getNoDistrito());
    params.put("PARAMETRO32", cliente.getSaldo());
    params.put("PARAMETRO33", cliente.getNoCorregimiento());
    params.put("PARAMETRO34", cliente.getDisponible());
    params.put("PARAMETRO35", cliente.getReferido());
    params.put("PARAMETRO36", cliente.getInstruccionEspecial());
    params.put("PARAMETRO37", cliente.getExcentoImp());
    params.put("PARAMETRO38", cliente.getDistribuidor());
    params.put("PARAMETRO39", cliente.getDv());
    params.put("PARAMETRO40", cliente.getMovil());
    params.put("PARAMETRO41", cliente.getFechaNacimiento());
    params.put("PARAMETRO42", cliente.getNoGrupoMercado());
    params.put("PACTIVO", "S");
    params.put("INDICADOR", "I");
    params.put("PREPLICAR", "N");
    params.put("PUSUARIO", "PORTAL");
    params.put("V_SECUENCIAL", null); // OUT param, se ignora al enviar
    params.put("PARAMETRO43", cliente.getAccesoWeb());
    params.put("PARAMETRO44", cliente.getUsuarioWeb());
    params.put("PARAMETRO45", cliente.getNoPlazo());
    params.put("PARAMETRO46", cliente.getIndClienteContado());
    params.put("PARAMETRO47", cliente.getUsuarioCreacion());
    params.put("PMODULO_ORIGEN", cliente.getModuloOrigen() != null ? cliente.getModuloOrigen() : "WEB");
    params.put("PLATITUD", cliente.getLatitud());
    params.put("PLONGITUD", cliente.getLongitud());
    params.put("PTOLERANCIA", cliente.getTolerancia());
    params.put("PRUTA", cliente.getRuta());
    params.put("PCOD_RUTA", cliente.getCodRuta());
    params.put("PIND_SOPORTE", cliente.getIndSoporte());
    params.put("PCLIENTE_HELP_DESK", cliente.getClienteHelpDesk());
    params.put("PESTADO_PROMO", cliente.getEstadoPromo());
    params.put("PCOD_RUTA", cliente.getCodRuta());
    params.put("PIND_SUCURSAL", cliente.getIndSucursal());
    params.put("PTIPO_P", cliente.getTipoP());
    params.put("pind_sexo", cliente.getIndSexo());
    params.put("pvalidar_morosidad_contado", cliente.getValidarMorosidadContado());
    params.put("pcompania_inter_cia", cliente.getCompaniaInterCia());

    // Mostrar en consola los valores enviados
    System.out.println("Valores enviados al procedimiento CLIENTE:");
    params.forEach((k, v) -> System.out.println(k + ": " + v));

    Map<String, Object> result = jdbcCall.execute(params);

    // Mostrar en consola los valores generados/autogenerados por el SP
    System.out.println("Respuesta del procedimiento CLIENTE:");
    result.forEach((k, v) -> System.out.println(k + ": " + v));

    // Mensaje claro según resultado
    Map<String, Object> response = new HashMap<>();
    if (result.containsKey("V_SECUENCIAL") && result.get("V_SECUENCIAL") != null) {
      response.put("success", true);
      response.put("mensaje", "Cliente registrado exitosamente. Código generado: " + result.get("V_SECUENCIAL"));
    } else if (result.containsKey("mensaje")) {
      response.put("success", false);
      response.put("mensaje", result.get("mensaje"));
    } else {
      response.put("success", false);
      response.put("mensaje", "No se pudo registrar el cliente. Consulte con soporte.");
    }
    response.put("valores_enviados", params);
    response.put("valores_generados", result);
    return response;
  }

  @GetMapping("/buscar")
  public Object buscarCliente(
          @RequestParam int noCia,
          @RequestParam int noCliente,
          @RequestParam String grupo) {
      ClienteId id = new ClienteId();
      id.setNoCia(noCia);
      id.setNoCliente(noCliente);
      id.setGrupo(grupo);
      Optional<Cliente> cliente = repository.findById(id);
      if (cliente.isPresent()) {
          return cliente.get();
      } else {
          return java.util.Collections.singletonMap("mensaje", "Cliente no existe");
      }
  }
}