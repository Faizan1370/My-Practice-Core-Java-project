package com.faizan.multithread.prac.prac19;

public class EvenODD {
	int num=1;
	int limit=20;
	
	public synchronized void printEven() {
		while(true) {
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
	
	public synchronized void printOdd() {
		while(true) {
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
		EvenODD evenODD = new EvenODD();
		Thread t1 = new Thread(()->evenODD.printEven());
		Thread t2 = new Thread(()->evenODD.printOdd());
		
		t1.start();
		t2.start();
	}

}
