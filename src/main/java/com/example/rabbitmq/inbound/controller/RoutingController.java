package com.example.rabbitmq.inbound.controller;

import com.example.rabbitmq.inbound.fachada.RoutingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/routing")
public class RoutingController {

    private RoutingService service;

    @PostMapping
    public ResponseEntity send(){
        service.send();
        return ResponseEntity.ok().build();
    }

}
