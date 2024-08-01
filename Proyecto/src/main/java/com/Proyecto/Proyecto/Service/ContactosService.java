/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Service;

import com.Proyecto.Proyecto.Domain.Contactos;
import java.util.List;

/**
 *
 * @author alejh
 */
public interface ContactosService {
    List<Contactos> getContactos(String nombre);

    Contactos getContacto(Contactos contactos);

    void save(Contactos contactos);

    void delete(Contactos contactos);
}