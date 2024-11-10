package com.example.rabbitmq.outbound.mensageria.rpc.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.rabbitmq.outbound.mensageria.rpc")
public class RpcConfig {

    @Value("${rabbitmq.rpc.queue.name}")
    private String rpcQueueName;

    @Bean
    public Queue rpcQueue() {
        return new Queue(rpcQueueName);
    }

}
