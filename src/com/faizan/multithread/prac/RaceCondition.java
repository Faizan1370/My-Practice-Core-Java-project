package com.faizan.multithread.prac;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceCondition {

    static int count = 0;
  //  static AtomicInteger atomicCount = new AtomicInteger(0);
    
    public synchronized void incrementCount() {
        count++; // Now thread-safe
    }

    public void sharedResource2() {
        for (int i = 0; i < 2000; i++) {
            incrementCount();
            //atomicCount.incrementAndGet(); // Thread-safe
        }
        // System.out.println("Atomic Count: " + atomicCount.get());
        System.out.println("Synchronized Count: " + count);
    }

    
    public static void main(String[] args) throws InterruptedException {
        RaceCondition task = new RaceCondition();

        Thread t1 = new Thread(() -> task.sharedResource2());
        Thread t2 = new Thread(() -> task.sharedResource2());

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        //System.out.println("Final Atomic Count: " + atomicCount.get());
        System.out.println("Final Count (Race Condition): " + count);
    }
}
