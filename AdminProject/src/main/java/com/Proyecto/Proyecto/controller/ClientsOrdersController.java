/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.controller;

import com.Proyecto.Proyecto.Domain.Citas;
import com.Proyecto.Proyecto.Domain.Empleado;
import com.Proyecto.Proyecto.Domain.Sedes;
import com.Proyecto.Proyecto.Domain.Usuario;
import com.Proyecto.Proyecto.Service.CitasService;
import com.Proyecto.Proyecto.Service.OrdenesService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author hhern
 */
@Controller
@RequestMapping("/clientsorders")
public class ClientsOrdersController {
    
    @Autowired
    private OrdenesService ordenesService;
    
    @Autowired
    private CitasService citasService;

    @GetMapping("/listado")
    private String listado(Model model,Principal principal) {
        Usuario user=citasService.getUsuariosbyUsername(principal.getName());
        var ordenes = ordenesService.getOrdenesbyuser(user.getIdUsuario());
        model.addAttribute("ordenes", ordenes);
        //
        List<Citas> citas2 = ordenesService.getCitas();
        Map<Long, String> citasMap = citas2.stream()
                .collect(Collectors.toMap(Citas::getIdCita, Citas::getPlaca));
        model.addAttribute("citasMap", citasMap);
        //
        List<Empleado> empleados2 = ordenesService.getEmpleados();
        Map<Long, String> empleadosMap = empleados2.stream()
                .collect(Collectors.toMap(Empleado::getIdEmpleado, Empleado::getNombre));
        model.addAttribute("empleadosMap", empleadosMap);
        //
        List<Sedes> sedes2 = ordenesService.getSedes();
        Map<Long, String> sedesMap = sedes2.stream()
                .collect(Collectors.toMap(Sedes::getIdSede, Sedes::getNombre));
        model.addAttribute("sedesMap", sedesMap);
        return "/clientsorders/listado";
    }
}
