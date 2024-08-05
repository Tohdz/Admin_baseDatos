/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto.Proyecto.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Proyecto.Proyecto.Domain.Rol;
import java.sql.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class RolDao  {
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public void save(Rol rol) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("ADD_ROL")
                .withCatalogName("PACKAGE_ROL")
                .declareParameters(
                        new SqlParameter("RNAME", Types.VARCHAR),
                        new SqlParameter("IDUSER", Types.BIGINT)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("RNAME", rol.getNombre());
        mapSqlParameterSource.addValue("IDUSER", rol.getIdUsuario());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
}
