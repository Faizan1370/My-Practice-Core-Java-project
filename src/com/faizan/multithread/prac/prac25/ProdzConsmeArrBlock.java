package com.faizan.multithread.prac.prac25;

import java.util.concurrent.ArrayBlockingQueue;

public class ProdzConsmeArrBlock {
	int num=1;
	ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(1);
	
	public void produce() {
		while(true) {
			System.out.println("produce num :"+num);
		
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
				System.out.println("consume num: "+queue.take());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		ProdzConsmeArrBlock cons = new ProdzConsmeArrBlock();
		Thread t1 = new Thread(() -> cons.produce());
		Thread t2 = new Thread(() -> cons.consume());

		t1.start();
		t2.start();
	}
}
