package com.example.backenduppgift.Services;

import com.example.backenduppgift.DTO.BlacklistDto;
import com.example.backenduppgift.DTO.CustomerDto;
import com.example.backenduppgift.Entities.Blacklist;
import com.example.backenduppgift.Entities.Customer;

import java.util.List;

public interface BlacklistService {

    public Blacklist blacklistDtoTOBlacklist(BlacklistDto blacklistDto);

    public void addBlacklistedCustomer(Blacklist blacklist);

    public boolean existsBlacklist (Blacklist blacklist);

    public boolean isEpostInBlacklist(String epost);

    public List<BlacklistDto> getAllBlacklistedCustomers();

    public BlacklistDto blacklistToBlacklistDto(Blacklist blacklist);

    public String addCustomerToBlacklist (BlacklistDto blacklistDto);

    public void deleteCustomerById(Long id);

    public Blacklist getById(Long id);
}
