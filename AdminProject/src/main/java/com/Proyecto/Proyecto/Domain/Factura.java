/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name = "FIDE_FACTURAS_TB")
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FACTURA")
    private Long idFactura;
    @Column(name = "ID_SEDE")
    private Long idSede;
    @Column(name = "FECHA")
    private Date fecha;
    @Column(name = "PRECIO_FINAL")
    private double total;
    @Column(name = "ID_USUARIO")
    private Long idUsuario;
    
    

    public Factura() {
    }

    public Factura(Long idUSuario,Long idSede) {
        this.idUsuario = idUSuario;
        this.idSede = idSede;
        this.fecha = Calendar.getInstance().getTime();
  
    }

    

}
