package com.faizan.multithread.prac.prac19;

import java.util.concurrent.ArrayBlockingQueue;

public class ProDDConArrayBlock {
	int num=1;
	ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(1);
	
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
		ProDDConArrayBlock dcon = new ProDDConArrayBlock();
		Thread t1= new Thread(()->dcon.produce());
		Thread t2 = new Thread(()->dcon.consume());
		
		t1.start();
		t2.start();
	}

}
