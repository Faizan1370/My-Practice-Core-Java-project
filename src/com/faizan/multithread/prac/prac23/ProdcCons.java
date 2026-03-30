package com.faizan.multithread.prac.prac23;

public class ProdcCons {
	int num = 1;
	boolean isProduced = false;

	public synchronized void produce() {
		while (true) {
			while (isProduced) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Produce Number :" + num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProduced = true;
			notify();
		}
	}

	public synchronized void consume() {
		while (true) {
			while (!isProduced) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Consumed Number :" + num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProduced = false;
			num++;
			notify();
		}
	}

	public static void main(String[] args) {
		ProdcCons cons = new ProdcCons();
		Thread t1 = new Thread(() -> cons.produce());
		Thread t2 = new Thread(() -> cons.consume());

		t1.start();
		t2.start();
	}

}
