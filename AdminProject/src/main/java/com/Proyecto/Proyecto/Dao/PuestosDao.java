/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Dao;

import com.Proyecto.Proyecto.Domain.Puestos;
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
public class PuestosDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Puestos> getListPuestos() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_PUESTOS_TB_GET_PUESTOS_SP")
                .withCatalogName("FIDE_TALLER_PUESTOS_PKG")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Puestos>() {
                    @Override
                    public Puestos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Puestos puesto = new Puestos();
                        puesto.setIdPuesto(rs.getLong("ID_PUESTO"));
                        puesto.setNombre(rs.getString("NOMBRE"));
                        puesto.setEstado(rs.getBoolean("ESTADO"));
                        return puesto;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Puestos> puestoList = (List<Puestos>) results.get("DATOS");
        return puestoList;
    }

    public Puestos getOnePuesto(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_PUESTOS_TB_GET_PUESTO_SP")
                .withCatalogName("FIDE_TALLER_PUESTOS_PKG")
                .declareParameters(new SqlParameter("CID", Types.BIGINT), new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Puestos>() {
                    @Override
                    public Puestos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Puestos puesto = new Puestos();
                        puesto.setIdPuesto(rs.getLong("ID_PUESTO"));
                        puesto.setNombre(rs.getString("NOMBRE"));
                        puesto.setEstado(rs.getBoolean("ESTADO"));
                        return puesto;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("CID", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Puestos> puestoList = (List<Puestos>) results.get("DATOS");
        return puestoList.isEmpty() ? null : puestoList.get(0);
    }

    public void savePuesto(Puestos puesto) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_PUESTOS_TB_ADD_PUESTO_SP")
                .withCatalogName("FIDE_TALLER_PUESTOS_PKG")
                .declareParameters(
                        new SqlParameter("NOM", Types.VARCHAR),
                        new SqlParameter("ACT", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("NOM", puesto.getNombre());
        mapSqlParameterSource.addValue("ACT", puesto.isEstado());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }

    public void deletePuesto(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_PUESTOS_TB_DELETE_PUESTO_SP")
                .withCatalogName("FIDE_TALLER_PUESTOS_PKG")
                .declareParameters(new SqlParameter("CID", Types.BIGINT));
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("CID", id);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }

    public void updatePuesto(Long id, String NOM, boolean ACT) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_PUESTOS_TB_UPDATE_PUESTO_SP")
                .withCatalogName("FIDE_TALLER_PUESTOS_PKG")
                .declareParameters(
                        new SqlParameter("CID", Types.BIGINT),
                        new SqlParameter("NOM", Types.VARCHAR),
                        new SqlParameter("ACT", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("CID", id);
        mapSqlParameterSource.addValue("NOM", NOM);
        mapSqlParameterSource.addValue("ACT", ACT);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
}
