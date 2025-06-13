package com.example.demo.dto;

public class UsuarioDTO {
    private String usuario;
    private String nomUsuario;
    private String apeUsuario;
    private String email;
    private String clave;
    private String indActivo;
    private String indCliente;
    private String indCajero;
    private String indCobrador;
    private String indVendedor;
    private String indSupervisor;
    private String autorizaDescuento;
    private String creaCliente;
    private String emiteFacturaMovil;
    private String supervisorGc;
    private String liderDepto;
    private String tipoUsuarioHelpdesk;
    private String tomaFisica;
    private String desreferenciarDoc;
    private String autorizaCompra;
    private String montoAutoriza;
    private String tomaFisicaInv;
    private String autorizaLotePagos;
    private String coRegistraMesCerrados;
    private String cxpRegistraMesCerrados;
    private String cxcRegistraMesCerrados;
    private String autorizaPrecio;
    private String autorizaGrpMercado;
    private String cxpProcesaDocumento;
    private String cxcProcesaDocumento;
    private String formulacionInv;
    private String autorizaPackingInv;
    private String autorizaEntraPackingInv;
    private String ordenesMs;
    private String coordinaEntrevista;
    private String ndProcesaPagoCliente;
    private String fcCambiaPrecio;
    private String fcCambiaLprecio;
    private String fcMargenMinimo;
    private String indVerPreciosInventario;
    private String fcRegistraNcSinFac;
    private String verImagenesPortal;
    private String verGraficasPortal;
    private String cxcAnularRecibo;
    private String fcRegistraCostoDevolu;
    private String cmModificaSolicitud;
    private String fcFacDistintaSucursal;
    private String fcCambiaPrecioServ;
    private String baCierraConci;
    private String baReversaConci;
    private String fcProcesarDevolucion;
    private String autorizaDescuentoLimite;
    private String coIndAutorizaCuentaContable;
    private String fcRegistraConVendedor;
    private String indCajeroFi;
    private String indCobradorFi;
    private String indProcesaDocumentoFi;
    private String indTrasladoCuentas;
    private String indAccessAllFi;
    private String indSupervisorFi;
    private String indAjMesContable;
    
    // Constructor vacío
    public UsuarioDTO() {}
    
    // Getters y Setters
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    
    public String getNomUsuario() { return nomUsuario; }
    public void setNomUsuario(String nomUsuario) { this.nomUsuario = nomUsuario; }
    
    public String getApeUsuario() { return apeUsuario; }
    public void setApeUsuario(String apeUsuario) { this.apeUsuario = apeUsuario; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }
    
    public String getIndActivo() { return indActivo; }
    public void setIndActivo(String indActivo) { this.indActivo = indActivo; }
    
    public String getIndCliente() { return indCliente; }
    public void setIndCliente(String indCliente) { this.indCliente = indCliente; }
    
    public String getIndCajero() { return indCajero; }
    public void setIndCajero(String indCajero) { this.indCajero = indCajero; }
    
    public String getIndCobrador() { return indCobrador; }
    public void setIndCobrador(String indCobrador) { this.indCobrador = indCobrador; }
    
    public String getIndVendedor() { return indVendedor; }
    public void setIndVendedor(String indVendedor) { this.indVendedor = indVendedor; }
    
    public String getIndSupervisor() { return indSupervisor; }
    public void setIndSupervisor(String indSupervisor) { this.indSupervisor = indSupervisor; }
    
    public String getAutorizaDescuento() { return autorizaDescuento; }
    public void setAutorizaDescuento(String autorizaDescuento) { this.autorizaDescuento = autorizaDescuento; }
    
    public String getCreaCliente() { return creaCliente; }
    public void setCreaCliente(String creaCliente) { this.creaCliente = creaCliente; }
    
    public String getEmiteFacturaMovil() { return emiteFacturaMovil; }
    public void setEmiteFacturaMovil(String emiteFacturaMovil) { this.emiteFacturaMovil = emiteFacturaMovil; }
    
    public String getSupervisorGc() { return supervisorGc; }
    public void setSupervisorGc(String supervisorGc) { this.supervisorGc = supervisorGc; }
    
    public String getLiderDepto() { return liderDepto; }
    public void setLiderDepto(String liderDepto) { this.liderDepto = liderDepto; }
    
    public String getTipoUsuarioHelpdesk() { return tipoUsuarioHelpdesk; }
    public void setTipoUsuarioHelpdesk(String tipoUsuarioHelpdesk) { this.tipoUsuarioHelpdesk = tipoUsuarioHelpdesk; }
    
    public String getTomaFisica() { return tomaFisica; }
    public void setTomaFisica(String tomaFisica) { this.tomaFisica = tomaFisica; }
    
    public String getDesreferenciarDoc() { return desreferenciarDoc; }
    public void setDesreferenciarDoc(String desreferenciarDoc) { this.desreferenciarDoc = desreferenciarDoc; }
    
    public String getAutorizaCompra() { return autorizaCompra; }
    public void setAutorizaCompra(String autorizaCompra) { this.autorizaCompra = autorizaCompra; }
    
    public String getMontoAutoriza() { return montoAutoriza; }
    public void setMontoAutoriza(String montoAutoriza) { this.montoAutoriza = montoAutoriza; }
    
    public String getTomaFisicaInv() { return tomaFisicaInv; }
    public void setTomaFisicaInv(String tomaFisicaInv) { this.tomaFisicaInv = tomaFisicaInv; }
    
    public String getAutorizaLotePagos() { return autorizaLotePagos; }
    public void setAutorizaLotePagos(String autorizaLotePagos) { this.autorizaLotePagos = autorizaLotePagos; }
    
    public String getCoRegistraMesCerrados() { return coRegistraMesCerrados; }
    public void setCoRegistraMesCerrados(String coRegistraMesCerrados) { this.coRegistraMesCerrados = coRegistraMesCerrados; }
    
    public String getCxpRegistraMesCerrados() { return cxpRegistraMesCerrados; }
    public void setCxpRegistraMesCerrados(String cxpRegistraMesCerrados) { this.cxpRegistraMesCerrados = cxpRegistraMesCerrados; }
    
    public String getCxcRegistraMesCerrados() { return cxcRegistraMesCerrados; }
    public void setCxcRegistraMesCerrados(String cxcRegistraMesCerrados) { this.cxcRegistraMesCerrados = cxcRegistraMesCerrados; }
    
    public String getAutorizaPrecio() { return autorizaPrecio; }
    public void setAutorizaPrecio(String autorizaPrecio) { this.autorizaPrecio = autorizaPrecio; }
    
    public String getAutorizaGrpMercado() { return autorizaGrpMercado; }
    public void setAutorizaGrpMercado(String autorizaGrpMercado) { this.autorizaGrpMercado = autorizaGrpMercado; }
    
    public String getCxpProcesaDocumento() { return cxpProcesaDocumento; }
    public void setCxpProcesaDocumento(String cxpProcesaDocumento) { this.cxpProcesaDocumento = cxpProcesaDocumento; }
    
    public String getCxcProcesaDocumento() { return cxcProcesaDocumento; }
    public void setCxcProcesaDocumento(String cxcProcesaDocumento) { this.cxcProcesaDocumento = cxcProcesaDocumento; }
    
    public String getFormulacionInv() { return formulacionInv; }
    public void setFormulacionInv(String formulacionInv) { this.formulacionInv = formulacionInv; }
    
    public String getAutorizaPackingInv() { return autorizaPackingInv; }
    public void setAutorizaPackingInv(String autorizaPackingInv) { this.autorizaPackingInv = autorizaPackingInv; }
    
    public String getAutorizaEntraPackingInv() { return autorizaEntraPackingInv; }
    public void setAutorizaEntraPackingInv(String autorizaEntraPackingInv) { this.autorizaEntraPackingInv = autorizaEntraPackingInv; }
    
    public String getOrdenesMs() { return ordenesMs; }
    public void setOrdenesMs(String ordenesMs) { this.ordenesMs = ordenesMs; }
    
    public String getCoordinaEntrevista() { return coordinaEntrevista; }
    public void setCoordinaEntrevista(String coordinaEntrevista) { this.coordinaEntrevista = coordinaEntrevista; }
    
    public String getNdProcesaPagoCliente() { return ndProcesaPagoCliente; }
    public void setNdProcesaPagoCliente(String ndProcesaPagoCliente) { this.ndProcesaPagoCliente = ndProcesaPagoCliente; }
    
    public String getFcCambiaPrecio() { return fcCambiaPrecio; }
    public void setFcCambiaPrecio(String fcCambiaPrecio) { this.fcCambiaPrecio = fcCambiaPrecio; }
    
    public String getFcCambiaLprecio() { return fcCambiaLprecio; }
    public void setFcCambiaLprecio(String fcCambiaLprecio) { this.fcCambiaLprecio = fcCambiaLprecio; }
    
    public String getFcMargenMinimo() { return fcMargenMinimo; }
    public void setFcMargenMinimo(String fcMargenMinimo) { this.fcMargenMinimo = fcMargenMinimo; }
    
    public String getIndVerPreciosInventario() { return indVerPreciosInventario; }
    public void setIndVerPreciosInventario(String indVerPreciosInventario) { this.indVerPreciosInventario = indVerPreciosInventario; }
    
    public String getFcRegistraNcSinFac() { return fcRegistraNcSinFac; }
    public void setFcRegistraNcSinFac(String fcRegistraNcSinFac) { this.fcRegistraNcSinFac = fcRegistraNcSinFac; }
    
    public String getVerImagenesPortal() { return verImagenesPortal; }
    public void setVerImagenesPortal(String verImagenesPortal) { this.verImagenesPortal = verImagenesPortal; }
    
    public String getVerGraficasPortal() { return verGraficasPortal; }
    public void setVerGraficasPortal(String verGraficasPortal) { this.verGraficasPortal = verGraficasPortal; }
    
    public String getCxcAnularRecibo() { return cxcAnularRecibo; }
    public void setCxcAnularRecibo(String cxcAnularRecibo) { this.cxcAnularRecibo = cxcAnularRecibo; }
    
    public String getFcRegistraCostoDevolu() { return fcRegistraCostoDevolu; }
    public void setFcRegistraCostoDevolu(String fcRegistraCostoDevolu) { this.fcRegistraCostoDevolu = fcRegistraCostoDevolu; }
    
    public String getCmModificaSolicitud() { return cmModificaSolicitud; }
    public void setCmModificaSolicitud(String cmModificaSolicitud) { this.cmModificaSolicitud = cmModificaSolicitud; }
    
    public String getFcFacDistintaSucursal() { return fcFacDistintaSucursal; }
    public void setFcFacDistintaSucursal(String fcFacDistintaSucursal) { this.fcFacDistintaSucursal = fcFacDistintaSucursal; }
    
    public String getFcCambiaPrecioServ() { return fcCambiaPrecioServ; }
    public void setFcCambiaPrecioServ(String fcCambiaPrecioServ) { this.fcCambiaPrecioServ = fcCambiaPrecioServ; }
    
    public String getBaCierraConci() { return baCierraConci; }
    public void setBaCierraConci(String baCierraConci) { this.baCierraConci = baCierraConci; }
    
    public String getBaReversaConci() { return baReversaConci; }
    public void setBaReversaConci(String baReversaConci) { this.baReversaConci = baReversaConci; }
    
    public String getFcProcesarDevolucion() { return fcProcesarDevolucion; }
    public void setFcProcesarDevolucion(String fcProcesarDevolucion) { this.fcProcesarDevolucion = fcProcesarDevolucion; }
    
    public String getAutorizaDescuentoLimite() { return autorizaDescuentoLimite; }
    public void setAutorizaDescuentoLimite(String autorizaDescuentoLimite) { this.autorizaDescuentoLimite = autorizaDescuentoLimite; }
    
    public String getCoIndAutorizaCuentaContable() { return coIndAutorizaCuentaContable; }
    public void setCoIndAutorizaCuentaContable(String coIndAutorizaCuentaContable) { this.coIndAutorizaCuentaContable = coIndAutorizaCuentaContable; }
    
    public String getFcRegistraConVendedor() { return fcRegistraConVendedor; }
    public void setFcRegistraConVendedor(String fcRegistraConVendedor) { this.fcRegistraConVendedor = fcRegistraConVendedor; }
    
    public String getIndCajeroFi() { return indCajeroFi; }
    public void setIndCajeroFi(String indCajeroFi) { this.indCajeroFi = indCajeroFi; }
    
    public String getIndCobradorFi() { return indCobradorFi; }
    public void setIndCobradorFi(String indCobradorFi) { this.indCobradorFi = indCobradorFi; }
    
    public String getIndProcesaDocumentoFi() { return indProcesaDocumentoFi; }
    public void setIndProcesaDocumentoFi(String indProcesaDocumentoFi) { this.indProcesaDocumentoFi = indProcesaDocumentoFi; }
    
    public String getIndTrasladoCuentas() { return indTrasladoCuentas; }
    public void setIndTrasladoCuentas(String indTrasladoCuentas) { this.indTrasladoCuentas = indTrasladoCuentas; }
    
    public String getIndAccessAllFi() { return indAccessAllFi; }
    public void setIndAccessAllFi(String indAccessAllFi) { this.indAccessAllFi = indAccessAllFi; }
    
    public String getIndSupervisorFi() { return indSupervisorFi; }
    public void setIndSupervisorFi(String indSupervisorFi) { this.indSupervisorFi = indSupervisorFi; }
    
    public String getIndAjMesContable() { return indAjMesContable; }
    public void setIndAjMesContable(String indAjMesContable) { this.indAjMesContable = indAjMesContable; }
}
