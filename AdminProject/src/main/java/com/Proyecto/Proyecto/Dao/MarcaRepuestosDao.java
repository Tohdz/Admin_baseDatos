/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Dao;

import com.Proyecto.Proyecto.Domain.MarcaRepuestos;
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
public class MarcaRepuestosDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<MarcaRepuestos> getListMarcaRepuestos() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_MARCAS_REPUESTOS_TB_GET_MARCAS_SP")
                .withCatalogName("FIDE_TALLER_MARCAS_REPUESTOS_PKG")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<MarcaRepuestos>() {
                    @Override
                    public MarcaRepuestos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        MarcaRepuestos marcarepuesto = new MarcaRepuestos();
                        marcarepuesto.setIdMarcaRepuesto(rs.getLong("ID_MARCA_REPUESTO"));
                        marcarepuesto.setNombre(rs.getString("NOMBRE"));
                        marcarepuesto.setEstado(rs.getBoolean("ESTADO"));
                        return marcarepuesto;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<MarcaRepuestos> marcarepuestoList = (List<MarcaRepuestos>) results.get("DATOS");
        return marcarepuestoList;
    }

    public MarcaRepuestos getMarcaRepuesto(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_MARCAS_REPUESTOS_TB_GET_MARCA_SP")
                .withCatalogName("FIDE_TALLER_MARCAS_REPUESTOS_PKG")
                .declareParameters(new SqlParameter("CID", Types.BIGINT), new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<MarcaRepuestos>() {
                    @Override
                    public MarcaRepuestos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        MarcaRepuestos marcarepuesto = new MarcaRepuestos();
                        marcarepuesto.setIdMarcaRepuesto(rs.getLong("ID_MARCA_REPUESTO"));
                        marcarepuesto.setNombre(rs.getString("NOMBRE"));
                        marcarepuesto.setEstado(rs.getBoolean("ESTADO"));
                        return marcarepuesto;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("CID", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<MarcaRepuestos> marcarepuestoList = (List<MarcaRepuestos>) results.get("DATOS");
        return marcarepuestoList.isEmpty() ? null : marcarepuestoList.get(0);
    }

    public void saveMarcaR(MarcaRepuestos marcarepuesto) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_MARCAS_REPUESTOS_TB_ADD_MARCA_SP")
                .withCatalogName("FIDE_TALLER_MARCAS_REPUESTOS_PKG")
                .declareParameters(
                        new SqlParameter("NOM", Types.VARCHAR),
                        new SqlParameter("ACT", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("NOM", marcarepuesto.getNombre());
        mapSqlParameterSource.addValue("ACT", marcarepuesto.isEstado());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }

    public void deleteMarcaR(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_MARCAS_REPUESTOS_TB_DELETE_MARCA_SP")
                .withCatalogName("FIDE_TALLER_MARCAS_REPUESTOS_PKG")
                .declareParameters(new SqlParameter("CID", Types.BIGINT));
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("CID", id);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }

    public void updateMarcaR(Long id, String NOM, boolean ACT) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_MARCAS_REPUESTOS_TB_UPDATE_MARCA_SP")
                .withCatalogName("FIDE_TALLER_MARCAS_REPUESTOS_PKG")
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
