package com.example.rabbitmq.inbound.fachada;


import com.example.rabbitmq.core.mensageria.Mensageria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RpcService {

    @Value("${rabbitmq.rpc.queue.name}")
    private String rpcQueueName;

    @Autowired
    @Qualifier("rpc")
    private Mensageria mensageria;

    public Integer calculate(int number) {
        return (Integer) mensageria.sendAndReceive(rpcQueueName, number);
    }
}
