package com.Proyecto.Proyecto.Service.Impl;

import com.Proyecto.Proyecto.Dao.CategoriaDao;
import com.Proyecto.Proyecto.Dao.JuegosDao;
import com.Proyecto.Proyecto.Domain.Categorias;
import com.Proyecto.Proyecto.Service.JuegosService;
import com.Proyecto.Proyecto.Domain.Juegos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JuegosServiceImpl implements JuegosService {

    @Autowired
    private JuegosDao juegosDao;
    

    @Override
    @Transactional(readOnly = true)
    public List<Juegos> getJuegos() {
        return juegosDao.getListJuegos();
    }

    @Override
    @Transactional(readOnly = true)
    public Juegos getJuego(Juegos juegos) {
        return juegosDao.getIdJuegos(juegos.getId_juego());
    }

    @Override
    @Transactional
    public void save(Juegos juegos) {
        juegosDao.saveJuegos(juegos);
    }

    @Override
    @Transactional
    public void delete(Juegos juegos) {
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
    public List<Juegos> getJuegosbycate(Long id) {
        return juegosDao.getJuegosbycate(id);
    }

    @Override
    public List<Categorias> cateMask() {
        return juegosDao.getdesc();
    }
}
