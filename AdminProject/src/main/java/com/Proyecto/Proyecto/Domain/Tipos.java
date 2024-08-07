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
@Table(name = "FIDE_TIPOS_TB")
public class Tipos implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SERVICIO")
    private Long idTipo ;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "ESTADO")
    private boolean estado;

    public Tipos() {
    }

    public Tipos(Long idTipo, String nombre, boolean estado) {
        this.idTipo = idTipo;
        this.nombre = nombre;
        this.estado = estado;
    }
    
    
}
