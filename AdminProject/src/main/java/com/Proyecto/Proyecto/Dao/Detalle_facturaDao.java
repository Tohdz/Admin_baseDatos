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
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_DETALLES_TB_ADD_DETALLE_SP")
                .declareParameters(
                        new SqlParameter("IDFACT", Types.BIGINT),
                        new SqlParameter("IDORD", Types.BIGINT),
                        new SqlParameter("IDREP", Types.DOUBLE),
                        new SqlParameter("CANT", Types.BIGINT),
                        new SqlParameter("PREC", Types.BIGINT)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("IDFACT", detalle.getIdFactura());
        mapSqlParameterSource.addValue("IDORD", detalle.getIdOrden());
        mapSqlParameterSource.addValue("IDREP", detalle.getIdRepuesto());
        mapSqlParameterSource.addValue("CANT", detalle.getCantidad());
        mapSqlParameterSource.addValue("PREC", detalle.getPrecio());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
}

