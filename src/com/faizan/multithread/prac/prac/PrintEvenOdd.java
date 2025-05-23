package com.faizan.multithread.prac.prac;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintEvenOdd {
	
	static ReentrantLock lock = new ReentrantLock();
	Condition even = lock.newCondition();
	Condition odd = lock.newCondition();
	static int limit=20;
	static int num=1;
	static ExecutorService executorService = Executors.newFixedThreadPool(2);
	
	public void printEven() {
		while(num<=limit) {
			lock.lock();
			while(num%2!=0) {
				try {
					even.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Even nmumber :"+num);
			num++;
			odd.signal();
			lock.unlock();
		}
	}
	
	public void printOdd() {
		while(num<=limit) {
			lock.lock();
			while(num%2==0) {
				try {
					odd.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Odd nmumber :"+num);
			num++;
			even.signal();
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		PrintEvenOdd evenOdd = new PrintEvenOdd();
		//Thread t1= new Thread(()->evenOdd.printEven());
		//Thread t2 = new Thread(()->evenOdd.printOdd());
		//executorService.submit(()->evenOdd.printEven());
		//executorService.submit(()->evenOdd.printOdd());
		//executorService.shutdown();
		CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->evenOdd.printEven(),executorService);
		CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(()->evenOdd.printOdd(),executorService);
		completableFuture.join();
		completableFuture1.join();
		executorService.shutdown();
		//t1.start();
		//t2.start();
	}

}
