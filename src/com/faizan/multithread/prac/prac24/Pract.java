package com.faizan.multithread.prac.prac24;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class Pract {
	//static int count = 0;
	static AtomicInteger count = new AtomicInteger(0);

	public void show() {
		for (int i = 0; i < 2000; i++) {
			count.incrementAndGet();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Pract pract = new Pract();
		Thread t1 = new Thread(() -> pract.show());
		Thread t2 = new Thread(() -> pract.show());
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(count);

	}
}
