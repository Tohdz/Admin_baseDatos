/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Service.Impl;

import com.Proyecto.Proyecto.Service.ContactoService;
import org.springframework.stereotype.Service;

@Service
public class ContactoServiceImpl implements ContactoService {

    @Override
    public void enviarMensaje(String nombre, String correo, String mensaje) {
        // Lógica para enviar el mensaje (puede ser un servicio de correo electrónico, etc.)
    }
}
