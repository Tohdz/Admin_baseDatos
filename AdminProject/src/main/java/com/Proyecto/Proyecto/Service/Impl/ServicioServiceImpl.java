/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Service.Impl;

import com.Proyecto.Proyecto.Dao.ServicioDao;
import com.Proyecto.Proyecto.Domain.Servicios;
import com.Proyecto.Proyecto.Service.ServicioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hhern
 */
@Service
public class ServicioServiceImpl implements ServicioService{
    @Autowired
    private ServicioDao servicioDao;

    @Override
    @Transactional(readOnly = true)
    public List<Servicios> getServicios() {
        var lista = servicioDao.getListServicios();
        return lista;
    }
    
    @Override
    //@Transactional(readOnly = true)
    public Servicios getServicio(Servicios servicio) {
        return servicioDao.getOneServicio(servicio.getIdServicio());
    }

    @Override
    @Transactional
    public void save(Servicios servicio) {
        servicioDao.saveServicio(servicio);
    }

    @Override
    @Transactional
    public void delete(Servicios servicio) {
        servicioDao.deleteServicio(servicio.getIdServicio());
    }

    @Override
    public void update(Long CID, String NOM,boolean ACT) {
        servicioDao.updateServicio(CID,NOM,ACT);
    }
}
