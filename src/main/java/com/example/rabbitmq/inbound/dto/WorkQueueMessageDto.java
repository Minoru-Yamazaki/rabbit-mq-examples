package com.example.rabbitmq.inbound.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WorkQueueMessageDto {

    private String message;

}
