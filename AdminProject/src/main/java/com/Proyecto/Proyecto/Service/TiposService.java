/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto.Proyecto.Service;

import com.Proyecto.Proyecto.Domain.Tipos;
import java.util.List;

/**
 *
 * @author hhern
 */
public interface TiposService {
    public List<Tipos> getTipos();
    public Tipos getTipo(Tipos servicio);
    public void save(Tipos servicio);
    public void delete(Tipos servicio);
    public void update(Long CID, String NOM,boolean ACT);
}
