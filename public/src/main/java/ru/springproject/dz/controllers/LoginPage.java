package ru.springproject.dz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginPage {

    @RequestMapping(method = RequestMethod.GET)
    public String getLoginPage(){
        return "login";
    }
}
