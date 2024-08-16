package com.demo.spring_tf_project.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login") // get custom login page
    public String loginPage(){
        return "view/login"; // login.html
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // get authenticated user (principal)
        if(auth != null){ // if the authenticated principal is not null
            new SecurityContextLogoutHandler().logout(request, response, auth); // then log out the principal
        }
        return "redirect:/login"; // redirect to login page
    }
}
