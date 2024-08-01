package com.Proyecto.Proyecto.Domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "CATEGORIA")
public class Categorias implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORIA")
    private Long idCategoria ;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "RUTA_IMAGEN")
    private String rutaImagen;
    @Column(name = "ESTADO")
    private boolean estado;

    @OneToMany
    @JoinColumn(name = "ID_CATEGORIA", updatable = false)
    List<Juegos> juegos;

    public Categorias() {
    }

    public Categorias(Long idCategoria, String descripcion, String rutaImagen, boolean estado, List<Juegos> juegos) {
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
        this.rutaImagen = rutaImagen;
        this.estado = estado;
        this.juegos = juegos;
    }

   



}