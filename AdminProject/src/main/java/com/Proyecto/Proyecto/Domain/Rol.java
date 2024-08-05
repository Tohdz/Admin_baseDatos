package com.Proyecto.Proyecto.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "ROL")
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ROL")
    private Long idRol;
    
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "ID_USUARIO")
    private Long idUsuario;
}