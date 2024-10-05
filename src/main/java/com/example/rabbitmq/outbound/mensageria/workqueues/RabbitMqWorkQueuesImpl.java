package com.example.rabbitmq.outbound.mensageria.workqueues;

import com.example.rabbitmq.core.dto.MensagemDto;
import com.example.rabbitmq.core.mensageria.Mensageria;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class RabbitMqWorkQueuesImpl implements Mensageria<MensagemDto> {

    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper;

    @Override
    public void send(String exchangeName, String routingKey, MensagemDto mensage) {
        try {
            log.info("writing value as String: {}", mensage);
            String stringMessage = objectMapper.writeValueAsString(mensage);
            log.info("Sending message: {}", mensage);
            rabbitTemplate.convertAndSend(exchangeName, routingKey, stringMessage);
        } catch (JsonProcessingException e) {
            System.out.println("Erro ao enviar mensage: " + mensage);
        }
    }

}
