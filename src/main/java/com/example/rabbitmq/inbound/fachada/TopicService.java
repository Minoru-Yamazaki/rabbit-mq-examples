package com.example.rabbitmq.inbound.fachada;


import com.example.rabbitmq.core.mensageria.Mensageria;
import com.example.rabbitmq.inbound.dto.TopicMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    @Autowired
    @Qualifier("topic")
    private Mensageria mensageria;

    @Value("${rabbitmq.topic.topic-exchange.name}")
    private String topicExchangeName;

    public void send(){
        // Enviar algumas mensagens com diferentes chaves de roteamento
        mensageria.send(topicExchangeName, "kern.error", new TopicMessageDto("Kernel error occurred"));
        mensageria.send(topicExchangeName, "app.info", new TopicMessageDto("Application info log"));
        mensageria.send(topicExchangeName, "app.warning", new TopicMessageDto("Application warning log"));
        mensageria.send(topicExchangeName, "app.error", new TopicMessageDto("Application error log"));
        mensageria.send(topicExchangeName, "kern.warning", new TopicMessageDto("Kernel warning log"));
        mensageria.send(topicExchangeName, "kern.info", new TopicMessageDto("Kernel info log"));
    }

}
