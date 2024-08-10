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
import com.Proyecto.Proyecto.Domain.Repuestos;
import com.Proyecto.Proyecto.Service.CategoriaService;
import com.Proyecto.Proyecto.Service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import com.Proyecto.Proyecto.Service.RepuestosService;

@Controller
public class CarritoController {

      @Autowired
    private ItemService itemService;
    @Autowired
    private RepuestosService juegosService;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/")
    public String mostrarJuegos(Model model) {
        // Lógica para obtener juegos con filtros
        List<Repuestos> juegos = juegosService.getJuegos();
        model.addAttribute("juegos", juegos);

        // Obtener todas las categorías y agregarlas al modelo
        List<Categorias> categorias = categoriaService.getCategorias();
        model.addAttribute("categorias", categorias);

        return "/index";
    }

    @GetMapping("/juegosPorCategoria")
    public String mostrarJuegosPorCategoria(@RequestParam(name = "categoriaId", required = false) Long categoriaId, Model model) {
        List<Repuestos> juegos;
        if (categoriaId != null) {
            juegos = juegosService.getJuegosbycate(categoriaId);
        } else {
            juegos = juegosService.getJuegos();
        }
        model.addAttribute("juegos", juegos);

        // Obtener todas las categorías y agregarlas al modelo
        List<Categorias> categorias = categoriaService.getCategorias();
        model.addAttribute("categorias", categorias);

        return "/index"; // Ruta correcta para la vista de juegos
    }

    
    
    //-----------------------------------------------------------------------------------------
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
            Repuestos juegos = juegosService.getJuego(item);
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
    
          @GetMapping("/carrito/sumar/{id_juego}")
    public String sumar(Model model, Item item) {
        Item item2 = itemService.get(item);
        System.out.println(item);
        if (item2 == null) {
            Repuestos juegos = juegosService.getJuego(item);
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
        return "redirect:/carrito/listado";
    }
    @GetMapping("/carrito/restar/{id_juego}")
    public String restar(Model model, Item item) {
        Item item2 = itemService.get(item);
        System.out.println(item);
        if (item2 == null) {
            Repuestos juegos = juegosService.getJuego(item);
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
        return "redirect:/carrito/listado";
    }

}
