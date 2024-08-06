/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Service.Impl;

import com.Proyecto.Proyecto.Dao.SedesDao;
import com.Proyecto.Proyecto.Domain.Sedes;
import com.Proyecto.Proyecto.Service.SedesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hhern
 */
@Service
public class SedesServiceImpl implements SedesService {
    
    @Autowired
    private SedesDao sedesDao;

    @Override
    public List<Sedes> getSedes() {
        return sedesDao.getSedes();
    }

    @Override
    public Sedes getSede(Sedes sede) {
        return sedesDao.getSede(sede.getIdSede());
    }

    @Override
    public void save(Sedes sede) {
        sedesDao.saveSede(sede);
    }

    @Override
    public void delete(Sedes sede) {
        sedesDao.deleteSede(sede.getIdSede());
    }

    @Override
    public void update(Long IDS, String NAME, String CITY, String DIR, String PHONE, boolean EST) {
        sedesDao.updateSede(IDS, NAME, CITY, DIR, PHONE, EST);
    }

    
    
}
