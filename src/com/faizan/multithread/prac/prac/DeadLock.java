package com.faizan.multithread.prac.prac;

public class DeadLock {
	
	private static String res1="res1";
	private static String res2="res2";
	
	
	public void print() {
		while(true) {
		synchronized (res1) {
			System.out.println("Res1 is locked by Thread1");
			synchronized (res2) {
				System.out.println("Res2 is locked by Thread1");
			}
		}
		}
	}
	
	public void print1() {
		while(true) {
		synchronized (res2) {
			System.out.println("Res2 is locked by Thread2");
			synchronized (res1) {
				System.out.println("Res1 is locked by Thread2");
			}
		}
	}
	}
	
	public static void main(String[] args) {
		DeadLock deadLock = new DeadLock();
		Thread t1= new Thread(()->deadLock.print());
		Thread t2= new Thread(()->deadLock.print1());
		t1.start();
		t2.start();
	}

}
