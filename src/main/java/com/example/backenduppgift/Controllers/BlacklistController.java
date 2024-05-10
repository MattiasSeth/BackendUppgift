package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.DTO.BlacklistDto;
import com.example.backenduppgift.DTO.CustomerDto;
import com.example.backenduppgift.Services.BlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/blacklist")
public class BlacklistController {

    private final BlacklistService blacklistService;

    @RequestMapping("/all")
    public String getAllCustomers(Model model){
        List<BlacklistDto> blacklistedCustomers = blacklistService.getAllBlacklistedCustomers();
        model.addAttribute("allCustomers", blacklistedCustomers);
        model.addAttribute("customerTitle", "All Blacklisted customers");
        model.addAttribute("addCustomer", "Add customer");
        return "showAllBlacklistedCustomers";
    }


}
