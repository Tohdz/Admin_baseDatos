package com.Proyecto.Proyecto.Service.Impl;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.Proyecto.Proyecto.Dao.FacturaDao;
import com.Proyecto.Proyecto.Dao.RepuestosDao;
import com.Proyecto.Proyecto.Domain.Factura;
import com.Proyecto.Proyecto.Domain.Item;
import com.Proyecto.Proyecto.Domain.Repuestos;
import com.Proyecto.Proyecto.Domain.Usuario;
import com.Proyecto.Proyecto.Domain.Detalle_Factura;
import com.Proyecto.Proyecto.Service.ItemService;
import static com.Proyecto.Proyecto.Service.ItemService.listaItems;
import com.Proyecto.Proyecto.Service.UsuarioService;
import com.Proyecto.Proyecto.Dao.Detalle_FacturaDao;
import java.util.Calendar;

@Service
public class ItemServiceImpl implements ItemService {

    @Override
    public List<Item> gets() {
        return listaItems;
    }
    //Se usa en el addCarrito... agrega un elemento

    @Override
    public void save(Item item) {
        boolean existe = false;
        for (Item i : listaItems) {

            //Busca si ya existe el producto en el carrito
            if (Objects.equals(i.getIdRepuesto(), item.getIdRepuesto())) {
                //Valida si aún puede colocar un item adicional -segun existencias-
                if (i.getCantidad() < item.getCantidad()) {
                    //Incrementa en 1 la cantidad de elementos
                    i.setCantidad(i.getCantidad() + 1);
                }
                existe = true;
                break;
            }
        }
        if (!existe) {//Si no está el producto en el carritose agrega cantidad =1. 
            item.setCantidad(1);
            listaItems.add(item);
        }
    }

    //Se usa para eliminar un producto del carrito
    @Override
    public void delete(Item item) {
        var posicion = -1;
        var existe = false;
        for (Item i : listaItems) {
            ++posicion;
            if (Objects.equals(i.getIdRepuesto(), item.getIdRepuesto())) {
                existe = true;
                break;
            }
        }
        if (existe) {
            listaItems.remove(posicion);
        }
    }

    //Se obtiene la información de un producto del carrito...para modificarlo
    @Override
    public Item get(Item item) {
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdRepuesto(), item.getIdRepuesto())) {
                return i;
            }
        }
        return null;
    }

    //Se usa en la página para actualizar la cantidad de productos
    @Override
    public void actualiza(Item item) {
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdRepuesto(), item.getIdRepuesto())) {
                i.setCantidad(item.getCantidad());
                break;
            }
        }
    }

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private FacturaDao facturaDao;
    @Autowired
    private Detalle_FacturaDao Detalle_FacturaDao;
    @Autowired
    private RepuestosDao repuestoDao;

    @Override
    public void facturar() {
        System.out.println("Facturando");
        //Se obtiene el usuario autenticado
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }
        if (username.isBlank()) {
            return;
        }
        Usuario usuario = usuarioService.getUsuarioPorUsername(username);
        if (usuario == null) {
            return;
        }
        Factura factura = new Factura(usuario.getIdUsuario(),usuario.getIdSede());
        facturaDao.savefactura(factura);
        factura=facturaDao.getfactura(Calendar.getInstance().getTime());
        double total = 0;
        for (Item i : listaItems) {
            System.out.println("Juego: " + i.getNombre()+ " Cantidad: " + i.getCantidad() + " Total: " + i.getPrecio() * i.getCantidad());
            Detalle_Factura detalle_factura = new Detalle_Factura(factura.getIdFactura(),i.getIdRepuesto(), i.getPrecio(), i.getCantidad());
            Detalle_FacturaDao.savedetalle(detalle_factura);
            Repuestos repuesto = repuestoDao.getRepuesto(i.getIdRepuesto());
            Long id=repuesto.getIdRepuesto();
            String img=repuesto.getImagen();
            String nom=repuesto.getNombre();
            Long mid=repuesto.getIdMarca();
            double prec=repuesto.getPrecio();
            int cant=(repuesto.getCantidad()- i.getCantidad());
            Long idc=repuesto.getIdCategoria();
            Long idsed=repuesto.getIdSede();
            boolean est=repuesto.isEstado();
            repuestoDao.updateRepuesto(id,img,nom,mid,prec,cant,idc,idsed,est);
            total += i.getPrecio() * i.getCantidad();
        }
        facturaDao.updatefactura(factura.getIdFactura(),total);
        listaItems.clear();
    }


}
