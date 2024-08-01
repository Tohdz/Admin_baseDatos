/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "DETALLE_FACTURA")
public class Detalle_Factura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DETALLE_FACTURA")
    private Long idVenta;
    @Column(name = "ID_FACTURA")
    private Long idFactura;
    @Column(name = "ID_JUEGO")
    private Long id_juego;
    @Column(name = "PRECIO")
    private double precio;
    @Column(name = "CANTIDAD")
    private int cantidad;

    public Detalle_Factura() {
    }

    public Detalle_Factura(Long idFactura, Long id_juego, double precio, int cantidad) {
        this.idFactura = idFactura;
        this.id_juego = id_juego;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    

}
