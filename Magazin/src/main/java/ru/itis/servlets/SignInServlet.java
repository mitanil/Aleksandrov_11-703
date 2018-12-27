package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.models.Auth;
import ru.itis.models.Product;
import ru.itis.models.User;
import ru.itis.repositories.AuthRepository;
import ru.itis.repositories.AuthRepositoryJdbcImpl;
import ru.itis.repositories.UserRepository;
import ru.itis.repositories.UserRepositoryJdbcImpl;
import ru.itis.services.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {


    UserService userService;
    AuthService authService;

    @Override
    @SneakyThrows
    public void init() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Class.forName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/shopbasket");

        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);
        AuthRepository authRepository = new AuthRepositoryJdbcImpl(dataSource);
        userService = new UserServiceImpl(userRepository);
        authService = new AuthServiceImpl(authRepository);
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        List<Integer> ids = new ArrayList<>();
        User user = userService.getUserByLoginAndPassword(login, password);
        if (user != null) {
            Auth auth = authService.createSession(user);
            Cookie cookie = new Cookie("auth", auth.getUuid());
            cookie.setMaxAge(60 * 60 * 24 * 3);
            response.addCookie(cookie);
            ids.add(1);
        } else {
            ids.add(2);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json");
        response.getWriter().write((objectMapper.writeValueAsString(ids)));
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){


        request.getRequestDispatcher("jsp/signIn.jsp").forward(request, response);
    }
}
