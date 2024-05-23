package com.example.backenduppgift;

import com.example.backenduppgift.Configurations.IntegrationProperties;
import com.example.backenduppgift.DTO.ShipperDto;
import com.example.backenduppgift.Entities.Shipper;
import com.example.backenduppgift.Services.ShipperService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
@RequiredArgsConstructor
@ComponentScan
public class FetchShippers implements CommandLineRunner {

    private final ShipperService shipperService;

    @Autowired
    IntegrationProperties properties;

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String url = properties.getShipper().getUrl();

        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, ShipperDto.class);
        List<ShipperDto> shippersList = objectMapper.readValue(new URL(url),
                collectionType);
        System.out.println("Fetching shippers");
        System.out.println(url);

        for (int i=0; i<shippersList.size(); i++){
            Shipper tempShipper = shipperService.shipperDtoToShipper(shippersList.get(i));
            if (!shipperService.existsShipper(tempShipper)) {
                shipperService.addShipper(tempShipper);
            }
        }
    }
}
