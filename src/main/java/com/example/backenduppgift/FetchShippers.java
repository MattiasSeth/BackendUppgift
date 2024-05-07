package com.example.backenduppgift;

import com.example.backenduppgift.DTO.Shipper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.net.URL;
import java.util.List;

@ComponentScan
public class FetchShippers implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Shipper.class);
        List<Shipper> shippersList = objectMapper.readValue(new URL("https://javaintegration.systementor.se/shippers"),
                collectionType);

        for (Shipper shipper : shippersList) {
            System.out.println(shipper.contactName);
        }
    }
}
