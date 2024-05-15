package com.example.backenduppgift.Services.impl;

import com.example.backenduppgift.DTO.RoomDto;
import com.example.backenduppgift.DTO.RoomEventDTO;
import com.example.backenduppgift.DTO.ShipperDto;
import com.example.backenduppgift.Entities.Room;
import com.example.backenduppgift.Entities.RoomEvent;
import com.example.backenduppgift.Entities.Shipper;
import com.example.backenduppgift.Repositories.RoomEventRepository;
import com.example.backenduppgift.Services.RoomEventService;
import com.example.backenduppgift.Services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomEventServiceImpl implements RoomEventService {

    private final RoomEventRepository roomEventRepository;
    private final RoomService roomService;
    @Override
    public RoomEvent roomEventDTOToRoomEvent(RoomEventDTO roomEventDTO) {
        Room room = roomService.getByIdToRoom(roomEventDTO.roomNo);
        return RoomEvent.builder()
                .room(room)
                .type(roomEventDTO.getType())
                .timeStamp(roomEventDTO.getTimeStamp())
                .cleaningByUser(roomEventDTO.getCleaningByUser())
                .build();
    }
    @Override
    public void addRoomEvent(RoomEvent roomEvent) {
        roomEventRepository.save(roomEvent);
    }
}
