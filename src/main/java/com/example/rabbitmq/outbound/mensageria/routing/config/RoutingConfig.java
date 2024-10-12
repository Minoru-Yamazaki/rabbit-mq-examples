package com.example.rabbitmq.outbound.mensageria.routing.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.rabbitmq.outbound.mensageria.routing")
public class RoutingConfig {

    @Value("${rabbitmq.routing.queue.error.name}")
    private String errorQueue;

    @Value("${rabbitmq.routing.queue.info.name}")
    private String infoQueue;

    @Value("${rabbitmq.routing.queue.warning.name}")
    private String warningQueue;

    @Value("${rabbitmq.routing.direct-exchange.name}")
    private String directExchange;

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(directExchange);
    }

    @Bean
    public Queue errorQueue() {
        return new Queue(errorQueue);
    }

    @Bean
    public Queue infoQueue() {
        return new Queue(infoQueue);
    }

    @Bean
    public Queue warningQueue() {
        return new Queue(warningQueue);
    }

    @Bean
    public Binding errorBinding(DirectExchange directExchange, Queue errorQueue) {
        return BindingBuilder.bind(errorQueue).to(directExchange).with("error");
    }

    @Bean
    public Binding infoBinding(DirectExchange directExchange, Queue infoQueue) {
        return BindingBuilder.bind(infoQueue).to(directExchange).with("info");
    }

    @Bean
    public Binding warningBinding(DirectExchange directExchange, Queue warningQueue) {
        return BindingBuilder.bind(warningQueue).to(directExchange).with("warning");
    }

}
