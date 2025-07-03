package com.example.demo.dto;

import java.util.List;

public class FacturasPaginadasDTO {
    private List<FacturaCompletaDTO> facturas;
    private int paginaActual;
    private int totalPaginas;
    private long totalElementos;
    private int tamanoPagina;
    private boolean esUltimaPagina;
    private boolean esPrimeraPagina;

    // Constructores
    public FacturasPaginadasDTO() {}

    public FacturasPaginadasDTO(List<FacturaCompletaDTO> facturas, int paginaActual, int totalPaginas, 
                               long totalElementos, int tamanoPagina, boolean esUltimaPagina, boolean esPrimeraPagina) {
        this.facturas = facturas;
        this.paginaActual = paginaActual;
        this.totalPaginas = totalPaginas;
        this.totalElementos = totalElementos;
        this.tamanoPagina = tamanoPagina;
        this.esUltimaPagina = esUltimaPagina;
        this.esPrimeraPagina = esPrimeraPagina;
    }

    // Getters y Setters
    public List<FacturaCompletaDTO> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<FacturaCompletaDTO> facturas) {
        this.facturas = facturas;
    }

    public int getPaginaActual() {
        return paginaActual;
    }

    public void setPaginaActual(int paginaActual) {
        this.paginaActual = paginaActual;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public long getTotalElementos() {
        return totalElementos;
    }

    public void setTotalElementos(long totalElementos) {
        this.totalElementos = totalElementos;
    }

    public int getTamanoPagina() {
        return tamanoPagina;
    }

    public void setTamanoPagina(int tamanoPagina) {
        this.tamanoPagina = tamanoPagina;
    }

    public boolean isEsUltimaPagina() {
        return esUltimaPagina;
    }

    public void setEsUltimaPagina(boolean esUltimaPagina) {
        this.esUltimaPagina = esUltimaPagina;
    }

    public boolean isEsPrimeraPagina() {
        return esPrimeraPagina;
    }

    public void setEsPrimeraPagina(boolean esPrimeraPagina) {
        this.esPrimeraPagina = esPrimeraPagina;
    }
}
