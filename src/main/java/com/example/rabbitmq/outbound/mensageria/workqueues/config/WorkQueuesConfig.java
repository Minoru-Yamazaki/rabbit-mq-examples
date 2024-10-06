package com.example.rabbitmq.outbound.mensageria.workqueues.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.rabbitmq.outbound.mensageria.workqueues")
public class WorkQueuesConfig {

    @Value("${rabbitmq.queue.one.name}")
    private String queueOne;

    // Dead Letter Queue (DLQ)
    @Value("${rabbitmq.queue.one.dlq-name}")
    private String queueOneDLQ;

    @Value("${rabbitmq.queue.one.exchange}")
    private String queueOneExchangeName;

    @Value("${rabbitmq.queue.one.routing-key}")
    private String queueOneRoutingKey;

    @Bean
    public Queue queueOne() {
        return QueueBuilder.durable(queueOne)
                .withArgument("x-dead-letter-exchange", "") // DLX (Dead Letter Exchange) padrão
                .withArgument("x-dead-letter-routing-key", queueOneDLQ) // Definindo a DLQ
                .build();
    }

    @Bean
    public Queue queueOneDLQ() {
        return new Queue(queueOneDLQ, true); // DLQ durável
    }

    @Bean
    public TopicExchange queueOneTopicExchange() {
        return new TopicExchange(queueOneExchangeName);
    }

    @Bean
    public Binding binding(Queue queueOne, TopicExchange queueOneTopicExchange) {
        return BindingBuilder.bind(queueOne).to(queueOneTopicExchange).with(queueOneRoutingKey);
    }

}
