/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Service.Impl;

import com.Proyecto.Proyecto.Dao.EmpleadoDao;
import com.Proyecto.Proyecto.Domain.Empleado;
import com.Proyecto.Proyecto.Domain.Puestos;
import com.Proyecto.Proyecto.Domain.Sedes;
import com.Proyecto.Proyecto.Service.EmpleadoService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hhern
 */
@Service
public class EmpleadoServiceImpl implements EmpleadoService{
    
    @Autowired
    private EmpleadoDao empleadoDao;

    @Override
    public List<Empleado> getEmpleados() {
        var lista = empleadoDao.getListEmpleado();
        return lista;
    }

    @Override
    public List<Puestos> getPuestos() {
        var lista = empleadoDao.getPuestos();
        return lista;
    }

    @Override
    public List<Sedes> getSedes() {
        var lista = empleadoDao.getSedes();
        return lista;
    }

    @Override
    public Empleado getEmpleado(Empleado empleado) {
        return empleadoDao.getOneEmpleado(empleado.getIdEmpleado());
    }

    @Override
    public void save(Empleado empleado) {
        empleadoDao.saveEmpleado(empleado);
    }

    @Override
    public void delete(Empleado empleado) {
        empleadoDao.deleteEmpleado(empleado.getIdEmpleado());
    }

    @Override
    public void update(Long EID, String NOM, String APE, String TEL, String CORRE, Date FECH, String SAL, Long IDP, Long IDS, boolean ACT,String UNAME) {
        empleadoDao.updateEmpleado(EID, NOM, APE, TEL, CORRE, FECH, SAL, IDP, IDS, ACT, UNAME);
    }

    @Override
    public List<Puestos> getPuestosbyState() {
        var lista = empleadoDao.getPuestosbyState();
        return lista;
    }

    @Override
    public List<Sedes> getSedesbyState() {
        var lista = empleadoDao.getSedesbyState();
        return lista;
    }

    @Override
    public Empleado getEmpleadobyusername(String name) {
        return empleadoDao.getEmpleadosbyusername(name);
    }
}
