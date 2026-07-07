package com.faizan.multithread.prac.prac26;

public class DeadLockS {
	private String res1 ="res1";
	private String res2 ="res2";
	
	public void m1() {
		synchronized (res1) {
			System.out.println("res1 locked by :"+Thread.currentThread().getName());
			synchronized (res2) {
				System.out.println("res2 locked by :"+Thread.currentThread().getName());
			}
		}
	}
	public void m2() {
		synchronized (res2) {
			System.out.println("res2 locked by :"+Thread.currentThread().getName());
			synchronized (res1) {
				System.out.println("res1 locked by :"+Thread.currentThread().getName());
			}
		}
	}
	public static void main(String[] args) {
		DeadLockS deadLockS = new DeadLockS();
		Thread t1 = new Thread(()->deadLockS.m1());
		Thread t2 = new Thread(()->deadLockS.m2());
		
		t1.start();
		t2.start();
	}


}
