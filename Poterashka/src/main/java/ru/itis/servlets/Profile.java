package ru.itis.servlets;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itis.forms.OrderForm;
import ru.itis.models.*;
import ru.itis.repositories.ClientRepositoryImpl;
import ru.itis.repositories.ItemRepositoryImpl;
import ru.itis.repositories.LocationRepositoryImpl;
import ru.itis.repositories.OrderRepositoryImpl;
import ru.itis.services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@WebServlet("/profile")
public class Profile extends HttpServlet {
//
//    @Autowired
//    ClientServices clientServices;
//    ItemServices itemServices;
//    LocationServices locationServices;
//    @Autowired
//    OrderServices orderServices;
//
//
//    @Override
//    @SneakyThrows
//    public void init() throws ServletException {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        Class.forName("org.postgresql.Driver");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/poterashka");
//
//        itemServices = new ItemServicesImpl(new ItemRepositoryImpl(dataSource));
//        locationServices = new LocationServicesImpl(new LocationRepositoryImpl(dataSource));
//    }
//
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Cookie cookies[] = req.getCookies();
//
//        Client client = null;
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("auth")) {
//                        client = clientServices.getUserByUUID(cookie.getValue());
//                }
//            }
//        }
//        List<Item> items = itemServices.getItemsByClient(client.getClientId());
//        List<OrderForm> orderForms = new ArrayList<>();
//        for (Item item :
//                items) {
//            Order order = orderServices.getOrder(item.getItemId());
//            Building building = null;
//            Location location = null;
//            if(order != null){
//                building = locationServices.getBuildingByLocation(order.getPlaceOfLost());
//                location = locationServices.getLocation(order.getPlaceOfLost());
//            }
//            orderForms.add(OrderForm.builder()
//                    .item(item)
//                    .order(order)
//                    .building(building)
//                    .location(location)
//                    .build());
//        }
//
//        req.setAttribute("user", client);
//        req.setAttribute("items", orderForms);
//        req.getRequestDispatcher("jsp/profile.jsp").forward(req, resp);
//    }
}
