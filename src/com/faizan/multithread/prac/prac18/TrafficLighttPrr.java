package com.faizan.multithread.prac.prac18;

public class TrafficLighttPrr {
	
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
			System.out.println("current color is :"+currentColor);
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
			System.out.println("current color is :"+currentColor);
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
			System.out.println("current color is :"+currentColor);
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
		TrafficLighttPrr lighttPrr = new TrafficLighttPrr();
		Thread t1 = new Thread(()->lighttPrr.printRed());
		Thread t2 = new  Thread(()->lighttPrr.printYellow());
		Thread t3 = new Thread(()->lighttPrr.printGreen());
		
		t1.start();
		t2.start();
		t3.start();
	}

}
