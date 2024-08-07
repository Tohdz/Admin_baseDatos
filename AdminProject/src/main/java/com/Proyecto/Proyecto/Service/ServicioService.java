/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto.Proyecto.Service;

import com.Proyecto.Proyecto.Domain.Servicios;
import java.util.List;

/**
 *
 * @author hhern
 */
public interface ServicioService {
    public List<Servicios> getServicios();
    public Servicios getServicio(Servicios servicio);
    public void save(Servicios servicio);
    public void delete(Servicios servicio);
    public void update(Long CID, String NOM,boolean ACT);
}
