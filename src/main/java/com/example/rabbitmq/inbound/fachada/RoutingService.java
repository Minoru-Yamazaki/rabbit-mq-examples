package com.example.rabbitmq.inbound.fachada;


import com.example.rabbitmq.core.mensageria.Mensageria;
import com.example.rabbitmq.inbound.dto.RoutingMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RoutingService {

    @Autowired
    @Qualifier("routing")
    private Mensageria mensageria;

    @Value("${rabbitmq.routing.direct-exchange.name}")
    private String directExchangeName;

    public void send(){
        // Simular o envio de diferentes mensagens
        mensageria.send(directExchangeName, "error", new RoutingMessageDto("This is an ERROR message"));
        mensageria.send(directExchangeName,"info", new RoutingMessageDto("This is an INFO message"));
        mensageria.send(directExchangeName,"warning", new RoutingMessageDto("This is a WARNING message"));
    }

}
