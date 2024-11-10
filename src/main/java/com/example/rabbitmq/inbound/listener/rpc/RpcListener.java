package com.example.rabbitmq.inbound.listener.rpc;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RpcListener {

    @RabbitListener(queues = "${rabbitmq.rpc.queue.name}")
    @SendTo  // Define que a resposta será enviada de volta para a fila de resposta
    public int calculate(int number, Channel channel, Message amqpMessage) throws IOException {
        System.out.println("[Listener] Received number: " + number);
        int result = number * number;  // Exemplo: calcula o quadrado do número
        System.out.println("[Listener] Sending response: " + result);
        channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
        return result;
    }

}
