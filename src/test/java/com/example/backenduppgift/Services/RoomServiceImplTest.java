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
import org.springframework.util.Assert;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomServiceImpl roomService;

    @Test
    void roomToRoomDto() {
        Room room = new Room();
        room.setId(1L);
        room.setPrice(100);
        room.setRoomType("Single");
        room.setSize(1);

        RoomDto roomDto = roomService.roomToRoomDto(room);

        Assert.isTrue(roomDto.getId().equals(room.getId()), "Room id mismatch");
        Assert.isTrue(roomDto.getPrice() == (room.getPrice()), "Room price mismatch");
        Assert.isTrue(roomDto.getRoomType().equals(room.getRoomType()), "Room type mismatch");
        Assert.isTrue(roomDto.getSize() == room.getSize(), "Room size mismatch");
    }

    @Test
    void roomDtoToRoom() {
        RoomDto roomDto = new RoomDto(1L, 100, "Single", 1);
        Room room = roomService.roomDtoToRoom(roomDto);

        Assert.isTrue(room.getId().equals(roomDto.getId()), "Room id mismatch");
        Assert.isTrue(room.getPrice() == (roomDto.getPrice()), "Room price mismatch");
        Assert.isTrue(room.getRoomType().equals(roomDto.getRoomType()), "Room type mismatch");
        Assert.isTrue(room.getSize() == roomDto.getSize(), "Room size mismatch");
    }

    @Test
    void getAllRooms() {
        Room roomOne = new Room();
        roomOne.setId(1L);
        roomOne.setPrice(100);
        roomOne.setRoomType("Single");
        roomOne.setSize(1);

        Room roomTwo = new Room();
        roomTwo.setId(2L);
        roomTwo.setPrice(150);
        roomTwo.setRoomType("Double");
        roomTwo.setSize(2);

        List<Room> rooms = new ArrayList<>();
        rooms.add(roomOne);
        rooms.add(roomTwo);

        when(roomRepository.findAll()).thenReturn(rooms);

        List<RoomDto> allRoomDto = roomService.getAllRooms();
        Assert.isTrue(allRoomDto.size() == 2, "There should be two rooms");
        Assert.isTrue(allRoomDto.get(0).getId().equals(roomOne.getId()), "First room id mismatch");
        Assert.isTrue(allRoomDto.get(1).getId().equals(roomTwo.getId()), "Second room id mismatch");
    }
}