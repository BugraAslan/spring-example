package com.example.springrabbitmq.consumer;

import com.example.springrabbitmq.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @RabbitListener(queues = "spring_queue")
    public void consume(Notification Notification) {
        System.out.println("Message received: " + Notification.getMessage().getText());
    }
}
