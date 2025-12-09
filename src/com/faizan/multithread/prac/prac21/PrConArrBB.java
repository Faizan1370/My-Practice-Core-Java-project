package com.faizan.multithread.prac.prac21;

import java.util.concurrent.ArrayBlockingQueue;

public class PrConArrBB {
	int num=1;
	ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(1);
	
	
	public void produce() {
		while(true) {
			System.out.println("Prodcue numvber :"+num);
			queue.add(num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			num++;
		}
	}
	
	public void consume() {
		while(true) {
			try {
				System.out.println("consumed num :"+queue.take());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		PrConArrBB conPr = new PrConArrBB();
		Thread t1 = new Thread(()->conPr.produce());
		Thread t2 = new Thread(()->conPr.consume());
		t1.start();
		t2.start();
	}

}
