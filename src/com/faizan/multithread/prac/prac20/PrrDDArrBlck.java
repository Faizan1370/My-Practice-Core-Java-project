package com.faizan.multithread.prac.prac20;

import java.util.concurrent.ArrayBlockingQueue;

public class PrrDDArrBlck {
	int num=1;
	ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(1);
	
	public void produce() {
		while(true) {
			try {
				System.out.println("Produced Num :"+num);
				queue.add(num);
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
				System.out.println("Consumed Num :"+queue.take());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		PrrDDArrBlck dd = new PrrDDArrBlck();
		Thread t1 = new Thread(()->dd.produce());
		Thread t2 = new Thread(()->dd.consume());
		
		t1.start();
		t2.start();
	}

}
