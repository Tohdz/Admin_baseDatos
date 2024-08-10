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
        super.setId_juego(repuesto.getId_juego());
        super.setImagen(repuesto.getImagen());
        super.setNombre(repuesto.getNombre());
        super.setEmpresa(repuesto.getEmpresa());
        super.setPrecio(repuesto.getPrecio());
        super.setExistencias(repuesto.getExistencias());
        super.setEstado(repuesto.isEstado());
        super.setIdcategoria(repuesto.getIdcategoria());
        this.cantidad = 0;
    }

}
