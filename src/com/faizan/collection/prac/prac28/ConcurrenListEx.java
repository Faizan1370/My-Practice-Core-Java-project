package com.faizan.collection.prac.prac28;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrenListEx {
	
	//ArrayList<String> list = new ArrayList<String>();
	CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
	
	public void add() {
		for(int i=0;i<50;i++) {
			list.add(i+"hi");
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
		ConcurrenListEx concurrenListEx = new ConcurrenListEx();
		Thread t1 =new Thread(()->concurrenListEx.add());
		Thread t2 = new Thread(()->concurrenListEx.show());
		
		t1.start();
		t2.start();
	}

}
