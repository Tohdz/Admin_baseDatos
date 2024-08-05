package com.Proyecto.Proyecto.Domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "JUEGO")
public class Juegos implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_JUEGO")
    private Long id_juego;
    @Column(name = "IMAGEN")
    private String imagen;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "EMPRESA")
    private String empresa;
    @Column(name = "PRECIO")
    private double precio;
    @Column(name = "EXISTENCIAS")
    private int existencias;
    @Column(name = "ESTADO")
    private boolean estado;
    @Column(name="ID_CATEGORIA")
    private Long idcategoria;
    

    public Juegos() {
    }

    
}