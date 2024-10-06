package com.example.rabbitmq.inbound.listener.workqueues;

import com.example.rabbitmq.core.entity.WorkQueueMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class QueueOneListener2 {

    private ObjectMapper objectMapper;

    @RabbitListener(queues = "${rabbitmq.queue.one.name}")
    public void receiveMessage(String message, Channel channel, Message amqpMessage) {
        try {
            WorkQueueMessage mensagem = objectMapper.readValue(message, WorkQueueMessage.class);
            System.out.println("Listener 2: " + mensagem);
            channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
        } catch (JsonProcessingException e) {
            System.out.println("Falha ao converter mensagem");
        } catch (IOException e) {
            System.out.println("Falha ao confirmar processamento da mensagem");
            try {
                // Rejeita a mensagem e reencaminha para a fila para ser processada novamente
                channel.basicNack(amqpMessage.getMessageProperties().getDeliveryTag(), false, true);
                System.out.println("Mensagem reencaminhada para a fila: " + message);
            } catch (Exception ex) {
                System.out.println("Falha ao enviar nack: " + ex.getMessage());
            }
        }
    }

}
