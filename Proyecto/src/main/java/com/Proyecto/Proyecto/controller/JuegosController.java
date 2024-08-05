package com.Proyecto.Proyecto.controller;

import com.Proyecto.Proyecto.Domain.Juegos;
import com.Proyecto.Proyecto.Service.JuegosService;
import com.Proyecto.Proyecto.Service.CategoriaService; // Importar el servicio de categorías
import com.Proyecto.Proyecto.Domain.Categorias; // Importar la clase de categorías
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
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

@Controller
@RequestMapping("/juego")
public class JuegosController {

    @Autowired
    private JuegosService juegosService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/juegos")
    public String mostrarJuegos(Model model) {
        var juegos = juegosService.getJuegos();
        model.addAttribute("juegos", juegos);
        List<Categorias> categorias2 = juegosService.cateMask();
        Map<Long, String> categoriasMap = categorias2.stream()
                .collect(Collectors.toMap(Categorias::getIdCategoria, Categorias::getDescripcion));
        List<Categorias> categorias = juegosService.getCates();
        model.addAttribute("categorias", categorias);
        model.addAttribute("categoriasMap", categoriasMap);
        return "juego/juegos";
    }

    @GetMapping("/nuevo")
    public String hotelNuevo(Juegos juego) {
        return "/juego/modifica";
    }

    @PostMapping("/guardar")
    public String hotelGuardar(Juegos juego) {
        juegosService.save(juego);
        return "redirect:/juego/juegos";
    }

    @GetMapping("/eliminar/{id_juego}")
    public String hotelEliminar(Juegos juego) {
        juegosService.delete(juego);
        return "redirect:/juego/juegos";
    }

    @GetMapping("/modificar/{id_juego}")
    public String hotelModificar(Juegos juego, Model model) {
        juego = juegosService.getJuego(juego);
        model.addAttribute("juego", juego);

        // Obtener todas las categorías y agregarlas al modelo
        List<Categorias> categorias = juegosService.getCates();
        model.addAttribute("categorias", categorias);

        return "/juego/modifica";
    }

    @PostMapping("/modificar2")
    public String categoriaModificar2(@RequestParam("id_juego") Long idJuego,
            @RequestParam("imagen") String imagen,
            @RequestParam("nombre") String nombre,
            @RequestParam("empresa") String empresa,
            @RequestParam("precio") double precio,
            @RequestParam("existencias") int existencias,
            @RequestParam("estado") boolean estado,
            @RequestParam("idcategoria") Long idcategoria) {
        juegosService.update(idJuego, imagen, nombre, empresa, precio, existencias, estado, idcategoria);
        return "redirect:/juego/juegos";
    }
}
