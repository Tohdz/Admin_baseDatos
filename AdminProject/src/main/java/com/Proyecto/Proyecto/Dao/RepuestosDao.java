package com.Proyecto.Proyecto.Dao;

import com.Proyecto.Proyecto.Domain.Categorias;
import com.Proyecto.Proyecto.Domain.Marcas;
import com.Proyecto.Proyecto.Domain.Repuestos;
import com.Proyecto.Proyecto.Domain.Sedes;
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

    public List<Repuestos> getRepuestos() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_REPUESTOS_TB_GET_REPUESTOS_SP")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Repuestos>() {
                    @Override
                    public Repuestos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Repuestos repuesto = new Repuestos();
                        repuesto.setIdRepuesto(rs.getLong("ID_REPUESTO"));
                        repuesto.setImagen(rs.getString("IMAGEN"));
                        repuesto.setNombre(rs.getString("NOMBRE"));
                        repuesto.setIdMarca(rs.getLong("ID_MARCA"));
                        repuesto.setPrecio(rs.getDouble("PRECIO"));
                        repuesto.setCantidad(rs.getInt("CANTIDAD"));
                        repuesto.setIdCategoria(rs.getLong("ID_CATEGORIA"));
                        repuesto.setIdSede(rs.getLong("ID_SEDE"));
                        repuesto.setEstado(rs.getBoolean("ESTADO"));
                        return repuesto;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Repuestos> repuestoList = (List<Repuestos>) results.get("DATOS");
        return repuestoList;
    }

    public Repuestos getRepuesto(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_REPUESTOS_TB_GET_REPUESTO_SP")
                .declareParameters(new SqlParameter("RID", Types.BIGINT), new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Repuestos>() {
                    @Override
                    public Repuestos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Repuestos repuesto = new Repuestos();
                        repuesto.setIdRepuesto(rs.getLong("ID_REPUESTO"));
                        repuesto.setImagen(rs.getString("IMAGEN"));
                        repuesto.setNombre(rs.getString("NOMBRE"));
                        repuesto.setIdMarca(rs.getLong("ID_MARCA"));
                        repuesto.setPrecio(rs.getDouble("PRECIO"));
                        repuesto.setCantidad(rs.getInt("CANTIDAD"));
                        repuesto.setIdCategoria(rs.getLong("ID_CATEGORIA"));
                        repuesto.setIdSede(rs.getLong("ID_SEDE"));
                        repuesto.setEstado(rs.getBoolean("ESTADO"));
                        return repuesto;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("RID", id);
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Repuestos> repuestoList = (List<Repuestos>) results.get("DATOS");
        return repuestoList.isEmpty() ? null : repuestoList.get(0);
    }

    public void saveRepuesto(Repuestos repuesto) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_REPUESTOS_TB_ADD_REPUESTO_SP")
                .declareParameters(
                        new SqlParameter("IMG", Types.VARCHAR),
                        new SqlParameter("NOM", Types.VARCHAR),
                        new SqlParameter("IDM", Types.BIGINT),
                        new SqlParameter("PREC", Types.DOUBLE),
                        new SqlParameter("CANT", Types.BIGINT),
                        new SqlParameter("CID", Types.BIGINT),
                        new SqlParameter("IDSED", Types.BIGINT),
                        new SqlParameter("EST", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("IMG", repuesto.getImagen());
        mapSqlParameterSource.addValue("NOM", repuesto.getNombre());
        mapSqlParameterSource.addValue("IDM", repuesto.getIdMarca());
        mapSqlParameterSource.addValue("PREC", repuesto.getPrecio());
        mapSqlParameterSource.addValue("CANT", repuesto.getCantidad());
        mapSqlParameterSource.addValue("CID", repuesto.getIdCategoria());
        mapSqlParameterSource.addValue("IDSED", repuesto.getIdSede());
        mapSqlParameterSource.addValue("EST", repuesto.isEstado());
        simpleJdbcCall.execute(mapSqlParameterSource);
    }

    public void deleteRepuesto(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_REPUESTOS_TB_DELETE_REPUESTO_SP")
                .declareParameters(new SqlParameter("RID", Types.BIGINT));
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("RID", id);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }

    public void updateRepuesto(Long RID , String IMG ,String NOM, Long MID ,double PREC , int CANT,Long CID,Long IDSED, boolean EST) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_REPUESTOS_TB_UPDATE_REPUESTO_SP")
                .declareParameters(
                        new SqlParameter("RID", Types.BIGINT),
                        new SqlParameter("IMG", Types.VARCHAR),
                        new SqlParameter("NOM", Types.VARCHAR),
                        new SqlParameter("MID", Types.BIGINT),
                        new SqlParameter("PREC", Types.DOUBLE),
                        new SqlParameter("CANT", Types.BIGINT),
                        new SqlParameter("CID", Types.BIGINT),
                        new SqlParameter("IDSED", Types.BIGINT),
                        new SqlParameter("EST", Types.BOOLEAN)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("RID", RID);
        mapSqlParameterSource.addValue("IMG", IMG);
        mapSqlParameterSource.addValue("NOM", NOM);
        mapSqlParameterSource.addValue("MID", MID);
        mapSqlParameterSource.addValue("PREC", PREC);
        mapSqlParameterSource.addValue("CANT", CANT);
        mapSqlParameterSource.addValue("CID", CID);
        mapSqlParameterSource.addValue("IDSED", IDSED);
        mapSqlParameterSource.addValue("EST", EST);
        simpleJdbcCall.execute(mapSqlParameterSource);
    }
    
    public List<Marcas> getMarcasbyState() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_MARCAS_TB_GET_MARCASBYSTATE_SP")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Marcas>() {
                    @Override
                    public Marcas mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Marcas marca = new Marcas();
                        marca.setIdMarca(rs.getLong("ID_MARCA"));
                        marca.setNombre(rs.getString("NOMBRE"));
                        marca.setEstado(rs.getBoolean("ESTADO"));
                        return marca;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Marcas> marcaList = (List<Marcas>) results.get("DATOS");
        return marcaList;
    }
    
    public List<Marcas> getMarcas() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_MARCAS_TB_GET_MARCAS_SP")
                .declareParameters(new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Marcas>() {
                    @Override
                    public Marcas mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Marcas marca = new Marcas();
                        marca.setIdMarca(rs.getLong("ID_MARCA"));
                        marca.setNombre(rs.getString("NOMBRE"));
                        marca.setEstado(rs.getBoolean("ESTADO"));
                        return marca;
                    }
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Marcas> marcaList = (List<Marcas>) results.get("DATOS");
        return marcaList;
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
    
    public List<Categorias> getCategorias() {
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
    
    public List<Categorias> getCategoriasbyState() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_CATEGORIAS_TB_GET_CATEGORIABYSTATE_SP")
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


    public List<Repuestos> getRepuestosbycate(Long id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("ADMIN_FIDE_TALLER_USER")
                .withProcedureName("FIDE_REPUESTOS_TB_GET_REPUESTOSBYCATEGORIA_SP")
                .declareParameters(new SqlParameter("CID", Types.BIGINT), new SqlParameter("DATOS", Types.REF_CURSOR))
                .returningResultSet("DATOS", new RowMapper<Repuestos>() {
                    @Override
                    public Repuestos mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Repuestos repuesto = new Repuestos();
                        repuesto.setIdRepuesto(rs.getLong("ID_REPUESTO"));
                        repuesto.setImagen(rs.getString("IMAGEN"));
                        repuesto.setNombre(rs.getString("NOMBRE"));
                        repuesto.setIdMarca(rs.getLong("ID_MARCA"));
                        repuesto.setPrecio(rs.getDouble("PRECIO"));
                        repuesto.setCantidad(rs.getInt("CANTIDAD"));
                        repuesto.setIdCategoria(rs.getLong("ID_CATEGORIA"));
                        repuesto.setIdSede(rs.getLong("ID_SEDE"));
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

}
