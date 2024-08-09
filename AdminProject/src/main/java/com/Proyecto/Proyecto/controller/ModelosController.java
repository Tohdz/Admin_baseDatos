/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.controller;

import com.Proyecto.Proyecto.Domain.Marcas;
import com.Proyecto.Proyecto.Domain.Modelos;
import com.Proyecto.Proyecto.Service.ModelosService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
@RequestMapping("/modelos")
public class ModelosController {
    @Autowired
    private ModelosService modelosService;

    @GetMapping("/listado")
    private String listado(Model model) {
        var modelos = modelosService.getModelos();
        model.addAttribute("modelos", modelos);
        List<Marcas> marcas = modelosService.getMarcasbyState();
        model.addAttribute("marcas", marcas);
        //
        List<Marcas> marcas2 = modelosService.getMarcas();
        Map<Long, String> marcasMap = marcas2.stream()
                .collect(Collectors.toMap(Marcas::getIdMarca, Marcas::getNombre));
        model.addAttribute("marcasMap", marcasMap);
        return "/modelos/listado";
    }


    @PostMapping("/guardar")
    public String modeloGuardar(@RequestParam(value = "estado", defaultValue = "false") boolean estado,Modelos modelo) {
        modelo.setEstado(estado);
        modelosService.save(modelo);
        return "redirect:/modelos/listado";
    }

    @GetMapping("/eliminar/{idModelo}")
    public String modeloEliminar(Modelos modelo) {
        modelosService.delete(modelo);
        return "redirect:/modelos/listado";
    }

    @GetMapping("/modificar/{idModelo}")
    public String modeloModificar(Modelos modelo, Model model) {
        modelo = modelosService.getModelo(modelo);
        model.addAttribute("modelo", modelo);
        List<Marcas> marcas = modelosService.getMarcasbyState();
        model.addAttribute("marcas", marcas);
        return "/modelos/modifica";
    }

    @PostMapping("/modificar2")
    public String modeloModificar2(@RequestParam("idModelo")Long idModelo,@RequestParam("nombre") String nombre,@RequestParam("idMarca") Long idMarca,@RequestParam(value="estado",defaultValue = "false") boolean estado) {
        modelosService.update(idModelo, nombre, idMarca, estado);
        return "redirect:/modelos/listado";
    }
}
