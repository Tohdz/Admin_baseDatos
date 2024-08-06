/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.Proyecto.Proyecto.Domain.Usuario;
import com.Proyecto.Proyecto.Service.RegistroService;
import com.Proyecto.Proyecto.Service.UsuarioService;
import org.springframework.ui.Model;
@Controller
@Slf4j
@RequestMapping("/registro")
public class RegistroController {
    @Autowired
    private RegistroService registroService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/nuevo")
    public String nuevo(Usuario usuario,Model model) {
        var sedes = usuarioService.getSedes();
        model.addAttribute("sedes", sedes);
        return "/registro/new";
    }

    @PostMapping("/crearUsuario")
    public String crearUsuario(Usuario usuario,Model model){
        if (!usuarioService.existeUsuarioPorUsernameOCorreo(usuario.getUsername(), usuario.getCorreo())) {
            registroService.save(usuario);
            return "/login";
        } else {
            model.addAttribute("error", "El usuario ya existe.");
            return "/registro/new"; 
        }
    }

}
