package com.example.backenduppgift;

import com.example.backenduppgift.DTO.RoomEventDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

@ComponentScan
public class ReadQueueApp implements CommandLineRunner {


    private String queueName = "b443c2b1-a8d5-45ae-ad50-4971d469537b";
    @Override
    public void run(String... args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("128.140.81.47");
        factory.setUsername("djk47589hjkew789489hjf894");
        factory.setPassword("sfdjkl54278frhj7");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            RoomEventDTO roomEvent = mapper.readValue(message, RoomEventDTO.class);

            //System.out.println(" [x] Received '" + message + "'");

            System.out.println(roomEvent.getRoomNo());
            System.out.println(roomEvent.getTimeStamp());
            System.out.println(roomEvent.getType());
            System.out.println(roomEvent.getCleaningByUser());



            // https://www.baeldung.com/jackson-annotations#bd-jackson-polymorphic-type-handling-annotations
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });

    }

}