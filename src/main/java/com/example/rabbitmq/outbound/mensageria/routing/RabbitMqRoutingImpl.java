package com.example.rabbitmq.outbound.mensageria.routing;

import com.example.rabbitmq.core.mensageria.Mensageria;
import com.example.rabbitmq.inbound.dto.RoutingMessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Qualifier("routing")
public class RabbitMqRoutingImpl implements Mensageria<RoutingMessageDto> {

    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper;

    @Override
    public void send(String directExchangeName, String routingKey, RoutingMessageDto message) {
        try {
            System.out.println("Preparing to send message with direct-exchange-name: " + directExchangeName  + " & routing-key: " + routingKey);
            String messageStr = objectMapper.writeValueAsString(message);
            System.out.println("Sending message: " + message);
            rabbitTemplate.convertAndSend(directExchangeName, routingKey, messageStr);
        } catch (JsonProcessingException e) {
            System.out.println("Erro ao enviar message: " + message);
        }
    }

}
