package com.example.backenduppgift;

import com.example.backenduppgift.Configurations.IntegrationProperties;
import com.example.backenduppgift.Security.UserDataSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Objects;

//
@SpringBootApplication
public class BackendUppgiftApplication {

    @Autowired
    private UserDataSeeder userDataSeeder;


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
        }else if (Objects.equals(args[0], "fetchcustomers")){
            SpringApplication application = new SpringApplication(FetchCustomers.class);
            application.setWebApplicationType(WebApplicationType.NONE);
            application.run(args);
        }else if (Objects.equals(args[0], "fetchqueue")){
            SpringApplication application = new SpringApplication(FetchQueue.class);
            application.setWebApplicationType(WebApplicationType.NONE);
            application.run(args);
        }
    }
    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            userDataSeeder.Seed();
        };
    }
}
