/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Service.Impl;

import com.Proyecto.Proyecto.Dao.RolDao;
import com.Proyecto.Proyecto.Domain.Rol;
import com.Proyecto.Proyecto.Domain.Usuario;
import com.Proyecto.Proyecto.Service.RolService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hhern
 */
@Service
public class RolServiceImpl implements RolService{
    
    @Autowired
    private RolDao rolDao;

    @Override
    public List<Rol> getRoles() {
        return rolDao.getroles();
    }

    @Override
    public Rol getRol(Rol rol) {
        return rolDao.getRol(rol.getIdRol());
    }

    @Override
    public void save(Rol rol) {
        rolDao.save(rol);
    }

    @Override
    public void delete(Rol rol) {
        rolDao.delete(rol.getIdRol());
    }

    @Override
    public void update(Long ID, String NOM, Long UID) {
        rolDao.update(ID, NOM, UID);
    }

    @Override
    public List<Usuario> getUsuariobyState() {
        return rolDao.getListUsuariobyState();
    }

    @Override
    public List<Usuario> getUsuarios() {
        return rolDao.getUsuarios();

    }

    @Override
    public List<Usuario> getUsuariobyStateandsede(Long id) {
        return rolDao.getUsuariosbyStateandsede(id);
    }

    @Override
    public List<Rol> getRolesbysede(Long id) {
        return rolDao.getrolesbysede(id);
    }
    
}
