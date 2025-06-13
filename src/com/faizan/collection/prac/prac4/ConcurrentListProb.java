package com.faizan.collection.prac.prac4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentListProb {
	
	//List<String> list = new ArrayList<String>();
	CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
	
	public void add() {
		for(int i=0;i<50;i++) {
			list.add("hi "+i);
		}
	}
	
	public void show() {
		while(true) {
			for(String str:list) {
				System.out.println(str);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		ConcurrentListProb concurrentListProb = new ConcurrentListProb();
		Thread t1= new Thread(()->concurrentListProb.add());
		Thread t2 = new Thread(()->concurrentListProb.show());
		
		t1.start();
		t2.start();
	}

}
