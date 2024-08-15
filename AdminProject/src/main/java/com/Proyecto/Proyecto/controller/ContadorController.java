/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.controller;

import com.Proyecto.Proyecto.Dao.Detalle_FacturaDao;
import com.Proyecto.Proyecto.Dao.FacturaDao;
import com.Proyecto.Proyecto.Domain.Detalle_Factura;
import com.Proyecto.Proyecto.Domain.Factura;
import com.Proyecto.Proyecto.Domain.Repuestos;
import com.Proyecto.Proyecto.Domain.Usuario;
import com.Proyecto.Proyecto.Service.CitasService;
import com.Proyecto.Proyecto.Service.RepuestosService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author hhern
 */
@Controller
@RequestMapping("/contadores")
public class ContadorController {

    @Autowired
    private FacturaDao facturadao;

    @Autowired
    private Detalle_FacturaDao detalledao;

    @Autowired
    private CitasService citasService;

    @Autowired
    private RepuestosService repuestosService;

    @GetMapping("/listado")
    private String listado(Model model, Principal principal) {
        Usuario user = citasService.getUsuariosbyUsername(principal.getName());
        var facturas = facturadao.getFacturas(user.getIdSede());
        model.addAttribute("facturas", facturas);
        return "/contadores/listado";
    }

    @GetMapping("/modificar/{idFactura}")
    public String ordenModificar(@PathVariable Long idFactura, Model model) {
        Factura factura = facturadao.getfacturabyid(idFactura);
        model.addAttribute("factura", factura);
        List<Detalle_Factura> detalles = detalledao.getDetalles(idFactura);
        model.addAttribute("detalles", detalles);
        //
        List<Repuestos> repuestos = repuestosService.getRepuestos();
        Map<Long, String> repuestosMap = repuestos.stream()
                .collect(Collectors.toMap(Repuestos::getIdRepuesto, Repuestos::getNombre));
        model.addAttribute("repuestosMap", repuestosMap);
        //
        List<String> lista = detalledao.getplacasbyorden();
        Map<Long, String> listaMap = lista.stream()
                .collect(Collectors.toMap(str -> Long.parseLong(str.split(":")[0].trim()),str -> str.split(":")[1].trim()));
        model.addAttribute("listaMap", listaMap);
        return "/contadores/modifica";
    }

}
