/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Service.Impl;

import com.Proyecto.Proyecto.Dao.VehiculosDao;
import com.Proyecto.Proyecto.Domain.Marcas;
import com.Proyecto.Proyecto.Domain.Modelos;
import com.Proyecto.Proyecto.Domain.Sedes;
import com.Proyecto.Proyecto.Domain.Tipos;
import com.Proyecto.Proyecto.Domain.Usuario;
import com.Proyecto.Proyecto.Domain.Vehiculos;
import com.Proyecto.Proyecto.Service.VehiculosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hhern
 */
@Service
public class VehiculoServiceImpl implements VehiculosService{
    
    @Autowired
    private VehiculosDao vehiculosDao;

    @Override
    public List<Vehiculos> getVehiculos() {
        var lista = vehiculosDao.getVehiculos();
        return lista;
    }

    @Override
    public List<Marcas> getMarcas() {
        var lista = vehiculosDao.getListMarcas();
        return lista;
    }

    @Override
    public List<Modelos> getModelos(Long MID) {
        var lista = vehiculosDao.getModelos(MID);
        return lista;
    }

    @Override
    public List<Tipos> getTipos() {
        var lista = vehiculosDao.getListTipos();
        return lista;
    }

    @Override
    public List<Sedes> getSedes() {
        var lista = vehiculosDao.getSedes();
        return lista;
    }

    @Override
    public List<Usuario> getUsuarios() {
        var lista = vehiculosDao.getListUsuario();
        return lista;
    }

    @Override
    public Vehiculos getVehiculo(Vehiculos vehiculo) {
        return vehiculosDao.getOneVehiculo(vehiculo.getPlaca());
    }

    @Override
    public void save(Vehiculos vehiculo) {
        vehiculosDao.saveVehiculo(vehiculo);
    }

    @Override
    public void delete(Vehiculos vehiculo) {
        vehiculosDao.deleteVehiculo(vehiculo.getPlaca());
    }

    @Override
    public void update(String plac, Long mid, Long idm, Long tid, int ano, Long ids, Long usid, boolean act) {
        vehiculosDao.updateVehiculo(plac, mid, idm, tid, ano, ids, usid, act);
    }

    @Override
    public List<Modelos> getModelos2() {
        var lista = vehiculosDao.getListModelos();
        return lista;
    }
    
}
