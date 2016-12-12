package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import ru.dz.entity.User;
import ru.dz.repository.UserRepository;
import ru.dz.security.MyUserDetail;

import javax.servlet.http.HttpServletRequest;

import static org.apache.coyote.http11.Constants.a;

/**
 * Created by Vlad.M on 29.11.2016.
 */
@Controller
public class RegisterController {

    private final UserRepository userRepository;
    private final HttpServletRequest request;

    @Autowired
    public RegisterController(UserRepository userRepository, HttpServletRequest request) {
        this.userRepository = userRepository;
        this.request = request;
    }

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String getRegPage(ModelMap modelMap) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {

            return "index";
        }
        return "registration";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String registration(@RequestParam("username") String username, @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("repassword") String confirm_password, ModelMap map) {
        if (username.length() < 2 || username.length() > 30) {
            map.addAttribute("message", "Заполните корректно имя и фамилию");
            return "registration";
        }
        if (password.length() < 5 || password.length() > 30) {
            map.addAttribute("message", "пароль должен быть не меньше 5 и не больше 30 символов");
            return "registration";
        }
        if (!password.equals(confirm_password)) {
            map.addAttribute("message", "Пароли не совпадают");
            return "registration";
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);

        MyUserDetail myUserDetail = new MyUserDetail(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(myUserDetail, null, myUserDetail.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return "redirect:/main";
    }
}
