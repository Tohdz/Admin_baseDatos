/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.controller;

import com.Proyecto.Proyecto.Domain.Servicios;
import com.Proyecto.Proyecto.Service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author hhern
 */
@Controller
@RequestMapping("/servicios")
public class ServicioController {
    @Autowired
    private ServicioService servicioService;

    @GetMapping("/listado")
    private String listado(Model model) {
        var servicios = servicioService.getServicios();
        model.addAttribute("servicios", servicios);
        return "/servicios/listado";
    }


    @PostMapping("/guardar")
    public String servicioGuardar(@RequestParam(value = "estado", defaultValue = "false") boolean estado,Servicios servicio) {
        servicio.setEstado(estado);
        servicioService.save(servicio);
        return "redirect:/servicios/listado";
    }

    @GetMapping("/eliminar/{idServicio}")
    public String servicioEliminar(Servicios servicio) {
        servicioService.delete(servicio);
        return "redirect:/servicios/listado";
    }

    @GetMapping("/modificar/{idServicio}")
    public String servicioModificar(Servicios servicio, Model model) {
        servicio = servicioService.getServicio(servicio);
        model.addAttribute("servicio", servicio);
        return "/servicios/modifica";
    }

    @PostMapping("/modificar2")
    public String servicioModificar2(@RequestParam("idServicio")Long idServicio, @RequestParam("nombre") String nombre,@RequestParam(value="estado",defaultValue = "false") boolean estado) {
        servicioService.update(idServicio,nombre,estado);
        return "redirect:/servicios/listado";
    }
}
