package com.faizan.multithread.prac.prac5;

public class DeadLockProb {
	
	private static String res1="res1";
	private static String res2="res2";
	
	
	public void m1() {
		while(true) {
			synchronized (res1) {
				System.out.println("res 1 lock by :"+Thread.currentThread().getName());
				synchronized (res2) {
					System.out.println("res 2 lock by :"+Thread.currentThread().getName());
				}
			}
		}
	}
	
	public void m2() {
		while(true) {
			synchronized (res2) {
				System.out.println("res 2 lock by :"+Thread.currentThread().getName());
				synchronized (res1) {
					System.out.println("res 1 lock by :"+Thread.currentThread().getName());
				}
			}
		}
	}
	
	public static void main(String[] args) {
		DeadLockProb deadLockProb = new DeadLockProb();
		Thread t1= new Thread(()->deadLockProb.m1());
		Thread t2= new Thread(()->deadLockProb.m2());
		
		t1.start();
		t2.start();
				
	}

}
