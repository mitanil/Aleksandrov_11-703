package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itis.models.Client;
import ru.itis.models.Item;
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

//@WebServlet("/missingItemsAjax")
public class MissingItemsAjax extends HttpServlet {
//    @Autowired
//    ClientServices clientServices;
//    ItemServices itemServices;
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
//    }
//
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String diap = req.getParameter("diap");
//        String itemIdS = req.getParameter("itemId");
//        List<Item> items = new ArrayList<>();
//        if(diap.equals("a"))
//            items = itemServices.getAllItemsNotInOrder();
//        else if(diap.equals("u")){
//            Cookie cookies[] = req.getCookies();
//
//            Client client = null;
//            if (cookies != null) {
//                for (Cookie cookie : cookies) {
//                    if (cookie.getName().equals("auth")) {
//                        client = clientServices.getUserByUUID(cookie.getValue());
//                    }
//                }
//            }
//            items = itemServices.getItemsByClient(client.getClientId());
//        }else if(diap.equals("one")){
//            Integer itemId = Integer.parseInt(itemIdS);
//            items.add(itemServices.getItemById(itemId));
//        }
//
//        resp.setCharacterEncoding("UTF-8");
//        resp.setContentType("application/json");
//        resp.getWriter().write((new ObjectMapper()).writeValueAsString(items));
//    }
}
