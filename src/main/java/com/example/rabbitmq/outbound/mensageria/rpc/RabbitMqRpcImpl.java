package com.example.rabbitmq.outbound.mensageria.rpc;

import com.example.rabbitmq.core.mensageria.Mensageria;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("rpc")
@RequiredArgsConstructor
public class RabbitMqRpcImpl implements Mensageria<Integer> {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(String topicExchangeName, String routingKey, Integer number) {}

    @Override
    public Integer sendAndReceive(String queueName, Integer number) {
        System.out.println("[Sender] Sending number [" + number + "] to square area calculate");
        Integer response = (Integer) rabbitTemplate.convertSendAndReceive(queueName, number);
        System.out.println("[Sender] Response : " + response);
        return response;
    }

}
