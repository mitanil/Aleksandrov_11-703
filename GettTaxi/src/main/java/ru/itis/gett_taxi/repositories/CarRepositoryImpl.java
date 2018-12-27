package ru.itis.gett_taxi.repositories;

import ru.itis.gett_taxi.models.Car;
import ru.itis.gett_taxi.services.ConnectDB;

public class CarRepositoryImpl implements CarsRepository {

    ConnectDB connectDB;

    Car car;

    public CarRepositoryImpl(ConnectDB c){
        connectDB = c;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }
}
