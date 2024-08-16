/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Dao;

import com.Proyecto.Proyecto.Domain.Marcas;
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
public class MarcasDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Marcas> getListMarcas() {
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

    public Marcas getOneMarca(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_MARCAS_TB_GET_MARCA_SP")
                .withCatalogName("FIDE_TALLER_MARCAS_PKG")
                .declareParameters(new SqlParameter("CID", Types.BIGINT), new SqlParameter("DATOS", Types.REF_CURSOR))
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
        mapSqlParameterSource.addValue("CID", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Marcas> marcaList = (List<Marcas>) results.get("DATOS");
        return marcaList.isEmpty() ? null : marcaList.get(0);
    }

    public void saveMarca(Marcas marca) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_MARCAS_TB_ADD_MARCA_SP")
                .withCatalogName("FIDE_TALLER_MARCAS_PKG")
                .declareParameters(
                        new SqlParameter("NOM", Types.VARCHAR),
                        new SqlParameter("ACT", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("NOM", marca.getNombre());
        mapSqlParameterSource.addValue("ACT", marca.isEstado());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }

    public void deleteMarca(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_MARCAS_TB_DELETE_MARCA_SP")
                .withCatalogName("FIDE_TALLER_MARCAS_PKG")
                .declareParameters(new SqlParameter("CID", Types.BIGINT));
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("CID", id);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }

    public void updateMarca(Long id, String NOM, boolean ACT) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_MARCAS_TB_UPDATE_MARCA_SP")
                .withCatalogName("FIDE_TALLER_MARCAS_PKG")
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
