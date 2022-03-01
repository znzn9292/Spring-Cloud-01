package com.znzn.userservice.security;

import com.znzn.userservice.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private Environment env;
    private UserService userService;
    private PasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(Environment env, UserService userService, PasswordEncoder bCryptPasswordEncoder) {
        this.env = env;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // 권한에 대한 설정
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.err.println("HttpSecurity - config");
        http.csrf().disable();
            http.authorizeRequests().antMatchers("/actuator/**").permitAll();
            http.authorizeRequests().antMatchers("/**")
                        .hasIpAddress("192.168.45.242")
                    .and()
                        .addFilter(getAuthenticationFilter());

        http.headers().frameOptions().disable();
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        System.err.println("getAuthenticationFilter");
        AuthenticationManager authenticationManager = authenticationManager();
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager, userService, env);
        authenticationFilter.setAuthenticationManager(authenticationManager);
        return authenticationFilter;
    }

    // 인증에 대한 설정
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.err.println("AuthenticationManagerBuilder - config");
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }
}
