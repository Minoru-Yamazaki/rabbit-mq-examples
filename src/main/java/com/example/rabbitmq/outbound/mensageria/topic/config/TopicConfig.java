package com.example.rabbitmq.outbound.mensageria.topic.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.rabbitmq.outbound.mensageria.topic")
public class TopicConfig {

    @Value("${rabbitmq.topic.queue.error.name}")
    private String topicErrorQueue;

    @Value("${rabbitmq.topic.queue.all-logs.name}")
    private String allLogsQueue;

    @Value("${rabbitmq.topic.topic-exchange.name}")
    private String topicExchange;

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(topicExchange);
    }

    @Bean
    public Queue topicErrorQueue() {
        return new Queue(topicErrorQueue);
    }

    @Bean
    public Queue allLogsQueue() {
        return new Queue(allLogsQueue);
    }

    // Binding que associa mensagens de erros (chaves como '*.error') à fila de erros
    @Bean
    public Binding topicErrorBinding(TopicExchange topicExchange, Queue topicErrorQueue) {
        return BindingBuilder.bind(topicErrorQueue).to(topicExchange).with("*.error");
    }

    // Binding que associa todas as mensagens (chave de roteamento '#') à fila de logs
    @Bean
    public Binding allLogsBinding(TopicExchange topicExchange, Queue allLogsQueue) {
        return BindingBuilder.bind(allLogsQueue).to(topicExchange).with("#");
    }

}
