package com.example.rabbitmq.inbound.controller;

import com.example.rabbitmq.core.dto.MensagemDto;
import com.example.rabbitmq.inbound.fachada.WorkQueueService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/work-queues")
public class WorkQueueController {

    private WorkQueueService service;

    @PostMapping
    public ResponseEntity sendTwice(@RequestBody MensagemDto mensagem){
        service.sendTwice(mensagem);
        return ResponseEntity.ok().build();
    }

}
