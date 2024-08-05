package com.Proyecto.Proyecto.Dao;

import com.Proyecto.Proyecto.Domain.Categorias;
import com.Proyecto.Proyecto.Domain.Juegos;
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
public class JuegosDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Juegos> getListJuegos() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("GET_JUEGOS")
                .withCatalogName("PACKAGE_JUEGO")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Juegos>() {
                    @Override
                    public Juegos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Juegos juego = new Juegos();
                        juego.setId_juego(rs.getLong("ID_JUEGO"));
                        juego.setImagen(rs.getString("IMAGEN"));
                        juego.setNombre(rs.getString("NOMBRE"));
                        juego.setEmpresa(rs.getString("EMPRESA"));
                        juego.setPrecio(rs.getDouble("PRECIO"));
                        juego.setExistencias(rs.getInt("EXISTENCIAS"));
                        juego.setIdcategoria(rs.getLong("ID_CATEGORIA"));
                        juego.setEstado(rs.getBoolean("ESTADO"));
                        return juego;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Juegos> juegoList = (List<Juegos>) results.get("DATOS");
        return juegoList;
    }

    public Juegos getIdJuegos(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("GET_ONE_JUEGO")
                .withCatalogName("PACKAGE_JUEGO")
                .declareParameters(new SqlParameter("JID", Types.BIGINT), new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Juegos>() {
                    @Override
                    public Juegos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Juegos juego = new Juegos();
                        juego.setId_juego(rs.getLong("ID_JUEGO"));
                        juego.setImagen(rs.getString("IMAGEN"));
                        juego.setNombre(rs.getString("NOMBRE"));
                        juego.setEmpresa(rs.getString("EMPRESA"));
                        juego.setPrecio(rs.getInt("PRECIO"));
                        juego.setExistencias(rs.getInt("EXISTENCIAS"));
                        juego.setIdcategoria(rs.getLong("ID_CATEGORIA"));
                        juego.setEstado(rs.getBoolean("ESTADO"));
                        return juego;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("JID", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Juegos> juegoList = (List<Juegos>) results.get("DATOS");
        return juegoList.isEmpty() ? null : juegoList.get(0);
    }

    public void saveJuegos(Juegos juego) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("ADD_JUEGO")
                .withCatalogName("PACKAGE_JUEGO")
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
        mapSqlParameterSource.addValue("IMG", juego.getImagen());
        mapSqlParameterSource.addValue("NOM", juego.getNombre());
        mapSqlParameterSource.addValue("EMP", juego.getEmpresa());
        mapSqlParameterSource.addValue("PREC", juego.getPrecio());
        mapSqlParameterSource.addValue("EXI", juego.getExistencias());
        mapSqlParameterSource.addValue("EST", juego.isEstado());
        mapSqlParameterSource.addValue("ID_CAT", juego.getIdcategoria());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }

    public void deleteJuegos(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("DELETE_JUEGO")
                .withCatalogName("PACKAGE_JUEGO")
                .declareParameters(new SqlParameter("JID", Types.BIGINT));
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("JID", id);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }

    public void updateJuegos(Long JID , String IMG ,String NOM, String EMP ,double PREC , int EXI, boolean EST , Long ID_CAT) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("UPDATE_JUEGO")
                .withCatalogName("PACKAGE_JUEGO")
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
                .withCatalogName("PACKAGE_JUEGO")
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
    
    public List<Juegos> getJuegosbycate(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("GET_JUEGOSBYCATEGORIA")
                .withCatalogName("PACKAGE_JUEGO")
                .declareParameters(new SqlParameter("CID", Types.BIGINT), new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Juegos>() {
                    @Override
                    public Juegos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Juegos juego = new Juegos();
                        juego.setId_juego(rs.getLong("ID_JUEGO"));
                        juego.setImagen(rs.getString("IMAGEN"));
                        juego.setNombre(rs.getString("NOMBRE"));
                        juego.setEmpresa(rs.getString("EMPRESA"));
                        juego.setPrecio(rs.getDouble("PRECIO"));
                        juego.setExistencias(rs.getInt("EXISTENCIAS"));
                        juego.setIdcategoria(rs.getLong("ID_CATEGORIA"));
                        juego.setEstado(rs.getBoolean("ESTADO"));
                        return juego;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("CID", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Juegos> juegoList = (List<Juegos>) results.get("DATOS");
        return juegoList;
    }
    
    public List<Categorias> getdesc() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("admin_lenguajes")
                .withProcedureName("GET_CATEGORIA_DESC")
                .withCatalogName("PACKAGE_JUEGO")
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
