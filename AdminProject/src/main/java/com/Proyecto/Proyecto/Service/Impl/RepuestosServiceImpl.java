package com.Proyecto.Proyecto.Service.Impl;

import com.Proyecto.Proyecto.Dao.CategoriaDao;
import com.Proyecto.Proyecto.Dao.RepuestosDao;
import com.Proyecto.Proyecto.Domain.Categorias;
import com.Proyecto.Proyecto.Domain.Marcas;
import com.Proyecto.Proyecto.Domain.Repuestos;
import com.Proyecto.Proyecto.Domain.Sedes;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.Proyecto.Proyecto.Service.RepuestosService;

@Service
public class RepuestosServiceImpl implements RepuestosService {

    @Autowired
    private RepuestosDao repuestosDao;

    @Override
    public Repuestos getRepuesto(Repuestos repuesto) {
        return repuestosDao.getRepuesto(repuesto.getIdRepuesto());
    }

    @Override
    public List<Repuestos> getRepuestos() {
        var lista = repuestosDao.getRepuestos();
        return lista;
    }

    @Override
    public List<Repuestos> getRepuestosbycategoria(Long CID) {
        var lista = repuestosDao.getRepuestosbycate(CID);
        return lista;
    }

    @Override
    public List<Marcas> getMarcasbyState() {
        var lista = repuestosDao.getMarcasbyState();
        return lista;
    }

    @Override
    public List<Categorias> getCategoriasbyState() {
        var lista = repuestosDao.getCategoriasbyState();
        return lista;
    }

    @Override
    public List<Sedes> getSedesbyState() {
        var lista = repuestosDao.getSedesbyState();
        return lista;
    }

    @Override
    public void save(Repuestos repuesto) {
        repuestosDao.saveRepuesto(repuesto);
    }

    @Override
    public void delete(Repuestos repuesto) {
        repuestosDao.deleteRepuesto(repuesto.getIdRepuesto());
    }

    @Override
    public void update(Long RID , String IMG ,String NOM, Long MID ,double PREC , int CANT,Long CID,Long IDSED, boolean EST) {
        repuestosDao.updateRepuesto(RID, IMG, NOM, MID, PREC, CANT, CID, IDSED, EST);
    }

    @Override
    public List<Marcas> getMarcas() {
        var lista = repuestosDao.getMarcas();
        return lista;
    }

    @Override
    public List<Categorias> getCategorias() {
        var lista = repuestosDao.getCategorias();
        return lista;
    }

    @Override
    public List<Sedes> getSedes() {
        var lista = repuestosDao.getSedes();
        return lista;
    }
    

    
}
