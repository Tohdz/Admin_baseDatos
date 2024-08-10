/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto.Proyecto.Service;

import com.Proyecto.Proyecto.Domain.Citas;
import com.Proyecto.Proyecto.Domain.Empleado;
import com.Proyecto.Proyecto.Domain.Sedes;
import com.Proyecto.Proyecto.Domain.Servicios;
import com.Proyecto.Proyecto.Domain.Vehiculos;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 *
 * @author hhern
 */
public interface CitasService {
    public Citas getCita(Citas cita);
    public List<Citas> getCitas();
    public List<Vehiculos> getVehiculosbyState();
    public List<Servicios> getServiciosbyState();
    public List<Empleado> getEmpleadosbyState();
    public List<Sedes> getSedesbyState();
    public void save(Citas cita);
    public void delete(Citas cita);
    public void update(Long ID, String PLAC,LocalDateTime FECH,Long SERID,Long EMPID,Long IDSED,boolean ACT);
    public List<Servicios> getServicios();
    public List<Empleado> getEmpleados();
    public List<Sedes> getSedes();
}
