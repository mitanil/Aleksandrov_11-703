package ru.itis.servlets;

import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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


@WebServlet("/home")
public class Home extends HttpServlet {

    OrderServices orderServices;
    ClientServices clientServices;



    @Override
    @SneakyThrows
    public void init() throws ServletException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Class.forName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/poterashka");
        orderServices = new OrderServicesImpl(new OrderRepositoryImpl(dataSource));
        clientServices = new ClientServicesImpl(new ClientRepositoryImpl(dataSource), new BCryptPasswordEncoder());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("jsp/home.jsp").forward(request, response);
    }

}
