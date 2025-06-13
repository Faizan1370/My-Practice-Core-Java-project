package com.faizan.multithread.prac.prac5;

import java.util.concurrent.ArrayBlockingQueue;

public class ProduceConsumeArrayBlockingQueue {
	
	int num=1;
	
	ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<Integer>(1);
	
	public void produce() {
		while(true) {
			System.out.println("Produced Number :"+num);
			try {
				arrayBlockingQueue.put(num);
				num++;
				Thread.sleep(1000);
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
				System.out.println("Consumed Number :"+take);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {
		ProduceConsumeArrayBlockingQueue producerConsumer = new ProduceConsumeArrayBlockingQueue();
		Thread t1 = new Thread(()->producerConsumer.produce());
		Thread t2 = new Thread(()->producerConsumer.consume());
		t1.start();
		t2.start();
	}

}
