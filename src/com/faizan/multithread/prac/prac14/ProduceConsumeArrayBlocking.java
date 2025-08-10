package com.faizan.multithread.prac.prac14;

import java.util.concurrent.ArrayBlockingQueue;

public class ProduceConsumeArrayBlocking {
	
	int num=1;
	ArrayBlockingQueue<Integer> queue= new ArrayBlockingQueue<Integer>(1);
	
	public void produce() {
		while(true) {
			System.out.println("Produce Number :"+num);
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
				System.out.println("Consume Number :"+queue.take());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {
		ProduceConsumeArrayBlocking consumePro = new ProduceConsumeArrayBlocking();
		Thread t1= new Thread(()->consumePro.produce());
		Thread t2 = new Thread(()->consumePro.consume());
		
		t1.start();
		t2.start();
	}


}
