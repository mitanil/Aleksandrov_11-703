package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
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

@WebServlet("/signUp")
public class SignUp extends HttpServlet {

    ClientServices clientServices;


    @Override
    @SneakyThrows
    public void init() throws ServletException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Class.forName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/poterashka");

        clientServices = new ClientServicesImpl(new ClientRepositoryImpl(dataSource), new BCryptPasswordEncoder());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String rawPassword = req.getParameter("password");
        String name = req.getParameter("name");

        ObjectMapper objectMapper = new ObjectMapper();
        Integer res = 0;
        resp.setContentType("application/json");
        Client client = Client.builder()
                .login(login)
                .name(name)
                .rawPassword(rawPassword)
                .build();
        client = clientServices.createClient(client);
        if(client != null) {
            String uuid = clientServices.createUUID(client.getLogin(), rawPassword);
            Cookie auth = new Cookie("auth", uuid);
            auth.setMaxAge(7 * 24 * 60 * 60);
            resp.addCookie(auth);
            res = 1;
        }
        resp.getWriter().write(objectMapper.writeValueAsString(res));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/signUp.jsp").forward(req,resp);
    }
}
