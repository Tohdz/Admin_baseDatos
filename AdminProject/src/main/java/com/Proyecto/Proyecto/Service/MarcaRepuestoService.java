/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto.Proyecto.Service;

import com.Proyecto.Proyecto.Domain.MarcaRepuestos;
import java.util.List;

/**
 *
 * @author hhern
 */
public interface MarcaRepuestoService {
    public List<MarcaRepuestos> getMarcaRepuestos();
    public MarcaRepuestos getMarca(MarcaRepuestos marcarepuesto);
    public void save(MarcaRepuestos marcarepuesto);
    public void delete(MarcaRepuestos marcarepuesto);
    public void update(Long CID, String NOM,boolean ACT);
}
