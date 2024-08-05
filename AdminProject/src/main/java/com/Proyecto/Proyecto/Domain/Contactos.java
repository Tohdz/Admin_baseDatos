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
@Table(name = "CONTACTO")
public class Contactos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONTACTO")
    private Long idContacto;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "NUMERO")
    private int numero;
    @Column(name = "MENSAJE")
    private String mensaje;

    public Contactos() {
    }

    public Contactos(Long idContacto, String nombre, String apellido, int numero, String mensaje) {
        this.idContacto = idContacto;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numero = numero;
        this.mensaje = mensaje;
    }
    
    
}
