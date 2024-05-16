package com.example.backenduppgift.Services;

import com.example.backenduppgift.DTO.ShipperDto;
import com.example.backenduppgift.Entities.Shipper;
import com.example.backenduppgift.Repositories.ShipperRepository;
import com.example.backenduppgift.Services.impl.ShipperServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ShipperServiceImplTest {

    @Mock
    private ShipperRepository shipperRepository;

    private ShipperServiceImpl shipperService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        shipperService = new ShipperServiceImpl(shipperRepository);
    }

    @Test
    void shipperDtoToShipper() {
        ShipperDto shipperDto = new ShipperDto();
        Shipper shipper = shipperService.shipperDtoToShipper(shipperDto);
        assertEquals(shipperDto.getExternalId(), shipper.getExternalId());
    }

    @Test
    void shipperToShipperDto() {
        Shipper shipper = new Shipper();
        ShipperDto shipperDto = shipperService.shipperToShipperDto(shipper);
        assertEquals(shipper.getExternalId(), shipperDto.getExternalId());
    }

    @Test
    void addShipper() {
        Shipper shipper = new Shipper();
        shipperService.addShipper(shipper);
        verify(shipperRepository, times(1)).save(shipper);
    }

    @Test
    void existsShipperExists() {
        int externalId = 1;
        Shipper shipper = new Shipper();
        shipper.setExternalId(externalId);
        when(shipperRepository.findByExternalId(externalId)).thenReturn(shipper);
        boolean exists = shipperService.existsShipper(shipper);
        assertTrue(exists);
    }

    @Test
    void existsShipperDoesNotExist() {
        int externalId = 1;
        when(shipperRepository.findByExternalId(externalId)).thenReturn(null);
        boolean exists = shipperService.existsShipper(new Shipper());
        assertTrue(!exists);
    }

    @Test
    void getAllShippers() {
        Shipper shipper1 = new Shipper();
        Shipper shipper2 = new Shipper();

        List<Shipper> shippers = Arrays.asList(shipper1, shipper2);
        when(shipperRepository.findAll()).thenReturn(shippers);
        List<ShipperDto> shipperDtos = shipperService.getAllShippers();
        assertEquals(2, shipperDtos.size());

    }
}
