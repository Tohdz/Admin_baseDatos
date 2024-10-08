/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto.Proyecto.Service;

import com.Proyecto.Proyecto.Domain.Citas;
import com.Proyecto.Proyecto.Domain.Empleado;
import com.Proyecto.Proyecto.Domain.Ordenes;
import com.Proyecto.Proyecto.Domain.Sedes;
import com.Proyecto.Proyecto.Domain.Usuario;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author hhern
 */
public interface OrdenesService {
    public Ordenes getOrden(Ordenes orden);
    public Ordenes getOrdenbyid(Long id);
    public Usuario getusuariobyordenes(Long id);
    public List<Ordenes> getOrdenes();
    public List<Ordenes> getOrdenesbyuser(Long id);
    public List<Ordenes> getOrdenesbyStateandSede(Long id);
    public List<Citas> getCitasbyState();
    public List<Empleado> getEmpleadosbyState();
    public List<Sedes> getSedesbyState();
    public void save(Ordenes orden);
    public void delete(Ordenes orden);
    public void update(Long OID, Long CID,LocalDateTime FECH,String COMS,Long EMPID,Long IDSED,boolean ACT);
    public List<Citas> getCitas();
    public List<Empleado> getEmpleados();
    public List<Sedes> getSedes();
}
