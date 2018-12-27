package ru.itis.repositories;

import ru.itis.models.Building;
import ru.itis.models.Location;

import java.util.List;

public interface LocationRepository {

    Building createBuilding(String name, String address);
    Location createLocation(String name, Integer buildingId, Integer locationParentId);
    List<Building> getAllBuildings();
    List<Location> getAllLocations();
    List<Location> getLocationsInBuilding(Integer buildingId);
    List<Location> getLocationsInLocation(Integer locationId);


    Location getLocation(Integer locationId);

    Building getBuildingByLocation(Integer locationId);

    Building getBuilding(Integer buildingId);
}
