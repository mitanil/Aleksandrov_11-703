package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.models.Building;
import ru.itis.models.Location;
import ru.itis.repositories.LocationRepository;

import java.util.List;

public class LocationServicesImpl implements LocationServices {
    @Autowired
    LocationRepository locationRepository;

    public LocationServicesImpl() {
    }

    public Building createBuilding(String name, String address) {
        return locationRepository.createBuilding(name, address);
    }

    @Override
    public Location createLocation(String name, Integer building, Integer parentLocation) {
        return locationRepository.createLocation(name, building, parentLocation);
    }

    public List<Building> getBuildings() {
        return locationRepository.getAllBuildings();
    }

    public List<Location> getAllLocations() {
        return locationRepository.getAllLocations();
    }

    @Override
    public List<Location> getLocationsInBuilding(Integer building) {
        return locationRepository.getLocationsInBuilding(building);
    }

    @Override
    public List<Location> getLocationsInLocation(Integer locationId) {
        return locationRepository.getLocationsInLocation(locationId);
    }

    @Override
    public Location getLocation(Integer locationId) {
        if (locationId == null) return null;
        return locationRepository.getLocation(locationId);
    }

    @Override
    public Building getBuildingByLocation(Integer locationId) {
        if (locationId == null) return null;
        return locationRepository.getBuildingByLocation(locationId);
    }

    @Override
    public Building getBuilding(Integer buildingId) {
        return locationRepository.getBuilding(buildingId);
    }

}
