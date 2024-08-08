/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto.Proyecto.Dao;

import com.Proyecto.Proyecto.Domain.Rol;
import com.Proyecto.Proyecto.Domain.Sedes;
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

    public Usuario getIdUsuario(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_USUARIOS_TB_GET_USUARIOBYID_SP")
                .declareParameters(new SqlParameter("UID", Types.BIGINT), new SqlParameter("DATOS", Types.REF_CURSOR))
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
        mapSqlParameterSource.addValue("UID", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Usuario> usuarioList = (List<Usuario>) results.get("DATOS");
        return usuarioList.isEmpty() ? null : usuarioList.get(0);
    }

    public Usuario getUsername(String username) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_USUARIOS_TB_GET_USUARIOBYUSERNAME_SP")
                .declareParameters(new SqlParameter("UNAME", Types.VARCHAR), new SqlParameter("DATOS", Types.REF_CURSOR))
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
        mapSqlParameterSource.addValue("UNAME", username);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Usuario> usuarioList = (List<Usuario>) results.get("DATOS");
        return usuarioList.isEmpty() ? null : usuarioList.get(0);
    }

    public Usuario getUsernameandPassword(String username, String password) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_USUARIOS_TB_GET_USERNAMEANDPASSWORD_SP")
                .declareParameters(new SqlParameter("UNAME", Types.VARCHAR), new SqlParameter("PASS", Types.VARCHAR), new SqlParameter("DATOS", Types.REF_CURSOR))
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
        mapSqlParameterSource.addValue("UNAME", username);
        mapSqlParameterSource.addValue("PASS", password);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Usuario> usuarioList = (List<Usuario>) results.get("DATOS");
        return usuarioList.isEmpty() ? null : usuarioList.get(0);
    }

    public Usuario getUsernameORCorreo(String username, String correo) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_USUARIOS_TB_GET_USERNAMEORCORREO_SP")
                .declareParameters(new SqlParameter("UNAME", Types.VARCHAR), new SqlParameter("CORR", Types.VARCHAR), new SqlParameter("DATOS", Types.REF_CURSOR))
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
        mapSqlParameterSource.addValue("UNAME", username);
        mapSqlParameterSource.addValue("CORR", correo);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Usuario> usuarioList = (List<Usuario>) results.get("DATOS");
        return usuarioList.isEmpty() ? null : usuarioList.get(0);
    }

    public void saveUsuario(Usuario usuario) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_USUARIOS_TB_ADD_USUARIO_SP")
                .declareParameters(
                        new SqlParameter("USID", Types.BIGINT),
                        new SqlParameter("UNAME", Types.VARCHAR),
                        new SqlParameter("PASS", Types.VARCHAR),
                        new SqlParameter("UNOM", Types.VARCHAR),
                        new SqlParameter("APELL", Types.VARCHAR),
                        new SqlParameter("CORRE", Types.VARCHAR),
                        new SqlParameter("TEL", Types.VARCHAR),
                        new SqlParameter("IDS", Types.BIGINT),
                        new SqlParameter("EST", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("USID", usuario.getIdUsuario());
        mapSqlParameterSource.addValue("UNAME", usuario.getUsername());
        mapSqlParameterSource.addValue("PASS", usuario.getPassword());
        mapSqlParameterSource.addValue("UNOM", usuario.getNombre());
        mapSqlParameterSource.addValue("APELL", usuario.getApellidos());
        mapSqlParameterSource.addValue("CORRE", usuario.getCorreo());
        mapSqlParameterSource.addValue("TEL", usuario.getTelefono());
        mapSqlParameterSource.addValue("IDS", usuario.getIdSede());
        mapSqlParameterSource.addValue("EST", usuario.isEstado());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }

    public void deleteUsuario(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_USUARIOS_TB_DELETE_USUARIO_SP")
                .declareParameters(new SqlParameter("USEID", Types.BIGINT));
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("USEID", id);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }

    public boolean USUARIO_EXISTE(String username, String correo) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withFunctionName("FIDE_USUARIOS_TB_USUARIO_EXISTE_FN")
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
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_USUARIOS_TB_GET_ROLES_SP")
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

    public void updateusuario(Long USERID, String USNAM, String CONTRAS, String NOMBR, String APELLI, String MAIL, String PHONE,Long IDS, boolean ACTV) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_USUARIOS_TB_UPDATE_USUARIO_SP")
                .declareParameters(
                        new SqlParameter("USERID", Types.BIGINT),
                        new SqlParameter("USNAM", Types.VARCHAR),
                        new SqlParameter("CONTRAS", Types.VARCHAR),
                        new SqlParameter("NOMBR", Types.VARCHAR),
                        new SqlParameter("APELLI", Types.VARCHAR),
                        new SqlParameter("MAIL", Types.VARCHAR),
                        new SqlParameter("PHONE", Types.VARCHAR),
                        new SqlParameter("IDS", Types.BIGINT),
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
        mapSqlParameterSource.addValue("IDS", IDS);
        mapSqlParameterSource.addValue("ACTV", ACTV);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public List<Sedes> getSedes() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_SEDES_TB_GET_SEDES_SP")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Sedes>() {
                    @Override
                    public Sedes mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Sedes sede = new Sedes();
                        sede.setIdSede(rs.getLong("ID_SEDE"));
                        sede.setNombre(rs.getString("NOMBRE"));
                        sede.setCiudad(rs.getString("CIUDAD"));
                        sede.setDireccion(rs.getString("DIRECCION"));
                        sede.setTelefono(rs.getString("TELEFONO"));
                        sede.setEstado(rs.getBoolean("ESTADO"));
                        return sede;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Sedes> sedeList = (List<Sedes>) results.get("DATOS");
        return sedeList;
    }

}
