package com.example.springrabbitmq.producer;

import com.example.springrabbitmq.model.Notification;
import com.example.springrabbitmq.model.NotificationMessage;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
public class NotificationProducer{

    @Value("${spring-rabbitmq.rabbit.routing.name}")
    private String routingName;

    @Value("${spring-rabbitmq.rabbit.routing.name}")
    private String exchangeName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setId(1);
        notificationMessage.setCreatedAt(new Date());
        notificationMessage.setTo("bugra");
        notificationMessage.setFrom("morwoss");
        notificationMessage.setText("hello");

        Notification notification = new Notification();
        notification.setId(1);
        notification.setCreatedAt(new Date());
        notification.setMessage(notificationMessage);

        this.sendToQueue(notification);
    }

    public void sendToQueue(Notification notification) {
        try {
            rabbitTemplate.convertAndSend(this.exchangeName, this.routingName, notification);
            System.out.println("Message produced: " + notification.getMessage().getText());
        } catch (AmqpException amqpException) {
            System.out.println(amqpException.getMessage());
        }
    }
}
