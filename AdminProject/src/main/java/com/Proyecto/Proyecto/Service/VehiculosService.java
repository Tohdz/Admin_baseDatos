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
    public Vehiculos getVehiculo(Vehiculos vehiculo);
    public List<Vehiculos> getVehiculos();
    public List<Vehiculos> getVehiculosbyUser(Long id);
    public List<Marcas> getMarcasbyState();
    public List<Modelos> getModelosbyMarca(Long MID);
    public List<Tipos> getTiposbyState();
    public List<Sedes> getSedesbyState();
    public List<Usuario> getUsuariosbyState();
    public Usuario getUsuariosbyUsername(String UNAME);
    public void save(Vehiculos vehiculo);
    public void delete(Vehiculos vehiculo);
    public void update(String plac,Long mid,Long idm,Long tid,int ano,Long ids,Long usid,boolean act);
    public List<Modelos> getModelos();
    public List<Marcas> getMarcas();
    public List<Tipos> getTipos();
    public List<Sedes> getSedes();
    public List<Usuario> getUsuarios();
}
