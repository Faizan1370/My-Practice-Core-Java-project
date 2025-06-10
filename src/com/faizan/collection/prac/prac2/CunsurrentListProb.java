package com.faizan.collection.prac.prac2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CunsurrentListProb {
	
	//List<String> list = new ArrayList<String>();
	
	CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
	
	public void add() {
		for(int i=0;i<100;i++) {
			list.add("hi :"+i);
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
		CunsurrentListProb cunsurrentListProb = new CunsurrentListProb();
		Thread t1 = new Thread(()->cunsurrentListProb.add());
		Thread t2 = new Thread(()->cunsurrentListProb.show());
		
		t1.start();
		t2.start();
	}

}
