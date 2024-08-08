/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.controller;

import com.Proyecto.Proyecto.Domain.Marcas;
import com.Proyecto.Proyecto.Domain.Modelos;
import com.Proyecto.Proyecto.Domain.Sedes;
import com.Proyecto.Proyecto.Domain.Tipos;
import com.Proyecto.Proyecto.Domain.Usuario;
import com.Proyecto.Proyecto.Domain.Vehiculos;
import com.Proyecto.Proyecto.Service.VehiculosService;
import java.util.List;
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
@RequestMapping("/vehiculos")
public class VehiculosController {
    
    @Autowired
    private VehiculosService vehiculosService;

    @GetMapping("/listado")
    private String listado(@RequestParam("idMarca") Long idMarca,Model model) {
        var vehiculos = vehiculosService.getVehiculos();
        model.addAttribute("vehiculos", vehiculos);
        List<Marcas> marcas = vehiculosService.getMarcas();
        model.addAttribute("marcas", marcas);
        List<Modelos> modelos = vehiculosService.getModelos(idMarca);
        model.addAttribute("modelos", modelos);
        List<Tipos> tipos = vehiculosService.getTipos();
        model.addAttribute("tipos", tipos);
        List<Sedes> sedes = vehiculosService.getSedes();
        model.addAttribute("sedes", sedes);
        List<Usuario> usuarios = vehiculosService.getUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "/vehiculos/listado";
    }

    @PostMapping("/guardar")
    public String vehiculoGuardar(@RequestParam(value = "estado", defaultValue = "false") boolean estado, Vehiculos vehiculo) {
        vehiculo.setEstado(estado);
        vehiculosService.save(vehiculo);
        return "redirect:/vehiculos/listado";
    }

    @GetMapping("/eliminar/{placa}")
    public String vehiculoEliminar(Vehiculos vehiculo) {
        vehiculosService.delete(vehiculo);
        return "redirect:/vehiculos/listado";
    }

    @GetMapping("/modificar/{placa}")
    public String vehiculoModificar(@RequestParam("idMarca") Long idMarca,Vehiculos vehiculo, Model model) {
        vehiculo = vehiculosService.getVehiculo(vehiculo);
        model.addAttribute("vehiculo", vehiculo);
        List<Marcas> marcas = vehiculosService.getMarcas();
        model.addAttribute("marcas", marcas);
        List<Modelos> modelos = vehiculosService.getModelos(idMarca);
        model.addAttribute("modelos", modelos);
        List<Tipos> tipos = vehiculosService.getTipos();
        model.addAttribute("tipos", tipos);
        List<Sedes> sedes = vehiculosService.getSedes();
        model.addAttribute("sedes", sedes);
        List<Usuario> usuarios = vehiculosService.getUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "/vehiculos/modifica";
    }

    @PostMapping("/modificar2")
    public String vehiculoModificar2(@RequestParam("placa") String placa,@RequestParam("idMarca") Long idMarca, @RequestParam("idModelo") Long idModelo, @RequestParam("idTipo") Long idTipo, @RequestParam("año") int año, @RequestParam("idSede")  Long idSede, @RequestParam("idUsuario") Long idUsuario,@RequestParam(value = "estado", defaultValue = "false") boolean estado) {
        vehiculosService.update(placa, idMarca, idModelo, idTipo, año, idSede, idUsuario, estado);
        return "redirect:/vehiculos/listado";
    }
}
