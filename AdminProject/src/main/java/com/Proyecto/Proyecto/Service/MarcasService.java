/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto.Proyecto.Service;

import com.Proyecto.Proyecto.Domain.Marcas;
import java.util.List;

/**
 *
 * @author hhern
 */
public interface MarcasService {
    public List<Marcas> getMarcas();
    public Marcas getMarca(Marcas marca);
    public void save(Marcas marca);
    public void delete(Marcas marca);
    public void update(Long CID, String NOM,boolean ACT);
}
