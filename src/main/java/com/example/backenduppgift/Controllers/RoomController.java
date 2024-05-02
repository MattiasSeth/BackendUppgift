package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.DTO.CustomerDto;
import com.example.backenduppgift.DTO.RoomDto;
import com.example.backenduppgift.Entities.Room;
import com.example.backenduppgift.Repositories.RoomRepository;
import com.example.backenduppgift.Services.BookingService;
import com.example.backenduppgift.Services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final BookingService bookingService;
    private final RoomService roomService;

    @RequestMapping("rooms")
    public List<RoomDto> getAllRooms(){
        return roomService.getAllRooms();
    }

    @PostMapping("rooms/add")
    public List<RoomDto> addRoomDto(@RequestBody RoomDto roomDto){
        roomService.getAllRooms().add(roomDto);
        return roomService.getAllRooms();
    }

    @PostMapping("rooms/delete")
    public List<RoomDto> deleteRoomDto(@RequestBody RoomDto roomDto){
        roomService.getAllRooms().remove(roomDto);
        return roomService.getAllRooms();
    }

    @PostMapping("result")
    public List<RoomDto> searchRooms(@RequestParam @DateTimeFormat(pattern = "MM/dd/yyyy") LocalDate startDate, @RequestParam @DateTimeFormat(pattern = "MM/dd/yyyy") LocalDate endDate){
        return bookingService.findAvailableRooms(startDate, endDate);
    }

}
