/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.controller;

import com.Proyecto.Proyecto.Domain.Marcas;
import com.Proyecto.Proyecto.Service.MarcasService;
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
@RequestMapping("/marcas")
public class MarcasController {
    @Autowired
    private MarcasService marcasService;

    @GetMapping("/listado")
    private String listado(Model model) {
        var marcas = marcasService.getMarcas();
        model.addAttribute("marcas", marcas);
        return "/marcas/listado";
    }


    @PostMapping("/guardar")
    public String marcaGuardar(@RequestParam(value = "estado", defaultValue = "false") boolean estado,Marcas marca) {
        marca.setEstado(estado);
        marcasService.save(marca);
        return "redirect:/marcas/listado";
    }

    @GetMapping("/eliminar/{idMarca}")
    public String marcaEliminar(Marcas marca) {
        marcasService.delete(marca);
        return "redirect:/marcas/listado";
    }

    @GetMapping("/modificar/{idMarca}")
    public String marcaModificar(Marcas marca, Model model) {
        marca = marcasService.getMarca(marca);
        model.addAttribute("marca", marca);
        return "/marcas/modifica";
    }

    @PostMapping("/modificar2")
    public String marcaModificar2(@RequestParam("idMarca")Long idMarca, @RequestParam("nombre") String nombre,@RequestParam(value="estado",defaultValue = "false") boolean estado) {
        marcasService.update(idMarca, nombre, estado);
        return "redirect:/marcas/listado";
    }
}
