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

            Customer c1 = new Customer("Seth");
            Room r1 = new Room("Double",2);

            customerRepository.save(c1);
            roomRepository.save(r1);

            LocalDate startDate = LocalDate.of(2024, 4, 22);
            LocalDate endDate = LocalDate.of(2024, 4, 30);
            bookingRepository.save(new Booking(c1,r1,0,startDate,endDate));
        };
    }
}
