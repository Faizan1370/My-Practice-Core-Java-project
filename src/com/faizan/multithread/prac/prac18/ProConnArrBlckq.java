package com.faizan.multithread.prac.prac18;

import java.util.concurrent.ArrayBlockingQueue;

public class ProConnArrBlckq {
	int num=1;
	
	ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(1);
	
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
				System.out.println("Consumed Number :"+queue.take());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		ProConnArrBlckq connPOr = new ProConnArrBlckq();
		Thread t1= new Thread(()->connPOr.produce());
		Thread t2 = new Thread(()->connPOr.consume());
		
		t1.start();
		t2.start();
	}
}
