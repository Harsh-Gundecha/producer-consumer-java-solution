package com.example.ProducerConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.Random;

public class Consumer extends Thread {
    private int maxCapacity, currentProduct, consumptionCount, totalContinuoslyFailedAttempt, allowedFailedAttempt = 10, maxSleepMilliseconds;
    Queue<Integer> sharedQ;
    Logger logger = LoggerFactory.getLogger(Consumer.class);

    public Consumer(Queue<Integer> consumerQ, int maxSleepMilliseconds){
        sharedQ = consumerQ;
        totalContinuoslyFailedAttempt = 0;
        this.maxSleepMilliseconds = maxSleepMilliseconds;
        consumptionCount=0;
    }

    public int getConsumptionCount() {
        return consumptionCount;
    }

    public int consume(int data){
        consumptionCount++;
        logger.info("Consumed data : " + data);
        return data;
    }

    @Override
    public void run() {
        while(totalContinuoslyFailedAttempt < allowedFailedAttempt){
            synchronized (sharedQ) {
                if(!sharedQ.isEmpty())
                    consume(sharedQ.remove());
                else
                    totalContinuoslyFailedAttempt++;
            }

            try {
                sleep(new Random().nextInt(maxSleepMilliseconds));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
