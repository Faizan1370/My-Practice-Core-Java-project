package com.faizan.multithread.prac.prac15;

public class EvenOddpr {
	int num=1;
	int limit=20;
	
	public synchronized void evenNumber() {
		while(num<limit) {
			while(num%2!=0) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Even Number :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			num++;
			notify();
		}
	}
	
	public synchronized void oddNumber() {
		while(num<limit) {
			while(num%2==0) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Odd Number :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			num++;
			notify();
		}
	}
	
	public static void main(String[] args) {
		EvenOddpr evenOddpr = new EvenOddpr();
		Thread t1= new Thread(()->evenOddpr.evenNumber());
		Thread t2= new Thread(()->evenOddpr.oddNumber());
		
		t1.start();
		t2.start();
	}

}
