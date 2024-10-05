package com.example.rabbitmq.inbound.listener.workqueues;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CounterSingleton {

    @Getter
    private static int count = 0;
    private static CounterSingleton counter;

    public static CounterSingleton getInstance(){
        if (Objects.isNull(counter)) counter = new CounterSingleton();
        return counter;
    }

    public void addCounter(){
        if (count == 0) count++;
        else count = 0;
    }

    public boolean counterEqualsZero(){
        return count == 0;
    }

}
