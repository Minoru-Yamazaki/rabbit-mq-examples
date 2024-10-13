package com.example.rabbitmq.inbound.listener.routing;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RoutingListener {

    @RabbitListener(queues = "${rabbitmq.routing.queue.error.name}")
    public void receiveMessageQueueError(String message, Channel channel, Message amqpMessage) throws IOException {
        System.out.println("Received ERROR message: " + message);
        channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = "${rabbitmq.routing.queue.info.name}")
    public void receiveMessageQueueInfo(String message, Channel channel, Message amqpMessage) throws IOException {
        System.out.println("Received INFO message: " + message);
        channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = "${rabbitmq.routing.queue.warning.name}")
    public void receiveMessageQueueWarning(String message, Channel channel, Message amqpMessage) throws IOException {
        System.out.println("Received WARNING message: " + message);
        channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
    }

}
