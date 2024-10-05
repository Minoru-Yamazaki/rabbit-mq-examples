package com.example.rabbitmq.inbound.listener.workqueues;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class QueueOneListener1 {

    private ObjectMapper objectMapper;

    @RabbitListener(queues = "${rabbitmq.queue.one.name}")
    public void receiveMessage(String message, Channel channel, Message amqpMessage) {
        try {
            System.out.println("Listener 1");
            throwRunTimeException(); // Simula erro
            channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            System.out.println("Falha ao processar mensagem");
            try {
                CounterSingleton contador = CounterSingleton.getInstance();
                if (contador.counterEqualsZero())
                    // Rejeita a mensagem e reencaminha para a fila para ser processada novamente
                    channel.basicNack(amqpMessage.getMessageProperties().getDeliveryTag(), false, true);
                else
                    // Rejeita a mensagem e a envia para a Dead Letter Queue (DLQ)
                    channel.basicReject(amqpMessage.getMessageProperties().getDeliveryTag(), false);
                contador.addCounter();
            } catch (IOException ex) {
                System.out.println("Falha ao processar mensagem");
            }
        }
    }

    private void throwRunTimeException() {
        throw new RuntimeException("Error");
    }

}
