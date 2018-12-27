package ru.itis.gett_taxi.repositories;

import ru.itis.gett_taxi.models.Driver;
import ru.itis.gett_taxi.models.User;
import ru.itis.gett_taxi.services.ConnectDB;

public class DriversRepositoryImpl implements DriversRepository{
    ConnectDB connectDB;

    private Driver driver;

    public DriversRepositoryImpl(ConnectDB c){
        connectDB = c;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Driver getDriver() {
        return driver;
    }
}
