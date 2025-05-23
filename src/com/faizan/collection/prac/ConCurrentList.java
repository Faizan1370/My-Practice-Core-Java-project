package com.faizan.collection.prac;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConCurrentList {
	
	//static List<String> list = new ArrayList<>();
	static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
	
	public void add() {
		for(int i=0;i<100;i++) {
			list.add("data"+i);
		}
	}
	
	public void read() {
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
		ConCurrentList conCurrentList = new ConCurrentList();
		
		Thread t1 = new Thread(()->conCurrentList.add());
		Thread t2 = new Thread(()->conCurrentList.read());
		
		t1.start();
		t2.start();
	}

}
