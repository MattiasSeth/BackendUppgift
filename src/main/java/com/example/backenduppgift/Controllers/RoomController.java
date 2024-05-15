package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.DTO.BlacklistDto;
import com.example.backenduppgift.DTO.RoomDto;
import com.example.backenduppgift.DTO.RoomEventDTO;
import com.example.backenduppgift.Entities.Blacklist;
import com.example.backenduppgift.Services.BlacklistService;
import com.example.backenduppgift.Services.RoomEventService;
import com.example.backenduppgift.Services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/room")
public class RoomController {

    private final RoomService roomService;
    private final RoomEventService roomEventService;

    @RequestMapping("/all")
    public String getAllCustomers(Model model){
        List<RoomDto> rooms = roomService.getAllRooms();
        model.addAttribute("allRooms", rooms);
        model.addAttribute("roomTitle", "All Rooms");
        return "showAllRooms";
    }

    @RequestMapping(path = "/details/{id}")
    public String editName (@PathVariable Long id, Model model){
        List<RoomEventDTO> roomEvents = roomEventService.getRoomEventWithRoomId(id);
        model.addAttribute("roomDetails", "Room details");
        model.addAttribute("roomEvents", roomEvents);
        return "showRoomDetails";
    }

}
