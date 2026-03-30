package com.faizan.multithread.prac.prac23;

public class EvenOd {
	int num=1;
	int limit=20;
	
	public synchronized void printEven() {
		while(num<limit) {
			while(num % 2 !=0) {
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
	public synchronized void printOdd() {
		while(num<limit) {
			while(num % 2 ==0) {
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
		EvenOd evenOd = new EvenOd();
		Thread t1 = new Thread(()->evenOd.printEven());
		Thread t2 = new Thread(()->evenOd.printOdd());
		
		t1.start();
		t2.start();
	}
}
