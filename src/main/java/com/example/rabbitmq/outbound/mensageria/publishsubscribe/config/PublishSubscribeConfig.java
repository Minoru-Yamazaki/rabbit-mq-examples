package com.example.rabbitmq.outbound.mensageria.publishsubscribe.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.rabbitmq.outbound.mensageria.publishsubscribe")
public class PublishSubscribeConfig {

    @Value("${rabbitmq.publish-subscribe.queue.two.name}")
    private String queueTwo;

    @Value("${rabbitmq.publish-subscribe.queue.three.name}")
    private String queueThree;

    @Value("${rabbitmq.publish-subscribe.fanout.exchange-name}")
    private String fanoutExchangeName;

    // Definir o Fanout Exchange
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(fanoutExchangeName);
    }

    // Definir a primeira fila
    @Bean
    public Queue queueTwo() {
        return new Queue(queueTwo, true);
    }

    // Definir a segunda fila
    @Bean
    public Queue queueThree() {
        return new Queue(queueThree, true);
    }

    // Vincular a fila queueTwo ao Fanout Exchange
    @Bean
    public Binding bindingQueueTwo(FanoutExchange fanoutExchange, Queue queueTwo) {
        return BindingBuilder.bind(queueTwo).to(fanoutExchange);
    }

    // Vincular a fila queueThree ao Fanout Exchange
    @Bean
    public Binding bindingQueueThree(FanoutExchange fanoutExchange, Queue queueThree) {
        return BindingBuilder.bind(queueThree).to(fanoutExchange);
    }

}
