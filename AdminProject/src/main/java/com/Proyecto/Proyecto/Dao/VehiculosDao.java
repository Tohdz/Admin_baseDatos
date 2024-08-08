/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Dao;

import com.Proyecto.Proyecto.Domain.Sedes;
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
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Vehiculos>() {
                    @Override
                    public Vehiculos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Vehiculos vehiculo = new Vehiculos();
                        vehiculo.setPlaca(rs.getString("PLACA"));
                        vehiculo.setIdMarca(rs.getLong("ID_MARCA"));
                        vehiculo.setIdModelo(rs.getLong("ID_MODELO"));
                        vehiculo.setIdTipo(rs.getLong("ID_TIPO"));
                        vehiculo.setAño(rs.getInt("AÑO"));
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

    public Vehiculos getOneVehiculo(String placa) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_VEHICULOS_TB_GET_VEHICULO_SP")
                .declareParameters(new SqlParameter("PLACA", Types.VARCHAR),new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Vehiculos>() {
                    @Override
                    public Vehiculos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Vehiculos vehiculo = new Vehiculos();
                        vehiculo.setPlaca(rs.getString("PLACA"));
                        vehiculo.setIdMarca(rs.getLong("ID_MARCA"));
                        vehiculo.setIdModelo(rs.getLong("ID_MODELO"));
                        vehiculo.setIdTipo(rs.getLong("ID_TIPO"));
                        vehiculo.setAño(rs.getInt("AÑO"));
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
                .declareParameters(
                        new SqlParameter("PLAC", Types.VARCHAR),
                        new SqlParameter("MID", Types.BIGINT),
                        new SqlParameter("IDM", Types.BIGINT),
                        new SqlParameter("TID", Types.BIGINT),
                        new SqlParameter("ANO", Types.BIGINT),
                        new SqlParameter("IDS", Types.BIGINT),
                        new SqlParameter("USID", Types.BIGINT),
                        new SqlParameter("ACT", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("PLAC", vehiculo.getPlaca());
        mapSqlParameterSource.addValue("MID", vehiculo.getIdMarca());
        mapSqlParameterSource.addValue("IDM", vehiculo.getIdModelo());
        mapSqlParameterSource.addValue("TID", vehiculo.getIdTipo());
        mapSqlParameterSource.addValue("ANO", vehiculo.getAño());
        mapSqlParameterSource.addValue("IDS", vehiculo.getIdSede());
        mapSqlParameterSource.addValue("USID", vehiculo.getIdUsuario());
        mapSqlParameterSource.addValue("ACT", vehiculo.isEstado());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public void deleteVehiculo(String PLACA) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_VEHICULOS_TB_DELETE_VEHICULO_SP")
                .declareParameters(new SqlParameter("PLACA", Types.VARCHAR));
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("PLACA", PLACA);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public void updateVehiculo(String plac,Long mid,Long idm,Long tid,int ano,Long ids,Long usid,boolean act) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_VEHICULOS_TB_UPDATE_VEHICULO_SP") 
                .declareParameters(
                        new SqlParameter("PLAC", Types.VARCHAR),
                        new SqlParameter("MID", Types.BIGINT),
                        new SqlParameter("IDM", Types.BIGINT),
                        new SqlParameter("TID", Types.BIGINT),
                        new SqlParameter("ANO", Types.BIGINT),
                        new SqlParameter("IDS", Types.BIGINT),
                        new SqlParameter("USID", Types.BIGINT),
                        new SqlParameter("ACT", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
       mapSqlParameterSource.addValue("PLAC", plac);
       mapSqlParameterSource.addValue("MID", mid);
        mapSqlParameterSource.addValue("IDM", idm);
        mapSqlParameterSource.addValue("TID", tid);
        mapSqlParameterSource.addValue("ANO", ano);
        mapSqlParameterSource.addValue("IDS", ids);
        mapSqlParameterSource.addValue("USID", usid);
        mapSqlParameterSource.addValue("ACT", act);
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
