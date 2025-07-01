package com.faizan.multithread.prac.prac7;

import java.util.concurrent.ArrayBlockingQueue;

public class ProduceConsumeArrayBloackingQueue {
	
	int num=1;
	boolean isProduced=false;
	ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<Integer>(1);
	
	public void produce() throws InterruptedException {
		while(true) {
			System.out.println("produce num :"+num);
			arrayBlockingQueue.offer(num);
			num++;
			Thread.sleep(1000);
		}
	}
	
	public void consume() throws InterruptedException {
		while(true) {
			System.out.println("Consume number :"+arrayBlockingQueue.take());
			Thread.sleep(1000);
		}
	}
	
	public static void main(String[] args) {
		ProduceConsumeArrayBloackingQueue consumerProb = new ProduceConsumeArrayBloackingQueue();
		Thread t1= new Thread(()->{
			try {
				consumerProb.produce();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		Thread t2 = new Thread(()->{
			try {
				consumerProb.consume();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		t1.start();
		t2.start();
	}

}
