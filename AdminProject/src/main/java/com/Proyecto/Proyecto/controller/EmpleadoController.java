/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.controller;

import com.Proyecto.Proyecto.Domain.Empleado;
import com.Proyecto.Proyecto.Domain.Puestos;
import com.Proyecto.Proyecto.Domain.Sedes;
import com.Proyecto.Proyecto.Service.EmpleadoService;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author hhern
 */
@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    
    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/listado")
    private String listado(Model model) {
        var empleados = empleadoService.getEmpleados();
        model.addAttribute("empleados", empleados);
        List<Puestos> puestos = empleadoService.getPuestosbyState();
        model.addAttribute("puestos", puestos);
        List<Sedes> sedes = empleadoService.getSedesbyState();
        model.addAttribute("sedes", sedes);
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
        return "/empleados/listado";
    }

    @PostMapping("/guardar")
    public String empleadoGuardar(@RequestParam(value = "estado", defaultValue = "false") boolean estado, Empleado empleado) {
        empleado.setEstado(estado);
        empleadoService.save(empleado);
        return "redirect:/empleados/listado";
    }

    @GetMapping("/eliminar/{idEmpleado}")
    public String empleadoEliminar(Empleado empleado) {
        empleadoService.delete(empleado);
        return "redirect:/empleados/listado";
    }

    @GetMapping("/modificar/{idEmpleado}")
    public String empleadoModificar(Empleado empleado, Model model) {
        empleado = empleadoService.getEmpleado(empleado);
        model.addAttribute("empleado", empleado);
        List<Puestos> puestos = empleadoService.getPuestosbyState();
        model.addAttribute("puestos", puestos);
        List<Sedes> sedes = empleadoService.getSedesbyState();
        model.addAttribute("sedes", sedes);
        return "/empleados/modifica";
    }

    @PostMapping("/modificar2")
    public String empleadoModificar2(@RequestParam("idEmpleado") Long idEmpleado, @RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido, @RequestParam("telefono") String telefono, @RequestParam("correo") String correo, @RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @RequestParam("salario") String salario, @RequestParam("idPuesto") Long idPuesto, @RequestParam("idSede") Long idSede, @RequestParam(value = "estado", defaultValue = "false") boolean estado) {
        empleadoService.update(idEmpleado, nombre, apellido, telefono, correo, fecha, salario, idPuesto, idSede, estado);
        return "redirect:/empleados/listado";
    }
}
