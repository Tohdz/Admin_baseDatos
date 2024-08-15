/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Dao;

import com.Proyecto.Proyecto.Domain.Empleado;
import com.Proyecto.Proyecto.Domain.Puestos;
import com.Proyecto.Proyecto.Domain.Sedes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
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
public class EmpleadoDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Empleado> getListEmpleado() {
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
                        empleado.setUsername(rs.getString("USERNAME"));
                        return empleado;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Empleado> empleadoList = (List<Empleado>) results.get("DATOS");
        return empleadoList;
    }
    
    public List<Empleado> getEmpleadosbysede(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_EMPLEADOS_TB_GET_EMPLEADOSBYSEDE_SP")
                .declareParameters(new SqlParameter("IDSED", Types.BIGINT),new SqlParameter("DATOS", Types.REF_CURSOR))
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
                        empleado.setUsername(rs.getString("USERNAME"));
                        return empleado;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("IDSED", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Empleado> empleadoList = (List<Empleado>) results.get("DATOS");
        return empleadoList;
    }
    
    public Empleado getEmpleadosbyusername(String name) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_EMPLEADOS_TB_GET_EMPLEADOSBYUSERNAME_SP")
                .declareParameters(new SqlParameter("UNAME", Types.VARCHAR),new SqlParameter("DATOS", Types.REF_CURSOR))
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
                        empleado.setUsername(rs.getString("USERNAME"));
                        return empleado;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("UNAME", name);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Empleado> empleadoList = (List<Empleado>) results.get("DATOS");
        return empleadoList.isEmpty() ? null : empleadoList.get(0);
    }

    public Empleado getOneEmpleado(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_EMPLEADOS_TB_GET_EMPLEADO_SP")
                .declareParameters(new SqlParameter("EID", Types.BIGINT),new SqlParameter("DATOS", Types.REF_CURSOR))
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
                        empleado.setUsername(rs.getString("USERNAME"));
                        return empleado;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("EID", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Empleado> empleadoList = (List<Empleado>) results.get("DATOS");
        return empleadoList.isEmpty() ? null : empleadoList.get(0);
    }

    public void saveEmpleado(Empleado empleado) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_EMPLEADOS_TB_ADD_EMPLEADO_SP")
                .declareParameters(
                        new SqlParameter("NOM", Types.VARCHAR),
                        new SqlParameter("APE", Types.VARCHAR),
                        new SqlParameter("TEL", Types.VARCHAR),
                        new SqlParameter("CORRE", Types.VARCHAR),
                        new SqlParameter("FECH", Types.DATE),
                        new SqlParameter("SAL", Types.VARCHAR),
                        new SqlParameter("IDP", Types.BIGINT),
                        new SqlParameter("IDS", Types.BIGINT),
                        new SqlParameter("ACT", Types.BOOLEAN),
                        new SqlParameter("UNAME", Types.VARCHAR)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("NOM", empleado.getNombre());
        mapSqlParameterSource.addValue("APE", empleado.getApellido());
        mapSqlParameterSource.addValue("TEL", empleado.getTelefono());
        mapSqlParameterSource.addValue("CORRE", empleado.getCorreo());
        mapSqlParameterSource.addValue("FECH", empleado.getFecha());
        mapSqlParameterSource.addValue("SAL", empleado.getSalario());
        mapSqlParameterSource.addValue("IDP", empleado.getIdPuesto());
        mapSqlParameterSource.addValue("IDS", empleado.getIdSede());
        mapSqlParameterSource.addValue("ACT", empleado.isEstado());
        mapSqlParameterSource.addValue("UNAME", empleado.getUsername());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public void deleteEmpleado(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_EMPLEADOS_TB_DELETE_EMPLEADO_SP")
                .declareParameters(new SqlParameter("EID", Types.BIGINT));
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("EID", id);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public void updateEmpleado(Long EID,String NOM,String APE,String TEL,String CORRE,Date FECH,String SAL,Long IDP,Long IDS,boolean ACT,String UNAME) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_EMPLEADOS_TB_UPDATE_EMPLEADO_SP")
                .declareParameters(
                        new SqlParameter("EID", Types.BIGINT),
                        new SqlParameter("NOM", Types.VARCHAR),
                        new SqlParameter("APE", Types.VARCHAR),
                        new SqlParameter("TEL", Types.VARCHAR),
                        new SqlParameter("CORRE", Types.VARCHAR),
                        new SqlParameter("FECH", Types.DATE),
                        new SqlParameter("SAL", Types.VARCHAR),
                        new SqlParameter("IDP", Types.BIGINT),
                        new SqlParameter("IDS", Types.BIGINT),
                        new SqlParameter("ACT", Types.BOOLEAN),
                        new SqlParameter("UNAME", Types.VARCHAR)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
       mapSqlParameterSource.addValue("EID", EID);
       mapSqlParameterSource.addValue("NOM", NOM);
        mapSqlParameterSource.addValue("APE", APE);
        mapSqlParameterSource.addValue("TEL", TEL);
        mapSqlParameterSource.addValue("CORRE", CORRE);
        mapSqlParameterSource.addValue("FECH", FECH);
        mapSqlParameterSource.addValue("SAL", SAL);
        mapSqlParameterSource.addValue("IDP", IDP);
        mapSqlParameterSource.addValue("IDS", IDS);
        mapSqlParameterSource.addValue("ACT", ACT);
        mapSqlParameterSource.addValue("UNAME", UNAME);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public List<Puestos> getPuestosbyState() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_PUESTOS_TB_GET_PUESTOSBYSTATE_SP")
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
    
    public List<Puestos> getPuestos() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_PUESTOS_TB_GET_PUESTOS_SP")
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
