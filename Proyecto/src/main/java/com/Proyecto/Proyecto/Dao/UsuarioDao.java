/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto.Proyecto.Dao;

import com.Proyecto.Proyecto.Domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Proyecto.Proyecto.Domain.Usuario;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import java.sql.SQLException;
import java.util.Map;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Usuario> getListUsuario() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("GET_USUARIOS")
                .withCatalogName("PACKAGE_USUARIO")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Usuario>() {
                    @Override
                    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Usuario usuario = new Usuario();
                        usuario.setIdUsuario(rs.getLong("ID_USUARIO"));
                        usuario.setUsername(rs.getString("USERNAME"));
                        usuario.setPassword(rs.getString("PASSWORD"));
                        usuario.setNombre(rs.getString("NOMBRE"));
                        usuario.setApellidos(rs.getString("APELLIDOS"));
                        usuario.setCorreo(rs.getString("CORREO"));
                        usuario.setTelefono(rs.getString("TELEFONO"));
                        usuario.setEstado(rs.getBoolean("ESTADO"));
                        return usuario;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Usuario> usuarioList = (List<Usuario>) results.get("DATOS");
        return usuarioList;
    }

    public Usuario getIdUsuario(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("GET_USUARIOBYID")
                .withCatalogName("PACKAGE_USUARIO")
                .declareParameters(new SqlParameter("UID", Types.BIGINT), new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Usuario>() {
                    @Override
                    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Usuario usuario = new Usuario();
                        usuario.setIdUsuario(rs.getLong("ID_USUARIO"));
                        usuario.setUsername(rs.getString("USERNAME"));
                        usuario.setPassword(rs.getString("PASSWORD"));
                        usuario.setNombre(rs.getString("NOMBRE"));
                        usuario.setApellidos(rs.getString("APELLIDOS"));
                        usuario.setCorreo(rs.getString("CORREO"));
                        usuario.setTelefono(rs.getString("TELEFONO"));
                        usuario.setEstado(rs.getBoolean("ESTADO"));
                        return usuario;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("UID", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Usuario> usuarioList = (List<Usuario>) results.get("DATOS");
        return usuarioList.isEmpty() ? null : usuarioList.get(0);
    }

    public Usuario getUsername(String username) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("GET_USUARIOBYUSERNAME")
                .withCatalogName("PACKAGE_USUARIO")
                .declareParameters(new SqlParameter("UNAME", Types.VARCHAR), new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Usuario>() {
                    @Override
                    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Usuario usuario = new Usuario();
                        usuario.setIdUsuario(rs.getLong("ID_USUARIO"));
                        usuario.setUsername(rs.getString("USERNAME"));
                        usuario.setPassword(rs.getString("PASSWORD"));
                        usuario.setNombre(rs.getString("NOMBRE"));
                        usuario.setApellidos(rs.getString("APELLIDOS"));
                        usuario.setCorreo(rs.getString("CORREO"));
                        usuario.setTelefono(rs.getString("TELEFONO"));
                        usuario.setEstado(rs.getBoolean("ESTADO"));
                        return usuario;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("UNAME", username);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Usuario> usuarioList = (List<Usuario>) results.get("DATOS");
        return usuarioList.isEmpty() ? null : usuarioList.get(0);
    }

    public Usuario getUsernameandPassword(String username, String password) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("GET_USERNAMEANDPASSWORD")
                .withCatalogName("PACKAGE_USUARIO")
                .declareParameters(new SqlParameter("UNAME", Types.VARCHAR), new SqlParameter("PASS", Types.VARCHAR), new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Usuario>() {
                    @Override
                    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Usuario usuario = new Usuario();
                        usuario.setIdUsuario(rs.getLong("ID_USUARIO"));
                        usuario.setUsername(rs.getString("USERNAME"));
                        usuario.setPassword(rs.getString("PASSWORD"));
                        usuario.setNombre(rs.getString("NOMBRE"));
                        usuario.setApellidos(rs.getString("APELLIDOS"));
                        usuario.setCorreo(rs.getString("CORREO"));
                        usuario.setTelefono(rs.getString("TELEFONO"));
                        usuario.setEstado(rs.getBoolean("ESTADO"));
                        return usuario;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("UNAME", username);
        mapSqlParameterSource.addValue("PASS", password);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Usuario> usuarioList = (List<Usuario>) results.get("DATOS");
        return usuarioList.isEmpty() ? null : usuarioList.get(0);
    }

    public Usuario getUsernameORCorreo(String username, String correo) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("GET_USERNAMEORCORREO")
                .withCatalogName("PACKAGE_USUARIO")
                .declareParameters(new SqlParameter("UNAME", Types.VARCHAR), new SqlParameter("CORR", Types.VARCHAR), new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Usuario>() {
                    @Override
                    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Usuario usuario = new Usuario();
                        usuario.setIdUsuario(rs.getLong("ID_USUARIO"));
                        usuario.setUsername(rs.getString("USERNAME"));
                        usuario.setPassword(rs.getString("PASSWORD"));
                        usuario.setNombre(rs.getString("NOMBRE"));
                        usuario.setApellidos(rs.getString("APELLIDOS"));
                        usuario.setCorreo(rs.getString("CORREO"));
                        usuario.setTelefono(rs.getString("TELEFONO"));
                        usuario.setEstado(rs.getBoolean("ESTADO"));
                        return usuario;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("UNAME", username);
        mapSqlParameterSource.addValue("CORR", correo);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Usuario> usuarioList = (List<Usuario>) results.get("DATOS");
        return usuarioList.isEmpty() ? null : usuarioList.get(0);
    }

    public void saveUsuario(Usuario usuario) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("ADD_USUARIO")
                .withCatalogName("PACKAGE_USUARIO")
                .declareParameters(
                        new SqlParameter("UNAME", Types.VARCHAR),
                        new SqlParameter("PASS", Types.VARCHAR),
                        new SqlParameter("UNOM", Types.VARCHAR),
                        new SqlParameter("APELL", Types.VARCHAR),
                        new SqlParameter("CORRE", Types.VARCHAR),
                        new SqlParameter("TEL", Types.VARCHAR),
                        new SqlParameter("EST", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("UNAME", usuario.getUsername());
        mapSqlParameterSource.addValue("PASS", usuario.getPassword());
        mapSqlParameterSource.addValue("UNOM", usuario.getNombre());
        mapSqlParameterSource.addValue("APELL", usuario.getApellidos());
        mapSqlParameterSource.addValue("CORRE", usuario.getCorreo());
        mapSqlParameterSource.addValue("TEL", usuario.getTelefono());
        mapSqlParameterSource.addValue("EST", usuario.isEstado());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }

    public void deleteUsuario(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("DELETE_USUARIO")
                .withCatalogName("PACKAGE_USUARIO")
                .declareParameters(new SqlParameter("USEID", Types.BIGINT));
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("USEID", id);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }

    public boolean USUARIO_EXISTE(String username, String correo) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withCatalogName("PACKAGE_USUARIO")
                .withFunctionName("USUARIO_EXISTE")
                .declareParameters(
                        new SqlParameter("UNAME", Types.VARCHAR),
                        new SqlParameter("CORRE", Types.VARCHAR))
                .declareParameters(new SqlOutParameter("RETURN_VALUE", Types.INTEGER));
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("UNAME", username);
        mapSqlParameterSource.addValue("CORRE", correo);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        Integer result = (Integer) results.get("RETURN_VALUE");
        return result != null && result == 1;
    }

    public List<Rol> getroles(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("GET_ROLES")
                .withCatalogName("PACKAGE_USUARIO")
                .declareParameters(new SqlParameter("UID", Types.BIGINT), new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Rol>() {
                    @Override
                    public Rol mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Rol rol = new Rol();
                        rol.setNombre(rs.getString("NOMBRE"));
                        return rol;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("UID", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Rol> rolList = (List<Rol>) results.get("DATOS");
        return rolList;
    }

    public void updateusuario(Long USERID, String USNAM, String CONTRAS, String NOMBR, String APELLI, String MAIL, String PHONE, boolean ACTV) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("UPDATE_USUARIO")
                .withCatalogName("PACKAGE_USUARIO")
                .declareParameters(
                        new SqlParameter("USERID", Types.BIGINT),
                        new SqlParameter("USNAM", Types.VARCHAR),
                        new SqlParameter("CONTRAS", Types.VARCHAR),
                        new SqlParameter("NOMBR", Types.VARCHAR),
                        new SqlParameter("APELLI", Types.VARCHAR),
                        new SqlParameter("MAIL", Types.VARCHAR),
                        new SqlParameter("PHONE", Types.VARCHAR),
                        new SqlParameter("ACTV", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("USERID", USERID);
        mapSqlParameterSource.addValue("USNAM", USNAM);
        mapSqlParameterSource.addValue("CONTRAS", CONTRAS);
        mapSqlParameterSource.addValue("NOMBR", NOMBR);
        mapSqlParameterSource.addValue("APELLI", APELLI);
        mapSqlParameterSource.addValue("MAIL", MAIL);
        mapSqlParameterSource.addValue("PHONE", PHONE);
        mapSqlParameterSource.addValue("ACTV", ACTV);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }

}
