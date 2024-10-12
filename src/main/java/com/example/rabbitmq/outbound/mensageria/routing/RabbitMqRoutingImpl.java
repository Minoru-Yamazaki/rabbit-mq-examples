package com.example.rabbitmq.outbound.mensageria.routing;

import com.example.rabbitmq.core.mensageria.Mensageria;
import com.example.rabbitmq.inbound.dto.RoutingMessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
@Qualifier("routing")
public class RabbitMqRoutingImpl implements Mensageria<RoutingMessageDto> {

    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper;

    @Override
    public void send(String directExchangeName, String routingKey, RoutingMessageDto message) {
        try {
            log.info("Preparing to send message with direct-exchange-name: {} & routing-key{}", directExchangeName, routingKey);
            String messageStr = objectMapper.writeValueAsString(message);
            log.info("Sending message: {}", message);
            rabbitTemplate.convertAndSend(directExchangeName, routingKey, messageStr);
        } catch (JsonProcessingException e) {
            log.error("Error sending message: {}", message);
        }
    }

}
