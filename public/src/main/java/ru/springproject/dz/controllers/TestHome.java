package ru.springproject.dz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestHome {

    @RequestMapping("/home")
    public String kek(){
        return "test";
    }
}
