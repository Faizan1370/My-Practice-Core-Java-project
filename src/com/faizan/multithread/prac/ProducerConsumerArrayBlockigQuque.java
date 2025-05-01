package com.faizan.multithread.prac;

import java.util.concurrent.ArrayBlockingQueue;

public class ProducerConsumerArrayBlockigQuque {
	
	ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<Integer>(1);
	private int value = 1;
	
	public void produce() {
		while(true) {
			System.out.println("Produced :"+value);
			try {
				arrayBlockingQueue.put(value);
				value++;
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
				System.out.println("Consumed :"+take);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	  public static void main(String[] args) {
	        ProducerConsumerExer pc = new ProducerConsumerExer();

	        Thread producerThread = new Thread(() -> pc.produce());
	        Thread consumerThread = new Thread(() -> pc.consume());

	        producerThread.start();
	        consumerThread.start();
	    }
}
