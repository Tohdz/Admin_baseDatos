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
import java.security.Principal;
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
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author hhern
 */
@Controller
@RequestMapping("/clientsvehicles")
public class ClientsVehiclesController {
    
    @Autowired
    private VehiculosService vehiculosService;

    @GetMapping("/listado")
    private String listado(Model model,Principal principal) {
        Usuario user=vehiculosService.getUsuariosbyUsername(principal.getName());
        var vehiculos = vehiculosService.getVehiculosbyUser(user.getIdUsuario());
        model.addAttribute("vehiculos", vehiculos);
        List<Marcas> marcas = vehiculosService.getMarcasbyState();
        model.addAttribute("marcas", marcas);
        List<Tipos> tipos = vehiculosService.getTiposbyState();
        model.addAttribute("tipos", tipos);
        //
        List<Marcas> marcas2 = vehiculosService.getMarcas();
        Map<Long, String> marcasMap = marcas2.stream()
                .collect(Collectors.toMap(Marcas::getIdMarca, Marcas::getNombre));
        model.addAttribute("marcasMap", marcasMap);
        //
        List<Modelos> modelos2 = vehiculosService.getModelos();
        Map<Long, String> modelosMap = modelos2.stream()
                .collect(Collectors.toMap(Modelos::getIdModelo, Modelos::getNombre));
        model.addAttribute("modelosMap", modelosMap);
        //
        List<Tipos> tipos2 = vehiculosService.getTipos();
        Map<Long, String> tiposMap = tipos2.stream()
                .collect(Collectors.toMap(Tipos::getIdTipo, Tipos::getNombre));
        model.addAttribute("tiposMap", tiposMap);
        return "/clientsvehicles/listado";
    }

    @GetMapping("/getModelos")
    @ResponseBody // Esto indica que el retorno es JSON
    public List<Modelos> getModelos(@RequestParam Long idMarca) {
        List<Modelos> modelos = vehiculosService.getModelosbyMarca(idMarca);
        return modelos;
    }

    @PostMapping("/guardar")
    public String vehiculoGuardar(Vehiculos vehiculo,Principal principal) {
        Usuario user=vehiculosService.getUsuariosbyUsername(principal.getName());
        vehiculo.setIdSede(user.getIdSede());
        vehiculo.setIdUsuario(user.getIdUsuario());
        vehiculo.setEstado(true);
        vehiculosService.save(vehiculo);
        return "redirect:/clientsvehicles/listado";
    }

    @GetMapping("/eliminar/{placa}")
    public String vehiculoEliminar(Vehiculos vehiculo) {
        vehiculosService.delete(vehiculo);
        return "redirect:/clientsvehicles/listado";
    }

    @GetMapping("/modificar/{placa}")
    public String vehiculoModificar(Vehiculos vehiculo, Model model) {
        vehiculo = vehiculosService.getVehiculo(vehiculo);
        model.addAttribute("vehiculo", vehiculo);
        List<Marcas> marcas = vehiculosService.getMarcasbyState();
        model.addAttribute("marcas", marcas);
        List<Tipos> tipos = vehiculosService.getTiposbyState();
        model.addAttribute("tipos", tipos);
        return "/clientsvehicles/modifica";
    }

    @PostMapping("/modificar2")
    public String vehiculoModificar2(@RequestParam("placa") String placa, @RequestParam("idMarca") Long idMarca, @RequestParam("idModelo") Long idModelo, @RequestParam("idTipo") Long idTipo, @RequestParam("ano") int ano,Principal principal) {
        Usuario user=vehiculosService.getUsuariosbyUsername(principal.getName());
        Long idSede=user.getIdSede();
        Long idUsuario=user.getIdUsuario();
        boolean estado=true;
        System.out.println("maeeeeeeeeeeeeeeeeeeee"+placa+ idMarca+ idModelo+ idTipo+ ano+ idSede+ idUsuario+ estado);
        vehiculosService.update(placa, idMarca, idModelo, idTipo, ano, idSede, idUsuario, estado);
        return "redirect:/clientsvehicles/listado";
    }
}
