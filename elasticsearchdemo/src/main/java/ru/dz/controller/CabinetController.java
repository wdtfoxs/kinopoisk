package ru.dz.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Vlad.M on 10.12.2016.
 */
@Controller
@RequestMapping(value = "/profile")
@PreAuthorize("isAuthenticated()")
public class CabinetController {
    @RequestMapping(value = "")
    public String loadPage(){
        return "profile";
    }
}
