package com.faizan.multithread.prac.prac;

public class DeadLockE {
	
	static String res1="res1";
	static String res2="res2";
	
	public void print() {
		synchronized (res1) {
			System.out.println("res1 locked by Thread1 :");
			synchronized (res2) {
				System.out.println("res2 locked by Thread1 :");	
			}
			
		}
	}

	public void print1() {
		synchronized (res2) {
			System.out.println("res2 locked by Thread2 :");
			synchronized (res1) {
				System.out.println("res1 locked by Thread2 :");	
			}
			
		}
	}
	
	public static void main(String[] args) {
		DeadLockE deadLockE = new DeadLockE();
		Thread t1 = new Thread(()->deadLockE.print());
		Thread t2 = new Thread(()->deadLockE.print1());
		
		t1.start();
		t2.start();
	}
}
