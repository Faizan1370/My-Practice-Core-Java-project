package com.faizan.multithread.prac.prac17;

import java.util.concurrent.ArrayBlockingQueue;

public class ProduceConsumeArrBlck {
	int num=1;
	ArrayBlockingQueue<Integer> queue= new ArrayBlockingQueue<Integer>(1);
	
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
	

	public void consmed() {
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
		ProduceConsumeArrBlck consumePrr = new ProduceConsumeArrBlck();
		Thread t1= new Thread(()->consumePrr.produce());
		Thread t2= new Thread(()->consumePrr.consmed());
		
		t1.start();
		t2.start();
	}


}
