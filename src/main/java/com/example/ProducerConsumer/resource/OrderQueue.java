package com.example.ProducerConsumer.resource;

import com.example.ProducerConsumer.entity.FastFood;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class OrderQueue {

    @Bean
    public Queue<FastFood> getOrderQueue(){
        return new LinkedBlockingQueue<>();
    }
}
