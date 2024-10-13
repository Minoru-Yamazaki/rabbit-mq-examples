package com.example.rabbitmq.outbound.mensageria.workqueues;

import com.example.rabbitmq.core.mensageria.Mensageria;
import com.example.rabbitmq.inbound.dto.WorkQueueMessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Qualifier("work_queues")
public class RabbitMqWorkQueuesImpl implements Mensageria<WorkQueueMessageDto> {

    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper;

    @Override
    public void send(String exchangeName, String routingKey, WorkQueueMessageDto message) {
        try {
            System.out.println("Preparing to send message with exchange-name: " + exchangeName  + " & routing-key: " + routingKey);
            String stringMessage = objectMapper.writeValueAsString(message);
            System.out.println("Sending message: " + message);
            rabbitTemplate.convertAndSend(exchangeName, routingKey, stringMessage);
        } catch (JsonProcessingException e) {
            System.out.println("Erro ao enviar message: " + message);
        }
    }

}
