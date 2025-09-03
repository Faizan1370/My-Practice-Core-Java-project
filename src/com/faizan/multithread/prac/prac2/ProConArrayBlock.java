package com.faizan.multithread.prac.prac2;

import java.util.concurrent.ArrayBlockingQueue;

public class ProConArrayBlock {
	int num=1;
	ArrayBlockingQueue<Integer> queue= new ArrayBlockingQueue<Integer>(1);
	
	public void produce() {
		while(true) {
			try {
			System.out.println("produced number :"+num);
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
				System.out.println("consumed number :"+queue.take());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		ProConArrayBlock cconsumeProb = new ProConArrayBlock();
		Thread t1= new Thread(()->cconsumeProb.produce());
		Thread t2= new Thread(()->cconsumeProb.consume());
		
		t1.start();
		t2.start();
	}


}
