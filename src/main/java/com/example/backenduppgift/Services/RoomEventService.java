package com.example.backenduppgift.Services;

import com.example.backenduppgift.DTO.RoomEventDTO;
import com.example.backenduppgift.Entities.RoomEvent;

public interface RoomEventService {

    public RoomEvent roomEventDTOToRoomEvent(RoomEventDTO roomEventDTO);

    public void addRoomEvent(RoomEvent roomEvent);
}
