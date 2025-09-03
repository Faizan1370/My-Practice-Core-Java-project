package com.faizan.multithread.prac.prac15;

import java.util.concurrent.ArrayBlockingQueue;

public class ProduceConsumeArrayBloking {
	int num=1;
	
	ArrayBlockingQueue<Integer> quque = new ArrayBlockingQueue<Integer>(1);
	
	public void produce() {
		while(true) {
			System.out.println("produced number :"+num);
			quque.add(num);
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
			System.out.println("consumed number :"+quque.take());
			
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	public static void main(String[] args) {
		ProduceConsumeArrayBloking consume = new ProduceConsumeArrayBloking();
		Thread t1= new Thread(()->consume.produce());
		Thread t2= new Thread(()->consume.consume());
		
		t1.start();
		t2.start();
	}

}
