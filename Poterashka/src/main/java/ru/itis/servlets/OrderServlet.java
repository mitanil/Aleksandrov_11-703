package ru.itis.servlets;

import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itis.forms.OrderForm;
import ru.itis.models.*;
import ru.itis.repositories.ClientRepositoryImpl;
import ru.itis.repositories.ItemRepositoryImpl;
import ru.itis.repositories.LocationRepositoryImpl;
import ru.itis.repositories.OrderRepositoryImpl;
import ru.itis.services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {


    ClientServices clientServices;
    ItemServices itemServices;
    LocationServices locationServices;
    OrderServices orderServices;


    @Override
    @SneakyThrows
    public void init() throws ServletException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Class.forName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/poterashka");

        clientServices = new ClientServicesImpl(new ClientRepositoryImpl(dataSource), new BCryptPasswordEncoder());
        itemServices = new ItemServicesImpl(new ItemRepositoryImpl(dataSource));
        locationServices = new LocationServicesImpl(new LocationRepositoryImpl(dataSource));
        orderServices = new OrderServicesImpl(new OrderRepositoryImpl(dataSource));
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Integer orderId = Integer.parseInt(req.getParameter("orderId"));
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        if(action == null) {
            resp.getWriter().write("0");
            return;
        }
        if(action.equals("addr")){
            Integer locationId = Integer.parseInt(req.getParameter("loc"));
            orderServices.addCurrentLocation(orderId,locationId);
            resp.getWriter().write("1");
        }else if(action.equals("close")){
            orderServices.closeOrder(orderId);
            resp.getWriter().write("1");
        }else {
            resp.getWriter().write("0");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer orderId;
        try{
            orderId = Integer.parseInt(req.getParameter("order"));
        }catch (NullPointerException e){
            orderId = null;
        }
        Order order = orderServices.getOrderById(orderId);
        Item item = null;
        Location location = null;
        Building building = null;
        Client owner = null;
        Location locNow = null;

        if (order != null) {
            building = locationServices.getBuildingByLocation(order.getPlaceOfLost());
            location = locationServices.getLocation(order.getPlaceOfLost());
            locNow = locationServices.getLocation(order.getDestination());
            item = itemServices.getItemById(order.getItemId());
            if(item != null)
                owner = clientServices.getClientById(item.getOwner());
        }
        OrderForm orderForm = OrderForm.builder()
                .order(order)
                .building(building)
                .location(location)
                .item(item)
                .owner(owner)
                .locNow(locNow)
                .build();

        req.setAttribute("order", orderForm);
        req.getRequestDispatcher("jsp/order.jsp").forward(req, resp);
    }
}
