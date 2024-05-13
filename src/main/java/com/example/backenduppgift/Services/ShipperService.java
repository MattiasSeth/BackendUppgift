package com.example.backenduppgift.Services;

import com.example.backenduppgift.DTO.CustomerDto;
import com.example.backenduppgift.DTO.ShipperDto;
import com.example.backenduppgift.Entities.Shipper;

import java.util.List;

public interface ShipperService {

    public Shipper shipperDtoToShipper(ShipperDto shipperDto);
    public ShipperDto shipperToShipperDto(Shipper shipper);

    public void addShipper(Shipper shipper);

    public boolean existsShipper (Shipper shipper);

    public List<ShipperDto> getAllShippers();
}
