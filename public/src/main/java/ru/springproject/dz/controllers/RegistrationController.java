package ru.springproject.dz.controllers;


import com.vk.api.sdk.client.Lang;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import com.vk.api.sdk.queries.users.UserField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.springproject.core.dz.entity.User;
import ru.springproject.core.dz.services.UserService;
import ru.springproject.dz.security.MyUserDetail;
import ru.springproject.dz.valid.UserValid;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private static final String ATTR_REGISTRATION_FORM = "regForm";
    private final HttpServletRequest request;
    private final UserService userService;

    private final String VK_LINK = "https://oauth.vk.com/authorize";
    private final int CLIENT_ID = 5756557;
    private final String SECRET_KEY = "uexXpIHhdI94aBAExTbl";
    private final String REDIRECT_URI = "http://localhost:8080/registration/vk";

    @Autowired
    public RegistrationController(HttpServletRequest request, UserService userService) {
        this.request = request;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getRegPage() {
        request.setAttribute(ATTR_REGISTRATION_FORM, new UserValid());
        return "registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String regUser(@Valid @ModelAttribute(ATTR_REGISTRATION_FORM) UserValid userValid, BindingResult bindingResult) {
        boolean us = userService.existsUserByUsername(userValid.getUsername());
        boolean email = userService.existsUserByEmail(userValid.getEmail());
        boolean pass = Objects.equals(userValid.getPassword(), userValid.getRepassword());

        if (bindingResult.hasErrors() || !us || !pass || !email) {
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
        user.setPhoto("../../resources/images/p1.png");
        userService.addUser(user);

        MyUserDetail myUserDetail = new MyUserDetail(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(myUserDetail, null, myUserDetail.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return "redirect:/main";
    }

    @RequestMapping(value = "regvk")
    public RedirectView regThroughVk() {
        return new RedirectView(VK_LINK + "?client_id=" + CLIENT_ID + "&display=page&redirect_uri=" +
                REDIRECT_URI + "&scope=email,offline&response_type=code&v=5.60");
    }

    @RequestMapping(value = "/vk", method = RequestMethod.GET)
    public String regUserByVk(@RequestParam String code) throws ClientException, ApiException {
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);

        UserAuthResponse authResponse = vk.oauth().userAuthorizationCodeFlow(CLIENT_ID, SECRET_KEY, REDIRECT_URI, code).execute();
        List<UserXtrCounters> users = vk.users().get().userIds(authResponse.getUserId() + "").fields(UserField.PHOTO_50).lang(Lang.RU).execute();

        UserXtrCounters userXtrCounters = users.get(0);

        User myUser = userService.getUserByVk(userXtrCounters.getId());

        if (myUser == null) {
            User user = new User();
            user.setUsername(userXtrCounters.getFirstName());
            user.setVkontakte_id(userXtrCounters.getId());
            user.setPhoto(userXtrCounters.getPhoto50());
            userService.addUser(user);
            myUser = user;
        }

        MyUserDetail user = new MyUserDetail(myUser);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        return "redirect:/main";
    }
}
