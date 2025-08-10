package com.faizan.multithread.prac.prac10;

import java.util.concurrent.ArrayBlockingQueue;

public class ProduceConsumeArrayBlockinQueue {
	
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
				
				System.out.println("Consumed Number :"+arrayBlockingQueue.take());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		ProduceConsumeArrayBlockinQueue consumePr= new ProduceConsumeArrayBlockinQueue();
		Thread t1= new Thread(()->consumePr.produce());
		Thread t2= new Thread(()->consumePr.consume());
		
		t1.start();
		t2.start();
	}

}
