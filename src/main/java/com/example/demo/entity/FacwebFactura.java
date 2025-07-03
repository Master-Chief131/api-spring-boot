package com.example.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "facweb_factura")
@IdClass(FacwebFacturaId.class)
public class FacwebFactura {

    @Id
    @Column(name = "no_cia", nullable = false)
    private Integer noCia;

    @Id
    @Column(name = "no_factura", nullable = false)
    private Integer noFactura;

    @Column(name = "no_sucursal", nullable = false)
    private Integer noSucursal;

    @Column(name = "no_bodega", nullable = false)
    private Integer noBodega;

    @Column(name = "no_prefactura")
    private Integer noPrefactura;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "no_vendedor")
    private Integer noVendedor;

    @Column(name = "no_cliente")
    private Integer noCliente;

    @Column(name = "no_sucursal_cliente")
    private Integer noSucursalCliente;

    @Column(name = "TIPO_FACTURA", length = 3)
    private String tipoFactura;

    @Column(name = "PDESCUENTO", precision = 15, scale = 4)
    private BigDecimal pDescuento;

    @Column(name = "DESCUENTO_NOMINAL", precision = 15, scale = 4)
    private BigDecimal descuentoNominal;

    @Column(name = "SUBTOTAL_NOMINAL", precision = 15, scale = 4)
    private BigDecimal subtotalNominal;

    @Column(name = "IMPUESTO_NOMINAL", precision = 15, scale = 4)
    private BigDecimal impuestoNominal;

    @Column(name = "TOTAL_NOMINAL", precision = 15, scale = 4)
    private BigDecimal totalNominal;

    @Column(name = "DESCUENTO_DOLAR", precision = 15, scale = 4)
    private BigDecimal descuentoDolar;

    @Column(name = "SUBTOTAL_DOLAR", precision = 15, scale = 4)
    private BigDecimal subtotalDolar;

    @Column(name = "IMPUESTO_DOLAR", precision = 15, scale = 4)
    private BigDecimal impuestoDolar;

    @Column(name = "TOTAL_DOLAR", precision = 15, scale = 4)
    private BigDecimal totalDolar;

    @Column(name = "TIPO_CAMBIO")
    private Integer tipoCambio;

    @Column(name = "CODIGO_MONEDA")
    private Integer codigoMoneda;

    @Column(name = "FECHA_CAMBIO")
    private LocalDate fechaCambio;

    @Column(name = "USUARIO_CREO", length = 50)
    private String usuarioCreo;

    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;

    @Column(name = "STATUS", length = 1)
    private String status;

    @Column(name = "USUARIO_PREFACTURA", length = 50)
    private String usuarioPrefactura;

    @Column(name = "FECHA_PREFACTURA")
    private LocalDateTime fechaPrefactura;

    @Column(name = "USUARIO_FACTURO", length = 50)
    private String usuarioFacturo;

    @Column(name = "FECHA_FACTURO")
    private LocalDateTime fechaFacturo;

    @Column(name = "fecha_electronica_facturo")
    private LocalDateTime fechaElectronicaFacturo;

    @Column(name = "OBSERVACION", length = 500)
    private String observacion;

    @Column(name = "no_orden_compra", length = 100)
    private String noOrdenCompra;

    @Column(name = "USUARIO_ANULO", length = 50)
    private String usuarioAnulo;

    @Column(name = "FECHA_ANULO")
    private LocalDateTime fechaAnulo;

    @Column(name = "no_solicitud")
    private Integer noSolicitud;

    @Column(name = "numeroserieequipofiscal", length = 45)
    private String numeroSerieEquipoFiscal;

    @Column(name = "consecutivofiscal", length = 45)
    private String consecutivoFiscal;

    @Column(name = "fechadocumentofiscal", length = 45)
    private String fechaDocumentoFiscal;

    @Column(name = "horadocumentofiscal", length = 45)
    private String horaDocumentoFiscal;

    @Column(name = "subtotal", precision = 15, scale = 4)
    private BigDecimal subtotal;

    @Column(name = "totalitbms", precision = 15, scale = 4)
    private BigDecimal totalItbms;

    @Column(name = "totaldescuento", precision = 15, scale = 4)
    private BigDecimal totalDescuento;

    @Column(name = "grantotal", precision = 15, scale = 4)
    private BigDecimal granTotal;

    @Column(name = "estatus_fiscal", length = 45)
    private String estatusFiscal;

    @Column(name = "no_plazo")
    private Integer noPlazo;

    @Column(name = "nombre_cliente", length = 100)
    private String nombreCliente;

    @Column(name = "ruc_cedula", length = 25)
    private String rucCedula;

    @Column(name = "ind_cliente_contado", length = 1)
    private String indClienteContado;

    @Column(name = "tipo_descuento", length = 1)
    private String tipoDescuento;

    @Column(name = "no_cotizacion")
    private Integer noCotizacion;

    @Column(name = "revisado_actividad", length = 1)
    private String revisadoActividad = "N";

    @Column(name = "estado_despacho", length = 1)
    private String estadoDespacho;

    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;

    @Column(name = "bloqueado_cxc", columnDefinition = "TINYINT(1)")
    private Boolean bloqueadoCxc = false;

    @Column(name = "no_docu_bloqueado")
    private Integer noDocuBloqueado;

    @Column(name = "ind_localidad", length = 1)
    private String indLocalidad = "L";

    @Column(name = "grupo", length = 2)
    private String grupo;

    @Column(name = "no_razon")
    private Integer noRazon;

    @Column(name = "con_articulos", length = 1)
    private String conArticulos = "N";

    @Column(name = "ano")
    private Integer ano;

    @Column(name = "mes")
    private Integer mes;

    @Column(name = "con_devolucion", length = 1)
    private String conDevolucion = "N";

    @Column(name = "no_devolucion")
    private Integer noDevolucion;

    @Column(name = "tipo_devolucion", length = 2)
    private String tipoDevolucion;

    @Column(name = "para_merma", length = 1)
    private String paraMerma = "N";

    @Column(name = "cufe", length = 300)
    private String cufe;

    @Column(name = "con_factura_electronica", length = 1)
    private String conFacturaElectronica = "S";

    @Column(name = "fe_generada", length = 1)
    private String feGenerada = "N";

    @Column(name = "msj_factura_electronica", columnDefinition = "TEXT")
    private String msjFacturaElectronica;

    @Column(name = "tipo_venta")
    private Integer tipoVenta;

    @Column(name = "cliente_telefono", length = 50)
    private String clienteTelefono;

    @Column(name = "cliente_direccion", length = 300)
    private String clienteDireccion;

    @Column(name = "no_referido")
    private Integer noReferido;

    @Column(name = "nombre_referido", length = 300)
    private String nombreReferido;

    @Column(name = "tipo_gen_fiscal", length = 2)
    private String tipoGenFiscal;

    @Column(name = "intentos_envio")
    private Integer intentosEnvio;

    @Column(name = "para_enviar_fe", columnDefinition = "TINYINT(1)")
    private Boolean paraEnviarFe = false;

    @Column(name = "cafe_pdf", columnDefinition = "MEDIUMTEXT")
    private String cafePdf;

    @Column(name = "nombre_cafe", length = 300)
    private String nombreCafe;

    @Column(name = "no_sucursal_venta")
    private Integer noSucursalVenta;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "dv", length = 2)
    private String dv;

    @Column(name = "no_factura_ex")
    private Integer noFacturaEx = 0;

    @Column(name = "no_cia_cli")
    private Integer noCiaCli = 0;

    @Column(name = "inter_cia", length = 1)
    private String interCia = "N";

    @Column(name = "compania_inter_cia")
    private Integer companiaInterCia;

    @Column(name = "sucursal_inter_cia")
    private Integer sucursalInterCia;

    // Constructores
    public FacwebFactura() {}

    // Getters y Setters
    public Integer getNoCia() {
        return noCia;
    }

    public void setNoCia(Integer noCia) {
        this.noCia = noCia;
    }

    public Integer getNoFactura() {
        return noFactura;
    }

    public void setNoFactura(Integer noFactura) {
        this.noFactura = noFactura;
    }

    public Integer getNoSucursal() {
        return noSucursal;
    }

    public void setNoSucursal(Integer noSucursal) {
        this.noSucursal = noSucursal;
    }

    public Integer getNoBodega() {
        return noBodega;
    }

    public void setNoBodega(Integer noBodega) {
        this.noBodega = noBodega;
    }

    public Integer getNoPrefactura() {
        return noPrefactura;
    }

    public void setNoPrefactura(Integer noPrefactura) {
        this.noPrefactura = noPrefactura;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getNoVendedor() {
        return noVendedor;
    }

    public void setNoVendedor(Integer noVendedor) {
        this.noVendedor = noVendedor;
    }

    public Integer getNoCliente() {
        return noCliente;
    }

    public void setNoCliente(Integer noCliente) {
        this.noCliente = noCliente;
    }

    public Integer getNoSucursalCliente() {
        return noSucursalCliente;
    }

    public void setNoSucursalCliente(Integer noSucursalCliente) {
        this.noSucursalCliente = noSucursalCliente;
    }

    public String getTipoFactura() {
        return tipoFactura;
    }

    public void setTipoFactura(String tipoFactura) {
        this.tipoFactura = tipoFactura;
    }

    public BigDecimal getpDescuento() {
        return pDescuento;
    }

    public void setpDescuento(BigDecimal pDescuento) {
        this.pDescuento = pDescuento;
    }

    public BigDecimal getDescuentoNominal() {
        return descuentoNominal;
    }

    public void setDescuentoNominal(BigDecimal descuentoNominal) {
        this.descuentoNominal = descuentoNominal;
    }

    public BigDecimal getSubtotalNominal() {
        return subtotalNominal;
    }

    public void setSubtotalNominal(BigDecimal subtotalNominal) {
        this.subtotalNominal = subtotalNominal;
    }

    public BigDecimal getImpuestoNominal() {
        return impuestoNominal;
    }

    public void setImpuestoNominal(BigDecimal impuestoNominal) {
        this.impuestoNominal = impuestoNominal;
    }

    public BigDecimal getTotalNominal() {
        return totalNominal;
    }

    public void setTotalNominal(BigDecimal totalNominal) {
        this.totalNominal = totalNominal;
    }

    public BigDecimal getDescuentoDolar() {
        return descuentoDolar;
    }

    public void setDescuentoDolar(BigDecimal descuentoDolar) {
        this.descuentoDolar = descuentoDolar;
    }

    public BigDecimal getSubtotalDolar() {
        return subtotalDolar;
    }

    public void setSubtotalDolar(BigDecimal subtotalDolar) {
        this.subtotalDolar = subtotalDolar;
    }

    public BigDecimal getImpuestoDolar() {
        return impuestoDolar;
    }

    public void setImpuestoDolar(BigDecimal impuestoDolar) {
        this.impuestoDolar = impuestoDolar;
    }

    public BigDecimal getTotalDolar() {
        return totalDolar;
    }

    public void setTotalDolar(BigDecimal totalDolar) {
        this.totalDolar = totalDolar;
    }

    public Integer getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(Integer tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public Integer getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(Integer codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public LocalDate getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(LocalDate fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public String getUsuarioCreo() {
        return usuarioCreo;
    }

    public void setUsuarioCreo(String usuarioCreo) {
        this.usuarioCreo = usuarioCreo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsuarioPrefactura() {
        return usuarioPrefactura;
    }

    public void setUsuarioPrefactura(String usuarioPrefactura) {
        this.usuarioPrefactura = usuarioPrefactura;
    }

    public LocalDateTime getFechaPrefactura() {
        return fechaPrefactura;
    }

    public void setFechaPrefactura(LocalDateTime fechaPrefactura) {
        this.fechaPrefactura = fechaPrefactura;
    }

    public String getUsuarioFacturo() {
        return usuarioFacturo;
    }

    public void setUsuarioFacturo(String usuarioFacturo) {
        this.usuarioFacturo = usuarioFacturo;
    }

    public LocalDateTime getFechaFacturo() {
        return fechaFacturo;
    }

    public void setFechaFacturo(LocalDateTime fechaFacturo) {
        this.fechaFacturo = fechaFacturo;
    }

    public LocalDateTime getFechaElectronicaFacturo() {
        return fechaElectronicaFacturo;
    }

    public void setFechaElectronicaFacturo(LocalDateTime fechaElectronicaFacturo) {
        this.fechaElectronicaFacturo = fechaElectronicaFacturo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getNoOrdenCompra() {
        return noOrdenCompra;
    }

    public void setNoOrdenCompra(String noOrdenCompra) {
        this.noOrdenCompra = noOrdenCompra;
    }

    public String getUsuarioAnulo() {
        return usuarioAnulo;
    }

    public void setUsuarioAnulo(String usuarioAnulo) {
        this.usuarioAnulo = usuarioAnulo;
    }

    public LocalDateTime getFechaAnulo() {
        return fechaAnulo;
    }

    public void setFechaAnulo(LocalDateTime fechaAnulo) {
        this.fechaAnulo = fechaAnulo;
    }

    public Integer getNoSolicitud() {
        return noSolicitud;
    }

    public void setNoSolicitud(Integer noSolicitud) {
        this.noSolicitud = noSolicitud;
    }

    public String getNumeroSerieEquipoFiscal() {
        return numeroSerieEquipoFiscal;
    }

    public void setNumeroSerieEquipoFiscal(String numeroSerieEquipoFiscal) {
        this.numeroSerieEquipoFiscal = numeroSerieEquipoFiscal;
    }

    public String getConsecutivoFiscal() {
        return consecutivoFiscal;
    }

    public void setConsecutivoFiscal(String consecutivoFiscal) {
        this.consecutivoFiscal = consecutivoFiscal;
    }

    public String getFechaDocumentoFiscal() {
        return fechaDocumentoFiscal;
    }

    public void setFechaDocumentoFiscal(String fechaDocumentoFiscal) {
        this.fechaDocumentoFiscal = fechaDocumentoFiscal;
    }

    public String getHoraDocumentoFiscal() {
        return horaDocumentoFiscal;
    }

    public void setHoraDocumentoFiscal(String horaDocumentoFiscal) {
        this.horaDocumentoFiscal = horaDocumentoFiscal;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotalItbms() {
        return totalItbms;
    }

    public void setTotalItbms(BigDecimal totalItbms) {
        this.totalItbms = totalItbms;
    }

    public BigDecimal getTotalDescuento() {
        return totalDescuento;
    }

    public void setTotalDescuento(BigDecimal totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    public BigDecimal getGranTotal() {
        return granTotal;
    }

    public void setGranTotal(BigDecimal granTotal) {
        this.granTotal = granTotal;
    }

    public String getEstatusFiscal() {
        return estatusFiscal;
    }

    public void setEstatusFiscal(String estatusFiscal) {
        this.estatusFiscal = estatusFiscal;
    }

    public Integer getNoPlazo() {
        return noPlazo;
    }

    public void setNoPlazo(Integer noPlazo) {
        this.noPlazo = noPlazo;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getRucCedula() {
        return rucCedula;
    }

    public void setRucCedula(String rucCedula) {
        this.rucCedula = rucCedula;
    }

    public String getIndClienteContado() {
        return indClienteContado;
    }

    public void setIndClienteContado(String indClienteContado) {
        this.indClienteContado = indClienteContado;
    }

    public String getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTipoDescuento(String tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    public Integer getNoCotizacion() {
        return noCotizacion;
    }

    public void setNoCotizacion(Integer noCotizacion) {
        this.noCotizacion = noCotizacion;
    }

    public String getRevisadoActividad() {
        return revisadoActividad;
    }

    public void setRevisadoActividad(String revisadoActividad) {
        this.revisadoActividad = revisadoActividad;
    }

    public String getEstadoDespacho() {
        return estadoDespacho;
    }

    public void setEstadoDespacho(String estadoDespacho) {
        this.estadoDespacho = estadoDespacho;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Boolean getBloqueadoCxc() {
        return bloqueadoCxc;
    }

    public void setBloqueadoCxc(Boolean bloqueadoCxc) {
        this.bloqueadoCxc = bloqueadoCxc;
    }

    public Integer getNoDocuBloqueado() {
        return noDocuBloqueado;
    }

    public void setNoDocuBloqueado(Integer noDocuBloqueado) {
        this.noDocuBloqueado = noDocuBloqueado;
    }

    public String getIndLocalidad() {
        return indLocalidad;
    }

    public void setIndLocalidad(String indLocalidad) {
        this.indLocalidad = indLocalidad;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Integer getNoRazon() {
        return noRazon;
    }

    public void setNoRazon(Integer noRazon) {
        this.noRazon = noRazon;
    }

    public String getConArticulos() {
        return conArticulos;
    }

    public void setConArticulos(String conArticulos) {
        this.conArticulos = conArticulos;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public String getConDevolucion() {
        return conDevolucion;
    }

    public void setConDevolucion(String conDevolucion) {
        this.conDevolucion = conDevolucion;
    }

    public Integer getNoDevolucion() {
        return noDevolucion;
    }

    public void setNoDevolucion(Integer noDevolucion) {
        this.noDevolucion = noDevolucion;
    }

    public String getTipoDevolucion() {
        return tipoDevolucion;
    }

    public void setTipoDevolucion(String tipoDevolucion) {
        this.tipoDevolucion = tipoDevolucion;
    }

    public String getParaMerma() {
        return paraMerma;
    }

    public void setParaMerma(String paraMerma) {
        this.paraMerma = paraMerma;
    }

    public String getCufe() {
        return cufe;
    }

    public void setCufe(String cufe) {
        this.cufe = cufe;
    }

    public String getConFacturaElectronica() {
        return conFacturaElectronica;
    }

    public void setConFacturaElectronica(String conFacturaElectronica) {
        this.conFacturaElectronica = conFacturaElectronica;
    }

    public String getFeGenerada() {
        return feGenerada;
    }

    public void setFeGenerada(String feGenerada) {
        this.feGenerada = feGenerada;
    }

    public String getMsjFacturaElectronica() {
        return msjFacturaElectronica;
    }

    public void setMsjFacturaElectronica(String msjFacturaElectronica) {
        this.msjFacturaElectronica = msjFacturaElectronica;
    }

    public Integer getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(Integer tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public String getClienteTelefono() {
        return clienteTelefono;
    }

    public void setClienteTelefono(String clienteTelefono) {
        this.clienteTelefono = clienteTelefono;
    }

    public String getClienteDireccion() {
        return clienteDireccion;
    }

    public void setClienteDireccion(String clienteDireccion) {
        this.clienteDireccion = clienteDireccion;
    }

    public Integer getNoReferido() {
        return noReferido;
    }

    public void setNoReferido(Integer noReferido) {
        this.noReferido = noReferido;
    }

    public String getNombreReferido() {
        return nombreReferido;
    }

    public void setNombreReferido(String nombreReferido) {
        this.nombreReferido = nombreReferido;
    }

    public String getTipoGenFiscal() {
        return tipoGenFiscal;
    }

    public void setTipoGenFiscal(String tipoGenFiscal) {
        this.tipoGenFiscal = tipoGenFiscal;
    }

    public Integer getIntentosEnvio() {
        return intentosEnvio;
    }

    public void setIntentosEnvio(Integer intentosEnvio) {
        this.intentosEnvio = intentosEnvio;
    }

    public Boolean getParaEnviarFe() {
        return paraEnviarFe;
    }

    public void setParaEnviarFe(Boolean paraEnviarFe) {
        this.paraEnviarFe = paraEnviarFe;
    }

    public String getCafePdf() {
        return cafePdf;
    }

    public void setCafePdf(String cafePdf) {
        this.cafePdf = cafePdf;
    }

    public String getNombreCafe() {
        return nombreCafe;
    }

    public void setNombreCafe(String nombreCafe) {
        this.nombreCafe = nombreCafe;
    }

    public Integer getNoSucursalVenta() {
        return noSucursalVenta;
    }

    public void setNoSucursalVenta(Integer noSucursalVenta) {
        this.noSucursalVenta = noSucursalVenta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public Integer getNoFacturaEx() {
        return noFacturaEx;
    }

    public void setNoFacturaEx(Integer noFacturaEx) {
        this.noFacturaEx = noFacturaEx;
    }

    public Integer getNoCiaCli() {
        return noCiaCli;
    }

    public void setNoCiaCli(Integer noCiaCli) {
        this.noCiaCli = noCiaCli;
    }

    public String getInterCia() {
        return interCia;
    }

    public void setInterCia(String interCia) {
        this.interCia = interCia;
    }

    public Integer getCompaniaInterCia() {
        return companiaInterCia;
    }

    public void setCompaniaInterCia(Integer companiaInterCia) {
        this.companiaInterCia = companiaInterCia;
    }

    public Integer getSucursalInterCia() {
        return sucursalInterCia;
    }

    public void setSucursalInterCia(Integer sucursalInterCia) {
        this.sucursalInterCia = sucursalInterCia;
    }
}
