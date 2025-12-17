package com.faizan.multithread.prac.prac22;

import java.util.concurrent.ArrayBlockingQueue;

public class PrrArrayConBl {
	int num=1;
	
	ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(1);
	
	public void produce() {
		while(true) {
			System.out.println("produced Number :"+num);
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
				System.out.println("consume Number :"+queue.take());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		PrrArrayConBl conDD = new PrrArrayConBl();
		Thread t1 = new Thread(()->conDD.produce());
		Thread t2 = new Thread(()->conDD.consume());
		
		t1.start();
		t2.start();
	}


}
