package com.example.backenduppgift.Services;
import com.example.backenduppgift.DTO.ContractCustomerDTO;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ContractCustomerServiceImplIt {

    @Test
    void testXMLMapping() throws Exception {
        XmlMapper xmlMapper = new XmlMapper();

        try (InputStream inputStream = getClass().getResourceAsStream("/test-contract-customer.xml")) {
            assertNotNull(inputStream, "Input stream should not be null");
            ContractCustomerDTO dtoFromXML = xmlMapper.readValue(inputStream, ContractCustomerDTO.class);
            assertNotNull(dtoFromXML);
            assertEquals(1, dtoFromXML.getExternalId());
            assertEquals("Test", dtoFromXML.getCompanyName());
            assertEquals("John", dtoFromXML.getContactName());
            assertEquals("Manager", dtoFromXML.getContactTitle());
            assertEquals("123 Main St", dtoFromXML.getStreetAddress());
            assertEquals("Anytown", dtoFromXML.getCity());
            assertEquals(12345, dtoFromXML.getPostalCode());
            assertEquals("Test", dtoFromXML.getCountry());
            assertEquals("1234567890", dtoFromXML.getPhone());
            assertEquals("0987654321", dtoFromXML.getFax());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Exception should not have been thrown: " + e.getMessage());
        }
    }
}
