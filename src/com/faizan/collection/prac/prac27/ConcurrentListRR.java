package com.faizan.collection.prac.prac27;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentListRR {
	
	//ArrayList<String> list = new ArrayList<String>();\
	CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
	public void test() {
		CopyOnWriteArrayList<String> list1 = new CopyOnWriteArrayList<String>();
		for(int i=0;i<50;i++) {
			list1.add("hi"+i);
		}
		for(String str:list1) {
			System.out.println(str);
			list1.add("hib");
		}
	}
	
	public void add() {
		for(int i=0;i<50;i++) {
			list.add("hi"+i);
		}
	}
	public void show() {
		while(true) {
			for(String str :list) {
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
		ConcurrentListRR concurrentListRR = new ConcurrentListRR();
		concurrentListRR.test();
		/*
		 * Thread t1 = new Thread(()->concurrentListRR.add()); Thread t2 = new
		 * Thread(()->concurrentListRR.show());
		 * 
		 * t1.start(); t2.start();
		 */
	}

}
