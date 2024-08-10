package com.Proyecto.Proyecto.Service;

import com.Proyecto.Proyecto.Domain.Categorias;
import com.Proyecto.Proyecto.Domain.Repuestos;
import java.util.List;

public interface RepuestosService {
    List<Repuestos> getJuegos();
    
    List<Categorias> getCates();
    
    List<Repuestos> getJuegosbycate(Long id);

    Repuestos getJuego(Repuestos juegos);
    
    public List<Categorias> cateMask();

    public void save(Repuestos juegos);

    public void delete(Repuestos juegos);
    
    public void update(Long JID , String IMG ,String NOM, String EMP ,double PREC , int EXI, boolean EST , Long ID_CAT);

}