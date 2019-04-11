package ru.itis.services;

import ru.itis.models.*;

import java.util.List;


public interface OrderServices {
    Order getOrder(Integer item);
    Order createOrder(Order order);
    void closeOrder(Integer order);
    List<Order> getLastNOrders(Integer n);
    void updateOrder(Integer orderId, Integer desLoc, String description);
    List<Order> getOrdersInBuilding(Integer buildingId);
    List<Order> getOrdersInLocation(Integer locationId);

    Order getOrderById(Integer orderId);

    void addCurrentLocation(Integer orderId, Integer locationId);

}
