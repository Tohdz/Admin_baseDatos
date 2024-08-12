/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.controller;

import com.Proyecto.Proyecto.Domain.Citas;
import com.Proyecto.Proyecto.Domain.Empleado;
import com.Proyecto.Proyecto.Domain.Sedes;
import com.Proyecto.Proyecto.Domain.Servicios;
import com.Proyecto.Proyecto.Domain.Usuario;
import com.Proyecto.Proyecto.Domain.Vehiculos;
import com.Proyecto.Proyecto.Service.CitasService;
import java.security.Principal;
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
@RequestMapping("/clientscitas")
public class ClientsCitasController {
    
    @Autowired
    private CitasService citasService;

    @GetMapping("/listado")
    private String listado(Model model,Principal principal) {
        Usuario user=citasService.getUsuariosbyUsername(principal.getName());
        var citas = citasService.getCitasbyuser(user.getIdUsuario());
        model.addAttribute("citas", citas);
        //
        List<Vehiculos> vehiculos = citasService.getVehiculosbyuserandstate(user.getIdUsuario());
        model.addAttribute("vehiculos", vehiculos);
        List<Servicios> servicios = citasService.getServiciosbyState();
        model.addAttribute("servicios", servicios);
        List<Sedes> sedes = citasService.getSedesbyState();
        model.addAttribute("sedes", sedes);
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
        return "/clientscitas/listado";
    }

    @PostMapping("/guardar")
    public String citaGuardar(Citas cita) {
        cita.setEstado(true);
        citasService.save(cita);
        return "redirect:/clientscitas/listado";
    }

    @GetMapping("/eliminar/{idCita}")
    public String citaEliminar(Citas cita) {
        citasService.delete(cita);
        return "redirect:/clientscitas/listado";
    }

    @GetMapping("/modificar/{idCita}")
    public String citaModificar(Citas cita, Model model,Principal principal) {
        Usuario user=citasService.getUsuariosbyUsername(principal.getName());
        cita = citasService.getCita(cita);
        model.addAttribute("cita", cita);
        List<Vehiculos> vehiculos = citasService.getVehiculosbyuserandstate(user.getIdUsuario());
        model.addAttribute("vehiculos", vehiculos);
        List<Servicios> servicios = citasService.getServiciosbyState();
        model.addAttribute("servicios", servicios);
        List<Sedes> sedes = citasService.getSedesbyState();
        model.addAttribute("sedes", sedes);
        return "/clientscitas/modifica";
    }

    @PostMapping("/modificar2")
    public String citaModificar2(@RequestParam("idCita") Long idCita, @RequestParam("placa") String placa, @RequestParam("fechaHora") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaHora, @RequestParam("idServicio") Long idServicio,@RequestParam("idSede") Long idSede) {
        boolean estado=true;
        Long idEmpleado=null;
        citasService.update(idCita, placa, fechaHora, idServicio, idEmpleado, idSede, estado);
        return "redirect:/clientscitas/listado";
    }
}
