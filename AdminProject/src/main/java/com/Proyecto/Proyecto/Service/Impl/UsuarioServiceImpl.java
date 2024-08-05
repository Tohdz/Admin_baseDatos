/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.Proyecto.Proyecto.Dao.RolDao;
import com.Proyecto.Proyecto.Dao.UsuarioDao;
import com.Proyecto.Proyecto.Domain.Rol;
import com.Proyecto.Proyecto.Domain.Usuario;
import com.Proyecto.Proyecto.Service.UsuarioService;
import jakarta.persistence.EntityManager;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private RolDao rolDao;
    
     private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        return usuarioDao.getListUsuario();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuario(Usuario usuario) {
        return usuarioDao.getIdUsuario(usuario.getIdUsuario());
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsername(String username) {
        return usuarioDao.getUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameYPassword(String username, String password) {
        return usuarioDao.getUsernameandPassword(username, password);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameOCorreo(String username, String correo) {
        return usuarioDao.getUsernameORCorreo(username, correo);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeUsuarioPorUsernameOCorreo(String username, String correo) {
        return usuarioDao.USUARIO_EXISTE(username, correo);
    }

    @Override
    @Transactional
    public void save(Usuario usuario) {
        usuario.setEstado(true);
        usuarioDao.saveUsuario(usuario);
        
    }

    @Override
    @Transactional
    public void delete(Usuario usuario) {
        usuarioDao.deleteUsuario(usuario.getIdUsuario());
    }

    @Override
    public void save2(Usuario usuario) {
        usuario.setEstado(true);
        usuarioDao.saveUsuario(usuario);
        if (usuario.isEstado()) { 
        Rol rol = new Rol();
        rol.setNombre("ROLE_USER");
        rol.setIdUsuario(usuarioDao.getUsernameandPassword(usuario.getUsername(), usuario.getPassword()).getIdUsuario());
        rolDao.save(rol); 
        }
    }

    @Override
    public void updateuser(Long USERID, String USNAM, String CONTRAS, String NOMBR, String APELLI, String MAIL, String PHONE, boolean ACTV) {
        usuarioDao.updateusuario(USERID, USNAM, CONTRAS, NOMBR, APELLI, MAIL, PHONE, ACTV);
    }
  
}
