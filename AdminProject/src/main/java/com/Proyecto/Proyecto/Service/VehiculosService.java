/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto.Proyecto.Service;

import com.Proyecto.Proyecto.Domain.Marcas;
import com.Proyecto.Proyecto.Domain.Modelos;
import com.Proyecto.Proyecto.Domain.Sedes;
import com.Proyecto.Proyecto.Domain.Tipos;
import com.Proyecto.Proyecto.Domain.Usuario;
import com.Proyecto.Proyecto.Domain.Vehiculos;
import java.util.List;

/**
 *
 * @author hhern
 */
public interface VehiculosService {
    public List<Vehiculos> getVehiculos();
    public List<Marcas> getMarcas();
    public List<Modelos> getModelos(Long MID);
    public List<Modelos> getModelos2();
    public List<Tipos> getTipos();
    public List<Sedes> getSedes();
    public List<Usuario> getUsuarios();
    public Vehiculos getVehiculo(Vehiculos vehiculo);
    public void save(Vehiculos vehiculo);
    public void delete(Vehiculos vehiculo);
    public void update(String plac,Long mid,Long idm,Long tid,int ano,Long ids,Long usid,boolean act);
}
