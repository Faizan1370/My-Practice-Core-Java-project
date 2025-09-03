package com.faizan.multithread.prac.prac16;

import java.util.concurrent.ArrayBlockingQueue;

public class ProduceConsmeArrayBlockng {
	int num=1;
	ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(1);
	
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
		ProduceConsmeArrayBlockng consumePr = new ProduceConsmeArrayBlockng();
		Thread t1= new Thread(()->consumePr.produce());
		Thread t2= new Thread(()->consumePr.consume());
		
		t1.start();
		t2.start();
	}

}
