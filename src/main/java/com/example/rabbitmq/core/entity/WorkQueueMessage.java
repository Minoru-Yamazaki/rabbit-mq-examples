package com.example.rabbitmq.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WorkQueueMessage {

    private String message;

}
