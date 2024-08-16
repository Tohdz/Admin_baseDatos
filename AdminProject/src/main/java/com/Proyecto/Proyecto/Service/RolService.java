/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto.Proyecto.Service;

import com.Proyecto.Proyecto.Domain.Rol;
import com.Proyecto.Proyecto.Domain.Usuario;
import java.util.List;

/**
 *
 * @author hhern
 */
public interface RolService {
    public List<Rol> getRoles();
    public List<Rol> getRolesbysede(Long id);
    public List<Usuario> getUsuariobyState();
    public List<Usuario> getUsuariobyStateandsede(Long id);
    public List<Usuario> getUsuarios();
    public Rol getRol(Rol rol);
    public void save(Rol rol);
    public void delete(Rol rol);
    public void update(Long ID, String NOM, Long UID);
}
