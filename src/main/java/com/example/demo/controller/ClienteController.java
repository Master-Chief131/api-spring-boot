package com.example.demo.controller;

import com.example.demo.dto.ContactoDTO;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.ClienteId;
import com.example.demo.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
  private final DataSource dataSource;

  public ClienteController(ClienteRepository repository, DataSource dataSource) {
    this.repository = repository;
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
            + "?, ?)}"; // 66-67
    Map<Integer, Object> valoresEnviados = new HashMap<>();
    
    // INICIAR TRANSACCIÓN - Desactivar autocommit
    java.sql.Connection conn = null;
    try {
      conn = dataSource.getConnection();
      conn.setAutoCommit(false); // DESACTIVAR AUTOCOMMIT
      System.out.println("=== INICIANDO TRANSACCIÓN COMPLETA ===");
      
      java.sql.CallableStatement stmt = conn.prepareCall(sql);
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

      // DEBUG: Mostrar el JSON recibido
      System.out.println("DEBUG: JSON recibido en registrarDesdePortal: " + new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(cliente));
      // Validación y log de parámetros críticos
      System.out.println("DEBUG: noProvincia recibido en request: " + cliente.getNoProvincia());
      if (cliente.getNoProvincia() == 0) {
        System.out.println("ADVERTENCIA: noProvincia es 0, probablemente no se envió o llegó vacío");
      }

      // Mostrar en consola los valores enviados
      System.out.println("Valores enviados al procedimiento CLIENTE:");
      valoresEnviados.forEach((k, v) -> System.out.println("Parametro " + k + ": " + v));      // Ejecutar procedimiento CLIENTE
      System.out.println("=== PASO 1: Registrando Cliente ===");
      stmt.execute();
      int vSecuencial = stmt.getInt(48); // OUT param en la posición 48
      System.out.println("Cliente registrado con código: " + vSecuencial);
      
      // Registrar contacto automáticamente después del cliente exitoso
      System.out.println("=== PASO 2: Registrando Contacto ===");
      Map<String, Object> contactoInfo = registrarContacto(cliente, vSecuencial, conn);
      
      // Verificar si el contacto se registró correctamente
      if (!(Boolean) contactoInfo.get("success")) {
        throw new RuntimeException("Error al registrar contacto: " + contactoInfo.get("mensaje"));
      }
      
      // Registrar usuario automáticamente después del contacto exitoso
      System.out.println("=== PASO 3: Registrando Usuario ===");
      Map<String, Object> usuarioInfo = registrarUsuario(cliente, vSecuencial, conn);
        // Verificar si el usuario se registró correctamente
      if (!(Boolean) usuarioInfo.get("success")) {
        throw new RuntimeException("Error al registrar usuario: " + usuarioInfo.get("mensaje"));
      }
      
      // PASO 4: Actualizar el contacto con el usuario registrado
      System.out.println("=== PASO 4: Actualizando contacto con usuario registrado ===");
      String usuarioRegistrado = (String) usuarioInfo.get("usuario_registrado");
      Integer contactoSecuencial = (Integer) contactoInfo.get("secuencial_contacto");
      
      Map<String, Object> actualizacionContacto = actualizarUsuarioWebContacto(
          vSecuencial, contactoSecuencial, usuarioRegistrado, conn);
      
      // Verificar si la actualización del contacto fue exitosa
      if (!(Boolean) actualizacionContacto.get("success")) {
        throw new RuntimeException("Error al actualizar contacto con usuario: " + actualizacionContacto.get("mensaje"));
      }
      
      // Si llegamos aquí, todo salió bien - HACER COMMIT
      conn.commit();
      System.out.println("=== TRANSACCIÓN COMPLETADA EXITOSAMENTE - COMMIT REALIZADO ===");
        response.put("success", true);
      response.put("mensaje", "Cliente, contacto y usuario registrados exitosamente. Código generado: " + vSecuencial);
      response.put("V_SECUENCIAL", vSecuencial);
      response.put("contacto_registrado", contactoInfo);
      response.put("usuario_registrado", usuarioInfo);
      response.put("contacto_actualizado", actualizacionContacto);
      response.put("valores_enviados", valoresEnviados);
      response.put("transaccion_estado", "COMPLETADA");
      
    } catch (Exception e) {
      System.err.println("=== ERROR EN TRANSACCIÓN - HACIENDO ROLLBACK ===");
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
      
      // ROLLBACK en caso de error
      if (conn != null) {
        try {
          
          conn.rollback();
          System.out.println("=== ROLLBACK COMPLETADO - NINGÚN DATO FUE GUARDADO ===");
        } catch (Exception rollbackEx) {
          System.err.println("Error en rollback: " + rollbackEx.getMessage());
        }
      }
      
      response.put("success", false);
      response.put("mensaje", "Error en el registro: " + e.getMessage());
      response.put("valores_enviados", valoresEnviados);
      response.put("transaccion_estado", "ROLLBACK_EJECUTADO");
    } finally {
      // Cerrar la conexión
      if (conn != null) {
        try {
          conn.close();
        } catch (Exception closeEx) {
          System.err.println("Error cerrando conexión: " + closeEx.getMessage());
        }      }
    }
    return response;
  }

  private Map<String, Object> registrarContacto(Cliente cliente, int vSecuencial, java.sql.Connection conn) {
    Map<String, Object> contactoInfo = new HashMap<>();
    
    try {
      ContactoDTO contactoDTO;
        // Crear contacto según el tipo de persona
      if ("N".equals(cliente.getPersonaNj())) { // Persona Natural
        // Para persona natural, el cliente es su propio contacto
        contactoDTO = new ContactoDTO();
        contactoDTO.setNoCia(cliente.getId().getNoCia());
        contactoDTO.setNoCliente(vSecuencial);
        contactoDTO.setGrupo(cliente.getId().getGrupo());
        contactoDTO.setNombre(cliente.getNombre());
        contactoDTO.setApellido(cliente.getApellidoCont() != null ? cliente.getApellidoCont() : "");
        contactoDTO.setTelefono(cliente.getTelefono());
        contactoDTO.setMovil(cliente.getMovil());
        contactoDTO.setEmail(cliente.getEmail1());
        contactoDTO.setDireccion(cliente.getDireccion());        contactoDTO.setCedula(cliente.getRucCedula());
        contactoDTO.setIndPrincipal("S");
        contactoDTO.setIndEstado("S");
        contactoDTO.setUsuarioCreacion(cliente.getUsuarioCreacion() != null ? cliente.getUsuarioCreacion() : "PORTAL_ADMIN");
        contactoDTO.setAccesoWeb("S");
        contactoDTO.setUsuarioWeb(cliente.getUsuarioWeb());
        contactoDTO.setSexo(cliente.getIndSexo());
        contactoDTO.setFechaNacimiento(cliente.getFechaNacimiento() != null ? cliente.getFechaNacimiento().toString() : "");
      } else { // Persona Jurídica
        // Para persona jurídica, usar el objeto contacto enviado en el JSON
        contactoDTO = cliente.getContacto();
        if (contactoDTO != null) {
          contactoDTO.setNoCia(cliente.getId().getNoCia());
          contactoDTO.setNoCliente(vSecuencial);
          contactoDTO.setGrupo(cliente.getId().getGrupo());
          contactoDTO.setIndPrincipal("S");
          contactoDTO.setIndEstado("S");
          if (contactoDTO.getUsuarioCreacion() == null) {
            contactoDTO.setUsuarioCreacion(cliente.getUsuarioCreacion() != null ? cliente.getUsuarioCreacion() : "PORTAL_ADMIN");
          }
          if (contactoDTO.getAccesoWeb() == null) {
            contactoDTO.setAccesoWeb("S");
          }
          if (contactoDTO.getUsuarioWeb() == null) {
            contactoDTO.setUsuarioWeb(cliente.getUsuarioWeb());
          }
        } else {
          // Si no se proporciona contacto para persona jurídica, crear uno básico
          contactoDTO = new ContactoDTO();
          contactoDTO.setNoCia(cliente.getId().getNoCia());
          contactoDTO.setNoCliente(vSecuencial);
          contactoDTO.setGrupo(cliente.getId().getGrupo());
          contactoDTO.setNombre(cliente.getNombreCont() != null ? cliente.getNombreCont() : "Contacto Principal");
          contactoDTO.setApellido(cliente.getApellidoCont() != null ? cliente.getApellidoCont() : "");
          contactoDTO.setTelefono(cliente.getTelefono());
          contactoDTO.setMovil(cliente.getMovil());
          contactoDTO.setEmail(cliente.getEmail1());
          contactoDTO.setDireccion(cliente.getDireccion());
          contactoDTO.setIndPrincipal("S");
          contactoDTO.setIndEstado("S");
          contactoDTO.setUsuarioCreacion(cliente.getUsuarioCreacion() != null ? cliente.getUsuarioCreacion() : "PORTAL_ADMIN");
          contactoDTO.setAccesoWeb("S");
          contactoDTO.setUsuarioWeb(cliente.getUsuarioWeb());
        }
      }
        if (contactoDTO != null) {        // Llamar al procedimiento almacenado CONTACTO - 25 IN + 1 OUT = 26 parámetros
        String sql = "{call CONTACTO(\n"
        + "?, ?, ?, ?, ?,\n"        // 1-5
        + "?, ?, ?, ?, ?,\n"        // 6-10
        + "?, ?, ?, ?, ?,\n"        // 11-15
        + "?, ?, ?, ?, ?,\n"        // 16-20
        + "?, ?, ?, ?, ?,\n"        // 21-25
        + "?)}";                    // 26
        
        try (java.sql.CallableStatement stmt = conn.prepareCall(sql)) {
            int idx = 1;
            
          System.out.println("DEBUG: Iniciando registro de contacto con parámetros:");
          System.out.println("  - NO_CIA: " + (contactoDTO.getNoCia() != null ? contactoDTO.getNoCia() : 0));
          System.out.println("  - NO_CLIENTE: " + (contactoDTO.getNoCliente() != null ? contactoDTO.getNoCliente().toString() : ""));
          System.out.println("  - NOMBRE: " + contactoDTO.getNombre());
          System.out.println("  - APELLIDO: " + contactoDTO.getApellido());
          System.out.println("  - TELEFONO: " + contactoDTO.getTelefono());
          System.out.println("  - EMAIL: " + contactoDTO.getEmail());
          
          // CIA INT(5) - Compañía
          stmt.setInt(idx++, contactoDTO.getNoCia() != null ? contactoDTO.getNoCia() : 0);
          
          // PARAMETRO1 VARCHAR(50) - NO_CLIENTE  
          stmt.setString(idx++, contactoDTO.getNoCliente() != null ? contactoDTO.getNoCliente().toString() : "");
          
          // PARAMETRO2 VARCHAR(50) - NO_CONTACTO (null para INSERT)
          stmt.setString(idx++, null);
          
          // PARAMETRO3 VARCHAR(50) - NOMBRE
          stmt.setString(idx++, contactoDTO.getNombre() != null ? contactoDTO.getNombre() : "");
          
          // PARAMETRO4 VARCHAR(50) - APELLIDO
          stmt.setString(idx++, contactoDTO.getApellido() != null ? contactoDTO.getApellido() : "");
          
          // PARAMETRO5 VARCHAR(50) - COD_CARGO
          stmt.setString(idx++, contactoDTO.getCargo() != null ? contactoDTO.getCargo() : "");
          
          // PARAMETRO6 VARCHAR(200) - DIRECCION
          stmt.setString(idx++, contactoDTO.getDireccion() != null ? contactoDTO.getDireccion() : "");
          
          // PARAMETRO7 VARCHAR(200) - DIRECCION1 (dirección secundaria)
          stmt.setString(idx++, contactoDTO.getDireccion1() != null ? contactoDTO.getDireccion1() : "");
          
          // PARAMETRO8 VARCHAR(50) - TELEFONO
          stmt.setString(idx++, contactoDTO.getTelefono() != null ? contactoDTO.getTelefono() : "");
          
          // PARAMETRO9 VARCHAR(50) - EXTENSION
          stmt.setString(idx++, contactoDTO.getExtension() != null ? contactoDTO.getExtension() : "");
          
          // PARAMETRO10 VARCHAR(50) - FAX
          stmt.setString(idx++, contactoDTO.getFax() != null ? contactoDTO.getFax() : "");
          
          // PARAMETRO11 VARCHAR(50) - EMAIL
          stmt.setString(idx++, contactoDTO.getEmail() != null ? contactoDTO.getEmail() : "");
          
          // PARAMETRO12 VARCHAR(50) - MOVIL
          stmt.setString(idx++, contactoDTO.getMovil() != null ? contactoDTO.getMovil() : "");
          
          // PARAMETRO13 VARCHAR(50) - PAGINA_WEB
          stmt.setString(idx++, contactoDTO.getPaginaWeb() != null ? contactoDTO.getPaginaWeb() : "");
          
          // PARAMETRO14 VARCHAR(50) - (No se usa en INSERT según el SP)
          stmt.setString(idx++, "");
          
          // PARAMETRO15 VARCHAR(50) - SEXO
          stmt.setString(idx++, contactoDTO.getSexo() != null ? contactoDTO.getSexo() : "");
          
          // PARAMETRO16 VARCHAR(50) - FECHA_NACIMIENTO
          stmt.setString(idx++, contactoDTO.getFechaNacimiento() != null ? contactoDTO.getFechaNacimiento() : "");
          
          // PARAMETRO17 VARCHAR(50) - USUARIO_APLICACION
          stmt.setString(idx++, contactoDTO.getUsuarioCreacion() != null ? contactoDTO.getUsuarioCreacion() : "PORTAL_ADMIN");
          
          // INDICADOR VARCHAR(1) - "I" para INSERT
          stmt.setString(idx++, "I");
            // PACTIVO VARCHAR(1) - Estado activo
          stmt.setString(idx++, contactoDTO.getIndEstado() != null ? contactoDTO.getIndEstado() : "S");
          
          // PREPLICAR VARCHAR(1) - "N" para no replicar
          stmt.setString(idx++, "N");
          
          // PACCESO_WEB VARCHAR(1) - Acceso web
          stmt.setString(idx++, contactoDTO.getAccesoWeb() != null ? contactoDTO.getAccesoWeb() : "S");
          
          // PUSUARIO_WEB VARCHAR(50) - Usuario web
          stmt.setString(idx++, contactoDTO.getUsuarioWeb() != null ? contactoDTO.getUsuarioWeb() : contactoDTO.getUsuarioCreacion());
          
          // PUSUARIO_HELPDESK VARCHAR(50) - Usuario helpdesk
          stmt.setString(idx++, contactoDTO.getUsuarioHelpdesk() != null ? contactoDTO.getUsuarioHelpdesk() : contactoDTO.getUsuarioCreacion());
          
          // PACCESO_HELPDESK VARCHAR(1) - Acceso helpdesk
          stmt.setString(idx++, contactoDTO.getAccesoHelpdesk() != null ? contactoDTO.getAccesoHelpdesk() : "N");          // V_SECUENCIAL OUT - Parámetro de salida
          stmt.registerOutParameter(idx, java.sql.Types.INTEGER);
          
          System.out.println("DEBUG: Total de parámetros configurados: " + idx + " (debería ser 26 - 25 IN + 1 OUT)");
          System.out.println("DEBUG: Ejecutando procedimiento almacenado CONTACTO...");
          stmt.execute();
          int contactoSecuencial = stmt.getInt(idx);
          System.out.println("DEBUG: Contacto registrado exitosamente con código: " + contactoSecuencial);
            // Agregar información del contacto registrado a la respuesta
          contactoInfo.put("success", true);
          contactoInfo.put("secuencial_contacto", contactoSecuencial);
          contactoInfo.put("mensaje", "Contacto registrado exitosamente");
          contactoInfo.put("tipo_persona", "N".equals(cliente.getPersonaNj()) ? "Persona Natural" : "Persona Jurídica");
          contactoInfo.put("nombre", contactoDTO.getNombre());
          contactoInfo.put("apellido", contactoDTO.getApellido());
          contactoInfo.put("telefono", contactoDTO.getTelefono());
          contactoInfo.put("email", contactoDTO.getEmail());          contactoInfo.put("cedula", contactoDTO.getCedula());
          contactoInfo.put("es_principal", contactoDTO.getIndPrincipal());
          contactoInfo.put("estado", contactoDTO.getIndEstado());
          
        } // Cierra el try-with-resources
      } // Cierra el if (contactoDTO != null)
    } catch (Exception e) {
      System.err.println("Error al registrar contacto: " + e.getMessage());
      e.printStackTrace();
      contactoInfo.put("success", false);
      contactoInfo.put("mensaje", "Error al registrar contacto: " + e.getMessage());
    }
      return contactoInfo;
  }
  private Map<String, Object> registrarUsuario(Cliente cliente, int vSecuencial, java.sql.Connection conn) {
    Map<String, Object> usuarioInfo = new HashMap<>();
    
    try {
      com.example.demo.dto.UsuarioDTO usuarioDTO;
      
      // Crear usuario según los datos del cliente o del objeto usuario enviado en el JSON
      if (cliente.getUsuario() != null) {
        // Si se envió un objeto usuario en el JSON, usarlo
        usuarioDTO = cliente.getUsuario();
      } else {
        // Si no se envió, crear uno básico con los datos del cliente
        usuarioDTO = new com.example.demo.dto.UsuarioDTO();
        usuarioDTO.setUsuario(cliente.getUsuarioWeb() != null ? cliente.getUsuarioWeb() : cliente.getRucCedula());
        usuarioDTO.setNomUsuario(cliente.getNombre());
        usuarioDTO.setApeUsuario(cliente.getApellidoCont() != null ? cliente.getApellidoCont() : "");
        usuarioDTO.setEmail(cliente.getEmail1());
        usuarioDTO.setClave("123456"); // Clave por defecto
        usuarioDTO.setIndActivo("S");
        usuarioDTO.setIndCliente("S");
      }
        // Verificar si el usuario ya existe
      String checkUserSql = "SELECT usuario FROM segweb_usuario WHERE usuario = ?";
      try (java.sql.PreparedStatement checkStmt = conn.prepareStatement(checkUserSql)) {
        
        checkStmt.setString(1, usuarioDTO.getUsuario());
        java.sql.ResultSet rs = checkStmt.executeQuery();
        
        if (rs.next()) {
          usuarioInfo.put("success", false);
          usuarioInfo.put("mensaje", "USUARIO: [" + usuarioDTO.getUsuario() + "] YA EXISTE!");
          return usuarioInfo;
        }
      }
      
      // Función simple para encriptar clave (similar al servlet original)
      String claveEncriptada = encriptarClave(usuarioDTO.getClave());
      
      // Insertar el usuario
      String insertSql = "INSERT INTO segweb_usuario " +
        "(usuario, nom_usuario, ape_usuario, email, " +
        "ind_activo, ind_cliente, ind_cajero, ind_cobrador, " +
        "ind_vendedor, frecuencia_cambio_clave, clave, " +
        "ind_verificar, admon, creado, ind_supervisor, " +
        "autoriza_descuento, crea_cliente, emite_factura_movil, " +
        "supervisor_gc, lider_depto, tipo_usuario_helpdesk, toma_fisica, " +
        "desreferenciar_doc, autoriza_compra, monto_autoriza_hasta, " +
        "toma_fisica_inv, autoriza_lote_pagos, co_registra_mes_cerrados, " +
        "cxp_registra_mes_cerrados, cxc_registra_mes_cerrados, " +
        "autoriza_precio, autoriza_grp_mercado, " +
        "cxp_procesa_documento, cxc_procesa_documento, formulacion_inv, " +
        "autoriza_packing_inv, autoriza_entra_packing_inv, modifica_orden_mf, " +
        "coordina_entrevista, nd_procesa_pago_cliente, " +
        "fc_cambia_precio, fc_cambia_lprecio, fc_minimo_margen, " +
        "ind_ver_precios_inventario, fc_registra_nc_sin_fac, " +
        "fc_fecha_exp_nc_sin_fac, ver_imagenes_portal, ver_graficas_portal, " +
        "cxc_anular_recibo, fc_registra_costo_devolu, cm_modifica_solicitud, " +
        "fc_fac_distinta_sucursal, fc_cambia_precio_serv, " +
        "ba_cierra_conci, ba_reversa_conci, fc_procesar_devolucion, " +
        "fc_porcentaje_descuento_limite, co_ind_autoriza_cuenta_contable, " +
        "fc_registra_con_vendedor, ind_cajero_fi, ind_cobrador_fi, " +
        "ind_procesa_documento_fi, ind_traslado_cuentas, " +
        "ind_access_all_fi, ind_supervisor_fi, ind_aj_mes_contable) " +        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      
      try (java.sql.PreparedStatement stmt = conn.prepareStatement(insertSql)) {
        
        int idx = 1;
        stmt.setString(idx++, usuarioDTO.getUsuario());
        stmt.setString(idx++, usuarioDTO.getNomUsuario());
        stmt.setString(idx++, usuarioDTO.getApeUsuario());
        stmt.setString(idx++, usuarioDTO.getEmail());
        stmt.setString(idx++, usuarioDTO.getIndActivo() != null ? usuarioDTO.getIndActivo() : "S");
        stmt.setString(idx++, usuarioDTO.getIndCliente() != null ? usuarioDTO.getIndCliente() : "S");
        stmt.setString(idx++, usuarioDTO.getIndCajero() != null ? usuarioDTO.getIndCajero() : "N");
        stmt.setString(idx++, usuarioDTO.getIndCobrador() != null ? usuarioDTO.getIndCobrador() : "N");
        stmt.setString(idx++, usuarioDTO.getIndVendedor() != null ? usuarioDTO.getIndVendedor() : "N");
        stmt.setString(idx++, "N"); // frecuencia_cambio_clave
        stmt.setString(idx++, claveEncriptada);
        stmt.setString(idx++, "S"); // ind_verificar
        stmt.setString(idx++, "PORTAL"); // admon
        stmt.setTimestamp(idx++, new java.sql.Timestamp(new java.util.Date().getTime())); // creado
        stmt.setString(idx++, usuarioDTO.getIndSupervisor() != null ? usuarioDTO.getIndSupervisor() : "N");
        stmt.setString(idx++, usuarioDTO.getAutorizaDescuento() != null ? usuarioDTO.getAutorizaDescuento() : "N");
        stmt.setString(idx++, usuarioDTO.getCreaCliente() != null ? usuarioDTO.getCreaCliente() : "N");
        stmt.setString(idx++, usuarioDTO.getEmiteFacturaMovil() != null ? usuarioDTO.getEmiteFacturaMovil() : "N");
        stmt.setString(idx++, usuarioDTO.getSupervisorGc() != null ? usuarioDTO.getSupervisorGc() : "N");
        stmt.setString(idx++, usuarioDTO.getLiderDepto() != null ? usuarioDTO.getLiderDepto() : "N");
        stmt.setString(idx++, usuarioDTO.getTipoUsuarioHelpdesk() != null ? usuarioDTO.getTipoUsuarioHelpdesk() : "N");
        stmt.setString(idx++, usuarioDTO.getTomaFisica() != null ? usuarioDTO.getTomaFisica() : "N");
        stmt.setString(idx++, usuarioDTO.getDesreferenciarDoc() != null ? usuarioDTO.getDesreferenciarDoc() : "N");
        stmt.setString(idx++, usuarioDTO.getAutorizaCompra() != null ? usuarioDTO.getAutorizaCompra() : "N");
        stmt.setString(idx++, usuarioDTO.getMontoAutoriza() != null ? usuarioDTO.getMontoAutoriza() : "0.00");
        stmt.setString(idx++, usuarioDTO.getTomaFisicaInv() != null ? usuarioDTO.getTomaFisicaInv() : "N");
        stmt.setString(idx++, usuarioDTO.getAutorizaLotePagos() != null ? usuarioDTO.getAutorizaLotePagos() : "N");
        stmt.setString(idx++, usuarioDTO.getCoRegistraMesCerrados() != null ? usuarioDTO.getCoRegistraMesCerrados() : "N");
        stmt.setString(idx++, usuarioDTO.getCxpRegistraMesCerrados() != null ? usuarioDTO.getCxpRegistraMesCerrados() : "N");
        stmt.setString(idx++, usuarioDTO.getCxcRegistraMesCerrados() != null ? usuarioDTO.getCxcRegistraMesCerrados() : "N");
        stmt.setString(idx++, usuarioDTO.getAutorizaPrecio() != null ? usuarioDTO.getAutorizaPrecio() : "N");
        stmt.setString(idx++, usuarioDTO.getAutorizaGrpMercado() != null ? usuarioDTO.getAutorizaGrpMercado() : "N");
        stmt.setString(idx++, usuarioDTO.getCxpProcesaDocumento() != null ? usuarioDTO.getCxpProcesaDocumento() : "N");
        stmt.setString(idx++, usuarioDTO.getCxcProcesaDocumento() != null ? usuarioDTO.getCxcProcesaDocumento() : "N");
        stmt.setString(idx++, usuarioDTO.getFormulacionInv() != null ? usuarioDTO.getFormulacionInv() : "N");
        stmt.setString(idx++, usuarioDTO.getAutorizaPackingInv() != null ? usuarioDTO.getAutorizaPackingInv() : "N");
        stmt.setString(idx++, usuarioDTO.getAutorizaEntraPackingInv() != null ? usuarioDTO.getAutorizaEntraPackingInv() : "N");
        stmt.setString(idx++, usuarioDTO.getOrdenesMs() != null ? usuarioDTO.getOrdenesMs() : "N");
        stmt.setString(idx++, usuarioDTO.getCoordinaEntrevista() != null ? usuarioDTO.getCoordinaEntrevista() : "N");
        stmt.setString(idx++, usuarioDTO.getNdProcesaPagoCliente() != null ? usuarioDTO.getNdProcesaPagoCliente() : "N");
        stmt.setString(idx++, usuarioDTO.getFcCambiaPrecio() != null ? usuarioDTO.getFcCambiaPrecio() : "N");
        stmt.setString(idx++, usuarioDTO.getFcCambiaLprecio() != null ? usuarioDTO.getFcCambiaLprecio() : "N");
        stmt.setString(idx++, usuarioDTO.getFcMargenMinimo() != null ? usuarioDTO.getFcMargenMinimo() : "0");
        stmt.setString(idx++, usuarioDTO.getIndVerPreciosInventario() != null ? usuarioDTO.getIndVerPreciosInventario() : "N");
        stmt.setString(idx++, usuarioDTO.getFcRegistraNcSinFac() != null ? usuarioDTO.getFcRegistraNcSinFac() : "N");
        stmt.setDate(idx++, null); // fc_fecha_exp_nc_sin_fac
        stmt.setString(idx++, usuarioDTO.getVerImagenesPortal() != null ? usuarioDTO.getVerImagenesPortal() : "S");
        stmt.setString(idx++, usuarioDTO.getVerGraficasPortal() != null ? usuarioDTO.getVerGraficasPortal() : "S");
        stmt.setString(idx++, usuarioDTO.getCxcAnularRecibo() != null ? usuarioDTO.getCxcAnularRecibo() : "N");
        stmt.setString(idx++, usuarioDTO.getFcRegistraCostoDevolu() != null ? usuarioDTO.getFcRegistraCostoDevolu() : "N");
        stmt.setString(idx++, usuarioDTO.getCmModificaSolicitud() != null ? usuarioDTO.getCmModificaSolicitud() : "N");
        stmt.setString(idx++, usuarioDTO.getFcFacDistintaSucursal() != null ? usuarioDTO.getFcFacDistintaSucursal() : "N");
        stmt.setString(idx++, usuarioDTO.getFcCambiaPrecioServ() != null ? usuarioDTO.getFcCambiaPrecioServ() : "N");
        stmt.setString(idx++, usuarioDTO.getBaCierraConci() != null ? usuarioDTO.getBaCierraConci() : "N");
        stmt.setString(idx++, usuarioDTO.getBaReversaConci() != null ? usuarioDTO.getBaReversaConci() : "N");
        stmt.setString(idx++, usuarioDTO.getFcProcesarDevolucion() != null ? usuarioDTO.getFcProcesarDevolucion() : "N");
        stmt.setString(idx++, usuarioDTO.getAutorizaDescuentoLimite() != null ? usuarioDTO.getAutorizaDescuentoLimite() : "0");
        stmt.setString(idx++, usuarioDTO.getCoIndAutorizaCuentaContable() != null ? usuarioDTO.getCoIndAutorizaCuentaContable() : "N");
        stmt.setString(idx++, usuarioDTO.getFcRegistraConVendedor() != null ? usuarioDTO.getFcRegistraConVendedor() : "N");
        stmt.setString(idx++, usuarioDTO.getIndCajeroFi() != null ? usuarioDTO.getIndCajeroFi() : "N");
        stmt.setString(idx++, usuarioDTO.getIndCobradorFi() != null ? usuarioDTO.getIndCobradorFi() : "N");
        stmt.setString(idx++, usuarioDTO.getIndProcesaDocumentoFi() != null ? usuarioDTO.getIndProcesaDocumentoFi() : "N");
        stmt.setString(idx++, usuarioDTO.getIndTrasladoCuentas() != null ? usuarioDTO.getIndTrasladoCuentas() : "N");
        stmt.setString(idx++, usuarioDTO.getIndAccessAllFi() != null ? usuarioDTO.getIndAccessAllFi() : "N");
        stmt.setString(idx++, usuarioDTO.getIndSupervisorFi() != null ? usuarioDTO.getIndSupervisorFi() : "N");
        stmt.setString(idx++, usuarioDTO.getIndAjMesContable() != null ? usuarioDTO.getIndAjMesContable() : "N");
        
        int filasAfectadas = stmt.executeUpdate();
          if (filasAfectadas > 0) {
          System.out.println("Usuario registrado exitosamente: " + usuarioDTO.getUsuario());
            usuarioInfo.put("success", true);
          usuarioInfo.put("mensaje", "Usuario registrado exitosamente");
          usuarioInfo.put("usuario", usuarioDTO.getUsuario());
          usuarioInfo.put("usuario_registrado", usuarioDTO.getUsuario()); // Campo específico para la actualización del contacto
          usuarioInfo.put("nombre_completo", usuarioDTO.getNomUsuario() + " " + (usuarioDTO.getApeUsuario() != null ? usuarioDTO.getApeUsuario() : ""));
          usuarioInfo.put("nombre", usuarioDTO.getNomUsuario());
          usuarioInfo.put("apellido", usuarioDTO.getApeUsuario());
          usuarioInfo.put("email", usuarioDTO.getEmail());
          usuarioInfo.put("activo", usuarioDTO.getIndActivo());
          usuarioInfo.put("es_cliente", usuarioDTO.getIndCliente());
          usuarioInfo.put("es_cajero", usuarioDTO.getIndCajero() != null ? usuarioDTO.getIndCajero() : "N");
          usuarioInfo.put("es_cobrador", usuarioDTO.getIndCobrador() != null ? usuarioDTO.getIndCobrador() : "N");
          usuarioInfo.put("es_vendedor", usuarioDTO.getIndVendedor() != null ? usuarioDTO.getIndVendedor() : "N");
          usuarioInfo.put("es_supervisor", usuarioDTO.getIndSupervisor() != null ? usuarioDTO.getIndSupervisor() : "N");
          usuarioInfo.put("puede_crear_clientes", usuarioDTO.getCreaCliente() != null ? usuarioDTO.getCreaCliente() : "N");
          usuarioInfo.put("autoriza_descuentos", usuarioDTO.getAutorizaDescuento() != null ? usuarioDTO.getAutorizaDescuento() : "N");
          usuarioInfo.put("acceso_portal_imagenes", usuarioDTO.getVerImagenesPortal() != null ? usuarioDTO.getVerImagenesPortal() : "S");
          usuarioInfo.put("acceso_portal_graficas", usuarioDTO.getVerGraficasPortal() != null ? usuarioDTO.getVerGraficasPortal() : "S");
          usuarioInfo.put("fecha_creacion", new java.util.Date().toString());
          usuarioInfo.put("origen_registro", "PORTAL_WEB");
          
          // Información de permisos especiales
          Map<String, Object> permisos = new HashMap<>();
          permisos.put("modifica_precios", usuarioDTO.getFcCambiaPrecio() != null ? usuarioDTO.getFcCambiaPrecio() : "N");
          permisos.put("autoriza_compras", usuarioDTO.getAutorizaCompra() != null ? usuarioDTO.getAutorizaCompra() : "N");
          permisos.put("monto_autorizado", usuarioDTO.getMontoAutoriza() != null ? usuarioDTO.getMontoAutoriza() : "0.00");
          permisos.put("procesa_documentos_cxc", usuarioDTO.getCxcProcesaDocumento() != null ? usuarioDTO.getCxcProcesaDocumento() : "N");
          permisos.put("procesa_documentos_cxp", usuarioDTO.getCxpProcesaDocumento() != null ? usuarioDTO.getCxpProcesaDocumento() : "N");
          permisos.put("ve_precios_inventario", usuarioDTO.getIndVerPreciosInventario() != null ? usuarioDTO.getIndVerPreciosInventario() : "N");
          usuarioInfo.put("permisos_especiales", permisos);
          
        } else {
          usuarioInfo.put("success", false);
          usuarioInfo.put("mensaje", "No se pudo registrar el usuario");
        }
      }
      
    } catch (Exception e) {
      System.err.println("Error al registrar usuario: " + e.getMessage());
      e.printStackTrace();
      usuarioInfo.put("success", false);
      usuarioInfo.put("mensaje", "Error al registrar usuario: " + e.getMessage());
    }
    
    return usuarioInfo;
  }

  private String encriptarClave(String clave) {
    // Implementación simple de encriptación (puedes mejorarla según tus necesidades)
    // Por ahora retorna la clave en uppercase como en el servlet original
    return clave != null ? clave.toUpperCase() : "123456";
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
  }  private Map<String, Object> actualizarUsuarioWebContacto(int noCliente, int noContacto, String usuarioWeb, java.sql.Connection conn) {
    Map<String, Object> resultado = new HashMap<>();
    
    try {
      // Primero obtener los datos actuales del contacto
      String selectSql = "SELECT nombre, apellido, cod_cargo, direccion, direccion1, telefono, extension, fax, email, movil, pagina_web, sexo, fecha_nacimiento, usuario_aplicacion, activo FROM genweb_contacto_cliente WHERE no_cia = 1 AND no_cliente = ? AND no_contacto = ?";
      
      String nombre = "", apellido = "", codCargo = "", direccion = "", direccion1 = "";
      String telefono = "", extension = "", fax = "", email = "", movil = "";
      String paginaWeb = "", sexo = "", fechaNacimiento = "", usuarioAplicacion = "", activo = "S";
      
      try (java.sql.PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
        selectStmt.setInt(1, noCliente);
        selectStmt.setInt(2, noContacto);
        
        java.sql.ResultSet rs = selectStmt.executeQuery();
        if (rs.next()) {
          nombre = rs.getString("nombre") != null ? rs.getString("nombre") : "";
          apellido = rs.getString("apellido") != null ? rs.getString("apellido") : "";
          codCargo = rs.getString("cod_cargo") != null ? rs.getString("cod_cargo") : "";
          direccion = rs.getString("direccion") != null ? rs.getString("direccion") : "";
          direccion1 = rs.getString("direccion1") != null ? rs.getString("direccion1") : "";
          telefono = rs.getString("telefono") != null ? rs.getString("telefono") : "";
          extension = rs.getString("extension") != null ? rs.getString("extension") : "";
          fax = rs.getString("fax") != null ? rs.getString("fax") : "";
          email = rs.getString("email") != null ? rs.getString("email") : "";
          movil = rs.getString("movil") != null ? rs.getString("movil") : "";
          paginaWeb = rs.getString("pagina_web") != null ? rs.getString("pagina_web") : "";
          sexo = rs.getString("sexo") != null ? rs.getString("sexo") : "";
          fechaNacimiento = rs.getString("fecha_nacimiento") != null ? rs.getString("fecha_nacimiento") : "";
          usuarioAplicacion = rs.getString("usuario_aplicacion") != null ? rs.getString("usuario_aplicacion") : "PORTAL_ADMIN";
          activo = rs.getString("activo") != null ? rs.getString("activo") : "S";
        }
      }
      
      // Ahora actualizar el contacto con el procedimiento almacenado CONTACTO usando operación UPDATE
      String sql = "{call CONTACTO(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
      
      try (java.sql.CallableStatement stmt = conn.prepareCall(sql)) {
        int idx = 1;
        
        System.out.println("DEBUG: Actualizando contacto con usuario web: " + usuarioWeb);
        System.out.println("  - NO_CLIENTE: " + noCliente);
        System.out.println("  - NO_CONTACTO: " + noContacto);
        
        // CIA INT(5) - Compañía
        stmt.setInt(idx++, 1);
        
        // PARAMETRO1 VARCHAR(50) - NO_CLIENTE  
        stmt.setString(idx++, String.valueOf(noCliente));
        
        // PARAMETRO2 VARCHAR(50) - NO_CONTACTO (para UPDATE)
        stmt.setString(idx++, String.valueOf(noContacto));
        
        // PARAMETRO3 VARCHAR(50) - NOMBRE
        stmt.setString(idx++, nombre);
        
        // PARAMETRO4 VARCHAR(50) - APELLIDO
        stmt.setString(idx++, apellido);
        
        // PARAMETRO5 VARCHAR(50) - COD_CARGO
        stmt.setString(idx++, codCargo);
        
        // PARAMETRO6 VARCHAR(200) - DIRECCION
        stmt.setString(idx++, direccion);
        
        // PARAMETRO7 VARCHAR(200) - DIRECCION1
        stmt.setString(idx++, direccion1);
        
        // PARAMETRO8 VARCHAR(50) - TELEFONO
        stmt.setString(idx++, telefono);
        
        // PARAMETRO9 VARCHAR(50) - EXTENSION
        stmt.setString(idx++, extension);
        
        // PARAMETRO10 VARCHAR(50) - FAX
        stmt.setString(idx++, fax);
        
        // PARAMETRO11 VARCHAR(50) - EMAIL
        stmt.setString(idx++, email);
        
        // PARAMETRO12 VARCHAR(50) - MOVIL
        stmt.setString(idx++, movil);
        
        // PARAMETRO13 VARCHAR(50) - PAGINA_WEB
        stmt.setString(idx++, paginaWeb);
        
        // PARAMETRO14 VARCHAR(50) - (No se usa en UPDATE)
        stmt.setString(idx++, "");
        
        // PARAMETRO15 VARCHAR(50) - SEXO
        stmt.setString(idx++, sexo);
        
        // PARAMETRO16 VARCHAR(50) - FECHA_NACIMIENTO
        stmt.setString(idx++, fechaNacimiento);
        
        // PARAMETRO17 VARCHAR(50) - USUARIO_APLICACION
        stmt.setString(idx++, usuarioAplicacion);
        
        // INDICADOR VARCHAR(1) - "U" para UPDATE
        stmt.setString(idx++, "U");
        
        // PACTIVO VARCHAR(1) - Estado activo
        stmt.setString(idx++, activo);
        
        // PREPLICAR VARCHAR(1) - "N" para no replicar
        stmt.setString(idx++, "N");
        
        // PACCESO_WEB VARCHAR(1) - Acceso web
        stmt.setString(idx++, "S");
        
        // PUSUARIO_WEB VARCHAR(50) - Usuario web (ESTE ES EL CAMPO QUE QUEREMOS ACTUALIZAR)
        stmt.setString(idx++, usuarioWeb);
        
        // PUSUARIO_HELPDESK VARCHAR(50) - Usuario helpdesk
        stmt.setString(idx++, "PORTAL_ADMIN");
        
        // PACCESO_HELPDESK VARCHAR(1) - Acceso helpdesk
        stmt.setString(idx++, "N");
        
        // V_SECUENCIAL OUT - Parámetro de salida
        stmt.registerOutParameter(idx, java.sql.Types.INTEGER);
        
        System.out.println("DEBUG: Ejecutando UPDATE del contacto...");
        stmt.execute();
        int contactoActualizado = stmt.getInt(idx);
        
        System.out.println("DEBUG: Contacto actualizado exitosamente. Secuencial: " + contactoActualizado);
        
        resultado.put("success", true);
        resultado.put("mensaje", "Usuario web actualizado en contacto exitosamente");
        resultado.put("secuencial_contacto", contactoActualizado);
        resultado.put("usuario_web_asignado", usuarioWeb);
        resultado.put("no_cliente", noCliente);
        resultado.put("no_contacto", noContacto);
        
      }
    } catch (Exception e) {
      System.err.println("Error al actualizar usuario web en contacto: " + e.getMessage());
      e.printStackTrace();
      resultado.put("success", false);
      resultado.put("mensaje", "Error al actualizar usuario web en contacto: " + e.getMessage());
    }    
    return resultado;
  }
}