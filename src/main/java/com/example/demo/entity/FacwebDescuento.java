package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "facweb_descuento")
@IdClass(FacwebDescuentoId.class)
public class FacwebDescuento implements Serializable {
    @Id
    @Column(name = "no_cia")
    private Integer noCia;

    @Id
    @Column(name = "no_grupo")
    private Integer noGrupo;

    @Id
    @Column(name = "No_articulo", length = 50)
    private String noArticulo;

    @Column(name = "Precio_Venta", precision = 15, scale = 6)
    private BigDecimal precioVenta;

    @Column(name = "Precio_Base", precision = 15, scale = 6)
    private BigDecimal precioBase;

    @Column(name = "Porcentaje", precision = 15, scale = 6)
    private BigDecimal porcentaje;

    @Column(name = "Limite_Oferta")
    @Temporal(TemporalType.DATE)
    private Date limiteOferta;

    @Column(name = "tipo", length = 1)
    private String tipo;

    @Column(name = "usuario_crea", length = 50)
    private String usuarioCrea;

    @Column(name = "fecha_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCrea;

    @Column(name = "usuario_mod", length = 50)
    private String usuarioMod;

    @Column(name = "fecha_mod")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMod;

    @Column(name = "estado_lista_precio", length = 1)
    private String estadoListaPrecio;

    @Column(name = "precio_base_lista", precision = 15, scale = 2)
    private BigDecimal precioBaseLista;

    @Column(name = "precio_venta_lista", precision = 15, scale = 2)
    private BigDecimal precioVentaLista;

    @Column(name = "usuario_lista_mod", length = 50)
    private String usuarioListaMod;

    @Column(name = "precio_base_ant", precision = 15, scale = 2)
    private BigDecimal precioBaseAnt;

    @Column(name = "precio_venta_ant", precision = 15, scale = 2)
    private BigDecimal precioVentaAnt;

    @Column(name = "usuario_lista_apr", length = 50)
    private String usuarioListaApr;

    @Column(name = "porcentaje_ant", precision = 15, scale = 6)
    private BigDecimal porcentajeAnt;

    // Getters and setters omitted for brevity (add as needed)
}
