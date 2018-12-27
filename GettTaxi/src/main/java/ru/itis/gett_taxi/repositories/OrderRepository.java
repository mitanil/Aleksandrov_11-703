package ru.itis.gett_taxi.repositories;

import ru.itis.gett_taxi.models.Driver;
import ru.itis.gett_taxi.models.Order;

public interface OrderRepository {
    public void setOrder(Order order);
    public Order getOrder();
}
