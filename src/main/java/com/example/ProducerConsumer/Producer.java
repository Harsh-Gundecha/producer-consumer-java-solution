package com.example.ProducerConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.Random;

public class Producer extends Thread {
    private int maxCapacity, currentProduct, productionCount, maxSleepMilliseconds;
    Queue<Integer> sharedQ;
    Logger logger = LoggerFactory.getLogger(Consumer.class);

    public Producer(Queue<Integer> producerQ, int size, int maxSleepMilliseconds){
        sharedQ = producerQ;
        maxCapacity = size;
        this.maxSleepMilliseconds = maxSleepMilliseconds;
        currentProduct = -1;
        productionCount = 0;
    }

    public int getProductionCount() {
        return productionCount;
    }

    public int produce(int data){
        currentProduct = data+1;
        logger.info("Produced data : " + currentProduct);
        return currentProduct;
    }

    @Override
    public void run(){
        for(productionCount=0; productionCount < maxCapacity; productionCount++){
            synchronized (sharedQ){
                sharedQ.add(produce(currentProduct));
            }

            try {
                sleep(new Random().nextInt(maxSleepMilliseconds));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}