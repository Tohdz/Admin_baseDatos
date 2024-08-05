/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Proyecto.Proyecto.Domain.Detalle_Factura;
import java.sql.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class Detalle_FacturaDao  {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void savedetalle(Detalle_Factura detalle) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("ADD_DETALLE_FACTURA")
                .withCatalogName("PACKAGE_DETALLE_FACTURA")
                .declareParameters(
                        new SqlParameter("IDFACT", Types.BIGINT),
                        new SqlParameter("IDJUEGO", Types.BIGINT),
                        new SqlParameter("PRECIO", Types.DOUBLE),
                        new SqlParameter("CANTIDAD", Types.BIGINT)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("IDFACT", detalle.getIdFactura());
        mapSqlParameterSource.addValue("IDJUEGO", detalle.getId_juego());
        mapSqlParameterSource.addValue("PRECIO", detalle.getPrecio());
        mapSqlParameterSource.addValue("CANTIDAD", detalle.getCantidad());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
}

