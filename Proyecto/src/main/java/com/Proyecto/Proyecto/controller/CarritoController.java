/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.controller;

import com.Proyecto.Proyecto.Domain.Categorias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Proyecto.Proyecto.Domain.Item;
import com.Proyecto.Proyecto.Domain.Juegos;
import com.Proyecto.Proyecto.Service.CategoriaService;
import com.Proyecto.Proyecto.Service.ItemService;
import com.Proyecto.Proyecto.Service.JuegosService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarritoController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private JuegosService juegosService;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/")
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

        return "/index";
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

        return "/index";
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

        return "/index"; // Ruta correcta para la vista de juegos
    }

    @PostMapping("/filtrarPorNombre")
    public String filtrarPorNombre(@RequestParam(value = "nombre", required = false) String nombre,
            Model model) {
        List<Juegos> juegos = juegosService.findByNombreContaining(nombre);
        model.addAttribute("juegos", juegos);
        return "/index"; // Asegúrate de que la vista correspondiente sea la correcta
    }
    //Para ver el carrito

    @GetMapping("/carrito/listado")
    public String inicio(Model model) {
        var items = itemService.gets();
        model.addAttribute("items", items);
        var carritoTotalVenta = 0;
        for (Item i : items) {
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("carritoTotal",
                carritoTotalVenta);
        return "/carrito/listado";
    }

    //Para Agregar un producto al carrito
    @GetMapping("/carrito/agregar/{id_juego}")
    public ModelAndView agregarItem(Model model, Item item) {
        Item item2 = itemService.get(item);
        System.out.println(item);
        if (item2 == null) {
            Juegos juegos = juegosService.getJuego(item);
            item2 = new Item(juegos);

        }
        itemService.save(item2);
        System.out.println(item2);
        var lista = itemService.gets();
        var totalCarritos = 0;
        var carritoTotalVenta = 0;
        for (Item i : lista) {
            totalCarritos += i.getCantidad();
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("listaItems", lista);
        model.addAttribute("listaTotal", totalCarritos);
        model.addAttribute("carritoTotal", carritoTotalVenta);
        return new ModelAndView("/carrito/fragmentos :: verCarrito");
    }
    //Para mofificar un producto del carrito

    @GetMapping("/carrito/modificar/{id_juego}")
    public String modificarItem(Item item, Model model) {
        item = itemService.get(item);
        model.addAttribute("item", item);
        return "/carrito/modificar";
    }
    //Para eliminar un elemento del carrito

    @GetMapping("/carrito/eliminar/{id_juego}")
    public String eliminarItem(Item item) {
        itemService.delete(item);
        return "redirect:/carrito/listado";
    }
    //Para actualizar un producto del carrito (cantidad)

    @PostMapping("/carrito/guardar")
    public String guardarItem(Item item) {

        itemService.actualiza(item);
        return "redirect:/carrito/listado";
    }
    //Para facturar los productos del carrito... no implementado...

    @GetMapping("/facturar/carrito")
    public String facturarCarrito() {
        itemService.facturar();
        return "redirect:/carrito/mensaje";
    }

}
