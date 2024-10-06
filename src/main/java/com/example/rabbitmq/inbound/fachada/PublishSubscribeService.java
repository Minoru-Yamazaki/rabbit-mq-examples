package com.example.rabbitmq.inbound.fachada;


import com.example.rabbitmq.inbound.dto.PublishSubscribeMessageDto;
import com.example.rabbitmq.core.mensageria.Mensageria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PublishSubscribeService {

    @Autowired
    @Qualifier("publish_subscribe")
    private Mensageria mensageria;

    @Value("${rabbitmq.publish-subscribe.fanout.exchange-name}")
    private String fanoutExchangeName;

    public void send(PublishSubscribeMessageDto dto){
        log.info("Sending message: {}", dto);
        mensageria.send(fanoutExchangeName, "", dto);
    }

}
