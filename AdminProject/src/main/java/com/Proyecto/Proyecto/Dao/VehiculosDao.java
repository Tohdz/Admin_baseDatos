/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Dao;

import com.Proyecto.Proyecto.Domain.Marcas;
import com.Proyecto.Proyecto.Domain.Modelos;
import com.Proyecto.Proyecto.Domain.Sedes;
import com.Proyecto.Proyecto.Domain.Tipos;
import com.Proyecto.Proyecto.Domain.Usuario;
import com.Proyecto.Proyecto.Domain.Vehiculos;
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

/**
 *
 * @author hhern
 */
@Repository
public class VehiculosDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Vehiculos> getVehiculos() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_VEHICULOS_TB_GET_VEHICULOS_SP")
                .withCatalogName("FIDE_TALLER_VEHICULOS_PKG")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Vehiculos>() {
                    @Override
                    public Vehiculos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Vehiculos vehiculo = new Vehiculos();
                        vehiculo.setPlaca(rs.getString("PLACA"));
                        vehiculo.setIdMarca(rs.getLong("ID_MARCA"));
                        vehiculo.setIdModelo(rs.getLong("ID_MODELO"));
                        vehiculo.setIdTipo(rs.getLong("ID_TIPO"));
                        vehiculo.setAno(rs.getInt("ANO"));
                        vehiculo.setIdSede(rs.getLong("ID_SEDE"));
                        vehiculo.setIdUsuario(rs.getLong("ID_USUARIO"));
                        vehiculo.setEstado(rs.getBoolean("ESTADO"));
                        return vehiculo;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Vehiculos> vehiculoList = (List<Vehiculos>) results.get("DATOS");
        return vehiculoList;
    }
    
    public List<Vehiculos> getVehiculosbyUser(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_VEHICULOS_TB_GET_VEHICULOSBYUSER_SP")
                .withCatalogName("FIDE_TALLER_VEHICULOS_PKG")
                .declareParameters(new SqlParameter("USID", Types.BIGINT),new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Vehiculos>() {
                    @Override
                    public Vehiculos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Vehiculos vehiculo = new Vehiculos();
                        vehiculo.setPlaca(rs.getString("PLACA"));
                        vehiculo.setIdMarca(rs.getLong("ID_MARCA"));
                        vehiculo.setIdModelo(rs.getLong("ID_MODELO"));
                        vehiculo.setIdTipo(rs.getLong("ID_TIPO"));
                        vehiculo.setAno(rs.getInt("ANO"));
                        vehiculo.setIdSede(rs.getLong("ID_SEDE"));
                        vehiculo.setIdUsuario(rs.getLong("ID_USUARIO"));
                        vehiculo.setEstado(rs.getBoolean("ESTADO"));
                        return vehiculo;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("USID", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Vehiculos> vehiculoList = (List<Vehiculos>) results.get("DATOS");
        return vehiculoList;
    }

    public Vehiculos getOneVehiculo(String placa) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_VEHICULOS_TB_GET_VEHICULO_SP")
                .withCatalogName("FIDE_TALLER_VEHICULOS_PKG")
                .declareParameters(new SqlParameter("PLACA", Types.VARCHAR),new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Vehiculos>() {
                    @Override
                    public Vehiculos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Vehiculos vehiculo = new Vehiculos();
                        vehiculo.setPlaca(rs.getString("PLACA"));
                        vehiculo.setIdMarca(rs.getLong("ID_MARCA"));
                        vehiculo.setIdModelo(rs.getLong("ID_MODELO"));
                        vehiculo.setIdTipo(rs.getLong("ID_TIPO"));
                        vehiculo.setAno(rs.getInt("ANO"));
                        vehiculo.setIdSede(rs.getLong("ID_SEDE"));
                        vehiculo.setIdUsuario(rs.getLong("ID_USUARIO"));
                        vehiculo.setEstado(rs.getBoolean("ESTADO"));
                        return vehiculo;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("PLACA", placa);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Vehiculos> vehiculoList = (List<Vehiculos>) results.get("DATOS");
        return vehiculoList.isEmpty() ? null : vehiculoList.get(0);
    }

    public void saveVehiculo(Vehiculos vehiculo) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_VEHICULOS_TB_ADD_VEHICULO_SP")
                .withCatalogName("FIDE_TALLER_VEHICULOS_PKG")
                .declareParameters(
                        new SqlParameter("PLAC", Types.VARCHAR),
                        new SqlParameter("MID", Types.BIGINT),
                        new SqlParameter("IDM", Types.BIGINT),
                        new SqlParameter("TID", Types.BIGINT),
                        new SqlParameter("ANO", Types.INTEGER),
                        new SqlParameter("IDS", Types.BIGINT),
                        new SqlParameter("USID", Types.BIGINT),
                        new SqlParameter("ACT", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("PLAC", vehiculo.getPlaca());
        mapSqlParameterSource.addValue("MID", vehiculo.getIdMarca());
        mapSqlParameterSource.addValue("IDM", vehiculo.getIdModelo());
        mapSqlParameterSource.addValue("TID", vehiculo.getIdTipo());
        mapSqlParameterSource.addValue("ANO", vehiculo.getAno());
        mapSqlParameterSource.addValue("IDS", vehiculo.getIdSede());
        mapSqlParameterSource.addValue("USID", vehiculo.getIdUsuario());
        mapSqlParameterSource.addValue("ACT", vehiculo.isEstado());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public void deleteVehiculo(String PLACA) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_VEHICULOS_TB_DELETE_VEHICULO_SP")
                .withCatalogName("FIDE_TALLER_VEHICULOS_PKG")
                .declareParameters(new SqlParameter("PLAC", Types.VARCHAR));
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("PLAC", PLACA);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public void updateVehiculo(String plac,Long mid,Long idm,Long tid,int ano,Long ids,Long usid,boolean act) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_VEHICULOS_TB_UPDATE_VEHICULO_SP")
                .withCatalogName("FIDE_TALLER_VEHICULOS_PKG")
                .declareParameters(
                        new SqlParameter("PLAC", Types.VARCHAR),
                        new SqlParameter("MID", Types.BIGINT),
                        new SqlParameter("IDM", Types.BIGINT),
                        new SqlParameter("TID", Types.BIGINT),
                        new SqlParameter("LLEAR", Types.BIGINT),
                        new SqlParameter("IDS", Types.BIGINT),
                        new SqlParameter("USID", Types.BIGINT),
                        new SqlParameter("ACT", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
       mapSqlParameterSource.addValue("PLAC", plac);
       mapSqlParameterSource.addValue("MID", mid);
        mapSqlParameterSource.addValue("IDM", idm);
        mapSqlParameterSource.addValue("TID", tid);
        mapSqlParameterSource.addValue("LLEAR", ano);
        mapSqlParameterSource.addValue("IDS", ids);
        mapSqlParameterSource.addValue("USID", usid);
        mapSqlParameterSource.addValue("ACT", act);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
 
    public List<Sedes> getSedesbyState() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_SEDES_TB_GET_SEDESBYSTATE_SP")
                .withCatalogName("FIDE_TALLER_SEDES_PKG")
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
    
    public List<Usuario> getListUsuariobyState() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_USUARIOS_TB_GET_USUARIOSBYSTATE_SP")
                .withCatalogName("FIDE_TALLER_USUARIOS_PKG")
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
    
    public Usuario getUsuariobyUsername(String USNAME) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_USUARIOS_TB_GET_USUARIOBYUSERNAME_SP")
                .withCatalogName("FIDE_TALLER_USUARIOS_PKG")
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
        mapSqlParameterSource.addValue("UNAME", USNAME);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Usuario> usuarioList = (List<Usuario>) results.get("DATOS");
        return usuarioList.isEmpty() ? null : usuarioList.get(0);
    }
    
     public List<Tipos> getTiposbyState() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_TIPOS_TB_GET_TIPOSBYSTATE_SP")
                .withCatalogName("FIDE_TALLER_TIPOS_PKG")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Tipos>() {
                    @Override
                    public Tipos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Tipos tipo = new Tipos();
                        tipo.setIdTipo(rs.getLong("ID_TIPO"));
                        tipo.setNombre(rs.getString("NOMBRE"));
                        tipo.setEstado(rs.getBoolean("ESTADO"));
                        return tipo;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Tipos> tipoList = (List<Tipos>) results.get("DATOS");
        return tipoList;
    }
     
    public List<Marcas> getMarcasbyState() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_MARCAS_TB_GET_MARCASBYSTATE_SP")
                .withCatalogName("FIDE_TALLER_MARCAS_PKG")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Marcas>() {
                    @Override
                    public Marcas mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Marcas marca = new Marcas();
                        marca.setIdMarca(rs.getLong("ID_MARCA"));
                        marca.setNombre(rs.getString("NOMBRE"));
                        marca.setEstado(rs.getBoolean("ESTADO"));
                        return marca;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Marcas> marcaList = (List<Marcas>) results.get("DATOS");
        return marcaList;
    }
    
    public List<Modelos> getModelosbyMarca(Long MID) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_MODELOS_TB_GET_MODELOSBYMARCA_SP")
                .withCatalogName("FIDE_TALLER_MODELOS_PKG")
                .declareParameters(new SqlParameter("MID", Types.BIGINT),new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Modelos>() {
                    @Override
                    public Modelos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Modelos modelo = new Modelos();
                        modelo.setIdModelo(rs.getLong("ID_MODELO"));
                        modelo.setNombre(rs.getString("NOMBRE"));
                        modelo.setIdMarca(rs.getLong("ID_MARCA"));
                        modelo.setEstado(rs.getBoolean("ESTADO"));
                        return modelo;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
         mapSqlParameterSource.addValue("MID", MID);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Modelos> modeloList = (List<Modelos>) results.get("DATOS");
        return modeloList;
    }
    
    public List<Modelos> getModelos() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_MODELOS_TB_GET_MODELOS_SP")
                .withCatalogName("FIDE_TALLER_MODELOS_PKG")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Modelos>() {
                    @Override
                    public Modelos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Modelos modelo = new Modelos();
                        modelo.setIdModelo(rs.getLong("ID_MODELO"));
                        modelo.setNombre(rs.getString("NOMBRE"));
                        modelo.setIdMarca(rs.getLong("ID_MARCA"));
                        modelo.setEstado(rs.getBoolean("ESTADO"));
                        return modelo;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Modelos> modeloList = (List<Modelos>) results.get("DATOS");
        return modeloList;
    }
    
    public List<Marcas> getMarcas() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_MARCAS_TB_GET_MARCAS_SP")
                .withCatalogName("FIDE_TALLER_MARCAS_PKG")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Marcas>() {
                    @Override
                    public Marcas mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Marcas marca = new Marcas();
                        marca.setIdMarca(rs.getLong("ID_MARCA"));
                        marca.setNombre(rs.getString("NOMBRE"));
                        marca.setEstado(rs.getBoolean("ESTADO"));
                        return marca;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Marcas> marcaList = (List<Marcas>) results.get("DATOS");
        return marcaList;
    }
    
    public List<Tipos> getTipos() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_TIPOS_TB_GET_TIPOS_SP")
                .withCatalogName("FIDE_TALLER_TIPOS_PKG")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Tipos>() {
                    @Override
                    public Tipos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Tipos tipo = new Tipos();
                        tipo.setIdTipo(rs.getLong("ID_TIPO"));
                        tipo.setNombre(rs.getString("NOMBRE"));
                        tipo.setEstado(rs.getBoolean("ESTADO"));
                        return tipo;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Tipos> tipoList = (List<Tipos>) results.get("DATOS");
        return tipoList;
    }
    
    public List<Sedes> getSedes() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_SEDES_TB_GET_SEDES_SP")
                .withCatalogName("FIDE_TALLER_SEDES_PKG")
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
    
    public List<Usuario> getUsuarios() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_USUARIOS_TB_GET_USUARIOS_SP")
                .withCatalogName("FIDE_TALLER_USUARIOS_PKG")
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
