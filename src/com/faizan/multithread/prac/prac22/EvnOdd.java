package com.faizan.multithread.prac.prac22;

public class EvnOdd {
	int num=1;
	
	
	public  synchronized void printEven() {
		while(true) {
			while(num % 2!=0) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Even Numbere :"+num);
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
	public  synchronized void printOdd() {
		while(true) {
			while(num % 2==0) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Odd Numbere :"+num);
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
		EvnOdd evnOdd = new EvnOdd();
		Thread t1 = new Thread(()->evnOdd.printEven());
		Thread t2 = new Thread(()->evnOdd.printOdd());
		
		t1.start();
		t2.start();
	}

}
