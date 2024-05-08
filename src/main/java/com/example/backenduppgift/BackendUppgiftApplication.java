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
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

//
@SpringBootApplication
public class BackendUppgiftApplication {

    public static void main(String[] args) {
        if (args.length == 0) {
            SpringApplication.run(BackendUppgiftApplication.class, args);
        }else if (Objects.equals(args[0], "fetchshippers")){
            SpringApplication application = new SpringApplication(FetchShippers.class);
            application.setWebApplicationType(WebApplicationType.NONE);
            application.run(args);
        }else if (Objects.equals(args[0], "adddata")){
            SpringApplication application = new SpringApplication(AddData.class);
            application.setWebApplicationType(WebApplicationType.NONE);
            application.run(args);
        }else if (Objects.equals(args[0], "fetchblacklist")){
            SpringApplication application = new SpringApplication(FetchBlacklist.class);
            application.setWebApplicationType(WebApplicationType.NONE);
            application.run(args);
        }
    }
}
