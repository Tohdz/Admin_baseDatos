/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Dao;

import com.Proyecto.Proyecto.Domain.Citas;
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
public class CitasDao {
    
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    public List<Citas> getListCitas() {
//        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
//                .withSchemaName("ADMIN_FIDE_TALLER_USER")
//                .withProcedureName("FIDE_EMPLEADOS_TB_GET_EMPLEADOS_SP")
//                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
//                .returningResultSet("DATOS", new RowMapper<Citas>() {
//                    @Override
//                    public Citas mapRow(ResultSet rs, int rowNum) throws SQLException {
//                        Citas cita = new Citas();
//                        cita.setIdCita(rs.getLong("ID_CITA"));
//                        cita.setPlaca(rs.getString("PLACA"));
//                        cita.setFechaHora(rs.getDate("FECHA"));
//                        cita.setIdServicio(rs.getLong("ID_SERVICIO"));
//                        cita.setIdEmpleado(rs.getLong("ID_EMPLEADO"));
//                        cita.setIdSede(rs.getLong("ID_SEDE"));
//                        cita.setEstado(rs.getBoolean("ESTADO"));
//                        return cita;
//                    }
//                });
//        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
//        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
//        List<Citas> citaList = (List<Citas>) results.get("DATOS");
//        return citaList;
//    }
//
//    public Citas getOneCitas(Long id) {
//        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
//                .withSchemaName("ADMIN_FIDE_TALLER_USER")
//                .withProcedureName("FIDE_EMPLEADOS_TB_GET_EMPLEADO_SP")
//                .declareParameters(new SqlParameter("EID", Types.BIGINT),new SqlParameter("DATOS", Types.REF_CURSOR))
//                .returningResultSet("DATOS", new RowMapper<Citas>() {
//                    @Override
//                    public Citas mapRow(ResultSet rs, int rowNum) throws SQLException {
//                        Citas cita = new Citas();
//                        cita.setIdCita(rs.getLong("ID_CITA"));
//                        cita.setPlaca(rs.getString("PLACA"));
//                        cita.setFechaHora(rs.getDate("FECHA"));
//                        cita.setIdServicio(rs.getLong("ID_SERVICIO"));
//                        cita.setIdEmpleado(rs.getLong("ID_EMPLEADO"));
//                        cita.setIdSede(rs.getLong("ID_SEDE"));
//                        cita.setEstado(rs.getBoolean("ESTADO"));
//                        return cita;
//                    }
//                });
//        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
//        mapSqlParameterSource.addValue("EID", id);
//        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
//        List<Citas> citaList = (List<Citas>) results.get("DATOS");
//        return citaList.isEmpty() ? null : citaList.get(0);
//    }
//
//    public void saveCitas(Citas cita) {
//        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
//                .withSchemaName("ADMIN_FIDE_TALLER_USER")
//                .withProcedureName("FIDE_EMPLEADOS_TB_ADD_EMPLEADO_SP")
//                .declareParameters(
//                        new SqlParameter("NOM", Types.VARCHAR),
//                        new SqlParameter("APE", Types.VARCHAR),
//                        new SqlParameter("TEL", Types.VARCHAR),
//                        new SqlParameter("CORRE", Types.VARCHAR),
//                        new SqlParameter("FECH", Types.DATE),
//                        new SqlParameter("SAL", Types.VARCHAR),
//                        new SqlParameter("IDP", Types.BIGINT),
//                        new SqlParameter("IDS", Types.BIGINT),
//                        new SqlParameter("ACT", Types.BOOLEAN)
//                );
//        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
//        mapSqlParameterSource.addValue("NOM", cita.getNombre());
//        mapSqlParameterSource.addValue("APE", cita.getApellido());
//        mapSqlParameterSource.addValue("TEL", cita.getTelefono());
//        mapSqlParameterSource.addValue("CORRE", cita.getCorreo());
//        mapSqlParameterSource.addValue("FECH", cita.getFecha());
//        mapSqlParameterSource.addValue("SAL", cita.getSalario());
//        mapSqlParameterSource.addValue("IDP", cita.getIdPuesto());
//        mapSqlParameterSource.addValue("IDS", cita.getIdSede());
//        mapSqlParameterSource.addValue("ACT", cita.isEstado());
//        simpleJdbcCall.execute(mapSqlParameterSource);
//    }
//    
//    public void deleteCitas(Long id) {
//        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
//                .withSchemaName("ADMIN_FIDE_TALLER_USER")
//                .withProcedureName("FIDE_EMPLEADOS_TB_DELETE_EMPLEADO_SP")
//                .declareParameters(new SqlParameter("EID", Types.BIGINT));
//        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
//        mapSqlParameterSource.addValue("EID", id);
//        simpleJdbcCall.execute(mapSqlParameterSource);
//    }
//    
//    public void updateCitas(Long EID,String NOM,String APE,String TEL,String CORRE,Date FECH,String SAL,Long IDP,Long IDS,boolean ACT) {
//        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
//                .withSchemaName("ADMIN_FIDE_TALLER_USER")
//                .withProcedureName("FIDE_EMPLEADOS_TB_UPDATE_EMPLEADO_SP")
//                .declareParameters(
//                        new SqlParameter("EID", Types.BIGINT),
//                        new SqlParameter("NOM", Types.VARCHAR),
//                        new SqlParameter("APE", Types.VARCHAR),
//                        new SqlParameter("TEL", Types.VARCHAR),
//                        new SqlParameter("CORRE", Types.VARCHAR),
//                        new SqlParameter("FECH", Types.DATE),
//                        new SqlParameter("SAL", Types.VARCHAR),
//                        new SqlParameter("IDP", Types.BIGINT),
//                        new SqlParameter("IDS", Types.BIGINT),
//                        new SqlParameter("ACT", Types.BOOLEAN)
//                );
//        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
//       mapSqlParameterSource.addValue("EID", EID);
//       mapSqlParameterSource.addValue("NOM", NOM);
//        mapSqlParameterSource.addValue("APE", APE);
//        mapSqlParameterSource.addValue("TEL", TEL);
//        mapSqlParameterSource.addValue("CORRE", CORRE);
//        mapSqlParameterSource.addValue("FECH", FECH);
//        mapSqlParameterSource.addValue("SAL", SAL);
//        mapSqlParameterSource.addValue("IDP", IDP);
//        mapSqlParameterSource.addValue("IDS", IDS);
//        mapSqlParameterSource.addValue("ACT", ACT);
//        simpleJdbcCall.execute(mapSqlParameterSource);
//    }
}
