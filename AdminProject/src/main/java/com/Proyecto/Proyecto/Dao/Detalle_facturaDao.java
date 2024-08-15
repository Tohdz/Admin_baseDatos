/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Proyecto.Dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Proyecto.Proyecto.Domain.Detalle_Factura;
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
                        new SqlParameter("IDREP", Types.BIGINT),
                        new SqlParameter("CANT", Types.BIGINT),
                        new SqlParameter("PREC", Types.DOUBLE)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("IDFACT", detalle.getIdFactura());
        mapSqlParameterSource.addValue("IDORD", detalle.getIdOrden());
        mapSqlParameterSource.addValue("IDREP", detalle.getIdRepuesto());
        mapSqlParameterSource.addValue("CANT", detalle.getCantidad());
        mapSqlParameterSource.addValue("PREC", detalle.getPrecio());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
     public List<Detalle_Factura> getDetalles(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_DETALLES_TB_GET_DETALLESBYFACTURA_SP")
                .declareParameters(new SqlParameter("FID", Types.BIGINT),new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Detalle_Factura>() {
                    @Override
                    public Detalle_Factura mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Detalle_Factura detalle = new Detalle_Factura();
                        detalle.setIdVenta(rs.getLong("ID_DETALLE"));
                        detalle.setIdFactura(rs.getLong("ID_FACTURA"));
                        detalle.setIdOrden(rs.getLong("ID_ORDEN"));
                        detalle.setIdRepuesto(rs.getLong("ID_REPUESTO"));
                        detalle.setCantidad(rs.getInt("CANTIDAD"));
                        detalle.setPrecio(rs.getDouble("PRECIO_UNITARIO"));
                        return detalle;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("FID", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Detalle_Factura> detalleList = (List<Detalle_Factura>) results.get("DATOS");
        return detalleList;
    }
}

