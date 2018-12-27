package ru.itis.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Building;
import ru.itis.models.Location;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LocationRepositoryImpl implements LocationRepository{

//  language=sql
    private static final String SQL_GET_LOCATION_BY_NAME = "SELECT * FROM location WHERE location_name = ?";
    private static final String SQL_GET_ALL_BUILDINGS = "SELECT * FROM building";
    private static final String SQL_GET_ALL_LOCATIONS = "SELECT * FROM location";
    private static final String SQL_GET_LOCATIONS_IN_BUILDING = "SELECT *FROM location WHERE building_parent = ? AND location_parent IS NULL";
    private static final String SQL_GET_BUILDING_BY_NAME = "SELECT * FROM building WHERE building_name = ?";
    private static final String SQL_GET_LOCATIONS_IN_LOCATION = "WITH RECURSIVE l AS ( " +
            "    SELECT location_id, location_parent " +
            "    FROM location " +
            "    WHERE location_id = ? " +
            "  UNION " +
            "    SELECT location.location_id, location.location_parent " +
            "    FROM location JOIN l ON location.location_parent = l.location_id " +
            ")" +
            "SELECT  location.* FROM location JOIN l on l.location_id = location.location_id";
    private static final String SQL_GET_LOCATION_BY_ID = "SELECT * FROM location WHERE location_id = ?";
    private static final String SQL_GET_BUILDING_BY_ID = "SELECT * FROM building WHERE building_id = ?";
    private static final String SQL_GET_BUILDING_BY_LOCATION_ID = "SELECT building.* FROM building JOIN location l on building.building_id = l.building_parent WHERE location_id = ?";
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Location> locationRowMapper = ((resultSet, i) -> Location.builder()
            .locationId(resultSet.getInt("location_id"))
            .building(resultSet.getInt("building_parent"))
            .locationName(resultSet.getString("location_name"))
            .parentLocation(resultSet.getInt("location_parent"))
            .build());

    private RowMapper<Building> buildingRowMapper = ((resultSet, i) -> Building.builder()
            .address(resultSet.getString("address"))
            .buildingName(resultSet.getString("building_name"))
            .id(resultSet.getInt("building_id"))
            .build());


    public LocationRepositoryImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //    language=sql
    private static final String SQL_CREATE_BUILDING = "INSERT INTO building (building_name, address) VALUES (?,?);";

    //    language=sql
    private static final String SQL_CREATE_LOCATION = "INSERT INTO location (location_name, building_parent, location_parent) VALUES (?,?,?);";



    public Building createBuilding(String name, String address) {
        try{
            jdbcTemplate.update(SQL_CREATE_BUILDING, name, address);
            return jdbcTemplate.queryForObject(SQL_GET_BUILDING_BY_NAME, buildingRowMapper, name);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Location createLocation(String name, Integer buildingId, Integer locationParentId) {
        try{
            jdbcTemplate.update(SQL_CREATE_LOCATION, name, buildingId, locationParentId);
            return jdbcTemplate.queryForObject(SQL_GET_LOCATION_BY_NAME, locationRowMapper, name);
        }catch (Exception e){
            return null;
        }
    }

    public List<Building> getAllBuildings() {
        try{
            return jdbcTemplate.query(SQL_GET_ALL_BUILDINGS, buildingRowMapper);
        }catch (Exception e){
            return null;
        }
    }

    public List<Location> getAllLocations() {
        try{
            return jdbcTemplate.query(SQL_GET_ALL_LOCATIONS, locationRowMapper);
        }catch (Exception e){
            return null;
        }
    }

    public List<Location> getLocationsInBuilding(Integer buildingId) {
        try{
            return jdbcTemplate.query(SQL_GET_LOCATIONS_IN_BUILDING, locationRowMapper, buildingId);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Location> getLocationsInLocation(Integer locationId) {
        return jdbcTemplate.query(SQL_GET_LOCATIONS_IN_LOCATION, locationRowMapper, locationId);
    }

    @Override
    public Location getLocation(Integer locationId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_LOCATION_BY_ID, locationRowMapper, locationId);
        }catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public Building getBuildingByLocation(Integer locationId) {
        return jdbcTemplate.queryForObject(SQL_GET_BUILDING_BY_LOCATION_ID, buildingRowMapper, locationId);
    }

    @Override
    public Building getBuilding(Integer buildingId) {
        return jdbcTemplate.queryForObject(SQL_GET_BUILDING_BY_ID, buildingRowMapper, buildingId);
    }
}
