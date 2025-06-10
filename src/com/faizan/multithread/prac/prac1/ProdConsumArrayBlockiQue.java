package com.faizan.multithread.prac.prac1;

import java.util.concurrent.ArrayBlockingQueue;

public class ProdConsumArrayBlockiQue {
	
	static ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<Integer>(1);
    int num =1;
	
	public void produe() {
		
		while(true) {
			System.out.println("Pro num :"+num);
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
				System.out.println("con num :"+take);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		ProdConsumArrayBlockiQue arrayBlockingQueye = new ProdConsumArrayBlockiQue();
		Thread t1 = new Thread(()->arrayBlockingQueye.produe());
		Thread t2 = new Thread(()->arrayBlockingQueye.consume());
		t1.start();
		t2.start();
	}
}
