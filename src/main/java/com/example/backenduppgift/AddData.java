package com.example.backenduppgift;

import com.example.backenduppgift.Entities.Booking;
import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.Entities.Room;
import com.example.backenduppgift.Repositories.BookingRepository;
import com.example.backenduppgift.Repositories.CustomerRepository;
import com.example.backenduppgift.Repositories.RoomEventRepository;
import com.example.backenduppgift.Repositories.RoomRepository;
import com.example.backenduppgift.Security.UserDataSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;

@ComponentScan
public class AddData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final RoomEventRepository roomEventRepository;

    public AddData(CustomerRepository customerRepository, RoomRepository roomRepository, BookingRepository bookingRepository, RoomEventRepository roomEventRepository) {
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
        this.roomEventRepository = roomEventRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Customers
        Customer c1 = new Customer("Seth","Seth@yh.nackademin.com");
        Customer c2 = new Customer("Gustav","Gustav@yh.nackademin.com");
        Customer c3 = new Customer("Edgar", "Edgar@yh.nackademin.com");
        Customer c4 = new Customer("Hadi", "Hadi@yh.nackademin.com");
        Customer c5 = new Customer("Nadia", "Nadia@yh.nackademin.com");
        customerRepository.save(c1);
        customerRepository.save(c2);
        customerRepository.save(c3);
        customerRepository.save(c4);
        customerRepository.save(c5);

        // Rooms
        Room r1 = new Room("Single",0,700);
        Room r2 = new Room("Single",0,925);
        Room r3 = new Room("Single",0,850);
        Room r4 = new Room("Single",0,1015);
        Room r5 = new Room("Double",1,1750);
        Room r6 = new Room("Double",1,1225);
        Room r7 = new Room("Double",1,2000);
        Room r8 = new Room("Double",2,1450);
        Room r9 = new Room("Double",2,1275);
        Room r10 = new Room("Double",2,975);

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
        bookingRepository.save(new Booking(c1,r1,0,startDate,endDate, 10000));
        bookingRepository.save(new Booking(c2,r2,0,startDate,endDate, 20000));

        // Queues

    }
}
