/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.controller;

import com.Proyecto.Proyecto.Domain.Sedes;
import com.Proyecto.Proyecto.Domain.Usuario;
import com.Proyecto.Proyecto.Service.CitasService;
import com.Proyecto.Proyecto.Service.UsuarioService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@RequestMapping("/adminusers")
public class AdminusersController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private CitasService citasService;

    @GetMapping("/listado")
    public String listado(Model model,Principal principal) {
        Usuario user=citasService.getUsuariosbyUsername(principal.getName());
        var usuarios = usuarioService.getUsuariosbysede(user.getIdSede());
        model.addAttribute("usuarios", usuarios);
        //
        List<Sedes> sedes2 = usuarioService.getSedes();
        Map<Long, String> sedesMap = sedes2.stream()
                .collect(Collectors.toMap(Sedes::getIdSede, Sedes::getNombre));
        model.addAttribute("sedesMap", sedesMap);
        return "/adminusers/listado";
    }

    @GetMapping("/nuevo")
    public String usuarioNuevo(Usuario usuario) {
        return "/adminusers/modifica";
    }

    @PostMapping("/guardar")
    public String usuarioGuardar(Usuario usuario,Principal principal) {
        Usuario user=citasService.getUsuariosbyUsername(principal.getName());
        var codigo = new BCryptPasswordEncoder();
        usuario.setPassword(codigo.encode(usuario.getPassword()));
        usuario.setIdSede(user.getIdSede());
        usuarioService.save2(usuario);
        return "redirect:/adminusers/listado";
    }

    @GetMapping("/eliminar/{idUsuario}")
    public String usuarioEliminar(Usuario usuario) {
        usuarioService.delete(usuario);
        return "redirect:/adminusers/listado";
    }

    @GetMapping("/modificar/{idUsuario}")
    public String usuarioModificar(Usuario usuario, Model model) {
        usuario = usuarioService.getUsuario(usuario);
        model.addAttribute("usuario", usuario);
        var sedes = usuarioService.getSedesbyState();
        model.addAttribute("sedes", sedes);
        return "/adminusers/modifica";
    }

    @PostMapping("/modificar2")
    public String usuarioModificar2(@RequestParam("idUsuario") Long idUsuario, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("nombre") String nombre,
            @RequestParam("apellidos") String apellidos, @RequestParam("correo") String correo, @RequestParam("telefono") String telefono,@RequestParam(value = "estado", defaultValue = "false") boolean estado,Principal principal) {
        Usuario user=citasService.getUsuariosbyUsername(principal.getName());
        Long idSede=user.getIdSede();
        var codigo = new BCryptPasswordEncoder();
        usuarioService.updateuser(idUsuario, username, codigo.encode(password), nombre, apellidos, correo, telefono, idSede, estado);
        return "redirect:/adminusers/listado";
    }
    
}
