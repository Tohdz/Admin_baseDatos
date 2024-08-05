package com.Proyecto.Proyecto.Service;

import com.Proyecto.Proyecto.Domain.Categorias;
import java.util.List;

public interface CategoriaService {

    public List<Categorias> getCategorias();
    public Categorias getCategoria(Categorias categoria);
    public void save(Categorias categoria);
    public void delete(Categorias categoria);
    public void update(Long CID, String DESCRIP,String IMG,boolean ACT);
}