package com.Proyecto.Proyecto.Domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "FIDE_REPUESTOS_TB")
public class Repuestos implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REPUESTO")
    private Long idRepuesto;
    @Column(name = "IMAGEN")
    private String imagen;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "ID_MARCA")
    private Long idMarca;
    @Column(name = "PRECIO")
    private double precio;
    @Column(name = "CANTIDAD")
    private int cantidad;
    @Column(name="ID_CATEGORIA")
    private Long idCategoria;
    @Column(name="ID_SEDE")
    private Long idSede;
    @Column(name = "ESTADO")
    private boolean estado;
    
    

    public Repuestos() {
    }

    public Repuestos(Long idRepuesto, String imagen, String nombre, Long idMarca, double precio, int cantidad, Long idCategoria, Long idSede, boolean estado) {
        this.idRepuesto = idRepuesto;
        this.imagen = imagen;
        this.nombre = nombre;
        this.idMarca = idMarca;
        this.precio = precio;
        this.cantidad = cantidad;
        this.idCategoria = idCategoria;
        this.idSede = idSede;
        this.estado = estado;
    }

    
}