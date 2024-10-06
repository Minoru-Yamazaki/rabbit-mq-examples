package com.example.rabbitmq.inbound.listener.publish_subscribe;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class SubscribesListener {

    @RabbitListener(queues = "${rabbitmq.publish-subscribe.queue.two.name}")
    public void receiveMessageQueueTwo(String message, Channel channel, Message amqpMessage) throws IOException {
        log.info("Subscribe 1, message: {}", message);
        channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = "${rabbitmq.publish-subscribe.queue.three.name}")
    public void receiveMessageQueueThree(String message, Channel channel, Message amqpMessage) throws IOException {
        log.info("Subscribe 2, message: {}", message);
        channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
    }

}
