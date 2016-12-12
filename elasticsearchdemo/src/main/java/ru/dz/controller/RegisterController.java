package ru.dz.controller;

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
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.dz.entity.User;
import ru.dz.repository.UserRepository;
import ru.dz.security.MyUserDetail;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 * Created by Vlad.M on 29.11.2016.
 */
@Controller
@PropertySource("classpath:application.properties")
@RequestMapping(value = "/reg")
public class RegisterController {

    private final UserRepository userRepository;
    private final HttpServletRequest request;
    private Environment env;

    @Autowired
    public RegisterController(UserRepository userRepository, HttpServletRequest request, Environment env) {
        this.userRepository = userRepository;
        this.request = request;
        this.env = env;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getRegPage(ModelMap modelMap) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {

            return "index";
        }
        return "registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registration(@RequestParam("username") String username, @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("repassword") String confirm_password, ModelMap map) {
        boolean us = checkUsername(username);
        boolean em = checkEmail(email);
        boolean pass = checkPassword(password);
        boolean passEq = password.equals(confirm_password);
        boolean userExist = userRepository.findByUsername(username) == null;
        boolean emailExist = userRepository.findByEmail(email) == null;

        if (!us || !em || !pass || !passEq || !userExist || !emailExist){
            if (!checkUsername(username))
                map.addAttribute("username", "Имя пользователя должно состоять из 4 и более букв и цифр латинского язык");
            if (!em)
                map.addAttribute("email", "Email введен некорректно");
            if (!pass)
                map.addAttribute("password", "Пароль может состоять из минимум 6 цифр и букв латинского языка");
            if (!passEq)
                map.addAttribute("repassword", "Пароли не совпадают");
            if (!userExist)
                map.addAttribute("usernameex", "Данное имя уже используется");
            if (!emailExist)
                map.addAttribute("emailex", "Email уже используется");
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

    @RequestMapping(value = "regvk")
    public RedirectView regThroughVk() throws IOException {
        return new RedirectView(env.getProperty("vk.link") + "?client_id=" + env.getProperty("vk.client.id") + "&display=page&redirect_uri=" +
                env.getProperty("vk.redirecturi") + "&scope=email,offline&response_type=code&v=5.60");
    }

    @RequestMapping(value = "/vk", method = RequestMethod.GET)
    public String regUserByVk(@RequestParam String code) throws ClientException, ApiException {
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);

        UserAuthResponse authResponse = vk.oauth().userAuthorizationCodeFlow(Integer.valueOf(env.getProperty("vk.client.id")), env.getProperty("vk.secretkey"), env.getProperty("vk.redirecturi"), code).execute();
        List<UserXtrCounters> users = vk.users().get().userIds(authResponse.getUserId() + "").fields(UserField.PHOTO_50).lang(Lang.RU).execute();

        UserXtrCounters userXtrCounters = users.get(0);

        User myUser = userRepository.findByVk(userXtrCounters.getId());

        if (myUser == null) {
            User user = new User();
            user.setUsername(userXtrCounters.getFirstName());
            user.setVk(userXtrCounters.getId());
            user.setPhoto(userXtrCounters.getPhoto50());
            userRepository.save(user);
            myUser = user;
        }

        MyUserDetail user = new MyUserDetail(myUser);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        return "redirect:/main";
    }

    private boolean checkEmail(String email){
        Pattern pattern = Pattern.compile("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean checkUsername(String username){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{4,20}$");
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    private boolean checkPassword(String password){
        Pattern pattern = Pattern.compile("^[A-z0-9]{6,18}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
