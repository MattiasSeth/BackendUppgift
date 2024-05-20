package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.DTO.ContractCustomerDTO;
import com.example.backenduppgift.DTO.ShipperDto;
import com.example.backenduppgift.Entities.ContractCustomer;
import com.example.backenduppgift.Repositories.ContractCustomerRepository;
import com.example.backenduppgift.Services.ContractCustomerService;
import com.example.backenduppgift.Services.ShipperService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/contract-customer")
public class ContractCustomerController {

    private final ContractCustomerService contractCustomerService;
    private final ContractCustomerRepository csr;


    @RequestMapping("/all")
    public String getContractCustomers(Model model,
                                       @RequestParam(defaultValue = "companyName") String sortBy,
                                       @RequestParam(defaultValue = "asc") String sortOrder,
                                       @RequestParam(required = false) String searchWord,
                                       @RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "25") int size
    ){

        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        List<ContractCustomer> contractCustomers = csr.findAll(sort);
        Page<ContractCustomer> clientsPage;
        clientsPage = csr.findAll(pageable);


        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("clientsPage", clientsPage);
        model.addAttribute("contractCustomers", contractCustomers);
        model.addAttribute("searchWord", searchWord);

        return "showAllContractCustomers";

    }



    @GetMapping("/search")
    public String searchCompany(Model model,
                                       @RequestParam(defaultValue = "companyName") String sortBy,
                                       @RequestParam(defaultValue = "asc") String sortOrder,
                                       @RequestParam String searchWord,
                                       @RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "25") int size
    ){



        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        List<ContractCustomer> companyMatches = contractCustomerService.searchCompanies(searchWord, sort);

        int start = Math.min((page - 1) * size, companyMatches.size());
        int end = Math.min(start + size, companyMatches.size());
        List<ContractCustomer> pageResults = companyMatches.subList(start, end);


        Page<ContractCustomer> clientsPage = new PageImpl<>(pageResults, pageable, companyMatches.size());

        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("clientsPage", clientsPage);
        model.addAttribute("searchWord", searchWord);

        return "search-result";

    }
    @GetMapping("/all/{id}")
    public String showSpecificCustomer(@PathVariable  Long id, Model model) {
        ContractCustomer contractCustomer = contractCustomerService.getCustomerById(id);
        model.addAttribute("contractCustomer", contractCustomer);
        return "company-info.html";
    }
}
