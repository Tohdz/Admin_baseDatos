/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Service.Impl;

import com.Proyecto.Proyecto.Dao.CitasDao;
import com.Proyecto.Proyecto.Domain.Citas;
import com.Proyecto.Proyecto.Domain.Empleado;
import com.Proyecto.Proyecto.Domain.Sedes;
import com.Proyecto.Proyecto.Domain.Servicios;
import com.Proyecto.Proyecto.Domain.Usuario;
import com.Proyecto.Proyecto.Domain.Vehiculos;
import com.Proyecto.Proyecto.Service.CitasService;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hhern
 */
@Service
public class CitasServiceImpl implements CitasService{
    
    @Autowired
    private CitasDao citasDao;

    @Override
    public Citas getCita(Citas cita) {
        return citasDao.getCita(cita.getIdCita());
    }

    @Override
    public List<Citas> getCitas() {
        var lista = citasDao.getCitas();
        return lista;
    }

    @Override
    public List<Vehiculos> getVehiculosbyState() {
        var lista = citasDao.getVehiculosbyState();
        return lista;
    }

    @Override
    public List<Servicios> getServiciosbyState() {
        var lista = citasDao.getServiciosbyState();
        return lista;
    }

    @Override
    public List<Empleado> getEmpleadosbyState() {
        var lista = citasDao.getEmpleadobyState();
        return lista;
    }

    @Override
    public List<Sedes> getSedesbyState() {
        var lista = citasDao.getSedesbyState();
        return lista;
    }

    @Override
    public void save(Citas cita) {
        citasDao.saveCitas(cita);
    }

    @Override
    public void delete(Citas cita) {
        citasDao.deleteCitas(cita.getIdCita());
    }

    @Override
    public void update(Long ID, String PLAC, LocalDateTime FECH, Long SERID, Long EMPID, Long IDSED, boolean ACT) {
        citasDao.updateCitas(ID, PLAC, FECH, SERID, EMPID, IDSED, ACT);
    }

    @Override
    public List<Servicios> getServicios() {
        var lista = citasDao.getServicios();
        return lista;
    }

    @Override
    public List<Empleado> getEmpleados() {
        var lista = citasDao.getEmpleadoS();
        return lista;
    }

    @Override
    public List<Sedes> getSedes() {
        var lista = citasDao.getSedes();
        return lista;
    }

    @Override
    public List<Vehiculos> getVehiculosbyuserandstate(Long id) {
        var lista = citasDao.getVehiculosbyuserandstate(id);
        return lista;
    }

    @Override
    public Usuario getUsuariosbyUsername(String UNAME) {
        return citasDao.getUsuariobyUsername(UNAME);
    }

    @Override
    public List<Citas> getCitasbyuser(Long id) {
        var lista = citasDao.getCitasbyuser(id);
        return lista;
    }

    @Override
    public List<Citas> getCitasbyState() {
        var lista = citasDao.getCitasbyState();
        return lista;
    }

    @Override
    public Citas getCitaid(Long id) {
        return citasDao.getCita(id);
    }
    
}
