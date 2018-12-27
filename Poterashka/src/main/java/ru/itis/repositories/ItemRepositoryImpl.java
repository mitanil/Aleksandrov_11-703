package ru.itis.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Category;
import ru.itis.models.Client;
import ru.itis.models.Item;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemRepositoryImpl implements ItemRepository{

    private static final String SQL_FIND_ITEM_BY_NAME = "SELECT * FROM item WHERE item_name LIKE ?;";
    private static final String SQL_GET_ALL_ITEMS_NOT_IN_ORDER = "SELECT item.* FROM item EXCEPT (SELECT item.* FROM item JOIN \"order\" o on item.item_id = o.item_id WHERE o.is_close = FALSE)";
    private JdbcTemplate jdbcTemplate;

    public ItemRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);

    }

//    language=sql

//    language=sql
public static final String SQL_CREATE_ITEM = "INSERT INTO item (item_name, category_id, owner_id, description, image) VALUES (?,?,?,?,?)";
    //    language=sql
    public static final String SQL_GET_ITEM_BY_ID = "SELECT * FROM item WHERE item_id = ?";

    //    language=sql
    public static final String SQL_GET_ITEM_BY_NAME = "SELECT * FROM item WHERE item_name = ?";

    //    language=sql
    public static final String SQL_GET_ITEM_BY_CLIENT_ID = "SELECT * FROM item WHERE owner_id = ?";

//    language=sql
    private static final String SQL_GET_ALL_ITEMS_IN_BUILDING ="SELECT DISTINCT item.* " +
        "FROM item " +
        "JOIN \"order\" o on item.item_id = o.item_id " +
        "JOIN location l on o.place_lost = l.location_id " +
        "WHERE l.building_parent = ?;";


    //    language=sql
    private static final String SQL_GET_ALL_ITEMS_IN_LOCATION =  "WITH RECURSIVE l AS ( " +
            "    SELECT location_id, location_parent " +
            "    FROM location " +
            "    WHERE location_parent ? " +
            "  UNION " +
            "    SELECT location.location_id, location.location_parent " +
            "    FROM location JOIN l ON location.location_parent = l.location_id " +
            ")" +
            "SELECT DISTINCT item.* FROM item JOIN \"order\" o on item.item_id = o.item_id JOIN l on o.place_lost = l.location_id WHERE o.is_close = FALSE;";




    RowMapper<Item> itemRowMapper = ((resultSet, i) -> Item.builder()
            .itemId(resultSet.getInt("item_id"))
            .category(resultSet.getInt("category_id"))
            .itemName(resultSet.getString("item_name"))
            .description(resultSet.getString("description"))
            .owner(resultSet.getInt("owner_id"))
            .image(resultSet.getString("image"))
            .build()
    );

    @Override
    public Item createItem(Item item) {
        try{
            jdbcTemplate.update(SQL_CREATE_ITEM, item.getItemName(), 1, item.getOwner(), item.getDescription(), item.getImage());
            return jdbcTemplate.queryForObject(SQL_GET_ITEM_BY_NAME, itemRowMapper, item.getItemName());
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Item getItem(String name) {
        try{
            return jdbcTemplate.queryForObject(SQL_GET_ITEM_BY_NAME, itemRowMapper, name);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Item getItem(Integer itemId) {
        return jdbcTemplate.queryForObject(SQL_GET_ITEM_BY_ID, itemRowMapper, itemId);
    }

    @Override
    public List<Item> getItems(Integer ownerId) {
        return jdbcTemplate.query(SQL_GET_ITEM_BY_CLIENT_ID, itemRowMapper, ownerId);
    }

    @Override
    public List<Item> getItemsInLocation(Integer locationId) {
        return jdbcTemplate.query(SQL_GET_ALL_ITEMS_IN_LOCATION, itemRowMapper, locationId);
    }

    @Override
    public List<Item> getItemsInBuilding(Integer buildingId) {
        return jdbcTemplate.query(SQL_GET_ALL_ITEMS_IN_BUILDING, itemRowMapper, buildingId);
    }

    @Override
    public List<Item> findItem(String name) {
        return jdbcTemplate.query(SQL_FIND_ITEM_BY_NAME, itemRowMapper, "%" + name + "%");
    }

    @Override
    public List<Item> getAllItemsNotInOrder() {

        return jdbcTemplate.query(SQL_GET_ALL_ITEMS_NOT_IN_ORDER, itemRowMapper);
    }

}
