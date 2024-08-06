package com.Proyecto.Proyecto.Dao;

import com.Proyecto.Proyecto.Domain.Categorias;
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
public class CategoriaDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Categorias> getListCategorias() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_CATEGORIAS_TB_GET_CATEGORIA_SP")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Categorias>() {
                    @Override
                    public Categorias mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Categorias categoria = new Categorias();
                        categoria.setIdCategoria(rs.getLong("ID_CATEGORIA"));
                        categoria.setDescripcion(rs.getString("DESCRIPCION"));
                        categoria.setRutaImagen(rs.getString("IMAGEN"));
                        categoria.setEstado(rs.getBoolean("ESTADO"));
                        return categoria;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Categorias> categoriaList = (List<Categorias>) results.get("DATOS");
        return categoriaList;
    }

    public Categorias getOneCategoria(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_CATEGORIAS_TB_GET_ONE_CATEGORIA_SP")
                .declareParameters(new SqlParameter("CID", Types.BIGINT),new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Categorias>() {
                    @Override
                    public Categorias mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Categorias categoria = new Categorias();
                        categoria.setIdCategoria(rs.getLong("ID_CATEGORIA"));
                        categoria.setDescripcion(rs.getString("DESCRIPCION"));
                        categoria.setRutaImagen(rs.getString("IMAGEN"));
                        categoria.setEstado(rs.getBoolean("ESTADO"));
                        return categoria;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("CID", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Categorias> categoriaList = (List<Categorias>) results.get("DATOS");
        return categoriaList.isEmpty() ? null : categoriaList.get(0);
    }

    public void saveCategoria(Categorias categoria) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_CATEGORIAS_TB_ADD_CATEGORIA_SP")
                .declareParameters(
                        new SqlParameter("DESCRIP", Types.VARCHAR),
                        new SqlParameter("IMG", Types.VARCHAR),
                        new SqlParameter("ACT", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("DESCRIP", categoria.getDescripcion());
        mapSqlParameterSource.addValue("IMG", categoria.getRutaImagen());
        mapSqlParameterSource.addValue("ACT", categoria.isEstado());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public void deleteCategoria(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_CATEGORIAS_TB_DELETE_CATEGORIA_SP")
                .declareParameters(new SqlParameter("CID", Types.BIGINT));
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("CID", id);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public void updateCategoria(Long CID, String DESCRIP,String IMG,boolean ACT) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_CATEGORIAS_TB_UPDATE_CATEGORIA_SP")
                .declareParameters(
                        new SqlParameter("CID", Types.BIGINT),
                        new SqlParameter("DESCRIP", Types.VARCHAR),
                        new SqlParameter("IMG", Types.VARCHAR),
                        new SqlParameter("ACT", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("CID",CID);
        mapSqlParameterSource.addValue("DESCRIP", DESCRIP);
        mapSqlParameterSource.addValue("IMG", IMG);
        mapSqlParameterSource.addValue("ACT", ACT);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }

}
