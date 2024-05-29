package com.example.backenduppgift.Services.impl;

import com.example.backenduppgift.DTO.BlacklistDto;
import com.example.backenduppgift.Entities.Blacklist;
import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.Repositories.BlacklistRepository;
import com.example.backenduppgift.Services.BlacklistService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlacklistServiceImpl implements BlacklistService {

    private final BlacklistRepository blacklistRepository;
    @Override
    public Blacklist blacklistDtoTOBlacklist(BlacklistDto blacklistDto) {
        return Blacklist.builder()
                .externalId(blacklistDto.getExternalId())
                .email(blacklistDto.getEmail())
                .name(blacklistDto.getName())
                .created(blacklistDto.getCreated())
                .ok(blacklistDto.getOk())
                .customerGroup(blacklistDto.getCustomerGroup())
                .build();
    }

    @Override
    public void addBlacklistedCustomer(Blacklist blacklist) {
        blacklistRepository.save(blacklist);
    }

    @Override
    public boolean existsBlacklist(Blacklist blacklist) {
        int blacklistExternalId = blacklist.getExternalId();
        blacklistRepository.findByExternalId(blacklistExternalId);

        Optional<Blacklist> blacklistOptional = Optional.ofNullable(blacklistRepository.findByExternalId(blacklistExternalId));
        return blacklistOptional.isPresent();
    }

    @Override
    public boolean isEpostInBlacklist(String epost) {
        List<Blacklist> blacklistRepositoryAll = blacklistRepository.findAll();
        for (Blacklist blacklist : blacklistRepositoryAll) {
            if (blacklist.getEmail()==null)
                continue;
            if (blacklist.getEmail().equals(epost)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<BlacklistDto> getAllBlacklistedCustomers() {
        return blacklistRepository.findAll().stream().map(blacklist->blacklistToBlacklistDto(blacklist)).toList();
    }

    @Override
    public BlacklistDto blacklistToBlacklistDto(Blacklist blacklist) {
        return BlacklistDto.builder()
                .id(blacklist.getId())
                .ok(blacklist.getOk())
                .name(blacklist.getName())
                .created(blacklist.getCreated())
                .email(blacklist.getEmail())
                .externalId(blacklist.getExternalId())
                .customerGroup(blacklist.getCustomerGroup())
                .build();
    }

    @Override
    public String addCustomerToBlacklist(BlacklistDto blacklistDto) {
        blacklistRepository.save(blacklistDtoTOBlacklist(blacklistDto));
        return "Customer saved";
    }

    @Override
    @Transactional
    public void deleteCustomerById(Long id) {
        blacklistRepository.deleteById(id);
    }

    @Override
    public Blacklist getById(Long id) {
        return blacklistRepository.findById(id).get();
    }

    @Override
    public void updateBlacklistedCustomer(BlacklistDto blacklistDto) {
        Blacklist existingBlacklist = blacklistRepository.findById(blacklistDto.getId()).get();
        existingBlacklist.setName(blacklistDto.getName());
        existingBlacklist.setEmail(blacklistDto.getEmail());
        existingBlacklist.setCustomerGroup(blacklistDto.getCustomerGroup());
        existingBlacklist.setOk(blacklistDto.getOk());
        existingBlacklist.setCreated(new Date());

        blacklistRepository.save(existingBlacklist);
    }
}