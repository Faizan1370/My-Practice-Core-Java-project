package com.faizan.multithread.prac.prac6;

public class TrafficeLight {
	
	private static String red="red";
	private static String yellow="yellow";
	private static String green="green";
	
	private static String currentColor=red;
	
	
	public synchronized void printRed() throws InterruptedException{
		while(true) {
			while(!currentColor.equalsIgnoreCase(red)) {
				wait();
			}
			System.out.println("Current color is :"+currentColor);
			Thread.sleep(1000);
			currentColor=yellow;
			notifyAll();
			
		}
	}
	
	public synchronized void printYellow() throws InterruptedException{
		while(true) {
			while(!currentColor.equalsIgnoreCase(yellow)) {
				wait();
			}
			System.out.println("Current color is :"+currentColor);
			Thread.sleep(1000);
			currentColor=green;
			notifyAll();
			
		}
	}
	
	public synchronized void printGreen() throws InterruptedException{
		while(true) {
			while(!currentColor.equalsIgnoreCase(green)) {
				wait();
			}
			System.out.println("Current color is :"+currentColor);
			Thread.sleep(1000);
			currentColor=red;
			notifyAll();
			
		}
	}
	
	public static void main(String[] args) {
		TrafficeLight light = new TrafficeLight();
		
		Thread t1 = new Thread(()->{
			try {
				light.printRed();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		Thread t2 = new Thread(()->{
			try {
				light.printYellow();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		Thread t3 = new Thread(()->{
			try {
				light.printGreen();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		t1.start();
		t2.start();
		t3.start();
		
		
		
	}
}
