package com.example.rabbitmq.inbound.controller;

import com.example.rabbitmq.inbound.fachada.RpcService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/rpc")
public class RpcController {

    private RpcService service;

    @GetMapping
    public ResponseEntity<Integer> calculate(@RequestParam int number){
        return ResponseEntity.ok(service.calculate(number));
    }

}
