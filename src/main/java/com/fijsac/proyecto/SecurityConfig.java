package com.fijsac.proyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.fijsac.proyecto.controller.model.service.UserService;

@Configuration
public class SecurityConfig {
      @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public static BCryptPasswordEncoder encriptarPassword(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void criptografiaPassword(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(userService).passwordEncoder(encriptarPassword());
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Autowired
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests()
        .requestMatchers("/*", "/login").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin().loginPage("/login").defaultSuccessUrl("/dashboard/", true)
        .and()
        .logout().permitAll();
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception{
        return (web)->web.ignoring().requestMatchers("/css/**","/js/**","/assets/**");
    }

}
