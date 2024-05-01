package com.example.backenduppgift;

import com.example.backenduppgift.Entities.Booking;
import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.Entities.Room;
import com.example.backenduppgift.Repositories.BookingRepository;
import com.example.backenduppgift.Repositories.CustomerRepository;
import com.example.backenduppgift.Repositories.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
//
@SpringBootApplication
public class BackendUppgiftApplication {

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
}
