/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.controller;

import com.Proyecto.Proyecto.Domain.Citas;
import com.Proyecto.Proyecto.Domain.Empleado;
import com.Proyecto.Proyecto.Domain.Ordenes;
import com.Proyecto.Proyecto.Domain.Sedes;
import com.Proyecto.Proyecto.Domain.Servicios;
import com.Proyecto.Proyecto.Domain.Usuario;
import com.Proyecto.Proyecto.Service.CitasService;
import com.Proyecto.Proyecto.Service.EmpleadoService;
import com.Proyecto.Proyecto.Service.OrdenesService;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author hhern
 */
@Controller
@RequestMapping("/mecanicos")
public class MecController {
    
    @Autowired
    private CitasService citasService;
    
    @Autowired
    private OrdenesService ordenesService;
    
    @Autowired
    private EmpleadoService empleadoService;
    
    @GetMapping("/listado")
    private String listado(Model model) {
        var citas = citasService.getCitasbyState();
        model.addAttribute("citas", citas);
        //
        List<Servicios> servicios2 = citasService.getServicios();
        Map<Long, String> serviciosMap = servicios2.stream()
                .collect(Collectors.toMap(Servicios::getIdServicio, Servicios::getNombre));
        model.addAttribute("serviciosMap", serviciosMap);
        //
        List<Sedes> sedes2 = citasService.getSedes();
        Map<Long, String> sedesMap = sedes2.stream()
                .collect(Collectors.toMap(Sedes::getIdSede, Sedes::getNombre));
        model.addAttribute("sedesMap", sedesMap);
        return "/mecanicos/listado";
    }
    
    @GetMapping("/modificar/{idCita}")
    public String citaModificar(@PathVariable Long idCita, Model model) {
        Citas cita = citasService.getCitaid(idCita);
        model.addAttribute("cita", cita);
        return "/mecanicos/modifica";
    }
    
    @PostMapping("/guardar")
    public String ordenGuardar(@RequestParam("idCita") Long idCita,Principal principal,Ordenes orden) {
        Usuario user=citasService.getUsuariosbyUsername(principal.getName());
        Empleado empleado=empleadoService.getEmpleadobyusername(user.getUsername());
        Citas cita=citasService.getCitaid(idCita);
        String placa=cita.getPlaca();
        LocalDateTime fechaHora=cita.getFechaHora();
        Long idServicio=cita.getIdServicio();
        Long idEmpleado=empleado.getIdEmpleado();
        Long idSede=cita.getIdSede();
        boolean estado=false;
        citasService.update(idCita, placa, fechaHora, idServicio, idEmpleado, idSede, estado);
        orden.setIdCita(idCita);
        orden.setIdEmpleado(empleado.getIdEmpleado());
        orden.setIdSede(empleado.getIdSede());
        orden.setEstado(true);
        ordenesService.save(orden);
        return "redirect:/mecanicos/listado";
    }
}
