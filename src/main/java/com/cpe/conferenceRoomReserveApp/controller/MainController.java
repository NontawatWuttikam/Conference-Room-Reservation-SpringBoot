package com.cpe.conferenceRoomReserveApp.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cpe.conferenceRoomReserveApp.entity.Branch;
import com.cpe.conferenceRoomReserveApp.entity.Building;
import com.cpe.conferenceRoomReserveApp.entity.Reservation;
import com.cpe.conferenceRoomReserveApp.entity.Room;
import com.cpe.conferenceRoomReserveApp.entity.User;
import com.cpe.conferenceRoomReserveApp.iclass.IAddRoom;
import com.cpe.conferenceRoomReserveApp.iclass.IReservationData;
import com.cpe.conferenceRoomReserveApp.repository.RoomRepository;
import com.cpe.conferenceRoomReserveApp.service.BranchService;
import com.cpe.conferenceRoomReserveApp.service.BuildingService;
import com.cpe.conferenceRoomReserveApp.service.ReservationService;
import com.cpe.conferenceRoomReserveApp.service.RoomService;
import com.cpe.conferenceRoomReserveApp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

class C {
    public static int FLOAT_FLOOR = -1;
    public static String FLOAT_TH = "ลอย";
}

@Controller
public class MainController {

    @Autowired
    BranchService branchService;

    @Autowired
    RoomService roomService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    UserService userService;

    @Autowired
    BuildingService buildingService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/selectbranch")
    public ResponseEntity<List<Building>> selectBranchDropDown(Model model, String branchId) {
        System.out.print("test");
        return ResponseEntity.status(HttpStatus.OK).body(buildingService.getByBranchID(Long.parseLong(branchId)));
    }

    @RequestMapping("/login.html")
    public String login(Model model) {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        List<Branch> branches = branchService.getAllBranch();
        List<Room> rooms = roomService.getAllRoom();

        Map<Long, List<Room>> roomMapByBranch = rooms.stream().collect(Collectors.groupingBy(Room::getBranchID));

        System.out.println(new BCryptPasswordEncoder().encode("password"));

        User user = userService.findByEmail(loggedInUser.getName());
        List<Building> building = buildingService.getAll();

        List<Reservation> reserves = reservationService.getAll();
        Optional<Room> room = roomService.getRoomById(8L);
        List<Reservation> reservesByRoom = reservationService.getReservationByRoom(8L);
        String floor = (room.get().getFloor() == C.FLOAT_FLOOR) ? C.FLOAT_TH : String.valueOf(room.get().getFloor());
        model.addAttribute("roomName", room.get().getRoomName() + " " + room.get().getBranch().getBranchName() + " "
                + room.get().getBuilding().getBuildingName() + " " + " ชั้น " + floor);
        model.addAttribute("maxPeople", " จำนวนคนสูงสุด " + room.get().getMaxPeople() + " คน");
        model.addAttribute("reserves", reserves);
        model.addAttribute("reservesByRoom", reservesByRoom);
        model.addAttribute("selectedRoom", room);
        model.addAttribute("HORooms", roomMapByBranch.get(0L));
        model.addAttribute("RJRooms", roomMapByBranch.get(1L));
        model.addAttribute("username", user.getFirstName() + " " + user.getLastName());
        model.addAttribute("userID", user.getId());
        model.addAttribute("branches", branches);

        model.addAttribute("building", building);

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

        User user = userService.findByEmail(loggedInUser.getName());
        List<Building> building = buildingService.getAll();

        Optional<Room> room = roomService.getRoomById(roomId);
        model.addAttribute("reserves", reserves);
        model.addAttribute("reservesByRoom", reservesByRoom);
        String floor = (room.get().getFloor() == C.FLOAT_FLOOR) ? C.FLOAT_TH : String.valueOf(room.get().getFloor());
        model.addAttribute("roomName", room.get().getRoomName() + " " + room.get().getBranch().getBranchName() + " "
                + room.get().getBuilding().getBuildingName() + " " + " ชั้น " + floor);
        model.addAttribute("maxPeople", " จำนวนคนสูงสุด " + room.get().getMaxPeople() + " คน");

        model.addAttribute("HORooms", roomMapByBranch.get(0L));
        model.addAttribute("RJRooms", roomMapByBranch.get(1L));

        model.addAttribute("username", user.getFirstName() + " " + user.getLastName());
        model.addAttribute("userID", user.getId());
        model.addAttribute("branches", branches);

        model.addAttribute("building", building);
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

        if (iReservationData.getStartDateTime().get(SHIFT_INDEX).toLowerCase().equals("am") && shour == 12)
            startDateTime.setHours(0);
        else if (iReservationData.getStartDateTime().get(SHIFT_INDEX).toLowerCase().equals("am") && shour != 12)
            startDateTime.setHours(shour);
        else if (iReservationData.getStartDateTime().get(SHIFT_INDEX).toLowerCase().equals("pm") && shour == 12)
            startDateTime.setHours(shour);
        else if (iReservationData.getStartDateTime().get(SHIFT_INDEX).toLowerCase().equals("pm") && shour != 12)
            startDateTime.setHours(12 + shour);
        startDateTime.setMinutes(sminute);

        Date endDateTime = new Date();
        endDateTime.setDate(Integer.parseInt(iReservationData.getEndDateTime().get(DAY_INDEX)));
        endDateTime.setMonth(months.indexOf(iReservationData.getEndDateTime().get(MONTH_INDEX)));

        endDateTime.setYear(Integer.parseInt(iReservationData.getEndDateTime().get(YEAR_INDEX)) - 1900);

        int ehour = Integer.parseInt(iReservationData.getEndDateTime().get(TIME_INDEX).split(":")[0]);
        int eminute = Integer.parseInt(iReservationData.getEndDateTime().get(TIME_INDEX).split(":")[1]);

        if (iReservationData.getEndDateTime().get(SHIFT_INDEX).toLowerCase().equals("am") && ehour == 12)
            endDateTime.setHours(0);
        else if (iReservationData.getEndDateTime().get(SHIFT_INDEX).toLowerCase().equals("am") && ehour != 12)
            endDateTime.setHours(ehour);
        else if (iReservationData.getEndDateTime().get(SHIFT_INDEX).toLowerCase().equals("pm") && ehour == 12)
            endDateTime.setHours(ehour);
        else if (iReservationData.getEndDateTime().get(SHIFT_INDEX).toLowerCase().equals("pm") && ehour != 12)
            endDateTime.setHours(12 + ehour);

        endDateTime.setMinutes(eminute);

        reserve.setStartDateTime(startDateTime);
        reserve.setEndDateTime(endDateTime);

        reserve.setReserverID(Integer.parseInt(iReservationData.getUserId()));

        System.out.println(reserve);

        reservationService.createReservation(reserve);

        return "home";
    }

    @PostMapping("/addroom")
    public String addRoom(Model model, IAddRoom iAddRoom) {
        Room room = new Room();
        room.setBuildingId(Long.parseLong(iAddRoom.getBuildingId()));
        room.setFloor(Integer.parseInt(iAddRoom.getFloorNo()));
        room.setRoomName(iAddRoom.getTitle());
        room.setMaxPeople(Integer.parseInt(iAddRoom.getMaxPeople()));
        roomService.addRoom(room);
        return "home";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "home"; // You can redirect wherever you want, but generally it's a good practice to
                       // show login screen again.
    }

}
