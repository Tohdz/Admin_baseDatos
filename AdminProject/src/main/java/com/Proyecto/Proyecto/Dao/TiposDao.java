/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Dao;

import com.Proyecto.Proyecto.Domain.Tipos;
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
public class TiposDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Tipos> getListTipos() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_TIPOS_TB_GET_TIPOS_SP")
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

    public Tipos getOneTipo(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_TIPOS_TB_GET_TIPO_SP")
                .declareParameters(new SqlParameter("CID", Types.BIGINT),new SqlParameter("DATOS", Types.REF_CURSOR))
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
        mapSqlParameterSource.addValue("CID", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Tipos> tipoList = (List<Tipos>) results.get("DATOS");
        return tipoList.isEmpty() ? null : tipoList.get(0);
    }

    public void saveTipo(Tipos tipo) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_TIPOS_TB_ADD_TIPO_SP")
                .declareParameters(
                        new SqlParameter("NOM", Types.VARCHAR),
                        new SqlParameter("ACT", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("NOM", tipo.getNombre());
        mapSqlParameterSource.addValue("ACT", tipo.isEstado());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public void deleteTipo(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_TIPOS_TB_DELETE_TIPO_SP")
                .declareParameters(new SqlParameter("CID", Types.BIGINT));
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("CID", id);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public void updateTipo(Long id, String NOM,boolean ACT) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_TIPOS_TB_UPDATE_TIPO_SP")
                .declareParameters(
                        new SqlParameter("CID", Types.BIGINT),
                        new SqlParameter("NOM", Types.VARCHAR),
                        new SqlParameter("ACT", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("CID",id);
        mapSqlParameterSource.addValue("NOM", NOM);
        mapSqlParameterSource.addValue("ACT", ACT);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
}
