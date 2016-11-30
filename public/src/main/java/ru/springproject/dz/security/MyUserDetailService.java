package ru.springproject.dz.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.springproject.core.dz.entity.User;
import ru.springproject.core.dz.services.UserService;


@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User with name " + username + " not found");
        return new ru.springproject.dz.security.MyUserDetail(user);
    }
}
