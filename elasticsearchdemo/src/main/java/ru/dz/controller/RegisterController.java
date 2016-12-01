package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.dz.entity.MyUser;
import ru.dz.repository.UserRepository;
import ru.dz.valid.UserValid;

import javax.validation.Valid;

/**
 * Created by Vlad.M on 29.11.2016.
 */
@Controller
public class RegisterController {
    private static final String ATTR_REGISTRATION_FORM = "regForm";

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/reg",method = RequestMethod.GET)
    public String getRegPage(ModelMap modelMap){
        modelMap.addAttribute(ATTR_REGISTRATION_FORM, new UserValid());
        return "registration";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String authenticate(@Valid @ModelAttribute("regform") UserValid regform,
                               BindingResult result, ModelMap modelMap){
        if(result.hasErrors()){
            return "registration";
        }
        else{
            if (userRepository.findByEmail(regform.getEmail())!=null){
                modelMap.addAttribute("emailExists","Пользователь с такой почтой уже зарегистрирован");
                return "registration";
            }
            userRepository.save(new MyUser(regform.getUsername(),regform.getSurname(),regform.getEmail(),regform.getPassword()));
            return "index";
        }
    }
}
