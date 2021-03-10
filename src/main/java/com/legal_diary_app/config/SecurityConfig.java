package com.legal_diary_app.config;

import com.legal_diary_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private UserService userService;

    @Autowired
    public void setUserDetailService(UserService userservice){this.userService = userservice;}

    @Override
    protected void configure(HttpSecurity http){
        try {

            http.authorizeRequests()
                    .antMatchers("/css/*").permitAll()
                    .antMatchers("/js/*").permitAll()
                    .antMatchers("/webfonts/*").permitAll()
                    .antMatchers("/webjars/**").permitAll()
                    .antMatchers("/registration/**").permitAll()
                    .antMatchers("/**").authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/authenticateTheUser")
                    .permitAll()
                    .and()
                    .logout()
                    .logoutSuccessUrl("/login")
                    .permitAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
   public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }

    @Bean
    public SecurityContextHolder getSecurityContext(){return new SecurityContextHolder();};

}
