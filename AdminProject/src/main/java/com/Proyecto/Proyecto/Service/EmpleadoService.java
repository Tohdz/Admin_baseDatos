/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto.Proyecto.Service;

import com.Proyecto.Proyecto.Domain.Empleado;
import com.Proyecto.Proyecto.Domain.Puestos;
import com.Proyecto.Proyecto.Domain.Sedes;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author hhern
 */
public interface EmpleadoService {
    public List<Empleado> getEmpleados();
    public Empleado getEmpleado(Empleado empleado);
    public Empleado getEmpleadobyusername(String name);
    public List<Puestos> getPuestosbyState();
    public List<Sedes> getSedesbyState();
    public void save(Empleado empleado);
    public void delete(Empleado empleado);
    public void update(Long EID,String NOM,String APE,String TEL,String CORRE,Date FECH,String SAL,Long IDP,Long IDS,boolean ACT,String UNAME);
    public List<Puestos> getPuestos();
    public List<Sedes> getSedes();
}
