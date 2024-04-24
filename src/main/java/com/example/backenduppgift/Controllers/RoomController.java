package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.DTO.RoomDto;
import com.example.backenduppgift.Entities.Room;
import com.example.backenduppgift.Repositories.RoomRepository;
import com.example.backenduppgift.Services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomRepository roomRepository;

    private final RoomService roomService;

    @RequestMapping("Rooms")
    public List<RoomDto> getAllRoomsDto(){
        return roomService.getAllRoomDto();
    }
}
