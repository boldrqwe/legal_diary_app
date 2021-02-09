//package com.legal_diary_app.controllers;
//
//import com.legal_diary_app.service.UserService;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//
//
//
//@Controller
//@RequestMapping("/users")
//public class UserController {
//
//
//    private UserService userService;
//
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//  @GetMapping
//    public String showUser(Model model){
//        model.addAttribute("users", userService.findAll());
//        return "users";
//    }
//
//
//}
