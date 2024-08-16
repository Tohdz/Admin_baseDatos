/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.controller;

import com.Proyecto.Proyecto.Domain.Empleado;
import com.Proyecto.Proyecto.Domain.Puestos;
import com.Proyecto.Proyecto.Domain.Sedes;
import com.Proyecto.Proyecto.Domain.Usuario;
import com.Proyecto.Proyecto.Service.CitasService;
import com.Proyecto.Proyecto.Service.EmpleadoService;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping("/adminemployees")
public class AdminEmploController {
    
    @Autowired
    private EmpleadoService empleadoService;
    
    @Autowired
    private CitasService citasService;

    @GetMapping("/listado")
    private String listado(Model model,Principal principal) {
        Usuario user=citasService.getUsuariosbyUsername(principal.getName());
        var empleados = empleadoService.getEmpleadosbysede(user.getIdSede());
        model.addAttribute("empleados", empleados);
        List<Puestos> puestos = empleadoService.getPuestosbyState();
        model.addAttribute("puestos", puestos);
        //
        List<Puestos> puestos2 = empleadoService.getPuestos();
        Map<Long, String> puestosMap = puestos2.stream()
                .collect(Collectors.toMap(Puestos::getIdPuesto, Puestos::getNombre));
        model.addAttribute("puestosMap", puestosMap);
        //
        List<Sedes> sedes2 = empleadoService.getSedes();
        Map<Long, String> sedesMap = sedes2.stream()
                .collect(Collectors.toMap(Sedes::getIdSede, Sedes::getNombre));
        model.addAttribute("sedesMap", sedesMap);
        return "/adminemployees/listado";
    }

    @PostMapping("/guardar")
    public String empleadoGuardar(@RequestParam(value = "estado", defaultValue = "false") boolean estado, Empleado empleado,Principal principal) {
        Usuario user=citasService.getUsuariosbyUsername(principal.getName());
        empleado.setIdSede(user.getIdSede());
        empleado.setEstado(estado);
        empleadoService.save(empleado);
        return "redirect:/adminemployees/listado";
    }

    @GetMapping("/eliminar/{idEmpleado}")
    public String empleadoEliminar(Empleado empleado) {
        empleadoService.delete(empleado);
        return "redirect:/adminemployees/listado";
    }

    @GetMapping("/modificar/{idEmpleado}")
    public String empleadoModificar(Empleado empleado, Model model) {
        empleado = empleadoService.getEmpleado(empleado);
        model.addAttribute("empleado", empleado);
        List<Puestos> puestos = empleadoService.getPuestosbyState();
        model.addAttribute("puestos", puestos);
        return "/adminemployees/modifica";
    }

    @PostMapping("/modificar2")
    public String empleadoModificar2(@RequestParam("idEmpleado") Long idEmpleado, @RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido, @RequestParam("telefono") String telefono, @RequestParam("correo") String correo, @RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @RequestParam("salario") String salario, @RequestParam("idPuesto") Long idPuesto, @RequestParam(value = "estado", defaultValue = "false") boolean estado,@RequestParam("username") String username,Principal principal) {
        Usuario user=citasService.getUsuariosbyUsername(principal.getName());
        Long idSede=user.getIdSede();
        empleadoService.update(idEmpleado, nombre, apellido, telefono, correo, fecha, salario, idPuesto, idSede, estado,username);
        return "redirect:/adminemployees/listado";
    }
    
}
