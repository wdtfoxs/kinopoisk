package ru.dz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Vlad.M on 29.11.2016.
 */
@Controller
public class AuthController {
    @RequestMapping(value = "/auth")
    public String loadAuthPage(){
        return "login";
    }
}
