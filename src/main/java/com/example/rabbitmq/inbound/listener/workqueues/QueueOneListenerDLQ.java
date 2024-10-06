package com.example.rabbitmq.inbound.listener.workqueues;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class QueueOneListenerDLQ {

    @RabbitListener(queues = "${rabbitmq.queue.one.dlq-name}")
    public void receiveMessage(String message, Channel channel, Message amqpMessage) throws IOException {
        System.out.println("mensagem da fila DLQ: " + message);
        channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
    }

}
