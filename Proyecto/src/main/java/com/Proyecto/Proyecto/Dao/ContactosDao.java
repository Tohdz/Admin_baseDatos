/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Dao;
import com.Proyecto.Proyecto.Domain.Contactos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author alejh
 */
public interface ContactosDao extends JpaRepository <Contactos, Long> {
     List<Contactos> findByNombre(String nombre);
    
}