package com.geeks.guru.ds.concurrancy;


import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ProducerConsumerJava8 {
    public static void main(String[] args) throws InterruptedException {
        Queue<Integer> queue = new ArrayDeque<>(2);
        Random random = new Random();
        Thread producer = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(3000);
                    synchronized (queue) {
                        if (queue.isEmpty()) queue.wait();
                        System.out.println("Consuming " + queue.poll());
                        queue.notify();
                    }

                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted!");
            }
        });

        Thread consumer = new Thread(() -> {

            try {
                while (true) {
                    Thread.sleep(1000);
                    int e = random.nextInt();
                    System.out.println("Producing " + e);
                    synchronized (queue) {
                        if (queue.size() == 2) queue.wait();
                        queue.offer(e);
                        queue.notify();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted!");
            }

        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}
