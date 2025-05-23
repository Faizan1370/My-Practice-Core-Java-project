package com.faizan.multithread.prac.prac;

import java.util.concurrent.ArrayBlockingQueue;

public class ProdConsumeArrayBlockingQueye {
	
	ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<Integer>(1);
	int num=1;
	
	public void produce() {
	
		while(true) {
			System.out.println("Produced Value :"+num);
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
				System.out.println("consumed Value :"+take);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	public static void main(String[] args) {
		ProdConsumeArrayBlockingQueye arrayBlockingQueye = new ProdConsumeArrayBlockingQueye();
		Thread t1 = new Thread(()->arrayBlockingQueye.produce());
		Thread t2 = new Thread(()->arrayBlockingQueye.consume());
		t1.start();
		t2.start();
	}

}
