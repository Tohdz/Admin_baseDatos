package com.Proyecto.Proyecto.controller;

import com.Proyecto.Proyecto.Domain.Sedes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.Proyecto.Proyecto.Domain.Usuario;
import com.Proyecto.Proyecto.Service.UsuarioService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var usuarios = usuarioService.getUsuarios();
        var sedes = usuarioService.getSedes();
        model.addAttribute("sedes", sedes);
        model.addAttribute("usuarios", usuarios);
        List<Sedes> sedes2 = usuarioService.getSedes();
        Map<Long, String> sedesMap = sedes2.stream()
                .collect(Collectors.toMap(Sedes::getIdSede, Sedes::getNombre));
        model.addAttribute("sedesMap", sedesMap);
        return "/usuario/listado";
    }

    @GetMapping("/nuevo")
    public String usuarioNuevo(Usuario usuario) {
        return "/usuario/modifica";
    }

    @PostMapping("/guardar")
    public String usuarioGuardar(Usuario usuario) {
        var codigo = new BCryptPasswordEncoder();
        usuario.setPassword(codigo.encode(usuario.getPassword()));
        usuarioService.save2(usuario);
        return "redirect:/usuario/listado";
    }

    @GetMapping("/eliminar/{idUsuario}")
    public String usuarioEliminar(Usuario usuario) {
        usuarioService.delete(usuario);
        return "redirect:/usuario/listado";
    }

    @GetMapping("/modificar/{idUsuario}")
    public String usuarioModificar(Usuario usuario, Model model) {
        usuario = usuarioService.getUsuario(usuario);
        model.addAttribute("usuario", usuario);
        var sedes = usuarioService.getSedes();
        model.addAttribute("sedes", sedes);
        return "/usuario/modifica";
    }

    @PostMapping("/modificar2")
    public String usuarioModificar2(@RequestParam("idUsuario") Long idUsuario, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("nombre") String nombre,
            @RequestParam("apellidos") String apellidos, @RequestParam("correo") String correo, @RequestParam("telefono") String telefono, @RequestParam("idSede") Long idSede, @RequestParam(value = "estado", defaultValue = "false") boolean estado) {
        var codigo = new BCryptPasswordEncoder();
        usuarioService.updateuser(idUsuario, username, codigo.encode(password), nombre, apellidos, correo, telefono, idSede, estado);
        return "redirect:/usuario/listado";
    }
}
