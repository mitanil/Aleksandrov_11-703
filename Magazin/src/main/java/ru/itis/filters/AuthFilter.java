package ru.itis.filters;

import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.models.Basket;
import ru.itis.models.User;
import ru.itis.repositories.*;
import ru.itis.services.*;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebFilter(urlPatterns = {"/home"})
public class AuthFilter implements Filter {

    UserService userService;
    BasketService basketService;


    @Override
    @SneakyThrows
    public void init(FilterConfig filterConfig){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Class.forName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/shopbasket");

        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);
        BasketRepository basketRepository = new BasketRepositoryJdbcImpl(dataSource);
        userService = new UserServiceImpl(userRepository);
        basketService = new BasketServiceImpl(basketRepository, userRepository);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        Cookie cookies[] = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("auth")) {
                    if (userService.isExistByCookie(cookie.getValue())) {
                        User user = userService.getUserByUUID(cookie.getValue());
                        Long basketId;
                        try{
                            basketId = basketService.getBasketIdByUserId(user.getUserId());
                        }catch (NullPointerException e){
                                basketId = basketService.createBasket(user.getUserId()).getId();
                        }
                        Cookie baskIdCookie = new Cookie("baskId", basketId.toString());
                        response.addCookie(baskIdCookie);
                        filterChain.doFilter(request, response);
                    }
                }
            }
            response.sendRedirect("/signIn");
            return;
        }
        response.sendRedirect("/signIn");
        return;
    }

    @Override
    public void destroy() {

    }
}
