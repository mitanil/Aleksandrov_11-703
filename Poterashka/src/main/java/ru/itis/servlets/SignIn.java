package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itis.models.Client;
import ru.itis.repositories.ClientRepositoryImpl;
import ru.itis.services.ClientServices;
import ru.itis.services.ClientServicesImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/signIn")
public class SignIn extends HttpServlet {
//
//    @Autowired
//    ClientServices clientServices;
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String login = req.getParameter("login");
//        String rawPassword = req.getParameter("password");
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        Integer res = 0;
//        resp.setContentType("application/json");
//        Client client = Client.builder()
//                .login(login)
//                .rawPassword(rawPassword)
//                .build();
//        String uuid = clientServices.createUUID(client.getLogin(), client.getRawPassword());
//        if(uuid != null){
//            Cookie auth = new Cookie("auth", uuid);
//            auth.setMaxAge(7 * 24 * 60 * 60);
//            resp.addCookie(auth);
//            res = 1;
//        }
//        resp.getWriter().write(objectMapper.writeValueAsString(res));
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("jsp/signIn.jsp").forward(req,resp);
//    }
}
