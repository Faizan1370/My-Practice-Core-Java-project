package com.faizan.multithread.prac.prac11;

import java.util.concurrent.ArrayBlockingQueue;

import com.faizan.multithread.prac.prac6.ProduceComsumeArrayBlockingQuque;

public class ProduceConsumeArraBlcokQueue {
	
	int num=1;
	ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<Integer>(1);
	
	
	public void produce() {
		while(true) {
			System.out.println("prodcue number :"+num);
			arrayBlockingQueue.add(num);
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
				System.out.println("consumed Number :"+arrayBlockingQueue.take());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		ProduceComsumeArrayBlockingQuque consumePro  = new ProduceComsumeArrayBlockingQuque();
		Thread t1= new Thread(()->consumePro.produce());
		Thread t2= new Thread(()->consumePro.consume());
		
		t1.start();
		t2.start();
	}


}
