package com.example.backenduppgift.Services;

import com.example.backenduppgift.DTO.RoomDto;
import com.example.backenduppgift.Entities.Room;
import com.example.backenduppgift.Repositories.RoomRepository;
import com.example.backenduppgift.Services.impl.RoomServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RoomServiceImplTest {
    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomServiceImpl roomService = new RoomServiceImpl(roomRepository);

    @Test
    void roomToRoomDto() {
        Room room = new Room();
        room.setId(1l);
        room.setRoomType("Single");
        room.setSize(1);

        RoomDto roomDto = roomService.roomToRoomDto(room);

        Assert.isTrue(roomDto.getId().equals(room.getId()), "Room id mismatch");
        Assert.isTrue(roomDto.getRoomType().equals(room.getRoomType()), "Room type mismatch");
        Assert.isTrue(roomDto.getSize() == room.getSize(), "Room size mismatch");
    }

    @Test
    void getAllRooms() throws Exception {
        Room roomOne = new Room();
        roomOne.setId(1L);
        roomOne.setRoomType("Single");
        roomOne.setSize(1);

        Room roomTwo = new Room();
        roomTwo.setId(2L);
        roomTwo.setRoomType("Single");
        roomTwo.setSize(1);

        List<Room> rooms = new ArrayList<>();
        rooms.add(roomOne);
        rooms.add(roomTwo);

        when(roomRepository.findAll()).thenReturn(rooms);
        RoomServiceImpl roomServiceTwo = new RoomServiceImpl(roomRepository);

        List<RoomDto> allRoomDto = roomServiceTwo.getAllRooms();
        Assert.isTrue(allRoomDto.size() == 2, "There should be two rooms");
    }

}
