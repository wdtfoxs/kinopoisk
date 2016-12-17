package ru.dz.config;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.dz.security.MyUserDetailService;

/**
 * Created by Vlad.M on 29.11.2016.
 */

@ComponentScan("ru.dz")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailService userDetailsService;

    @Autowired
    public SecurityConfig(MyUserDetailService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.addFilter(requestHeaderAuthenticationFilter())
//                .antMatcher("/protected/**").authorizeRequests().anyRequest().authenticated();

        http.authorizeRequests().antMatchers("/profile").access("hasRole('ROLE_USER')")
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/login").failureUrl("/login?error=true").usernameParameter("username").passwordParameter("password")

                .defaultSuccessUrl("/main")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/main")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
//                .addFilter(requestHeaderAuthenticationFilter())
//                .antMatcher("/protected/**")
//                .authorizeRequests().anyRequest().authenticated()
//                .and()
                .csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

//    @Bean
//    public RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter()throws Exception{
//        RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter = new RequestHeaderAuthenticationFilter();
//        requestHeaderAuthenticationFilter.setPrincipalRequestHeader("X-AUTH-TOKEN");
//        requestHeaderAuthenticationFilter.setAuthenticationManager(authenticationManager());
//        requestHeaderAuthenticationFilter.setExceptionIfHeaderMissing(false);
//
//        return requestHeaderAuthenticationFilter;
//    }

//    @Bean
//    public PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider(){
//        PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider = new PreAuthenticatedAuthenticationProvider();
//        preAuthenticatedAuthenticationProvider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper<>(userDetailsService));
//
//        return preAuthenticatedAuthenticationProvider;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(preAuthenticatedAuthenticationProvider());
//    }
}

