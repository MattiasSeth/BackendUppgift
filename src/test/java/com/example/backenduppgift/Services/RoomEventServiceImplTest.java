package com.example.backenduppgift.Services;
import com.example.backenduppgift.DTO.RoomEventDTO;
import com.example.backenduppgift.Entities.Room;
import com.example.backenduppgift.Entities.RoomEvent;
import com.example.backenduppgift.Repositories.RoomEventRepository;
import com.example.backenduppgift.Services.impl.RoomEventServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;

public class RoomEventServiceImplTest {

    @Mock
    private RoomEventRepository roomEventRepository;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomEventServiceImpl roomEventService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRoomEventDTOToRoomEvent() {
        // Mock data
        RoomEventDTO roomEventDTO = new RoomEventDTO();
        roomEventDTO.setRoomNo(1L);
        roomEventDTO.setType("Cleaning");
        Room room = new Room();
        when(roomService.getByIdToRoom(anyLong())).thenReturn(room);
        RoomEvent roomEvent = roomEventService.roomEventDTOToRoomEvent(roomEventDTO);
        assertSame(room, roomEvent.getRoom());
        assertEquals("Cleaning", roomEvent.getType());
    }

    @Test
    public void testAddRoomEvent() {
        RoomEvent roomEvent = new RoomEvent();
        roomEventService.addRoomEvent(roomEvent);
        verify(roomEventRepository, times(1)).save(roomEvent);
    }

    @Test
    public void testGetRoomEventWithRoomId() {
        RoomEvent roomEvent1 = new RoomEvent();
        RoomEvent roomEvent2 = new RoomEvent();
        when(roomEventRepository.findRoomEventByRoomId(1L)).thenReturn(Arrays.asList(roomEvent1, roomEvent2));
        List<RoomEventDTO> roomEventDTOs = roomEventService.getRoomEventWithRoomId(1L);
        assertEquals(2, roomEventDTOs.size());
    }

    @Test
    public void testRoomEventToRoomEventDTO() {
        RoomEvent roomEvent = new RoomEvent();
        roomEvent.setCleaningByUser("User");
        roomEvent.setType("Cleaning");
        RoomEventDTO roomEventDTO = roomEventService.roomEventToRoomEventDTO(roomEvent);
        assertEquals("User", roomEventDTO.getCleaningByUser());
        assertEquals("Cleaning", roomEventDTO.getType());
    }
}
