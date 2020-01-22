package com.example.ProducerConsumer.service;

import com.example.ProducerConsumer.entity.FastFood;
import com.example.ProducerConsumer.resource.OrderQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Queue;

@Service
public class OrderQueueService {
    @Autowired
    private OrderQueue orderQueue;

    public boolean postOrder(FastFood fastFood){
        return orderQueue.getOrderQueue().add(fastFood);
    }

    public FastFood getOrder(){
        return orderQueue.getOrderQueue().remove();
    }

    public boolean doesOrderExist(){
        return !orderQueue.getOrderQueue().isEmpty();
    }

}
