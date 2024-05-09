package com.example.backenduppgift.Services;

import com.example.backenduppgift.DTO.ShipperDto;
import com.example.backenduppgift.Entities.Shipper;

public interface ShipperService {

    public Shipper shipperDtoToShipper(ShipperDto shipperDto);

    public void addShipper(Shipper shipper);

    public boolean existsShipper (Shipper shipper);

}
