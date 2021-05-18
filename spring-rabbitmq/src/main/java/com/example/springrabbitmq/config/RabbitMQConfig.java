package com.example.springrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // TODO
    //  @Value >>> management in application.properties
    //  @Bean >>> spring boot ioc container

    @Value("${spring-rabbitmq.rabbit.queue.name}")
    private String queueName;

    @Value("${spring-rabbitmq.rabbit.routing.name}")
    private String routingName;

    @Value("${spring-rabbitmq.rabbit.exchange.name}")
    private String exchangeName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public Queue queue() { // TODO create queue
        return new Queue(this.queueName, true);
    }

    @Bean
    public DirectExchange directExchange() { // TODO create exchange
        return new DirectExchange(this.exchangeName);
    }

    @Bean
    public Binding binding(final Queue queue, final DirectExchange directExchange) { // TODO create binding key
        return BindingBuilder.bind(queue).to(directExchange).with(this.routingName);
    }
}
