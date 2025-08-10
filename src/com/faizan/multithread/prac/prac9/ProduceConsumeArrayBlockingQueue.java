package com.faizan.multithread.prac.prac9;

import java.util.concurrent.ArrayBlockingQueue;

public class ProduceConsumeArrayBlockingQueue {
	
	int num=1;
	ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<Integer>(1);
	
	
	public void produce() {
		while(true) {
			System.out.println("Produced num:"+num);
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
				System.out.println("Consumed num:"+arrayBlockingQueue.take());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		ProduceConsumeArrayBlockingQueue consumePro = new ProduceConsumeArrayBlockingQueue();
		Thread t1 = new Thread(()->consumePro.produce());
		Thread t2 = new Thread(()->consumePro.consume());
		
		t1.start();
		t2.start();
	}

}
