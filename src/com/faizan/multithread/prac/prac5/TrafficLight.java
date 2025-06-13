package com.faizan.multithread.prac.prac5;

public class TrafficLight {
	
	private static String red="red";
	private static String yellow="yellow";
	private static String green="green";
	
	private static String currentColor=red;
	
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
			
			System.out.println("Current color :"+currentColor);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentColor =yellow;
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
			
			System.out.println("Current color :"+currentColor);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentColor =green;
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
			
			System.out.println("Current color :"+currentColor);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentColor =red;
			notifyAll();
		}
	}
	
	public static void main(String[] args) {
		TrafficLight light = new TrafficLight();
		Thread t1= new Thread(()->light.printRed());
		Thread t2= new Thread(()->light.printYellow());
		Thread t3 = new Thread(()->light.printGreen());
		
		t1.start();
		t2.start();
		t3.start();
	}

}
