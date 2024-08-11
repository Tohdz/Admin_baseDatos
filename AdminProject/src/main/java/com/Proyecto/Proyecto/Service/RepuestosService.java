package com.Proyecto.Proyecto.Service;

import com.Proyecto.Proyecto.Domain.Categorias;
import com.Proyecto.Proyecto.Domain.Marcas;
import com.Proyecto.Proyecto.Domain.Repuestos;
import com.Proyecto.Proyecto.Domain.Sedes;
import java.util.List;

public interface RepuestosService {
    public Repuestos getRepuesto(Repuestos repuesto);
    public List<Repuestos> getRepuestos();
    public List<Repuestos> getRepuestosbycategoria(Long CID);
    public List<Marcas> getMarcasbyState();
    public List<Categorias> getCategoriasbyState();
    public List<Sedes> getSedesbyState();
    public void save(Repuestos repuesto);
    public void delete(Repuestos repuesto);
    public void update(Long RID , String IMG ,String NOM, Long MID ,double PREC , int CANT,Long CID,Long IDSED, boolean EST);
    public List<Marcas> getMarcas();
    public List<Categorias> getCategorias();
    public List<Sedes> getSedes();
    
}