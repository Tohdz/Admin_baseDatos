/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Service.Impl;

import com.Proyecto.Proyecto.Dao.MarcaRepuestosDao;
import com.Proyecto.Proyecto.Domain.MarcaRepuestos;
import com.Proyecto.Proyecto.Service.MarcaRepuestoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hhern
 */
@Service
public class MarcaRepuestoServiceImpl implements MarcaRepuestoService {
    
    @Autowired
    private MarcaRepuestosDao marcarepuestosDao;

    @Override
    public List<MarcaRepuestos> getMarcaRepuestos() {
        var lista = marcarepuestosDao.getListMarcaRepuestos();
        return lista;
    }

    @Override
    public MarcaRepuestos getMarca(MarcaRepuestos marcarepuesto) {
        return marcarepuestosDao.getMarcaRepuesto(marcarepuesto.getIdMarcaRepuesto());
    }

    @Override
    public void save(MarcaRepuestos marcarepuesto) {
        marcarepuestosDao.saveMarcaR(marcarepuesto);
    }

    @Override
    public void delete(MarcaRepuestos marcarepuesto) {
        marcarepuestosDao.deleteMarcaR(marcarepuesto.getIdMarcaRepuesto());
    }

    @Override
    public void update(Long CID, String NOM, boolean ACT) {
        marcarepuestosDao.updateMarcaR(CID, NOM, ACT);
    }
    
    
    
}
