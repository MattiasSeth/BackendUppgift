package com.example.backenduppgift.Services;

import com.example.backenduppgift.DTO.BlacklistDto;
import com.example.backenduppgift.Entities.Blacklist;

public interface BlacklistService {

    public Blacklist blacklistDtoTOBlacklist(BlacklistDto blacklistDto);

    public void addBlacklistedCustomer(Blacklist blacklist);

    public boolean existsBlacklist (Blacklist blacklist);

    public boolean isEpostInBlacklist(String epost);
}
