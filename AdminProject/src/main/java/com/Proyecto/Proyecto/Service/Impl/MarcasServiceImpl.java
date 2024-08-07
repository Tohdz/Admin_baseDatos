/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Service.Impl;

import com.Proyecto.Proyecto.Dao.MarcasDao;
import com.Proyecto.Proyecto.Domain.Marcas;
import com.Proyecto.Proyecto.Service.MarcasService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hhern
 */
@Service
public class MarcasServiceImpl implements MarcasService {
    @Autowired
    private MarcasDao marcasDao;

    @Override
    public List<Marcas> getMarcas() {
        var lista = marcasDao.getListMarcas();
        return lista;
    }

    @Override
    public Marcas getMarca(Marcas marca) {
        return marcasDao.getOneMarca(marca.getIdMarca());
    }

    @Override
    public void save(Marcas marca) {
        marcasDao.saveMarca(marca);
    }

    @Override
    public void delete(Marcas marca) {
        marcasDao.deleteMarca(marca.getIdMarca());
    }

    @Override
    public void update(Long CID, String NOM, boolean ACT) {
        marcasDao.updateMarca(CID, NOM, ACT);
    }
}
