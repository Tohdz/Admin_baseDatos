/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Dao;

import com.Proyecto.Proyecto.Domain.Citas;
import com.Proyecto.Proyecto.Domain.Empleado;
import com.Proyecto.Proyecto.Domain.Ordenes;
import com.Proyecto.Proyecto.Domain.Sedes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
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
public class OrdenesDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Ordenes> getOrdenes() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_ORDENES_TB_GET_ORDENES_SP")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Ordenes>() {
                    @Override
                    public Ordenes mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Ordenes orden = new Ordenes();
                        orden.setIdOrden(rs.getLong("ID_ORDEN"));
                        orden.setIdCita(rs.getLong("ID_CITA"));
                        orden.setFechaHora(rs.getObject("FECHA", LocalDateTime.class));
                        orden.setComentario(rs.getString("COMENTARIOS"));
                        orden.setIdEmpleado(rs.getLong("ID_EMPLEADO"));
                        orden.setIdSede(rs.getLong("ID_SEDE"));
                        return orden;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Ordenes> ordenList = (List<Ordenes>) results.get("DATOS");
        return ordenList;
    }

    public Ordenes getOrden(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_ORDENES_TB_GET_ORDEN_SP")
                .declareParameters(new SqlParameter("OID", Types.BIGINT),new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Ordenes>() {
                    @Override
                    public Ordenes mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Ordenes orden = new Ordenes();
                        orden.setIdOrden(rs.getLong("ID_ORDEN"));
                        orden.setIdCita(rs.getLong("ID_CITA"));
                        orden.setFechaHora(rs.getObject("FECHA", LocalDateTime.class));
                        orden.setComentario(rs.getString("COMENTARIOS"));
                        orden.setIdEmpleado(rs.getLong("ID_EMPLEADO"));
                        orden.setIdSede(rs.getLong("ID_SEDE"));
                        return orden;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("OID", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Ordenes> ordenList = (List<Ordenes>) results.get("DATOS");
        return ordenList.isEmpty() ? null : ordenList.get(0);
    }

    public void saveOrden(Ordenes orden) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_ORDENES_TB_ADD_ORDEN_SP")
                .declareParameters(
                        new SqlParameter("CID", Types.BIGINT),
                        new SqlParameter("FECH", Types.DATE),
                        new SqlParameter("COMS", Types.VARCHAR),
                        new SqlParameter("EMPID", Types.BIGINT),
                        new SqlParameter("IDSED", Types.BIGINT)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("CID", orden.getIdCita());
        mapSqlParameterSource.addValue("FECH", orden.getFechaHora());
        mapSqlParameterSource.addValue("COMS", orden.getComentario());
        mapSqlParameterSource.addValue("EMPID", orden.getIdEmpleado());
        mapSqlParameterSource.addValue("IDSED", orden.getIdSede());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public void deleteOrden(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_ORDENES_TB_DELETE_ORDEN_SP")
                .declareParameters(new SqlParameter("OID", Types.BIGINT));
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("OID", id);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public void updateOrden(Long OID, Long CID,LocalDateTime FECH,String COMS,Long EMPID,Long IDSED) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_ORDENES_TB_UPDATE_ORDEN_SP")
                .declareParameters(
                        new SqlParameter("OID", Types.BIGINT),
                        new SqlParameter("CID", Types.BIGINT),
                        new SqlParameter("FECH", Types.DATE),
                        new SqlParameter("COMS", Types.VARCHAR),
                        new SqlParameter("EMPID", Types.BIGINT),
                        new SqlParameter("IDSED", Types.BIGINT)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
       mapSqlParameterSource.addValue("OID", OID);
       mapSqlParameterSource.addValue("CID", CID);
        mapSqlParameterSource.addValue("FECH", FECH);
        mapSqlParameterSource.addValue("COMS", COMS);
        mapSqlParameterSource.addValue("EMPID", EMPID);
        mapSqlParameterSource.addValue("IDSED", IDSED);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public List<Sedes> getSedesbyState() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_SEDES_TB_GET_SEDESBYSTATE_SP")
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
    
    public List<Empleado> getEmpleadobyState() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_EMPLEADOS_TB_GET_EMPLEADOSBYSTATE_SP")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Empleado>() {
                    @Override
                    public Empleado mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Empleado empleado = new Empleado();
                        empleado.setIdEmpleado(rs.getLong("ID_EMPLEADO"));
                        empleado.setNombre(rs.getString("NOMBRE"));
                        empleado.setApellido(rs.getString("APELLIDO"));
                        empleado.setTelefono(rs.getString("TELEFONO"));
                        empleado.setCorreo(rs.getString("CORREO"));
                        empleado.setFecha(rs.getDate("FECHA_CONTRATO"));
                        empleado.setSalario(rs.getString("SALARIO"));
                        empleado.setIdPuesto(rs.getLong("ID_PUESTO"));
                        empleado.setIdSede(rs.getLong("ID_SEDE"));
                        empleado.setEstado(rs.getBoolean("ESTADO"));
                        return empleado;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Empleado> empleadoList = (List<Empleado>) results.get("DATOS");
        return empleadoList;
    }
    
    public List<Citas> getCitasbyState() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_CITAS_TB_GET_CITASBYSTATE_SP")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Citas>() {
                    @Override
                    public Citas mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Citas cita = new Citas();
                        cita.setIdCita(rs.getLong("ID_CITA"));
                        cita.setPlaca(rs.getString("PLACA"));
                        cita.setFechaHora(rs.getObject("FECHA", LocalDateTime.class));
                        cita.setIdServicio(rs.getLong("ID_SERVICIO"));
                        cita.setIdEmpleado(rs.getLong("ID_EMPLEADO"));
                        cita.setIdSede(rs.getLong("ID_SEDE"));
                        cita.setEstado(rs.getBoolean("ESTADO"));
                        return cita;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Citas> citaList = (List<Citas>) results.get("DATOS");
        return citaList;
    }
    
    public List<Citas> getCitas() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_CITAS_TB_GET_CITAS_SP")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Citas>() {
                    @Override
                    public Citas mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Citas cita = new Citas();
                        cita.setIdCita(rs.getLong("ID_CITA"));
                        cita.setPlaca(rs.getString("PLACA"));
                        cita.setFechaHora(rs.getObject("FECHA", LocalDateTime.class));
                        cita.setIdServicio(rs.getLong("ID_SERVICIO"));
                        cita.setIdEmpleado(rs.getLong("ID_EMPLEADO"));
                        cita.setIdSede(rs.getLong("ID_SEDE"));
                        cita.setEstado(rs.getBoolean("ESTADO"));
                        return cita;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Citas> citaList = (List<Citas>) results.get("DATOS");
        return citaList;
    }
    
    public List<Empleado> getEmpleados() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_EMPLEADOS_TB_GET_EMPLEADOS_SP")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Empleado>() {
                    @Override
                    public Empleado mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Empleado empleado = new Empleado();
                        empleado.setIdEmpleado(rs.getLong("ID_EMPLEADO"));
                        empleado.setNombre(rs.getString("NOMBRE"));
                        empleado.setApellido(rs.getString("APELLIDO"));
                        empleado.setTelefono(rs.getString("TELEFONO"));
                        empleado.setCorreo(rs.getString("CORREO"));
                        empleado.setFecha(rs.getDate("FECHA_CONTRATO"));
                        empleado.setSalario(rs.getString("SALARIO"));
                        empleado.setIdPuesto(rs.getLong("ID_PUESTO"));
                        empleado.setIdSede(rs.getLong("ID_SEDE"));
                        empleado.setEstado(rs.getBoolean("ESTADO"));
                        return empleado;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Empleado> empleadoList = (List<Empleado>) results.get("DATOS");
        return empleadoList;
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
