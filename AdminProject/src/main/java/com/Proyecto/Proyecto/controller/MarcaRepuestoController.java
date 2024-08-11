/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.controller;


import com.Proyecto.Proyecto.Domain.MarcaRepuestos;
import com.Proyecto.Proyecto.Service.MarcaRepuestoService;
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
@RequestMapping("/marcarepuestos")
public class MarcaRepuestoController {
    @Autowired
    private MarcaRepuestoService marcarepuestoService;

    @GetMapping("/listado")
    private String listado(Model model) {
        var marcarepuestos = marcarepuestoService.getMarcaRepuestos();
        model.addAttribute("marcarepuestos", marcarepuestos);
        return "/marcarepuestos/listado";
    }


    @PostMapping("/guardar")
    public String marcaGuardar(@RequestParam(value = "estado", defaultValue = "false") boolean estado,MarcaRepuestos marcarepuesto) {
        marcarepuesto.setEstado(estado);
        marcarepuestoService.save(marcarepuesto);
        return "redirect:/marcarepuestos/listado";
    }

    @GetMapping("/eliminar/{idMarcaRepuesto}")
    public String marcaEliminar(MarcaRepuestos marcarepuesto) {
        marcarepuestoService.delete(marcarepuesto);
        return "redirect:/marcarepuestos/listado";
    }

    @GetMapping("/modificar/{idMarcaRepuesto}")
    public String marcaModificar(MarcaRepuestos marcarepuesto, Model model) {
        marcarepuesto = marcarepuestoService.getMarca(marcarepuesto);
        model.addAttribute("marcarepuesto", marcarepuesto);
        return "/marcarepuestos/modifica";
    }

    @PostMapping("/modificar2")
    public String marcaModificar2(@RequestParam("idMarcaRepuesto")Long idMarcaRepuesto, @RequestParam("nombre") String nombre,@RequestParam(value="estado",defaultValue = "false") boolean estado) {
        marcarepuestoService.update(idMarcaRepuesto, nombre, estado);
        return "redirect:/marcarepuestos/listado";
    }
}
