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
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author hhern
 */
@Data
@Entity
@Table(name = "FIDE_ORDENES_TB")
public class Ordenes implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORDEN")
    private Long idOrden ;
    @Column(name = "ID_CITA")
    private Long idCita;
    @Column(name = "FECHA")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime  fechaHora;
    @Column(name = "COMENTARIOS")
    private String comentario ;
    @Column(name = "ID_EMPLEADO")
    private Long idEmpleado;
    @Column(name = "ID_SEDE")
    private Long idSede;

    public Ordenes() {
    }

    public Ordenes(Long idOrden, Long idCita, LocalDateTime fechaHora, String comentario, Long idEmpleado, Long idSede) {
        this.idOrden = idOrden;
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.comentario = comentario;
        this.idEmpleado = idEmpleado;
        this.idSede = idSede;
    }

}


