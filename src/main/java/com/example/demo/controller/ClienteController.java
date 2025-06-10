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
            + "?, ?)}"; // 66-67(agregados dos más)
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

      // DEBUG: Mostrar el JSON recibido
      System.out.println("DEBUG: JSON recibido en registrarDesdePortal: " + new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(cliente));
      // Validación y log de parámetros críticos
      System.out.println("DEBUG: noProvincia recibido en request: " + cliente.getNoProvincia());
      if (cliente.getNoProvincia() == 0) {
        System.out.println("ADVERTENCIA: noProvincia es 0, probablemente no se envió o llegó vacío");
      }

      // Mostrar en consola los valores enviados
      System.out.println("Valores enviados al procedimiento CLIENTE:");
      valoresEnviados.forEach((k, v) -> System.out.println("Parametro " + k + ": " + v));      // Ejecutar
      stmt.execute();      int vSecuencial = stmt.getInt(48); // OUT param en la posición 48
        // Registrar contacto automáticamente después del cliente exitoso
      Map<String, Object> contactoInfo = registrarContacto(cliente, vSecuencial);
      
      // Registrar usuario automáticamente después del contacto exitoso
      Map<String, Object> usuarioInfo = registrarUsuario(cliente, vSecuencial);
      
      response.put("success", true);
      response.put("mensaje", "Cliente registrado exitosamente. Código generado: " + vSecuencial);
      response.put("V_SECUENCIAL", vSecuencial);
      response.put("contacto_registrado", contactoInfo);
      response.put("usuario_registrado", usuarioInfo);
      response.put("valores_enviados", valoresEnviados);
    } catch (Exception e) {
      response.put("success", false);
      response.put("mensaje", e.getMessage());
      response.put("valores_enviados", valoresEnviados);
    }
    return response;  }
  private Map<String, Object> registrarContacto(Cliente cliente, int vSecuencial) {
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
        contactoDTO.setDireccion(cliente.getDireccion());
        contactoDTO.setCedula(cliente.getRucCedula());
        contactoDTO.setIndPrincipal("S");
        contactoDTO.setIndEstado("A");
      } else { // Persona Jurídica
        // Para persona jurídica, usar el objeto contacto enviado en el JSON
        contactoDTO = cliente.getContacto();
        if (contactoDTO != null) {
          contactoDTO.setNoCia(cliente.getId().getNoCia());
          contactoDTO.setNoCliente(vSecuencial);
          contactoDTO.setGrupo(cliente.getId().getGrupo());
          contactoDTO.setIndPrincipal("S");
          contactoDTO.setIndEstado("A");
        }
      }
        if (contactoDTO != null) {
        // Llamar al procedimiento almacenado CONTACTO
        String sql = "{call CONTACTO(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        try (java.sql.Connection conn = dataSource.getConnection();
             java.sql.CallableStatement stmt = conn.prepareCall(sql)) {
          
          int idx = 1;
          // Parámetros del procedimiento CONTACTO según definición del SP
          
          // CIA INT(5)
          stmt.setInt(idx++, contactoDTO.getNoCia() != null ? contactoDTO.getNoCia() : 0);
          
          // PARAMETRO1-17 (VARCHAR)
          stmt.setString(idx++, contactoDTO.getNoCliente() != null ? contactoDTO.getNoCliente().toString() : "");
          stmt.setString(idx++, contactoDTO.getGrupo());
          stmt.setString(idx++, contactoDTO.getNombre());
          stmt.setString(idx++, contactoDTO.getApellido());
          stmt.setString(idx++, contactoDTO.getTelefono());
          stmt.setString(idx++, contactoDTO.getEmail());
          stmt.setString(idx++, contactoDTO.getDireccion());
          stmt.setString(idx++, contactoDTO.getCedula());
          stmt.setString(idx++, contactoDTO.getMovil());
          stmt.setString(idx++, contactoDTO.getCargo());
          stmt.setString(idx++, contactoDTO.getDepartamento());
          stmt.setString(idx++, contactoDTO.getTelefonoOficina());
          stmt.setString(idx++, contactoDTO.getExtension());
          stmt.setString(idx++, contactoDTO.getFax());
          stmt.setString(idx++, contactoDTO.getObservaciones());
          stmt.setString(idx++, contactoDTO.getHorarioTrabajo());
          stmt.setString(idx++, contactoDTO.getRedes());
          
          // INDICADOR VARCHAR(1)
          stmt.setString(idx++, "I");
          
          // PACTIVO VARCHAR(1)
          stmt.setString(idx++, contactoDTO.getIndEstado());
          
          // PREPLICAR VARCHAR(1)
          stmt.setString(idx++, "N");
          
          // PACCESO_WEB VARCHAR(1)
          stmt.setString(idx++, "S");
          
          // PUSUARIO_WEB VARCHAR(50)
          stmt.setString(idx++, contactoDTO.getUsuarioCreacion());
          
          // PUSUARIO_HELPDESK VARCHAR(50)
          stmt.setString(idx++, contactoDTO.getUsuarioCreacion());
          
          // PACCESO_HELPDESK VARCHAR(1)
          stmt.setString(idx++, "N");
          
          // V_SECUENCIAL OUT
          stmt.registerOutParameter(idx, java.sql.Types.INTEGER);
          
          stmt.execute();
          int contactoSecuencial = stmt.getInt(idx);
          System.out.println("Contacto registrado exitosamente con código: " + contactoSecuencial);
            // Agregar información del contacto registrado a la respuesta
          contactoInfo.put("success", true);
          contactoInfo.put("secuencial_contacto", contactoSecuencial);
          contactoInfo.put("mensaje", "Contacto registrado exitosamente");
          contactoInfo.put("tipo_persona", "N".equals(cliente.getPersonaNj()) ? "Persona Natural" : "Persona Jurídica");
          contactoInfo.put("nombre", contactoDTO.getNombre());
          contactoInfo.put("apellido", contactoDTO.getApellido());
          contactoInfo.put("telefono", contactoDTO.getTelefono());
          contactoInfo.put("email", contactoDTO.getEmail());
          contactoInfo.put("cedula", contactoDTO.getCedula());
          contactoInfo.put("es_principal", contactoDTO.getIndPrincipal());
          contactoInfo.put("estado", contactoDTO.getIndEstado());
          
        }
      } else {
        contactoInfo.put("success", false);
        contactoInfo.put("mensaje", "No se pudo crear el objeto contacto");
      }
    } catch (Exception e) {
      System.err.println("Error al registrar contacto: " + e.getMessage());
      e.printStackTrace();
      contactoInfo.put("success", false);
      contactoInfo.put("mensaje", "Error al registrar contacto: " + e.getMessage());
    }
      return contactoInfo;
  }

  private Map<String, Object> registrarUsuario(Cliente cliente, int vSecuencial) {
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
      try (java.sql.Connection conn = dataSource.getConnection();
           java.sql.PreparedStatement checkStmt = conn.prepareStatement(checkUserSql)) {
        
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
        "ind_access_all_fi, ind_supervisor_fi, ind_aj_mes_contable) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      
      try (java.sql.Connection conn = dataSource.getConnection();
           java.sql.PreparedStatement stmt = conn.prepareStatement(insertSql)) {
        
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
  }
}