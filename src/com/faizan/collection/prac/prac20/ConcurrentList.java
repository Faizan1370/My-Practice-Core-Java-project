package com.faizan.collection.prac.prac20;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentList {
//	ArrayList<String> list = new ArrayList<String>();
	CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
	
	public void add() {
		for(int i=0;i<50;i++) {
			list.add("hi"+i);
		}
	}
	
	public void show() {
		while(true) {
			for(String str:list) {
				System.out.println(str);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		ConcurrentList concurrentList = new ConcurrentList();
		Thread t1= new Thread(()->concurrentList.add());
		Thread t2 = new Thread(()->concurrentList.show());
		t1.start();
		t2.start();
	}

}
