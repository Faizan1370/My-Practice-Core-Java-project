package com.faizan.collection.prac.prac23;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import com.faizan.collection.prac.prac22.ConcurrentListPrr;

public class CuncurrenListPrr {
	
	//ArrayList<String> list = new ArrayList<String>();
	CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
	
	public void add() {
		for(int i=0;i<50;i++) {
			list.add("St"+i);
		}
	}
	
	public void show() {
		while(true) {
			for(String str:list) {
				System.out.println(str);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		CuncurrenListPrr concurrentListPrr = new CuncurrenListPrr();
		Thread t1= new Thread(()->concurrentListPrr.add());
		Thread t2= new Thread(()->concurrentListPrr.show());
		
		t1.start();
		t2.start();
	}

}
