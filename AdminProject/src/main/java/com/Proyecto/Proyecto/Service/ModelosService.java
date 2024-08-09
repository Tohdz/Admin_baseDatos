/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto.Proyecto.Service;

import com.Proyecto.Proyecto.Domain.Marcas;
import com.Proyecto.Proyecto.Domain.Modelos;
import java.util.List;

/**
 *
 * @author hhern
 */
public interface ModelosService {
    public List<Modelos> getModelos();
    public Modelos getModelo(Modelos modelo);
    public void save(Modelos modelo);
    public void delete(Modelos modelo);
    public void update(Long CID, String NOM,Long MID,boolean ACT);
    public List<Marcas> getMarcas();
    public List<Marcas> getMarcasbyState();
}
