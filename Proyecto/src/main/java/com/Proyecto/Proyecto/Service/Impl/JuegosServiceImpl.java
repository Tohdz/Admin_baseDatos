package com.Proyecto.Proyecto.Service.Impl;

import com.Proyecto.Proyecto.Dao.Juegosdao;
import com.Proyecto.Proyecto.Service.JuegosService;
import com.Proyecto.Proyecto.Domain.Juegos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JuegosServiceImpl implements JuegosService {

    @Autowired
    private Juegosdao juegosDao;

    @Override
    @Transactional(readOnly = true)
    public List<Juegos> getJuegos(String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            return juegosDao.findByNombre(nombre);
        } else {
            return juegosDao.findAll();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Juegos getJuego(Juegos juegos) {
    return juegosDao.findById(juegos.getId_juego()).orElse(null);
}


    @Override
    @Transactional
    public void save(Juegos juegos) {
        juegosDao.save(juegos);
    }

    @Override
    @Transactional
    public void delete(Juegos juegos) {
        juegosDao.delete(juegos);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Juegos> findByPrecioBetweenOrderByPrecio(double precioInf, double precioSup) {
        return juegosDao.findByPrecioBetweenOrderByPrecio(precioInf, precioSup);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Juegos> getJuegosPorCategoria(Long categoriaId) {
        return juegosDao.findByCategoria_IdCategoria(categoriaId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Juegos> getJuegosConFiltros(Double precioInf, Double precioSup, Long categoriaId) {
        if (precioInf != null && precioSup != null && categoriaId != null) {
            return juegosDao.findByPrecioBetweenAndCategoriaIdOrderByPrecio(precioInf, precioSup, categoriaId);
        } else if (precioInf != null && precioSup != null) {
            return juegosDao.findByPrecioBetweenOrderByPrecio(precioInf, precioSup);
        } else if (categoriaId != null) {
            return juegosDao.findByCategoria_IdCategoria(categoriaId);
        } else {
            return juegosDao.findAll(); // Retornar todos los juegos si no se aplican filtros
        }
    }
        @Override
    @Transactional(readOnly = true)
    public List<Juegos> findByNombreContaining(String nombre) {
        return juegosDao.findByNombreContaining(nombre);
    }
}