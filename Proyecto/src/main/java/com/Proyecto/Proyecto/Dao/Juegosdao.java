package com.Proyecto.Proyecto.Dao;

import com.Proyecto.Proyecto.Domain.Juegos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Juegosdao extends JpaRepository<Juegos, Long> {

    List<Juegos> findByNombre(String nombre);

    // Método actualizado para ordenar por precio
    List<Juegos> findByPrecioBetweenOrderByPrecio(double precioInf, double precioSup);

    // Método para encontrar juegos por ID de categoría
    List<Juegos> findByCategoria_IdCategoria(Long categoriaId);

    // Método para encontrar juegos por precio dentro de un rango y ID de categoría, ordenados por precio
    List<Juegos> findByPrecioBetweenAndCategoria_IdCategoriaOrderByPrecio(double precioInf, double precioSup, Long categoriaId);

    // Custom query to find juegos by precio between and categoriaId ordered by precio
    @Query("SELECT j FROM Juegos j WHERE j.precio BETWEEN ?1 AND ?2 AND j.categoria.idCategoria = ?3 ORDER BY j.precio")
    List<Juegos> findByPrecioBetweenAndCategoriaIdOrderByPrecio(double precioInf, double precioSup, Long categoriaId);

    List<Juegos> findByNombreContaining(String nombre);
}
