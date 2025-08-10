package com.faizan.multithread.prac.prac10;

public class ProduceConsumePro {
	int num=1;
	boolean isProduced=false;
	
	
	public synchronized void produce() {
		while(true) {
			while(isProduced) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Produced Number :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProduced=true;
			notify();
		}
	}
	
	public synchronized void consume() {
		while(true) {
			while(!isProduced) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Consumed Number :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProduced=false;
			num++;
			notify();
		}
	}
	
	public static void main(String[] args) {
		ProduceConsumePro consumePro = new ProduceConsumePro();
		Thread t1= new Thread(()->consumePro.produce());
		Thread t2 = new Thread(()->consumePro.consume());
		
		t1.start();
		t2.start();
	}

}
