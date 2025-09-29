package com.faizan.collection.prac.prac10;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConsurrentLisPrr {
	//ArrayList<String> list = new ArrayList<>();
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
		ConsurrentLisPrr consurrentLisPrr = new ConsurrentLisPrr();
		Thread t1 = new Thread(()->consurrentLisPrr.add());
		Thread t2 = new Thread(()->consurrentLisPrr.show());
		
		t1.start();
		t2.start();
	}

}
