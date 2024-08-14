/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.controller;

import com.Proyecto.Proyecto.Dao.Detalle_FacturaDao;
import com.Proyecto.Proyecto.Dao.FacturaDao;
import com.Proyecto.Proyecto.Domain.Citas;
import com.Proyecto.Proyecto.Domain.Detalle_Factura;
import com.Proyecto.Proyecto.Domain.Empleado;
import com.Proyecto.Proyecto.Domain.Factura;
import com.Proyecto.Proyecto.Domain.Ordenes;
import com.Proyecto.Proyecto.Domain.Sedes;
import com.Proyecto.Proyecto.Domain.Usuario;
import com.Proyecto.Proyecto.Service.CitasService;
import com.Proyecto.Proyecto.Service.OrdenesService;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author hhern
 */
@Controller
@RequestMapping("/cajeros")
public class CashController {
    
    @Autowired
    private OrdenesService ordenesService;
    
    @Autowired
    private CitasService citasService;
    
    @Autowired
    private FacturaDao facturadao;
    
    @Autowired
    private Detalle_FacturaDao detalledao;

    @GetMapping("/listado")
    private String listado(Model model,Principal principal) {
        Usuario user=citasService.getUsuariosbyUsername(principal.getName());
        var ordenes = ordenesService.getOrdenesbyStateandSede(user.getIdSede());
        model.addAttribute("ordenes", ordenes);
        //
        List<Citas> citas2 = ordenesService.getCitas();
        Map<Long, String> citasMap = citas2.stream()
                .collect(Collectors.toMap(Citas::getIdCita, Citas::getPlaca));
        model.addAttribute("citasMap", citasMap);
        //
        List<Empleado> empleados2 = ordenesService.getEmpleados();
        Map<Long, String> empleadosMap = empleados2.stream()
                .collect(Collectors.toMap(Empleado::getIdEmpleado, Empleado::getNombre));
        model.addAttribute("empleadosMap", empleadosMap);
        //
        List<Sedes> sedes2 = ordenesService.getSedes();
        Map<Long, String> sedesMap = sedes2.stream()
                .collect(Collectors.toMap(Sedes::getIdSede, Sedes::getNombre));
        model.addAttribute("sedesMap", sedesMap);
        return "/cajeros/listado";
    }
    
    @GetMapping("/modificar/{idOrden}")
    public String ordenModificar(@PathVariable Long idOrden,Model model) {
        Ordenes orden = ordenesService.getOrdenbyid(idOrden);
        model.addAttribute("orden", orden);
        return "/cajeros/modifica";
    }
    
    @PostMapping("/guardar")
    public String ordenGuardar(@RequestParam("idOrden") Long idOrden,@RequestParam("price") double price,Principal principal) {
        Usuario user2=ordenesService.getusuariobyordenes(idOrden);
        Usuario user=citasService.getUsuariosbyUsername(principal.getName());
        Ordenes orden=ordenesService.getOrdenbyid(idOrden);
        Long idCita=orden.getIdCita();
        LocalDateTime  fechaHora=orden.getFechaHora();
        String comments=orden.getComentario();
        Long idEmpleado=orden.getIdEmpleado();
        Long idSede=orden.getIdSede();
        boolean estado=false;
        ordenesService.update(idOrden, idCita, fechaHora, comments, idEmpleado, idSede, estado);
        //
        Factura factura = new Factura(user2.getIdUsuario(),user.getIdSede());
        facturadao.savefactura(factura);
        factura=facturadao.getfactura(Calendar.getInstance().getTime());
        double total=price+(price*0.13);
        facturadao.updatefactura(factura.getIdFactura(),total);
        //
        Detalle_Factura detalle_factura = new Detalle_Factura();
        detalle_factura.setIdFactura(factura.getIdFactura());
        detalle_factura.setIdOrden(idOrden);
        detalle_factura.setPrecio(price);
        detalle_factura.setCantidad(1);
        detalledao.savedetalle(detalle_factura);
        return "redirect:/cajeros/listado";
    }
}
