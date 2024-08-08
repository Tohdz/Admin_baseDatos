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
@Table(name = "FIDE_VEHICULOS_TB")
public class Vehiculos implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLACA")
    private String placa ;
    @Column(name = "ID_MARCA")
    private Long idMarca;
    @Column(name = "ID_MODELO")
    private Long idModelo;
    @Column(name = "ID_TIPO")
    private Long idTipo;
    @Column(name = "AÑO")
    private int año;
    @Column(name = "ID_SEDE")
    private Long idSede;
    @Column(name = "ID_USUARIO")
    private Long idUsuario;
    @Column(name = "ESTADO")
    private boolean estado;

    public Vehiculos() {
    }

    public Vehiculos(String placa, Long idMarca, Long idModelo, Long idTipo, int año, Long idSede, Long idUsuario, boolean estado) {
        this.placa = placa;
        this.idMarca = idMarca;
        this.idModelo = idModelo;
        this.idTipo = idTipo;
        this.año = año;
        this.idSede = idSede;
        this.idUsuario = idUsuario;
        this.estado = estado;
    } 
}
