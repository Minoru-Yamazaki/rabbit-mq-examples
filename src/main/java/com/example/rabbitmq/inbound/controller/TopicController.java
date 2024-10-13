package com.example.rabbitmq.inbound.controller;

import com.example.rabbitmq.inbound.fachada.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/topic")
public class TopicController {

    private TopicService service;

    @PostMapping
    public ResponseEntity send(){
        service.send();
        return ResponseEntity.ok().build();
    }

}
