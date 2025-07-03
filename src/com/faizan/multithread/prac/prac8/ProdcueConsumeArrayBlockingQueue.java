package com.faizan.multithread.prac.prac8;

import java.util.concurrent.ArrayBlockingQueue;

public class ProdcueConsumeArrayBlockingQueue {
	int num=1;
	 ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<Integer>(1);

	 
	 public void produce() {
		 while(true) {
			 System.out.println("produced Num :"+num);
			 arrayBlockingQueue.offer(num);
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
			 Integer take = arrayBlockingQueue.take();
			 System.out.println("consumed Num :"+take);
			
			 
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	 }
	 
	 public static void main(String[] args) {
		 ProdcueConsumeArrayBlockingQueue consumerProb = new ProdcueConsumeArrayBlockingQueue();
			Thread t1= new Thread(()->consumerProb.produce());
			Thread t2= new Thread(()->consumerProb.consume());
			
			t1.start();
			t2.start();
		}
}
