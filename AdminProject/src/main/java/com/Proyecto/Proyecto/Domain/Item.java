/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)

public class Item extends Repuestos {

    private int cantidad; //Almacenar la cantidad de items de un producto

    public Item() {
    }

    public Item(Repuestos repuesto) {
        super.setIdRepuesto(repuesto.getIdRepuesto());
        super.setImagen(repuesto.getImagen());
        super.setNombre(repuesto.getNombre());
        super.setIdMarca(repuesto.getIdMarca());
        super.setPrecio(repuesto.getPrecio());
        super.setCantidad(repuesto.getCantidad());
        super.setIdCategoria(repuesto.getIdCategoria());
        super.setIdSede(repuesto.getIdSede());
        super.setEstado(repuesto.isEstado());
        this.cantidad = 0;
    }

}
