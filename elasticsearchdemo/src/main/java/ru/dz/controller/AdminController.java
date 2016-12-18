package ru.dz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by Vlad.M on 18.12.2016.
 */
@Controller
public class AdminController {
    private static final String ADMIN_MAPPING = "/admin";
    @RequestMapping(value = ADMIN_MAPPING)
    public String loadPage(){
        return "admin";
    }

}
