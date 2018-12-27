package ru.itis.services;

import ru.itis.models.Order;
import ru.itis.repositories.OrderRepository;

import java.util.List;

public class OrderServicesImpl implements OrderServices{
    OrderRepository orderRepository;
    public OrderServicesImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Order getOrder(Integer item) {
        try{
            return orderRepository.getOrderByItem(item);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.createOrder(order);
    }

    @Override
    public void closeOrder(Integer order) {
        orderRepository.closeOrder(order);
    }

    @Override
    public List<Order> getLastNOrders(Integer n) {
        return orderRepository.getLastNOrders(n);
    }

    @Override
    public void updateOrder(Integer orderId, Integer desLoc, String description) {

    }

    @Override
    public List<Order> getOrdersInBuilding(Integer buildingId) {
        return orderRepository.getOrdersInBuilding(buildingId);
    }

    @Override
    public List<Order> getOrdersInLocation(Integer locationId) {
        return orderRepository.getOrdersInLocation(locationId);
    }

    @Override
    public Order getOrderById(Integer orderId) {
        if(orderId == null) return null;
        return orderRepository.getOrderById(orderId);
    }

    @Override
    public void addCurrentLocation(Integer orderId, Integer locationId) {
        orderRepository.addCurrentLocation(orderId, locationId);
    }

}
