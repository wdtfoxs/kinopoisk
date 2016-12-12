package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Vlad.M on 29.11.2016.
 */
@Controller
public class LoginController {

    private final HttpServletRequest request;

    @Autowired
    public LoginController(HttpServletRequest request) {
        this.request = request;
    }

    @RequestMapping(value = "/login")
    public String getLoginPage(Boolean error){
        request.setAttribute("error", error);
        return "login";
    }
}
