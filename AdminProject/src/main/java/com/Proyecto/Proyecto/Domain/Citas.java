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
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author hhern
 */
@Data
@Entity
@Table(name = "FIDE_CITAS_TB")
public class Citas implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CITA")
    private Long idCita ;
    @Column(name = "PLACA")
    private String placa;
    @Column(name = "FECHA")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date  fechaHora;
    @Column(name = "ID_SERVICIO")
    private Long idServicio;
    @Column(name = "ID_EMPLEADO")
    private Long idEmpleado;
    @Column(name = "ID_SEDE")
    private Long idSede;
    @Column(name = "ESTADO")
    private boolean estado;

    public Citas() {
    }

    public Citas(Long idCita, String placa, Date fechaHora, Long idServicio, Long idEmpleado, Long idSede, boolean estado) {
        this.idCita = idCita;
        this.placa = placa;
        this.fechaHora = fechaHora;
        this.idServicio = idServicio;
        this.idEmpleado = idEmpleado;
        this.idSede = idSede;
        this.estado = estado;
    }

}
