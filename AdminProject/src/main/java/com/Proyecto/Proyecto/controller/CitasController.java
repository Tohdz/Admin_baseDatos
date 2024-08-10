/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.controller;


import com.Proyecto.Proyecto.Domain.Citas;
import com.Proyecto.Proyecto.Domain.Empleado;
import com.Proyecto.Proyecto.Domain.Sedes;
import com.Proyecto.Proyecto.Domain.Servicios;
import com.Proyecto.Proyecto.Domain.Vehiculos;
import com.Proyecto.Proyecto.Service.CitasService;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
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
@RequestMapping("/citas")
public class CitasController {
    
    @Autowired
    private CitasService citasService;

    @GetMapping("/listado")
    private String listado(Model model) {
        var citas = citasService.getCitas();
        model.addAttribute("citas", citas);
        //
        List<Vehiculos> vehiculos = citasService.getVehiculosbyState();
        model.addAttribute("vehiculos", vehiculos);
        List<Servicios> servicios = citasService.getServiciosbyState();
        model.addAttribute("servicios", servicios);
        List<Empleado> empleados = citasService.getEmpleadosbyState();
        model.addAttribute("empleados", empleados);
        List<Sedes> sedes = citasService.getSedesbyState();
        model.addAttribute("sedes", sedes);
        //
//        List<Puestos> puestos2 = citasService.getPuestos();
//        Map<Long, String> puestosMap = puestos2.stream()
//                .collect(Collectors.toMap(Puestos::getIdPuesto, Puestos::getNombre));
//        model.addAttribute("puestosMap", puestosMap);
//        //
//        List<Sedes> sedes2 = citasService.getSedes();
//        Map<Long, String> sedesMap = sedes2.stream()
//                .collect(Collectors.toMap(Sedes::getIdSede, Sedes::getNombre));
//        model.addAttribute("sedesMap", sedesMap);
        return "/citas/listado";
    }

    @PostMapping("/guardar")
    public String citaGuardar(@RequestParam(value = "estado", defaultValue = "false") boolean estado, Citas cita) {
        cita.setEstado(estado);
        citasService.save(cita);
        return "redirect:/citas/listado";
    }

    @GetMapping("/eliminar/{idCita}")
    public String citaEliminar(Citas cita) {
        citasService.delete(cita);
        return "redirect:/citas/listado";
    }

    @GetMapping("/modificar/{idCita}")
    public String citaModificar(Citas cita, Model model) {
        cita = citasService.getCita(cita);
        model.addAttribute("cita", cita);
        List<Vehiculos> vehiculos = citasService.getVehiculosbyState();
        model.addAttribute("vehiculos", vehiculos);
        List<Servicios> servicios = citasService.getServiciosbyState();
        model.addAttribute("servicios", servicios);
        List<Empleado> empleados = citasService.getEmpleadosbyState();
        model.addAttribute("empleados", empleados);
        List<Sedes> sedes = citasService.getSedesbyState();
        model.addAttribute("sedes", sedes);
        return "/citas/modifica";
    }

    @PostMapping("/modificar2")
    public String citaModificar2(@RequestParam("idCita") Long idCita, @RequestParam("placa") String placa, @RequestParam("fechaHora") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaHora, @RequestParam("idServicio") Long idServicio, @RequestParam("idEmpleado") Long idEmpleado,@RequestParam("idSede") Long idSede, @RequestParam(value = "estado", defaultValue = "false") boolean estado) {
        citasService.update(idCita, placa, fechaHora, idServicio, idEmpleado, idSede, estado);
        return "redirect:/citas/listado";
    }
}
