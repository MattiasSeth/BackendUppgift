package com.example.backenduppgift;

import com.example.backenduppgift.DTO.BlacklistDto;
import com.example.backenduppgift.Entities.Blacklist;
import com.example.backenduppgift.Services.BlacklistService;
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
public class FetchBlacklist implements CommandLineRunner {

    private final BlacklistService blacklistService;
    @Override
    public void run(String... args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, BlacklistDto.class);
        List<BlacklistDto> blacklistList = objectMapper.readValue(new URL("https://javabl.systementor.se/api/stefan/blacklist"),
                collectionType);
        System.out.println("Fetching blacklist");

        for (int i=0; i<blacklistList.size(); i++){
            Blacklist tempBlacklist = blacklistService.blacklistDtoTOBlacklist(blacklistList.get(i));
            if (!blacklistService.existsBlacklist(tempBlacklist)) {
                blacklistService.addBlacklistedCustomer(tempBlacklist);
            }
        }
    }
}
