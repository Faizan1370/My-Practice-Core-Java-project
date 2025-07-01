package com.faizan.collection.prac.prac6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConsurrentListPro {
	
	//static List<String> list = new ArrayList<String>();
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
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		ConsurrentListPro consurrentListPro = new ConsurrentListPro();
		Thread t1 = new Thread(()->consurrentListPro.add());
		Thread t2 = new Thread(()->consurrentListPro.show());
		
		t1.start();
		t2.start();
	}
}
