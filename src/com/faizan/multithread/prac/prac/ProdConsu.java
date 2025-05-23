package com.faizan.multithread.prac.prac;

public class ProdConsu {
	
	static int num=1;
	static boolean isProdue=false;
	
	public synchronized void produce() {
		while(true) {
			while(isProdue) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Produced Number :"+num);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProdue =true;
			notify();
		}
	}
	
	public synchronized void consume() {
		while(true) {
			while(!isProdue) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Consumed Number :"+num);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProdue =false;
			num++;
			notify();
		}
	}
	public static void main(String[] args) {
		ProdConsu consu = new ProdConsu();
		Thread thread = new Thread(()->consu.produce());
		Thread thread2 = new Thread(()->consu.consume());
		thread.start();
		thread2.start();
	}

}
