package com.faizan.multithread.prac;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExampleProdConsumerProblem {
    private final Lock lock = new ReentrantLock();
    private final Condition produced = lock.newCondition();
    private final Condition consumed = lock.newCondition();

    private boolean isProduced = false;
    private int value = 0;

    public void produce() throws InterruptedException {
        while (true) {
            lock.lock();
            try {
                while (isProduced) {
                    produced.await();  // Wait until consumed
                }
                value++;
                System.out.println("Produced: " + value);
                isProduced = true;
                consumed.signal(); // Notify consumer
            } finally {
                lock.unlock();
            }
            Thread.sleep(1000);
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            lock.lock();
            try {
                while (!isProduced) {  // just change to isProduced true will perform deaclock
                    consumed.await(); // Wait until produced
                }
                System.out.println("Consumed: " + value);
                isProduced = false;
                produced.signal(); // Notify producer
            } finally {
                lock.unlock();
            }
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) {
        ReentrantLockExampleProdConsumerProblem example = new ReentrantLockExampleProdConsumerProblem();

        Thread producerThread = new Thread(() -> {
            try {
                example.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                example.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
