package com.faizan.multithread.prac.prac;

import java.util.concurrent.ArrayBlockingQueue;

public class ProdConArrayBlocking {
	
	static ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<Integer>(1);
    static int num=1;
	
	public void produced() {
		while(true) {
			try {
				arrayBlockingQueue.put(num);
				System.out.println("Produced Num :"+num);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			num++;
			
		}
	}
	
	public void consumed() {
		while(true) {
			try {
				Integer take = arrayBlockingQueue.take();
				System.out.println("Consumed Num :"+take);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
	public static void main(String[] args) {
		ProdConArrayBlocking arrayBlocking = new ProdConArrayBlocking();
		Thread t1 = new Thread(()->arrayBlocking.produced());
		Thread t2 = new Thread(()->arrayBlocking.consumed());
		
		t1.start();
		t2.start();
	}
}
