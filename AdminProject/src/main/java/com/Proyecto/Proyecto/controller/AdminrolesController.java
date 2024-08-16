/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.controller;

import com.Proyecto.Proyecto.Domain.Rol;
import com.Proyecto.Proyecto.Domain.Usuario;
import com.Proyecto.Proyecto.Service.CitasService;
import com.Proyecto.Proyecto.Service.RolService;
import java.security.Principal;
import java.util.Arrays;
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
@RequestMapping("/adminroles")
public class AdminrolesController {
    
    @Autowired
    private RolService rolService;
    
    @Autowired
    private CitasService citasService;
    
    @GetMapping("/listado")
    private String listado(Model model,Principal principal) {
        Usuario user=citasService.getUsuariosbyUsername(principal.getName());
        var roles = rolService.getRolesbysede(user.getIdSede());
        model.addAttribute("roles", roles);
        //
        List<Usuario> usuarios = rolService.getUsuariobyStateandsede(user.getIdSede());
        model.addAttribute("usuarios", usuarios);
        //
        List<String> rols = Arrays.asList("ROLE_USER", "ROLE_MEC","ROLE_CASH","ROLE_CONT");
        model.addAttribute("rols", rols);
        //
        
        //
        List<Usuario> usuarios2 = rolService.getUsuarios();
        Map<Long, String> usuariosMap = usuarios2.stream()
                .collect(Collectors.toMap(Usuario::getIdUsuario, Usuario::getUsername));
        model.addAttribute("usuariosMap", usuariosMap);
        return "/adminroles/listado";
    }


    @PostMapping("/guardar")
    public String rolGuardar(Rol rol) {
        rolService.save(rol);
        return "redirect:/adminroles/listado";
    }

    @GetMapping("/eliminar/{idRol}")
    public String rolEliminar(Rol rol) {
        rolService.delete(rol);
        return "redirect:/adminroles/listado";
    }

    @GetMapping("/modificar/{idRol}")
    public String rolModificar(Rol rol, Model model,Principal principal) {
        Usuario user=citasService.getUsuariosbyUsername(principal.getName());
        rol = rolService.getRol(rol);
        model.addAttribute("rol", rol);
        List<Usuario> usuarios = rolService.getUsuariobyStateandsede(user.getIdSede());
        model.addAttribute("usuarios", usuarios);
        List<String> rols = Arrays.asList("ROLE_USER", "ROLE_MEC","ROLE_CASH","ROLE_CONT");
        model.addAttribute("rols", rols);
        return "/adminroles/modifica";
    }

    @PostMapping("/modificar2")
    public String rolModificar2(@RequestParam("idRol")Long idRol, @RequestParam("nombre") String nombre,@RequestParam("idUsuario")Long idUsuario) {
        rolService.update(idRol, nombre, idUsuario);
        return "redirect:/adminroles/listado";
    }
}
