package com.faizan.multithread.prac.prac4;

import java.util.concurrent.ArrayBlockingQueue;

public class ProduceConsumeArrayBlockingQueue {
	
	int num=1;
	static ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<Integer>(1);
	
	
	
	public void produce() {
		while(true) {
			System.out.println("produce number :"+num);
			try {
				arrayBlockingQueue.put(num);
				num++;
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void consume() {
		while(true) {
			
			try {
				Integer take = arrayBlockingQueue.take();
				System.out.println("consume number :"+take);
				
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		ProducerConsumerProblemReentantLock consumerProblemReentantLock = new ProducerConsumerProblemReentantLock();
		Thread t1= new Thread(()->consumerProblemReentantLock.produce());
		Thread t2 = new Thread(()->consumerProblemReentantLock.consume());
		
		t1.start();
		t2.start();
	}

}
