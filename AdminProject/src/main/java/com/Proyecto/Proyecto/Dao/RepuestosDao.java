package com.Proyecto.Proyecto.Dao;

import com.Proyecto.Proyecto.Domain.Categorias;
import com.Proyecto.Proyecto.Domain.Repuestos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Repository
public class RepuestosDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Repuestos> getListJuegos() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("GET_JUEGOS")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Repuestos>() {
                    @Override
                    public Repuestos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Repuestos repuesto = new Repuestos();
                        repuesto.setId_juego(rs.getLong("ID_JUEGO"));
                        repuesto.setImagen(rs.getString("IMAGEN"));
                        repuesto.setNombre(rs.getString("NOMBRE"));
                        repuesto.setEmpresa(rs.getString("EMPRESA"));
                        repuesto.setPrecio(rs.getDouble("PRECIO"));
                        repuesto.setExistencias(rs.getInt("EXISTENCIAS"));
                        repuesto.setIdcategoria(rs.getLong("ID_CATEGORIA"));
                        repuesto.setEstado(rs.getBoolean("ESTADO"));
                        return repuesto;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Repuestos> repuestoList = (List<Repuestos>) results.get("DATOS");
        return repuestoList;
    }

    public Repuestos getIdJuegos(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("GET_ONE_JUEGO")
                .declareParameters(new SqlParameter("JID", Types.BIGINT), new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Repuestos>() {
                    @Override
                    public Repuestos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Repuestos repuesto = new Repuestos();
                        repuesto.setId_juego(rs.getLong("ID_JUEGO"));
                        repuesto.setImagen(rs.getString("IMAGEN"));
                        repuesto.setNombre(rs.getString("NOMBRE"));
                        repuesto.setEmpresa(rs.getString("EMPRESA"));
                        repuesto.setPrecio(rs.getInt("PRECIO"));
                        repuesto.setExistencias(rs.getInt("EXISTENCIAS"));
                        repuesto.setIdcategoria(rs.getLong("ID_CATEGORIA"));
                        repuesto.setEstado(rs.getBoolean("ESTADO"));
                        return repuesto;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("JID", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Repuestos> repuestoList = (List<Repuestos>) results.get("DATOS");
        return repuestoList.isEmpty() ? null : repuestoList.get(0);
    }

    public void saveJuegos(Repuestos repuesto) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("ADD_JUEGO")
                .declareParameters(
                        new SqlParameter("IMG", Types.VARCHAR),
                        new SqlParameter("NOM", Types.VARCHAR),
                        new SqlParameter("EMP", Types.VARCHAR),
                        new SqlParameter("PREC", Types.DOUBLE),
                        new SqlParameter("EXI", Types.BIGINT),
                        new SqlParameter("EST", Types.BOOLEAN),
                        new SqlParameter("ID_CAT", Types.BIGINT)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("IMG", repuesto.getImagen());
        mapSqlParameterSource.addValue("NOM", repuesto.getNombre());
        mapSqlParameterSource.addValue("EMP", repuesto.getEmpresa());
        mapSqlParameterSource.addValue("PREC", repuesto.getPrecio());
        mapSqlParameterSource.addValue("EXI", repuesto.getExistencias());
        mapSqlParameterSource.addValue("EST", repuesto.isEstado());
        mapSqlParameterSource.addValue("ID_CAT", repuesto.getIdcategoria());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }

    public void deleteJuegos(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("DELETE_JUEGO")
                .declareParameters(new SqlParameter("JID", Types.BIGINT));
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("JID", id);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }

    public void updateJuegos(Long JID , String IMG ,String NOM, String EMP ,double PREC , int EXI, boolean EST , Long ID_CAT) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("UPDATE_JUEGO")
                .declareParameters(
                        new SqlParameter("JID", Types.BIGINT),
                        new SqlParameter("IMG", Types.VARCHAR),
                        new SqlParameter("NOM", Types.VARCHAR),
                        new SqlParameter("EMP", Types.VARCHAR),
                        new SqlParameter("PREC", Types.DOUBLE),
                        new SqlParameter("EXI", Types.BIGINT),
                        new SqlParameter("EST", Types.BOOLEAN),
                        new SqlParameter("ID_CAT", Types.BIGINT)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("JID", JID);
        mapSqlParameterSource.addValue("IMG", IMG);
        mapSqlParameterSource.addValue("NOM", NOM);
        mapSqlParameterSource.addValue("EMP", EMP);
        mapSqlParameterSource.addValue("PREC", PREC);
        mapSqlParameterSource.addValue("EXI", EXI);
        mapSqlParameterSource.addValue("EST", EST);
        mapSqlParameterSource.addValue("ID_CAT", ID_CAT);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }

    public List<Categorias> getCategorias() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("GET_CATEGORIAS")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Categorias>() {
                    @Override
                    public Categorias mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Categorias categoria = new Categorias();
                        categoria.setIdCategoria(rs.getLong("ID_CATEGORIA")); 
                        categoria.setDescripcion(rs.getString("DESCRIPCION")); 
                        return categoria;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Categorias> cateList = (List<Categorias>) results.get("DATOS");
        return cateList;
    }
    
    public List<Repuestos> getJuegosbycate(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("GET_JUEGOSBYCATEGORIA")
                .declareParameters(new SqlParameter("CID", Types.BIGINT), new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Repuestos>() {
                    @Override
                    public Repuestos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Repuestos repuesto = new Repuestos();
                        repuesto.setId_juego(rs.getLong("ID_JUEGO"));
                        repuesto.setImagen(rs.getString("IMAGEN"));
                        repuesto.setNombre(rs.getString("NOMBRE"));
                        repuesto.setEmpresa(rs.getString("EMPRESA"));
                        repuesto.setPrecio(rs.getDouble("PRECIO"));
                        repuesto.setExistencias(rs.getInt("EXISTENCIAS"));
                        repuesto.setIdcategoria(rs.getLong("ID_CATEGORIA"));
                        repuesto.setEstado(rs.getBoolean("ESTADO"));
                        return repuesto;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("CID", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Repuestos> repuestoList = (List<Repuestos>) results.get("DATOS");
        return repuestoList;
    }
    
    public List<Categorias> getdesc() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("GET_CATEGORIA_DESC")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Categorias>() {
                    @Override
                    public Categorias mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Categorias categoria = new Categorias();
                        categoria.setIdCategoria(rs.getLong("ID_CATEGORIA")); 
                        categoria.setDescripcion(rs.getString("DESCRIPCION")); 
                        return categoria;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Categorias> categoriasList = (List<Categorias>) results.get("DATOS");
        return categoriasList;
    }

}
