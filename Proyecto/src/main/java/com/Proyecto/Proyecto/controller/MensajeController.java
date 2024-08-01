package com.Proyecto.Proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MensajeController {

    @GetMapping("/carrito/mensaje")
    public String mostrarPaginaAgradecimiento() {
        return "carrito/mensaje"; // Nombre de la vista de agradecimiento
    }
}
