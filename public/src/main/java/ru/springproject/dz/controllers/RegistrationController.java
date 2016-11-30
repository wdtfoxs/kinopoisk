package ru.springproject.dz.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.springproject.core.dz.entity.User;
import ru.springproject.core.dz.services.UserService;
import ru.springproject.dz.valid.UserValid;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private static final String ATTR_REGISTRATION_FORM = "regForm";

    private final HttpServletRequest request;

    protected final AuthenticationManager authenticationManager;

    private final UserService userService;

    @Autowired
    public RegistrationController(HttpServletRequest request, UserService userService, AuthenticationManager authenticationManager) {
        this.request = request;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getRegPage(){
        request.setAttribute(ATTR_REGISTRATION_FORM, new UserValid());
        return "registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String regUser(@Valid @ModelAttribute(ATTR_REGISTRATION_FORM) UserValid userValid, BindingResult bindingResult){
        boolean us = userService.existsUserByUsername(userValid.getUsername());
        boolean email = userService.existsUserByEmail(userValid.getEmail());
        boolean pass = Objects.equals(userValid.getPassword(), userValid.getRepassword());

        if (bindingResult.hasErrors() || !us || !pass || !email){
            if (!us)
                bindingResult.rejectValue("username", "error.username", "Данное имя уже занято");
            if (!pass)
                bindingResult.rejectValue("password", "error.password", "Пароли не совпадают");
            if (!email)
                bindingResult.rejectValue("email", "error.email", "Данный email занят другим пользователем");
            return "registration";
        }

        User user = new User();
        user.setUsername(userValid.getUsername());
        user.setPassword(userValid.getPassword());
        user.setEmail(userValid.getEmail());
        userService.addUser(user);

        return "login";
    }
}
