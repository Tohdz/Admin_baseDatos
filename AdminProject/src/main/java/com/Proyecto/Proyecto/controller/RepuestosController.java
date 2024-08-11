package com.Proyecto.Proyecto.controller;

import com.Proyecto.Proyecto.Domain.Repuestos;
import com.Proyecto.Proyecto.Service.CategoriaService; // Importar el servicio de categorías
import com.Proyecto.Proyecto.Domain.Categorias; // Importar la clase de categorías
import com.Proyecto.Proyecto.Domain.Marcas;
import com.Proyecto.Proyecto.Domain.Sedes;
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
import com.Proyecto.Proyecto.Service.RepuestosService;

@Controller
@RequestMapping("/repuestos")
public class RepuestosController {

    @Autowired
    private RepuestosService repuestosService;


    @GetMapping("/listado")
    public String mostrarRepuestos(Model model) {
        var repuestos = repuestosService.getRepuestos();
        model.addAttribute("repuestos", repuestos);
        //
        List<Marcas> marcas = repuestosService.getMarcasbyState();
        model.addAttribute("marcas", marcas);
        List<Categorias> categorias = repuestosService.getCategoriasbyState();
        model.addAttribute("categorias", categorias);
        List<Sedes> sedes = repuestosService.getSedesbyState();
        model.addAttribute("sedes", sedes);
        //
        List<Categorias> categorias2 = repuestosService.getCategorias();
        Map<Long, String> categoriasMap = categorias2.stream()
                .collect(Collectors.toMap(Categorias::getIdCategoria, Categorias::getDescripcion));
        model.addAttribute("categoriasMap", categoriasMap);
        //
        List<Marcas> marcas2 = repuestosService.getMarcas();
        Map<Long, String> marcasMap = marcas2.stream()
                .collect(Collectors.toMap(Marcas::getIdMarca, Marcas::getNombre));
        model.addAttribute("marcasMap", marcasMap);
        //
        List<Sedes> sedes2 = repuestosService.getSedes();
        Map<Long, String> sedesMap = sedes2.stream()
                .collect(Collectors.toMap(Sedes::getIdSede, Sedes::getNombre));
        model.addAttribute("sedesMap", sedesMap);
        return "repuestos/listado";
    }


    @PostMapping("/guardar")
    public String Guardar(@RequestParam(value = "estado", defaultValue = "false") boolean estado,Repuestos repuesto) {
        repuesto.setEstado(estado);
        repuestosService.save(repuesto);
        return "redirect:/repuestos/listado";
    }

    @GetMapping("/eliminar/{idRepuesto}")
    public String Eliminar(Repuestos repuesto) {
        repuestosService.delete(repuesto);
        return "redirect:/repuestos/listado";
    }

    @GetMapping("/modificar/{idRepuesto}")
    public String Modificar(Repuestos repuesto, Model model) {
        repuesto = repuestosService.getRepuesto(repuesto);
        model.addAttribute("repuesto", repuesto);
        //
        List<Marcas> marcas = repuestosService.getMarcasbyState();
        model.addAttribute("marcas", marcas);
        List<Categorias> categorias = repuestosService.getCategoriasbyState();
        model.addAttribute("categorias", categorias);
        List<Sedes> sedes = repuestosService.getSedesbyState();
        model.addAttribute("sedes", sedes);
        return "/repuestos/modifica";
    }

    @PostMapping("/modificar2")
    public String Modificar2(@RequestParam("idRepuesto") Long idRepuesto,
            @RequestParam("imagen") String imagen,
            @RequestParam("nombre") String nombre,
            @RequestParam("idMarca") Long idMarca,
            @RequestParam("precio") double precio,
            @RequestParam("cantidad") int cantidad,
            @RequestParam("idCategoria") Long idCategoria,
            @RequestParam("idSede") Long idSede,
            @RequestParam(value="estado",defaultValue = "false") boolean estado){
        repuestosService.update(idRepuesto,imagen,nombre,idMarca,precio,cantidad,idCategoria,idSede,estado);
        return "redirect:/repuestos/listado";
    }
}
