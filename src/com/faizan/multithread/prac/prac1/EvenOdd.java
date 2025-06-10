package com.faizan.multithread.prac.prac1;

public class EvenOdd {
	
	static int limit=20;
	static int num=1;
	
	
	
	public synchronized void printEven() {
		while(num<limit) {
			while(num%2!=0) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			System.out.println("Even Num :"+num);
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
	
	public synchronized void prinOdd() {
		while(num<limit) {
			while(num%2==0) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			System.out.println("Odd Num :"+num);
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
		EvenOdd evenOdd = new EvenOdd();
		Thread t1 = new Thread(()->evenOdd.printEven());
		Thread t2 = new Thread(()->evenOdd.prinOdd());
		t1.start();
		t2.start();
	}

}
