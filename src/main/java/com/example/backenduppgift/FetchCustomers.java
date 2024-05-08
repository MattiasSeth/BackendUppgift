package com.example.backenduppgift;

import com.example.backenduppgift.DTO.CustomerListXMLDto;
import com.example.backenduppgift.DTO.CustomerXMLDto;
import com.example.backenduppgift.Entities.CustomerXML;
import com.example.backenduppgift.Entities.Shipper;
import com.example.backenduppgift.Services.CustomerXMLService;
import com.example.backenduppgift.Services.ShipperService;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.net.URL;
import java.util.List;
@RequiredArgsConstructor
@ComponentScan
public class FetchCustomers implements CommandLineRunner {

    private final CustomerXMLService customerXMLService;


    @Override
    public void run(String... args) throws Exception {
        try {
            JacksonXmlModule module = new JacksonXmlModule();
            module.setDefaultUseWrapper(false);
            XmlMapper xmlMapper = new XmlMapper(module);
            System.out.println("Before reading XML data");
            CustomerListXMLDto customerListXMLDto = xmlMapper.readValue(new URL("https://javaintegration.systementor.se/customers"),
                    CustomerListXMLDto.class);

            System.out.println("After reading XML data");

            // Access the list of customer DTOs and iterate over them
            List<CustomerXMLDto> customerList = customerListXMLDto.getCustomerXMLDtoList();

            for (int i=0; i<customerList.size(); i++){
                CustomerXML tempCustomerXML = customerXMLService.CustomerXMLDtoToCustomer(customerList.get(i));
                if (!customerXMLService.existsCustomerXML(tempCustomerXML)) {
                    customerXMLService.addCustomer(tempCustomerXML);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
