/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.controller;

import com.Proyecto.Proyecto.Domain.Puestos;
import com.Proyecto.Proyecto.Service.PuestosService;
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
@RequestMapping("/puestos")
public class PuestosController {
    @Autowired
    private PuestosService puestosService;

    @GetMapping("/listado")
    private String listado(Model model) {
        var puestos = puestosService.getPuestos();
        model.addAttribute("puestos", puestos);
        return "/puestos/listado";
    }


    @PostMapping("/guardar")
    public String puestoGuardar(@RequestParam(value = "estado", defaultValue = "false") boolean estado,Puestos puesto) {
        puesto.setEstado(estado);
        puestosService.save(puesto);
        return "redirect:/puestos/listado";
    }

    @GetMapping("/eliminar/{idPuesto}")
    public String puestoEliminar(Puestos puesto) {
        puestosService.delete(puesto);
        return "redirect:/puestos/listado";
    }

    @GetMapping("/modificar/{idPuesto}")
    public String puestoModificar(Puestos puesto, Model model) {
        puesto = puestosService.getPuesto(puesto);
        model.addAttribute("puesto", puesto);
        return "/puestos/modifica";
    }

    @PostMapping("/modificar2")
    public String puestoModificar2(@RequestParam("idPuesto")Long idPuesto, @RequestParam("nombre") String nombre,@RequestParam(value="estado",defaultValue = "false") boolean estado) {
        puestosService.update(idPuesto, nombre, estado);
        return "redirect:/puestos/listado";
    }
}
