package com.Proyecto.Proyecto.Service.Impl;

import com.Proyecto.Proyecto.Dao.CategoriaDao;
import com.Proyecto.Proyecto.Dao.RepuestosDao;
import com.Proyecto.Proyecto.Domain.Categorias;
import com.Proyecto.Proyecto.Domain.Repuestos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.Proyecto.Proyecto.Service.RepuestosService;

@Service
public class RepuestosServiceImpl implements RepuestosService {

    @Autowired
    private RepuestosDao juegosDao;
    

    @Override
    @Transactional(readOnly = true)
    public List<Repuestos> getJuegos() {
        return juegosDao.getListJuegos();
    }

    @Override
    @Transactional(readOnly = true)
    public Repuestos getJuego(Repuestos juegos) {
        return juegosDao.getIdJuegos(juegos.getId_juego());
    }

    @Override
    @Transactional
    public void save(Repuestos juegos) {
        juegosDao.saveJuegos(juegos);
    }

    @Override
    @Transactional
    public void delete(Repuestos juegos) {
        juegosDao.deleteJuegos(juegos.getId_juego());
    }

    @Override
    public List<Categorias> getCates() {
        return juegosDao.getCategorias();
    }

    @Override
    public void update(Long JID , String IMG ,String NOM, String EMP ,double PREC , int EXI, boolean EST , Long ID_CAT) {
        juegosDao.updateJuegos(JID,IMG,NOM,EMP,PREC,EXI,EST,ID_CAT);
    }

    @Override
    public List<Repuestos> getJuegosbycate(Long id) {
        return juegosDao.getJuegosbycate(id);
    }

    @Override
    public List<Categorias> cateMask() {
        return juegosDao.getdesc();
    }
}
