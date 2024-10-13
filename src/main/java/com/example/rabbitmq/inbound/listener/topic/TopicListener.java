package com.example.rabbitmq.inbound.listener.topic;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TopicListener {

    @RabbitListener(queues = "${rabbitmq.topic.queue.error.name}")
    public void receiveMessageQueueError(String message, Channel channel, Message amqpMessage) throws IOException {
        System.out.println("Received ERROR message: " + message);
        channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = "${rabbitmq.topic.queue.all-logs.name}")
    public void receiveMessageAllLogs(String message, Channel channel, Message amqpMessage) throws IOException {
        System.out.println("Received LOG message: " + message);
        channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
    }

}
