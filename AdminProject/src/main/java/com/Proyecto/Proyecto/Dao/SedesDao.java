/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Dao;

import com.Proyecto.Proyecto.Domain.Sedes;
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
public class SedesDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

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

    public Sedes getSede(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_SEDES_TB_GET_SEDESBYID_SP")
                .declareParameters(new SqlParameter("IDS", Types.BIGINT), new SqlParameter("DATOS", Types.REF_CURSOR))
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
        mapSqlParameterSource.addValue("IDS", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Sedes> sedeList = (List<Sedes>) results.get("DATOS");
        return sedeList.isEmpty() ? null : sedeList.get(0);
    }

    public void saveSede(Sedes sede) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_SEDES_TB_ADD_SEDE_SP")
                .declareParameters(
                        new SqlParameter("NOMB", Types.VARCHAR),
                        new SqlParameter("CIT", Types.VARCHAR),
                        new SqlParameter("DIR", Types.VARCHAR),
                        new SqlParameter("TEL", Types.VARCHAR),
                        new SqlParameter("EST", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("NOMB", sede.getNombre());
        mapSqlParameterSource.addValue("CIT", sede.getCiudad());
        mapSqlParameterSource.addValue("DIR", sede.getDireccion());
        mapSqlParameterSource.addValue("TEL", sede.getTelefono());
        mapSqlParameterSource.addValue("EST", sede.isEstado());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }

    public void deleteSede(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_SEDES_TB_DELETE_SEDE_SP")
                .declareParameters(new SqlParameter("IDS", Types.BIGINT));
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("IDS", id);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }

    public void updateSede(Long ID, String NOM, String CIT,String DIR, String TEL, boolean ACT) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_SEDES_TB_UPDATE_SEDE_SP")
                .declareParameters(
                        new SqlParameter("IDS", Types.BIGINT),
                        new SqlParameter("NOMB", Types.VARCHAR),
                        new SqlParameter("CIT", Types.VARCHAR),
                        new SqlParameter("DIR", Types.VARCHAR),
                        new SqlParameter("TEL", Types.VARCHAR),
                        new SqlParameter("EST", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("IDS", ID);
        mapSqlParameterSource.addValue("NOMB", NOM);
        mapSqlParameterSource.addValue("CIT", CIT);
        mapSqlParameterSource.addValue("DIR", DIR);
        mapSqlParameterSource.addValue("TEL", TEL);
        mapSqlParameterSource.addValue("EST", ACT);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
}
