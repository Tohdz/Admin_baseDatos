/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.controller;

import com.Proyecto.Proyecto.Domain.Tipos;
import com.Proyecto.Proyecto.Service.TiposService;
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
@RequestMapping("/tipos")
public class TiposController {
    @Autowired
    private TiposService tipoService;

    @GetMapping("/listado")
    private String listado(Model model) {
        var tipos = tipoService.getTipos();
        model.addAttribute("tipos", tipos);
        return "/tipos/listado";
    }


    @PostMapping("/guardar")
    public String tipoGuardar(@RequestParam(value = "estado", defaultValue = "false") boolean estado,Tipos tipo) {
        tipo.setEstado(estado);
        tipoService.save(tipo);
        return "redirect:/tipos/listado";
    }

    @GetMapping("/eliminar/{idTipo}")
    public String tipoEliminar(Tipos tipo) {
        tipoService.delete(tipo);
        return "redirect:/tipos/listado";
    }

    @GetMapping("/modificar/{idTipo}")
    public String tipoModificar(Tipos tipo, Model model) {
        tipo = tipoService.getTipo(tipo);
        model.addAttribute("tipo", tipo);
        return "/tipos/modifica";
    }

    @PostMapping("/modificar2")
    public String tipoModificar2(@RequestParam("idTipo")Long idTipo, @RequestParam("nombre") String nombre,@RequestParam(value="estado",defaultValue = "false") boolean estado) {
        tipoService.update(idTipo, nombre, estado);
        return "redirect:/tipos/listado";
    }
}
