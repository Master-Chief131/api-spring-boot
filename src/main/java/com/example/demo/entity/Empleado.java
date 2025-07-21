package com.example.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Entidad que representa la tabla pl_empleado
 */
@Entity
@Table(name = "pl_empleado")
@IdClass(EmpleadoId.class)
public class Empleado {
    
    @Id
    @Column(name = "no_cia", nullable = false)
    private Integer noCia;
    
    @Id
    @Column(name = "cod_pla", nullable = false, length = 2)
    private String codPla;
    
    @Id
    @Column(name = "no_emple", nullable = false, length = 6)
    private String noEmple;
    
    @Column(name = "nombre", nullable = false, length = 103)
    private String nombre;
    
    @Column(name = "cedula", nullable = false, length = 15)
    private String cedula;
    
    @Column(name = "asegu", length = 15)
    private String asegu;
    
    @Column(name = "nacion", length = 15)
    private String nacion;
    
    @Column(name = "direccion", length = 50)
    private String direccion;
    
    @Column(name = "apartado", length = 20)
    private String apartado;
    
    @Column(name = "telefono", length = 10)
    private String telefono;
    
    @Column(name = "e_civil", length = 1)
    private String eCivil;
    
    @Column(name = "sexo", length = 1)
    private String sexo;
    
    @Column(name = "clase", length = 1)
    private String clase;
    
    @Column(name = "dependi")
    private Integer dependi;
    
    @Column(name = "puesto", nullable = false, length = 4)
    private String puesto;
    
    @Column(name = "depto", nullable = false, length = 5)
    private String depto;
    
    @Column(name = "seccion", length = 2)
    private String seccion;
    
    @Column(name = "tipo_emp", nullable = false, length = 2)
    private String tipoEmp;
    
    @Column(name = "sangre", length = 4)
    private String sangre;
    
    @Column(name = "estado", length = 2)
    private String estado;
    
    @Column(name = "pago", length = 1)
    private String pago;
    
    @Column(name = "licencia", length = 2)
    private String licencia;
    
    @Column(name = "f_nacimi")
    @Temporal(TemporalType.DATE)
    private Date fNacimi;
    
    @Column(name = "f_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fIngreso;
    
    @Column(name = "f_egreso")
    @Temporal(TemporalType.DATE)
    private Date fEgreso;
    
    @Column(name = "f_ultaum")
    @Temporal(TemporalType.DATE)
    private Date fUltaum;
    
    @Column(name = "f_ultvac")
    @Temporal(TemporalType.DATE)
    private Date fUltvac;
    
    @Column(name = "f_ultact")
    @Temporal(TemporalType.DATE)
    private Date fUltact;
    
    @Column(name = "f_vence")
    @Temporal(TemporalType.DATE)
    private Date fVence;
    
    @Column(name = "base", precision = 10, scale = 2)
    private BigDecimal base;
    
    @Column(name = "salario", precision = 10, scale = 5)
    private BigDecimal salario;
    
    @Column(name = "aumento", precision = 10, scale = 2)
    private BigDecimal aumento;
    
    @Column(name = "per_inic")
    private Integer perInic;
    
    @Column(name = "coop", length = 2)
    private String coop;
    
    @Column(name = "sind", length = 2)
    private String sind;
    
    @Column(name = "tarjeta", length = 1)
    private String tarjeta;
    
    @Column(name = "educa", length = 30)
    private String educa;
    
    @Column(name = "horas", precision = 5, scale = 2)
    private BigDecimal horas;
    
    @Column(name = "semana", precision = 5, scale = 2)
    private BigDecimal semana;
    
    @Column(name = "cuenta", length = 18)
    private String cuenta;
    
    @Column(name = "t_cuenta", length = 1)
    private String tCuenta;
    
    @Column(name = "flag", length = 1)
    private String flag;
    
    @Column(name = "sala", length = 1)
    private String sala;
    
    @Column(name = "cheque", length = 8)
    private String cheque;
    
    @Column(name = "dias_vac", precision = 5, scale = 2)
    private BigDecimal diasVac;
    
    @Column(name = "salant", precision = 10, scale = 5)
    private BigDecimal salant;
    
    @Column(name = "f_carnet")
    @Temporal(TemporalType.DATE)
    private Date fCarnet;
    
    @Column(name = "foto", length = 300)
    private String foto;
    
    @Column(name = "secuencia", length = 8)
    private String secuencia;
    
    @Column(name = "motivo", length = 30)
    private String motivo;
    
    @Column(name = "no_sec", length = 2)
    private String noSec;
    
    @Column(name = "paga_licencia", length = 1)
    private String pagaLicencia;
    
    @Column(name = "cd_banco", length = 3)
    private String cdBanco;
    
    @Column(name = "sindicalizado", length = 1)
    private String sindicalizado;
    
    @Column(name = "sal_grep", precision = 10, scale = 2)
    private BigDecimal salGrep;
    
    @Column(name = "email", length = 40)
    private String email;
    
    @Column(name = "tel_ext_cia", length = 8)
    private String telExtCia;
    
    @Column(name = "fax_ext_cia", length = 8)
    private String faxExtCia;
    
    @Column(name = "no_emple_old")
    private Integer noEmpleOld;
    
    @Column(name = "celular", length = 10)
    private String celular;
    
    @Column(name = "gasrep", precision = 10, scale = 2)
    private BigDecimal gasrep;
    
    @Column(name = "gastra", precision = 10, scale = 2)
    private BigDecimal gastra;
    
    @Column(name = "gasgas", precision = 10, scale = 2)
    private BigDecimal gasgas;
    
    @Column(name = "colseg", length = 1)
    private String colseg;
    
    @Column(name = "telefono2", length = 10)
    private String telefono2;
    
    @Column(name = "nombre_ant", length = 30)
    private String nombreAnt;
    
    @Column(name = "horario_corrido_sabado", length = 1)
    private String horarioCorridoSabado;
    
    @Column(name = "enviar_a_proafili", length = 1)
    private String enviarAProafili;
    
    @Column(name = "grupo", length = 2)
    private String grupo;
    
    @Column(name = "no_cliente")
    private Integer noCliente;
    
    @Column(name = "area", length = 2)
    private String area;
    
    @Column(name = "no_unid", length = 2)
    private String noUnid;
    
    @Column(name = "centro_costo", length = 9)
    private String centroCosto;
    
    @Column(name = "nivel", length = 6)
    private String nivel;
    
    @Column(name = "sub_nivel", length = 2)
    private String subNivel;
    
    @Column(name = "ult_acc", length = 8)
    private String ultAcc;
    
    @Column(name = "fecha_traslado")
    @Temporal(TemporalType.DATE)
    private Date fechaTraslado;
    
    @Column(name = "formato", length = 30)
    private String formato;
    
    @Column(name = "digito_verificador", length = 2)
    private String digitoVerificador;
    
    @Column(name = "cedula_anexo", length = 18)
    private String cedulaAnexo;
    
    @Column(name = "tipo_id_tributario", length = 2)
    private String tipoIdTributario;
    
    @Column(name = "tipo_id_tributario_pre", length = 2)
    private String tipoIdTributarioPre;
    
    @Column(name = "declara", length = 1)
    private String declara;
    
    @Column(name = "horas_extras", length = 1)
    private String horasExtras;
    
    @Column(name = "codigo_horario", length = 6)
    private String codigoHorario;
    
    @Column(name = "codigo_horario_sabado", length = 6)
    private String codigoHorarioSabado;
    
    @Column(name = "cat_evaluacion", length = 4)
    private String catEvaluacion;
    
    @Column(name = "maneja_dist_centro", length = 1)
    private String manejaDistCentro;
    
    @Column(name = "user_crea", length = 40)
    private String userCrea;
    
    @Column(name = "fecha_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCrea;
    
    @Column(name = "user_modi", length = 40)
    private String userModi;
    
    @Column(name = "fecha_modi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModi;
    
    @Column(name = "tipo_renta", length = 10)
    private String tipoRenta;
    
    @Column(name = "vendedor", length = 1)
    private String vendedor;
    
    @Column(name = "nombre1", length = 25)
    private String nombre1;
    
    @Column(name = "nombre2", length = 25)
    private String nombre2;
    
    @Column(name = "apellido1", length = 25)
    private String apellido1;
    
    @Column(name = "apellido2", length = 25)
    private String apellido2;
    
    @Column(name = "no_pais")
    private Integer noPais;
    
    @Column(name = "no_provincia")
    private Integer noProvincia;
    
    @Column(name = "no_distrito")
    private Integer noDistrito;
    
    @Column(name = "no_corregimiento")
    private Integer noCorregimiento;
    
    @Column(name = "ruta_foto", length = 150)
    private String rutaFoto;
    
    @Column(name = "usuario", length = 50)
    private String usuario;
    
    @Column(name = "pa_generado", length = 1)
    private String paGenerado;
    
    @Column(name = "sms_comp", length = 1)
    private String smsComp;
    
    @Column(name = "correo_comp", length = 1)
    private String correoComp;
    
    @Column(name = "representante_cia", length = 1)
    private String representanteCia;
    
    @Column(name = "ind_supervisor", length = 1)
    private String indSupervisor;
    
    @Column(name = "latitud", length = 20)
    private String latitud;
    
    @Column(name = "longitud", length = 20)
    private String longitud;
    
    @Column(name = "codigo_reloj", length = 20)
    private String codigoReloj;

    // Constructor por defecto
    public Empleado() {}

    // Getters y Setters
    public Integer getNoCia() {
        return noCia;
    }

    public void setNoCia(Integer noCia) {
        this.noCia = noCia;
    }

    public String getCodPla() {
        return codPla;
    }

    public void setCodPla(String codPla) {
        this.codPla = codPla;
    }

    public String getNoEmple() {
        return noEmple;
    }

    public void setNoEmple(String noEmple) {
        this.noEmple = noEmple;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getAsegu() {
        return asegu;
    }

    public void setAsegu(String asegu) {
        this.asegu = asegu;
    }

    public String getNacion() {
        return nacion;
    }

    public void setNacion(String nacion) {
        this.nacion = nacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getApartado() {
        return apartado;
    }

    public void setApartado(String apartado) {
        this.apartado = apartado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getECivil() {
        return eCivil;
    }

    public void setECivil(String eCivil) {
        this.eCivil = eCivil;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public Integer getDependi() {
        return dependi;
    }

    public void setDependi(Integer dependi) {
        this.dependi = dependi;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getDepto() {
        return depto;
    }

    public void setDepto(String depto) {
        this.depto = depto;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getTipoEmp() {
        return tipoEmp;
    }

    public void setTipoEmp(String tipoEmp) {
        this.tipoEmp = tipoEmp;
    }

    public String getSangre() {
        return sangre;
    }

    public void setSangre(String sangre) {
        this.sangre = sangre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public Date getFNacimi() {
        return fNacimi;
    }

    public void setFNacimi(Date fNacimi) {
        this.fNacimi = fNacimi;
    }

    public Date getFIngreso() {
        return fIngreso;
    }

    public void setFIngreso(Date fIngreso) {
        this.fIngreso = fIngreso;
    }

    public Date getFEgreso() {
        return fEgreso;
    }

    public void setFEgreso(Date fEgreso) {
        this.fEgreso = fEgreso;
    }

    public Date getFUltaum() {
        return fUltaum;
    }

    public void setFUltaum(Date fUltaum) {
        this.fUltaum = fUltaum;
    }

    public Date getFUltvac() {
        return fUltvac;
    }

    public void setFUltvac(Date fUltvac) {
        this.fUltvac = fUltvac;
    }

    public Date getFUltact() {
        return fUltact;
    }

    public void setFUltact(Date fUltact) {
        this.fUltact = fUltact;
    }

    public Date getFVence() {
        return fVence;
    }

    public void setFVence(Date fVence) {
        this.fVence = fVence;
    }

    public BigDecimal getBase() {
        return base;
    }

    public void setBase(BigDecimal base) {
        this.base = base;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public BigDecimal getAumento() {
        return aumento;
    }

    public void setAumento(BigDecimal aumento) {
        this.aumento = aumento;
    }

    public Integer getPerInic() {
        return perInic;
    }

    public void setPerInic(Integer perInic) {
        this.perInic = perInic;
    }

    public String getCoop() {
        return coop;
    }

    public void setCoop(String coop) {
        this.coop = coop;
    }

    public String getSind() {
        return sind;
    }

    public void setSind(String sind) {
        this.sind = sind;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getEduca() {
        return educa;
    }

    public void setEduca(String educa) {
        this.educa = educa;
    }

    public BigDecimal getHoras() {
        return horas;
    }

    public void setHoras(BigDecimal horas) {
        this.horas = horas;
    }

    public BigDecimal getSemana() {
        return semana;
    }

    public void setSemana(BigDecimal semana) {
        this.semana = semana;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getTCuenta() {
        return tCuenta;
    }

    public void setTCuenta(String tCuenta) {
        this.tCuenta = tCuenta;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public BigDecimal getDiasVac() {
        return diasVac;
    }

    public void setDiasVac(BigDecimal diasVac) {
        this.diasVac = diasVac;
    }

    public BigDecimal getSalant() {
        return salant;
    }

    public void setSalant(BigDecimal salant) {
        this.salant = salant;
    }

    public Date getFCarnet() {
        return fCarnet;
    }

    public void setFCarnet(Date fCarnet) {
        this.fCarnet = fCarnet;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getNoSec() {
        return noSec;
    }

    public void setNoSec(String noSec) {
        this.noSec = noSec;
    }

    public String getPagaLicencia() {
        return pagaLicencia;
    }

    public void setPagaLicencia(String pagaLicencia) {
        this.pagaLicencia = pagaLicencia;
    }

    public String getCdBanco() {
        return cdBanco;
    }

    public void setCdBanco(String cdBanco) {
        this.cdBanco = cdBanco;
    }

    public String getSindicalizado() {
        return sindicalizado;
    }

    public void setSindicalizado(String sindicalizado) {
        this.sindicalizado = sindicalizado;
    }

    public BigDecimal getSalGrep() {
        return salGrep;
    }

    public void setSalGrep(BigDecimal salGrep) {
        this.salGrep = salGrep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelExtCia() {
        return telExtCia;
    }

    public void setTelExtCia(String telExtCia) {
        this.telExtCia = telExtCia;
    }

    public String getFaxExtCia() {
        return faxExtCia;
    }

    public void setFaxExtCia(String faxExtCia) {
        this.faxExtCia = faxExtCia;
    }

    public Integer getNoEmpleOld() {
        return noEmpleOld;
    }

    public void setNoEmpleOld(Integer noEmpleOld) {
        this.noEmpleOld = noEmpleOld;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public BigDecimal getGasrep() {
        return gasrep;
    }

    public void setGasrep(BigDecimal gasrep) {
        this.gasrep = gasrep;
    }

    public BigDecimal getGastra() {
        return gastra;
    }

    public void setGastra(BigDecimal gastra) {
        this.gastra = gastra;
    }

    public BigDecimal getGasgas() {
        return gasgas;
    }

    public void setGasgas(BigDecimal gasgas) {
        this.gasgas = gasgas;
    }

    public String getColseg() {
        return colseg;
    }

    public void setColseg(String colseg) {
        this.colseg = colseg;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getNombreAnt() {
        return nombreAnt;
    }

    public void setNombreAnt(String nombreAnt) {
        this.nombreAnt = nombreAnt;
    }

    public String getHorarioCorridoSabado() {
        return horarioCorridoSabado;
    }

    public void setHorarioCorridoSabado(String horarioCorridoSabado) {
        this.horarioCorridoSabado = horarioCorridoSabado;
    }

    public String getEnviarAProafili() {
        return enviarAProafili;
    }

    public void setEnviarAProafili(String enviarAProafili) {
        this.enviarAProafili = enviarAProafili;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Integer getNoCliente() {
        return noCliente;
    }

    public void setNoCliente(Integer noCliente) {
        this.noCliente = noCliente;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNoUnid() {
        return noUnid;
    }

    public void setNoUnid(String noUnid) {
        this.noUnid = noUnid;
    }

    public String getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(String centroCosto) {
        this.centroCosto = centroCosto;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getSubNivel() {
        return subNivel;
    }

    public void setSubNivel(String subNivel) {
        this.subNivel = subNivel;
    }

    public String getUltAcc() {
        return ultAcc;
    }

    public void setUltAcc(String ultAcc) {
        this.ultAcc = ultAcc;
    }

    public Date getFechaTraslado() {
        return fechaTraslado;
    }

    public void setFechaTraslado(Date fechaTraslado) {
        this.fechaTraslado = fechaTraslado;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getDigitoVerificador() {
        return digitoVerificador;
    }

    public void setDigitoVerificador(String digitoVerificador) {
        this.digitoVerificador = digitoVerificador;
    }

    public String getCedulaAnexo() {
        return cedulaAnexo;
    }

    public void setCedulaAnexo(String cedulaAnexo) {
        this.cedulaAnexo = cedulaAnexo;
    }

    public String getTipoIdTributario() {
        return tipoIdTributario;
    }

    public void setTipoIdTributario(String tipoIdTributario) {
        this.tipoIdTributario = tipoIdTributario;
    }

    public String getTipoIdTributarioPre() {
        return tipoIdTributarioPre;
    }

    public void setTipoIdTributarioPre(String tipoIdTributarioPre) {
        this.tipoIdTributarioPre = tipoIdTributarioPre;
    }

    public String getDeclara() {
        return declara;
    }

    public void setDeclara(String declara) {
        this.declara = declara;
    }

    public String getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(String horasExtras) {
        this.horasExtras = horasExtras;
    }

    public String getCodigoHorario() {
        return codigoHorario;
    }

    public void setCodigoHorario(String codigoHorario) {
        this.codigoHorario = codigoHorario;
    }

    public String getCodigoHorarioSabado() {
        return codigoHorarioSabado;
    }

    public void setCodigoHorarioSabado(String codigoHorarioSabado) {
        this.codigoHorarioSabado = codigoHorarioSabado;
    }

    public String getCatEvaluacion() {
        return catEvaluacion;
    }

    public void setCatEvaluacion(String catEvaluacion) {
        this.catEvaluacion = catEvaluacion;
    }

    public String getManejaDistCentro() {
        return manejaDistCentro;
    }

    public void setManejaDistCentro(String manejaDistCentro) {
        this.manejaDistCentro = manejaDistCentro;
    }

    public String getUserCrea() {
        return userCrea;
    }

    public void setUserCrea(String userCrea) {
        this.userCrea = userCrea;
    }

    public Date getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(Date fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    public String getUserModi() {
        return userModi;
    }

    public void setUserModi(String userModi) {
        this.userModi = userModi;
    }

    public Date getFechaModi() {
        return fechaModi;
    }

    public void setFechaModi(Date fechaModi) {
        this.fechaModi = fechaModi;
    }

    public String getTipoRenta() {
        return tipoRenta;
    }

    public void setTipoRenta(String tipoRenta) {
        this.tipoRenta = tipoRenta;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Integer getNoPais() {
        return noPais;
    }

    public void setNoPais(Integer noPais) {
        this.noPais = noPais;
    }

    public Integer getNoProvincia() {
        return noProvincia;
    }

    public void setNoProvincia(Integer noProvincia) {
        this.noProvincia = noProvincia;
    }

    public Integer getNoDistrito() {
        return noDistrito;
    }

    public void setNoDistrito(Integer noDistrito) {
        this.noDistrito = noDistrito;
    }

    public Integer getNoCorregimiento() {
        return noCorregimiento;
    }

    public void setNoCorregimiento(Integer noCorregimiento) {
        this.noCorregimiento = noCorregimiento;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPaGenerado() {
        return paGenerado;
    }

    public void setPaGenerado(String paGenerado) {
        this.paGenerado = paGenerado;
    }

    public String getSmsComp() {
        return smsComp;
    }

    public void setSmsComp(String smsComp) {
        this.smsComp = smsComp;
    }

    public String getCorreoComp() {
        return correoComp;
    }

    public void setCorreoComp(String correoComp) {
        this.correoComp = correoComp;
    }

    public String getRepresentanteCia() {
        return representanteCia;
    }

    public void setRepresentanteCia(String representanteCia) {
        this.representanteCia = representanteCia;
    }

    public String getIndSupervisor() {
        return indSupervisor;
    }

    public void setIndSupervisor(String indSupervisor) {
        this.indSupervisor = indSupervisor;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getCodigoReloj() {
        return codigoReloj;
    }

    public void setCodigoReloj(String codigoReloj) {
        this.codigoReloj = codigoReloj;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "noCia=" + noCia +
                ", codPla='" + codPla + '\'' +
                ", noEmple='" + noEmple + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
