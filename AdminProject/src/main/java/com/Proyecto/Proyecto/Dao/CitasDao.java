/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Dao;

import com.Proyecto.Proyecto.Domain.Citas;
import com.Proyecto.Proyecto.Domain.Empleado;
import com.Proyecto.Proyecto.Domain.Sedes;
import com.Proyecto.Proyecto.Domain.Servicios;
import com.Proyecto.Proyecto.Domain.Vehiculos;
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
public class CitasDao {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

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
                        cita.setFechaHora(rs.getDate("FECHA"));
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

    public Citas getCita(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_CITAS_TB_GET_CITA_SP")
                .declareParameters(new SqlParameter("CID", Types.BIGINT),new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Citas>() {
                    @Override
                    public Citas mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Citas cita = new Citas();
                        cita.setIdCita(rs.getLong("ID_CITA"));
                        cita.setPlaca(rs.getString("PLACA"));
                        cita.setFechaHora(rs.getDate("FECHA"));
                        cita.setIdServicio(rs.getLong("ID_SERVICIO"));
                        cita.setIdEmpleado(rs.getLong("ID_EMPLEADO"));
                        cita.setIdSede(rs.getLong("ID_SEDE"));
                        cita.setEstado(rs.getBoolean("ESTADO"));
                        return cita;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("CID", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Citas> citaList = (List<Citas>) results.get("DATOS");
        return citaList.isEmpty() ? null : citaList.get(0);
    }

    public void saveCitas(Citas cita) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_CITAS_TB_ADD_CITA_SP")
                .declareParameters(
                        new SqlParameter("PLAC", Types.VARCHAR),
                        new SqlParameter("FECH", Types.DATE),
                        new SqlParameter("SERID", Types.BIGINT),
                        new SqlParameter("EMPID", Types.BIGINT),
                        new SqlParameter("IDSED", Types.BIGINT),
                        new SqlParameter("ACT", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("PLAC", cita.getPlaca());
        mapSqlParameterSource.addValue("FECH", cita.getFechaHora());
        mapSqlParameterSource.addValue("SERID", cita.getIdServicio());
        mapSqlParameterSource.addValue("EMPID", cita.getIdEmpleado());
        mapSqlParameterSource.addValue("IDSED", cita.getIdSede());
        mapSqlParameterSource.addValue("ACT", cita.isEstado());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public void deleteCitas(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_CITAS_TB_DELETE_CITA_SP")
                .declareParameters(new SqlParameter("CID", Types.BIGINT));
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("CID", id);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public void updateCitas(Long ID, String PLAC,Date FECH,Long SERID,Long EMPID,Long IDSED,boolean ACT) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_CITAS_TB_UPDATE_CITA_SP")
                .declareParameters(
                        new SqlParameter("CID", Types.BIGINT),
                        new SqlParameter("PLAC", Types.VARCHAR),
                        new SqlParameter("FECH", Types.DATE),
                        new SqlParameter("SERID", Types.BIGINT),
                        new SqlParameter("EMPID", Types.BIGINT),
                        new SqlParameter("IDSED", Types.BIGINT),
                        new SqlParameter("ACT", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
       mapSqlParameterSource.addValue("CID", ID);
       mapSqlParameterSource.addValue("PLAC", PLAC);
        mapSqlParameterSource.addValue("FECH", FECH);
        mapSqlParameterSource.addValue("SERID", SERID);
        mapSqlParameterSource.addValue("EMPID", EMPID);
        mapSqlParameterSource.addValue("IDSED", IDSED);
        mapSqlParameterSource.addValue("ACT", ACT);
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
    
    public List<Servicios> getServiciosbyState() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_SERVICIOS_TB_GET_SERVICIOSBYSTATE_SP")
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
    
    public List<Vehiculos> getVehiculosbyState() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_VEHICULOS_TB_GET_VEHICULOSBYSTATE_SP")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Vehiculos>() {
                    @Override
                    public Vehiculos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Vehiculos vehiculo = new Vehiculos();
                        vehiculo.setPlaca(rs.getString("PLACA"));
                        vehiculo.setIdMarca(rs.getLong("ID_MARCA"));
                        vehiculo.setIdModelo(rs.getLong("ID_MODELO"));
                        vehiculo.setIdTipo(rs.getLong("ID_TIPO"));
                        vehiculo.setAno(rs.getInt("ANO"));
                        vehiculo.setIdSede(rs.getLong("ID_SEDE"));
                        vehiculo.setIdUsuario(rs.getLong("ID_USUARIO"));
                        vehiculo.setEstado(rs.getBoolean("ESTADO"));
                        return vehiculo;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Vehiculos> vehiculoList = (List<Vehiculos>) results.get("DATOS");
        return vehiculoList;
    }
    
    public List<Servicios> getServicios() {
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
    
    public List<Empleado> getEmpleadoS() {
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
