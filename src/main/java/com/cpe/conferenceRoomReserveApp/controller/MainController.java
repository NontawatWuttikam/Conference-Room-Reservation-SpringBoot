package com.cpe.conferenceRoomReserveApp.controller;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/login.html")
    public String login(Model model) {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", loggedInUser.getName());
        return "home";
    }
}
