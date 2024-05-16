package com.example.backenduppgift.Services;
import com.example.backenduppgift.DTO.BlacklistDto;
import com.example.backenduppgift.Entities.Blacklist;
import com.example.backenduppgift.Repositories.BlacklistRepository;
import com.example.backenduppgift.Services.impl.BlacklistServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BlacklistServiceImplTest {

    @Mock
    private BlacklistRepository blacklistRepository;

    @InjectMocks
    private BlacklistServiceImpl blacklistService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBlacklistDtoTOBlacklist() {
        // Arrange
        BlacklistDto blacklistDto = new BlacklistDto();
        blacklistDto.setName("Albin");
        blacklistDto.setEmail("albin@karl.com");
        blacklistDto.setCreated(new Date());
        blacklistDto.setOk(true);
        blacklistDto.setCustomerGroup("Test Group");

        // Act
        Blacklist blacklist = blacklistService.blacklistDtoTOBlacklist(blacklistDto);

        // Assert
        assertNotNull(blacklist);
        assertEquals(blacklistDto.getName(), blacklist.getName());
        assertEquals(blacklistDto.getEmail(), blacklist.getEmail());
        assertEquals(blacklistDto.getCreated(), blacklist.getCreated());
        assertEquals(blacklistDto.getOk(), blacklist.getOk());
        assertEquals(blacklistDto.getCustomerGroup(), blacklist.getCustomerGroup());
    }

    @Test
    public void testAddBlacklistedCustomer() {
        Blacklist blacklist = new Blacklist();
        blacklistService.addBlacklistedCustomer(blacklist);
        verify(blacklistRepository, times(1)).save(blacklist);
    }

    @Test
    public void testExistsBlacklist() {
        Blacklist blacklist = new Blacklist();
        blacklist.setExternalId(123);
        when(blacklistRepository.findByExternalId(123)).thenReturn(blacklist);
        boolean exists = blacklistService.existsBlacklist(blacklist);
        assertTrue(exists);
    }

    @Test
    public void testIsEpostInBlacklist() {
        // Arrange
        String email = "albin@karl.com";
        List<Blacklist> blacklistRepositoryAll = new ArrayList<>();
        Blacklist blacklist = new Blacklist();
        blacklist.setEmail(email);
        blacklistRepositoryAll.add(blacklist);
        when(blacklistRepository.findAll()).thenReturn(blacklistRepositoryAll);
        boolean isInBlacklist = blacklistService.isEpostInBlacklist(email);
        assertTrue(isInBlacklist);
    }
}
