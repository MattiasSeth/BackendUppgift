package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.DTO.BlacklistDto;
import com.example.backenduppgift.DTO.CustomerDto;
import com.example.backenduppgift.Entities.Blacklist;
import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.Services.BlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    @RequestMapping("/add")
    public String addCustomerToBlacklist(){
        return "addBlacklist.html";
    }

    @PostMapping("/addDone")
    public String addCustomerDone(@RequestParam String name, @RequestParam String email,
                                  @RequestParam String customerGroup,
                                  @RequestParam boolean ok,
                                  Model model){
        BlacklistDto customer = new BlacklistDto();
        customer.setName(name);
        customer.setEmail(email);
        customer.setCustomerGroup(customerGroup);
        customer.setOk(ok);
        customer.setCreated(new Date());
        blacklistService.addCustomerToBlacklist(customer);

        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("customerGroup", customerGroup);
        model.addAttribute("ok", ok);
        return "redirect:/blacklist/all";
    }



    @RequestMapping("/delete")
    public String getAllWithDelete (Model model) {
        List<BlacklistDto> customers = blacklistService.getAllBlacklistedCustomers();
        model.addAttribute("allCustomers", customers);
        model.addAttribute("customerTitle", "All Blacklisted customers");
        return "redirect:/blacklist/all";
    }

    @RequestMapping(path = "/deleteById/{id}")
    public String deleteCap(@PathVariable Long id, Model model) {
            blacklistService.deleteCustomerById(id);
        return getAllWithDelete(model);
    }

    @RequestMapping(path = "/edit/{id}")
    public String editName (@PathVariable Long id, Model model){
        Blacklist customer = blacklistService.getById(id);
        model.addAttribute("Blacklist", customer);
        return "editBlacklistForm";
    }

    @PostMapping("/update")
    public String updateCustomerName(Model model, BlacklistDto blacklistDto){
        blacklistDto.setCreated(new Date());
        blacklistService.addCustomerToBlacklist(blacklistDto);

        List<BlacklistDto> customers = blacklistService.getAllBlacklistedCustomers();
        model.addAttribute("allCustomers", customers);
        model.addAttribute("customerTitle", "All blacklisted Customers");
        model.addAttribute("addCustomer", "Add Customers");
        return "showAllBlacklistedCustomers";
    }


}
