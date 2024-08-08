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
import java.time.LocalDate;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


/**
 *
 * @author hhern
 */
@Data
@Entity
@Table(name = "FIDE_EMPLEADOS_TB")
public class Empleado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EMPLEADO")
    private Long idEmpleado ;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "CORREO")
    private String correo;
    @Column(name = "FECHA_CONTRATO")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date  fecha;
    @Column(name = "SALARIO")
    private String salario;
    @Column(name = "ID_PUESTO")
    private Long idPuesto;
    @Column(name = "ID_SEDE")
    private Long idSede;
    @Column(name = "ESTADO")
    private boolean estado;

    public Empleado() {
    }

    public Empleado(Long idEmpleado, String nombre, String apellido, String telefono, String correo, Date fecha, String salario, Long idPuesto, Long idSede, boolean estado) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.fecha = fecha;
        this.salario = salario;
        this.idPuesto = idPuesto;
        this.idSede = idSede;
        this.estado = estado;
    }
    
    
}
