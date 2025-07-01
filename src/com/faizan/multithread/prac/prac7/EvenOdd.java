package com.faizan.multithread.prac.prac7;

public class EvenOdd {
	
	int num=1;
	int limi=20;
	
	public synchronized void printEven() throws InterruptedException {
		while(num<limi) {
			while(num%2!=0) {
				wait();
			}
			System.out.println("Even No ."+num);
			Thread.sleep(1000);
			num++;
			notify();
				
			}
		}
	
	
	public synchronized void printOdd() throws InterruptedException {
		while(num<limi) {
			while(num%2==0) {
				wait();
			}
			System.out.println("Odd No ."+num);
			Thread.sleep(1000);
			num++;
			notify();
				
			}
		}
	
	
	public static void main(String[] args) {
		EvenOdd consumerProb = new EvenOdd();
		Thread t1= new Thread(()->{
			try {
				consumerProb.printEven();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		Thread t2 = new Thread(()->{
			try {
				consumerProb.printOdd();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		t1.start();
		t2.start();
	}

}
