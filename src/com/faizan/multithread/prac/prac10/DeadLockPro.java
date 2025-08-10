package com.faizan.multithread.prac.prac10;

public class DeadLockPro {
	
	private String res1="res1";
	private String res2="res2";
	
	public void m1() {
		while(true) {
			synchronized (res1) {
				System.out.println("res1 lock by :"+Thread.currentThread().getName());
				synchronized (res2) {
					System.out.println("res2 lock by :"+Thread.currentThread().getName());	
				}
				
			}
		}
	}
   
	public void m2() {
		while(true) {
			synchronized (res2) {
				System.out.println("res2 lock by :"+Thread.currentThread().getName());
				synchronized (res1) {
					System.out.println("res1 lock by :"+Thread.currentThread().getName());	
				}
				
			}
		}
	}
	
	public static void main(String[] args) {
		DeadLockPro deadLockPro = new DeadLockPro();
		Thread t1 = new Thread(()->deadLockPro.m1());
		Thread t2 = new Thread(()->deadLockPro.m2());
		t1.start();
		t2.start();
	}
}
