package com.example.ProducerConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@SpringBootApplication
public class ProducerConsumerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProducerConsumerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		final int capacity = 25, maxSleepMilliseconds = 500;
		Queue<Integer> sharedQ = new LinkedBlockingQueue<>();
		Logger logger = LoggerFactory.getLogger(ProducerConsumerApplication.class);

		Producer producer = new Producer(sharedQ, capacity, maxSleepMilliseconds);
		Consumer consumer = new Consumer(sharedQ, maxSleepMilliseconds);

		producer.start();
		consumer.start();
		producer.join();
		consumer.join();

		logger.info("Producer produced " + producer.getProductionCount());
		logger.info("Consumer consumed " + consumer.getConsumptionCount());
	}
}
