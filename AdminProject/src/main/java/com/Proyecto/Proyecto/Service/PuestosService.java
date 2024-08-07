/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto.Proyecto.Service;

import com.Proyecto.Proyecto.Domain.Puestos;
import java.util.List;

/**
 *
 * @author hhern
 */
public interface PuestosService {
     public List<Puestos> getPuestos();
    public Puestos getPuesto(Puestos puesto);
    public void save(Puestos puesto);
    public void delete(Puestos puesto);
    public void update(Long CID, String NOM,boolean ACT);
}
