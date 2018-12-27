package ru.itis.repositories;


import lombok.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Product;

import javax.sql.DataSource;
import java.util.List;


public class ProductRepositoryJdbcImpl implements ProductRepository {


    JdbcTemplate jdbcTemplate;

//    language=SQL
    private static final String SQL_GET_ALL_PRODUCTS = "SELECT * FROM products ORDER BY products.id";

    RowMapper<Product> productRowMapper = ((resultSet, i) -> Product.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .build());

    public ProductRepositoryJdbcImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Product> getProductsList() {
        return jdbcTemplate.query(SQL_GET_ALL_PRODUCTS, productRowMapper);
    }
}
