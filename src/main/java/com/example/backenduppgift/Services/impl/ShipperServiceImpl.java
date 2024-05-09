package com.example.backenduppgift.Services.impl;

import com.example.backenduppgift.DTO.ShipperDto;
import com.example.backenduppgift.Entities.Shipper;
import com.example.backenduppgift.Repositories.ShipperRepository;
import com.example.backenduppgift.Services.ShipperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShipperServiceImpl implements ShipperService {

    private final ShipperRepository shipperRepository;
    @Override
    public Shipper shipperDtoToShipper(ShipperDto shipperDto) { //. Id, CompanyName and Phone
        return Shipper.builder().externalId(shipperDto.getExternalId())
                .phone(shipperDto.getPhone())
                .companyName(shipperDto.getCompanyName())
                .build();
    }

    @Override
    public void addShipper(Shipper shipper) {
        shipperRepository.save(shipper);
    }

    @Override
    public boolean existsShipper(Shipper shipper) {
        int shipperExternalId = shipper.getExternalId();
        shipperRepository.findByExternalId(shipperExternalId);

        Optional<Shipper> shipperOptional = Optional.ofNullable(shipperRepository.findByExternalId(shipperExternalId));
        return shipperOptional.isPresent();
    }

}
