package com.mycompany.prueba_tecnica_product.repository;

import com.mycompany.prueba_tecnica_product.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Repository
public class ProductoRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public List<Producto> insertarProducto(Producto productInput) {

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("sp_insertAndListProducts")
                .declareParameters(new SqlParameter[]{
                        new SqlParameter("nombre", Types.VARCHAR),
                        new SqlParameter("fec_registro", Types.DATE),
                        new SqlOutParameter("p_cursor", Types.REF_CURSOR),
                        new SqlOutParameter("codigoRespuesta", Types.INTEGER),
                        new SqlOutParameter("mensajeRespuesta", Types.VARCHAR)
                })
                .returningResultSet("p_cursor", new RowMapper<Producto>() {
                    @Override
                    public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Producto productoOutput = new Producto();
                        productoOutput.setIdProducto(rs.getInt(1));
                        productoOutput.setNombre(rs.getString(2));
                        productoOutput.setFec_registro(formatDate(rs.getDate(3)));
                        return productoOutput;
                    }
                });

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("nombre", productInput.getNombre());
        mapSqlParameterSource.addValue("fec_registro", productInput.getFec_registro());

        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        List<Producto> productList = (List<Producto>) results.get("p_cursor");

        return productList;
    }

    private String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(date);
    }
}
