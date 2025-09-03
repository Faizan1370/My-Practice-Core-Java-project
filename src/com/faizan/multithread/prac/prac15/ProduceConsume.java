package com.faizan.multithread.prac.prac15;

public class ProduceConsume {
	
	int num=1;
	boolean isProduce=false;
	
	public synchronized void produce() {
		while(true) {
			while(isProduce) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Produce Number :"+num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isProduce =true;
			notify();
		}
	}
	
	public synchronized void consume() {
		while(true) {
			while(!isProduce) {
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
			isProduce =false;
			num++;
			notify();
		}
	}
	
	public static void main(String[] args) {
		ProduceConsume consume = new ProduceConsume();
		Thread t1= new Thread(()->consume.produce());
		Thread t2= new Thread(()->consume.consume());
		
		t1.start();
		t2.start();
	}

}
