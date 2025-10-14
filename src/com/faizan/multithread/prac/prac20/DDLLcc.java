package com.faizan.multithread.prac.prac20;

public class DDLLcc {
	private String res1 ="res1";
	private String res2="res2";
	
	public void m1() {
		while(true) {
			synchronized (res1) {
				System.out.println("res1 locked by :"+Thread.currentThread().getName());
				synchronized (res2) {
					System.out.println("res2 locked by :"+Thread.currentThread().getName());
				}
			}
		}
	}
	
	public void m2() {
		while(true) {
			synchronized (res2) {
				System.out.println("res2 locked by :"+Thread.currentThread().getName());
				synchronized (res1) {
					System.out.println("res1 locked by :"+Thread.currentThread().getName());
				}
			}
		}
	}
	
	public static void main(String[] args) {
		DDLLcc ddlLcc = new DDLLcc();
		Thread t1 = new Thread(()->ddlLcc.m1());
		Thread t2 = new Thread(()->ddlLcc.m2());
		
		t1.start();
		t2.start();
	}

}
