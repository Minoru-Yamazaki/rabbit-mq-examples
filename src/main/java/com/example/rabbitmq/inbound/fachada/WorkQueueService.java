package com.example.rabbitmq.inbound.fachada;


import com.example.rabbitmq.core.dto.WorkQueueMessageDto;
import com.example.rabbitmq.core.mensageria.Mensageria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WorkQueueService {

    @Autowired
    private Mensageria mensageria;

    @Value("${rabbitmq.queue.one.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.queue.one.routing-key}")
    private String routingKey;

    public void sendTwice(WorkQueueMessageDto dto){
        log.info("Sending message for the first time");
        mensageria.send(exchangeName, routingKey, dto);
        log.info("Sending message for the second time");
        mensageria.send(exchangeName, routingKey, dto);
    }

}
