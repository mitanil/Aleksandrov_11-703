package ru.itis.servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.forms.ProductGroup;
import ru.itis.models.Product;
import ru.itis.repositories.*;
import ru.itis.services.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/products.json")
public class ProductServlet extends HttpServlet {
    BasketService basketService;
    UserService userService;
    ProductService productService;

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
        basketService = new BasketServiceImpl(basketRepository, userRepository);
        userService = new UserServiceImpl(userRepository);
        productService = new ProductServiceImpl(new ProductRepositoryJdbcImpl(dataSource));
    }

    @Override
    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        List<ProductGroup> ids = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        Cookie[] cookies = request.getCookies();
        String uuid = null;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("auth")){
                uuid = cookie.getValue();
                ids = basketService.getProductsByUserUuid(uuid);
                break;
            }
        }
//        Long itemId = Long.parseLong(request.getParameter("id"));
//        Cookie[] cookies = request.getCookies();
//        for(Cookie cookie : cookies){
//            if(cookie.getName().equals("baskId")){
//                basketService.addProduct(itemId, Long.parseLong(cookie.getValue()));
//                ids = basketService.getGroupedProducts(Long.parseLong(cookie.getValue()));
//                break;
//            }
//        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(ids));
//        response.sendRedirect("/home");
    }


    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){

        List<Product> products = productService.getAllProducts();

        ObjectMapper objectMapper = new ObjectMapper();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(products));
//        request.getRequestDispatcher("jsp/home.jsp").forward(request, response);

//        request.setAttribute("products", products);
    }
}
