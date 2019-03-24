package ru.itis.servlets;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itis.models.Item;
import ru.itis.repositories.ClientRepositoryImpl;
import ru.itis.repositories.ItemRepositoryImpl;
import ru.itis.services.ClientServices;
import ru.itis.services.ClientServicesImpl;
import ru.itis.services.ItemServices;
import ru.itis.services.ItemServicesImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/createItemAjax")
public class CreateItemAjax extends HttpServlet {
//    ItemServices itemServices;
//    @Autowired
//    ClientServices clientServices;
//
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
//        itemServices = new ItemServicesImpl(new ItemRepositoryImpl(dataSource));
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String image = req.getParameter("image");
//        String itemName = req.getParameter("itemName");
//        String descr = req.getParameter("descr");
//        String isFounder = req.getParameter("founder");
//        Integer clientId = null;
//        if (isFounder.equals("false")) {
//            Cookie cookies[] = req.getCookies();
//            if (cookies != null) {
//                for (Cookie cookie : cookies) {
//                    if (cookie.getName().equals("auth")) {
//                        clientId = clientServices.getUserByUUID(cookie.getValue()).getClientId();
//                    }
//                }
//            }
//        }
//        Item item = Item.builder()
//                .itemName(itemName)
//                .description(descr)
//                .owner(clientId)
//                .image(image)
//                .build();
//        itemServices.createItem(item);
//        resp.getWriter().write("1");
//    }
}
