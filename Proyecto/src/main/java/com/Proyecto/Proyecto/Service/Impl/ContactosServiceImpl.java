/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Service.Impl;

import com.Proyecto.Proyecto.Dao.ContactosDao;
import com.Proyecto.Proyecto.Domain.Contactos;
import com.Proyecto.Proyecto.Service.ContactosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alejh
 */
@Service
public class ContactosServiceImpl implements ContactosService {

    @Autowired
    private ContactosDao contactosDao;

    @Override
    @Transactional(readOnly = true)
    public List<Contactos> getContactos(String nombre) {
         if (nombre != null && !nombre.isEmpty()) {
             return contactosDao.findByNombre(nombre);
         } else {
             return contactosDao.findAll();
         }
    }

    @Override
    @Transactional(readOnly = true)
    public Contactos getContacto(Contactos contactos) {
        return contactosDao.findById(contactos.getIdContacto()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Contactos contactos) {
        contactosDao.save(contactos);
    }

    @Override
    @Transactional
    public void delete(Contactos contactos) {
        contactosDao.delete(contactos);
    }

}