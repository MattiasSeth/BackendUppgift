package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.Entities.Room;
import com.example.backenduppgift.Repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomRepository roomRepository;

    @RequestMapping("Rooms")
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }
}
