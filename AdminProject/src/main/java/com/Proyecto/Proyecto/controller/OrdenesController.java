/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.controller;

import com.Proyecto.Proyecto.Domain.Citas;
import com.Proyecto.Proyecto.Domain.Ordenes;
import com.Proyecto.Proyecto.Domain.Empleado;
import com.Proyecto.Proyecto.Domain.Sedes;
import com.Proyecto.Proyecto.Service.OrdenesService;
import java.time.LocalDateTime;
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
@RequestMapping("/ordenes")
public class OrdenesController {
    @Autowired
    private OrdenesService ordenesService;

    @GetMapping("/listado")
    private String listado(Model model) {
        var ordenes = ordenesService.getOrdenes();
        model.addAttribute("ordenes", ordenes);
        //
        List<Citas> citas = ordenesService.getCitasbyState();
        model.addAttribute("citas", citas);
        List<Empleado> empleados = ordenesService.getEmpleadosbyState();
        model.addAttribute("empleados", empleados);
        List<Sedes> sedes = ordenesService.getSedesbyState();
        model.addAttribute("sedes", sedes);
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
        return "/ordenes/listado";
    }

    @PostMapping("/guardar")
    public String ordenGuardar(@RequestParam(value = "estado", defaultValue = "false") boolean estado,Ordenes orden) {
        orden.setEstado(estado);
        ordenesService.save(orden);
        return "redirect:/ordenes/listado";
    }

    @GetMapping("/eliminar/{idOrden}")
    public String ordenEliminar(Ordenes orden) {
        ordenesService.delete(orden);
        return "redirect:/ordenes/listado";
    }

    @GetMapping("/modificar/{idOrden}")
    public String ordenModificar(Ordenes orden, Model model) {
        orden = ordenesService.getOrden(orden);
        model.addAttribute("orden", orden);
        //
        List<Citas> citas = ordenesService.getCitasbyState();
        model.addAttribute("citas", citas);
        List<Empleado> empleados = ordenesService.getEmpleadosbyState();
        model.addAttribute("empleados", empleados);
        List<Sedes> sedes = ordenesService.getSedesbyState();
        model.addAttribute("sedes", sedes);
        return "/ordenes/modifica";
    }

    @PostMapping("/modificar2")
    public String ordenModificar2(@RequestParam("idOrden") Long idOrden, @RequestParam("idCita") Long idCita, @RequestParam("fechaHora") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaHora, @RequestParam("comentario") String comentario, @RequestParam("idEmpleado") Long idEmpleado,@RequestParam("idSede") Long idSede,@RequestParam(value = "estado", defaultValue = "false") boolean estado) {
        ordenesService.update(idOrden, idCita, fechaHora, comentario, idEmpleado, idSede,estado);
        return "redirect:/ordenes/listado";
    }
}
