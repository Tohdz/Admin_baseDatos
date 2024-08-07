/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Service.Impl;

import com.Proyecto.Proyecto.Dao.TiposDao;
import com.Proyecto.Proyecto.Domain.Tipos;
import com.Proyecto.Proyecto.Service.TiposService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hhern
 */
@Service
public class TipoServiceImpl implements TiposService {
    
    @Autowired
    private TiposDao tiposDao;

    @Override
    public List<Tipos> getTipos() {
        var lista = tiposDao.getListTipos();
        return lista;
    }

    @Override
    public Tipos getTipo(Tipos tipo) {
        return tiposDao.getOneTipo(tipo.getIdTipo());
    }

    @Override
    public void save(Tipos tipo) {
        tiposDao.saveTipo(tipo);
    }

    @Override
    public void delete(Tipos tipo) {
        tiposDao.deleteTipo(tipo.getIdTipo());
    }

    @Override
    public void update(Long CID, String NOM, boolean ACT) {
        tiposDao.updateTipo(CID, NOM, ACT);
    }
    
}
