/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto.Proyecto.Service;

import com.Proyecto.Proyecto.Domain.Sedes;
import java.util.List;

/**
 *
 * @author hhern
 */
public interface SedesService {
    public List<Sedes> getSedes();
    public Sedes getSede(Sedes categoria);
    public void save(Sedes categoria);
    public void delete(Sedes categoria);
    public void update(Long IDS,String NAME,String CITY,String DIR,String PHONE,boolean EST);
}
