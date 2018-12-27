package ru.itis.services;

import ru.itis.models.Building;
import ru.itis.models.Location;

import java.util.List;

public interface LocationServices {
    Building createBuilding(String name, String address);
    Location createLocation(String name, Integer building, Integer parentLocation);
    List<Building> getBuildings();
    List<Location> getAllLocations();
    List<Location> getLocationsInBuilding(Integer building);
    List<Location> getLocationsInLocation(Integer location);

    Location getLocation(Integer locationId);

    Building getBuildingByLocation(Integer locationId);

    Building getBuilding(Integer buildingId);
}
