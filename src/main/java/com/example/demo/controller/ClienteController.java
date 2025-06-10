package com.example.demo.controller;

import com.example.demo.dto.ContactoDTO;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.ClienteId;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
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
  public Page<Cliente> getAll(@RequestParam(required = false) Integer noCia, Pageable pageable) {
    if (noCia != null) {
      Specification<Cliente> spec = (root, query, cb) -> cb.equal(root.get("id").get("noCia"), noCia);
      return repository.findAll(spec, pageable);
    } else {
      return repository.findAll(pageable);
    }
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
      .collect(java.util.stream.Collectors.toList());
  }

  @PostMapping("/registro-portal")
  public Map<String, Object> registrarDesdePortal(@RequestBody Cliente cliente) {
    Map<String, Object> response = new HashMap<>();
    String sql = "{call CLIENTE(\n"
            + "?, ?, ?, ?, ?,\n"   // 1-5
            + "?, ?, ?, ?, ?,\n"   // 6-10
            + "?, ?, ?, ?, ?,\n"   // 11-15
            + "?, ?, ?, ?, ?,\n"   // 16-20
            + "?, ?, ?, ?, ?,\n"   // 21-25
            + "?, ?, ?, ?, ?,\n"   // 26-30
            + "?, ?, ?, ?, ?,\n"   // 31-35
            + "?, ?, ?, ?, ?,\n"   // 36-40
            + "?, ?, ?, ?, ?,\n"   // 41-45
            + "?, ?, ?, ?, ?,\n"   // 46-50
            + "?, ?, ?, ?, ?,\n"   // 51-55
            + "?, ?, ?, ?, ?,\n"   // 56-60
            + "?, ?, ?, ?, ?,\n"   // 61-65
            + "?, ?, ?, ?)}"; // 66-69
    Map<Integer, Object> valoresEnviados = new HashMap<>();
    try (java.sql.Connection conn = dataSource.getConnection();
         java.sql.CallableStatement stmt = conn.prepareCall(sql)) {
      int idx = 1;
      stmt.setInt(idx, cliente.getId().getNoCia()); valoresEnviados.put(idx++, cliente.getId().getNoCia());
      stmt.setObject(idx, null); valoresEnviados.put(idx++, null);
      stmt.setObject(idx, null); valoresEnviados.put(idx++, null);
      stmt.setObject(idx, null); valoresEnviados.put(idx++, null);
      
      stmt.setObject(idx, null); valoresEnviados.put(idx++, null);
      stmt.setString(idx, cliente.getId().getGrupo()); valoresEnviados.put(idx++, cliente.getId().getGrupo());
      stmt.setString(idx, cliente.getPersonaNj() != null ? cliente.getPersonaNj() : "N"); valoresEnviados.put(idx++, cliente.getPersonaNj() != null ? cliente.getPersonaNj() : "N");

      stmt.setObject(idx, cliente.getNoEmpleado()); valoresEnviados.put(idx++, cliente.getNoEmpleado());
      stmt.setString(idx, cliente.getNombre()); valoresEnviados.put(idx++, cliente.getNombre());
      stmt.setString(idx, cliente.getNombreCont()); valoresEnviados.put(idx++, cliente.getNombreCont());
      stmt.setString(idx, cliente.getApellidoCont()); valoresEnviados.put(idx++, cliente.getApellidoCont());
      stmt.setObject(idx, cliente.getNoActividad()); valoresEnviados.put(idx++, cliente.getNoActividad());
      stmt.setObject(idx, cliente.getNoTipoEmpresa()); valoresEnviados.put(idx++, cliente.getNoTipoEmpresa());

      stmt.setString(idx, cliente.getExtension()); valoresEnviados.put(idx++, cliente.getExtension());
      stmt.setString(idx, cliente.getRucCedula()); valoresEnviados.put(idx++, cliente.getRucCedula());
      stmt.setString(idx, cliente.getDireccion()); valoresEnviados.put(idx++, cliente.getDireccion());
      stmt.setString(idx, cliente.getTelefono()); valoresEnviados.put(idx++, cliente.getTelefono());

      stmt.setString(idx, cliente.getFax()); valoresEnviados.put(idx++, cliente.getFax());
      
      stmt.setString(idx, cliente.getExtension2()); valoresEnviados.put(idx++, cliente.getExtension2());
      stmt.setString(idx, cliente.getTelefono2()); valoresEnviados.put(idx++, cliente.getTelefono2());

      stmt.setString(idx, cliente.getPaginaWeb()); valoresEnviados.put(idx++, cliente.getPaginaWeb());
      stmt.setString(idx, cliente.getPaginaWeb2()); valoresEnviados.put(idx++, cliente.getPaginaWeb2());
      
      stmt.setString(idx, cliente.getFacebook()); valoresEnviados.put(idx++, cliente.getFacebook());
      stmt.setString(idx, cliente.getEmail1()); valoresEnviados.put(idx++, cliente.getEmail1());

      stmt.setString(idx, cliente.getInstagram()); valoresEnviados.put(idx++, cliente.getInstagram());
      stmt.setString(idx, cliente.getTwitter()); valoresEnviados.put(idx++, cliente.getTwitter());
      stmt.setString(idx, cliente.getApartado()); valoresEnviados.put(idx++, cliente.getApartado());
      
      stmt.setObject(idx, cliente.getLimiteCredi()); valoresEnviados.put(idx++, cliente.getLimiteCredi());
      stmt.setObject(idx, cliente.getNoPais()); valoresEnviados.put(idx++, cliente.getNoPais());
      stmt.setObject(idx, cliente.getNoOrigen()); valoresEnviados.put(idx++, cliente.getNoOrigen());
      stmt.setObject(idx, cliente.getNoProvincia()); valoresEnviados.put(idx++, cliente.getNoProvincia());

      
      stmt.setObject(idx, cliente.getSaldo()); valoresEnviados.put(idx++, cliente.getSaldo());
      stmt.setObject(idx, cliente.getNoDistrito()); valoresEnviados.put(idx++, cliente.getNoDistrito());

      
      stmt.setObject(idx, cliente.getDisponible()); valoresEnviados.put(idx++, cliente.getDisponible());
      stmt.setObject(idx, cliente.getNoCorregimiento()); valoresEnviados.put(idx++, cliente.getNoCorregimiento());

      stmt.setString(idx, cliente.getReferido()); valoresEnviados.put(idx++, cliente.getReferido());
      stmt.setString(idx, cliente.getInstruccionEspecial()); valoresEnviados.put(idx++, cliente.getInstruccionEspecial());
      stmt.setString(idx, cliente.getExcentoImp()); valoresEnviados.put(idx++, cliente.getExcentoImp());
      stmt.setString(idx, cliente.getDistribuidor()); valoresEnviados.put(idx++, cliente.getDistribuidor());
      stmt.setString(idx, cliente.getDv()); valoresEnviados.put(idx++, cliente.getDv());
      
      stmt.setObject(idx, cliente.getFechaNacimiento()); valoresEnviados.put(idx++, cliente.getFechaNacimiento());
      stmt.setString(idx, cliente.getMovil()); valoresEnviados.put(idx++, cliente.getMovil());
      
      stmt.setObject(idx, cliente.getNoGrupoMercado()); valoresEnviados.put(idx++, cliente.getNoGrupoMercado());
      stmt.setString(idx, "S"); valoresEnviados.put(idx++, "S");
      stmt.setString(idx, "I"); valoresEnviados.put(idx++, "I");
      stmt.setString(idx, "N"); valoresEnviados.put(idx++, "N");
      stmt.setString(idx, "PORTAL"); valoresEnviados.put(idx++, "PORTAL"); // 47
      // 48: OUT V_SECUENCIAL (debe ir aquí)
      stmt.registerOutParameter(idx, java.sql.Types.INTEGER); valoresEnviados.put(idx, "OUT V_SECUENCIAL"); idx++;
      // 52: PUSUARIO (usuario de creación)
      stmt.setString(idx, cliente.getUsuarioCreacion()); valoresEnviados.put(idx++, cliente.getUsuarioCreacion());
      // 53: PARAMETRO43 (acceso web)
      stmt.setString(idx, cliente.getAccesoWeb()); valoresEnviados.put(idx++, cliente.getAccesoWeb());
      // 54: PARAMETRO44 (usuario web)
      stmt.setString(idx, cliente.getUsuarioWeb()); valoresEnviados.put(idx++, cliente.getUsuarioWeb());
      // 55: PARAMETRO45 (plazo)
      stmt.setObject(idx, cliente.getNoPlazo()); valoresEnviados.put(idx++, cliente.getNoPlazo());
      // 56: PARAMETRO46 (cliente contado)
      stmt.setString(idx, cliente.getIndClienteContado()); valoresEnviados.put(idx++, cliente.getIndClienteContado());
      // 57: PMODULO_ORIGEN
      stmt.setString(idx, cliente.getModuloOrigen() != null ? cliente.getModuloOrigen() : "WEB"); valoresEnviados.put(idx++, cliente.getModuloOrigen() != null ? cliente.getModuloOrigen() : "WEB");
      // 58: PLATITUD
      stmt.setString(idx, cliente.getLatitud()); valoresEnviados.put(idx++, cliente.getLatitud());
      // 59: PLONGITUD
      stmt.setString(idx, cliente.getLongitud()); valoresEnviados.put(idx++, cliente.getLongitud());
      // 60: PTOLERANCIA
      stmt.setObject(idx, cliente.getTolerancia()); valoresEnviados.put(idx++, cliente.getTolerancia());
      // 61: PRUTA
      stmt.setString(idx, cliente.getRuta()); valoresEnviados.put(idx++, cliente.getRuta());
      // 62: PIND_SOPORTE
      stmt.setString(idx, cliente.getIndSoporte()); valoresEnviados.put(idx++, cliente.getIndSoporte());
      // 63: PCLIENTE_HELP_DESK
      stmt.setString(idx, cliente.getClienteHelpDesk()); valoresEnviados.put(idx++, cliente.getClienteHelpDesk());
      // 64: PESTADO_PROMO
      stmt.setString(idx, cliente.getEstadoPromo()); valoresEnviados.put(idx++, cliente.getEstadoPromo());
      // 65: PCOD_RUTA
      stmt.setString(idx, cliente.getCodRuta()); valoresEnviados.put(idx++, cliente.getCodRuta());
      // 66: PIND_SUCURSAL
      stmt.setString(idx, cliente.getIndSucursal()); valoresEnviados.put(idx++, cliente.getIndSucursal());
      // 67: PTIPO_P
      stmt.setString(idx, cliente.getTipoP()); valoresEnviados.put(idx++, cliente.getTipoP());
      // 68: pind_sexo
      stmt.setString(idx, cliente.getIndSexo()); valoresEnviados.put(idx++, cliente.getIndSexo());
      // 69: pvalidar_morosidad_contado
      stmt.setString(idx, cliente.getValidarMorosidadContado()); valoresEnviados.put(idx++, cliente.getValidarMorosidadContado());
      stmt.setObject(idx, cliente.getCompaniaInterCia()); valoresEnviados.put(idx++, cliente.getCompaniaInterCia());
      stmt.setObject(idx, null); valoresEnviados.put(idx++, null);
      stmt.setObject(idx, null); valoresEnviados.put(idx++, null);

      // DEBUG: Mostrar el JSON recibido
      System.out.println("DEBUG: JSON recibido en registrarDesdePortal: " + new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(cliente));
      // Validación y log de parámetros críticos
      System.out.println("DEBUG: noProvincia recibido en request: " + cliente.getNoProvincia());
      if (cliente.getNoProvincia() == 0) {
        System.out.println("ADVERTENCIA: noProvincia es 0, probablemente no se envió o llegó vacío");
      }

      // Mostrar en consola los valores enviados
      System.out.println("Valores enviados al procedimiento CLIENTE:");
      valoresEnviados.forEach((k, v) -> System.out.println("Parametro " + k + ": " + v));

      // Ejecutar registro de cliente
      stmt.execute();
      int vSecuencial = stmt.getInt(48); // OUT param en la posición 48
      
      // Si el cliente fue registrado exitosamente, registrar el contacto
      if (vSecuencial > 0 && cliente.getContacto() != null) {
        registrarContacto(cliente, vSecuencial);
      }
      
      response.put("success", true);
      response.put("mensaje", "Cliente registrado exitosamente. Código generado: " + vSecuencial);
      response.put("V_SECUENCIAL", vSecuencial);
      response.put("valores_enviados", valoresEnviados);
    } catch (Exception e) {
      response.put("success", false);
      response.put("mensaje", e.getMessage());
      response.put("valores_enviados", valoresEnviados);
    }
    return response;
  }

  private void registrarContacto(Cliente cliente, int vSecuencial) throws Exception {
    String sqlContacto = "{call CONTACTO(\n"
                        + "?, ?, ?, ?, ?,\n" // 1-5
                        + "?, ?, ?, ?, ?,\n" // 6-10
                        + "?, ?, ?, ?, ?,\n" // 11-15
                        + "?, ?, ?, ?, ?,\n" // 16-20
                        + "?, ?, ?, ?, ?)}"; // 21-25
    
    try (java.sql.Connection conn = dataSource.getConnection();
         java.sql.CallableStatement stmtContacto = conn.prepareCall(sqlContacto)) {
      
      ContactoDTO contacto = cliente.getContacto();
      
      // Si es persona natural, usar los datos del cliente como contacto
      if ("N".equals(cliente.getPersonaNj())) {
        contacto = new ContactoDTO();
        contacto.setNombre(cliente.getNombre());
        contacto.setApellido(""); // Para persona natural, apellido puede estar en el nombre
        contacto.setTelefono(cliente.getTelefono());
        contacto.setEmail(cliente.getEmail1());
        contacto.setMovil(cliente.getMovil());
        contacto.setFax(cliente.getFax());
        contacto.setExtension(cliente.getExtension());
        contacto.setDireccion(cliente.getDireccion());
        contacto.setSexo(cliente.getIndSexo());
        contacto.setFechaNacimiento(cliente.getFechaNacimiento() != null ? cliente.getFechaNacimiento().toString() : null);
      }
      
      int idx = 1;
      // CIA
      stmtContacto.setInt(idx++, cliente.getId().getNoCia());
      // PARAMETRO1 - NO_CLIENTE
      stmtContacto.setString(idx++, String.valueOf(vSecuencial));
      // PARAMETRO2 - NO_CONTACTO (null para nuevo)
      stmtContacto.setString(idx++, null);
      // PARAMETRO3 - NOMBRE
      stmtContacto.setString(idx++, contacto.getNombre());
      // PARAMETRO4 - APELLIDO
      stmtContacto.setString(idx++, contacto.getApellido());
      // PARAMETRO5 - COD_CARGO
      stmtContacto.setString(idx++, contacto.getCodCargo());
      // PARAMETRO6 - DIRECCION
      stmtContacto.setString(idx++, contacto.getDireccion());
      // PARAMETRO7 - DIRECCION1
      stmtContacto.setString(idx++, contacto.getDireccion1());
      // PARAMETRO8 - TELEFONO
      stmtContacto.setString(idx++, contacto.getTelefono());
      // PARAMETRO9 - EXTENSION
      stmtContacto.setString(idx++, contacto.getExtension());
      // PARAMETRO10 - FAX
      stmtContacto.setString(idx++, contacto.getFax());
      // PARAMETRO11 - EMAIL
      stmtContacto.setString(idx++, contacto.getEmail());
      // PARAMETRO12 - MOVIL
      stmtContacto.setString(idx++, contacto.getMovil());
      // PARAMETRO13 - PAGINA_WEB
      stmtContacto.setString(idx++, contacto.getPaginaWeb());
      // PARAMETRO14 - null (no usado)
      stmtContacto.setString(idx++, null);
      // PARAMETRO15 - SEXO
      stmtContacto.setString(idx++, contacto.getSexo());
      // PARAMETRO16 - FECHA_NACIMIENTO
      stmtContacto.setString(idx++, contacto.getFechaNacimiento());
      // PARAMETRO17 - USUARIO_APLICACION
      stmtContacto.setString(idx++, cliente.getUsuarioCreacion());
      // INDICADOR
      stmtContacto.setString(idx++, "I"); // Insert
      // PACTIVO
      stmtContacto.setString(idx++, "S"); // Activo
      // PREPLICAR
      stmtContacto.setString(idx++, "N"); // No replicar
      // PACCESO_WEB
      stmtContacto.setString(idx++, contacto.getAccesoWeb() != null ? contacto.getAccesoWeb() : "N");
      // PUSUARIO_WEB
      stmtContacto.setString(idx++, contacto.getUsuarioWeb());
      // PUSUARIO_HELPDESK
      stmtContacto.setString(idx++, contacto.getUsuarioHelpdesk());
      // PACCESO_HELPDESK
      stmtContacto.setString(idx++, contacto.getAccesoHelpdesk() != null ? contacto.getAccesoHelpdesk() : "N");
      // OUT V_SECUENCIAL
      stmtContacto.registerOutParameter(idx++, java.sql.Types.INTEGER);
      
      stmtContacto.execute();
      int contactoId = stmtContacto.getInt(25); // OUT param
      System.out.println("Contacto registrado con ID: " + contactoId);
    }
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