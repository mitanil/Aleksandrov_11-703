package ru.itis.repositories;


import lombok.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.forms.ProductGroup;
import ru.itis.models.Basket;
import ru.itis.models.Product;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.util.List;


public class BasketRepositoryJdbcImpl implements BasketRepository{


    JdbcTemplate jdbcTemplate;

    //    language=sql
    private static final String SQL_CREATE_BASKET = "INSERT INTO basket (user_id) VALUES (?)";

    //    language=sql
    private static final String SQL_GET_BASKET_BY_USER_ID = "SELECT * FROM basket WHERE user_id = ?";

    //    language=sql
    private static final String SQL_GET_PRODUCTS_FROM_BASKET = "SELECT * FROM products join basket_pruducts bp on products.id = bp.product_id WHERE bp.basket_id = ?;";

    //    language=sql
    private static final String SQL_ADD_PRODUCT = "INSERT INTO basket_pruducts (basket_id, product_id) VALUES (?,?);";


//    language=sql
    private static final String SQL_GET_GROUP_PRODUCTS =
        "SELECT products.id, products.name, COUNT(basket_id) " +
        "FROM products " +
        "       join basket_pruducts bp on products.id = bp.product_id " +
        "WHERE bp.basket_id = ? " +
        "GROUP BY products.id " +
        "ORDER BY products.id;";

    private RowMapper<Basket> basketRowMapper = ((resultSet, i) -> Basket.builder()
            .id(resultSet.getLong("basket_id"))
            .build());

    public BasketRepositoryJdbcImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    RowMapper<Product> productRowMapper = ((resultSet, i) -> Product.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .build());

    @Override
    public Basket createBasket(User user) {
        try {
            jdbcTemplate.update(SQL_CREATE_BASKET, user.getUserId());
            return getBasketByUser(user);
        }catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public List<Product> getProductsInBasket(Basket basket) {

        try {
            return jdbcTemplate.query(SQL_GET_PRODUCTS_FROM_BASKET, productRowMapper, basket.getId());
        }catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public Basket getBasketByUser(User user) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_BASKET_BY_USER_ID, basketRowMapper, user.getUserId());
        }catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public Basket getBasketById(String basketId) {
        return null;
    }

    @Override
    public boolean addProduct(Long productId, Long basketId) {
        try {
            jdbcTemplate.update(SQL_ADD_PRODUCT, basketId, productId);
            return true;
        }catch (DataAccessException e){
            return false;
        }
    }

    RowMapper<ProductGroup> productGroupRowMapper = ((resultSet, i) -> ProductGroup.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .count(resultSet.getInt("count"))
            .build());

    @Override
    public List<ProductGroup> getGroupsProducts(Long basketId) {
        try {
            return jdbcTemplate.query(SQL_GET_GROUP_PRODUCTS, productGroupRowMapper, basketId);
        }catch (DataAccessException e){
            return null;
        }
    }
}
