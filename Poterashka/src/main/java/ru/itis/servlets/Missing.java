package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itis.models.Order;
import ru.itis.repositories.ClientRepositoryImpl;
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

//@WebServlet("/missing")
public class Missing extends HttpServlet {
//
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
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Integer itemId = Integer.parseInt(req.getParameter("itemId"));
//        Integer locId = Integer.parseInt(req.getParameter("loc"));
//        String descr = req.getParameter("descr");
//
//        Order order = Order.builder()
//                .itemId(itemId)
//                .placeOfLost(locId)
//                .description(descr)
//                .build();
//        order = orderServices.createOrder(order);
//        resp.setCharacterEncoding("UTF-8");
//        resp.setContentType("application/json");
//        resp.getWriter().write((new ObjectMapper()).writeValueAsString(order));
//
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("jsp/missing.jsp").forward(req, resp);
//    }
}
