package com.example.ProducerConsumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerApplicationTests {

	private Producer producer;
	private Consumer consumer;
	private Queue<Integer> sharedQ;
	private int maxSleepMilliseconds;
	private int capacity;

	@BeforeEach
	void setup(){
		capacity = new Random().nextInt(25);
		maxSleepMilliseconds = new Random().nextInt(250);
		sharedQ = new LinkedBlockingQueue<>();
        producer = new Producer(sharedQ, capacity, maxSleepMilliseconds);
		consumer = new Consumer(sharedQ, maxSleepMilliseconds);
	}

	@RepeatedTest(value = 5)
	void massConsumptionTest() throws InterruptedException {
		producer.start();
		producer.join();
		consumer.start();
		consumer.join();
		assertEquals(producer.getProductionCount(), consumer.getConsumptionCount(),
				"check production count");
	}

}
