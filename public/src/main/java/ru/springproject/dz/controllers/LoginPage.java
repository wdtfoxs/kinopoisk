package ru.springproject.dz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;



@Controller
@RequestMapping("/login")
public class LoginPage {

    private final HttpServletRequest request;

    @Autowired
    public LoginPage(HttpServletRequest request) {
        this.request = request;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getLoginPage(Boolean error){
        request.setAttribute("error", error);
        return "login";
    }

}
