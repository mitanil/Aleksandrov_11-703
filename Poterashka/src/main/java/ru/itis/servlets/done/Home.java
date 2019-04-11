package ru.itis.servlets.done;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.repositories.ClientRepositoryImpl;
import ru.itis.repositories.OrderRepositoryImpl;
import ru.itis.services.ClientServices;
import ru.itis.services.ClientServicesImpl;
import ru.itis.services.OrderServices;
import ru.itis.services.OrderServicesImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//@WebServlet("/home")
public class Home extends HttpServlet {
//
//    @Autowired
//    OrderServices orderServices;
//    @Autowired
//    ClientServices clientServices;
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        request.getRequestDispatcher("jsp/home.jsp").forward(request, response);
//    }

}
