package com.example.ProducerConsumer.resource;

import com.example.ProducerConsumer.entity.FastFood;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/*
import org.springframework.stereotype.Component;
@Component
/*/
import org.springframework.context.annotation.Configuration;
@Configuration
//*/

public class OrderQueue {

    final static Logger LOGGER = LoggerFactory.getLogger(OrderQueue.class);

    @Bean
    public Queue<FastFood> getOrderQueue(){
        LOGGER.error("spawning new List");
        return new LinkedBlockingQueue<>();
    }
}
