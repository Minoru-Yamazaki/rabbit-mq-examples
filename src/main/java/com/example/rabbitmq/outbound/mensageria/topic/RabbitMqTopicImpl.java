package com.example.rabbitmq.outbound.mensageria.topic;

import com.example.rabbitmq.core.mensageria.Mensageria;
import com.example.rabbitmq.inbound.dto.TopicMessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Qualifier("topic")
public class RabbitMqTopicImpl implements Mensageria<TopicMessageDto> {

    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper;

    @Override
    public void send(String topicExchangeName, String routingKey, TopicMessageDto message) {
        try {
            System.out.println("Preparing to send message with topic-exchange-name: " + topicExchangeName + " & routing-key " + routingKey);
            String messageStr = objectMapper.writeValueAsString(message);
            System.out.println("Sending message: " + message);
            rabbitTemplate.convertAndSend(topicExchangeName, routingKey, messageStr);
        } catch (JsonProcessingException e) {
            System.out.println("Error sending message: " + message);
        }
    }

}
