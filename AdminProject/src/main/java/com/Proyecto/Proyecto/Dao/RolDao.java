/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto.Proyecto.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Proyecto.Proyecto.Domain.Rol;
import com.Proyecto.Proyecto.Domain.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class RolDao  {
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public void save(Rol rol) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_ROLES_TB_ADD_ROL_SP")
                .declareParameters(
                        new SqlParameter("RNAME", Types.VARCHAR),
                        new SqlParameter("IDUSER", Types.BIGINT)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("RNAME", rol.getNombre());
        mapSqlParameterSource.addValue("IDUSER", rol.getIdUsuario());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public void delete(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_ROLES_TB_DELETE_ROLBYID_SP")
                .declareParameters(new SqlParameter("RID", Types.BIGINT));
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("RID", id);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
     
    public List<Rol> getroles() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_ROLES_TB_GET_ROLES_SP")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Rol>() {
                    @Override
                    public Rol mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Rol rol = new Rol();
                        rol.setIdRol(rs.getLong("ID_ROL"));
                        rol.setNombre(rs.getString("NOMBRE"));
                        rol.setIdUsuario(rs.getLong("ID_USUARIO"));
                        return rol;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Rol> rolesList = (List<Rol>) results.get("DATOS");
        return rolesList;
    }
    
    public List<Rol> getrolesbysede(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_ROLES_TB_GET_ROLESBYSEDE_SP")
                .declareParameters(new SqlParameter("IDSED", Types.BIGINT),new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Rol>() {
                    @Override
                    public Rol mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Rol rol = new Rol();
                        rol.setIdRol(rs.getLong("ID_ROL"));
                        rol.setNombre(rs.getString("NOMBRE"));
                        rol.setIdUsuario(rs.getLong("ID_USUARIO"));
                        return rol;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("IDSED", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Rol> rolesList = (List<Rol>) results.get("DATOS");
        return rolesList;
    }
    
    public Rol getRol(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_ROLES_TB_GET_ROL_SP")
                .declareParameters(new SqlParameter("RID", Types.BIGINT), new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Rol>() {
                    @Override
                    public Rol mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Rol rol = new Rol();
                        rol.setIdRol(rs.getLong("ID_ROL"));
                        rol.setNombre(rs.getString("NOMBRE"));
                        rol.setIdUsuario(rs.getLong("ID_USUARIO"));
                        return rol;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("RID", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Rol> rolesList = (List<Rol>) results.get("DATOS");
        return rolesList.isEmpty() ? null : rolesList.get(0);
    }
    
    public void update(Long ID, String NOM, Long UID) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_ROLES_TB_UPDATE_ROL_SP")
                .declareParameters(
                        new SqlParameter("RID", Types.BIGINT),
                        new SqlParameter("NOMB", Types.VARCHAR),
                        new SqlParameter("USID", Types.BIGINT)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("RID", ID);
        mapSqlParameterSource.addValue("NOMB", NOM);
        mapSqlParameterSource.addValue("USID", UID);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public List<Usuario> getListUsuariobyState() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_USUARIOS_TB_GET_USUARIOSBYSTATE_SP")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Usuario>() {
                    @Override
                    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Usuario usuario = new Usuario();
                        usuario.setIdUsuario(rs.getLong("ID_USUARIO"));
                        usuario.setUsername(rs.getString("USERNAME"));
                        usuario.setPassword(rs.getString("UPASSWORD"));
                        usuario.setNombre(rs.getString("NOMBRE"));
                        usuario.setApellidos(rs.getString("APELLIDO"));
                        usuario.setCorreo(rs.getString("CORREO"));
                        usuario.setTelefono(rs.getString("TELEFONO"));
                        usuario.setIdSede(rs.getLong("ID_SEDE"));
                        usuario.setEstado(rs.getBoolean("ESTADO"));
                        return usuario;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Usuario> usuarioList = (List<Usuario>) results.get("DATOS");
        return usuarioList;
    }
    
    public List<Usuario> getUsuariosbyStateandsede(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_USUARIOS_TB_GET_USUARIOSBYSTATE_SP")
                .declareParameters(new SqlParameter("IDSED", Types.BIGINT),new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Usuario>() {
                    @Override
                    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Usuario usuario = new Usuario();
                        usuario.setIdUsuario(rs.getLong("ID_USUARIO"));
                        usuario.setUsername(rs.getString("USERNAME"));
                        usuario.setPassword(rs.getString("UPASSWORD"));
                        usuario.setNombre(rs.getString("NOMBRE"));
                        usuario.setApellidos(rs.getString("APELLIDO"));
                        usuario.setCorreo(rs.getString("CORREO"));
                        usuario.setTelefono(rs.getString("TELEFONO"));
                        usuario.setIdSede(rs.getLong("ID_SEDE"));
                        usuario.setEstado(rs.getBoolean("ESTADO"));
                        return usuario;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("IDSED", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Usuario> usuarioList = (List<Usuario>) results.get("DATOS");
        return usuarioList;
    }
    
     public List<Usuario> getUsuarios() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_USUARIOS_TB_GET_USUARIOS_SP")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Usuario>() {
                    @Override
                    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Usuario usuario = new Usuario();
                        usuario.setIdUsuario(rs.getLong("ID_USUARIO"));
                        usuario.setUsername(rs.getString("USERNAME"));
                        usuario.setPassword(rs.getString("UPASSWORD"));
                        usuario.setNombre(rs.getString("NOMBRE"));
                        usuario.setApellidos(rs.getString("APELLIDO"));
                        usuario.setCorreo(rs.getString("CORREO"));
                        usuario.setTelefono(rs.getString("TELEFONO"));
                        usuario.setIdSede(rs.getLong("ID_SEDE"));
                        usuario.setEstado(rs.getBoolean("ESTADO"));
                        return usuario;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Usuario> usuarioList = (List<Usuario>) results.get("DATOS");
        return usuarioList;
    }
}
