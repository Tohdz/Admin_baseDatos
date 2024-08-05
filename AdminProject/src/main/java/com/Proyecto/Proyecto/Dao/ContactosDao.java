/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Dao;
import com.Proyecto.Proyecto.Domain.Contactos;
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


@Repository
public class ContactosDao  {
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public List<Contactos> getListContactos() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("GET_CONTACTO")
                .withCatalogName("PACKAGE_CONTACTO")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Contactos>() {
                    @Override
                    public Contactos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Contactos contacto = new Contactos();
                        contacto.setIdContacto(rs.getLong("ID_CONTACTO"));
                        contacto.setNombre(rs.getString("NOMBRE"));
                        contacto.setApellido(rs.getString("APELLIDO"));
                        contacto.setNumero(rs.getInt("NUMERO"));
                        contacto.setMensaje(rs.getString("MENSAJE"));
                        return contacto;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Contactos> contactoList = (List<Contactos>) results.get("DATOS");
        return contactoList;
    }
    
    public void saveContacto(Contactos contacto) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("ADD_CONTACTO")
                .withCatalogName("PACKAGE_CONTACTO")
                .declareParameters(
                        new SqlParameter("NOMBRE", Types.VARCHAR),
                        new SqlParameter("APELLIDO", Types.VARCHAR),
                        new SqlParameter("NUMERO", Types.BIGINT),
                        new SqlParameter("MENSAJE", Types.VARCHAR)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("NOMBRE", contacto.getNombre());
        mapSqlParameterSource.addValue("APELLIDO", contacto.getApellido());
        mapSqlParameterSource.addValue("NUMERO", contacto.getNumero());
        mapSqlParameterSource.addValue("MENSAJE", contacto.getMensaje());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public void deleteContacto(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("DELETE_CONTACTO")
                .withCatalogName("PACKAGE_CONTACTO")
                .declareParameters(new SqlParameter("CID", Types.BIGINT));
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("CID", id);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }  
}