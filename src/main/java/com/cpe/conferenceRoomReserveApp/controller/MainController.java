package com.cpe.conferenceRoomReserveApp.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cpe.conferenceRoomReserveApp.entity.Branch;
import com.cpe.conferenceRoomReserveApp.entity.Reservation;
import com.cpe.conferenceRoomReserveApp.entity.Room;
import com.cpe.conferenceRoomReserveApp.entity.Staff;
import com.cpe.conferenceRoomReserveApp.iclass.IReservationData;
import com.cpe.conferenceRoomReserveApp.service.BranchService;
import com.cpe.conferenceRoomReserveApp.service.ReservationService;
import com.cpe.conferenceRoomReserveApp.service.RoomService;
import com.cpe.conferenceRoomReserveApp.service.StaffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainController {

    @Autowired
    BranchService branchService;

    @Autowired
    RoomService roomService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    StaffService staffService;

    @RequestMapping("/login.html")
    public String login(Model model) {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        List<Branch> branches = branchService.getAllBranch();
        List<Room> rooms = roomService.getAllRoom();

        Map<Long, List<Room>> roomMapByBranch = rooms.stream().collect(Collectors.groupingBy(Room::getBranchID));

        System.out.println(new BCryptPasswordEncoder().encode("password"));

        List<Reservation> reserves = reservationService.getAll();
        Optional<Room> room = roomService.getRoomById(8L);
        List<Reservation> reservesByRoom = reservationService.getReservationByRoom(8L);
        model.addAttribute("roomName", room.get().getRoomName() + " " + room.get().getBranch().getBranchName());
        model.addAttribute("reserves", reserves);
        model.addAttribute("reservesByRoom", reservesByRoom);
        model.addAttribute("selectedRoom", room);
        model.addAttribute("HORooms", roomMapByBranch.get(0L));
        model.addAttribute("RJRooms", roomMapByBranch.get(1L));
        model.addAttribute("username", loggedInUser.getName());
        model.addAttribute("branches", branches);
        return "home";
    }

    @RequestMapping("/room/{rid}")
    public String getBranch(Model model, @PathVariable("rid") Long roomId) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        List<Branch> branches = branchService.getAllBranch();
        List<Room> rooms = roomService.getAllRoom();
        Map<Long, List<Room>> roomMapByBranch = rooms.stream().collect(Collectors.groupingBy(Room::getBranchID));

        System.out.println(new BCryptPasswordEncoder().encode("password"));

        List<Reservation> reserves = reservationService.getAll();
        ;

        List<Reservation> reservesByRoom = reservationService.getReservationByRoom(roomId);
        Optional<Room> room = roomService.getRoomById(roomId);
        model.addAttribute("reserves", reserves);
        model.addAttribute("reservesByRoom", reservesByRoom);
        model.addAttribute("roomName", room.get().getRoomName() + " " + room.get().getBranch().getBranchName());

        model.addAttribute("HORooms", roomMapByBranch.get(0L));
        model.addAttribute("RJRooms", roomMapByBranch.get(1L));

        model.addAttribute("username", loggedInUser.getName());
        model.addAttribute("branches", branches);
        return "home";
    }

    @RequestMapping(value = "reserveroom", method = RequestMethod.POST)
    public String postMethodName(Model model, IReservationData iReservationData) {
        int TIME_INDEX = 3;
        int YEAR_INDEX = 2;
        int DAY_INDEX = 1;
        int MONTH_INDEX = 0;
        int SHIFT_INDEX = 4;

        List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December");
        Reservation reserve = new Reservation();
        reserve.setTitle(iReservationData.getTitle());
        reserve.setDescription(iReservationData.getDescription());
        reserve.setRoomID(Integer.parseInt(iReservationData.getRoom()));

        Date startDateTime = new Date();
        startDateTime.setDate(Integer.parseInt(iReservationData.getStartDateTime().get(DAY_INDEX)));
        startDateTime.setMonth(months.indexOf(iReservationData.getStartDateTime().get(MONTH_INDEX)));
        startDateTime.setYear(Integer.parseInt(iReservationData.getStartDateTime().get(YEAR_INDEX)) - 1900);

        int shour = Integer.parseInt(iReservationData.getStartDateTime().get(TIME_INDEX).split(":")[0]);
        int sminute = Integer.parseInt(iReservationData.getStartDateTime().get(TIME_INDEX).split(":")[1]);

        startDateTime.setHours(shour);
        startDateTime.setMinutes(sminute);

        Date endDateTime = new Date();
        endDateTime.setDate(Integer.parseInt(iReservationData.getEndDateTime().get(DAY_INDEX)));
        endDateTime.setMonth(months.indexOf(iReservationData.getEndDateTime().get(MONTH_INDEX)));

        endDateTime.setYear(Integer.parseInt(iReservationData.getEndDateTime().get(YEAR_INDEX)) - 1900);

        int ehour = Integer.parseInt(iReservationData.getEndDateTime().get(TIME_INDEX).split(":")[0]);
        int eminute = Integer.parseInt(iReservationData.getEndDateTime().get(TIME_INDEX).split(":")[1]);

        endDateTime.setHours(ehour);
        endDateTime.setMinutes(eminute);

        reserve.setStartDateTime(startDateTime);
        reserve.setEndDateTime(endDateTime);

        Optional<Staff> staffInfo = staffService.getStaffByName(iReservationData.getUsername());

        reserve.setReserverID(staffInfo.get().getStaffID());

        System.out.println(reserve);

        reservationService.createReservation(reserve);

        return "home";
    }

}
