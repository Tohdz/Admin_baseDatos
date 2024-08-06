/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.controller;

import com.Proyecto.Proyecto.Domain.Sedes;
import com.Proyecto.Proyecto.Service.SedesService;
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
@RequestMapping("/sedes")
public class SedesController {
    
    @Autowired
    private SedesService sedesService;
    
    @GetMapping("/listado")
    private String listado(Model model) {
        var sedes= sedesService.getSedes();
        model.addAttribute("sedes", sedes);
        return "/sedes/listado";
    }


    @PostMapping("/guardar")
    public String sedeGuardar(@RequestParam(value = "estado", defaultValue = "false") boolean estado,Sedes sede) {
        sede.setEstado(estado);
        sedesService.save(sede);
        return "redirect:/sedes/listado";
    }

    @GetMapping("/eliminar/{idSede}")
    public String sedeEliminar(Sedes sede) {
        sedesService.delete(sede);
        return "redirect:/sedes/listado";
    }

    @GetMapping("/modificar/{idSede}")
    public String sedeModificar(Sedes sede, Model model) {
        sede = sedesService.getSede(sede);
        model.addAttribute("sede", sede);
        return "/sedes/modifica";
    }

    @PostMapping("/modificar2")
    public String sedeModificar2(@RequestParam("idSede")Long id,@RequestParam("nombre")String nom, @RequestParam("ciudad") String cit,@RequestParam("direccion") String dir,@RequestParam("telefono") String tel, @RequestParam(value="estado",defaultValue = "false") boolean estado) {
        sedesService.update(id, nom, cit, dir, tel, estado);
        return "redirect:/sedes/listado";
    }
}
