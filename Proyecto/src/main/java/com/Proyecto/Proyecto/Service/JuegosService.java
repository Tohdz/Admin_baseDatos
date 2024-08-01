package com.Proyecto.Proyecto.Service;

import com.Proyecto.Proyecto.Domain.Juegos;
import java.util.List;

public interface JuegosService {
    List<Juegos> getJuegos(String nombre);

    Juegos getJuego(Juegos juegos);

    void save(Juegos juegos);

    void delete(Juegos juegos);

    List<Juegos> findByPrecioBetweenOrderByPrecio(double precioInf, double precioSup);

    List<Juegos> getJuegosPorCategoria(Long categoriaId);

    List<Juegos> getJuegosConFiltros(Double precioInf, Double precioSup, Long categoriaId);
    
    List<Juegos> findByNombreContaining(String nombre);
}