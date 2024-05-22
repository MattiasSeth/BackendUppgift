package com.example.backenduppgift.Services;

import com.example.backenduppgift.DTO.ContractCustomerDTO;
import com.example.backenduppgift.Entities.ContractCustomer;
import com.example.backenduppgift.Repositories.ContractCustomerRepository;
import com.example.backenduppgift.Services.impl.ContractCustomerServiceImpl;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContractCustomerServiceImplTest {

    @Mock
    private ContractCustomerRepository contractCustomerRepository;

    @InjectMocks
    private ContractCustomerServiceImpl contractCustomerService;

    private ContractCustomerDTO contractCustomerDTO;
    private ContractCustomer contractCustomer;

    @BeforeEach
    void setUp() {
        contractCustomerDTO = ContractCustomerDTO.builder()
                .externalId(1)
                .companyName("Test")
                .contactName("John")
                .contactTitle("Manager")
                .streetAddress("123 Main St")
                .city("Anytown")
                .postalCode(12345)
                .country("Test")
                .phone("1234567890")
                .fax("0987654321")
                .build();

        contractCustomer = ContractCustomer.builder()
                .externalId(1)
                .companyName("Test Company")
                .contactName("John Doe")
                .contactTitle("Manager")
                .streetAddress("123 Main St")
                .city("Anytown")
                .postalCode(12345)
                .country("Test Country")
                .phone("123-456-7890")
                .fax("098-765-4321")
                .build();
    }

    @Test
    void testCustomerXMLDtoToCustomer() {
        ContractCustomer customer = contractCustomerService.customerXMLDtoToCustomer(contractCustomerDTO);
        assertEquals(contractCustomerDTO.getExternalId(), customer.getExternalId());
        assertEquals(contractCustomerDTO.getCompanyName(), customer.getCompanyName());
        assertEquals(contractCustomerDTO.getContactName(), customer.getContactName());
        assertEquals(contractCustomerDTO.getContactTitle(), customer.getContactTitle());
        assertEquals(contractCustomerDTO.getStreetAddress(), customer.getStreetAddress());
        assertEquals(contractCustomerDTO.getCity(), customer.getCity());
        assertEquals(contractCustomerDTO.getPostalCode(), customer.getPostalCode());
        assertEquals(contractCustomerDTO.getCountry(), customer.getCountry());
        assertEquals(contractCustomerDTO.getPhone(), customer.getPhone());
        assertEquals(contractCustomerDTO.getFax(), customer.getFax());
    }

    @Test
    void testAddCustomer() {
        contractCustomerService.addCustomer(contractCustomer);
        verify(contractCustomerRepository, times(1)).save(contractCustomer);
    }

    @Test
    void testExistsCustomerXML() {
        when(contractCustomerRepository.findByExternalId(1)).thenReturn(contractCustomer);
        assertTrue(contractCustomerService.existsCustomerXML(contractCustomer));

        when(contractCustomerRepository.findByExternalId(1)).thenReturn(null);
        assertFalse(contractCustomerService.existsCustomerXML(contractCustomer));
    }

    @Test
    void testGetAllContractCustomers() {
        when(contractCustomerRepository.findAll()).thenReturn(Collections.singletonList(contractCustomer));
        List<ContractCustomerDTO> customers = contractCustomerService.getAllContractCustomers();
        assertEquals(1, customers.size());
        assertEquals(contractCustomer.getCompanyName(), customers.get(0).getCompanyName());
        assertEquals(contractCustomer.getContactName(), customers.get(0).getContactName());
        assertEquals(contractCustomer.getCountry(), customers.get(0).getCountry());
        assertEquals(contractCustomer.getContactTitle(), customers.get(0).getContactTitle());
    }

    @Test
    void testXMLMapping() throws Exception {
        XmlMapper xmlMapper = new XmlMapper();

        try (InputStream inputStream = getClass().getResourceAsStream("/test-contract-customer.xml")) {
            assertNotNull(inputStream, "Input stream should not be null");
            ContractCustomerDTO dtoFromXML = xmlMapper.readValue(inputStream, ContractCustomerDTO.class);
            assertNotNull(dtoFromXML);
            assertEquals(contractCustomerDTO.getExternalId(), dtoFromXML.getExternalId());
            assertEquals(contractCustomerDTO.getCompanyName(), dtoFromXML.getCompanyName());
            assertEquals(contractCustomerDTO.getContactName(), dtoFromXML.getContactName());
            assertEquals(contractCustomerDTO.getContactTitle(), dtoFromXML.getContactTitle());
            assertEquals(contractCustomerDTO.getStreetAddress(), dtoFromXML.getStreetAddress());
            assertEquals(contractCustomerDTO.getCity(), dtoFromXML.getCity());
            assertEquals(contractCustomerDTO.getPostalCode(), dtoFromXML.getPostalCode());
            assertEquals(contractCustomerDTO.getCountry(), dtoFromXML.getCountry());
            assertEquals(contractCustomerDTO.getPhone(), dtoFromXML.getPhone());
            assertEquals(contractCustomerDTO.getFax(), dtoFromXML.getFax());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Exception should not have been thrown: " + e.getMessage());
        }
    }
}
