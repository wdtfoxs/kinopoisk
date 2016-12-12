package ru.dz.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.dz.security.MyUserDetail;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class MainUserAspect {

    private static final String USER = "user";

    private final HttpServletRequest request;

    @Autowired
    public MainUserAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Pointcut("@annotation(ru.dz.aspects.annotation.IncludeUser)")
    public void includeUserMethod() {
    }

    @Before("includeUserMethod()")
    public void includeMenuInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            request.setAttribute(USER, ((MyUserDetail) authentication.getPrincipal()).getUser());
        }
    }
}
