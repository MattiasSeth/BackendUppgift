package com.example.backenduppgift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
}
