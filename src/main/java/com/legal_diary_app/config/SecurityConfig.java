//package com.legal_diary_app.config;
//
//import com.legal_diary_app.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    private UserService userService;
//
//    @Autowired
//    public void setUserDetailService(UserService userservice){this.userService = userservice;}
//
//    @Override
//    protected void configure(HttpSecurity http){
//        try {
//
//            http.authorizeRequests()
//                    .antMatchers("/admin/**").hasRole( "ADMIN")
//                    .antMatchers("/diary/**").hasAnyRole("USER", "ADMIN")
//                    .antMatchers("/diary**").permitAll()
//                    .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .loginProcessingUrl("/authenticateTheUser")
//                    .permitAll()
//                    .and()
//                    .logout()
//                    .logoutSuccessUrl("/login")
//                    .permitAll();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//   public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        authenticationProvider.setUserDetailsService(userService);
//        return authenticationProvider;
//    }
//
//}
