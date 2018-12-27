package ru.itis.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Order;

import javax.sql.DataSource;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository{

//    language=sql
    private static final String SQL_GET_ALL_ORDERS = "SELECT * FROM \"order\" WHERE is_close = FALSE;";
    private static final String SQL_GET_ORDER = "SELECT * FROM \"order\" WHERE is_close = FALSE AND item_id = ?";
    private static final String SQL_CREATE_ORDER = "INSERT INTO \"order\" (item_id, user_find, place_lost, destination, description) VALUES (?,?,?,?,?);";
    //    language=sql
    private static final String SQL_FIND_ORDER_BY_ITEM_NAME = "SELECT order.* FROM \"order\" AS o JOIN item i ON i.item_id = o.item_id WHERE item_name LIKE ?;";
    private static final String SQL_GET_LAST_TEN_ORDERS = "SELECT * FROM \"order\" WHERE is_close = FALSE ORDER BY order_id DESC LIMIT ?;";
    private static final String SQL_CLOSE_ORDER = "UPDATE \"order\" SET is_close=TRUE WHERE order_id=?";
    private static final String SQL_GET_ORDERS_IN_LOCATION = "\n" +
            "WITH RECURSIVE l AS (\n" +
            "    SELECT location_id, location_parent\n" +
            "    FROM location\n" +
            "    WHERE location_id  = ?\n" +
            "  UNION\n" +
            "    SELECT location.location_id, location.location_parent\n" +
            "    FROM location JOIN l ON location.location_parent = l.location_id\n" +
            ")\n" +
            "SELECT  o.* FROM \"order\" AS o JOIN l on l.location_id = o.place_lost;";
    private static final String SQL_GET_ORDERS_IN_BUILDING = "SELECT o.* FROM \"order\" AS o JOIN (SELECT location_id FROM location WHERE building_parent = ?) l on o.place_lost = location_id;";
    private static final String SQL_GET_ORDER_BY_ID = "SELECT * FROM \"order\" WHERE order_id = ?";
    private static final String SQL_ADD_CURRENT_LOCATION = "UPDATE \"order\" SET destination = ? WHERE order_id=?";

    private JdbcTemplate jdbcTemplate;

    public OrderRepositoryImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    RowMapper<Order> orderRowMapper = ((resultSet, i) -> Order.builder()
            .orderId(resultSet.getInt("order_id"))
            .itemId(resultSet.getInt("item_id"))
            .finder(resultSet.getInt("user_find"))
            .placeOfLost(resultSet.getInt("place_lost"))
            .destination(resultSet.getInt("destination"))
            .description(resultSet.getString("description"))
            .close(resultSet.getBoolean("is_close"))
            .build());


    @Override
    public Order createOrder(Order order) {
            try{
                jdbcTemplate.queryForObject(SQL_GET_ORDER, orderRowMapper, order.getItemId());
                return null;
            }catch (DataAccessException e){
                jdbcTemplate.update(SQL_CREATE_ORDER, order.getItemId(), order.getFinder(), order.getPlaceOfLost(), order.getDestination(), order.getDescription());
                return jdbcTemplate.queryForObject(SQL_GET_ORDER, orderRowMapper, order.getItemId());
            }
    }

    @Override
    public List<Order> getOrders() {
        return jdbcTemplate.query(SQL_GET_ALL_ORDERS, orderRowMapper);
    }

    @Override
    public boolean closeOrder(Integer orderId) {
        try{
            jdbcTemplate.update(SQL_CLOSE_ORDER, orderId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Order> findOrder(String itemName) {
        return jdbcTemplate.query(SQL_FIND_ORDER_BY_ITEM_NAME, orderRowMapper, "%" + itemName + "%");
    }

    @Override
    public Order getOrderByItem(Integer itemId) {
        return jdbcTemplate.queryForObject(SQL_GET_ORDER, orderRowMapper, itemId);
    }

    @Override
    public List<Order> getLastNOrders(Integer n) {
        return jdbcTemplate.query(SQL_GET_LAST_TEN_ORDERS, orderRowMapper, n);
    }

    @Override
    public List<Order> getOrdersInBuilding(Integer buildingId) {
        return jdbcTemplate.query(SQL_GET_ORDERS_IN_BUILDING, orderRowMapper, buildingId);
    }

    @Override
    public List<Order> getOrdersInLocation(Integer locationId) {
        return jdbcTemplate.query(SQL_GET_ORDERS_IN_LOCATION, orderRowMapper, locationId);
    }

    @Override
    public Order getOrderById(Integer orderId) {
        try{
            return jdbcTemplate.queryForObject(SQL_GET_ORDER_BY_ID, orderRowMapper, orderId);
        }catch (Exception e){
            return  null;
        }
    }

    @Override
    public void addCurrentLocation(Integer orderId, Integer locationId) {
        jdbcTemplate.update(SQL_ADD_CURRENT_LOCATION, locationId, orderId);
    }
}
