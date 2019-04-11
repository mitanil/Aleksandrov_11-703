package ru.itis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller("/")
public class DefaultController {

    @RequestMapping(method = RequestMethod.GET)
    public String getDefault(){
        return "redirect:/home";
    }

}
