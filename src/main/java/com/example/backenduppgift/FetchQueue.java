package com.example.backenduppgift;

import com.example.backenduppgift.DTO.RoomEventDTO;
import com.example.backenduppgift.Entities.RoomEvent;
import com.example.backenduppgift.Services.RoomEventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

@RequiredArgsConstructor
@ComponentScan
public class FetchQueue implements CommandLineRunner {

    private final RoomEventService roomEventService;

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
            System.out.println("Message received: " + message);
            RoomEventDTO roomEvent = mapper.readValue(message, RoomEventDTO.class);

            RoomEvent tempRoomEvent = roomEventService.roomEventDTOToRoomEvent(roomEvent);
            roomEventService.addRoomEvent(tempRoomEvent);
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });

    }

}