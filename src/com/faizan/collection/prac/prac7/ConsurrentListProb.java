package com.faizan.collection.prac.prac7;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConsurrentListProb {
	
	ArrayList<String> list = new ArrayList<String>();
	//CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
	
	public  void add() {
		for(int i=0;i<100;i++) {
			list.add("hi"+i);
		}
	}
	
	public void show() {
	    for (String str : list) {
	        System.out.println(str);
	        try {
	            Thread.sleep(100);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public static void main(String[] args) throws InterruptedException {
	    ConsurrentListProb consurrentListProb = new ConsurrentListProb();
	    Thread t1 = new Thread(() -> consurrentListProb.add());
	    t1.start();
	    t1.join(); // Wait for add() to complete

	    Thread t2 = new Thread(() -> consurrentListProb.show());
	    t2.start();
	}


	
	/*
	 * public void show() { while(true) { for(String str:list) {
	 * System.out.println(str); try { Thread.sleep(100); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } } } }
	 */
	
	/*
	 * public static void main(String[] args) { ConsurrentListProb
	 * consurrentListProb = new ConsurrentListProb(); Thread t1= new
	 * Thread(()->consurrentListProb.add()); Thread t2 = new
	 * Thread(()->consurrentListProb.show()); t1.start(); t2.start(); }
	 */
}
