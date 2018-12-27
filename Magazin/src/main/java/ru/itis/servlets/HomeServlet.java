package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.forms.ProductGroup;
import ru.itis.models.Product;
import ru.itis.repositories.*;
import ru.itis.services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    ProductService productService;
    BasketService basketService;
    UserService userService;

    @Override
    @SneakyThrows
    public void init() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Class.forName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/shopbasket");

        BasketRepository basketRepository = new BasketRepositoryJdbcImpl(dataSource);
        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);
        ProductRepository productRepository = new ProductRepositoryJdbcImpl(dataSource);
        productService = new ProductServiceImpl(productRepository);
        basketService = new BasketServiceImpl(basketRepository, userRepository);
        userService = new UserServiceImpl(userRepository);
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        List<ProductGroup> ids = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        Long itemId = Long.parseLong(request.getParameter("id"));
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("baskId")){
                basketService.addProduct(itemId, Long.parseLong(cookie.getValue()));
                ids = basketService.getGroupedProducts(Long.parseLong(cookie.getValue()));
                break;
            }
        }
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(ids));
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        String uuid = null;
        List<Product> products = productService.getAllProducts();
        List<ProductGroup> productGroup = null;
        for(Cookie cookie : cookies) {
            if (cookie.getName().equals("auth")) {
                uuid = cookie.getValue();
                productGroup = basketService.getProductsByUserUuid(uuid);
            }
        }


        //
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json");
//        response.getWriter().write(objectMapper.writeValueAsString(products));



        request.setAttribute("products", products);
        request.setAttribute("productsInBasket", productGroup);
        request.getRequestDispatcher("jsp/home.jsp").forward(request, response);

//        request.setAttribute("products", products);
    }


}
