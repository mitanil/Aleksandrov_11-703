package ru.itis.gett_taxi.repositories;

import ru.itis.gett_taxi.models.Order;
import ru.itis.gett_taxi.services.ConnectDB;

public class OrderRepositoryImpl implements OrderRepository {
    ConnectDB connectDB;

    private Order order;

    public OrderRepositoryImpl(ConnectDB c) {
        connectDB = c;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
