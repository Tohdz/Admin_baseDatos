/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Service.Impl;

import com.Proyecto.Proyecto.Dao.PuestosDao;
import com.Proyecto.Proyecto.Domain.Puestos;
import com.Proyecto.Proyecto.Service.PuestosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hhern
 */
@Service
public class PuestoServiceImpl implements PuestosService{
    
    @Autowired
    private PuestosDao puestosDao;

    @Override
    public List<Puestos> getPuestos() {
        var lista = puestosDao.getListPuestos();
        return lista;
    }

    @Override
    public Puestos getPuesto(Puestos puesto) {
        return puestosDao.getOnePuesto(puesto.getIdPuesto());
    }

    @Override
    public void save(Puestos puesto) {
        puestosDao.savePuesto(puesto);
    }

    @Override
    public void delete(Puestos puesto) {
        puestosDao.deletePuesto(puesto.getIdPuesto());
    }

    @Override
    public void update(Long CID, String NOM, boolean ACT) {
        puestosDao.updatePuesto(CID, NOM, ACT);
    }
    
}
