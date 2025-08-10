package com.faizan.multithread.prac.prac11;

public class TrafficLighht {
	
	private String red="red";
	private String yellow="yellow";
	private String green="green";
	
	private String currentColor=red;
	
	public synchronized void printRed() {
		while(true) {
			while(!currentColor.equalsIgnoreCase(red)) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Current Color :"+currentColor);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentColor=yellow;
			notifyAll();
		}
	}
	
	public synchronized void printYellow() {
		while(true) {
			while(!currentColor.equalsIgnoreCase(yellow)) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Current Color :"+currentColor);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentColor=green;
			notifyAll();
		}
	}

	
	public synchronized void printGreen() {
		while(true) {
			while(!currentColor.equalsIgnoreCase(green)) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Current Color :"+currentColor);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentColor=red;
			notifyAll();
		}
	}
	
	public static void main(String[] args) {
		TrafficLighht lighht = new TrafficLighht();
		Thread t1= new Thread(()->lighht.printRed());
		Thread t2= new Thread(()->lighht.printYellow());
		Thread t3= new Thread(()->lighht.printGreen());
		
		t1.start();
		t2.start();
		t3.start();
	}


}
