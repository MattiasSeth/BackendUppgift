package com.example.backenduppgift;

import com.example.backenduppgift.DTO.ContractCustomerListDTO;
import com.example.backenduppgift.DTO.ContractCustomerDTO;
import com.example.backenduppgift.Entities.ContractCustomer;
import com.example.backenduppgift.Services.ContractCustomerService;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.net.URL;
import java.util.List;
@RequiredArgsConstructor
@ComponentScan
public class FetchCustomers implements CommandLineRunner {

    private final ContractCustomerService contractCustomerService;


    @Override
    public void run(String... args) throws Exception {
        try {
            JacksonXmlModule module = new JacksonXmlModule();
            module.setDefaultUseWrapper(false);
            XmlMapper xmlMapper = new XmlMapper(module);
            System.out.println("Before reading XML data");
            ContractCustomerListDTO contractCustomerListDTO = xmlMapper.readValue(new URL("https://javaintegration.systementor.se/customers"),
                    ContractCustomerListDTO.class);

            System.out.println("After reading XML data");

            // Access the list of customer DTOs and iterate over them
            List<ContractCustomerDTO> customerList = contractCustomerListDTO.getContractCustomerDTOList();

            for (int i=0; i<customerList.size(); i++){
                ContractCustomer tempContractCustomer = contractCustomerService.customerXMLDtoToCustomer(customerList.get(i));
                if (!contractCustomerService.existsCustomerXML(tempContractCustomer)) {
                    contractCustomerService.addCustomer(tempContractCustomer);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
