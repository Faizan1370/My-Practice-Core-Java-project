package com.faizan.multithread.prac;

public class PrintEvenOddNumbers {
	
	private static int num=1;
	private static int limit=20;
	
	public synchronized void printEven() {
		while(num<this.limit) {
			if(num%2!=0) {
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
		while(num<this.limit) {
			if(num%2==0) {
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
		PrintEvenOddNumbers evenOddNumbers = new PrintEvenOddNumbers();
		Thread t1 = new Thread(()->evenOddNumbers.printEven());
		Thread t2 = new Thread(()->evenOddNumbers.printOdd());
		t1.start();
		t2.start();
	}

}
