package com.cpe.conferenceRoomReserveApp.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.cpe.conferenceRoomReserveApp.entity.Branch;
import com.cpe.conferenceRoomReserveApp.entity.Reservation;
import com.cpe.conferenceRoomReserveApp.entity.Room;
import com.cpe.conferenceRoomReserveApp.service.BranchService;
import com.cpe.conferenceRoomReserveApp.service.ReservationService;
import com.cpe.conferenceRoomReserveApp.service.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    BranchService branchService;

    @Autowired
    RoomService roomService;

    @Autowired
    ReservationService reservationService;

    @RequestMapping("/login.html")
    public String login(Model model) {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        List<Branch> branches = branchService.getAllBranch();
        List<Room> rooms = roomService.getAllRoom();

        Map<Long, List<Room>> roomMapByBranch = rooms.stream().collect(Collectors.groupingBy(Room::getBranchID));

        System.out.println(new BCryptPasswordEncoder().encode("password"));

        List<Reservation> reserves = reservationService.getReservationByRoom(8L);
        model.addAttribute("reserves", reserves);
        model.addAttribute("HORooms", roomMapByBranch.get(0L));
        model.addAttribute("RJRooms", roomMapByBranch.get(1L));
        model.addAttribute("username", loggedInUser.getName());
        model.addAttribute("branches", branches);
        return "home";
    }
}
