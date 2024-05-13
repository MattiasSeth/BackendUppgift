package com.example.backenduppgift.Services.impl;

import com.example.backenduppgift.DTO.RoomDto;
import com.example.backenduppgift.Entities.Room;
import com.example.backenduppgift.Repositories.RoomRepository;
import com.example.backenduppgift.Services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    final private RoomRepository roomRepository;

    @Override
    public List<RoomDto> getAllRooms() {
        return roomRepository.findAll().stream().map(r -> roomToRoomDto(r)).toList();
    }

    @Override
    public Room getByIdToRoom(Long id) {
        return roomRepository.findById(id).get();
    }

    @Override
    public RoomDto getById(Long id) {
        return roomToRoomDto(roomRepository.findById(id).get());
    }

    @Override
    public RoomDto roomToRoomDto(Room room) {
        return RoomDto.builder().id(room.getId())
                .price(room.getPrice())
                .size(room.getSize())
                .roomType(room.getRoomType())
                .build();
    }
    @Override
    public Room roomDtoToRoom(RoomDto roomDto){
        return Room.builder().id(roomDto.getId())
                .price(roomDto.getPrice())
                .size(roomDto.getSize())
                .roomType(roomDto.getRoomType())
                .build();
    }
}
