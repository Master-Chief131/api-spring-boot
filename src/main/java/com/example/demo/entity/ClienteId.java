package com.example.demo.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Embeddable
public class ClienteId implements Serializable {
    @Column(name = "NO_CIA")
    private int noCia;

    @Column(name = "NO_CLIENTE")
    private int noCliente;

    @Column(name = "GRUPO", length = 2)
    private String grupo;

    public int getNoCia() { return noCia; }
    public void setNoCia(int noCia) { this.noCia = noCia; }
    public int getNoCliente() { return noCliente; }
    public void setNoCliente(int noCliente) { this.noCliente = noCliente; }
    public String getGrupo() { return grupo; }
    public void setGrupo(String grupo) { this.grupo = grupo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteId that = (ClienteId) o;
        return noCia == that.noCia &&
               noCliente == that.noCliente &&
               (grupo != null ? grupo.equals(that.grupo) : that.grupo == null);
    }

    @Override
    public int hashCode() {
        int result = noCia;
        result = 31 * result + noCliente;
        result = 31 * result + (grupo != null ? grupo.hashCode() : 0);
        return result;
    }
}
