package com.cpe.conferenceRoomReserveApp.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.cpe.conferenceRoomReserveApp.entity.Branch;
import com.cpe.conferenceRoomReserveApp.service.BranchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    BranchService branchService;

    @RequestMapping("/login.html")
    public String login(Model model) {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        List<Branch> branches = branchService.getAllBranch();

        Map<Long, List<Branch>> branchMap = branches.stream().collect(Collectors.groupingBy(Branch::getBranchID));

        model.addAttribute("username", loggedInUser.getName());
        model.addAttribute("branches", branches);
        return "home";
    }
}
