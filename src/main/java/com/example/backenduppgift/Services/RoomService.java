package com.example.backenduppgift.Services;

import com.example.backenduppgift.DTO.RoomDto;
import com.example.backenduppgift.Entities.Room;

import java.util.List;

public interface RoomService {

    public RoomDto roomToRoomDto(Room room);

    public List<RoomDto> getAllRooms();

    public RoomDto getById(Long id);

    Room roomDtoToRoom(RoomDto roomDto);
}
