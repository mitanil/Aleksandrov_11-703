package ru.itis.gett_taxi.repositories;

import ru.itis.gett_taxi.models.Driver;

public interface DriversRepository {
    public void setDriver(Driver driver);
    public Driver getDriver();

    boolean register();

    boolean enter();
}
