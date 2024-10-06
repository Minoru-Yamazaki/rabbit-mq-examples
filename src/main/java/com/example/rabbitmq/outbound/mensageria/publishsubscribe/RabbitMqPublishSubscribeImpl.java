package com.example.rabbitmq.outbound.mensageria.publishsubscribe;

import com.example.rabbitmq.inbound.dto.PublishSubscribeMessageDto;
import com.example.rabbitmq.core.mensageria.Mensageria;
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
@Qualifier("publish_subscribe")
public class RabbitMqPublishSubscribeImpl implements Mensageria<PublishSubscribeMessageDto> {

    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper;

    @Override
    public void send(String fanoutExchangeName, String routingKey, PublishSubscribeMessageDto message) {
        try {
            log.info("fanout-exchange-name: {}, message: {}", fanoutExchangeName, message);
            String stringMessage = objectMapper.writeValueAsString(message);
            log.info("Sending message.");
            rabbitTemplate.convertAndSend(fanoutExchangeName, "", stringMessage);
        } catch (JsonProcessingException e) {
            log.error("Publish & Sunscribe: Erro ao enviar mensage: {}", message);
        }
    }
}
