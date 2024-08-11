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
@Table(name = "FIDE_DETALLES_TB")
public class Detalle_Factura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DETALLE")
    private Long idVenta;
    @Column(name = "ID_FACTURA")
    private Long idFactura;
    @Column(name = "ID_ORDEN")
    private Long idOrden;
    @Column(name = "ID_REPUESTO")
    private Long idRepuesto;
    @Column(name = "PRECIO_UNITARIO")
    private double precio;
    @Column(name = "CANTIDAD")
    private int cantidad;

    public Detalle_Factura() {
    }

    public Detalle_Factura(Long idFactura, Long idRepuesto, double precio, int cantidad) {
        this.idFactura = idFactura;
        this.idRepuesto = idRepuesto;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    

}
