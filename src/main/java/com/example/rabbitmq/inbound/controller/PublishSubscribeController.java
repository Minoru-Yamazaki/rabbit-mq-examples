package com.example.rabbitmq.inbound.controller;

import com.example.rabbitmq.inbound.dto.PublishSubscribeMessageDto;
import com.example.rabbitmq.inbound.fachada.PublishSubscribeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/publish-subscribe")
public class PublishSubscribeController {

    private PublishSubscribeService service;

    @PostMapping
    public ResponseEntity send(@RequestBody PublishSubscribeMessageDto mensagem){
        service.send(mensagem);
        return ResponseEntity.ok().build();
    }

}
