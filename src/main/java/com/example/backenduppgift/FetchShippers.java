package com.example.backenduppgift;

import com.example.backenduppgift.DTO.ShipperDto;
import com.example.backenduppgift.Entities.Shipper;
import com.example.backenduppgift.Services.ShipperService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.net.URL;
import java.util.List;
@RequiredArgsConstructor
@ComponentScan
public class FetchShippers implements CommandLineRunner {

    private final ShipperService shipperService;

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, ShipperDto.class);
        List<ShipperDto> shippersList = objectMapper.readValue(new URL("https://javaintegration.systementor.se/shippers"),
                collectionType);

        for (int i=0; i<shippersList.size(); i++){
            Shipper tempShipper = shipperService.ShipperDtoToShipper(shippersList.get(i));
            if (!shipperService.existsShipper(tempShipper)) {
                shipperService.addShipper(tempShipper);
            }
        }
    }
}
