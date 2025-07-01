package com.faizan.multithread.prac.prac6;

import java.util.concurrent.ArrayBlockingQueue;

public class ProduceComsumeArrayBlockingQuque {
	
	int num=1;
	
	ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<Integer>(1);
	
	
	public void produce()
	{
		while(true) {
			System.out.println("Prudce num :"+num);
			try {
			
			arrayBlockingQueue.offer(num);
			num++;
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void consume()
	{
		while(true) {
			
			try {
			
			Integer take = arrayBlockingQueue.take();
			System.out.println("Consume num :"+take);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		ProduceComsumeArrayBlockingQuque arrayBlockingQuque = new ProduceComsumeArrayBlockingQuque();
		Thread t1 = new Thread(()->arrayBlockingQuque.produce());
		Thread t2 = new Thread(()->arrayBlockingQuque.consume());
		t1.start();
		t2.start();
	}
}
