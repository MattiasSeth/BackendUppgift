package com.example.backenduppgift;

import com.example.backenduppgift.DTO.BlacklistDto;
import com.example.backenduppgift.DTO.ShipperDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.CommandLineRunner;

import java.net.URL;
import java.util.List;

public class FetchBlacklist implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, BlacklistDto.class);
        List<BlacklistDto> blacklistList = objectMapper.readValue(new URL("https://javabl.systementor.se/api/stefan/blacklist"),
                collectionType);

        for (BlacklistDto blacklistDto : blacklistList) {
            System.out.println(blacklistDto.getId());
            System.out.println(blacklistDto.getName());
            System.out.println(blacklistDto.getGroup());
            System.out.println(blacklistDto.getEmail());
            System.out.println(blacklistDto.getCreated());
            System.out.println(blacklistDto.ok);
        }
    }
}
