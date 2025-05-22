package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "genweb_clientes")
public class Cliente {
    @EmbeddedId
    private ClienteId id;

    @Column(name = "NOMBRE", length = 60)
    private String nombre;

    @Column(name = "DIRECCION", length = 200)
    private String direccion;

    @Column(name = "TELEFONO", length = 30)
    private String telefono;

    @Column(name = "EMAIL1", length = 200)
    private String email1;

    @Column(name = "FAX", length = 30)
    private String fax;

    @Column(name = "PAGINA_WEB", length = 100)
    private String paginaWeb;

    @Column(name = "NO_ACTIVIDAD")
    private Integer noActividad;

    @Column(name = "NO_TIPO_EMPRESA")
    private Integer noTipoEmpresa;

    @Column(name = "APARTADO", length = 20)
    private String apartado;

    @Column(name = "NO_PAIS")
    private int noPais;

    @Column(name = "TIPO_SEGUIMIENTO", length = 1)
    private String tipoSeguimiento;

    @Column(name = "NO_EMPLEADO", length = 50)
    private String noEmpleado;

    @Column(name = "NO_ORIGEN")
    private Integer noOrigen;

    @Column(name = "NOMBRE_ORIGEN", length = 50)
    private String nombreOrigen;

    @Column(name = "FECHA_CREACION")
    private java.time.LocalDate fechaCreacion;

    @Column(name = "USUARIO_APLICACION", length = 50)
    private String usuarioAplicacion;

    @Column(name = "NO_LINEA", precision = 9, scale = 0)
    private BigDecimal noLinea;

    @Column(name = "CAPACIDAD_NEGOCIO", length = 1)
    private String capacidadNegocio;

    @Column(name = "CIUDAD", length = 6)
    private String ciudad;

    @Column(name = "TIPO_CLIENTE")
    private Integer tipoCliente;

    @Column(name = "PROCESADO", length = 1)
    private String procesado;

    @Column(name = "SEGMENTO_MERCADEO", length = 2)
    private String segmentoMercadeo;

    @Column(name = "IDIOMA", length = 1)
    private String idioma;

    @Column(name = "USUARIO_REASIGNADO", length = 50)
    private String usuarioReasignado;

    @Column(name = "CENTRO_CONTABLE", length = 2)
    private String centroContable;

    @Column(name = "FECHA_CAMBIO_CLIENTE")
    private java.time.LocalDate fechaCambioCliente;

    @Column(name = "USUARIO_CAMBIO_CLIENTE", length = 10)
    private String usuarioCambioCliente;

    @Column(name = "NO_TIPO_DOCUMENTO", length = 6)
    private String noTipoDocumento;

    @Column(name = "NO_EMPRESA", precision = 9, scale = 0)
    private BigDecimal noEmpresa;

    @Column(name = "RUC_CEDULA", length = 25)
    private String rucCedula;

    @Column(name = "FECHA_CAMBIOS")
    private java.time.LocalDate fechaCambios;

    @Column(name = "PERSONA_NJ", length = 1)
    private String personaNj;

    @Column(name = "ETI", length = 2)
    private String eti;

    @Column(name = "EXCENTO_IMP", length = 1)
    private String excentoImp;

    @Column(name = "NOMBRE_CONT", length = 50)
    private String nombreCont;

    @Column(name = "APELLIDO_CONT", length = 50)
    private String apellidoCont;

    @Column(name = "F_CIERRE")
    private java.time.LocalDate fCierre;

    @Column(name = "ACTIVO", length = 1)
    private String activo;

    @Column(name = "TIPO_P", length = 2)
    private String tipoP;

    @Column(name = "NO_PROVINCIA")
    private int noProvincia;

    @Column(name = "NO_DISTRITO")
    private int noDistrito;

    @Column(name = "EXTENSION", length = 8)
    private String extension;

    @Column(name = "LIMITE_CREDI", precision = 17, scale = 2)
    private BigDecimal limiteCredi;

    @Column(name = "NO_CORREGIMIENTO")
    private Integer noCorregimiento;

    @Column(name = "FECHA_MODIFICACION")
    private java.time.LocalDateTime fechaModificacion;

    @Column(name = "USUARIO_CREACION", length = 50)
    private String usuarioCreacion;

    @Column(name = "USUARIO_MODIFICACION", length = 50)
    private String usuarioModificacion;

    @Column(name = "TELEFONO2", length = 30)
    private String telefono2;

    @Column(name = "PAGINA_WEB2", length = 100)
    private String paginaWeb2;

    @Column(name = "facebook", length = 200)
    private String facebook;

    @Column(name = "twitter", length = 200)
    private String twitter;

    @Column(name = "instagram", length = 200)
    private String instagram;

    @Column(name = "DISTRIBUIDOR", length = 1)
    private String distribuidor;

    @Column(name = "REFERIDO", length = 100)
    private String referido;

    @Column(name = "INSTRUCCION_ESPECIAL", length = 2000)
    private String instruccionEspecial;

    @Column(name = "EXTENSION2", length = 8)
    private String extension2;

    @Column(name = "SALDO", precision = 17, scale = 2)
    private BigDecimal saldo;

    @Column(name = "DISPONIBLE", precision = 17, scale = 2)
    private BigDecimal disponible;

    @Column(name = "MOVIL", length = 50)
    private String movil;

    @Column(name = "DV", length = 25)
    private String dv;

    @Column(name = "FECHA_NACIMIENTO")
    private java.time.LocalDate fechaNacimiento;

    @Column(name = "NO_GRUPO_MERCADO")
    private Integer noGrupoMercado;

    @Column(name = "ACCESO_WEB", length = 1)
    private String accesoWeb;

    @Column(name = "USUARIO_WEB", length = 50)
    private String usuarioWeb;

    @Column(name = "NO_PLAZO")
    private Integer noPlazo;

    @Column(name = "fecha_ultima_compra")
    private java.time.LocalDateTime fechaUltimaCompra;

    @Column(name = "IND_CLIENTE_CONTADO", length = 1)
    private String indClienteContado;

    @Column(name = "MODULO_ORIGEN", length = 3)
    private String moduloOrigen;

    @Column(name = "LATITUD", length = 20)
    private String latitud;

    @Column(name = "LONGITUD", length = 20)
    private String longitud;

    @Column(name = "TOLERANCIA")
    private Integer tolerancia;

    @Column(name = "RUTA", length = 1)
    private String ruta;

    @Column(name = "IND_SOPORTE", length = 1)
    private String indSoporte;

    @Column(name = "CLIENTE_HELP_DESK", length = 1)
    private String clienteHelpDesk;

    @Column(name = "ESTADO_PROMO", length = 1)
    private String estadoPromo;

    @Column(name = "COD_RUTA", length = 15)
    private String codRuta;

    @Column(name = "ind_sucursal", length = 1)
    private String indSucursal;

    @Column(name = "skype", length = 100)
    private String skype;

    @Column(name = "NO_CLIENTE_ANT", length = 6)
    private String noClienteAnt;

    @Column(name = "CODIGO_TERCERO")
    private Integer codigoTercero;

    @Column(name = "ind_sexo", length = 1)
    private String indSexo;

    @Column(name = "validar_morosidad_contado", length = 1)
    private String validarMorosidadContado;

    @Column(name = "no_cia_cli")
    private Integer noCiaCli;

    @Column(name = "id_cliente", length = 20)
    private String idCliente;

    @Column(name = "numero_cliente")
    private Integer numeroCliente;

    @Column(name = "compania_inter_cia")
    private Integer companiaInterCia;

    // Getters y setters generados para todos los campos
    public ClienteId getId() { return id; }
    public void setId(ClienteId id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getEmail1() { return email1; }
    public void setEmail1(String email1) { this.email1 = email1; }
    public String getFax() { return fax; }
    public void setFax(String fax) { this.fax = fax; }
    public String getPaginaWeb() { return paginaWeb; }
    public void setPaginaWeb(String paginaWeb) { this.paginaWeb = paginaWeb; }
    public Integer getNoActividad() { return noActividad; }
    public void setNoActividad(Integer noActividad) { this.noActividad = noActividad; }
    public Integer getNoTipoEmpresa() { return noTipoEmpresa; }
    public void setNoTipoEmpresa(Integer noTipoEmpresa) { this.noTipoEmpresa = noTipoEmpresa; }
    public String getApartado() { return apartado; }
    public void setApartado(String apartado) { this.apartado = apartado; }
    public int getNoPais() { return noPais; }
    public void setNoPais(int noPais) { this.noPais = noPais; }
    public String getTipoSeguimiento() { return tipoSeguimiento; }
    public void setTipoSeguimiento(String tipoSeguimiento) { this.tipoSeguimiento = tipoSeguimiento; }
    public String getNoEmpleado() { return noEmpleado; }
    public void setNoEmpleado(String noEmpleado) { this.noEmpleado = noEmpleado; }
    public Integer getNoOrigen() { return noOrigen; }
    public void setNoOrigen(Integer noOrigen) { this.noOrigen = noOrigen; }
    public String getNombreOrigen() { return nombreOrigen; }
    public void setNombreOrigen(String nombreOrigen) { this.nombreOrigen = nombreOrigen; }
    public java.time.LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(java.time.LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public String getUsuarioAplicacion() { return usuarioAplicacion; }
    public void setUsuarioAplicacion(String usuarioAplicacion) { this.usuarioAplicacion = usuarioAplicacion; }
    public BigDecimal getNoLinea() { return noLinea; }
    public void setNoLinea(BigDecimal noLinea) { this.noLinea = noLinea; }
    public String getCapacidadNegocio() { return capacidadNegocio; }
    public void setCapacidadNegocio(String capacidadNegocio) { this.capacidadNegocio = capacidadNegocio; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public Integer getTipoCliente() { return tipoCliente; }
    public void setTipoCliente(Integer tipoCliente) { this.tipoCliente = tipoCliente; }
    public String getProcesado() { return procesado; }
    public void setProcesado(String procesado) { this.procesado = procesado; }
    public String getSegmentoMercadeo() { return segmentoMercadeo; }
    public void setSegmentoMercadeo(String segmentoMercadeo) { this.segmentoMercadeo = segmentoMercadeo; }
    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    public String getUsuarioReasignado() { return usuarioReasignado; }
    public void setUsuarioReasignado(String usuarioReasignado) { this.usuarioReasignado = usuarioReasignado; }
    public String getCentroContable() { return centroContable; }
    public void setCentroContable(String centroContable) { this.centroContable = centroContable; }
    public java.time.LocalDate getFechaCambioCliente() { return fechaCambioCliente; }
    public void setFechaCambioCliente(java.time.LocalDate fechaCambioCliente) { this.fechaCambioCliente = fechaCambioCliente; }
    public String getUsuarioCambioCliente() { return usuarioCambioCliente; }
    public void setUsuarioCambioCliente(String usuarioCambioCliente) { this.usuarioCambioCliente = usuarioCambioCliente; }
    public String getNoTipoDocumento() { return noTipoDocumento; }
    public void setNoTipoDocumento(String noTipoDocumento) { this.noTipoDocumento = noTipoDocumento; }
    public BigDecimal getNoEmpresa() { return noEmpresa; }
    public void setNoEmpresa(BigDecimal noEmpresa) { this.noEmpresa = noEmpresa; }
    public String getRucCedula() { return rucCedula; }
    public void setRucCedula(String rucCedula) { this.rucCedula = rucCedula; }
    public java.time.LocalDate getFechaCambios() { return fechaCambios; }
    public void setFechaCambios(java.time.LocalDate fechaCambios) { this.fechaCambios = fechaCambios; }
    public String getPersonaNj() { return personaNj; }
    public void setPersonaNj(String personaNj) { this.personaNj = personaNj; }
    public String getEti() { return eti; }
    public void setEti(String eti) { this.eti = eti; }
    public String getExcentoImp() { return excentoImp; }
    public void setExcentoImp(String excentoImp) { this.excentoImp = excentoImp; }
    public String getNombreCont() { return nombreCont; }
    public void setNombreCont(String nombreCont) { this.nombreCont = nombreCont; }
    public String getApellidoCont() { return apellidoCont; }
    public void setApellidoCont(String apellidoCont) { this.apellidoCont = apellidoCont; }
    public java.time.LocalDate getFCierre() { return fCierre; }
    public void setFCierre(java.time.LocalDate fCierre) { this.fCierre = fCierre; }
    public String getActivo() { return activo; }
    public void setActivo(String activo) { this.activo = activo; }
    public String getTipoP() { return tipoP; }
    public void setTipoP(String tipoP) { this.tipoP = tipoP; }
    public int getNoProvincia() { return noProvincia; }
    public void setNoProvincia(int noProvincia) { this.noProvincia = noProvincia; }
    public int getNoDistrito() { return noDistrito; }
    public void setNoDistrito(int noDistrito) { this.noDistrito = noDistrito; }
    public String getExtension() { return extension; }
    public void setExtension(String extension) { this.extension = extension; }
    public BigDecimal getLimiteCredi() { return limiteCredi; }
    public void setLimiteCredi(BigDecimal limiteCredi) { this.limiteCredi = limiteCredi; }
    public Integer getNoCorregimiento() { return noCorregimiento; }
    public void setNoCorregimiento(Integer noCorregimiento) { this.noCorregimiento = noCorregimiento; }
    public java.time.LocalDateTime getFechaModificacion() { return fechaModificacion; }
    public void setFechaModificacion(java.time.LocalDateTime fechaModificacion) { this.fechaModificacion = fechaModificacion; }
    public String getUsuarioCreacion() { return usuarioCreacion; }
    public void setUsuarioCreacion(String usuarioCreacion) { this.usuarioCreacion = usuarioCreacion; }
    public String getUsuarioModificacion() { return usuarioModificacion; }
    public void setUsuarioModificacion(String usuarioModificacion) { this.usuarioModificacion = usuarioModificacion; }
    public String getTelefono2() { return telefono2; }
    public void setTelefono2(String telefono2) { this.telefono2 = telefono2; }
    public String getPaginaWeb2() { return paginaWeb2; }
    public void setPaginaWeb2(String paginaWeb2) { this.paginaWeb2 = paginaWeb2; }
    public String getFacebook() { return facebook; }
    public void setFacebook(String facebook) { this.facebook = facebook; }
    public String getTwitter() { return twitter; }
    public void setTwitter(String twitter) { this.twitter = twitter; }
    public String getInstagram() { return instagram; }
    public void setInstagram(String instagram) { this.instagram = instagram; }
    public String getDistribuidor() { return distribuidor; }
    public void setDistribuidor(String distribuidor) { this.distribuidor = distribuidor; }
    public String getReferido() { return referido; }
    public void setReferido(String referido) { this.referido = referido; }
    public String getInstruccionEspecial() { return instruccionEspecial; }
    public void setInstruccionEspecial(String instruccionEspecial) { this.instruccionEspecial = instruccionEspecial; }
    public String getExtension2() { return extension2; }
    public void setExtension2(String extension2) { this.extension2 = extension2; }
    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }
    public BigDecimal getDisponible() { return disponible; }
    public void setDisponible(BigDecimal disponible) { this.disponible = disponible; }
    public String getMovil() { return movil; }
    public void setMovil(String movil) { this.movil = movil; }
    public String getDv() { return dv; }
    public void setDv(String dv) { this.dv = dv; }
    public java.time.LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(java.time.LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public Integer getNoGrupoMercado() { return noGrupoMercado; }
    public void setNoGrupoMercado(Integer noGrupoMercado) { this.noGrupoMercado = noGrupoMercado; }
    public String getAccesoWeb() { return accesoWeb; }
    public void setAccesoWeb(String accesoWeb) { this.accesoWeb = accesoWeb; }
    public String getUsuarioWeb() { return usuarioWeb; }
    public void setUsuarioWeb(String usuarioWeb) { this.usuarioWeb = usuarioWeb; }
    public Integer getNoPlazo() { return noPlazo; }
    public void setNoPlazo(Integer noPlazo) { this.noPlazo = noPlazo; }
    public java.time.LocalDateTime getFechaUltimaCompra() { return fechaUltimaCompra; }
    public void setFechaUltimaCompra(java.time.LocalDateTime fechaUltimaCompra) { this.fechaUltimaCompra = fechaUltimaCompra; }
    public String getIndClienteContado() { return indClienteContado; }
    public void setIndClienteContado(String indClienteContado) { this.indClienteContado = indClienteContado; }
    public String getModuloOrigen() { return moduloOrigen; }
    public void setModuloOrigen(String moduloOrigen) { this.moduloOrigen = moduloOrigen; }
    public String getLatitud() { return latitud; }
    public void setLatitud(String latitud) { this.latitud = latitud; }
    public String getLongitud() { return longitud; }
    public void setLongitud(String longitud) { this.longitud = longitud; }
    public Integer getTolerancia() { return tolerancia; }
    public void setTolerancia(Integer tolerancia) { this.tolerancia = tolerancia; }
    public String getRuta() { return ruta; }
    public void setRuta(String ruta) { this.ruta = ruta; }
    public String getIndSoporte() { return indSoporte; }
    public void setIndSoporte(String indSoporte) { this.indSoporte = indSoporte; }
    public String getClienteHelpDesk() { return clienteHelpDesk; }
    public void setClienteHelpDesk(String clienteHelpDesk) { this.clienteHelpDesk = clienteHelpDesk; }
    public String getEstadoPromo() { return estadoPromo; }
    public void setEstadoPromo(String estadoPromo) { this.estadoPromo = estadoPromo; }
    public String getCodRuta() { return codRuta; }
    public void setCodRuta(String codRuta) { this.codRuta = codRuta; }
    public String getIndSucursal() { return indSucursal; }
    public void setIndSucursal(String indSucursal) { this.indSucursal = indSucursal; }
    public String getSkype() { return skype; }
    public void setSkype(String skype) { this.skype = skype; }
    public String getNoClienteAnt() { return noClienteAnt; }
    public void setNoClienteAnt(String noClienteAnt) { this.noClienteAnt = noClienteAnt; }
    public Integer getCodigoTercero() { return codigoTercero; }
    public void setCodigoTercero(Integer codigoTercero) { this.codigoTercero = codigoTercero; }
    public String getIndSexo() { return indSexo; }
    public void setIndSexo(String indSexo) { this.indSexo = indSexo; }
    public String getValidarMorosidadContado() { return validarMorosidadContado; }
    public void setValidarMorosidadContado(String validarMorosidadContado) { this.validarMorosidadContado = validarMorosidadContado; }
    public Integer getNoCiaCli() { return noCiaCli; }
    public void setNoCiaCli(Integer noCiaCli) { this.noCiaCli = noCiaCli; }
    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }
    public Integer getNumeroCliente() { return numeroCliente; }
    public void setNumeroCliente(Integer numeroCliente) { this.numeroCliente = numeroCliente; }
    public Integer getCompaniaInterCia() { return companiaInterCia; }
    public void setCompaniaInterCia(Integer companiaInterCia) { this.companiaInterCia = companiaInterCia; }
}