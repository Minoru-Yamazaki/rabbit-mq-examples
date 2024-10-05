package com.example.rabbitmq.core.mensageria;

public interface Mensageria<T> {
    void send(String exchangeName, String routingKey, T dto);
}
