package com.faizan.collection.prac.prac1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConCurreentList1 {
	
	
	//private static List<String> list = new ArrayList<>();
	
	private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
	
	public void add() {
		for(int i=0;i<1000;i++) {
			list.add("val "+i);
		}
	}
	
	public void print() {
		while(true) {
			for(String str:list) {
				System.out.println(str);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		ConCurreentList1 conCurreentList1 = new ConCurreentList1();
		Thread t1 = new Thread(()->conCurreentList1.add());
		Thread t2 = new Thread(()->conCurreentList1.print());
		
		t1.start();
		t2.start();
	}

}
