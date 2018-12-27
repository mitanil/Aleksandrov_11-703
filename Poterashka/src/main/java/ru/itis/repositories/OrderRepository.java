package ru.itis.repositories;

import ru.itis.models.Client;
import ru.itis.models.Item;
import ru.itis.models.Location;
import ru.itis.models.Order;

import java.util.List;

public interface OrderRepository {
    Order createOrder(Order order);
    List<Order> getOrders();
    boolean closeOrder(Integer orderId);
    List<Order> findOrder(String itemName);
    Order getOrderByItem(Integer itemId);
    List<Order> getLastNOrders(Integer n);
    List<Order> getOrdersInBuilding(Integer buildingId);
    List<Order> getOrdersInLocation(Integer locationId);

    Order getOrderById(Integer orderId);

    void addCurrentLocation(Integer orderId, Integer locationId);
}
