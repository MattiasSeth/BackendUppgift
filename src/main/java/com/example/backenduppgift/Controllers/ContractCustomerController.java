package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.DTO.ContractCustomerDTO;
import com.example.backenduppgift.DTO.ShipperDto;
import com.example.backenduppgift.Services.ContractCustomerService;
import com.example.backenduppgift.Services.ShipperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/contract-customer")
public class ContractCustomerController {

    private final ContractCustomerService contractCustomerService;

    @RequestMapping("/all")
    public String getContractCustomers(Model model){
        List<ContractCustomerDTO> contractCustomer = contractCustomerService.getAllContractCustomers();
        model.addAttribute("allContractCustomers", contractCustomer);
        return "showAllContractCustomers";
    }
}
