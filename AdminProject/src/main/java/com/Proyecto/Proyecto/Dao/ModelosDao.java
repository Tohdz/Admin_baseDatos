/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Dao;

import com.Proyecto.Proyecto.Domain.Marcas;
import com.Proyecto.Proyecto.Domain.Modelos;
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
public class ModelosDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Modelos> getListModelos() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_MODELOS_TB_GET_MODELOS_SP")
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

    public Modelos getOneModelo(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_MODELOS_TB_GET_MODELO_SP")
                .declareParameters(new SqlParameter("CID", Types.BIGINT), new SqlParameter("DATOS", Types.REF_CURSOR))
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
        mapSqlParameterSource.addValue("CID", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Modelos> modeloList = (List<Modelos>) results.get("DATOS");
        return modeloList.isEmpty() ? null : modeloList.get(0);
    }

    public void saveModelo(Modelos modelo) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_MODELOS_TB_ADD_MODELO_SP")
                .declareParameters(
                        new SqlParameter("NOM", Types.VARCHAR),
                        new SqlParameter("MID", Types.BIGINT),
                        new SqlParameter("ACT", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("NOM", modelo.getNombre());
        mapSqlParameterSource.addValue("MID", modelo.getIdMarca());
        mapSqlParameterSource.addValue("ACT", modelo.isEstado());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }

    public void deleteModelo(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_MODELOS_TB_DELETE_MODELO_SP")
                .declareParameters(new SqlParameter("CID", Types.BIGINT));
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("CID", id);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }

    public void updateModelo(Long id, String NOM,Long MID, boolean ACT) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_MODELOS_TB_UPDATE_MODELO_SP")
                .declareParameters(
                        new SqlParameter("CID", Types.BIGINT),
                        new SqlParameter("NOM", Types.VARCHAR),
                        new SqlParameter("MID", Types.BIGINT),
                        new SqlParameter("ACT", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("CID", id);
        mapSqlParameterSource.addValue("NOM", NOM);
        mapSqlParameterSource.addValue("MID", MID);
        mapSqlParameterSource.addValue("ACT", ACT);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public List<Marcas> getMarcasbyState() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_MARCAS_TB_GET_MARCASBYSTATE_SP")
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
    
    public List<Marcas> getMarcas() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_MARCAS_TB_GET_MARCAS_SP")
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
}
