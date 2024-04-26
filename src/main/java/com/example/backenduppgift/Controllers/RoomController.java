package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.DTO.CustomerDto;
import com.example.backenduppgift.DTO.RoomDto;
import com.example.backenduppgift.Entities.Room;
import com.example.backenduppgift.Repositories.RoomRepository;
import com.example.backenduppgift.Services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomRepository roomRepository;

    private final RoomService roomService;

    @RequestMapping("rooms")
    public List<RoomDto> getAllRooms(){
        return roomService.getAllRoomDto();
    }

    @PostMapping("rooms/add")
    public List<RoomDto> addRoomDto(@RequestBody RoomDto roomDto){
        roomService.getAllRoomDto().add(roomDto);
        return roomService.getAllRoomDto();
    }

    @PostMapping("rooms/delete")
    public List<RoomDto> deleteRoomDto(@RequestBody RoomDto roomDto){
        roomService.getAllRoomDto().remove(roomDto);
        return roomService.getAllRoomDto();
    }


}
