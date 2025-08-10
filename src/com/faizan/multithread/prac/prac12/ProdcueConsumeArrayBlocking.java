package com.faizan.multithread.prac.prac12;

import java.util.concurrent.ArrayBlockingQueue;

public class ProdcueConsumeArrayBlocking {
	
	int num=1;
	ArrayBlockingQueue<Integer>  queue= new ArrayBlockingQueue<Integer>(1);
   
	public void produce() {
		while(true) {
			System.out.println("Produced Number :"+num);
			queue.add(num);
			num++;
			try {
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
				System.out.println("Consume number :"+queue.take());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		ProdcueConsumeArrayBlocking consumeProb = new ProdcueConsumeArrayBlocking();
		Thread t1= new Thread(()->consumeProb.produce());
		Thread t2 =new Thread(()->consumeProb.consume());
		
		t1.start();
		t2.start();
	}
}
