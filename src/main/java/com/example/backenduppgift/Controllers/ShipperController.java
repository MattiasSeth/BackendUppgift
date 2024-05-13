package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.DTO.ShipperDto;
import com.example.backenduppgift.Services.ShipperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/shipper")
public class ShipperController {

    private final ShipperService shipperService;

    @RequestMapping("/all")
    public String getAllShippers(Model model){
        List<ShipperDto> shippers = shipperService.getAllShippers();
        model.addAttribute("allShippers", shippers);
        return "showAllShippers";
    }
}
