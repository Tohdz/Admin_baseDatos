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
@Table(name = "FIDE_MODELOS_TB")
public class Modelos implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MODELO")
    private Long idModelo ;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "ID_MARCA")
    private Long idMarca;
    @Column(name = "ESTADO")
    private boolean estado;

    public Modelos() {
    }

    public Modelos(Long idModelo, String nombre, Long idMarca, boolean estado) {
        this.idModelo = idModelo;
        this.nombre = nombre;
        this.idMarca = idMarca;
        this.estado = estado;
    }
}
