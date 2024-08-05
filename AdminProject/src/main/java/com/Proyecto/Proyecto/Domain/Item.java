/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)

public class Item extends Juegos {

    private int cantidad; //Almacenar la cantidad de items de un producto

    public Item() {
    }

    public Item(Juegos juegos) {
        super.setId_juego(juegos.getId_juego());
        super.setImagen(juegos.getImagen());
        super.setNombre(juegos.getNombre());
        super.setEmpresa(juegos.getEmpresa());
        super.setPrecio(juegos.getPrecio());
        super.setExistencias(juegos.getExistencias());
        super.setEstado(juegos.isEstado());
        super.setIdcategoria(juegos.getIdcategoria());
        this.cantidad = 0;
    }

}
