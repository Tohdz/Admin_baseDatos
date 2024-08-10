/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Service.Impl;

import com.Proyecto.Proyecto.Dao.OrdenesDao;
import com.Proyecto.Proyecto.Domain.Citas;
import com.Proyecto.Proyecto.Domain.Empleado;
import com.Proyecto.Proyecto.Domain.Ordenes;
import com.Proyecto.Proyecto.Domain.Sedes;
import com.Proyecto.Proyecto.Service.OrdenesService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hhern
 */
@Service
public class OrdenServiceImpl implements OrdenesService {
    
    @Autowired
    private OrdenesDao ordenesDao;

    @Override
    public Ordenes getOrden(Ordenes orden) {
        return ordenesDao.getOrden(orden.getIdOrden());
    }

    @Override
    public List<Ordenes> getOrdenes() {
        var lista = ordenesDao.getOrdenes();
        return lista;
    }

    @Override
    public List<Citas> getCitasbyState() {
        var lista = ordenesDao.getCitasbyState();
        return lista;
    }

    @Override
    public List<Empleado> getEmpleadosbyState() {
        var lista = ordenesDao.getEmpleadobyState();
        return lista;
    }

    @Override
    public List<Sedes> getSedesbyState() {
        var lista = ordenesDao.getSedesbyState();
        return lista;
    }

    @Override
    public void save(Ordenes orden) {
        ordenesDao.saveOrden(orden);
    }

    @Override
    public void delete(Ordenes orden) {
        ordenesDao.deleteOrden(orden.getIdOrden());
    }

    @Override
    public void update(Long OID, Long CID, LocalDateTime FECH, String COMS, Long EMPID, Long IDSED) {
        ordenesDao.updateOrden(OID, CID, FECH, COMS, EMPID, IDSED);
    }

    @Override
    public List<Citas> getCitas() {
        var lista = ordenesDao.getCitas();
        return lista;
    }

    @Override
    public List<Empleado> getEmpleados() {
        var lista = ordenesDao.getEmpleados();
        return lista;
    }

    @Override
    public List<Sedes> getSedes() {
        var lista = ordenesDao.getSedes();
        return lista;
    }
    
}
