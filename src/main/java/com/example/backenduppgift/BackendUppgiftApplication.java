package com.example.backenduppgift;

import com.example.backenduppgift.Entities.Booking;
import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.Entities.Room;
import com.example.backenduppgift.Repositories.BookingRepository;
import com.example.backenduppgift.Repositories.CustomerRepository;
import com.example.backenduppgift.Repositories.RoomRepository;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;

//
@SpringBootApplication
public class BackendUppgiftApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(BackendUppgiftApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo (CustomerRepository customerRepository, RoomRepository roomRepository,
                                   BookingRepository bookingRepository){
        return (args ) -> {

            // Customers
            Customer c1 = new Customer("Seth");
            Customer c2 = new Customer("Gustav");
            Customer c3 = new Customer("Edgar");
            Customer c4 = new Customer("Hadi");
            Customer c5 = new Customer("Nadia");
            customerRepository.save(c1);
            customerRepository.save(c2);
            customerRepository.save(c3);
            customerRepository.save(c4);
            customerRepository.save(c5);

            // Rooms
            Room r1 = new Room("Single",0);
            Room r2 = new Room("Single",0);
            Room r3 = new Room("Single",0);
            Room r4 = new Room("Single",0);
            Room r5 = new Room("Double",1);
            Room r6 = new Room("Double",1);
            Room r7 = new Room("Double",1);
            Room r8 = new Room("Double",2);
            Room r9 = new Room("Double",2);
            Room r10 = new Room("Double",2);

            roomRepository.save(r1);
            roomRepository.save(r2);
            roomRepository.save(r3);
            roomRepository.save(r4);
            roomRepository.save(r5);
            roomRepository.save(r6);
            roomRepository.save(r7);
            roomRepository.save(r8);
            roomRepository.save(r9);
            roomRepository.save(r10);

            // Dates
            LocalDate startDate = LocalDate.of(2022, 4, 22);
            LocalDate endDate = LocalDate.of(2025, 4, 30);

            // Bookings
            bookingRepository.save(new Booking(c1,r1,0,startDate,endDate));
            bookingRepository.save(new Booking(c2,r2,0,startDate,endDate));
        };
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            JacksonXmlModule module = new JacksonXmlModule();
            module.setDefaultUseWrapper(false);
            XmlMapper xmlMapper = new XmlMapper(module);
            System.out.println("Before reading XML data");
            Catalog catalog = xmlMapper.readValue(new URL("https://axmjqhyyjpat.objectstorage.eu-amsterdam-1.oci.customer-oci.com/n/axmjqhyyjpat/b/aspcodeprod/o/books.xml"),
                    Catalog.class);

            System.out.println("After reading XML data");

            List<Book> books = catalog.book;
            for (Book book : books) {
                System.out.println("Author: " + book.author);
                System.out.println("Title: " + book.title);
                System.out.println("Genre: " + book.category);
                System.out.println("Price: " + book.price);
                System.out.println("Publish Date: " + book.publish_date);
                System.out.println("Description: " + book.description);
                System.out.println("ID: " + book.id);
                System.out.println("Text: " + book.text);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
