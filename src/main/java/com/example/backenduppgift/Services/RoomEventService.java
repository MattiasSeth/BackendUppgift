package com.example.backenduppgift.Services;

import com.example.backenduppgift.DTO.RoomEventDTO;
import com.example.backenduppgift.Entities.RoomEvent;

import java.util.List;

public interface RoomEventService {

    public RoomEvent roomEventDTOToRoomEvent(RoomEventDTO roomEventDTO);

    public void addRoomEvent(RoomEvent roomEvent);

    public List<RoomEventDTO> getRoomEventWithRoomId(long roomId);
    public RoomEventDTO roomEventToRoomEventDTO(RoomEvent roomEvent);
}
