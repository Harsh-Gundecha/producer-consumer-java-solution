package com.example.ProducerConsumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsumerTest {

    private Producer producer;
    private Consumer consumer;
    private Queue<Integer> sharedQ;
    private int maxSleepMilliseconds;
    private int capacity;

    @BeforeEach
    void setup(){
        capacity = new Random().nextInt(25);
        maxSleepMilliseconds = new Random().nextInt(250);
        sharedQ = new LinkedBlockingQueue<>(IntStream.range(0, capacity).boxed().collect(Collectors.toCollection(LinkedList::new)));
        consumer = new Consumer(sharedQ, maxSleepMilliseconds);
    }

    @RepeatedTest(value = 5)
    void massConsumptionTest() throws InterruptedException {
        consumer.start();
        consumer.join();
        assertEquals(capacity, consumer.getConsumptionCount(),
                "check production count");
    }

    @Test
    void consumeTest(){
        int randomInt = new Random().nextInt();
        assertEquals(randomInt, consumer.consume(randomInt), "check single product production");
    }

}
