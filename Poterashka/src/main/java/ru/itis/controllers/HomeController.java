package ru.itis.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.forms.OrderForm;
import ru.itis.models.Order;
import ru.itis.services.ItemServices;
import ru.itis.services.LocationServices;
import ru.itis.services.OrderServices;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private OrderServices orderServices;
    @Autowired
    private LocationServices locationServices;
    @Autowired
    private ItemServices itemServices;

    @GetMapping("/home")
    public String getHomePage(){
        return "home_page";
    }


    @PostMapping(value = "/homeItemsAjax", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getOrders(){

        return null;
    }

    @GetMapping(value = "/HomeItemsAjax", produces = "application/json")
    public String getLastItems(){
        List<OrderForm> orderForms = new ArrayList<>();
        List<Order> orders = orderServices.getLastNOrders(12);
        for (Order order :
                orders) {
            orderForms.add(OrderForm.builder()
                    .order(order)
                    .building(locationServices.getBuilding(locationServices.getBuildingByLocation(order.getPlaceOfLost()).getId()))
                    .location(locationServices.getLocation(order.getPlaceOfLost()))
                    .item(itemServices.getItemById(order.getItemId()))
                    .build());
        }

        try {
            return (new ObjectMapper().writeValueAsString(orderForms));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }


}
