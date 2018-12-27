package ru.itis.gett_taxi.repositories;

import ru.itis.gett_taxi.models.Car;

public interface CarsRepository {
    public void setCar(Car car);
    public Car getCar();

    boolean register();
}
