/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Service.Impl;

import com.Proyecto.Proyecto.Dao.ModelosDao;
import com.Proyecto.Proyecto.Domain.Marcas;
import com.Proyecto.Proyecto.Domain.Modelos;
import com.Proyecto.Proyecto.Service.ModelosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hhern
 */
@Service
public class ModelosServiceImpl implements ModelosService {
    
    @Autowired
    private ModelosDao modelosDao;

    @Override
    public List<Modelos> getModelos() {
        var lista = modelosDao.getListModelos();
        return lista;
    }

    @Override
    public Modelos getModelo(Modelos modelo) {
        return modelosDao.getOneModelo(modelo.getIdModelo());
    }

    @Override
    public void save(Modelos modelo) {
        modelosDao.saveModelo(modelo);
    }

    @Override
    public void delete(Modelos modelo) {
        modelosDao.deleteModelo(modelo.getIdModelo());
    }

    @Override
    public void update(Long CID, String NOM, Long MID, boolean ACT) {
        modelosDao.updateModelo(CID, NOM, MID, ACT);
    }

    @Override
    public List<Marcas> getMarcas() {
        return modelosDao.getMarcas();
    }
    
}
