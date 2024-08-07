/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Dao;

import com.Proyecto.Proyecto.Domain.Servicios;
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
public class ServicioDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Servicios> getListServicios() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_SERVICIOS_TB_GET_SERVICIOS_SP")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Servicios>() {
                    @Override
                    public Servicios mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Servicios servicio = new Servicios();
                        servicio.setIdServicio(rs.getLong("ID_SERVICIO"));
                        servicio.setNombre(rs.getString("NOMBRE"));
                        servicio.setEstado(rs.getBoolean("ESTADO"));
                        return servicio;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Servicios> servicioList = (List<Servicios>) results.get("DATOS");
        return servicioList;
    }

    public Servicios getOneServicio(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_SERVICIOS_TB_GET_SERVICIO_SP")
                .declareParameters(new SqlParameter("CID", Types.BIGINT),new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Servicios>() {
                    @Override
                    public Servicios mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Servicios servicio = new Servicios();
                        servicio.setIdServicio(rs.getLong("ID_SERVICIO"));
                        servicio.setNombre(rs.getString("NOMBRE"));
                        servicio.setEstado(rs.getBoolean("ESTADO"));
                        return servicio;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("CID", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Servicios> servicioList = (List<Servicios>) results.get("DATOS");
        return servicioList.isEmpty() ? null : servicioList.get(0);
    }

    public void saveServicio(Servicios servicio) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_SERVICIOS_TB_ADD_SERVICIO_SP")
                .declareParameters(
                        new SqlParameter("NOM", Types.VARCHAR),
                        new SqlParameter("ACT", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("NOM", servicio.getNombre());
        mapSqlParameterSource.addValue("ACT", servicio.isEstado());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public void deleteServicio(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_SERVICIOS_TB_DELETE_SERVICIO_SP")
                .declareParameters(new SqlParameter("CID", Types.BIGINT));
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("CID", id);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public void updateServicio(Long id, String NOM,boolean ACT) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_SERVICIOS_TB_UPDATE_SERVICIO_SP")
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
