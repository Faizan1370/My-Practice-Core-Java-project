package com.faizan.multithread.prac.prac26;

import java.util.concurrent.ArrayBlockingQueue;

public class ProConsArraBls {
	
	int num=1;
	ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(1);
	
	public void produce() {
		while(true) {
			System.out.println("prodcue :"+num);
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
			 System.out.println("Consume"+ queue.take());
			
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		ProConsArraBls arraBls = new ProConsArraBls();
		Thread t1 = new Thread(()->arraBls.produce());
		Thread t2 = new Thread(()->arraBls.consume());
		
		t1.start();
		t2.start();
	}

}
