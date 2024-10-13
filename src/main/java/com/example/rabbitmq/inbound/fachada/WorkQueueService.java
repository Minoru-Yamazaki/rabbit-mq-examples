package com.example.rabbitmq.inbound.fachada;


import com.example.rabbitmq.core.mensageria.Mensageria;
import com.example.rabbitmq.inbound.dto.WorkQueueMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WorkQueueService {

    @Autowired
    @Qualifier("work_queues")
    private Mensageria mensageria;

    @Value("${rabbitmq.queue.one.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.queue.one.routing-key}")
    private String routingKey;

    public void sendTwice(WorkQueueMessageDto dto){
        mensageria.send(exchangeName, routingKey, dto);
        mensageria.send(exchangeName, routingKey, dto);
    }

}
