package com.Proyecto.Proyecto.controller;

import com.Proyecto.Proyecto.Domain.Juegos;
import com.Proyecto.Proyecto.Service.JuegosService;
import com.Proyecto.Proyecto.Service.CategoriaService; // Importar el servicio de categorías
import com.Proyecto.Proyecto.Domain.Categorias; // Importar la clase de categorías
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
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
    public String mostrarJuegos(@RequestParam(name = "precioInf", required = false) Double precioInf,
            @RequestParam(name = "precioSup", required = false) Double precioSup,
            @RequestParam(name = "categoriaId", required = false) Long categoriaId,
            Model model,
            HttpServletRequest request) {
        // Leer los parámetros de la URL
        String queryString = request.getQueryString();
        // Añadir los parámetros al modelo para mantener el estado
        model.addAttribute("queryString", queryString);

        // Lógica para obtener juegos con filtros
        List<Juegos> juegos = juegosService.getJuegosConFiltros(precioInf, precioSup, categoriaId);
        model.addAttribute("juegos", juegos);

        // Obtener todas las categorías y agregarlas al modelo
        List<Categorias> categorias = categoriaService.getCategorias();
        model.addAttribute("categorias", categorias);

        return "juego/juegos";
    }

    @PostMapping("/query1")
    public String consultaQuery1(@RequestParam(value = "precioInf") double precioInf,
            @RequestParam(value = "precioSup") double precioSup,
            @RequestParam(name = "categoriaId", required = false) Long categoriaId,
            Model model) {
        List<Juegos> juegos = juegosService.getJuegosConFiltros(precioInf, precioSup, categoriaId);
        model.addAttribute("juegos", juegos);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);

        List<Categorias> categorias = categoriaService.getCategorias();
        model.addAttribute("categorias", categorias);

        return "juego/juegos";
    }

    @GetMapping("/juegosPorCategoria")
    public String mostrarJuegosPorCategoria(@RequestParam(name = "categoriaId", required = false) Long categoriaId, Model model) {
        List<Juegos> juegos;
        if (categoriaId != null) {
            juegos = juegosService.getJuegosPorCategoria(categoriaId);
        } else {
            juegos = juegosService.getJuegos(null); // Obtener todos los juegos
        }
        model.addAttribute("juegos", juegos);

        // Obtener todas las categorías y agregarlas al modelo
        List<Categorias> categorias = categoriaService.getCategorias();
        model.addAttribute("categorias", categorias);

        return "juego/juegos"; // Ruta correcta para la vista de juegos
    }

    @PostMapping("/filtrarPorNombre")
    public String filtrarPorNombre(@RequestParam(value = "nombre", required = false) String nombre,
            Model model) {
        List<Juegos> juegos = juegosService.findByNombreContaining(nombre);
        model.addAttribute("juegos", juegos);
        return "juego/juegos"; // Asegúrate de que la vista correspondiente sea la correcta
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
        List<Categorias> categorias = categoriaService.getCategorias();
        model.addAttribute("categorias", categorias);

        return "/juego/modifica";
    }
}