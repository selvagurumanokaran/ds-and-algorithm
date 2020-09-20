package com.geeks.guru.ds.concurrancy;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {

    static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);


    public static void main(String[] args) throws InterruptedException {
        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}

class Producer implements Runnable {

    BlockingQueue<Integer> blockingQueue;

    Producer(BlockingQueue<Integer> queue) {
        this.blockingQueue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(2000);
                System.out.println("Consuming " + blockingQueue.take());
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted!");
        }
    }
}

class Consumer implements Runnable {

    BlockingQueue<Integer> blockingQueue;
    Random random;

    Consumer(BlockingQueue<Integer> queue) {
        this.blockingQueue = queue;
        random = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                int e = random.nextInt();
                System.out.println("Producing " + e);
                blockingQueue.put(e);
                //Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted!");
        }
    }
}