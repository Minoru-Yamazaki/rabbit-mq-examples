package com.example.rabbitmq.outbound.mensageria.publishsubscribe;

import com.example.rabbitmq.inbound.dto.PublishSubscribeMessageDto;
import com.example.rabbitmq.core.mensageria.Mensageria;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Qualifier("publish_subscribe")
public class RabbitMqPublishSubscribeImpl implements Mensageria<PublishSubscribeMessageDto> {

    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper;

    @Override
    public void send(String fanoutExchangeName, String routingKey, PublishSubscribeMessageDto message) {
        try {
            System.out.println("Preparing to send message with fanout-exchange-name: " + fanoutExchangeName  + " & routing-key: " + routingKey);
            String stringMessage = objectMapper.writeValueAsString(message);
            System.out.println("Sending message: " + message);
            rabbitTemplate.convertAndSend(fanoutExchangeName, "", stringMessage);
        } catch (JsonProcessingException e) {
            System.out.println("Publish & Sunscribe: Erro ao enviar message: " + message);
        }
    }

    @Override
    public PublishSubscribeMessageDto sendAndReceive(String queueName, PublishSubscribeMessageDto dto) {
        return null;
    }

}
