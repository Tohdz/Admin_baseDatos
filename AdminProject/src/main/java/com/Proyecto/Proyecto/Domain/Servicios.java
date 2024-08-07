/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author hhern
 */
@Data
@Entity
@Table(name = "FIDE_SERVICIOS_TB")
public class Servicios implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SERVICIO")
    private Long idServicio ;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "ESTADO")
    private boolean estado;

    public Servicios() {
    }

    public Servicios(Long idServicio, String nombre, boolean estado) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.estado = estado;
    }
    
    
    
}
