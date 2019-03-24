package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.forms.BuildForm;
import ru.itis.forms.OrderForm;
import ru.itis.models.Building;
import ru.itis.models.Order;
import ru.itis.repositories.ItemRepositoryImpl;
import ru.itis.repositories.LocationRepositoryImpl;
import ru.itis.repositories.OrderRepositoryImpl;
import ru.itis.services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@WebServlet("/HomeItemsAjax")
public class HomeItemsAjax extends HttpServlet {
//    @Autowired
//    OrderServices orderServices;
//    LocationServices locationServices;
//    ItemServices itemServices;
//
//    @Override
//    @SneakyThrows
//    public void init() throws ServletException {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        Class.forName("org.postgresql.Driver");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/poterashka");
//        itemServices = new ItemServicesImpl(new ItemRepositoryImpl(dataSource));
//        locationServices = new LocationServicesImpl(new LocationRepositoryImpl(dataSource));
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String buildId = req.getParameter("bild");
//        String locId = req.getParameter("loc");
//        ObjectMapper objectMapper = new ObjectMapper();
//        resp.setCharacterEncoding("UTF-8");
//        resp.setContentType("application/json");
//        List<OrderForm> orderForms = new ArrayList<>();
//        List<Order> orders;
//        if(locId != null){
//            orders = orderServices.getOrdersInLocation(Integer.parseInt(locId));
//        }else {
//            orders = orderServices.getOrdersInBuilding(Integer.parseInt(buildId));
//        }
//        for (Order order :
//                orders) {
//            orderForms.add(OrderForm.builder()
//                    .order(order)
//                    .building(locationServices.getBuilding(locationServices.getBuildingByLocation(order.getPlaceOfLost()).getId()))
//                    .location(locationServices.getLocation(order.getPlaceOfLost()))
//                    .item(itemServices.getItemById(order.getItemId()))
//                    .build());
//        }
//        resp.getWriter().write(objectMapper.writeValueAsString(orderForms));
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        List<OrderForm> orderForms = new ArrayList<>();
//        List<Order> orders = orderServices.getLastNOrders(12);
//        for (Order order :
//                orders) {
//            orderForms.add(OrderForm.builder()
//                    .order(order)
//                    .building(locationServices.getBuilding(locationServices.getBuildingByLocation(order.getPlaceOfLost()).getId()))
//                    .location(locationServices.getLocation(order.getPlaceOfLost()))
//                    .item(itemServices.getItemById(order.getItemId()))
//                    .build());
//        }
//        ObjectMapper objectMapper = new ObjectMapper();
//        resp.setCharacterEncoding("UTF-8");
//        resp.setContentType("application/json");
//        resp.getWriter().write(objectMapper.writeValueAsString(orderForms));
//    }
}
