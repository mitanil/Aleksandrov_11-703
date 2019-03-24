package ru.itis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String getDefault(){
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String getHomePage(){
        return "home_page";
    }


}
