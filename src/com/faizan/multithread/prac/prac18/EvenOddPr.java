package com.faizan.multithread.prac.prac18;

public class EvenOddPr {
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
			System.out.println("Print Even :"+num);
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
			System.out.println("Odd Even :"+num);
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
		EvenOddPr evenOddPr = new EvenOddPr();
		Thread t1= new Thread(()->evenOddPr.printEven());
		Thread t2 = new Thread(()->evenOddPr.printOdd());
		
		t1.start();
		t2.start();
	}

}
