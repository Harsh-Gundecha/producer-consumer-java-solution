package com.example.ProducerConsumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerTest {

    private Producer producer;
    private Queue<Integer> sharedQ;
    private int maxSleepMilliseconds;
    private int capacity;

    @BeforeEach
    void setup(){
        capacity = new Random().nextInt(25);
        maxSleepMilliseconds = new Random().nextInt(250);
        sharedQ = new LinkedBlockingQueue<>();
        producer = new Producer(sharedQ, capacity, maxSleepMilliseconds);
    }

    @RepeatedTest(value = 5)
    void massProductionTest() throws InterruptedException {
        producer.start();
        producer.join();
        assertEquals(capacity, producer.getProductionCount(),
                "check production count");
    }

    @Test
    void produceTest(){
        assertEquals(6, producer.produce(5), "check single product production");
    }

}
